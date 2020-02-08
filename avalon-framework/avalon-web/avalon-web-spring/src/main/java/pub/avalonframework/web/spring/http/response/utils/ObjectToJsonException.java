package pub.avalonframework.web.spring.http.response.utils;

import pub.avalonframework.core.beans.AvalonException;

/**
 * @author baichao
 */
public class ObjectToJsonException extends AvalonException {

    public ObjectToJsonException() {
    }

    public ObjectToJsonException(String message) {
        super(message);
    }

    public ObjectToJsonException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectToJsonException(Throwable cause) {
        super(cause);
    }
}