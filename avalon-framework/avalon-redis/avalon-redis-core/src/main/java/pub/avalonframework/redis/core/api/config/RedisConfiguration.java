package pub.avalonframework.redis.core.api.config;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import pub.avalonframework.redis.core.api.beans.RedisImpl;

/**
 * The redis configuration.
 *
 * @author baichao
 */
public class RedisConfiguration {

    /**
     * The redis hostname.
     */
    private String hostName;

    /**
     * The redis port.
     */
    private Integer port;

    /**
     * The redis database num.
     */
    private Integer database;

    /**
     * The redis password.
     */
    private String password;

    /**
     * The redis impl.
     */
    private RedisImpl impl;

    /**
     * The jedis configuration.
     */
    private JedisConfiguration jedis;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getDatabase() {
        return database;
    }

    public void setDatabase(Integer database) {
        this.database = database;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RedisImpl getImpl() {
        return impl;
    }

    public void setImpl(RedisImpl impl) {
        this.impl = impl;
    }

    public JedisConfiguration getJedis() {
        return jedis;
    }

    public void setJedis(JedisConfiguration jedis) {
        this.jedis = jedis;
    }

    public void injectRedisStandaloneConfiguration(RedisStandaloneConfiguration redisStandaloneConfiguration) {
        redisStandaloneConfiguration.setHostName(this.getHostName());
        redisStandaloneConfiguration.setPort(this.getPort());
        redisStandaloneConfiguration.setDatabase(this.getDatabase());
        redisStandaloneConfiguration.setPassword(RedisPassword.of(this.getPassword()));
    }

    public RedisConnectionFactory buildRedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        this.injectRedisStandaloneConfiguration(redisStandaloneConfiguration);
        RedisImpl redisImpl = this.getImpl();
        switch (redisImpl) {
            case JEDIS:
                JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
                JedisConfiguration jedisConfiguration = this.getJedis();
                if (jedisConfiguration != null) {
                    jedisConfiguration.injectPoolConfig(redisConnectionFactory.getPoolConfig());
                }
                return redisConnectionFactory;
            default:
                throw new UnsupportedRedisImplException(redisImpl, RedisConfiguration.class, null);
        }
    }
}