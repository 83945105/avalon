package pub.avalonframework.security.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pub.avalonframework.redis.core.yaml.config.YamlRedisConfiguration;

/**
 * Authorization redis configuration properties.
 *
 * @author baichao
 */
@ConfigurationProperties(prefix = "spring.avalon.security.authorization.redis")
public class SpringBootAuthorizationRedisConfigurationProperties extends YamlRedisConfiguration {

}