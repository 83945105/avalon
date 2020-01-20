package pub.avalonframework.security.core.api.config;

import pub.avalonframework.redis.core.api.config.RedisConfiguration;

/**
 * The session configuration.
 *
 * @author baichao
 */
public class SessionConfiguration {

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
     * The session cache name.
     */
    private String sessionCacheName;

    /**
     * The redis configuration.
     */
    private RedisConfiguration redis;

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

    public String getSessionCacheName() {
        return sessionCacheName;
    }

    public void setSessionCacheName(String sessionCacheName) {
        this.sessionCacheName = sessionCacheName;
    }

    public RedisConfiguration getRedis() {
        return redis;
    }

    public void setRedis(RedisConfiguration redis) {
        this.redis = redis;
    }
}