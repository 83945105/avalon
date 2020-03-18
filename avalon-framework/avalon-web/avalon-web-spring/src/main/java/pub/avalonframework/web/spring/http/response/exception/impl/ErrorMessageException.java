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

    public ErrorMessageException(int code) {
        super(new ErrorExceptionResultInfo(code));
    }

    public ErrorMessageException(String message) {
        super(new ErrorExceptionResultInfo(message));
    }

    public ErrorMessageException(int code, String message) {
        super(new ErrorExceptionResultInfo(code, message));
    }

    public ErrorMessageException(Throwable cause) {
        super(new ErrorExceptionResultInfo(), cause);
    }

    public ErrorMessageException(int code, Throwable cause) {
        super(new ErrorExceptionResultInfo(code), cause);
    }

    public ErrorMessageException(String message, Throwable cause) {
        super(new ErrorExceptionResultInfo(message), cause);
    }

    public ErrorMessageException(int code, String message, Throwable cause) {
        super(new ErrorExceptionResultInfo(code, message), cause);
    }

    private final static class ErrorExceptionResultInfo extends AbstractResultInfo {

        public ErrorExceptionResultInfo() {
            this(ResponseType.ERROR.name());
        }

        public ErrorExceptionResultInfo(int code) {
            this(code, ResponseType.ERROR.name());
        }

        public ErrorExceptionResultInfo(String message) {
            this(500, message);
        }

        public ErrorExceptionResultInfo(int code, String message) {
            this.init(code, message);
        }

        private void init(int code, String message) {
            this.code = code;
            this.message = message;
            this.error = true;
            this.responseType = ResponseType.ERROR;
        }
    }
}