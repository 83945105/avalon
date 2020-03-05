package pub.avalonframework.sqlhelper.core.helper;

import pub.avalonframework.sqlhelper.core.SqlhelperException;

/**
 * @author baichao
 */
public class TableAliasNullException extends SqlhelperException {

    public TableAliasNullException() {
    }

    public TableAliasNullException(String message) {
        super(message);
    }

    public TableAliasNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public TableAliasNullException(Throwable cause) {
        super(cause);
    }
}