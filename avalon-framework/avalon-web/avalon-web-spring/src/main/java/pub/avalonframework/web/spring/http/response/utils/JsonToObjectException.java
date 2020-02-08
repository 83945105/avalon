package pub.avalonframework.web.spring.http.response.utils;

import pub.avalonframework.core.beans.AvalonException;

/**
 * @author baichao
 */
public class JsonToObjectException extends AvalonException {

    public JsonToObjectException() {
    }

    public JsonToObjectException(String message) {
        super(message);
    }

    public JsonToObjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonToObjectException(Throwable cause) {
        super(cause);
    }
}