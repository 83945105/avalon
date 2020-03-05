package pub.avalonframework.sqlhelper.core.sqlbuilder.template;

import pub.avalonframework.sqlhelper.core.SqlhelperException;

/**
 * @author baichao
 */
public class MultiTableColumnException extends SqlhelperException {

    public MultiTableColumnException() {
    }

    public MultiTableColumnException(String message) {
        super(message);
    }

    public MultiTableColumnException(String message, Throwable cause) {
        super(message, cause);
    }

    public MultiTableColumnException(Throwable cause) {
        super(cause);
    }
}