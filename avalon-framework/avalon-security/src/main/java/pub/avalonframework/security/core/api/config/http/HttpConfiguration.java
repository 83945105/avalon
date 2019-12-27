package pub.avalonframework.security.core.api.config.http;

/**
 * Http configuration.
 *
 * @author baichao
 */
public class HttpConfiguration {

    /**
     * The session id name.
     */
    private String sessionIdName;

    /**
     * The session validation interval.
     */
    private Long sessionValidationInterval;

    /**
     * The session timeout.
     */
    private Long sessionTimeout;

    /**
     * The cookie max age.
     */
    private Long cookieMaxAge;

    /**
     * Used to get the key from the request header to determine if the request is ajax.
     */
    private String ajaxHeaderIdentificationKey;

    /**
     * Used to get the value from the request header to determine if the request is ajax.
     */
    private String ajaxHeaderIdentificationValue;

    public String getSessionIdName() {
        return sessionIdName;
    }

    public void setSessionIdName(String sessionIdName) {
        this.sessionIdName = sessionIdName;
    }

    public Long getSessionValidationInterval() {
        return sessionValidationInterval;
    }

    public void setSessionValidationInterval(Long sessionValidationInterval) {
        this.sessionValidationInterval = sessionValidationInterval;
    }

    public Long getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(Long sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public Long getCookieMaxAge() {
        return cookieMaxAge;
    }

    public void setCookieMaxAge(Long cookieMaxAge) {
        this.cookieMaxAge = cookieMaxAge;
    }

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