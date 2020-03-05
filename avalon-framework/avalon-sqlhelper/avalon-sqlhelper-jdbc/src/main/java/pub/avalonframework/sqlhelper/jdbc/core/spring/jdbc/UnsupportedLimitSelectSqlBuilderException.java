package pub.avalonframework.sqlhelper.jdbc.core.spring.jdbc;

import pub.avalonframework.sqlhelper.core.SqlhelperException;

/**
 * @author baichao
 */
public class UnsupportedLimitSelectSqlBuilderException extends SqlhelperException {

    public UnsupportedLimitSelectSqlBuilderException() {
    }

    public UnsupportedLimitSelectSqlBuilderException(String message) {
        super(message);
    }

    public UnsupportedLimitSelectSqlBuilderException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedLimitSelectSqlBuilderException(Throwable cause) {
        super(cause);
    }
}