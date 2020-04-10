package pub.avalonframework.wechat.official.account.spring.web.entity;

/**
 * @author baichao
 */
public class WebPageAuthorizationResponse<T> {

    private T response;

    private CacheValue data;

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public CacheValue getData() {
        return data;
    }

    public void setData(CacheValue data) {
        this.data = data;
    }
}