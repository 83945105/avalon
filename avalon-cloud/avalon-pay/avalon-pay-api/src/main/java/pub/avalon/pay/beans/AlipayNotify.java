package pub.avalon.pay.beans;

/**
 * 支付宝异步通知
 *
 * @author 白超
 * @date 2018/12/1
 */
public class AlipayNotify {

    /**
     * 通知时间
     */
    protected String notify_time;
    /**
     * 通知类型
     */
    protected String notify_type;
    /**
     * 通知校验ID
     */
    protected String notify_id;
    /**
     * 编码格式
     */
    protected String charset;
    /**
     * 接口版本
     */
    protected String version;
    /**
     * 签名类型
     */
    protected String sign_type;
    /**
     * 签名
     */
    protected String sign;
    /**
     * 授权方的app_id
     */
    protected String auth_app_id;

    public String getNotify_time() {
        return notify_time;
    }

    public void setNotify_time(String notify_time) {
        this.notify_time = notify_time;
    }

    public String getNotify_type() {
        return notify_type;
    }

    public void setNotify_type(String notify_type) {
        this.notify_type = notify_type;
    }

    public String getNotify_id() {
        return notify_id;
    }

    public void setNotify_id(String notify_id) {
        this.notify_id = notify_id;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAuth_app_id() {
        return auth_app_id;
    }

    public void setAuth_app_id(String auth_app_id) {
        this.auth_app_id = auth_app_id;
    }
}
