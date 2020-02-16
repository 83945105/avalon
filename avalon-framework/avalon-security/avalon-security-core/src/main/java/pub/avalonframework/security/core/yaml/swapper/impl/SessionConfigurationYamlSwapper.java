package pub.avalonframework.security.core.yaml.swapper.impl;

import pub.avalonframework.core.yaml.swapper.YamlSwapper;
import pub.avalonframework.redis.core.api.config.RedisConfiguration;
import pub.avalonframework.redis.core.yaml.config.YamlRedisConfiguration;
import pub.avalonframework.redis.core.yaml.swapper.impl.RedisConfigurationYamlSwapper;
import pub.avalonframework.security.core.api.config.SessionConfiguration;
import pub.avalonframework.security.core.yaml.config.YamlSessionConfiguration;

/**
 * @author baichao
 */
public final class SessionConfigurationYamlSwapper implements YamlSwapper<YamlSessionConfiguration, SessionConfiguration> {

    private final static String DEFAULT_SESSION_ID_NAME = "SESSIONID";

    private final static long DEFAULT_SESSION_VALIDATION_INTERVAL = 1000 * 60 * 60;

    private final static long DEFAULT_SESSION_TIME_OUT = 1000 * 60 * 60 * 24;

    private final static long DEFAULT_COOKIE_MAX_AGE = -1;

    private final static String DEFAULT_ACTIVE_SESSION_CACHE_NAME = "active:session";

    @Override
    public YamlSessionConfiguration swap(SessionConfiguration data) {
        data = data == null ? new SessionConfiguration() : data;
        YamlSessionConfiguration configuration = new YamlSessionConfiguration();
        String sessionIdName = data.getSessionIdName();
        configuration.setSessionIdName(sessionIdName == null ? DEFAULT_SESSION_ID_NAME : sessionIdName);
        Long sessionValidationInterval = data.getSessionValidationInterval();
        configuration.setSessionValidationInterval(sessionValidationInterval == null ? DEFAULT_SESSION_VALIDATION_INTERVAL : sessionValidationInterval);
        Long sessionTimeout = data.getSessionTimeout();
        configuration.setSessionTimeout(sessionTimeout == null ? DEFAULT_SESSION_TIME_OUT : sessionTimeout);
        Long cookieMaxAge = data.getCookieMaxAge();
        configuration.setCookieMaxAge(cookieMaxAge == null ? DEFAULT_COOKIE_MAX_AGE : cookieMaxAge);
        String sessionCacheName = data.getSessionCacheName();
        configuration.setSessionCacheName(sessionCacheName == null ? DEFAULT_ACTIVE_SESSION_CACHE_NAME : sessionCacheName);
        RedisConfiguration redis = data.getRedis();
        configuration.setRedis(new RedisConfigurationYamlSwapper().swap(redis));
        return configuration;
    }

    @Override
    public SessionConfiguration swap(YamlSessionConfiguration yamlConfiguration) {
        yamlConfiguration = yamlConfiguration == null ? new YamlSessionConfiguration() : yamlConfiguration;
        SessionConfiguration configuration = new SessionConfiguration();
        String sessionIdName = yamlConfiguration.getSessionIdName();
        configuration.setSessionIdName(sessionIdName == null ? DEFAULT_SESSION_ID_NAME : sessionIdName);
        Long sessionValidationInterval = yamlConfiguration.getSessionValidationInterval();
        configuration.setSessionValidationInterval(sessionValidationInterval == null ? DEFAULT_SESSION_VALIDATION_INTERVAL : sessionValidationInterval);
        Long sessionTimeout = yamlConfiguration.getSessionTimeout();
        configuration.setSessionTimeout(sessionTimeout == null ? DEFAULT_SESSION_TIME_OUT : sessionTimeout);
        Long cookieMaxAge = yamlConfiguration.getCookieMaxAge();
        configuration.setCookieMaxAge(cookieMaxAge == null ? DEFAULT_COOKIE_MAX_AGE : cookieMaxAge);
        String sessionCacheName = yamlConfiguration.getSessionCacheName();
        configuration.setSessionCacheName(sessionCacheName == null ? DEFAULT_ACTIVE_SESSION_CACHE_NAME : sessionCacheName);
        YamlRedisConfiguration redis = yamlConfiguration.getRedis();
        configuration.setRedis(new RedisConfigurationYamlSwapper().swap(redis));
        return configuration;
    }
}