package pub.avalonframework.security.data;

/**
 * @author baichao
 */
public interface PredicateExpressionOperations {

    void setColumn(String tableName, String tableAlias, String columnName, String columnAlias);

    void setComparisonType(ComparisonType comparisonType);

    void setValue(Object value);

    boolean hasColumn();

    boolean hasValue();

    enum ValueType {
        CONSTANT, PREDICATE_EXPRESSION
    }
}