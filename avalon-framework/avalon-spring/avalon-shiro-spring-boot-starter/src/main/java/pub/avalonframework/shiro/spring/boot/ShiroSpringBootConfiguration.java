package pub.avalonframework.shiro.spring.boot;

import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import pub.avalonframework.redis.core.api.config.RedisConfiguration;
import pub.avalonframework.redis.spring.core.RedisTemplate;
import pub.avalonframework.redis.spring.serializer.GenericToStringSerializer;
import pub.avalonframework.redis.spring.serializer.Jackson2JsonRedisSerializer;
import pub.avalonframework.security.core.api.beans.CacheType;
import pub.avalonframework.security.core.api.beans.IncorrectCacheTypeException;
import pub.avalonframework.security.core.api.config.*;
import pub.avalonframework.security.core.api.service.LoginAuthenticationService;
import pub.avalonframework.security.core.api.service.ResourceAuthenticationService;
import pub.avalonframework.security.core.api.service.WebService;
import pub.avalonframework.shiro.authz.permission.UrlPermissionResolver;
import pub.avalonframework.shiro.cache.RedisCacheManager;
import pub.avalonframework.shiro.realm.ShiroRealm;
import pub.avalonframework.shiro.service.impl.ShiroLoginAuthenticationServiceImpl;
import pub.avalonframework.shiro.service.impl.ShiroResourceAuthenticationServiceImpl;
import pub.avalonframework.shiro.service.impl.ShiroWebServiceImpl;
import pub.avalonframework.shiro.session.mgt.eis.RedisSessionDAO;
import pub.avalonframework.shiro.web.filter.authc.AjaxFormAuthenticationFilter;
import pub.avalonframework.shiro.web.filter.authc.ResourceCheckFilter;
import pub.avalonframework.shiro.web.session.mgt.RedisWebSessionManager;
import pub.avalonframework.shiro.web.session.mgt.SeparationWebSessionManager;

import javax.servlet.Filter;
import java.util.*;

/**
 * Shiro spring boot configuration.
 *
 * @author baichao
 */
@Configuration
@ConditionalOnBean(SecurityConfiguration.class)
public class ShiroSpringBootConfiguration implements EnvironmentAware {

    private static final Logger logger = LoggerFactory.getLogger(ShiroSpringBootConfiguration.class);

    private final SecurityConfiguration securityConfiguration;

    public ShiroSpringBootConfiguration(SecurityConfiguration securityConfiguration) {
        this.securityConfiguration = securityConfiguration;
    }

    @Bean
    @ConditionalOnMissingBean(WebService.class)
    public WebService webService() {
        return new ShiroWebServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(LoginAuthenticationService.class)
    public LoginAuthenticationService loginAuthenticationService() {
        return new ShiroLoginAuthenticationServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(ResourceAuthenticationService.class)
    public ResourceAuthenticationService resourceAuthenticationService() {
        return new ShiroResourceAuthenticationServiceImpl();
    }

    @Bean
    @ConditionalOnBean(SecurityConfiguration.class)
    @ConditionalOnMissingBean(Realm.class)
    public Realm realm(LoginAuthenticationService loginAuthenticationService) {
        AuthenticationConfiguration authenticationConfiguration = this.securityConfiguration.getAuthentication();
        AuthorizationConfiguration authorizationConfiguration = this.securityConfiguration.getAuthorization();
        ShiroRealm shiroRealm = new ShiroRealm(this.securityConfiguration, loginAuthenticationService);
        shiroRealm.setCachingEnabled(true);
        shiroRealm.setAuthenticationCachingEnabled(authenticationConfiguration.getCacheEnabled());
        shiroRealm.setAuthenticationCacheName(authenticationConfiguration.getCacheName());
        shiroRealm.setAuthorizationCachingEnabled(authorizationConfiguration.getCacheEnabled());
        shiroRealm.setAuthorizationCacheName(authorizationConfiguration.getCacheName());
        return shiroRealm;
    }

    @Bean
    @ConditionalOnMissingBean(SessionIdGenerator.class)
    public SessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }

    @Bean
    @ConditionalOnMissingBean(Cookie.class)
    public Cookie cookie() {
        SimpleCookie cookie = new SimpleCookie();
        SessionConfiguration sessionConfiguration = this.securityConfiguration.getSession();
        cookie.setName(sessionConfiguration.getSessionIdName());
        cookie.setMaxAge(Integer.valueOf(sessionConfiguration.getCookieMaxAge() + ""));
        cookie.setHttpOnly(true);
        return cookie;
    }

    @Bean
    @ConditionalOnMissingBean(SessionValidationScheduler.class)
    public SessionValidationScheduler sessionValidationScheduler() {
        SessionConfiguration sessionConfiguration = this.securityConfiguration.getSession();
        ExecutorServiceSessionValidationScheduler scheduler = new ExecutorServiceSessionValidationScheduler();
        scheduler.setInterval(sessionConfiguration.getSessionValidationInterval());
        return scheduler;
    }

    private RedisTemplate<String, Object, String, Object> sessionRedisTemplate() {
        RedisConfiguration redisConfiguration = this.securityConfiguration.getSession().getRedis();
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisConfiguration.getHostName());
        redisStandaloneConfiguration.setPort(redisConfiguration.getPort());
        redisStandaloneConfiguration.setDatabase(redisConfiguration.getDatabase());
        redisStandaloneConfiguration.setPassword(RedisPassword.of(redisConfiguration.getPassword()));
        return new RedisTemplate<>
                (
                        new JedisConnectionFactory(redisStandaloneConfiguration),
                        new Jackson2JsonRedisSerializer<>(Object.class),
                        new GenericToStringSerializer<>(String.class),
                        new Jackson2JsonRedisSerializer<>(Object.class),
                        new Jackson2JsonRedisSerializer<>(String.class),
                        new Jackson2JsonRedisSerializer<>(Object.class)
                );
    }

    @Bean
    @ConditionalOnMissingBean(SessionDAO.class)
    public SessionDAO sessionDAO(SessionIdGenerator sessionIdGenerator) {
        switch (this.securityConfiguration.getCacheType()) {
            case EHCACHE:
                EnterpriseCacheSessionDAO enterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
                enterpriseCacheSessionDAO.setSessionIdGenerator(sessionIdGenerator);
                enterpriseCacheSessionDAO.setActiveSessionsCacheName(this.securityConfiguration.getSession().getSessionCacheName());
                return enterpriseCacheSessionDAO;
            case REDIS:
                SessionConfiguration sessionConfiguration = this.securityConfiguration.getSession();
                RedisSessionDAO redisSessionDAO = new RedisSessionDAO(sessionRedisTemplate());
                redisSessionDAO.setSessionIdGenerator(sessionIdGenerator);
                redisSessionDAO.setExpire(sessionConfiguration.getSessionTimeout());
                return redisSessionDAO;
            default:
                throw new IncorrectCacheTypeException(securityConfiguration.getCacheType(), ShiroSpringBootConfiguration.class, null);
        }
    }

    @Bean
    @ConditionalOnMissingBean(SessionManager.class)
    public SessionManager sessionManager(SessionValidationScheduler sessionValidationScheduler, SessionDAO sessionDAO, Cookie sessionIdCookie) {
        SessionConfiguration sessionConfiguration = this.securityConfiguration.getSession();
        switch (this.securityConfiguration.getCacheType()) {
            case EHCACHE:
                SeparationWebSessionManager separationSessionManager = new SeparationWebSessionManager();
                separationSessionManager.setGlobalSessionTimeout(sessionConfiguration.getSessionTimeout());
                separationSessionManager.setSessionValidationScheduler(sessionValidationScheduler);
                separationSessionManager.setDeleteInvalidSessions(true);
                separationSessionManager.setSessionValidationSchedulerEnabled(true);
                separationSessionManager.setSessionDAO(sessionDAO);
                separationSessionManager.setSessionIdCookieEnabled(true);
                separationSessionManager.setSessionIdCookie(sessionIdCookie);
                return separationSessionManager;
            case REDIS:
                RedisWebSessionManager redisWebSessionManager = new RedisWebSessionManager();
                redisWebSessionManager.setGlobalSessionTimeout(sessionConfiguration.getSessionTimeout());
                redisWebSessionManager.setSessionValidationScheduler(sessionValidationScheduler);
                redisWebSessionManager.setDeleteInvalidSessions(true);
                redisWebSessionManager.setSessionValidationSchedulerEnabled(true);
                redisWebSessionManager.setSessionDAO(sessionDAO);
                redisWebSessionManager.setSessionIdCookieEnabled(true);
                redisWebSessionManager.setSessionIdCookie(sessionIdCookie);
                return redisWebSessionManager;
            default:
                throw new IncorrectCacheTypeException(securityConfiguration.getCacheType(), ShiroSpringBootConfiguration.class, null);
        }
    }

    @Bean
    @ConditionalOnMissingBean(PermissionResolver.class)
    public PermissionResolver permissionResolver() {
        return new UrlPermissionResolver();
    }

    @Bean
    @Qualifier("authorizer")
    @ConditionalOnMissingBean(name = "authorizer")
    public Authorizer authorizer(PermissionResolver permissionResolver) {
        ModularRealmAuthorizer modularRealmAuthorizer = new ModularRealmAuthorizer();
        modularRealmAuthorizer.setPermissionResolver(permissionResolver);
        return modularRealmAuthorizer;
    }

    private RedisTemplate<String, SimpleAuthenticationInfo, Object, Object> authenticationRedisTemplate() {
        RedisConfiguration redisConfiguration = this.securityConfiguration.getAuthentication().getRedis();
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisConfiguration.getHostName());
        redisStandaloneConfiguration.setPort(redisConfiguration.getPort());
        redisStandaloneConfiguration.setDatabase(redisConfiguration.getDatabase());
        redisStandaloneConfiguration.setPassword(RedisPassword.of(redisConfiguration.getPassword()));
        return new RedisTemplate<>
                (
                        new JedisConnectionFactory(redisStandaloneConfiguration),
                        new Jackson2JsonRedisSerializer<>(Object.class),
                        new GenericToStringSerializer<>(String.class),
                        new Jackson2JsonRedisSerializer<>(SimpleAuthenticationInfo.class),
                        new Jackson2JsonRedisSerializer<>(Object.class),
                        new Jackson2JsonRedisSerializer<>(Object.class)
                );
    }

    private RedisTemplate<String, SimpleAuthorizationInfo, Object, Object> authorizationRedisTemplate() {
        RedisConfiguration redisConfiguration = this.securityConfiguration.getAuthorization().getRedis();
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisConfiguration.getHostName());
        redisStandaloneConfiguration.setPort(redisConfiguration.getPort());
        redisStandaloneConfiguration.setDatabase(redisConfiguration.getDatabase());
        redisStandaloneConfiguration.setPassword(RedisPassword.of(redisConfiguration.getPassword()));
        return new RedisTemplate<>
                (
                        new JedisConnectionFactory(redisStandaloneConfiguration),
                        new Jackson2JsonRedisSerializer<>(Object.class),
                        new GenericToStringSerializer<>(String.class),
                        new Jackson2JsonRedisSerializer<>(SimpleAuthorizationInfo.class),
                        new Jackson2JsonRedisSerializer<>(Object.class),
                        new Jackson2JsonRedisSerializer<>(Object.class)
                );
    }

    @Bean
    @ConditionalOnMissingBean(CacheManager.class)
    public CacheManager cacheManager() {
        switch (this.securityConfiguration.getCacheType()) {
            case EHCACHE:
                EhCacheConfiguration ehCacheConfiguration = this.securityConfiguration.getEhCache();
                EhCacheManager ehCacheManager = new EhCacheManager();
                if (ehCacheConfiguration.getCacheManagerConfigFile() != null) {
                    ehCacheManager.setCacheManagerConfigFile(ehCacheConfiguration.getCacheManagerConfigFile());
                }
                return ehCacheManager;
            case REDIS:
                return new RedisCacheManager
                        (
                                this.securityConfiguration.getAuthentication().getCacheName(),
                                authenticationRedisTemplate(),
                                this.securityConfiguration.getAuthorization().getCacheName(),
                                authorizationRedisTemplate(),
                                -1
                        );
            default:
                throw new IncorrectCacheTypeException(securityConfiguration.getCacheType(), ShiroSpringBootConfiguration.class, null);
        }
    }

    @Bean
    @ConditionalOnMissingBean(SecurityManager.class)
    public SecurityManager securityManager(Realm realm, SessionManager sessionManager, @Qualifier("authorizer") Authorizer authorizer, CacheManager cacheManager) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setAuthorizer(authorizer);
        defaultWebSecurityManager.setSessionManager(sessionManager);
        defaultWebSecurityManager.setCacheManager(cacheManager);
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(SecurityManager securityManager) {
        MethodInvokingFactoryBean factoryBean = new MethodInvokingFactoryBean();
        factoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        factoryBean.setArguments(securityManager);
        return factoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 该过滤器不可以使用 @Bean 注册到spring容器, 这将会导致 ShiroFilterFactoryBean 内注册的过滤器 SpringShiroFilter 失效
     */
    private Filter formAuthenticationFilter(WebService webService, LoginAuthenticationService loginAuthenticationService) {
        AuthenticationConfiguration authenticationConfiguration = this.securityConfiguration.getAuthentication();
        AjaxFormAuthenticationFilter ajaxFormAuthenticationFilter = new AjaxFormAuthenticationFilter(webService, loginAuthenticationService, this.securityConfiguration);
        ajaxFormAuthenticationFilter.setUsernameParam(authenticationConfiguration.getUsernameKey());
        ajaxFormAuthenticationFilter.setPasswordParam(authenticationConfiguration.getPasswordKey());
        ajaxFormAuthenticationFilter.setLoginUrl(authenticationConfiguration.getUrl());
        ajaxFormAuthenticationFilter.setSuccessUrl(authenticationConfiguration.getSuccessUrl());
        ajaxFormAuthenticationFilter.setCrossDomain(true);
        return ajaxFormAuthenticationFilter;
    }

    /**
     * 该过滤器不可以使用 @Bean 注册到spring容器, 这将会导致 ShiroFilterFactoryBean 内注册的过滤器 SpringShiroFilter 失效
     */
    private Filter resourceCheckFilter(WebService webService, ResourceAuthenticationService resourceAuthenticationService) {
        return new ResourceCheckFilter(webService, resourceAuthenticationService, this.securityConfiguration);
    }

    @Bean
    @ConditionalOnMissingBean(ShiroFilterFactoryBean.class)
    public ShiroFilterFactoryBean shiroFilterFactoryBean(WebService webService, LoginAuthenticationService loginAuthenticationService, ResourceAuthenticationService resourceAuthenticationService, SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        FilterConfiguration filterConfiguration = this.securityConfiguration.getFilter();
        String formFilterName = filterConfiguration.getFormFilterName();
        shiroFilterFactoryBean.setLoginUrl(filterConfiguration.getLoginUrl());
        shiroFilterFactoryBean.setUnauthorizedUrl(filterConfiguration.getUnauthorizedUrl());
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put(formFilterName, formAuthenticationFilter(webService, loginAuthenticationService));
        filters.put("resourceCheckFilter", resourceCheckFilter(webService, resourceAuthenticationService));
        shiroFilterFactoryBean.setFilters(filters);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        if (!this.securityConfiguration.getEnabled()) {
            filterChainDefinitionMap.put("/**", "anon");
            shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
            return shiroFilterFactoryBean;
        }
        Set<String> releaseResources = new LinkedHashSet<>();
        Set<String> serviceReleaseResources = resourceAuthenticationService.getReleaseResources(Collections.emptyMap(), this.securityConfiguration);
        if (serviceReleaseResources != null && serviceReleaseResources.size() > 0) {
            releaseResources.addAll(serviceReleaseResources);
        }
        Set<String> configurationReleaseResources = filterConfiguration.getReleaseResources();
        if (configurationReleaseResources != null && configurationReleaseResources.size() > 0) {
            releaseResources.addAll(configurationReleaseResources);
        }
        logger.info("====================================================================================================");
        for (String releaseResource : releaseResources) {
            filterChainDefinitionMap.put(releaseResource, "anon");
            logger.info("Shiro release resource: " + releaseResource);
        }
        logger.info("====================================================================================================");
        String pageUrl = this.securityConfiguration.getAuthentication().getPageUrl();
        if (pageUrl != null && pageUrl.trim().length() > 0) {
            filterChainDefinitionMap.put(pageUrl, "anon");
        }
        String url = this.securityConfiguration.getAuthentication().getUrl();
        if (url != null && url.trim().length() > 0) {
            filterChainDefinitionMap.put(url, formFilterName + ",anon");
        }
        filterChainDefinitionMap.put("/**", formFilterName + ",resourceCheckFilter");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Override
    public void setEnvironment(Environment environment) {
        boolean enabled = this.securityConfiguration.getEnabled();
        if (!enabled) {
            logger.warn("Shiro disabled");
            return;
        }
        logger.info("Shiro enabled");
        CacheType cacheType = this.securityConfiguration.getCacheType();
        logger.info("Shiro cache type: " + cacheType.name());
    }
}