package pub.avalonframework.security.data;

/**
 * @author baichao
 */
public class PredicateExpression implements PredicateExpressionOperations {

    private String tableName;

    private String tableAlias;

    private String columnName;

    private String columnAlias;

    private ComparisonType comparisonType;

    private Object value;

    private ValueType valueType;

    @Override
    public void setColumn(String tableName, String tableAlias, String columnName, String columnAlias) {
        this.tableName = tableName;
        this.tableAlias = tableAlias;
        this.columnName = columnName;
        this.columnAlias = columnAlias;
    }

    @Override
    public void setComparisonType(ComparisonType comparisonType) {
        this.comparisonType = comparisonType;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
        if (value instanceof PredicateExpression) {
            this.valueType = ValueType.PREDICATE_EXPRESSION;
        } else {
            this.valueType = ValueType.CONSTANT;
        }
    }

    @Override
    public boolean hasColumn() {
        return this.columnName != null;
    }

    @Override
    public boolean hasValue() {
        return this.value != null;
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public String getTableAlias() {
        return tableAlias;
    }

    @Override
    public String getColumnName() {
        return columnName;
    }

    @Override
    public String getColumnAlias() {
        return columnAlias;
    }

    @Override
    public ComparisonType getComparisonType() {
        return comparisonType;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public ValueType getValueType() {
        return valueType;
    }
}