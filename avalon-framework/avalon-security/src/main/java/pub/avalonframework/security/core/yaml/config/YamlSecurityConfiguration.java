package pub.avalonframework.security.core.yaml.config;

import pub.avalonframework.security.core.api.beans.CacheType;
import pub.avalonframework.security.core.yaml.config.authentication.YamlAuthenticationConfiguration;
import pub.avalonframework.security.core.yaml.config.cache.YamlEhCacheConfiguration;
import pub.avalonframework.security.core.yaml.config.cache.YamlRedisConfiguration;
import pub.avalonframework.security.core.yaml.config.filter.YamlFilterConfiguration;
import pub.avalonframework.security.core.yaml.config.http.YamlHttpConfiguration;

/**
 * @author baichao
 */
public class YamlSecurityConfiguration implements YamlConfiguration {

    private Boolean enabled;

    private CacheType cacheType;

    private Boolean authenticationCacheEnabled;

    private Boolean authorizationCacheEnabled;

    private String authenticationCacheName;

    private String authorizationCacheName;

    private String activeSessionCacheName;

    private YamlHttpConfiguration httpConfiguration;

    private YamlAuthenticationConfiguration authenticationConfiguration;

    private YamlEhCacheConfiguration ehCacheConfiguration;

    private YamlRedisConfiguration redisConfiguration;

    private YamlFilterConfiguration filterConfiguration;

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

    public YamlHttpConfiguration getHttpConfiguration() {
        return httpConfiguration;
    }

    public void setHttpConfiguration(YamlHttpConfiguration httpConfiguration) {
        this.httpConfiguration = httpConfiguration;
    }

    public YamlAuthenticationConfiguration getAuthenticationConfiguration() {
        return authenticationConfiguration;
    }

    public void setAuthenticationConfiguration(YamlAuthenticationConfiguration authenticationConfiguration) {
        this.authenticationConfiguration = authenticationConfiguration;
    }

    public YamlEhCacheConfiguration getEhCacheConfiguration() {
        return ehCacheConfiguration;
    }

    public void setEhCacheConfiguration(YamlEhCacheConfiguration ehCacheConfiguration) {
        this.ehCacheConfiguration = ehCacheConfiguration;
    }

    public YamlRedisConfiguration getRedisConfiguration() {
        return redisConfiguration;
    }

    public void setRedisConfiguration(YamlRedisConfiguration redisConfiguration) {
        this.redisConfiguration = redisConfiguration;
    }

    public YamlFilterConfiguration getFilterConfiguration() {
        return filterConfiguration;
    }

    public void setFilterConfiguration(YamlFilterConfiguration filterConfiguration) {
        this.filterConfiguration = filterConfiguration;
    }
}