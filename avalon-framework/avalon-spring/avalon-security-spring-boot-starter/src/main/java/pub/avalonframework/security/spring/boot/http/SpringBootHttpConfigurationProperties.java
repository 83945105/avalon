package pub.avalonframework.security.spring.boot.http;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pub.avalonframework.security.core.yaml.config.http.YamlHttpConfiguration;

/**
 * Http configuration properties.
 *
 * @author baichao
 */
@ConfigurationProperties(prefix = "spring.avalon.security.http")
public class SpringBootHttpConfigurationProperties extends YamlHttpConfiguration {

}