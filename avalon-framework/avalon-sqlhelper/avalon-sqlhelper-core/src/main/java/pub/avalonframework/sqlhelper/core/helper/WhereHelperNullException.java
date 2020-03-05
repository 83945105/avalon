package pub.avalonframework.sqlhelper.core.helper;

import pub.avalonframework.sqlhelper.core.SqlhelperException;

/**
 * @author baichao
 */
public class WhereHelperNullException extends SqlhelperException {

    public WhereHelperNullException() {
    }

    public WhereHelperNullException(String message) {
        super(message);
    }

    public WhereHelperNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public WhereHelperNullException(Throwable cause) {
        super(cause);
    }
}