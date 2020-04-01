package pub.avalonframework.wechat.official.account.spring.web.entity;

/**
 * @author baichao
 */
public class WebPageAuthorizationResponse<T> {

    private T response;

    private String state;

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}