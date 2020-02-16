package pub.avalonframework.security.core.api.config;

import pub.avalonframework.redis.core.api.config.RedisConfiguration;

/**
 * Authentication configuration.
 *
 * @author baichao
 */
public class AuthenticationConfiguration {

    /**
     * Is cache enabled.
     */
    private Boolean cacheEnabled;

    /**
     * The cache name.
     */
    private String cacheName;

    /**
     * The redis configuration.
     */
    private RedisConfiguration redis;

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

    public Boolean getCacheEnabled() {
        return cacheEnabled;
    }

    public void setCacheEnabled(Boolean cacheEnabled) {
        this.cacheEnabled = cacheEnabled;
    }

    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public RedisConfiguration getRedis() {
        return redis;
    }

    public void setRedis(RedisConfiguration redis) {
        this.redis = redis;
    }

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