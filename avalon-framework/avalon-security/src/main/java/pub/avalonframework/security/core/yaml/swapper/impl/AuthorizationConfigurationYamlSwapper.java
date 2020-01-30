package pub.avalonframework.security.core.yaml.swapper.impl;

import pub.avalonframework.core.yaml.swapper.YamlSwapper;
import pub.avalonframework.redis.core.api.config.RedisConfiguration;
import pub.avalonframework.redis.core.yaml.config.YamlRedisConfiguration;
import pub.avalonframework.redis.core.yaml.swapper.impl.RedisConfigurationYamlSwapper;
import pub.avalonframework.security.core.api.config.AuthorizationConfiguration;
import pub.avalonframework.security.core.yaml.config.YamlAuthorizationConfiguration;

/**
 * @author baichao
 */
public final class AuthorizationConfigurationYamlSwapper implements YamlSwapper<YamlAuthorizationConfiguration, AuthorizationConfiguration> {

    private final static Boolean DEFAULT_CACHE_ENABLED = true;

    private final static String DEFAULT_CACHE_NAME = "authorization";

    @Override
    public YamlAuthorizationConfiguration swap(AuthorizationConfiguration data) {
        data = data == null ? new AuthorizationConfiguration() : data;
        YamlAuthorizationConfiguration configuration = new YamlAuthorizationConfiguration();
        Boolean cacheEnabled = data.getCacheEnabled();
        configuration.setCacheEnabled(cacheEnabled == null ? DEFAULT_CACHE_ENABLED : cacheEnabled);
        String cacheName = data.getCacheName();
        configuration.setCacheName(cacheName == null ? DEFAULT_CACHE_NAME : cacheName);
        RedisConfiguration redis = data.getRedis();
        configuration.setRedis(new RedisConfigurationYamlSwapper().swap(redis));
        return configuration;
    }

    @Override
    public AuthorizationConfiguration swap(YamlAuthorizationConfiguration yamlConfiguration) {
        yamlConfiguration = yamlConfiguration == null ? new YamlAuthorizationConfiguration() : yamlConfiguration;
        AuthorizationConfiguration configuration = new AuthorizationConfiguration();
        Boolean cacheEnabled = yamlConfiguration.getCacheEnabled();
        configuration.setCacheEnabled(cacheEnabled == null ? DEFAULT_CACHE_ENABLED : cacheEnabled);
        String cacheName = yamlConfiguration.getCacheName();
        configuration.setCacheName(cacheName == null ? DEFAULT_CACHE_NAME : cacheName);
        YamlRedisConfiguration redis = yamlConfiguration.getRedis();
        configuration.setRedis(new RedisConfigurationYamlSwapper().swap(redis));
        return configuration;
    }
}