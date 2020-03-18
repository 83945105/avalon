package pub.avalonframework.wechat.official.account.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pub.avalonframework.wechat.official.account.core.yaml.config.YamlWechatOfficialAccountConfiguration;

/**
 * @author baichao
 */
@ConfigurationProperties(prefix = "spring.avalon.wechat.official-account")
public class SpringBootWechatOfficialAccountConfigurationProperties extends YamlWechatOfficialAccountConfiguration {

}