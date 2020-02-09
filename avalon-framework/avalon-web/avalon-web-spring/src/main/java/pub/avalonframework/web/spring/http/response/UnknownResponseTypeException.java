package pub.avalonframework.web.spring.http.response;

import pub.avalonframework.core.beans.AvalonException;

/**
 * @author baichao
 */
public class UnknownResponseTypeException extends AvalonException {

    public UnknownResponseTypeException() {
    }

    public UnknownResponseTypeException(String message) {
        super(message);
    }

    public UnknownResponseTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownResponseTypeException(Throwable cause) {
        super(cause);
    }
}