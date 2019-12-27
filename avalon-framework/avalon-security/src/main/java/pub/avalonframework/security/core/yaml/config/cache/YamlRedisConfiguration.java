package pub.avalonframework.security.core.yaml.config.cache;

import pub.avalonframework.security.core.yaml.config.YamlConfiguration;

/**
 * @author baichao
 */
public class YamlRedisConfiguration implements YamlConfiguration {

    private YamlRedisSessionConfiguration session;

    private YamlRedisCacheConfiguration cache;

    public YamlRedisSessionConfiguration getSession() {
        return session;
    }

    public void setSession(YamlRedisSessionConfiguration session) {
        this.session = session;
    }

    public YamlRedisCacheConfiguration getCache() {
        return cache;
    }

    public void setCache(YamlRedisCacheConfiguration cache) {
        this.cache = cache;
    }
}