package pub.avalonframework.security.core.yaml.config;

import pub.avalonframework.core.yaml.config.YamlConfiguration;

/**
 * @author baichao
 */
public class YamlEhCacheConfiguration implements YamlConfiguration {

    private String cacheManagerConfigFile;

    public String getCacheManagerConfigFile() {
        return cacheManagerConfigFile;
    }

    public void setCacheManagerConfigFile(String cacheManagerConfigFile) {
        this.cacheManagerConfigFile = cacheManagerConfigFile;
    }
}