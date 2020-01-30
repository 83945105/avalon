package pub.avalonframework.shiro.spring.web;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.avalonframework.security.core.api.config.AuthenticationConfiguration;
import pub.avalonframework.security.core.api.config.FilterConfiguration;
import pub.avalonframework.security.core.api.config.SecurityConfiguration;
import pub.avalonframework.security.core.api.service.LoginAuthenticationService;
import pub.avalonframework.security.core.api.service.ResourceAuthenticationService;
import pub.avalonframework.security.core.api.service.WebService;
import pub.avalonframework.shiro.web.filter.authc.AjaxFormAuthenticationFilter;
import pub.avalonframework.shiro.web.filter.authc.ResourceCheckFilter;

import javax.servlet.Filter;
import java.util.*;

/**
 * @author baichao
 */
public class AvalonShiroFilterFactoryBean extends ShiroFilterFactoryBean {

    private static final Logger logger = LoggerFactory.getLogger(AvalonShiroFilterFactoryBean.class);

    private final SecurityConfiguration securityConfiguration;

    private final SecurityManager securityManager;

    private final WebService webService;

    private final LoginAuthenticationService loginAuthenticationService;

    private final ResourceAuthenticationService resourceAuthenticationService;

    public AvalonShiroFilterFactoryBean(SecurityConfiguration securityConfiguration, SecurityManager securityManager, WebService webService, LoginAuthenticationService loginAuthenticationService, ResourceAuthenticationService resourceAuthenticationService) {
        this.securityConfiguration = securityConfiguration;
        this.securityManager = securityManager;
        this.webService = webService;
        this.loginAuthenticationService = loginAuthenticationService;
        this.resourceAuthenticationService = resourceAuthenticationService;
        this.init();
    }

    private void init() {
        FilterConfiguration filterConfiguration = this.securityConfiguration.getFilter();
        String formFilterName = filterConfiguration.getFormFilterName();
        this.setLoginUrl(filterConfiguration.getLoginUrl());
        this.setUnauthorizedUrl(filterConfiguration.getUnauthorizedUrl());
        this.setSecurityManager(this.securityManager);
        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put(formFilterName, formAuthenticationFilter(this.webService, this.loginAuthenticationService));
        filters.put("resourceCheckFilter", resourceCheckFilter(this.webService, this.resourceAuthenticationService));
        this.setFilters(filters);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        if (!this.securityConfiguration.getEnabled()) {
            filterChainDefinitionMap.put("/**", "anon");
            this.setFilterChainDefinitionMap(filterChainDefinitionMap);
            return;
        }
        Set<String> releaseResources = new LinkedHashSet<>();
        Set<String> serviceReleaseResources = this.resourceAuthenticationService.getReleaseResources(Collections.emptyMap(), this.securityConfiguration);
        if (serviceReleaseResources != null && serviceReleaseResources.size() > 0) {
            releaseResources.addAll(serviceReleaseResources);
        }
        Set<String> configurationReleaseResources = filterConfiguration.getReleaseResources();
        if (configurationReleaseResources != null && configurationReleaseResources.size() > 0) {
            releaseResources.addAll(configurationReleaseResources);
        }
        AuthenticationConfiguration authentication = this.securityConfiguration.getAuthentication();
        releaseResources.add(authentication.getUrl());
        logger.info("====================================================================================================");
        for (String releaseResource : releaseResources) {
            filterChainDefinitionMap.put(releaseResource, "anon");
            logger.info("Shiro release resource: " + releaseResource);
        }
        logger.info("====================================================================================================");
        String pageUrl = authentication.getPageUrl();
        if (pageUrl != null && pageUrl.trim().length() > 0) {
            filterChainDefinitionMap.put(pageUrl, "anon");
        }
        String url = authentication.getUrl();
        if (url != null && url.trim().length() > 0) {
            filterChainDefinitionMap.put(url, formFilterName + ",anon");
        }
        filterChainDefinitionMap.put("/**", formFilterName + ",resourceCheckFilter");
        this.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }

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

    private Filter resourceCheckFilter(WebService webService, ResourceAuthenticationService resourceAuthenticationService) {
        return new ResourceCheckFilter(webService, resourceAuthenticationService, this.securityConfiguration);
    }
}