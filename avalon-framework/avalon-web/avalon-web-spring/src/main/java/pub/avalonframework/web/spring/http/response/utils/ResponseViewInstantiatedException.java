package pub.avalonframework.web.spring.http.response.utils;

import pub.avalonframework.core.beans.AvalonException;

/**
 * @author baichao
 */
public class ResponseViewInstantiatedException extends AvalonException {

    public ResponseViewInstantiatedException() {
    }

    public ResponseViewInstantiatedException(String message) {
        super(message);
    }

    public ResponseViewInstantiatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResponseViewInstantiatedException(Throwable cause) {
        super(cause);
    }
}