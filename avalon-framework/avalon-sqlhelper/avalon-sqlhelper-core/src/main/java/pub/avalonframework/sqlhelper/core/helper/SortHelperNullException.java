package pub.avalonframework.sqlhelper.core.helper;

import pub.avalonframework.sqlhelper.core.SqlhelperException;

/**
 * @author baichao
 */
public class SortHelperNullException extends SqlhelperException {

    public SortHelperNullException() {
    }

    public SortHelperNullException(String message) {
        super(message);
    }

    public SortHelperNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public SortHelperNullException(Throwable cause) {
        super(cause);
    }
}