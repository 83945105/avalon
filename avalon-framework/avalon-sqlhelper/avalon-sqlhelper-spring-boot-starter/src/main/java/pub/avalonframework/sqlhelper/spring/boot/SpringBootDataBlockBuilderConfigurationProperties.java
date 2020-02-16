package pub.avalonframework.sqlhelper.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pub.avalonframework.sqlhelper.core.yaml.config.YamlDataBlockBuilderConfiguration;

/**
 * @author baichao
 */
@ConfigurationProperties(prefix = "spring.avalon.sqlhelper.sql-builder.data-block-builder")
public class SpringBootDataBlockBuilderConfigurationProperties extends YamlDataBlockBuilderConfiguration {

}