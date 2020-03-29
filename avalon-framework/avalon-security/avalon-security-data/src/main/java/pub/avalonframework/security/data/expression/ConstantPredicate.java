package pub.avalonframework.security.data.expression;

/**
 * @author baichao
 */
public final class ConstantPredicate implements Predicate {

    private Object value;

    public ConstantPredicate(Object value) {
        this.value = value;
    }

    public String getValueString() {
        return value == null ? null : String.valueOf(value);
    }
}