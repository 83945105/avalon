package pub.avalonframework.security.core.yaml.swapper.impl;

import pub.avalonframework.security.core.api.config.cache.RedisCacheConfiguration;
import pub.avalonframework.security.core.api.config.cache.RedisConfiguration;
import pub.avalonframework.security.core.api.config.cache.RedisSessionConfiguration;
import pub.avalonframework.security.core.yaml.config.cache.YamlRedisCacheConfiguration;
import pub.avalonframework.security.core.yaml.config.cache.YamlRedisConfiguration;
import pub.avalonframework.security.core.yaml.config.cache.YamlRedisSessionConfiguration;
import pub.avalonframework.security.core.yaml.swapper.YamlSwapper;

/**
 * @author baichao
 */
public final class RedisConfigurationYamlSwapper implements YamlSwapper<YamlRedisConfiguration, RedisConfiguration> {

    private final RedisSessionConfigurationYamlSwapper redisSessionConfigurationYamlSwapper = new RedisSessionConfigurationYamlSwapper();

    private final RedisCacheConfigurationYamlSwapper redisCacheConfigurationYamlSwapper = new RedisCacheConfigurationYamlSwapper();

    @Override
    public YamlRedisConfiguration swap(RedisConfiguration data) {
        YamlRedisConfiguration configuration = new YamlRedisConfiguration();
        RedisSessionConfiguration redisSessionConfiguration = data.getRedisSessionConfiguration();
        configuration.setSession(redisSessionConfigurationYamlSwapper.swap(redisSessionConfiguration));
        RedisCacheConfiguration redisCacheConfiguration = data.getRedisCacheConfiguration();
        configuration.setCache(redisCacheConfigurationYamlSwapper.swap(redisCacheConfiguration));
        return configuration;
    }

    @Override
    public RedisConfiguration swap(YamlRedisConfiguration yamlConfiguration) {
        RedisConfiguration configuration = new RedisConfiguration();
        YamlRedisSessionConfiguration redisSessionConfiguration = yamlConfiguration.getSession();
        configuration.setRedisSessionConfiguration(redisSessionConfigurationYamlSwapper.swap(redisSessionConfiguration));
        YamlRedisCacheConfiguration redisCacheConfiguration = yamlConfiguration.getCache();
        configuration.setRedisCacheConfiguration(redisCacheConfigurationYamlSwapper.swap(redisCacheConfiguration));
        return configuration;
    }
}