package pub.avalonframework.web.spring.http.response;

/**
 * @author baichao
 */
public final class DefaultResultInfo extends AbstractResultInfo {

    public DefaultResultInfo(ResponseType responseType) {
        this(responseType, responseType.name());
    }

    public DefaultResultInfo(ResponseType responseType, String message) {
        this.init(responseType, message);
    }

    private void init(ResponseType responseType, String message) {
        this.responseType = responseType;
        this.message = message;
        switch (responseType) {
            case SUCCESS:
                this.success = true;
                break;
            case FAIL:
                this.fail = true;
                break;
            case ERROR:
                this.error = true;
                break;
            case PROXY_AUTHENTICATION_REQUIRED:
                this.fail = true;
                this.proxyAuthenticationRequired = true;
                break;
            case UNAUTHORIZED:
                this.fail = true;
                this.unauthorized = true;
                break;
            default:
                throw new UnknownResponseTypeException("Unknown response type: " + responseType);
        }
    }
}