package pub.avalonframework.sqlhelper.core.expression;

import pub.avalonframework.sqlhelper.core.SqlhelperException;

/**
 * @author baichao
 */
public class ComparisonValueNullException extends SqlhelperException {

    public ComparisonValueNullException() {
    }

    public ComparisonValueNullException(String message) {
        super(message);
    }

    public ComparisonValueNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComparisonValueNullException(Throwable cause) {
        super(cause);
    }
}