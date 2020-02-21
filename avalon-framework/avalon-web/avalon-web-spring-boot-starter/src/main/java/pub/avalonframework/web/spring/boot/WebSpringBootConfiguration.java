package pub.avalonframework.web.spring.boot;

import pub.avalonframework.web.spring.api.config.CorsConfiguration;
import pub.avalonframework.web.spring.api.config.ResourceConfiguration;
import pub.avalonframework.web.spring.api.config.WebSpringConfiguration;
import pub.avalonframework.web.spring.yaml.swapper.impl.CorsConfigurationYamlSwapper;
import pub.avalonframework.web.spring.yaml.swapper.impl.ResourceConfigurationYamlSwapper;
import pub.avalonframework.web.spring.yaml.swapper.impl.WebSpringConfigurationYamlSwapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Web spring boot configuration.
 *
 * @author baichao
 */
@Configuration
@EnableConfigurationProperties({
        SpringBootCorsConfigurationProperties.class,
        SpringBootResourceConfigurationProperties.class,
        SpringBootWebSpringConfigurationProperties.class
})
public class WebSpringBootConfiguration implements EnvironmentAware {

    private final SpringBootCorsConfigurationProperties corsConfigurationProperties;

    private final SpringBootResourceConfigurationProperties resourceConfigurationProperties;

    private final SpringBootWebSpringConfigurationProperties webSpringConfigurationProperties;

    public WebSpringBootConfiguration(SpringBootCorsConfigurationProperties corsConfigurationProperties, SpringBootResourceConfigurationProperties resourceConfigurationProperties, SpringBootWebSpringConfigurationProperties webSpringConfigurationProperties) {
        this.corsConfigurationProperties = corsConfigurationProperties;
        this.resourceConfigurationProperties = resourceConfigurationProperties;
        this.webSpringConfigurationProperties = webSpringConfigurationProperties;
    }

    @Bean
    @ConditionalOnMissingBean(CorsConfiguration.class)
    public CorsConfiguration corsConfiguration() {
        return new CorsConfigurationYamlSwapper().swap(corsConfigurationProperties);
    }

    @Bean
    @ConditionalOnMissingBean(ResourceConfiguration.class)
    public ResourceConfiguration resourceConfiguration() {
        return new ResourceConfigurationYamlSwapper().swap(resourceConfigurationProperties);
    }

    @Bean
    @ConditionalOnMissingBean(WebSpringConfiguration.class)
    public WebSpringConfiguration webSpringConfiguration(CorsConfiguration corsConfiguration,
                                                         ResourceConfiguration resourceConfiguration) {
        WebSpringConfiguration webSpringConfiguration = new WebSpringConfigurationYamlSwapper().swap(webSpringConfigurationProperties);
        webSpringConfiguration.setCors(corsConfiguration);
        webSpringConfiguration.setResource(resourceConfiguration);
        return webSpringConfiguration;
    }

    @Override
    public void setEnvironment(Environment environment) {

    }
}