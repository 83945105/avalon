package pub.avalonframework.wechat.official.account.spring.web.entity;

import java.io.Serializable;
import java.util.Map;

/**
 * @author baichao
 */
public final class CacheValue implements Serializable {

    private String param;

    private Map<String, String> params;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}