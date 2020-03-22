package pub.avalonframework.security.data.sql;

import pub.avalonframework.core.beans.AvalonException;

/**
 * @author baichao
 */
public class SqlSyntaxErrorException extends AvalonException {

    public SqlSyntaxErrorException() {
    }

    public SqlSyntaxErrorException(String message) {
        super(message);
    }

    public SqlSyntaxErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqlSyntaxErrorException(Throwable cause) {
        super(cause);
    }
}