package pub.avalonframework.security.core.api.config.authentication;

/**
 * Authentication configuration.
 *
 * @author baichao
 */
public class AuthenticationConfiguration {

    /**
     * The key used to get the username from the request.
     */
    private String usernameKey;

    /**
     * The key used to get the password from the request.
     */
    private String passwordKey;

    /**
     * URL used for login.
     */
    private String url;

    /**
     * Page URL for login.
     */
    private String pageUrl;

    /**
     * Jump URL after successful login.
     */
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