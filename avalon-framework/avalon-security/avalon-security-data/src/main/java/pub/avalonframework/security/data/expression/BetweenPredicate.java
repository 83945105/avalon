package pub.avalonframework.security.data.expression;

/**
 * @author baichao
 */
public final class BetweenPredicate implements PredicateExpression, Predicate {

    private Predicate predicate;

    private ComparisonOperator comparisonOperator = ComparisonOperator.BT;

    private Predicate masterValuePredicate;

    private Predicate slaveValuePredicate;

    public boolean hasPredicate() {
        return predicate != null;
    }

    public boolean hasMasterValuePredicate() {
        return masterValuePredicate != null;
    }

    public boolean hasSlaveValuePredicate() {
        return slaveValuePredicate != null;
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

    public Predicate getMasterValuePredicate() {
        return masterValuePredicate;
    }

    public void setMasterValuePredicate(Predicate masterValuePredicate) {
        this.masterValuePredicate = masterValuePredicate;
    }

    public Predicate getSlaveValuePredicate() {
        return slaveValuePredicate;
    }

    public void setSlaveValuePredicate(Predicate slaveValuePredicate) {
        this.slaveValuePredicate = slaveValuePredicate;
    }
}