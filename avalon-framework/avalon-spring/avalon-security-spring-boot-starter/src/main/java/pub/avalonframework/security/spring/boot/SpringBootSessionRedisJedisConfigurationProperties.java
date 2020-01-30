package pub.avalonframework.security.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pub.avalonframework.redis.core.yaml.config.YamlJedisConfiguration;
import pub.avalonframework.redis.core.yaml.config.YamlRedisConfiguration;

/**
 * Session redis jedis configuration properties.
 *
 * @author baichao
 */
@ConfigurationProperties(prefix = "spring.avalon.security.session.redis.jedis")
public class SpringBootSessionRedisJedisConfigurationProperties extends YamlJedisConfiguration {

}