package pub.avalonframework.security.spring.boot.filter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pub.avalonframework.security.core.yaml.config.filter.YamlFilterConfiguration;

/**
 * Filter configuration properties.
 *
 * @author baichao
 */
@ConfigurationProperties(prefix = "spring.avalon.security.filter")
public class SpringBootFilterConfigurationProperties extends YamlFilterConfiguration {

}