package pub.avalonframework.sqlhelper.core.helper;

import pub.avalonframework.sqlhelper.core.SqlhelperException;

/**
 * @author baichao
 */
public class TableHelperClassNullException extends SqlhelperException {

    public TableHelperClassNullException() {
    }

    public TableHelperClassNullException(String message) {
        super(message);
    }

    public TableHelperClassNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public TableHelperClassNullException(Throwable cause) {
        super(cause);
    }
}