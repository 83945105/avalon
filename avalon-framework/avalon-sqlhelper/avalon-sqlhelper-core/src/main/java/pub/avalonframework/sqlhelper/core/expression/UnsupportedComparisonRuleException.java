package pub.avalonframework.sqlhelper.core.expression;

import pub.avalonframework.sqlhelper.core.SqlhelperException;

/**
 * @author baichao
 */
public class UnsupportedComparisonRuleException extends SqlhelperException {

    public UnsupportedComparisonRuleException() {
    }

    public UnsupportedComparisonRuleException(String message) {
        super(message);
    }

    public UnsupportedComparisonRuleException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedComparisonRuleException(Throwable cause) {
        super(cause);
    }
}