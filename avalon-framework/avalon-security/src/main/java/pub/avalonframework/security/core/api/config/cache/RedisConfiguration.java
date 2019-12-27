package pub.avalonframework.security.core.api.config.cache;

/**
 * Redis configuration.
 *
 * @author baichao
 */
public class RedisConfiguration {

    /**
     * Cache config for session.
     */
    private RedisSessionConfiguration redisSessionConfiguration;

    /**
     * Cache config for authentication and authorization.
     */
    private RedisCacheConfiguration redisCacheConfiguration;

    public RedisSessionConfiguration getRedisSessionConfiguration() {
        return redisSessionConfiguration;
    }

    public void setRedisSessionConfiguration(RedisSessionConfiguration redisSessionConfiguration) {
        this.redisSessionConfiguration = redisSessionConfiguration;
    }

    public RedisCacheConfiguration getRedisCacheConfiguration() {
        return redisCacheConfiguration;
    }

    public void setRedisCacheConfiguration(RedisCacheConfiguration redisCacheConfiguration) {
        this.redisCacheConfiguration = redisCacheConfiguration;
    }
}