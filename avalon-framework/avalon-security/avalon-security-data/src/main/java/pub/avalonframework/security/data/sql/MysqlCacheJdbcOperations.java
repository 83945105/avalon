package pub.avalonframework.security.data.sql;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.database.mysql.MysqlJdbcOperations;
import pub.avalonframework.database.table.column.Column;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author baichao
 */
public class MysqlCacheJdbcOperations extends MysqlJdbcOperations {

    private String databaseName;

    private DatabaseType databaseType;

    private List<String> tableNames;

    private Map<String, Map<String, List<Column>>> databaseTableColumnsMap = new ConcurrentHashMap<>();

    private Map<String, Map<String, List<String>>> databaseTableColumnNamesMap = new ConcurrentHashMap<>();

    public MysqlCacheJdbcOperations(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public String selectDatabaseName() {
        if (databaseName == null) {
            databaseName = super.selectDatabaseName();
        }
        return databaseName;
    }

    @Override
    public DatabaseType selectDatabaseType() {
        if (databaseType == null) {
            databaseType = super.selectDatabaseType();
        }
        return databaseType;
    }

    @Override
    public List<String> selectTableNames() {
        if (tableNames == null) {
            tableNames = super.selectTableNames();
        }
        return tableNames;
    }

    @Override
    public List<Column> selectTableColumns(String databaseName, String tableName) {
        Map<String, List<Column>> tableColumnsMap = databaseTableColumnsMap.get(databaseName);
        if (tableColumnsMap == null) {
            tableColumnsMap = new ConcurrentHashMap<>();
            databaseTableColumnsMap.put(databaseName, tableColumnsMap);
        }
        List<Column> columns = tableColumnsMap.get(tableName);
        if (columns == null) {
            columns = super.selectTableColumns(databaseName, tableName);
            tableColumnsMap.put(tableName, columns);
        }
        return columns;
    }

    public List<Column> selectTableColumns(String tableName) {
        return selectTableColumns(selectDatabaseName(), tableName);
    }

    public List<String> selectTableColumnNames(String tableName) {
        String databaseName = selectDatabaseName();
        Map<String, List<String>> tableColumnNames = databaseTableColumnNamesMap.get(databaseName);
        if (tableColumnNames == null) {
            tableColumnNames = new ConcurrentHashMap<>();
            databaseTableColumnNamesMap.put(databaseName, tableColumnNames);
        }
        List<String> columnNames = tableColumnNames.get(tableName);
        if (columnNames == null) {
            columnNames = selectTableColumns(tableName).stream().map(column -> column.getName().toUpperCase()).collect(Collectors.toList());
            tableColumnNames.put(tableName, columnNames);
        }
        return columnNames;
    }

    public String selectTableNameOfUniqueColumnName(Set<String> tableNames, String columnName) {
        String tableName = null;
        List<String> tableNamesCache;
        for (String each : tableNames) {
            tableNamesCache = selectTableColumnNames(each);
            for (String tableNameCache : tableNamesCache) {
                if (columnName.equalsIgnoreCase(tableNameCache)) {
                    if (tableName != null) {
                        throw new SqlSyntaxErrorException("Column " + columnName + " in clause is ambiguous.");
                    }
                    tableName = each;
                    break;
                }
            }
        }
        if (tableName == null) {
            throw new SqlSyntaxErrorException("Unknown column " + columnName + " in clause.");
        }
        return tableName;
    }
}