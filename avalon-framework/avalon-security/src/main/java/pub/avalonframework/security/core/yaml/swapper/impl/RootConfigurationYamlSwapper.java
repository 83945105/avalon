package pub.avalonframework.security.core.yaml.swapper.impl;

import pub.avalonframework.security.core.api.beans.CacheType;
import pub.avalonframework.security.core.api.config.SecurityConfiguration;
import pub.avalonframework.security.core.yaml.config.YamlSecurityConfiguration;
import pub.avalonframework.security.core.yaml.swapper.YamlSwapper;

/**
 * @author baichao
 */
public final class RootConfigurationYamlSwapper implements YamlSwapper<YamlSecurityConfiguration, SecurityConfiguration> {

    private final static Boolean DEFAULT_ENABLED = false;

    private final static CacheType DEFAULT_CACHE_TYPE = CacheType.EHCACHE;

    private final static Boolean DEFAULT_AUTHENTICATION_CACHE_ENABLED = false;

    private final static Boolean DEFAULT_AUTHORIZATION_CACHE_ENABLED = true;

    private final static String DEFAULT_AUTHENTICATION_CACHE_NAME = "authentication";

    private final static String DEFAULT_AUTHORIZATION_CACHE_NAME = "authorization";

    private final static String DEFAULT_ACTIVE_SESSION_CACHE_NAME = "active:session";

    @Override
    public YamlSecurityConfiguration swap(SecurityConfiguration data) {
        YamlSecurityConfiguration configuration = new YamlSecurityConfiguration();
        Boolean enabled = data.getEnabled();
        configuration.setEnabled(enabled == null ? DEFAULT_ENABLED : enabled);
        CacheType cacheType = data.getCacheType();
        configuration.setCacheType(cacheType == null ? DEFAULT_CACHE_TYPE : cacheType);
        Boolean authenticationCacheEnabled = data.getAuthenticationCacheEnabled();
        configuration.setAuthenticationCacheEnabled(authenticationCacheEnabled == null ? DEFAULT_AUTHENTICATION_CACHE_ENABLED : authenticationCacheEnabled);
        Boolean authorizationCacheEnabled = data.getAuthorizationCacheEnabled();
        configuration.setAuthorizationCacheEnabled(authorizationCacheEnabled == null ? DEFAULT_AUTHORIZATION_CACHE_ENABLED : authorizationCacheEnabled);
        String authenticationCacheName = data.getAuthenticationCacheName();
        configuration.setAuthenticationCacheName(authenticationCacheName == null ? DEFAULT_AUTHENTICATION_CACHE_NAME : authenticationCacheName);
        String authorizationCacheName = data.getAuthorizationCacheName();
        configuration.setAuthorizationCacheName(authorizationCacheName == null ? DEFAULT_AUTHORIZATION_CACHE_NAME : authorizationCacheName);
        String activeSessionCacheName = data.getActiveSessionCacheName();
        configuration.setActiveSessionCacheName(activeSessionCacheName == null ? DEFAULT_ACTIVE_SESSION_CACHE_NAME : activeSessionCacheName);
        return configuration;
    }

    @Override
    public SecurityConfiguration swap(YamlSecurityConfiguration yamlConfiguration) {
        SecurityConfiguration configuration = new SecurityConfiguration();
        Boolean enabled = yamlConfiguration.getEnabled();
        configuration.setEnabled(enabled == null ? DEFAULT_ENABLED : enabled);
        CacheType cacheType = yamlConfiguration.getCacheType();
        configuration.setCacheType(cacheType == null ? DEFAULT_CACHE_TYPE : cacheType);
        Boolean authenticationCacheEnabled = yamlConfiguration.getAuthenticationCacheEnabled();
        configuration.setAuthenticationCacheEnabled(authenticationCacheEnabled == null ? DEFAULT_AUTHENTICATION_CACHE_ENABLED : authenticationCacheEnabled);
        Boolean authorizationCacheEnabled = yamlConfiguration.getAuthorizationCacheEnabled();
        configuration.setAuthorizationCacheEnabled(authorizationCacheEnabled == null ? DEFAULT_AUTHORIZATION_CACHE_ENABLED : authorizationCacheEnabled);
        String authenticationCacheName = yamlConfiguration.getAuthenticationCacheName();
        configuration.setAuthenticationCacheName(authenticationCacheName == null ? DEFAULT_AUTHENTICATION_CACHE_NAME : authenticationCacheName);
        String authorizationCacheName = yamlConfiguration.getAuthorizationCacheName();
        configuration.setAuthorizationCacheName(authorizationCacheName == null ? DEFAULT_AUTHORIZATION_CACHE_NAME : authorizationCacheName);
        String activeSessionCacheName = yamlConfiguration.getActiveSessionCacheName();
        configuration.setActiveSessionCacheName(activeSessionCacheName == null ? DEFAULT_ACTIVE_SESSION_CACHE_NAME : activeSessionCacheName);
        return configuration;
    }
}