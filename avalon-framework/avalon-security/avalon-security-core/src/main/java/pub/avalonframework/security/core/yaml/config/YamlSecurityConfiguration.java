package pub.avalonframework.security.core.yaml.config;

import pub.avalonframework.core.yaml.config.YamlConfiguration;
import pub.avalonframework.security.core.api.beans.CacheType;

/**
 * @author baichao
 */
public class YamlSecurityConfiguration implements YamlConfiguration {

    private Boolean enabled;

    private CacheType cacheType;

    private YamlHttpConfiguration http;

    private YamlSessionConfiguration session;

    private YamlAuthenticationConfiguration authentication;

    private YamlAuthorizationConfiguration authorization;

    private YamlEhCacheConfiguration ehCache;

    private YamlFilterConfiguration filter;

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

    public YamlHttpConfiguration getHttp() {
        return http;
    }

    public void setHttp(YamlHttpConfiguration http) {
        this.http = http;
    }

    public YamlSessionConfiguration getSession() {
        return session;
    }

    public void setSession(YamlSessionConfiguration session) {
        this.session = session;
    }

    public YamlAuthenticationConfiguration getAuthentication() {
        return authentication;
    }

    public void setAuthentication(YamlAuthenticationConfiguration authentication) {
        this.authentication = authentication;
    }

    public YamlAuthorizationConfiguration getAuthorization() {
        return authorization;
    }

    public void setAuthorization(YamlAuthorizationConfiguration authorization) {
        this.authorization = authorization;
    }

    public YamlEhCacheConfiguration getEhCache() {
        return ehCache;
    }

    public void setEhCache(YamlEhCacheConfiguration ehCache) {
        this.ehCache = ehCache;
    }

    public YamlFilterConfiguration getFilter() {
        return filter;
    }

    public void setFilter(YamlFilterConfiguration filter) {
        this.filter = filter;
    }
}