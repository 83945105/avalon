package pub.avalonframework.security.spring.boot.cache;

import org.springframework.boot.context.properties.ConfigurationProperties;
import pub.avalonframework.security.core.yaml.config.cache.YamlRedisSessionConfiguration;

/**
 * @author baichao
 */
@ConfigurationProperties(prefix = "spring.avalon.security.redis.session")
public class SpringBootRedisSessionConfigurationProperties extends YamlRedisSessionConfiguration {

}