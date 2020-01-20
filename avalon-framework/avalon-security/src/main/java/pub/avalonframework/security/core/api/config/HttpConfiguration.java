package pub.avalonframework.security.core.api.config;

/**
 * Http configuration.
 *
 * @author baichao
 */
public class HttpConfiguration {

    /**
     * Used to get the key from the request header to determine if the request is ajax.
     */
    private String ajaxHeaderIdentificationKey;

    /**
     * Used to get the value from the request header to determine if the request is ajax.
     */
    private String ajaxHeaderIdentificationValue;

    public String getAjaxHeaderIdentificationKey() {
        return ajaxHeaderIdentificationKey;
    }

    public void setAjaxHeaderIdentificationKey(String ajaxHeaderIdentificationKey) {
        this.ajaxHeaderIdentificationKey = ajaxHeaderIdentificationKey;
    }

    public String getAjaxHeaderIdentificationValue() {
        return ajaxHeaderIdentificationValue;
    }

    public void setAjaxHeaderIdentificationValue(String ajaxHeaderIdentificationValue) {
        this.ajaxHeaderIdentificationValue = ajaxHeaderIdentificationValue;
    }
}