package pub.avalonframework.sqlhelper.jdbc.core;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.sqlbuilder.SelectSqlBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.TableSqlBuilder;

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

    Map<String, Object> queryByPrimaryKey(Object keyValue, SelectSqlBuilder selectSqlBuilder);

    <T> T queryByPrimaryKey(Object keyValue, SelectSqlBuilder selectSqlBuilder, Class<T> returnType);
}