package pub.avalonframework.web.spring.http.response;

/**
 * @author baichao
 */
public abstract class AbstractResultInfo implements ResultInfo {

    protected int code;

    protected String message;

    protected boolean success;

    protected boolean fail;

    protected boolean error;

    protected boolean proxyAuthenticationRequired;

    protected boolean unauthorized;

    protected ResponseType responseType;

    protected AbstractResultInfo() {
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public boolean isFail() {
        return fail;
    }

    @Override
    public boolean isError() {
        return error;
    }

    @Override
    public boolean isProxyAuthenticationRequired() {
        return proxyAuthenticationRequired;
    }

    @Override
    public boolean isUnauthorized() {
        return unauthorized;
    }

    @Override
    public ResponseType getResponseType() {
        return responseType;
    }
}