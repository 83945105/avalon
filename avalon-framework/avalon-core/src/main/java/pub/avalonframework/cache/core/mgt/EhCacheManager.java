package pub.avalonframework.cache.core.mgt;

import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import pub.avalonframework.cache.core.Cache;
import pub.avalonframework.cache.core.EhCacheWrapper;
import pub.avalonframework.core.api.config.EhCacheConfiguration;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * EhCache manager.
 *
 * @author baichao
 */
public final class EhCacheManager {

    private final CacheManager cacheManager;

    public EhCacheManager() {
        this.cacheManager = init(null);
    }

    public EhCacheManager(String rootDirectory) {
        this.cacheManager = init(rootDirectory);
    }

    private CacheManager init(String rootDirectory) {
        try {
            if (rootDirectory == null) {
                return CacheManagerBuilder.newCacheManagerBuilder().build(true);
            }
            return CacheManagerBuilder.newCacheManagerBuilder().with(CacheManagerBuilder.persistence(rootDirectory)).build(true);
        } catch (Exception e) {
            throw new EhCacheManagerInitException(e.getMessage(), e);
        }
    }

    private <K, V> CacheConfiguration<K, V> buildCacheConfiguration(Class<K> key, Class<V> value, EhCacheConfiguration ehCacheConfiguration) {
        Long heapSize = ehCacheConfiguration.getHeapSize();
        Long offheapSize = ehCacheConfiguration.getOffheapSize();
        Long diskSize = ehCacheConfiguration.getDiskSize();
        Long timeToLiveMilliseconds = ehCacheConfiguration.getTimeToLiveMilliseconds();
        Long timeToIdleMilliseconds = ehCacheConfiguration.getTimeToIdleMilliseconds();
        ResourcePoolsBuilder resourcePoolsBuilder = ResourcePoolsBuilder.newResourcePoolsBuilder();
        if (heapSize != null) {
            //设置缓存堆容纳元素个数(JVM内存空间)超出个数后会存到offheap中
            resourcePoolsBuilder = resourcePoolsBuilder.heap(heapSize, EntryUnit.ENTRIES);
        }
        if (offheapSize != null) {
            //设置堆外储存大小(内存存储) 超出offheap的大小会淘汰规则被淘汰
            resourcePoolsBuilder = resourcePoolsBuilder.offheap(offheapSize, MemoryUnit.MB);
        }
        if (diskSize != null) {
            // 配置磁盘持久化储存(硬盘存储)用来持久化到磁盘,这里设置为false不启用
            resourcePoolsBuilder = resourcePoolsBuilder.disk(diskSize, MemoryUnit.MB, false);
        }
        CacheConfigurationBuilder<K, V> cacheConfigurationBuilder = CacheConfigurationBuilder.newCacheConfigurationBuilder(
                key, value,
                resourcePoolsBuilder
        );
        if (timeToLiveMilliseconds != null) {
            cacheConfigurationBuilder = cacheConfigurationBuilder.withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(
                    //设置缓存过期时间
                    Duration.of(timeToLiveMilliseconds, ChronoUnit.MILLIS))
            );
        }
        if (timeToIdleMilliseconds != null) {
            cacheConfigurationBuilder = cacheConfigurationBuilder.withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(
                    //设置被访问后过期时间(同时设置和TTL和TTI之后会被覆盖,这里TTI生效,之前版本xml配置后是两个配置了都会生效)
                    Duration.of(timeToIdleMilliseconds, ChronoUnit.MILLIS))
            );
        }
        return cacheConfigurationBuilder.build();
    }

    public <K, V> Cache<K, V> createCache(Class<K> keyType, Class<V> valueType, EhCacheConfiguration ehCacheConfiguration) {
        return new EhCacheWrapper<>(this.cacheManager.createCache(ehCacheConfiguration.getAlias(), buildCacheConfiguration(keyType, valueType, ehCacheConfiguration)));
    }

    public <K, V> Cache<K, V> getCache(String alias, Class<K> keyType, Class<V> valueType) {
        org.ehcache.Cache<K, V> cache = this.cacheManager.getCache(alias, keyType, valueType);
        return cache == null ? null : new EhCacheWrapper<>(cache);
    }

    public void removeCache(String alias) {
        this.cacheManager.removeCache(alias);
    }

    public void close() {
        this.cacheManager.close();
    }
}