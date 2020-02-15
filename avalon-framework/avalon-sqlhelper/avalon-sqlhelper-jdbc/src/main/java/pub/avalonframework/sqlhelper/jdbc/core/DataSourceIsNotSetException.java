package pub.avalonframework.sqlhelper.jdbc.core;

import pub.avalonframework.sqlhelper.core.SqlhelperException;

/**
 * @author baichao
 */
public class DataSourceIsNotSetException extends SqlhelperException {

    public DataSourceIsNotSetException() {
    }

    public DataSourceIsNotSetException(String message) {
        super(message);
    }

    public DataSourceIsNotSetException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataSourceIsNotSetException(Throwable cause) {
        super(cause);
    }
}