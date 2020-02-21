package pub.avalonframework.web.spring.boot;

import pub.avalonframework.web.spring.yaml.config.YamlCorsConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author baichao
 */
@ConfigurationProperties(prefix = "spring.avalon.web.spring.cors")
public class SpringBootCorsConfigurationProperties extends YamlCorsConfiguration {

}