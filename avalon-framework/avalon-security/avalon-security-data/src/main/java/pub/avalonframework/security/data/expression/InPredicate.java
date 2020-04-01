package pub.avalonframework.security.data.expression;

import java.util.LinkedList;
import java.util.List;

/**
 * @author baichao
 */
public final class InPredicate implements PredicateExpression, Predicate {

    private Predicate predicate;

    private ComparisonOperator comparisonOperator;

    private List<Predicate> valuePredicates = new LinkedList<>();

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

    public List<Predicate> getValuePredicates() {
        return valuePredicates;
    }

    public void addValuePredicates(Predicate valuePredicate) {
        this.valuePredicates.add(valuePredicate);
    }
}