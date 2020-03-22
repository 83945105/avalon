package pub.avalonframework.database;

import pub.avalonframework.database.table.column.Column;

import java.util.List;

/**
 * @author baichao
 */
public interface JdbcOperations {

    /**
     * Select database name.
     *
     * @return The current database name.
     */
    String selectDatabaseName();

    /**
     * Select table names.
     *
     * @return The table name list.
     */
    List<String> selectTableNames();

    /**
     * Select table columns.
     *
     * @param databaseName The database name.
     * @param tableName    The table name.
     * @return The table column list.
     */
    List<Column> selectTableColumns(String databaseName, String tableName);
}