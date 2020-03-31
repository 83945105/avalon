package pub.avalonframework.redis.spring.utils;

import pub.avalonframework.redis.core.api.beans.RedisImpl;
import pub.avalonframework.redis.core.api.config.JedisConfiguration;
import pub.avalonframework.redis.core.api.config.RedisConfiguration;
import pub.avalonframework.redis.core.api.config.UnsupportedRedisImplException;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * @author baichao
 */
public final class RedisSpringUtils {

    private RedisSpringUtils() {
    }

    public static RedisStandaloneConfiguration buildRedisStandaloneConfiguration(RedisConfiguration redisConfiguration) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisConfiguration.getHostName());
        redisStandaloneConfiguration.setPort(redisConfiguration.getPort());
        redisStandaloneConfiguration.setDatabase(redisConfiguration.getDatabase());
        redisStandaloneConfiguration.setPassword(RedisPassword.of(redisConfiguration.getPassword()));
        return redisStandaloneConfiguration;
    }

    public static RedisConnectionFactory buildRedisConnectionFactory(RedisConfiguration redisConfiguration) {
        RedisImpl redisImpl = redisConfiguration.getImpl();
        switch (redisImpl) {
            case JEDIS:
                JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(buildRedisStandaloneConfiguration(redisConfiguration));
                JedisConfiguration jedisConfiguration = redisConfiguration.getJedis();
                if (jedisConfiguration != null) {
                    injectPoolConfigFromRedisConfiguration(jedisConnectionFactory.getPoolConfig(), jedisConfiguration);
                }
                return jedisConnectionFactory;
            default:
                throw new UnsupportedRedisImplException(redisImpl);
        }
    }

    public static void injectPoolConfigFromRedisConfiguration(GenericObjectPoolConfig<?> poolConfig, JedisConfiguration jedisConfiguration) {
        poolConfig.setLifo(jedisConfiguration.getLifo());
        poolConfig.setFairness(jedisConfiguration.getFairness());
        poolConfig.setMaxTotal(jedisConfiguration.getMaxTotal());
        poolConfig.setMaxIdle(jedisConfiguration.getMaxIdle());
        poolConfig.setMinIdle(jedisConfiguration.getMinIdle());
        poolConfig.setMaxWaitMillis(jedisConfiguration.getMaxWaitMillis());
        poolConfig.setBlockWhenExhausted(jedisConfiguration.getBlockWhenExhausted());
        poolConfig.setTestOnCreate(jedisConfiguration.getTestOnCreate());
        poolConfig.setTestOnBorrow(jedisConfiguration.getTestOnBorrow());
        poolConfig.setTestOnReturn(jedisConfiguration.getTestOnReturn());
        poolConfig.setTestWhileIdle(jedisConfiguration.getTestWhileIdle());
        poolConfig.setTimeBetweenEvictionRunsMillis(jedisConfiguration.getTimeBetweenEvictionRunsMillis());
        poolConfig.setNumTestsPerEvictionRun(jedisConfiguration.getNumTestsPerEvictionRun());
        poolConfig.setMinEvictableIdleTimeMillis(jedisConfiguration.getMinEvictableIdleTimeMillis());
        poolConfig.setSoftMinEvictableIdleTimeMillis(jedisConfiguration.getSoftMinEvictableIdleTimeMillis());
        poolConfig.setEvictionPolicyClassName(jedisConfiguration.getEvictionPolicyClassName());
    }
}