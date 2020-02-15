package pub.avalonframework.sqlhelper.jdbc.core;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.api.config.JdbcConfiguration;
import pub.avalonframework.sqlhelper.core.mgt.SqlhelperManager;
import pub.avalonframework.sqlhelper.core.sqlbuilder.*;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.*;
import pub.avalonframework.sqlhelper.jdbc.core.utils.JdbcTools;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author baichao
 */
public class SpringJdbcTemplate implements JdbcTemplate, MethodInterceptor {

    private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;

    private JdbcConfiguration jdbcConfiguration;

    private DatabaseType databaseType;

    protected SpringJdbcTemplate(org.springframework.jdbc.core.JdbcTemplate jdbcTemplate) {
        this(jdbcTemplate, SqlhelperManager.getDefaultConfiguration().getJdbc());
    }

    protected SpringJdbcTemplate(org.springframework.jdbc.core.JdbcTemplate jdbcTemplate, JdbcConfiguration jdbcConfiguration) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcConfiguration = jdbcConfiguration;
    }

    private DatabaseType findDatabaseType(org.springframework.jdbc.core.JdbcTemplate jdbcTemplate) {
        DataSource dataSource = jdbcTemplate.getDataSource();
        if (dataSource == null) {
            throw new DataSourceIsNotSetException();
        }
        Connection connection;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new CannotGetJdbcConnectionException(e.getMessage(), e);
        }
        String databaseProductName;
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            databaseProductName = databaseMetaData.getDatabaseProductName();
        } catch (SQLException e) {
            throw new DataAccessResourceFailureException(e.getMessage(), e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return DatabaseType.valueOf(databaseProductName.toUpperCase());
    }

    public static SpringJdbcTemplate getInstance(org.springframework.jdbc.core.JdbcTemplate jdbcTemplate) {
        return new SpringJdbcTemplate(jdbcTemplate).createProxy();
    }

    public static SpringJdbcTemplate getInstance(org.springframework.jdbc.core.JdbcTemplate jdbcTemplate, JdbcConfiguration jdbcConfiguration) {
        return new SpringJdbcTemplate(jdbcTemplate, jdbcConfiguration).createProxy();
    }

    private SpringJdbcTemplate createProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.getClass());
        enhancer.setCallback(this);
        SpringJdbcTemplate springJdbcTemplate = (SpringJdbcTemplate) enhancer.create(new Class[]{org.springframework.jdbc.core.JdbcTemplate.class, JdbcConfiguration.class}, new Object[]{this.jdbcTemplate, this.jdbcConfiguration});
        DatabaseType databaseType = findDatabaseType(this.jdbcTemplate);
        this.databaseType = databaseType;
        springJdbcTemplate.databaseType = databaseType;
        return springJdbcTemplate;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        for (Object arg : args) {
            if (arg instanceof SqlBuilder) {
                ((SqlBuilder) arg).getConfiguration().setDatabaseType(this.getDatabaseType());
                return methodProxy.invokeSuper(obj, args);
            }
        }
        return methodProxy.invokeSuper(obj, args);
    }

    @Override
    public DatabaseType getDatabaseType() {
        return databaseType;
    }

    @Override
    public void copyTable(String targetTableName, boolean copyData, TableSqlBuilder tableSqlBuilder) {
        TableSqlBuilderResult tableSqlBuilderResult = tableSqlBuilder.copyTable(targetTableName, copyData);
        this.jdbcTemplate.update(tableSqlBuilderResult.getPreparedStatementSql());
    }

    @Override
    public void deleteTable(TableSqlBuilder tableSqlBuilder) {
        TableSqlBuilderResult tableSqlBuilderResult = tableSqlBuilder.deleteTable();
        this.jdbcTemplate.update(tableSqlBuilderResult.getPreparedStatementSql());
    }

    @Override
    public void renameTable(String targetTableName, TableSqlBuilder tableSqlBuilder) {
        TableSqlBuilderResult tableSqlBuilderResult = tableSqlBuilder.renameTable(targetTableName);
        this.jdbcTemplate.update(tableSqlBuilderResult.getPreparedStatementSql());
    }

    @Override
    public boolean isTableExist(TableSqlBuilder tableSqlBuilder) {
        TableSqlBuilderResult tableSqlBuilderResult = tableSqlBuilder.isTableExist();
        List<Map<String, Object>> results = this.jdbcTemplate.query(tableSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(tableSqlBuilderResult.getPreparedStatementArgs()), new RowMapperResultSetExtractor<>(new ColumnMapRowMapper(), 1));
        return results != null && results.size() == 1 && results.get(0).size() == 1;
    }

    @Override
    public int insertJavaBean(Object javaBean, InsertSqlBuilder insertSqlBuilder) {
        InsertSqlBuilderResult insertSqlBuilderResult = insertSqlBuilder.insertJavaBean(javaBean);
        return this.jdbcTemplate.update(insertSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(insertSqlBuilderResult.getPreparedStatementArgs()));
    }

    @Override
    public int insertJavaBeanSelective(Object javaBean, InsertSqlBuilder insertSqlBuilder) {
        InsertSqlBuilderResult insertSqlBuilderResult = insertSqlBuilder.insertJavaBeanSelective(javaBean);
        return this.jdbcTemplate.update(insertSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(insertSqlBuilderResult.getPreparedStatementArgs()));
    }

    @Override
    public int batchInsertJavaBeans(Collection<?> javaBeans, InsertSqlBuilder insertSqlBuilder) {
        InsertSqlBuilderResult insertSqlBuilderResult = insertSqlBuilder.batchInsertJavaBeans(javaBeans);
        return this.jdbcTemplate.update(insertSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(insertSqlBuilderResult.getPreparedStatementArgs()));
    }

    @Override
    public Map<String, Object> queryByPrimaryKey(Object keyValue, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.queryByPrimaryKey(keyValue);
        List<Map<String, Object>> results = this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()), new RowMapperResultSetExtractor<>(new ColumnMapRowMapper(), 1));
        return JdbcTools.nullableSingleResult(results);
    }

    @Override
    public <T> T queryByPrimaryKey(Object keyValue, SelectSqlBuilder selectSqlBuilder, Class<T> returnType) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.queryByPrimaryKey(keyValue);
        List<T> results = this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()), new RowMapperResultSetExtractor<>(new BeanPropertyRowMapper<>(returnType), 1));
        return JdbcTools.nullableSingleResult(results);
    }

    @Override
    public Map<String, Object> queryOne(SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        List<Map<String, Object>> results = this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()), new RowMapperResultSetExtractor<>(new ColumnMapRowMapper(), 1));
        return JdbcTools.nullableSingleResult(results);
    }

    @Override
    public <T> T queryOne(SelectSqlBuilder selectSqlBuilder, Class<T> returnType) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        List<T> results = this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()), new RowMapperResultSetExtractor<>(new BeanPropertyRowMapper<>(returnType), 1));
        return JdbcTools.nullableSingleResult(results);
    }

    @Override
    public List<Map<String, Object>> query(SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()), new RowMapperResultSetExtractor<>(new ColumnMapRowMapper()));
    }

    @Override
    public <T> List<T> query(SelectSqlBuilder selectSqlBuilder, Class<T> returnType) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()), new RowMapperResultSetExtractor<>(new BeanPropertyRowMapper<>(returnType)));
    }

    @Override
    public long queryCount(SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.queryCount();
        List<Long> results = this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new RowMapperResultSetExtractor<>(new SingleColumnRowMapper<>(Long.class), 1));
        return JdbcTools.countSingleResult(results);
    }

    @Override
    public <K, V> Map<K, V> queryColumnOneGroupByColumn(int keyColumnIndex, Class<K> keyColumnType, int valueColumnIndex, Class<V> valueColumnType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new GroupByColumnMapperResultSetExtractor<>(keyColumnIndex, keyColumnType, new ColumnOneGroupValueMapper<>(valueColumnIndex, valueColumnType)));
    }

    @Override
    public <K, V> Map<K, V> queryColumnOneGroupByColumn(String keyColumnName, Class<K> keyColumnType, String valueColumnName, Class<V> valueColumnType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new GroupByColumnMapperResultSetExtractor<>(keyColumnName, keyColumnType, new ColumnOneGroupValueMapper<>(valueColumnName, valueColumnType)));
    }

    @Override
    public <K, V> Map<K, List<V>> queryColumnGroupByColumn(int keyColumnIndex, Class<K> keyColumnType, int valueColumnIndex, Class<V> valueColumnType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new GroupByColumnMapperResultSetExtractor<>(keyColumnIndex, keyColumnType, new ColumnGroupValueMapper<>(valueColumnIndex, valueColumnType)));
    }

    @Override
    public <K, V> Map<K, List<V>> queryColumnGroupByColumn(String keyColumnName, Class<K> keyColumnType, String valueColumnName, Class<V> valueColumnType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new GroupByColumnMapperResultSetExtractor<>(keyColumnName, keyColumnType, new ColumnGroupValueMapper<>(valueColumnName, valueColumnType)));
    }

    @Override
    public <K> Map<K, Map<String, Object>> queryOneGroupByColumn(int keyColumnIndex, Class<K> keyColumnType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new GroupByColumnMapperResultSetExtractor<>(keyColumnIndex, keyColumnType, new RowOneGroupValueMapper()));
    }

    @Override
    public <K> Map<K, Map<String, Object>> queryOneGroupByColumn(String keyColumnName, Class<K> keyColumnType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new GroupByColumnMapperResultSetExtractor<>(keyColumnName, keyColumnType, new RowOneGroupValueMapper()));
    }

    @Override
    public <K, T> Map<K, T> queryOneGroupByColumn(int keyColumnIndex, Class<K> keyColumnType, Class<T> valueBeanType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new GroupByColumnMapperResultSetExtractor<>(keyColumnIndex, keyColumnType, new BeanOneGroupValueMapper<>(valueBeanType)));
    }

    @Override
    public <K, T> Map<K, T> queryOneGroupByColumn(String keyColumnName, Class<K> keyColumnType, Class<T> valueBeanType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new GroupByColumnMapperResultSetExtractor<>(keyColumnName, keyColumnType, new BeanOneGroupValueMapper<>(valueBeanType)));
    }

    @Override
    public <K> Map<K, List<Map<String, Object>>> queryGroupByColumn(int keyColumnIndex, Class<K> keyColumnType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new GroupByColumnMapperResultSetExtractor<>(keyColumnIndex, keyColumnType, new RowGroupValueMapper()));
    }

    @Override
    public <K> Map<K, List<Map<String, Object>>> queryGroupByColumn(String keyColumnName, Class<K> keyColumnType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new GroupByColumnMapperResultSetExtractor<>(keyColumnName, keyColumnType, new RowGroupValueMapper()));
    }

    @Override
    public <K, T> Map<K, List<T>> queryGroupByColumn(int keyColumnIndex, Class<K> keyColumnType, Class<T> valueBeanType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new GroupByColumnMapperResultSetExtractor<>(keyColumnIndex, keyColumnType, new BeanGroupValueMapper<>(valueBeanType)));
    }

    @Override
    public <K, T> Map<K, List<T>> queryGroupByColumn(String keyColumnName, Class<K> keyColumnType, Class<T> valueBeanType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new GroupByColumnMapperResultSetExtractor<>(keyColumnName, keyColumnType, new BeanGroupValueMapper<>(valueBeanType)));
    }

    @Override
    public <T> T queryColumnOne(int columnIndex, Class<T> columnType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        List<T> results = this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new ColumnRowMapperResultSetExtractor<>(columnIndex, columnType, 1));
        return JdbcTools.nullableSingleResult(results);
    }

    @Override
    public <T> T queryColumnOne(String columnName, Class<T> columnType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        List<T> results = this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new ColumnRowMapperResultSetExtractor<>(columnName, columnType, 1));
        return JdbcTools.nullableSingleResult(results);
    }

    @Override
    public <T> List<T> queryColumn(int columnIndex, Class<T> columnType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new ColumnRowMapperResultSetExtractor<>(columnIndex, columnType, 1));
    }

    @Override
    public <T> List<T> queryColumn(String columnName, Class<T> columnType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new ColumnRowMapperResultSetExtractor<>(columnName, columnType, 1));
    }

    @Override
    public int updateJavaBeanByPrimaryKey(Object keyValue, Object javaBean, UpdateSqlBuilder updateSqlBuilder) {
        UpdateSqlBuilderResult updateSqlBuilderResult = updateSqlBuilder.updateJavaBeanByPrimaryKey(keyValue, javaBean);
        return this.jdbcTemplate.update(updateSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(updateSqlBuilderResult.getPreparedStatementArgs()));
    }

    @Override
    public int updateJavaBeanByPrimaryKeySelective(Object keyValue, Object javaBean, UpdateSqlBuilder updateSqlBuilder) {
        UpdateSqlBuilderResult updateSqlBuilderResult = updateSqlBuilder.updateJavaBeanByPrimaryKeySelective(keyValue, javaBean);
        return this.jdbcTemplate.update(updateSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(updateSqlBuilderResult.getPreparedStatementArgs()));
    }

    @Override
    public int updateJavaBean(Object javaBean, UpdateSqlBuilder updateSqlBuilder) {
        UpdateSqlBuilderResult updateSqlBuilderResult = updateSqlBuilder.updateJavaBean(javaBean);
        return this.jdbcTemplate.update(updateSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(updateSqlBuilderResult.getPreparedStatementArgs()));
    }

    @Override
    public int updateJavaBeanSelective(Object javaBean, UpdateSqlBuilder updateSqlBuilder) {
        UpdateSqlBuilderResult updateSqlBuilderResult = updateSqlBuilder.updateJavaBeanSelective(javaBean);
        return this.jdbcTemplate.update(updateSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(updateSqlBuilderResult.getPreparedStatementArgs()));
    }

    @Override
    public int batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans, UpdateSqlBuilder updateSqlBuilder) {
        UpdateSqlBuilderResult updateSqlBuilderResult = updateSqlBuilder.batchUpdateJavaBeansByPrimaryKeys(javaBeans);
        return this.jdbcTemplate.update(updateSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(updateSqlBuilderResult.getPreparedStatementArgs()));
    }

    @Override
    public int updateOrInsertJavaBeans(Collection<?> javaBeans, UpdateSqlBuilder updateSqlBuilder) {
        UpdateSqlBuilderResult updateSqlBuilderResult = updateSqlBuilder.updateOrInsertJavaBeans(javaBeans);
        return this.jdbcTemplate.update(updateSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(updateSqlBuilderResult.getPreparedStatementArgs()));
    }

    @Override
    public int deleteByPrimaryKey(Object keyValue, DeleteSqlBuilder deleteSqlBuilder) {
        DeleteSqlBuilderResult deleteSqlBuilderResult = deleteSqlBuilder.deleteByPrimaryKey(keyValue);
        return this.jdbcTemplate.update(deleteSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(deleteSqlBuilderResult.getPreparedStatementArgs()));
    }

    @Override
    public int batchDeleteByPrimaryKeys(Collection<?> keyValues, DeleteSqlBuilder deleteSqlBuilder) {
        DeleteSqlBuilderResult deleteSqlBuilderResult = deleteSqlBuilder.batchDeleteByPrimaryKeys(keyValues);
        return this.jdbcTemplate.update(deleteSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(deleteSqlBuilderResult.getPreparedStatementArgs()));
    }

    @Override
    public int delete(DeleteSqlBuilder deleteSqlBuilder) {
        DeleteSqlBuilderResult deleteSqlBuilderResult = deleteSqlBuilder.delete();
        return this.jdbcTemplate.update(deleteSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(deleteSqlBuilderResult.getPreparedStatementArgs()));
    }
}