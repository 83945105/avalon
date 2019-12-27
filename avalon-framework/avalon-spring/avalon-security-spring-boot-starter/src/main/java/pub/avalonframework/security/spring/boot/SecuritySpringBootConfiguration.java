package pub.avalonframework.security.spring.boot;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import pub.avalonframework.security.core.api.config.SecurityConfiguration;
import pub.avalonframework.security.core.api.config.authentication.AuthenticationConfiguration;
import pub.avalonframework.security.core.api.config.cache.EhCacheConfiguration;
import pub.avalonframework.security.core.api.config.cache.RedisConfiguration;
import pub.avalonframework.security.core.api.config.filter.FilterConfiguration;
import pub.avalonframework.security.core.api.config.http.HttpConfiguration;
import pub.avalonframework.security.core.yaml.swapper.impl.*;
import pub.avalonframework.security.spring.boot.authentication.SpringBootAuthenticationConfigurationProperties;
import pub.avalonframework.security.spring.boot.cache.SpringBootEhCacheConfigurationProperties;
import pub.avalonframework.security.spring.boot.cache.SpringBootRedisCacheConfigurationProperties;
import pub.avalonframework.security.spring.boot.cache.SpringBootRedisConfigurationProperties;
import pub.avalonframework.security.spring.boot.cache.SpringBootRedisSessionConfigurationProperties;
import pub.avalonframework.security.spring.boot.filter.SpringBootFilterConfigurationProperties;
import pub.avalonframework.security.spring.boot.http.SpringBootHttpConfigurationProperties;

/**
 * Security spring boot configuration.
 *
 * @author baichao
 */
@Configuration
@EnableConfigurationProperties({
        SpringBootRootConfigurationProperties.class,
        SpringBootHttpConfigurationProperties.class,
        SpringBootAuthenticationConfigurationProperties.class,
        SpringBootEhCacheConfigurationProperties.class,
        SpringBootRedisConfigurationProperties.class,
        SpringBootRedisSessionConfigurationProperties.class,
        SpringBootRedisCacheConfigurationProperties.class,
        SpringBootFilterConfigurationProperties.class
})
public class SecuritySpringBootConfiguration implements EnvironmentAware {

    private final SpringBootRootConfigurationProperties rootConfigurationProperties;

    private final SpringBootHttpConfigurationProperties httpConfigurationProperties;

    private final SpringBootAuthenticationConfigurationProperties authenticationConfigurationProperties;

    private final SpringBootEhCacheConfigurationProperties ehCacheConfigurationProperties;

    private final SpringBootRedisConfigurationProperties redisConfigurationProperties;

    private final SpringBootFilterConfigurationProperties filterConfigurationProperties;

    public SecuritySpringBootConfiguration(SpringBootRootConfigurationProperties rootConfigurationProperties, SpringBootHttpConfigurationProperties httpConfigurationProperties, SpringBootAuthenticationConfigurationProperties authenticationConfigurationProperties, SpringBootEhCacheConfigurationProperties ehCacheConfigurationProperties, SpringBootRedisConfigurationProperties redisConfigurationProperties, SpringBootFilterConfigurationProperties filterConfigurationProperties) {
        this.rootConfigurationProperties = rootConfigurationProperties;
        this.httpConfigurationProperties = httpConfigurationProperties;
        this.authenticationConfigurationProperties = authenticationConfigurationProperties;
        this.ehCacheConfigurationProperties = ehCacheConfigurationProperties;
        this.redisConfigurationProperties = redisConfigurationProperties;
        this.filterConfigurationProperties = filterConfigurationProperties;
    }

    @Bean
    public HttpConfiguration httpConfiguration() {
        return new HttpConfigurationYamlSwapper().swap(httpConfigurationProperties);
    }

    @Bean
    public AuthenticationConfiguration authenticationConfiguration() {
        return new AuthenticationConfigurationYamlSwapper().swap(authenticationConfigurationProperties);
    }

    @Bean
    public EhCacheConfiguration ehCacheConfiguration() {
        return new EhCacheConfigurationYamlSwapper().swap(ehCacheConfigurationProperties);
    }

    @Bean
    public RedisConfiguration redisConfiguration() {
        return new RedisConfigurationYamlSwapper().swap(redisConfigurationProperties);
    }

    @Bean
    public FilterConfiguration filterConfiguration() {
        return new FilterConfigurationYamlSwapper().swap(filterConfigurationProperties);
    }

    @Bean
    public SecurityConfiguration securityConfiguration() {
        SecurityConfiguration securityConfiguration = new RootConfigurationYamlSwapper().swap(rootConfigurationProperties);
        securityConfiguration.setHttpConfiguration(httpConfiguration());
        securityConfiguration.setAuthenticationConfiguration(authenticationConfiguration());
        securityConfiguration.setEhCacheConfiguration(ehCacheConfiguration());
        securityConfiguration.setRedisConfiguration(redisConfiguration());
        securityConfiguration.setFilterConfiguration(filterConfiguration());
        return securityConfiguration;
    }

    @Override
    public void setEnvironment(Environment environment) {

    }
}