package pub.avalonframework.sqlhelper.jdbc.core;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.sqlbuilder.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author baichao
 */
public interface JdbcHelper {

    DatabaseType getDatabaseType();

    void copyTable(String targetTableName, boolean copyData, TableSqlBuilder tableSqlBuilder);

    void deleteTable(TableSqlBuilder tableSqlBuilder);

    void renameTable(String targetTableName, TableSqlBuilder tableSqlBuilder);

    boolean isTableExist(TableSqlBuilder tableSqlBuilder);

    int insertJavaBean(Object javaBean, InsertSqlBuilder insertSqlBuilder);

    int insertJavaBeanSelective(Object javaBean, InsertSqlBuilder insertSqlBuilder);

    int batchInsertJavaBeans(Collection<?> javaBeans, InsertSqlBuilder insertSqlBuilder);

    <R> R queryByPrimaryKey(Object keyValue, SelectSqlBuilder selectSqlBuilder, RowMapper<R> rowMapper);

    Map<String, Object> queryByPrimaryKey(Object keyValue, SelectSqlBuilder selectSqlBuilder);

    <R> R queryByPrimaryKey(Object keyValue, SelectSqlBuilder selectSqlBuilder, Class<R> returnType);

    <R> R queryOne(SelectSqlBuilder selectSqlBuilder, RowMapper<R> rowMapper);

    Map<String, Object> queryOne(SelectSqlBuilder selectSqlBuilder);

    <R> R queryOne(SelectSqlBuilder selectSqlBuilder, Class<R> returnType);

    <R> List<R> query(SelectSqlBuilder selectSqlBuilder, ResultSetExtractor<List<R>> resultSetExtractor);

    <R> List<R> query(SelectSqlBuilder selectSqlBuilder, RowMapper<R> rowMapper);

    List<Map<String, Object>> query(SelectSqlBuilder selectSqlBuilder);

    <R> List<R> query(SelectSqlBuilder selectSqlBuilder, Class<R> returnType);

    long queryCount(SelectSqlBuilder selectSqlBuilder);

    <K, V> Map<K, V> queryColumnOneGroupByColumn(int groupColumnIndex, Class<K> groupColumnType, int valueColumnIndex, Class<V> valueColumnType, SelectSqlBuilder selectSqlBuilder);

    <K, V> Map<K, V> queryColumnOneGroupByColumn(String groupColumnName, Class<K> groupColumnType, String valueColumnName, Class<V> valueColumnType, SelectSqlBuilder selectSqlBuilder);

    <K, V> Map<K, List<V>> queryColumnGroupByColumn(int groupColumnIndex, Class<K> groupColumnType, int valueColumnIndex, Class<V> valueColumnType, SelectSqlBuilder selectSqlBuilder);

    <K, V> Map<K, List<V>> queryColumnGroupByColumn(String groupColumnName, Class<K> groupColumnType, String valueColumnName, Class<V> valueColumnType, SelectSqlBuilder selectSqlBuilder);

    <K> Map<K, Map<String, Object>> queryOneGroupByColumn(int groupColumnIndex, Class<K> groupColumnType, SelectSqlBuilder selectSqlBuilder);

    <K> Map<K, Map<String, Object>> queryOneGroupByColumn(String groupColumnName, Class<K> groupColumnType, SelectSqlBuilder selectSqlBuilder);

    <K, R> Map<K, R> queryOneGroupByColumn(int groupColumnIndex, Class<K> groupColumnType, Class<R> valueBeanType, SelectSqlBuilder selectSqlBuilder);

    <K, R> Map<K, R> queryOneGroupByColumn(String groupColumnName, Class<K> groupColumnType, Class<R> valueBeanType, SelectSqlBuilder selectSqlBuilder);

    <K> Map<K, List<Map<String, Object>>> queryGroupByColumn(int groupColumnIndex, Class<K> groupColumnType, SelectSqlBuilder selectSqlBuilder);

    <K> Map<K, List<Map<String, Object>>> queryGroupByColumn(String groupColumnName, Class<K> groupColumnType, SelectSqlBuilder selectSqlBuilder);

    <K, R> Map<K, List<R>> queryGroupByColumn(int groupColumnIndex, Class<K> groupColumnType, Class<R> valueBeanType, SelectSqlBuilder selectSqlBuilder);

    <K, R> Map<K, List<R>> queryGroupByColumn(String groupColumnName, Class<K> groupColumnType, Class<R> valueBeanType, SelectSqlBuilder selectSqlBuilder);

    <R> R queryColumnOne(int columnIndex, Class<R> columnType, SelectSqlBuilder selectSqlBuilder);

    <R> R queryColumnOne(String columnName, Class<R> columnType, SelectSqlBuilder selectSqlBuilder);

    <R> List<R> queryColumn(int columnIndex, Class<R> columnType, SelectSqlBuilder selectSqlBuilder);

    <R> List<R> queryColumn(String columnName, Class<R> columnType, SelectSqlBuilder selectSqlBuilder);

    int updateJavaBeanByPrimaryKey(Object keyValue, Object javaBean, UpdateSqlBuilder updateSqlBuilder);

    int updateJavaBeanByPrimaryKeySelective(Object keyValue, Object javaBean, UpdateSqlBuilder updateSqlBuilder);

    int updateJavaBean(Object javaBean, UpdateSqlBuilder updateSqlBuilder);

    int updateJavaBeanSelective(Object javaBean, UpdateSqlBuilder updateSqlBuilder);

    int batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans, UpdateSqlBuilder updateSqlBuilder);

    int updateOrInsertJavaBeans(Collection<?> javaBeans, UpdateSqlBuilder updateSqlBuilder);

    int deleteByPrimaryKey(Object keyValue, DeleteSqlBuilder deleteSqlBuilder);

    int batchDeleteByPrimaryKeys(Collection<?> keyValues, DeleteSqlBuilder deleteSqlBuilder);

    int delete(DeleteSqlBuilder deleteSqlBuilder);
}