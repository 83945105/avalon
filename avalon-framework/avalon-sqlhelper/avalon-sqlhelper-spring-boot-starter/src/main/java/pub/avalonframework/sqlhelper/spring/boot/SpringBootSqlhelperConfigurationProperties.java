package pub.avalonframework.sqlhelper.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pub.avalonframework.sqlhelper.core.yaml.config.YamlSqlhelperConfiguration;

/**
 * @author baichao
 */
@ConfigurationProperties(prefix = "spring.avalon.sqlhelper")
public class SpringBootSqlhelperConfigurationProperties extends YamlSqlhelperConfiguration {

}