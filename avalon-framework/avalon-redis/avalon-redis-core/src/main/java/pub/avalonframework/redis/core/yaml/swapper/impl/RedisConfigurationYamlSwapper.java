package pub.avalonframework.redis.core.yaml.swapper.impl;

import pub.avalonframework.core.yaml.swapper.YamlSwapper;
import pub.avalonframework.redis.core.api.beans.RedisImpl;
import pub.avalonframework.redis.core.api.config.JedisConfiguration;
import pub.avalonframework.redis.core.api.config.RedisConfiguration;
import pub.avalonframework.redis.core.yaml.config.YamlJedisConfiguration;
import pub.avalonframework.redis.core.yaml.config.YamlRedisConfiguration;

/**
 * @author baichao
 */
public final class RedisConfigurationYamlSwapper implements YamlSwapper<YamlRedisConfiguration, RedisConfiguration> {

    public final static String DEFAULT_HOST_NAME = "localhost";

    public final static int DEFAULT_PORT = 6379;

    public final static int DEFAULT_DATABASE = 0;

    public final static RedisImpl DEFAULT_REDIS_IMPL = RedisImpl.JEDIS;

    @Override
    public YamlRedisConfiguration swap(RedisConfiguration data) {
        data = data == null ? new RedisConfiguration() : data;
        YamlRedisConfiguration configuration = new YamlRedisConfiguration();
        String hostName = data.getHostName();
        configuration.setHostName(hostName == null ? DEFAULT_HOST_NAME : hostName);
        Integer port = data.getPort();
        configuration.setPort(port == null ? DEFAULT_PORT : port);
        Integer database = data.getDatabase();
        configuration.setDatabase(database == null ? DEFAULT_DATABASE : database);
        String password = data.getPassword();
        configuration.setPassword(password);
        RedisImpl redisImpl = data.getImpl();
        configuration.setImpl(redisImpl == null ? DEFAULT_REDIS_IMPL : redisImpl);
        JedisConfiguration jedis = data.getJedis();
        configuration.setJedis(new JedisConfigurationYamlSwapper().swap(jedis));
        return configuration;
    }

    @Override
    public RedisConfiguration swap(YamlRedisConfiguration yamlConfiguration) {
        yamlConfiguration = yamlConfiguration == null ? new YamlRedisConfiguration() : yamlConfiguration;
        RedisConfiguration configuration = new RedisConfiguration();
        String hostName = yamlConfiguration.getHostName();
        configuration.setHostName(hostName == null ? DEFAULT_HOST_NAME : hostName);
        Integer port = yamlConfiguration.getPort();
        configuration.setPort(port == null ? DEFAULT_PORT : port);
        Integer database = yamlConfiguration.getDatabase();
        configuration.setDatabase(database == null ? DEFAULT_DATABASE : database);
        String password = yamlConfiguration.getPassword();
        configuration.setPassword(password);
        RedisImpl redisImpl = yamlConfiguration.getImpl();
        configuration.setImpl(redisImpl == null ? DEFAULT_REDIS_IMPL : redisImpl);
        YamlJedisConfiguration jedis = yamlConfiguration.getJedis();
        configuration.setJedis(new JedisConfigurationYamlSwapper().swap(jedis));
        return configuration;
    }
}