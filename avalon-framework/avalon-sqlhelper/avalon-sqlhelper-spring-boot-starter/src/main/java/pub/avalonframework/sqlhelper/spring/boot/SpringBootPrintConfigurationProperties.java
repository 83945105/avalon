package pub.avalonframework.sqlhelper.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pub.avalonframework.sqlhelper.core.yaml.config.YamlPrintConfiguration;

/**
 * @author baichao
 */
@ConfigurationProperties(prefix = "spring.avalon.sqlhelper.sql-print")
public class SpringBootPrintConfigurationProperties extends YamlPrintConfiguration {

}