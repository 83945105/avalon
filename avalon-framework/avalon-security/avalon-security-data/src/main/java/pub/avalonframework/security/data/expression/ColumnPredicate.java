package pub.avalonframework.security.data.expression;

/**
 * @author baichao
 */
public class ColumnPredicate implements Predicate {

    private String tableName;

    private String tableAlias;

    private String columnName;

    private String columnAlias;

    public ColumnPredicate(String tableName, String tableAlias, String columnName, String columnAlias) {
        this.tableName = tableName;
        this.tableAlias = tableAlias;
        this.columnName = columnName;
        this.columnAlias = columnAlias;
    }

    public String getTableName() {
        return tableName;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getColumnAlias() {
        return columnAlias;
    }
}