package pub.avalonframework.security.data.expression;

/**
 * @author baichao
 */
public final class ConstantPredicate implements Predicate {

    private Object value;

    public ConstantPredicate(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public String getValueString() {
        if (value == null) {
            return null;
        }
        return String.valueOf(value);
    }
}