package pub.avalonframework.security.data.utils;

import pub.avalonframework.core.beans.AvalonException;

/**
 * @author baichao
 */
public class SqlBuilderException extends AvalonException {

    public SqlBuilderException() {
    }

    public SqlBuilderException(String message) {
        super(message);
    }

    public SqlBuilderException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqlBuilderException(Throwable cause) {
        super(cause);
    }
}