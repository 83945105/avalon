package pub.avalonframework.web.spring.boot;

import pub.avalonframework.web.spring.yaml.config.YamlResourceConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author baichao
 */
@ConfigurationProperties(prefix = "spring.avalon.web.spring.resource")
public class SpringBootResourceConfigurationProperties extends YamlResourceConfiguration {

}