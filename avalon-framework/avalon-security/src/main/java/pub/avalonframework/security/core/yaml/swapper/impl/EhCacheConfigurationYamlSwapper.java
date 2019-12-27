package pub.avalonframework.security.core.yaml.swapper.impl;

import pub.avalonframework.security.core.api.config.cache.EhCacheConfiguration;
import pub.avalonframework.security.core.yaml.config.cache.YamlEhCacheConfiguration;
import pub.avalonframework.security.core.yaml.swapper.YamlSwapper;

/**
 * @author baichao
 */
public final class EhCacheConfigurationYamlSwapper implements YamlSwapper<YamlEhCacheConfiguration, EhCacheConfiguration> {

//    private final static String DEFAULT_CACHE_MANAGER_CONFIG_FILE = "classpath:ehcache.xml";
    private final static String DEFAULT_CACHE_MANAGER_CONFIG_FILE = null;

    @Override
    public YamlEhCacheConfiguration swap(EhCacheConfiguration data) {
        YamlEhCacheConfiguration configuration = new YamlEhCacheConfiguration();
        String cacheManagerConfigFile = data.getCacheManagerConfigFile();
        configuration.setCacheManagerConfigFile(cacheManagerConfigFile == null ? DEFAULT_CACHE_MANAGER_CONFIG_FILE : cacheManagerConfigFile);
        return configuration;
    }

    @Override
    public EhCacheConfiguration swap(YamlEhCacheConfiguration yamlConfiguration) {
        EhCacheConfiguration configuration = new EhCacheConfiguration();
        String cacheManagerConfigFile = yamlConfiguration.getCacheManagerConfigFile();
        configuration.setCacheManagerConfigFile(cacheManagerConfigFile == null ? DEFAULT_CACHE_MANAGER_CONFIG_FILE : cacheManagerConfigFile);
        return configuration;
    }
}