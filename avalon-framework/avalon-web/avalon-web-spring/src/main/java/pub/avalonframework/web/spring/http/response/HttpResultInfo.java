package pub.avalonframework.web.spring.http.response;

import org.springframework.http.HttpStatus;

/**
 * The http result info.
 *
 * @author baichao
 */
public class HttpResultInfo implements ResultInfo {

    private HttpStatus httpStatus;

    private String message;

    public HttpResultInfo() {
        this(HttpStatus.OK);
    }

    public HttpResultInfo(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpResultInfo(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public int getCode() {
        return httpStatus.value();
    }

    @Override
    public void setCode(int code) {
        this.httpStatus = HttpStatus.valueOf(code);
    }

    @Override
    public String getMessage() {
        return message == null ? httpStatus.getReasonPhrase() : message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean isSuccess() {
        int code = getCode();
        return code >= 200 && code < 300;
    }

    @Override
    public boolean isFail() {
        int code = getCode();
        return code >= 400 && code < 500;
    }

    @Override
    public boolean isError() {
        int code = getCode();
        return code >= 500 && code < 600;
    }
}