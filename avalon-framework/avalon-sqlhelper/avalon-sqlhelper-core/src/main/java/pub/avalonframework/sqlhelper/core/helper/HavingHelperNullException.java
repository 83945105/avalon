package pub.avalonframework.sqlhelper.core.helper;

import pub.avalonframework.sqlhelper.core.SqlhelperException;

/**
 * @author baichao
 */
public class HavingHelperNullException extends SqlhelperException {

    public HavingHelperNullException() {
    }

    public HavingHelperNullException(String message) {
        super(message);
    }

    public HavingHelperNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public HavingHelperNullException(Throwable cause) {
        super(cause);
    }
}