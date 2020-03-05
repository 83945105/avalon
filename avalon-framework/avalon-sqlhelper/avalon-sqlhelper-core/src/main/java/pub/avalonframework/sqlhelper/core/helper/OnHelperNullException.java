package pub.avalonframework.sqlhelper.core.helper;

import pub.avalonframework.sqlhelper.core.SqlhelperException;

/**
 * @author baichao
 */
public class OnHelperNullException extends SqlhelperException {

    public OnHelperNullException() {
    }

    public OnHelperNullException(String message) {
        super(message);
    }

    public OnHelperNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public OnHelperNullException(Throwable cause) {
        super(cause);
    }
}