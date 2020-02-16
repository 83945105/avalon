package pub.avalonframework.sqlhelper.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pub.avalonframework.sqlhelper.core.yaml.config.YamlJdbcConfiguration;

/**
 * @author baichao
 */
@ConfigurationProperties(prefix = "spring.avalon.sqlhelper.jdbc")
public class SpringBootJdbcConfigurationProperties extends YamlJdbcConfiguration {

}