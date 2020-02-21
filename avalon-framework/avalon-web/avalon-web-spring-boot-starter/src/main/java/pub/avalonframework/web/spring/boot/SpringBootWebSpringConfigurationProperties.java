package pub.avalonframework.web.spring.boot;

import pub.avalonframework.web.spring.yaml.config.YamlWebSpringConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author baichao
 */
@ConfigurationProperties(prefix = "spring.avalon.web.spring")
public class SpringBootWebSpringConfigurationProperties extends YamlWebSpringConfiguration {

}