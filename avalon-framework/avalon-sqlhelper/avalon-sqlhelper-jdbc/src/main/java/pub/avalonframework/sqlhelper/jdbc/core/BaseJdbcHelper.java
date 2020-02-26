package pub.avalonframework.sqlhelper.jdbc.core;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.sqlbuilder.DeleteSqlBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.SelectSqlBuilder;
import pub.avalonframework.sqlhelper.jdbc.core.engine.JdbcInsertEngine;
import pub.avalonframework.sqlhelper.jdbc.core.engine.JdbcSelectEngine;
import pub.avalonframework.sqlhelper.jdbc.core.engine.JdbcTableEngine;
import pub.avalonframework.sqlhelper.jdbc.core.factory.JdbcFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author baichao
 */
public abstract class BaseJdbcHelper<T, H extends TableHelper<H, HC, HO, HW, HG, HH, HS>,
        HC extends ColumnHelper<HC>,
        HO extends OnHelper<HO>,
        HW extends WhereHelper<HW>,
        HG extends GroupHelper<HG>,
        HH extends HavingHelper<HH>,
        HS extends SortHelper<HS>> {

    protected JdbcHelper jdbcHelper;

    protected JdbcFactory jdbcFactory;

    private Class<H> tableHelperClass;

    public void copyTable(String targetTableName, boolean copyData) {
        jdbcHelper.copyTable(targetTableName, copyData, new JdbcTableEngine<>(jdbcHelper, tableHelperClass));
    }

    public void deleteTable() {
        jdbcHelper.deleteTable(new JdbcTableEngine<>(jdbcHelper, tableHelperClass));
    }

    public void renameTable(String targetTableName) {
        jdbcHelper.renameTable(targetTableName, new JdbcTableEngine<>(jdbcHelper, tableHelperClass));
    }

    public boolean isTableExist() {
        return jdbcHelper.isTableExist(new JdbcTableEngine<>(jdbcHelper, tableHelperClass));
    }

    public int insertJavaBean(T javaBean) {
        return jdbcHelper.insertJavaBean(javaBean, new JdbcInsertEngine<>(jdbcHelper, tableHelperClass));
    }

    public int insertJavaBeanSelective(T javaBean) {
        return 0;
    }

    public int batchInsertJavaBeans(Collection<T> javaBeans) {
        return 0;
    }


    public <R> R queryByPrimaryKey(Object keyValue, RowMapper<R> rowMapper) {
        return null;
    }


    public T queryByPrimaryKey(Object keyValue) {
        return null;
    }


    public <R> R queryByPrimaryKey(Object keyValue, Class<R> returnType) {
        return null;
    }


    public <R> R queryOne(SelectSqlBuilder selectSqlBuilder, RowMapper<R> rowMapper) {
        return null;
    }


    public Map<String, Object> queryOne(Function<JdbcSelectEngine<H, HC, HO, HW, HG, HH, HS>, SelectSqlBuilder> function) {
        return jdbcHelper.queryOne(function.apply(new JdbcSelectEngine<>(jdbcHelper, tableHelperClass)));
    }

    public <R> R queryOne(SelectSqlBuilder selectSqlBuilder, Class<R> returnType) {
        return null;
    }


    public <R> List<R> query(SelectSqlBuilder selectSqlBuilder, ResultSetExtractor<List<R>> resultSetExtractor) {
        return null;
    }


    public <R> List<R> query(SelectSqlBuilder selectSqlBuilder, RowMapper<R> rowMapper) {
        return null;
    }


    public List<Map<String, Object>> query(SelectSqlBuilder selectSqlBuilder) {
        return null;
    }


    public <R> List<R> query(SelectSqlBuilder selectSqlBuilder, Class<R> returnType) {
        return null;
    }


    public long queryCount(SelectSqlBuilder selectSqlBuilder) {
        return 0;
    }


    public <K, V> Map<K, V> queryColumnOneGroupByColumn(int keyColumnIndex, Class<K> keyColumnType, int valueColumnIndex, Class<V> valueColumnType, SelectSqlBuilder selectSqlBuilder) {
        return null;
    }


    public <K, V> Map<K, V> queryColumnOneGroupByColumn(String keyColumnName, Class<K> keyColumnType, String valueColumnName, Class<V> valueColumnType, SelectSqlBuilder selectSqlBuilder) {
        return null;
    }


    public <K, V> Map<K, List<V>> queryColumnGroupByColumn(int keyColumnIndex, Class<K> keyColumnType, int valueColumnIndex, Class<V> valueColumnType, SelectSqlBuilder selectSqlBuilder) {
        return null;
    }


    public <K, V> Map<K, List<V>> queryColumnGroupByColumn(String keyColumnName, Class<K> keyColumnType, String valueColumnName, Class<V> valueColumnType, SelectSqlBuilder selectSqlBuilder) {
        return null;
    }


    public <K> Map<K, Map<String, Object>> queryOneGroupByColumn(int keyColumnIndex, Class<K> keyColumnType, SelectSqlBuilder selectSqlBuilder) {
        return null;
    }


    public <K> Map<K, Map<String, Object>> queryOneGroupByColumn(String keyColumnName, Class<K> keyColumnType, SelectSqlBuilder selectSqlBuilder) {
        return null;
    }


    public <K, R> Map<K, R> queryOneGroupByColumn(int keyColumnIndex, Class<K> keyColumnType, Class<R> valueBeanType, SelectSqlBuilder selectSqlBuilder) {
        return null;
    }


    public <K, R> Map<K, R> queryOneGroupByColumn(String keyColumnName, Class<K> keyColumnType, Class<R> valueBeanType, SelectSqlBuilder selectSqlBuilder) {
        return null;
    }


    public <K> Map<K, List<Map<String, Object>>> queryGroupByColumn(int keyColumnIndex, Class<K> keyColumnType, SelectSqlBuilder selectSqlBuilder) {
        return null;
    }


    public <K> Map<K, List<Map<String, Object>>> queryGroupByColumn(String keyColumnName, Class<K> keyColumnType, SelectSqlBuilder selectSqlBuilder) {
        return null;
    }


    public <K, R> Map<K, List<R>> queryGroupByColumn(int keyColumnIndex, Class<K> keyColumnType, Class<R> valueBeanType, SelectSqlBuilder selectSqlBuilder) {
        return null;
    }


    public <K, R> Map<K, List<R>> queryGroupByColumn(String keyColumnName, Class<K> keyColumnType, Class<R> valueBeanType, SelectSqlBuilder selectSqlBuilder) {
        return null;
    }


    public <R> R queryColumnOne(int columnIndex, Class<R> columnType, SelectSqlBuilder selectSqlBuilder) {
        return null;
    }


    public <R> R queryColumnOne(String columnName, Class<R> columnType, SelectSqlBuilder selectSqlBuilder) {
        return null;
    }


    public <R> List<R> queryColumn(int columnIndex, Class<R> columnType, SelectSqlBuilder selectSqlBuilder) {
        return null;
    }


    public <R> List<R> queryColumn(String columnName, Class<R> columnType, SelectSqlBuilder selectSqlBuilder) {
        return null;
    }


    public int updateJavaBeanByPrimaryKey(Object keyValue, T javaBean) {
        return 0;
    }


    public int updateJavaBeanByPrimaryKeySelective(Object keyValue, T javaBean) {
        return 0;
    }


    public int updateJavaBean(T javaBean) {
        return 0;
    }


    public int updateJavaBeanSelective(T javaBean) {
        return 0;
    }


    public int batchUpdateJavaBeansByPrimaryKeys(Collection<T> javaBeans) {
        return 0;
    }


    public int updateOrInsertJavaBeans(Collection<T> javaBeans) {
        return 0;
    }


    public int deleteByPrimaryKey(Object keyValue) {
        return 0;
    }


    public int batchDeleteByPrimaryKeys(Collection<?> keyValues) {
        return 0;
    }


    public int delete(DeleteSqlBuilder deleteSqlBuilder) {
        return 0;
    }
}