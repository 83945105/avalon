package pub.avalonframework.sqlhelper.core.helper;

import pub.avalonframework.sqlhelper.core.SqlhelperException;

/**
 * @author baichao
 */
public class ColumnHelperNullException extends SqlhelperException {

    public ColumnHelperNullException() {
    }

    public ColumnHelperNullException(String message) {
        super(message);
    }

    public ColumnHelperNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public ColumnHelperNullException(Throwable cause) {
        super(cause);
    }
}