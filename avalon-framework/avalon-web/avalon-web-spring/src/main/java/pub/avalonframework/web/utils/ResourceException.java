package pub.avalonframework.web.utils;

import pub.avalonframework.core.beans.AvalonException;

/**
 * @author baichao
 */
public class ResourceException extends AvalonException {

    public ResourceException() {
    }

    public ResourceException(String message) {
        super(message);
    }

    public ResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceException(Throwable cause) {
        super(cause);
    }
}