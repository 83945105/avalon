package pub.avalonframework.security.data.expression;

/**
 * @author baichao
 */
public final class IsNullPredicate implements PredicateExpression, Predicate {

    private Predicate predicate;

    private ComparisonOperator comparisonOperator;

    public boolean hasPredicate() {
        return predicate != null;
    }

    public Predicate getPredicate() {
        return predicate;
    }

    public void setPredicate(Predicate predicate) {
        this.predicate = predicate;
    }

    public ComparisonOperator getComparisonOperator() {
        return comparisonOperator;
    }

    public void setComparisonOperator(ComparisonOperator comparisonOperator) {
        this.comparisonOperator = comparisonOperator;
    }
}