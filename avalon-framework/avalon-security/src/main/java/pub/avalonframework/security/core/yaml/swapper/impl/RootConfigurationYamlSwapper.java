package pub.avalonframework.security.core.yaml.swapper.impl;

import pub.avalonframework.core.yaml.swapper.YamlSwapper;
import pub.avalonframework.security.core.api.beans.CacheType;
import pub.avalonframework.security.core.api.config.SecurityConfiguration;
import pub.avalonframework.security.core.yaml.config.YamlSecurityConfiguration;

/**
 * @author baichao
 */
public final class RootConfigurationYamlSwapper implements YamlSwapper<YamlSecurityConfiguration, SecurityConfiguration> {

    private final static Boolean DEFAULT_ENABLED = false;

    private final static CacheType DEFAULT_CACHE_TYPE = CacheType.EHCACHE;

    @Override
    public YamlSecurityConfiguration swap(SecurityConfiguration data) {
        YamlSecurityConfiguration configuration = new YamlSecurityConfiguration();
        Boolean enabled = data.getEnabled();
        configuration.setEnabled(enabled == null ? DEFAULT_ENABLED : enabled);
        CacheType cacheType = data.getCacheType();
        configuration.setCacheType(cacheType == null ? DEFAULT_CACHE_TYPE : cacheType);
        return configuration;
    }

    @Override
    public SecurityConfiguration swap(YamlSecurityConfiguration yamlConfiguration) {
        SecurityConfiguration configuration = new SecurityConfiguration();
        Boolean enabled = yamlConfiguration.getEnabled();
        configuration.setEnabled(enabled == null ? DEFAULT_ENABLED : enabled);
        CacheType cacheType = yamlConfiguration.getCacheType();
        configuration.setCacheType(cacheType == null ? DEFAULT_CACHE_TYPE : cacheType);
        return configuration;
    }
}