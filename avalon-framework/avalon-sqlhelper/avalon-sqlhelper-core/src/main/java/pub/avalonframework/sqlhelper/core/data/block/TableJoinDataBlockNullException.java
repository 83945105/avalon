package pub.avalonframework.sqlhelper.core.data.block;

import pub.avalonframework.sqlhelper.core.SqlhelperException;

/**
 * @author baichao
 */
public class TableJoinDataBlockNullException extends SqlhelperException {

    public TableJoinDataBlockNullException() {
    }

    public TableJoinDataBlockNullException(String message) {
        super(message);
    }

    public TableJoinDataBlockNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public TableJoinDataBlockNullException(Throwable cause) {
        super(cause);
    }
}