package pub.avalonframework.web.spring.http.response.exception.impl;

import pub.avalonframework.web.spring.http.response.AbstractResultInfo;
import pub.avalonframework.web.spring.http.response.ResponseType;

/**
 * @author baichao
 */
public class FailMessageException extends MessageException {

    public FailMessageException() {
        super(new FailExceptionResultInfo());
    }

    public FailMessageException(String message) {
        super(new FailExceptionResultInfo(message));
    }

    public FailMessageException(Throwable cause) {
        super(new FailExceptionResultInfo(), cause);
    }

    public FailMessageException(String message, Throwable cause) {
        super(new FailExceptionResultInfo(message), cause);
    }

    private final static class FailExceptionResultInfo extends AbstractResultInfo {

        public FailExceptionResultInfo() {
            this(ResponseType.FAIL.name());
        }

        public FailExceptionResultInfo(String message) {
            this.init(message);
        }

        private void init(String message) {
            this.message = message;
            this.fail = true;
        }
    }
}