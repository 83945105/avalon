package pub.avalonframework.security.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pub.avalonframework.security.core.yaml.config.YamlSecurityConfiguration;

/**
 * Root configuration properties.
 *
 * @author baichao
 */
@ConfigurationProperties(prefix = "spring.avalon.security")
public class SpringBootRootConfigurationProperties extends YamlSecurityConfiguration {

}