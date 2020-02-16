package pub.avalonframework.security.core.yaml.config;

import pub.avalonframework.core.yaml.config.YamlConfiguration;
import pub.avalonframework.redis.core.yaml.config.YamlRedisConfiguration;

/**
 * @author baichao
 */
public class YamlSessionConfiguration implements YamlConfiguration {

    private String sessionIdName;

    private Long sessionValidationInterval;

    private Long sessionTimeout;

    private Long cookieMaxAge;

    private String sessionCacheName;

    private YamlRedisConfiguration redis;

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

    public YamlRedisConfiguration getRedis() {
        return redis;
    }

    public void setRedis(YamlRedisConfiguration redis) {
        this.redis = redis;
    }
}