package pub.avalonframework.sqlhelper.core.data.block;

import pub.avalonframework.sqlhelper.core.SqlhelperException;

/**
 * @author baichao
 */
public class UnsupportedComparisonValueException extends SqlhelperException {

    public UnsupportedComparisonValueException() {
    }

    public UnsupportedComparisonValueException(String message) {
        super(message);
    }

    public UnsupportedComparisonValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedComparisonValueException(Throwable cause) {
        super(cause);
    }
}