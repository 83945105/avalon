package pub.avalonframework.sqlhelper.core.data.block;

import pub.avalonframework.sqlhelper.core.SqlhelperException;

/**
 * @author baichao
 */
public class UnsupportedComparisonValueTypeException extends SqlhelperException {

    public UnsupportedComparisonValueTypeException() {
    }

    public UnsupportedComparisonValueTypeException(String message) {
        super(message);
    }

    public UnsupportedComparisonValueTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedComparisonValueTypeException(Throwable cause) {
        super(cause);
    }
}