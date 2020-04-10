package pub.avalonframework.wechat.official.account.spring.boot;

import pub.avalonframework.wechat.official.account.core.yaml.config.YamlCustomMenuConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author baichao
 */
@ConfigurationProperties(prefix = "spring.avalon.wechat.official-account.custom-menu")
public class SpringBootCustomMenuConfigurationProperties extends YamlCustomMenuConfiguration {

}