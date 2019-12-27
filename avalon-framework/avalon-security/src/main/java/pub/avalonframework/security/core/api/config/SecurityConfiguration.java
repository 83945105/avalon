package pub.avalonframework.security.core.api.config;

import pub.avalonframework.security.core.api.beans.CacheType;
import pub.avalonframework.security.core.api.config.authentication.AuthenticationConfiguration;
import pub.avalonframework.security.core.api.config.cache.EhCacheConfiguration;
import pub.avalonframework.security.core.api.config.cache.RedisConfiguration;
import pub.avalonframework.security.core.api.config.filter.FilterConfiguration;
import pub.avalonframework.security.core.api.config.http.HttpConfiguration;

/**
 * Security configuration.
 *
 * @author baichao
 */
public class SecurityConfiguration {

    private Boolean enabled;

    private CacheType cacheType;

    private Boolean authenticationCacheEnabled;

    private Boolean authorizationCacheEnabled;

    private String authenticationCacheName;

    private String authorizationCacheName;

    private String activeSessionCacheName;

    private HttpConfiguration httpConfiguration;

    private AuthenticationConfiguration authenticationConfiguration;

    private EhCacheConfiguration ehCacheConfiguration;

    private RedisConfiguration redisConfiguration;

    private FilterConfiguration filterConfiguration;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public CacheType getCacheType() {
        return cacheType;
    }

    public void setCacheType(CacheType cacheType) {
        this.cacheType = cacheType;
    }

    public Boolean getAuthenticationCacheEnabled() {
        return authenticationCacheEnabled;
    }

    public void setAuthenticationCacheEnabled(Boolean authenticationCacheEnabled) {
        this.authenticationCacheEnabled = authenticationCacheEnabled;
    }

    public Boolean getAuthorizationCacheEnabled() {
        return authorizationCacheEnabled;
    }

    public void setAuthorizationCacheEnabled(Boolean authorizationCacheEnabled) {
        this.authorizationCacheEnabled = authorizationCacheEnabled;
    }

    public String getAuthenticationCacheName() {
        return authenticationCacheName;
    }

    public void setAuthenticationCacheName(String authenticationCacheName) {
        this.authenticationCacheName = authenticationCacheName;
    }

    public String getAuthorizationCacheName() {
        return authorizationCacheName;
    }

    public void setAuthorizationCacheName(String authorizationCacheName) {
        this.authorizationCacheName = authorizationCacheName;
    }

    public String getActiveSessionCacheName() {
        return activeSessionCacheName;
    }

    public void setActiveSessionCacheName(String activeSessionCacheName) {
        this.activeSessionCacheName = activeSessionCacheName;
    }

    public HttpConfiguration getHttpConfiguration() {
        return httpConfiguration;
    }

    public void setHttpConfiguration(HttpConfiguration httpConfiguration) {
        this.httpConfiguration = httpConfiguration;
    }

    public AuthenticationConfiguration getAuthenticationConfiguration() {
        return authenticationConfiguration;
    }

    public void setAuthenticationConfiguration(AuthenticationConfiguration authenticationConfiguration) {
        this.authenticationConfiguration = authenticationConfiguration;
    }

    public EhCacheConfiguration getEhCacheConfiguration() {
        return ehCacheConfiguration;
    }

    public void setEhCacheConfiguration(EhCacheConfiguration ehCacheConfiguration) {
        this.ehCacheConfiguration = ehCacheConfiguration;
    }

    public RedisConfiguration getRedisConfiguration() {
        return redisConfiguration;
    }

    public void setRedisConfiguration(RedisConfiguration redisConfiguration) {
        this.redisConfiguration = redisConfiguration;
    }

    public FilterConfiguration getFilterConfiguration() {
        return filterConfiguration;
    }

    public void setFilterConfiguration(FilterConfiguration filterConfiguration) {
        this.filterConfiguration = filterConfiguration;
    }
}