package pub.avalonframework.security.data.expression;

import pub.avalonframework.database.sql.expression.ComparisonOperator;
import pub.avalonframework.database.sql.expression.PredicateExpression;

/**
 * @author baichao
 */
public final class BinaryComparisonPredicate implements PredicateExpression, Predicate {

    private Predicate masterPredicate;

    private ComparisonOperator comparisonOperator;

    private Predicate slavePredicate;

    public boolean hasMasterPredicate() {
        return masterPredicate != null;
    }

    public boolean hasSlavePredicate() {
        return slavePredicate != null;
    }

    public Predicate getMasterPredicate() {
        return masterPredicate;
    }

    public void setMasterPredicate(Predicate masterPredicate) {
        this.masterPredicate = masterPredicate;
    }

    public ComparisonOperator getComparisonOperator() {
        return comparisonOperator;
    }

    public void setComparisonOperator(ComparisonOperator comparisonOperator) {
        this.comparisonOperator = comparisonOperator;
    }

    public Predicate getSlavePredicate() {
        return slavePredicate;
    }

    public void setSlavePredicate(Predicate slavePredicate) {
        this.slavePredicate = slavePredicate;
    }
}