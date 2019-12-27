package pub.avalonframework.security.core.yaml.config.authentication;

import pub.avalonframework.security.core.yaml.config.YamlConfiguration;

/**
 * @author baichao
 */
public class YamlAuthenticationConfiguration implements YamlConfiguration {

    private String usernameKey;

    private String passwordKey;

    private String url;

    private String pageUrl;

    private String successUrl;

    public String getUsernameKey() {
        return usernameKey;
    }

    public void setUsernameKey(String usernameKey) {
        this.usernameKey = usernameKey;
    }

    public String getPasswordKey() {
        return passwordKey;
    }

    public void setPasswordKey(String passwordKey) {
        this.passwordKey = passwordKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }
}