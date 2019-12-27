package pub.avalonframework.security.core.yaml.config.http;

import pub.avalonframework.security.core.yaml.config.YamlConfiguration;

/**
 * @author baichao
 */
public class YamlHttpConfiguration implements YamlConfiguration {

    private String sessionIdName;

    private Long sessionValidationInterval;

    private Long sessionTimeout;

    private Long cookieMaxAge;

    private String ajaxHeaderIdentificationKey;

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