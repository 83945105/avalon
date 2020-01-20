package pub.avalonframework.security.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pub.avalonframework.security.core.yaml.config.YamlAuthorizationConfiguration;

/**
 * Authorization configuration properties.
 *
 * @author baichao
 */
@ConfigurationProperties(prefix = "spring.avalon.security.authorization")
public class SpringBootAuthorizationConfigurationProperties extends YamlAuthorizationConfiguration {

}