package pub.avalonframework.security.spring.boot.cache;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pub.avalonframework.security.core.yaml.config.cache.YamlEhCacheConfiguration;

/**
 * EhCache configuration properties.
 *
 * @author baichao
 */
@ConfigurationProperties(prefix = "spring.avalon.security.ehcache")
public class SpringBootEhCacheConfigurationProperties extends YamlEhCacheConfiguration {

}