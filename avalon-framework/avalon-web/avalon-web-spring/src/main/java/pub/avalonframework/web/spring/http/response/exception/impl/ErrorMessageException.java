package pub.avalonframework.web.spring.http.response.exception.impl;

import pub.avalonframework.web.spring.http.response.AbstractResultInfo;
import pub.avalonframework.web.spring.http.response.ResponseType;

/**
 * @author baichao
 */
public class ErrorMessageException extends MessageException {

    public ErrorMessageException() {
        super(new ErrorExceptionResultInfo());
    }

    public ErrorMessageException(String message) {
        super(new ErrorExceptionResultInfo(message));
    }

    public ErrorMessageException(Throwable cause) {
        super(new ErrorExceptionResultInfo(), cause);
    }

    public ErrorMessageException(String message, Throwable cause) {
        super(new ErrorExceptionResultInfo(message), cause);
    }

    private final static class ErrorExceptionResultInfo extends AbstractResultInfo {

        public ErrorExceptionResultInfo() {
            this(ResponseType.ERROR.name());
        }

        public ErrorExceptionResultInfo(String message) {
            this.init(message);
        }

        private void init(String message) {
            this.message = message;
            this.error = true;
        }
    }
}