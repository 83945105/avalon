package pub.avalonframework.sqlhelper.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pub.avalonframework.sqlhelper.core.yaml.config.YamlSqlBuilderConfiguration;

/**
 * @author baichao
 */
@ConfigurationProperties(prefix = "spring.avalon.sqlhelper.sql-builder")
public class SpringBootSqlBuilderConfigurationProperties extends YamlSqlBuilderConfiguration {

}