package pub.avalonframework.sqlhelper.core.data.block;

import pub.avalonframework.sqlhelper.core.SqlhelperException;

/**
 * @author baichao
 */
public class GroupTypeNullException extends SqlhelperException {

    public GroupTypeNullException() {
    }

    public GroupTypeNullException(String message) {
        super(message);
    }

    public GroupTypeNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public GroupTypeNullException(Throwable cause) {
        super(cause);
    }
}