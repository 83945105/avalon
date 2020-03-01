package pub.avalonframework.sqlhelper.jdbc.core.spring.jdbc;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import pub.avalonframework.sqlhelper.core.callback.ColumnCallback;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.sqlbuilder.DeleteSqlBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.SelectSqlBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.UpdateSqlBuilder;
import pub.avalonframework.sqlhelper.jdbc.core.JdbcHelper;
import pub.avalonframework.sqlhelper.jdbc.core.engine.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author baichao
 */
public abstract class BaseJdbc<T, H extends TableHelper<H, HC, HO, HW, HG, HH, HS>,
        HC extends ColumnHelper<HC>,
        HO extends OnHelper<HO>,
        HW extends WhereHelper<HW>,
        HG extends GroupHelper<HG>,
        HH extends HavingHelper<HH>,
        HS extends SortHelper<HS>> {

    private Class<T> beanType;

    private Class<H> tableHelperClass;

    protected JdbcHelper jdbcHelper;

    public BaseJdbc() {
    }

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

    public int insertJavaBean(T javaBean, ColumnCallback<HC> columnCallback) {
        return jdbcHelper.insertJavaBean(javaBean, new JdbcInsertEngine<>(jdbcHelper, tableHelperClass).insert(columnCallback));
    }

    public int insertJavaBeanSelective(T javaBean) {
        return jdbcHelper.insertJavaBeanSelective(javaBean, new JdbcInsertEngine<>(jdbcHelper, tableHelperClass));
    }

    public int batchInsertJavaBeans(Collection<T> javaBeans) {
        return jdbcHelper.batchInsertJavaBeans(javaBeans, new JdbcInsertEngine<>(jdbcHelper, tableHelperClass));
    }

    public <R> R queryByPrimaryKey(Object keyValue, RowMapper<R> rowMapper) {
        return jdbcHelper.queryByPrimaryKey(keyValue, new JdbcSelectEngine<>(jdbcHelper, tableHelperClass), rowMapper);
    }

    public T queryByPrimaryKey(Object keyValue) {
        return jdbcHelper.queryByPrimaryKey(keyValue, new JdbcSelectEngine<>(jdbcHelper, tableHelperClass), beanType);
    }

    public <R> R queryByPrimaryKey(Object keyValue, Class<R> returnType) {
        return jdbcHelper.queryByPrimaryKey(keyValue, new JdbcSelectEngine<>(jdbcHelper, tableHelperClass), returnType);
    }

    public interface Select<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
            TC extends ColumnHelper<TC>,
            TO extends OnHelper<TO>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> {
        SelectSqlBuilder apply(JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> engine);
    }

    public <R> R queryOne(Select<H, HC, HO, HW, HG, HH, HS> select, RowMapper<R> rowMapper) {
        return jdbcHelper.queryOne(select.apply(new JdbcSelectEngine<>(jdbcHelper, tableHelperClass)), rowMapper);
    }

    public T queryOne(Select<H, HC, HO, HW, HG, HH, HS> select) {
        return jdbcHelper.queryOne(select.apply(new JdbcSelectEngine<>(jdbcHelper, tableHelperClass)), beanType);
    }

    public <R> R queryOne(Select<H, HC, HO, HW, HG, HH, HS> select, Class<R> returnType) {
        return jdbcHelper.queryOne(select.apply(new JdbcSelectEngine<>(jdbcHelper, tableHelperClass)), returnType);
    }

    public <R> List<R> query(Select<H, HC, HO, HW, HG, HH, HS> select, ResultSetExtractor<List<R>> resultSetExtractor) {
        return jdbcHelper.query(select.apply(new JdbcSelectEngine<>(jdbcHelper, tableHelperClass)), resultSetExtractor);
    }

    public <R> List<R> query(Select<H, HC, HO, HW, HG, HH, HS> select, RowMapper<R> rowMapper) {
        return jdbcHelper.query(select.apply(new JdbcSelectEngine<>(jdbcHelper, tableHelperClass)), rowMapper);
    }

    public List<T> query(Select<H, HC, HO, HW, HG, HH, HS> select) {
        return jdbcHelper.query(select.apply(new JdbcSelectEngine<>(jdbcHelper, tableHelperClass)), beanType);
    }

    public <R> List<R> query(Select<H, HC, HO, HW, HG, HH, HS> select, Class<R> returnType) {
        return jdbcHelper.query(select.apply(new JdbcSelectEngine<>(jdbcHelper, tableHelperClass)), returnType);
    }

    public long queryCount(Select<H, HC, HO, HW, HG, HH, HS> select) {
        return jdbcHelper.queryCount(select.apply(new JdbcSelectEngine<>(jdbcHelper, tableHelperClass)));
    }

    public <K, V> Map<K, V> queryColumnOneGroupByColumn(int groupColumnIndex, Class<K> groupColumnType, int valueColumnIndex, Class<V> valueColumnType, Select<H, HC, HO, HW, HG, HH, HS> select) {
        return jdbcHelper.queryColumnOneGroupByColumn(groupColumnIndex, groupColumnType, valueColumnIndex, valueColumnType, select.apply(new JdbcSelectEngine<>(jdbcHelper, tableHelperClass)));
    }

    public <K, V> Map<K, V> queryColumnOneGroupByColumn(String groupColumnName, Class<K> groupColumnType, String valueColumnName, Class<V> valueColumnType, Select<H, HC, HO, HW, HG, HH, HS> select) {
        return jdbcHelper.queryColumnOneGroupByColumn(groupColumnName, groupColumnType, valueColumnName, valueColumnType, select.apply(new JdbcSelectEngine<>(jdbcHelper, tableHelperClass)));
    }

    public <K, V> Map<K, List<V>> queryColumnGroupByColumn(int groupColumnIndex, Class<K> groupColumnType, int valueColumnIndex, Class<V> valueColumnType, Select<H, HC, HO, HW, HG, HH, HS> select) {
        return jdbcHelper.queryColumnGroupByColumn(groupColumnIndex, groupColumnType, valueColumnIndex, valueColumnType, select.apply(new JdbcSelectEngine<>(jdbcHelper, tableHelperClass)));
    }

    public <K, V> Map<K, List<V>> queryColumnGroupByColumn(String groupColumnName, Class<K> groupColumnType, String valueColumnName, Class<V> valueColumnType, Select<H, HC, HO, HW, HG, HH, HS> select) {
        return jdbcHelper.queryColumnGroupByColumn(groupColumnName, groupColumnType, valueColumnName, valueColumnType, select.apply(new JdbcSelectEngine<>(jdbcHelper, tableHelperClass)));
    }

    public <K> Map<K, T> queryOneGroupByColumn(int groupColumnIndex, Class<K> groupColumnType, Select<H, HC, HO, HW, HG, HH, HS> select) {
        return jdbcHelper.queryOneGroupByColumn(groupColumnIndex, groupColumnType, beanType, select.apply(new JdbcSelectEngine<>(jdbcHelper, tableHelperClass)));
    }

    public <K> Map<K, T> queryOneGroupByColumn(String groupColumnName, Class<K> groupColumnType, Select<H, HC, HO, HW, HG, HH, HS> select) {
        return jdbcHelper.queryOneGroupByColumn(groupColumnName, groupColumnType, beanType, select.apply(new JdbcSelectEngine<>(jdbcHelper, tableHelperClass)));
    }

    public <K, R> Map<K, R> queryOneGroupByColumn(int groupColumnIndex, Class<K> groupColumnType, Class<R> valueBeanType, Select<H, HC, HO, HW, HG, HH, HS> select) {
        return jdbcHelper.queryOneGroupByColumn(groupColumnIndex, groupColumnType, valueBeanType, select.apply(new JdbcSelectEngine<>(jdbcHelper, tableHelperClass)));
    }

    public <K, R> Map<K, R> queryOneGroupByColumn(String groupColumnName, Class<K> groupColumnType, Class<R> valueBeanType, Select<H, HC, HO, HW, HG, HH, HS> select) {
        return jdbcHelper.queryOneGroupByColumn(groupColumnName, groupColumnType, valueBeanType, select.apply(new JdbcSelectEngine<>(jdbcHelper, tableHelperClass)));
    }

    public <K> Map<K, List<T>> queryGroupByColumn(int groupColumnIndex, Class<K> groupColumnType, Select<H, HC, HO, HW, HG, HH, HS> select) {
        return jdbcHelper.queryGroupByColumn(groupColumnIndex, groupColumnType, beanType, select.apply(new JdbcSelectEngine<>(jdbcHelper, tableHelperClass)));
    }

    public <K> Map<K, List<T>> queryGroupByColumn(String groupColumnName, Class<K> groupColumnType, Select<H, HC, HO, HW, HG, HH, HS> select) {
        return jdbcHelper.queryGroupByColumn(groupColumnName, groupColumnType, beanType, select.apply(new JdbcSelectEngine<>(jdbcHelper, tableHelperClass)));
    }

    public <K, R> Map<K, List<R>> queryGroupByColumn(int groupColumnIndex, Class<K> groupColumnType, Class<R> valueBeanType, Select<H, HC, HO, HW, HG, HH, HS> select) {
        return jdbcHelper.queryGroupByColumn(groupColumnIndex, groupColumnType, valueBeanType, select.apply(new JdbcSelectEngine<>(jdbcHelper, tableHelperClass)));
    }

    public <K, R> Map<K, List<R>> queryGroupByColumn(String groupColumnName, Class<K> groupColumnType, Class<R> valueBeanType, Select<H, HC, HO, HW, HG, HH, HS> select) {
        return jdbcHelper.queryGroupByColumn(groupColumnName, groupColumnType, valueBeanType, select.apply(new JdbcSelectEngine<>(jdbcHelper, tableHelperClass)));
    }

    public <R> R queryColumnOne(int columnIndex, Class<R> columnType, Select<H, HC, HO, HW, HG, HH, HS> select) {
        return jdbcHelper.queryColumnOne(columnIndex, columnType, select.apply(new JdbcSelectEngine<>(jdbcHelper, tableHelperClass)));
    }

    public <R> R queryColumnOne(String columnName, Class<R> columnType, Select<H, HC, HO, HW, HG, HH, HS> select) {
        return jdbcHelper.queryColumnOne(columnName, columnType, select.apply(new JdbcSelectEngine<>(jdbcHelper, tableHelperClass)));
    }

    public <R> List<R> queryColumn(int columnIndex, Class<R> columnType, Select<H, HC, HO, HW, HG, HH, HS> select) {
        return jdbcHelper.queryColumn(columnIndex, columnType, select.apply(new JdbcSelectEngine<>(jdbcHelper, tableHelperClass)));
    }

    public <R> List<R> queryColumn(String columnName, Class<R> columnType, Select<H, HC, HO, HW, HG, HH, HS> select) {
        return jdbcHelper.queryColumn(columnName, columnType, select.apply(new JdbcSelectEngine<>(jdbcHelper, tableHelperClass)));
    }

    public int updateJavaBeanByPrimaryKey(Object keyValue, T javaBean) {
        return jdbcHelper.updateJavaBeanByPrimaryKey(keyValue, javaBean, new JdbcUpdateEngine<>(jdbcHelper, tableHelperClass));
    }

    public int updateJavaBeanByPrimaryKeySelective(Object keyValue, T javaBean) {
        return jdbcHelper.updateJavaBeanByPrimaryKeySelective(keyValue, javaBean, new JdbcUpdateEngine<>(jdbcHelper, tableHelperClass));
    }

    public interface Update<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
            TC extends ColumnHelper<TC>,
            TO extends OnHelper<TO>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> {
        UpdateSqlBuilder apply(JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> engine);
    }

    public int updateJavaBean(T javaBean, Update<H, HC, HO, HW, HG, HH, HS> update) {
        return jdbcHelper.updateJavaBean(javaBean, update.apply(new JdbcUpdateEngine<>(jdbcHelper, tableHelperClass)));
    }

    public int updateJavaBeanSelective(T javaBean, Update<H, HC, HO, HW, HG, HH, HS> update) {
        return jdbcHelper.updateJavaBeanSelective(javaBean, update.apply(new JdbcUpdateEngine<>(jdbcHelper, tableHelperClass)));
    }

    public int batchUpdateJavaBeansByPrimaryKeys(Collection<T> javaBeans) {
        return jdbcHelper.batchUpdateJavaBeansByPrimaryKeys(javaBeans, new JdbcUpdateEngine<>(jdbcHelper, tableHelperClass));
    }

    public int updateOrInsertJavaBeans(Collection<T> javaBeans) {
        return jdbcHelper.updateOrInsertJavaBeans(javaBeans, new JdbcUpdateEngine<>(jdbcHelper, tableHelperClass));
    }

    public int deleteByPrimaryKey(Object keyValue) {
        return jdbcHelper.deleteByPrimaryKey(keyValue, new JdbcDeleteEngine<>(jdbcHelper, tableHelperClass));
    }

    public int batchDeleteByPrimaryKeys(Collection<?> keyValues) {
        return jdbcHelper.batchDeleteByPrimaryKeys(keyValues, new JdbcDeleteEngine<>(jdbcHelper, tableHelperClass));
    }

    public interface Delete<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
            TC extends ColumnHelper<TC>,
            TO extends OnHelper<TO>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> {
        DeleteSqlBuilder apply(JdbcDeleteEngine<T, TC, TO, TW, TG, TH, TS> engine);
    }

    public int delete(Delete<H, HC, HO, HW, HG, HH, HS> delete) {
        return jdbcHelper.delete(delete.apply(new JdbcDeleteEngine<>(jdbcHelper, tableHelperClass)));
    }

    public void setBeanType(Class<T> beanType) {
        this.beanType = beanType;
    }

    public void setTableHelperClass(Class<H> tableHelperClass) {
        this.tableHelperClass = tableHelperClass;
    }

    public void setJdbcHelper(JdbcHelper jdbcHelper) {
        this.jdbcHelper = jdbcHelper;
    }
}