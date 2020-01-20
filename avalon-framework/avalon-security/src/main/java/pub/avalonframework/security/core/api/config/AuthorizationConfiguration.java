package pub.avalonframework.security.core.api.config;

import pub.avalonframework.redis.core.api.config.RedisConfiguration;

/**
 * The authorization configuration.
 *
 * @author baichao
 */
public class AuthorizationConfiguration {

    private Boolean cacheEnabled;

    private String cacheName;

    private RedisConfiguration redis;

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
}