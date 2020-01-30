package pub.avalonframework.security.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pub.avalonframework.redis.core.yaml.config.YamlJedisConfiguration;

/**
 * Authorization redis jedis configuration properties.
 *
 * @author baichao
 */
@ConfigurationProperties(prefix = "spring.avalon.security.authorization.redis.jedis")
public class SpringBootAuthorizationRedisJedisConfigurationProperties extends YamlJedisConfiguration {

}