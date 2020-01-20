package pub.avalonframework.security.core.api.config;

import pub.avalonframework.security.core.api.beans.CacheType;

/**
 * Security configuration.
 *
 * @author baichao
 */
public class SecurityConfiguration {

    private Boolean enabled;

    private CacheType cacheType;

    private HttpConfiguration http;

    private SessionConfiguration session;

    private AuthenticationConfiguration authentication;

    private AuthorizationConfiguration authorization;

    private EhCacheConfiguration ehCache;

    private FilterConfiguration filter;

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

    public HttpConfiguration getHttp() {
        return http;
    }

    public void setHttp(HttpConfiguration http) {
        this.http = http;
    }

    public SessionConfiguration getSession() {
        return session;
    }

    public void setSession(SessionConfiguration session) {
        this.session = session;
    }

    public AuthenticationConfiguration getAuthentication() {
        return authentication;
    }

    public void setAuthentication(AuthenticationConfiguration authentication) {
        this.authentication = authentication;
    }

    public AuthorizationConfiguration getAuthorization() {
        return authorization;
    }

    public void setAuthorization(AuthorizationConfiguration authorization) {
        this.authorization = authorization;
    }

    public EhCacheConfiguration getEhCache() {
        return ehCache;
    }

    public void setEhCache(EhCacheConfiguration ehCache) {
        this.ehCache = ehCache;
    }

    public FilterConfiguration getFilter() {
        return filter;
    }

    public void setFilter(FilterConfiguration filter) {
        this.filter = filter;
    }
}