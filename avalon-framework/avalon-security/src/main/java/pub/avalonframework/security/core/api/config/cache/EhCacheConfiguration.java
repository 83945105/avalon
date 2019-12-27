package pub.avalonframework.security.core.api.config.cache;

/**
 * EhCache configuration.
 *
 * @author baichao
 */
public class EhCacheConfiguration {

    /**
     * The EhCache config xml file path.
     */
    private String cacheManagerConfigFile;

    public String getCacheManagerConfigFile() {
        return cacheManagerConfigFile;
    }

    public void setCacheManagerConfigFile(String cacheManagerConfigFile) {
        this.cacheManagerConfigFile = cacheManagerConfigFile;
    }
}