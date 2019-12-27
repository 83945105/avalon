package pub.avalonframework.security.core.yaml.swapper.impl;

import pub.avalonframework.security.core.api.config.cache.RedisCacheConfiguration;
import pub.avalonframework.security.core.yaml.config.cache.YamlRedisCacheConfiguration;
import pub.avalonframework.security.core.yaml.swapper.YamlSwapper;

/**
 * @author baichao
 */
public final class RedisCacheConfigurationYamlSwapper implements YamlSwapper<YamlRedisCacheConfiguration, RedisCacheConfiguration> {

    private final static String DEFAULT_HOST_NAME = "localhost";

    private final static int DEFAULT_PORT = 6379;

    private final static int DEFAULT_DATABASE = 0;

    @Override
    public YamlRedisCacheConfiguration swap(RedisCacheConfiguration data) {
        YamlRedisCacheConfiguration configuration = new YamlRedisCacheConfiguration();
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
    public RedisCacheConfiguration swap(YamlRedisCacheConfiguration yamlConfiguration) {
        yamlConfiguration = yamlConfiguration == null ? new YamlRedisCacheConfiguration() : yamlConfiguration;
        RedisCacheConfiguration configuration = new RedisCacheConfiguration();
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