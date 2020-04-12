package pub.avalonframework.security.data.expression;

import pub.avalonframework.database.sql.expression.ComparisonOperator;
import pub.avalonframework.database.sql.expression.PredicateExpression;

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