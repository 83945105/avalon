package pub.avalonframework.security.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pub.avalonframework.security.core.yaml.config.YamlAuthenticationConfiguration;

/**
 * Authentication configuration properties.
 *
 * @author baichao
 */
@ConfigurationProperties(prefix = "spring.avalon.security.authentication")
public class SpringBootAuthenticationConfigurationProperties extends YamlAuthenticationConfiguration {

}