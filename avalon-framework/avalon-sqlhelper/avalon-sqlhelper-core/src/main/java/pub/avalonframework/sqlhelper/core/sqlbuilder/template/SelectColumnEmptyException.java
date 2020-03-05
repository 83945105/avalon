package pub.avalonframework.sqlhelper.core.sqlbuilder.template;

import pub.avalonframework.sqlhelper.core.SqlhelperException;

/**
 * @author baichao
 */
public class SelectColumnEmptyException extends SqlhelperException {

    public SelectColumnEmptyException() {
    }

    public SelectColumnEmptyException(String message) {
        super(message);
    }

    public SelectColumnEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public SelectColumnEmptyException(Throwable cause) {
        super(cause);
    }
}