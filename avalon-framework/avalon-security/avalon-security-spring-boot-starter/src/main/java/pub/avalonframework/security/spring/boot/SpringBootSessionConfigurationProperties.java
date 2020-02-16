package pub.avalonframework.security.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pub.avalonframework.security.core.yaml.config.YamlSessionConfiguration;

/**
 * Session configuration properties.
 *
 * @author baichao
 */
@ConfigurationProperties(prefix = "spring.avalon.security.session")
public class SpringBootSessionConfigurationProperties extends YamlSessionConfiguration {

}