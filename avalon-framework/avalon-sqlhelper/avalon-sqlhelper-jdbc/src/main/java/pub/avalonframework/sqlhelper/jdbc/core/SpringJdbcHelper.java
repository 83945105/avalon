package pub.avalonframework.sqlhelper.jdbc.core;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.*;
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
public class SpringJdbcHelper implements JdbcHelper, MethodInterceptor {

    private JdbcTemplate jdbcTemplate;

    private JdbcConfiguration jdbcConfiguration;

    private DatabaseType databaseType;

    protected SpringJdbcHelper(JdbcTemplate jdbcTemplate) {
        this(jdbcTemplate, SqlhelperManager.getDefaultConfiguration().getJdbc());
    }

    protected SpringJdbcHelper(JdbcTemplate jdbcTemplate, JdbcConfiguration jdbcConfiguration) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcConfiguration = jdbcConfiguration;
    }

    private DatabaseType findDatabaseType(JdbcTemplate jdbcTemplate) {
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

    public static SpringJdbcHelper getInstance(JdbcTemplate jdbcTemplate) {
        return new SpringJdbcHelper(jdbcTemplate).createProxy();
    }

    public static SpringJdbcHelper getInstance(JdbcTemplate jdbcTemplate, JdbcConfiguration jdbcConfiguration) {
        return new SpringJdbcHelper(jdbcTemplate, jdbcConfiguration).createProxy();
    }

    private SpringJdbcHelper createProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.getClass());
        enhancer.setCallback(this);
        SpringJdbcHelper springJdbcTemplate = (SpringJdbcHelper) enhancer.create(new Class[]{JdbcTemplate.class, JdbcConfiguration.class}, new Object[]{this.jdbcTemplate, this.jdbcConfiguration});
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
    public <R> R queryByPrimaryKey(Object keyValue, SelectSqlBuilder selectSqlBuilder, RowMapper<R> rowMapper) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.queryByPrimaryKey(keyValue);
        List<R> results = this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()), new RowMapperResultSetExtractor<>(rowMapper, 1));
        return JdbcTools.nullableSingleResult(results);
    }

    @Override
    public Map<String, Object> queryByPrimaryKey(Object keyValue, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.queryByPrimaryKey(keyValue);
        List<Map<String, Object>> results = this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()), new RowMapperResultSetExtractor<>(new ColumnMapRowMapper(), 1));
        return JdbcTools.nullableSingleResult(results);
    }

    @Override
    public <R> R queryByPrimaryKey(Object keyValue, SelectSqlBuilder selectSqlBuilder, Class<R> returnType) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.queryByPrimaryKey(keyValue);
        List<R> results = this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()), new RowMapperResultSetExtractor<>(new BeanPropertyRowMapper<>(returnType), 1));
        return JdbcTools.nullableSingleResult(results);
    }

    @Override
    public <R> R queryOne(SelectSqlBuilder selectSqlBuilder, RowMapper<R> rowMapper) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        List<R> results = this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()), new RowMapperResultSetExtractor<>(rowMapper, 1));
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
    public <R> R queryOne(SelectSqlBuilder selectSqlBuilder, Class<R> returnType) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        List<R> results = this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()), new RowMapperResultSetExtractor<>(new BeanPropertyRowMapper<>(returnType), 1));
        return JdbcTools.nullableSingleResult(results);
    }

    @Override
    public <R> List<R> query(SelectSqlBuilder selectSqlBuilder, ResultSetExtractor<List<R>> resultSetExtractor) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()), resultSetExtractor);
    }

    @Override
    public <R> List<R> query(SelectSqlBuilder selectSqlBuilder, RowMapper<R> rowMapper) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()), new RowMapperResultSetExtractor<>(rowMapper));
    }

    @Override
    public List<Map<String, Object>> query(SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()), new RowMapperResultSetExtractor<>(new ColumnMapRowMapper()));
    }

    @Override
    public <R> List<R> query(SelectSqlBuilder selectSqlBuilder, Class<R> returnType) {
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
    public <K, V> Map<K, V> queryColumnOneGroupByColumn(int groupColumnIndex, Class<K> groupColumnType, int valueColumnIndex, Class<V> valueColumnType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new GroupByColumnMapperResultSetExtractor<>(groupColumnIndex, groupColumnType, new ColumnOneGroupValueMapper<>(valueColumnIndex, valueColumnType)));
    }

    @Override
    public <K, V> Map<K, V> queryColumnOneGroupByColumn(String groupColumnName, Class<K> groupColumnType, String valueColumnName, Class<V> valueColumnType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new GroupByColumnMapperResultSetExtractor<>(groupColumnName, groupColumnType, new ColumnOneGroupValueMapper<>(valueColumnName, valueColumnType)));
    }

    @Override
    public <K, V> Map<K, List<V>> queryColumnGroupByColumn(int groupColumnIndex, Class<K> groupColumnType, int valueColumnIndex, Class<V> valueColumnType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new GroupByColumnMapperResultSetExtractor<>(groupColumnIndex, groupColumnType, new ColumnGroupValueMapper<>(valueColumnIndex, valueColumnType)));
    }

    @Override
    public <K, V> Map<K, List<V>> queryColumnGroupByColumn(String groupColumnName, Class<K> groupColumnType, String valueColumnName, Class<V> valueColumnType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new GroupByColumnMapperResultSetExtractor<>(groupColumnName, groupColumnType, new ColumnGroupValueMapper<>(valueColumnName, valueColumnType)));
    }

    @Override
    public <K> Map<K, Map<String, Object>> queryOneGroupByColumn(int groupColumnIndex, Class<K> groupColumnType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new GroupByColumnMapperResultSetExtractor<>(groupColumnIndex, groupColumnType, new RowOneGroupValueMapper()));
    }

    @Override
    public <K> Map<K, Map<String, Object>> queryOneGroupByColumn(String groupColumnName, Class<K> groupColumnType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new GroupByColumnMapperResultSetExtractor<>(groupColumnName, groupColumnType, new RowOneGroupValueMapper()));
    }

    @Override
    public <K, R> Map<K, R> queryOneGroupByColumn(int groupColumnIndex, Class<K> groupColumnType, Class<R> valueBeanType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new GroupByColumnMapperResultSetExtractor<>(groupColumnIndex, groupColumnType, new BeanOneGroupValueMapper<>(valueBeanType)));
    }

    @Override
    public <K, R> Map<K, R> queryOneGroupByColumn(String groupColumnName, Class<K> groupColumnType, Class<R> valueBeanType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new GroupByColumnMapperResultSetExtractor<>(groupColumnName, groupColumnType, new BeanOneGroupValueMapper<>(valueBeanType)));
    }

    @Override
    public <K> Map<K, List<Map<String, Object>>> queryGroupByColumn(int groupColumnIndex, Class<K> groupColumnType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new GroupByColumnMapperResultSetExtractor<>(groupColumnIndex, groupColumnType, new RowGroupValueMapper()));
    }

    @Override
    public <K> Map<K, List<Map<String, Object>>> queryGroupByColumn(String groupColumnName, Class<K> groupColumnType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new GroupByColumnMapperResultSetExtractor<>(groupColumnName, groupColumnType, new RowGroupValueMapper()));
    }

    @Override
    public <K, R> Map<K, List<R>> queryGroupByColumn(int groupColumnIndex, Class<K> groupColumnType, Class<R> valueBeanType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new GroupByColumnMapperResultSetExtractor<>(groupColumnIndex, groupColumnType, new BeanGroupValueMapper<>(valueBeanType)));
    }

    @Override
    public <K, R> Map<K, List<R>> queryGroupByColumn(String groupColumnName, Class<K> groupColumnType, Class<R> valueBeanType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new GroupByColumnMapperResultSetExtractor<>(groupColumnName, groupColumnType, new BeanGroupValueMapper<>(valueBeanType)));
    }

    @Override
    public <R> R queryColumnOne(int columnIndex, Class<R> columnType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        List<R> results = this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new ColumnRowMapperResultSetExtractor<>(columnIndex, columnType, 1));
        return JdbcTools.nullableSingleResult(results);
    }

    @Override
    public <R> R queryColumnOne(String columnName, Class<R> columnType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        List<R> results = this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new ColumnRowMapperResultSetExtractor<>(columnName, columnType, 1));
        return JdbcTools.nullableSingleResult(results);
    }

    @Override
    public <R> List<R> queryColumn(int columnIndex, Class<R> columnType, SelectSqlBuilder selectSqlBuilder) {
        SelectSqlBuilderResult selectSqlBuilderResult = selectSqlBuilder.query();
        return this.jdbcTemplate.query(selectSqlBuilderResult.getPreparedStatementSql(),
                new ArgumentListPreparedStatementSetter(selectSqlBuilderResult.getPreparedStatementArgs()),
                new ColumnRowMapperResultSetExtractor<>(columnIndex, columnType, 1));
    }

    @Override
    public <R> List<R> queryColumn(String columnName, Class<R> columnType, SelectSqlBuilder selectSqlBuilder) {
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