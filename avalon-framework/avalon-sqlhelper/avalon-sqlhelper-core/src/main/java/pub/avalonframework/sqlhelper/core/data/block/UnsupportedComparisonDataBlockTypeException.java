package pub.avalonframework.sqlhelper.core.data.block;

import pub.avalonframework.sqlhelper.core.SqlhelperException;

/**
 * @author baichao
 */
public class UnsupportedComparisonDataBlockTypeException extends SqlhelperException {

    public UnsupportedComparisonDataBlockTypeException() {
    }

    public UnsupportedComparisonDataBlockTypeException(String message) {
        super(message);
    }

    public UnsupportedComparisonDataBlockTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedComparisonDataBlockTypeException(Throwable cause) {
        super(cause);
    }
}