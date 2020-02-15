package pub.avalonframework.sqlhelper.core;

import pub.avalonframework.core.beans.AvalonException;

/**
 * @author baichao
 */
public class SqlhelperException extends AvalonException {

    public SqlhelperException() {
    }

    public SqlhelperException(String message) {
        super(message);
    }

    public SqlhelperException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqlhelperException(Throwable cause) {
        super(cause);
    }
}