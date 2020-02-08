package pub.avalonframework.web.spring.http.response.exception;

import pub.avalonframework.core.beans.AvalonException;

/**
 * The response exception.
 *
 * @author baichao
 */
public class ResponseException extends AvalonException {

    public ResponseException() {
    }

    public ResponseException(String message) {
        super(message);
    }

    public ResponseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResponseException(Throwable cause) {
        super(cause);
    }
}