package pub.avalonframework.sqlhelper.core.data.block;

import pub.avalonframework.sqlhelper.core.SqlhelperException;

/**
 * @author baichao
 */
public class UnsupportedColumnTypeException extends SqlhelperException {

    public UnsupportedColumnTypeException() {
    }

    public UnsupportedColumnTypeException(String message) {
        super(message);
    }

    public UnsupportedColumnTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedColumnTypeException(Throwable cause) {
        super(cause);
    }
}