package pub.avalonframework.sqlhelper.core.helper;

import pub.avalonframework.sqlhelper.core.SqlhelperException;

/**
 * @author baichao
 */
public class GroupHelperNullException extends SqlhelperException {

    public GroupHelperNullException() {
    }

    public GroupHelperNullException(String message) {
        super(message);
    }

    public GroupHelperNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public GroupHelperNullException(Throwable cause) {
        super(cause);
    }
}