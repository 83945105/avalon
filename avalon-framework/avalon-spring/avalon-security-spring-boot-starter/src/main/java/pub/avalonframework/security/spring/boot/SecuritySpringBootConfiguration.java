package pub.avalonframework.security.spring.boot;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import pub.avalonframework.security.core.api.config.*;
import pub.avalonframework.security.core.yaml.swapper.impl.*;

/**
 * Security spring boot configuration.
 *
 * @author baichao
 */
@Configuration
@EnableConfigurationProperties({
        SpringBootHttpConfigurationProperties.class,
        SpringBootSessionConfigurationProperties.class,
        SpringBootAuthenticationConfigurationProperties.class,
        SpringBootAuthorizationConfigurationProperties.class,
        SpringBootEhCacheConfigurationProperties.class,
        SpringBootFilterConfigurationProperties.class,
        SpringBootRootConfigurationProperties.class
})
public class SecuritySpringBootConfiguration implements EnvironmentAware {

    private final SpringBootHttpConfigurationProperties httpConfigurationProperties;

    private final SpringBootSessionConfigurationProperties sessionConfigurationProperties;

    private final SpringBootAuthenticationConfigurationProperties authenticationConfigurationProperties;

    private final SpringBootAuthorizationConfigurationProperties authorizationConfigurationProperties;

    private final SpringBootEhCacheConfigurationProperties ehCacheConfigurationProperties;

    private final SpringBootFilterConfigurationProperties filterConfigurationProperties;

    private final SpringBootRootConfigurationProperties rootConfigurationProperties;

    public SecuritySpringBootConfiguration(SpringBootHttpConfigurationProperties httpConfigurationProperties, SpringBootSessionConfigurationProperties sessionConfigurationProperties, SpringBootAuthenticationConfigurationProperties authenticationConfigurationProperties, SpringBootAuthorizationConfigurationProperties authorizationConfigurationProperties, SpringBootEhCacheConfigurationProperties ehCacheConfigurationProperties, SpringBootFilterConfigurationProperties filterConfigurationProperties, SpringBootRootConfigurationProperties rootConfigurationProperties) {
        this.httpConfigurationProperties = httpConfigurationProperties;
        this.sessionConfigurationProperties = sessionConfigurationProperties;
        this.authenticationConfigurationProperties = authenticationConfigurationProperties;
        this.authorizationConfigurationProperties = authorizationConfigurationProperties;
        this.ehCacheConfigurationProperties = ehCacheConfigurationProperties;
        this.filterConfigurationProperties = filterConfigurationProperties;
        this.rootConfigurationProperties = rootConfigurationProperties;
    }

    @Bean
    @ConditionalOnMissingBean(HttpConfiguration.class)
    public HttpConfiguration httpConfiguration() {
        return new HttpConfigurationYamlSwapper().swap(httpConfigurationProperties);
    }

    @Bean
    @ConditionalOnMissingBean(SessionConfiguration.class)
    public SessionConfiguration sessionConfiguration() {
        return new SessionConfigurationYamlSwapper().swap(sessionConfigurationProperties);
    }

    @Bean
    @ConditionalOnMissingBean(AuthenticationConfiguration.class)
    public AuthenticationConfiguration authenticationConfiguration() {
        return new AuthenticationConfigurationYamlSwapper().swap(authenticationConfigurationProperties);
    }

    @Bean
    @ConditionalOnMissingBean(AuthorizationConfiguration.class)
    public AuthorizationConfiguration authorizationConfiguration() {
        return new AuthorizationConfigurationYamlSwapper().swap(authorizationConfigurationProperties);
    }

    @Bean
    @ConditionalOnMissingBean(EhCacheConfiguration.class)
    public EhCacheConfiguration ehCacheConfiguration() {
        return new EhCacheConfigurationYamlSwapper().swap(ehCacheConfigurationProperties);
    }

    @Bean
    @ConditionalOnMissingBean(FilterConfiguration.class)
    public FilterConfiguration filterConfiguration() {
        return new FilterConfigurationYamlSwapper().swap(filterConfigurationProperties);
    }

    @Bean
    @ConditionalOnMissingBean(SecurityConfiguration.class)
    public SecurityConfiguration securityConfiguration(HttpConfiguration httpConfiguration,
                                                       SessionConfiguration sessionConfiguration,
                                                       AuthenticationConfiguration authenticationConfiguration,
                                                       AuthorizationConfiguration authorizationConfiguration,
                                                       EhCacheConfiguration ehCacheConfiguration,
                                                       FilterConfiguration filterConfiguration) {
        SecurityConfiguration securityConfiguration = new RootConfigurationYamlSwapper().swap(rootConfigurationProperties);
        securityConfiguration.setHttp(httpConfiguration);
        securityConfiguration.setSession(sessionConfiguration);
        securityConfiguration.setAuthentication(authenticationConfiguration);
        securityConfiguration.setAuthorization(authorizationConfiguration);
        securityConfiguration.setEhCache(ehCacheConfiguration);
        securityConfiguration.setFilter(filterConfiguration);
        return securityConfiguration;
    }

    @Override
    public void setEnvironment(Environment environment) {

    }
}