package pub.avalonframework.wechat.official.account.spring.boot;

import pub.avalonframework.core.yaml.config.YamlEhCacheConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author baichao
 */
@ConfigurationProperties(prefix = "spring.avalon.wechat.official-account.web-page-authorization.api-oauth2-state-cache")
public class SpringBootApiOauth2StateCacheConfigurationProperties extends YamlEhCacheConfiguration {

}