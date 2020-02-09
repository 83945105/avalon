package pub.avalonframework.web.spring.http.response;

import org.springframework.http.HttpStatus;

/**
 * The http result info.
 *
 * @author baichao
 */
public class HttpResultInfo extends AbstractResultInfo {

    private HttpStatus httpStatus;

    public HttpResultInfo() {
        this(HttpStatus.OK);
    }

    public HttpResultInfo(HttpStatus httpStatus) {
        this.init(httpStatus, httpStatus.getReasonPhrase());
    }

    public HttpResultInfo(HttpStatus httpStatus, String message) {
        this.init(httpStatus, message);
    }

    private void init(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.code = httpStatus.value();
        this.message = message;
        if (this.code >= 500) {
            this.error = true;
            this.responseType = ResponseType.ERROR;
        } else if (this.code >= 400) {
            this.fail = true;
            this.responseType = ResponseType.FAIL;
        } else if (this.code >= 200) {
            this.success = true;
            this.responseType = ResponseType.SUCCESS;
        }
        if (httpStatus == HttpStatus.PROXY_AUTHENTICATION_REQUIRED) {
            this.fail = true;
            this.proxyAuthenticationRequired = true;
            this.responseType = ResponseType.PROXY_AUTHENTICATION_REQUIRED;
        } else if (httpStatus == HttpStatus.UNAUTHORIZED) {
            this.fail = true;
            this.unauthorized = true;
            this.responseType = ResponseType.UNAUTHORIZED;
        }
    }

    @Override
    public void setCode(int code) {
        super.setCode(code);
        this.httpStatus = HttpStatus.valueOf(code);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}