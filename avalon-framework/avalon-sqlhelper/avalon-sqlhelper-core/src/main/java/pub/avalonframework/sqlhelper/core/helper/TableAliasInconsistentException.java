package pub.avalonframework.sqlhelper.core.helper;

import pub.avalonframework.sqlhelper.core.SqlhelperException;

/**
 * @author baichao
 */
public class TableAliasInconsistentException extends SqlhelperException {

    public TableAliasInconsistentException() {
    }

    public TableAliasInconsistentException(String message) {
        super(message);
    }

    public TableAliasInconsistentException(String message, Throwable cause) {
        super(message, cause);
    }

    public TableAliasInconsistentException(Throwable cause) {
        super(cause);
    }
}