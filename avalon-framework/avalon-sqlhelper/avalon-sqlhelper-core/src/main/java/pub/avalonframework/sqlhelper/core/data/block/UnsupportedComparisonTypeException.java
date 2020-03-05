package pub.avalonframework.sqlhelper.core.data.block;

import pub.avalonframework.sqlhelper.core.SqlhelperException;

/**
 * @author baichao
 */
public class UnsupportedComparisonTypeException extends SqlhelperException {

    public UnsupportedComparisonTypeException() {
    }

    public UnsupportedComparisonTypeException(String message) {
        super(message);
    }

    public UnsupportedComparisonTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedComparisonTypeException(Throwable cause) {
        super(cause);
    }
}