package pub.avalonframework.web.spring.http.response.exception.impl;

import pub.avalonframework.web.spring.http.response.DefaultResultInfo;
import pub.avalonframework.web.spring.http.response.ResponseType;

/**
 * @author baichao
 */
public class FailMessageException extends MessageException {

    public FailMessageException() {
        super(new DefaultResultInfo(ResponseType.FAIL));
    }

    public FailMessageException(String message) {
        super(new DefaultResultInfo(ResponseType.FAIL, message));
    }

    public FailMessageException(Throwable cause) {
        super(new DefaultResultInfo(ResponseType.FAIL), cause);
    }

    public FailMessageException(String message, Throwable cause) {
        super(new DefaultResultInfo(ResponseType.FAIL, message), cause);
    }
}