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

    public PredicateExpression(String tableName, String tableAlias) {
        this.tableName = tableName;
        this.tableAlias = tableAlias;
    }

    @Override
    public void setColumnNameAlias(String columnName, String columnAlias) {
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
}