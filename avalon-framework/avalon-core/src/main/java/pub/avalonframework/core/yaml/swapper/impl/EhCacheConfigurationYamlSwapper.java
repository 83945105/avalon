package pub.avalonframework.core.yaml.swapper.impl;

import pub.avalonframework.core.api.config.EhCacheConfiguration;
import pub.avalonframework.core.yaml.config.YamlEhCacheConfiguration;
import pub.avalonframework.core.yaml.swapper.YamlSwapper;

/**
 * @author baichao
 */
public final class EhCacheConfigurationYamlSwapper implements YamlSwapper<YamlEhCacheConfiguration, EhCacheConfiguration> {

    private final long DEFAULT_HEAP_SIZE = 100;

    private final long DEFAULT_OFFHEAP_SIZE = 128;

    private final long DEFAULT_TIME_TO_LIVE_MILLISECONDS = 1000 * 60;

    private final long DEFAULT_TIME_TO_IDLE_MILLISECONDS = 1000 * 10;

    @Override
    public YamlEhCacheConfiguration swap(EhCacheConfiguration data) {
        YamlEhCacheConfiguration configuration = new YamlEhCacheConfiguration();
        String alias = data.getAlias();
        if (alias == null || alias.length() == 0) {
            throw new EhCacheConfigurationException("The ehCache configuration alias is null.");
        }
        configuration.setAlias(alias);
        Long heapSize = data.getHeapSize();
        configuration.setHeapSize(heapSize == null ? DEFAULT_HEAP_SIZE : heapSize);
        Long offheapSize = data.getOffheapSize();
        configuration.setOffheapSize(offheapSize == null ? DEFAULT_OFFHEAP_SIZE : offheapSize);
        Long diskSize = data.getDiskSize();
        configuration.setDiskSize(diskSize);
        Long timeToLiveMilliseconds = data.getTimeToLiveMilliseconds();
        configuration.setTimeToLiveMilliseconds(timeToLiveMilliseconds == null ? DEFAULT_TIME_TO_LIVE_MILLISECONDS : timeToLiveMilliseconds);
        Long timeToIdleMilliseconds = data.getTimeToIdleMilliseconds();
        configuration.setTimeToIdleMilliseconds(timeToIdleMilliseconds == null ? DEFAULT_TIME_TO_IDLE_MILLISECONDS : timeToIdleMilliseconds);
        return configuration;
    }

    @Override
    public EhCacheConfiguration swap(YamlEhCacheConfiguration yamlConfiguration) {
        String alias = yamlConfiguration.getAlias();
        if (alias == null || alias.length() == 0) {
            throw new EhCacheConfigurationException("The ehCache configuration alias is null.");
        }
        EhCacheConfiguration configuration = new EhCacheConfiguration(alias);
        Long heapSize = yamlConfiguration.getHeapSize();
        configuration.setHeapSize(heapSize == null ? DEFAULT_HEAP_SIZE : heapSize);
        Long offheapSize = yamlConfiguration.getOffheapSize();
        configuration.setOffheapSize(offheapSize == null ? DEFAULT_OFFHEAP_SIZE : offheapSize);
        Long diskSize = yamlConfiguration.getDiskSize();
        configuration.setDiskSize(diskSize);
        Long timeToLiveMilliseconds = yamlConfiguration.getTimeToLiveMilliseconds();
        configuration.setTimeToLiveMilliseconds(timeToLiveMilliseconds == null ? DEFAULT_TIME_TO_LIVE_MILLISECONDS : timeToLiveMilliseconds);
        Long timeToIdleMilliseconds = yamlConfiguration.getTimeToIdleMilliseconds();
        configuration.setTimeToIdleMilliseconds(timeToIdleMilliseconds == null ? DEFAULT_TIME_TO_IDLE_MILLISECONDS : timeToIdleMilliseconds);
        return configuration;
    }
}