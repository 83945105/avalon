package pub.avalonframework.security.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pub.avalonframework.redis.core.yaml.config.YamlRedisConfiguration;

/**
 * Session redis configuration properties.
 *
 * @author baichao
 */
@ConfigurationProperties(prefix = "spring.avalon.security.session.redis")
public class SpringBootSessionRedisConfigurationProperties extends YamlRedisConfiguration {

}