package pub.avalonframework.wechat.official.account.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pub.avalonframework.wechat.official.account.core.yaml.config.YamlWebPageAuthorizationConfiguration;

/**
 * @author baichao
 */
@ConfigurationProperties(prefix = "spring.avalon.wechat.official-account.web-page-authorization")
public class SpringBootWebPageAuthorizationConfigurationProperties extends YamlWebPageAuthorizationConfiguration {

}