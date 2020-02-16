package pub.avalonframework.sqlhelper.jdbc.core;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.sqlbuilder.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author baichao
 */
public interface JdbcTemplate {

    DatabaseType getDatabaseType();

    void copyTable(String targetTableName, boolean copyData, TableSqlBuilder tableSqlBuilder);

    void deleteTable(TableSqlBuilder tableSqlBuilder);

    void renameTable(String targetTableName, TableSqlBuilder tableSqlBuilder);

    boolean isTableExist(TableSqlBuilder tableSqlBuilder);

    int insertJavaBean(Object javaBean, InsertSqlBuilder insertSqlBuilder);

    int insertJavaBeanSelective(Object javaBean, InsertSqlBuilder insertSqlBuilder);

    int batchInsertJavaBeans(Collection<?> javaBeans, InsertSqlBuilder insertSqlBuilder);

    Map<String, Object> queryByPrimaryKey(Object keyValue, SelectSqlBuilder selectSqlBuilder);

    <T> T queryByPrimaryKey(Object keyValue, SelectSqlBuilder selectSqlBuilder, Class<T> returnType);

    <T> T queryOne(SelectSqlBuilder selectSqlBuilder, RowMapper<T> rowMapper);

    Map<String, Object> queryOne(SelectSqlBuilder selectSqlBuilder);

    <T> T queryOne(SelectSqlBuilder selectSqlBuilder, Class<T> returnType);

    <T> List<T> query(SelectSqlBuilder selectSqlBuilder, ResultSetExtractor<List<T>> resultSetExtractor);

    <T> List<T> query(SelectSqlBuilder selectSqlBuilder, RowMapper<T> rowMapper);

    List<Map<String, Object>> query(SelectSqlBuilder selectSqlBuilder);

    <T> List<T> query(SelectSqlBuilder selectSqlBuilder, Class<T> returnType);

    long queryCount(SelectSqlBuilder selectSqlBuilder);

    <K, V> Map<K, V> queryColumnOneGroupByColumn(int keyColumnIndex, Class<K> keyColumnType, int valueColumnIndex, Class<V> valueColumnType, SelectSqlBuilder selectSqlBuilder);

    <K, V> Map<K, V> queryColumnOneGroupByColumn(String keyColumnName, Class<K> keyColumnType, String valueColumnName, Class<V> valueColumnType, SelectSqlBuilder selectSqlBuilder);

    <K, V> Map<K, List<V>> queryColumnGroupByColumn(int keyColumnIndex, Class<K> keyColumnType, int valueColumnIndex, Class<V> valueColumnType, SelectSqlBuilder selectSqlBuilder);

    <K, V> Map<K, List<V>> queryColumnGroupByColumn(String keyColumnName, Class<K> keyColumnType, String valueColumnName, Class<V> valueColumnType, SelectSqlBuilder selectSqlBuilder);

    <K> Map<K, Map<String, Object>> queryOneGroupByColumn(int keyColumnIndex, Class<K> keyColumnType, SelectSqlBuilder selectSqlBuilder);

    <K> Map<K, Map<String, Object>> queryOneGroupByColumn(String keyColumnName, Class<K> keyColumnType, SelectSqlBuilder selectSqlBuilder);

    <K, T> Map<K, T> queryOneGroupByColumn(int keyColumnIndex, Class<K> keyColumnType, Class<T> valueBeanType, SelectSqlBuilder selectSqlBuilder);

    <K, T> Map<K, T> queryOneGroupByColumn(String keyColumnName, Class<K> keyColumnType, Class<T> valueBeanType, SelectSqlBuilder selectSqlBuilder);

    <K> Map<K, List<Map<String, Object>>> queryGroupByColumn(int keyColumnIndex, Class<K> keyColumnType, SelectSqlBuilder selectSqlBuilder);

    <K> Map<K, List<Map<String, Object>>> queryGroupByColumn(String keyColumnName, Class<K> keyColumnType, SelectSqlBuilder selectSqlBuilder);

    <K, T> Map<K, List<T>> queryGroupByColumn(int keyColumnIndex, Class<K> keyColumnType, Class<T> valueBeanType, SelectSqlBuilder selectSqlBuilder);

    <K, T> Map<K, List<T>> queryGroupByColumn(String keyColumnName, Class<K> keyColumnType, Class<T> valueBeanType, SelectSqlBuilder selectSqlBuilder);

    <T> T queryColumnOne(int columnIndex, Class<T> columnType, SelectSqlBuilder selectSqlBuilder);

    <T> T queryColumnOne(String columnName, Class<T> columnType, SelectSqlBuilder selectSqlBuilder);

    <T> List<T> queryColumn(int columnIndex, Class<T> columnType, SelectSqlBuilder selectSqlBuilder);

    <T> List<T> queryColumn(String columnName, Class<T> columnType, SelectSqlBuilder selectSqlBuilder);

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