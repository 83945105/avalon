package pub.avalonframework.security.data.expression;

/**
 * @author baichao
 */
public final class LikePredicate implements PredicateExpression, Predicate {

    private Predicate masterPredicate;

    private ComparisonOperator comparisonOperator = ComparisonOperator.LK;

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

    public Predicate getSlavePredicate() {
        return slavePredicate;
    }

    public void setSlavePredicate(Predicate slavePredicate) {
        this.slavePredicate = slavePredicate;
    }
}