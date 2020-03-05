package pub.avalonframework.sqlhelper.core.data.block;

import pub.avalonframework.sqlhelper.core.SqlhelperException;

/**
 * @author baichao
 */
public class ColumnDataBlockInconsistentLengthException extends SqlhelperException {

    public ColumnDataBlockInconsistentLengthException() {
    }

    public ColumnDataBlockInconsistentLengthException(String message) {
        super(message);
    }

    public ColumnDataBlockInconsistentLengthException(String message, Throwable cause) {
        super(message, cause);
    }

    public ColumnDataBlockInconsistentLengthException(Throwable cause) {
        super(cause);
    }
}