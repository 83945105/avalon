package pub.avalonframework.security.core.yaml.config;

import pub.avalonframework.core.yaml.config.YamlConfiguration;
import pub.avalonframework.redis.core.yaml.config.YamlRedisConfiguration;

/**
 * @author baichao
 */
public class YamlAuthorizationConfiguration implements YamlConfiguration {

    private Boolean cacheEnabled;

    private String cacheName;

    private YamlRedisConfiguration redis;

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

    public YamlRedisConfiguration getRedis() {
        return redis;
    }

    public void setRedis(YamlRedisConfiguration redis) {
        this.redis = redis;
    }
}