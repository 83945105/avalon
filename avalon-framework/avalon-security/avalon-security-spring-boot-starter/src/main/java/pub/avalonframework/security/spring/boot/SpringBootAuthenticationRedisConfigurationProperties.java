package pub.avalonframework.security.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pub.avalonframework.redis.core.yaml.config.YamlRedisConfiguration;

/**
 * Authentication redis configuration properties.
 *
 * @author baichao
 */
@ConfigurationProperties(prefix = "spring.avalon.security.authentication.redis")
public class SpringBootAuthenticationRedisConfigurationProperties extends YamlRedisConfiguration {

}