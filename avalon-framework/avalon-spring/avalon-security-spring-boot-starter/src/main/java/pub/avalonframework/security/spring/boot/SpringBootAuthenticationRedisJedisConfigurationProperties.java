package pub.avalonframework.security.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pub.avalonframework.redis.core.yaml.config.YamlJedisConfiguration;

/**
 * Authentication redis jedis configuration properties.
 *
 * @author baichao
 */
@ConfigurationProperties(prefix = "spring.avalon.security.authentication.redis.jedis")
public class SpringBootAuthenticationRedisJedisConfigurationProperties extends YamlJedisConfiguration {

}