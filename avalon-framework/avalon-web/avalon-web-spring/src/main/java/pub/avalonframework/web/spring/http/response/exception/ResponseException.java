package pub.avalonframework.web.spring.http.response.exception;

import pub.avalonframework.core.beans.AvalonException;
import pub.avalonframework.web.spring.http.response.view.ExceptionView;

/**
 * The response exception.
 *
 * @author baichao
 */
public abstract class ResponseException extends AvalonException {

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

    public abstract ExceptionView transformToExceptionView();
}