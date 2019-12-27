package pub.avalonframework.security.core.yaml.swapper.impl;

import pub.avalonframework.security.core.api.config.cache.RedisSessionConfiguration;
import pub.avalonframework.security.core.yaml.config.cache.YamlRedisSessionConfiguration;
import pub.avalonframework.security.core.yaml.swapper.YamlSwapper;

/**
 * @author baichao
 */
public final class RedisSessionConfigurationYamlSwapper implements YamlSwapper<YamlRedisSessionConfiguration, RedisSessionConfiguration> {

    private final static String DEFAULT_HOST_NAME = "localhost";

    private final static int DEFAULT_PORT = 6379;

    private final static int DEFAULT_DATABASE = 0;

    @Override
    public YamlRedisSessionConfiguration swap(RedisSessionConfiguration data) {
        YamlRedisSessionConfiguration configuration = new YamlRedisSessionConfiguration();
        String hostName = data.getHostName();
        configuration.setHostName(hostName == null ? DEFAULT_HOST_NAME : hostName);
        Integer port = data.getPort();
        configuration.setPort(port == null ? DEFAULT_PORT : port);
        Integer database = data.getDatabase();
        configuration.setDatabase(database == null ? DEFAULT_DATABASE : database);
        String password = data.getPassword();
        configuration.setPassword(password);
        return configuration;
    }

    @Override
    public RedisSessionConfiguration swap(YamlRedisSessionConfiguration yamlConfiguration) {
        yamlConfiguration = yamlConfiguration == null ? new YamlRedisSessionConfiguration() : yamlConfiguration;
        RedisSessionConfiguration configuration = new RedisSessionConfiguration();
        String hostName = yamlConfiguration.getHostName();
        configuration.setHostName(hostName == null ? DEFAULT_HOST_NAME : hostName);
        Integer port = yamlConfiguration.getPort();
        configuration.setPort(port == null ? DEFAULT_PORT : port);
        Integer database = yamlConfiguration.getDatabase();
        configuration.setDatabase(database == null ? DEFAULT_DATABASE : database);
        String password = yamlConfiguration.getPassword();
        configuration.setPassword(password);
        return configuration;
    }
}