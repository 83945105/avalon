package pub.avalonframework.sqlhelper.core.data.block;

import pub.avalonframework.sqlhelper.core.SqlhelperException;

/**
 * @author baichao
 */
public class UnsupportedDataBlockTypeException extends SqlhelperException {

    public UnsupportedDataBlockTypeException() {
    }

    public UnsupportedDataBlockTypeException(String message) {
        super(message);
    }

    public UnsupportedDataBlockTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedDataBlockTypeException(Throwable cause) {
        super(cause);
    }
}