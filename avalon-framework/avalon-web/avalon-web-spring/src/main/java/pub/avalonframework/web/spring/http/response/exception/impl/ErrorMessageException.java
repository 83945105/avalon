package pub.avalonframework.web.spring.http.response.exception.impl;

import pub.avalonframework.web.spring.http.response.DefaultResultInfo;
import pub.avalonframework.web.spring.http.response.ResponseType;

/**
 * @author baichao
 */
public class ErrorMessageException extends MessageException {

    public ErrorMessageException() {
        super(new DefaultResultInfo(ResponseType.ERROR));
    }

    public ErrorMessageException(String message) {
        super(new DefaultResultInfo(ResponseType.ERROR, message));
    }

    public ErrorMessageException(Throwable cause) {
        super(new DefaultResultInfo(ResponseType.ERROR), cause);
    }

    public ErrorMessageException(String message, Throwable cause) {
        super(new DefaultResultInfo(ResponseType.ERROR, message), cause);
    }
}