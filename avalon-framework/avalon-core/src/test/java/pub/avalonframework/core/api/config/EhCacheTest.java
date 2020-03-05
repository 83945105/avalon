package pub.avalonframework.core.api.config;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * EhCache测试
 *
 * @author 白超
 */
public class EhCacheTest {

    private final static String ROOT_PATH = "H:/ehcacheData";

    /**
     * 测试只设置最大生存时间
     * 测试方式：设置最大生存时间1000毫秒
     * 线程第一次睡眠500毫秒 -> 数据存在
     * 线程第二次睡眠500毫秒 -> 数据不存在
     * 结论：设置了最大生存时间后, 超过该时间数据删除
     */
    @Test
    void TestOnlyTimeToLive() throws InterruptedException {
        CacheConfiguration<String, String> cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(
                String.class, String.class,
                ResourcePoolsBuilder
                        .newResourcePoolsBuilder()
                        .heap(1000, EntryUnit.ENTRIES)
                        .offheap(100, MemoryUnit.MB)
                        .disk(500, MemoryUnit.MB, false)
        ).withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(
                Duration.of(1000, ChronoUnit.MILLIS))
        ).build();
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .with(CacheManagerBuilder.persistence(ROOT_PATH))
                .withCache("cache", cacheConfiguration)
                .build(true);
        Cache<String, String> cache = cacheManager.getCache("cache", String.class, String.class);
        cache.put("key", "value");
        Thread.sleep(500);
        Assertions.assertNotNull(cache.get("key"));
        Thread.sleep(500);
        Assertions.assertNull(cache.get("key"));
        cacheManager.close();
    }

    /**
     * 测试只设置最大空闲时间
     * 测试方式：设置最大空闲时间1000毫秒
     * 线程第一次睡眠500毫秒 -> 数据存在
     * 线程第二次睡眠500毫秒 -> 数据存在
     * 线程第三次睡眠500毫秒 -> 数据存在
     * 线程第四次睡眠1000毫秒 -> 数据不存在
     * 结论：设置了数据最大空闲时间后, 访问数据间隔时间超过该时间, 数据删除
     */
    @Test
    void TestOnlyTimeToIdle() throws InterruptedException {
        CacheConfiguration<String, String> cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(
                String.class, String.class,
                ResourcePoolsBuilder
                        .newResourcePoolsBuilder()
                        .heap(1000, EntryUnit.ENTRIES)
                        .offheap(100, MemoryUnit.MB)
                        .disk(500, MemoryUnit.MB, false)
        ).withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(
                Duration.of(1000, ChronoUnit.MILLIS))
        ).build();
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .with(CacheManagerBuilder.persistence(ROOT_PATH))
                .withCache("cache", cacheConfiguration)
                .build(true);
        Cache<String, String> cache = cacheManager.getCache("cache", String.class, String.class);
        cache.put("key", "value");
        Thread.sleep(500);
        Assertions.assertNotNull(cache.get("key"));
        Thread.sleep(500);
        Assertions.assertNotNull(cache.get("key"));
        Thread.sleep(500);
        Assertions.assertNotNull(cache.get("key"));
        Thread.sleep(1000);
        Assertions.assertNull(cache.get("key"));
        cacheManager.close();
    }

    /**
     * 测试设置最大生存时间大于最大空闲时间
     * 测试方式：设置最大生存时间2000毫秒, 设置最大空闲时间1000毫秒
     * 线程第一次睡眠1000毫秒 -> 数据不存在
     * 重新设置数据
     * 线程第一次睡眠500毫秒 -> 数据存在
     * 线程第二次睡眠500毫秒 -> 数据存在
     * 线程第三次睡眠500毫秒 -> 数据存在
     * 线程第四次睡眠500毫秒 -> 数据存在
     * 线程第五次睡眠500毫秒 -> 数据存在, 此时数据累计存在时间已经大于设置的最大生存时间, 依然存在
     * 线程第六次睡眠1000毫秒 -> 数据不存在
     * 结论：设置最大生存时间大于最大空闲时间, 如果超过最大空闲时间未访问数据, 数据删除
     * 如果在最大空闲时间间隔内不停访问数据, 数据不会被删除, 哪怕超过了最大生存时间
     * 一旦访问数据时间间隔大于最大空闲时间, 数据删除
     * 最大生存时间在这种情况下貌似不起任何作用
     */
    @Test
    void TestTimeToLiveGreaterThanTimeToIdle() throws InterruptedException {
        CacheConfiguration<String, String> cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(
                String.class, String.class,
                ResourcePoolsBuilder
                        .newResourcePoolsBuilder()
                        .heap(1000, EntryUnit.ENTRIES)
                        .offheap(100, MemoryUnit.MB)
                        .disk(500, MemoryUnit.MB, false)
        ).withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(
                Duration.of(2000, ChronoUnit.MILLIS))
        ).withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(
                Duration.of(1000, ChronoUnit.MILLIS))
        ).build();
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .with(CacheManagerBuilder.persistence(ROOT_PATH))
                .withCache("cache", cacheConfiguration)
                .build(true);
        Cache<String, String> cache = cacheManager.getCache("cache", String.class, String.class);
        cache.put("key", "value");
        Thread.sleep(1000);
        Assertions.assertNull(cache.get("key"));
        cache.put("key", "value");
        Thread.sleep(500);
        Assertions.assertNotNull(cache.get("key"));
        Thread.sleep(500);
        Assertions.assertNotNull(cache.get("key"));
        Thread.sleep(500);
        Assertions.assertNotNull(cache.get("key"));
        Thread.sleep(500);
        Assertions.assertNotNull(cache.get("key"));
        Thread.sleep(500);
        Assertions.assertNotNull(cache.get("key"));
        Thread.sleep(1000);
        Assertions.assertNull(cache.get("key"));
        cacheManager.close();
    }

    /**
     * 测试设置最大生存时间小于最大空闲时间
     * 测试方式：设置最大生存时间1000毫秒, 设置最大空闲时间2000毫秒
     * 线程第一次睡眠1500毫秒 -> 数据存在, 数据存在时间已经超过了最大生存时间
     * 线程第二次睡眠1500毫秒 -> 数据存在
     * 线程第三次睡眠2000毫秒 -> 数据不存在
     * 结论：设置最大生存时间小于最大空闲时间, 如果超过最大生存时间未访问数据, 但是并未超过最大空闲时间, 数据还在
     * 一旦访问数据时间间隔大于最大空闲时间, 数据删除
     * 最大生存时间在这种情况下貌似不起任何作用
     */
    @Test
    void TestTimeToLiveLessThanTimeToIdle() throws InterruptedException {
        CacheConfiguration<String, String> cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(
                String.class, String.class,
                ResourcePoolsBuilder
                        .newResourcePoolsBuilder()
                        .heap(1000, EntryUnit.ENTRIES)
                        .offheap(100, MemoryUnit.MB)
                        .disk(500, MemoryUnit.MB, false)
        ).withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(
                Duration.of(1000, ChronoUnit.MILLIS))
        ).withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(
                Duration.of(2000, ChronoUnit.MILLIS))
        ).build();
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .with(CacheManagerBuilder.persistence(ROOT_PATH))
                .withCache("cache", cacheConfiguration)
                .build(true);
        Cache<String, String> cache = cacheManager.getCache("cache", String.class, String.class);
        cache.put("key", "value");
        Thread.sleep(1500);
        Assertions.assertNotNull(cache.get("key"));
        Thread.sleep(1500);
        Assertions.assertNotNull(cache.get("key"));
        Thread.sleep(2000);
        Assertions.assertNull(cache.get("key"));
        cacheManager.close();
    }

//    @Test
    void Test() {
        CacheConfiguration<String, String> cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(
                String.class, String.class,
                ResourcePoolsBuilder
                        .newResourcePoolsBuilder()
                        .heap(1000, EntryUnit.ENTRIES)
                        .offheap(100, MemoryUnit.MB)
                        .disk(500, MemoryUnit.MB, false)
        ).withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(
                Duration.of(1000, ChronoUnit.MILLIS))
        ).withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(
                Duration.of(2000, ChronoUnit.MILLIS))
        ).build();
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .with(CacheManagerBuilder.persistence(ROOT_PATH))
                .withCache("cache", cacheConfiguration)
                .build(true);
        Cache<String, String> cache = cacheManager.getCache("cache", String.class, String.class);
        CacheConfiguration<String, String> cacheConfiguration1 = CacheConfigurationBuilder.newCacheConfigurationBuilder(
                String.class, String.class,
                ResourcePoolsBuilder
                        .newResourcePoolsBuilder()
                        .heap(1000, EntryUnit.ENTRIES)
                        .offheap(100, MemoryUnit.MB)
                        .disk(500, MemoryUnit.MB, false)
        ).withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(
                Duration.of(1000, ChronoUnit.MILLIS))
        ).withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(
                Duration.of(2000, ChronoUnit.MILLIS))
        ).build();
        CacheManager cacheManager1 = CacheManagerBuilder.newCacheManagerBuilder()
                .with(CacheManagerBuilder.persistence(ROOT_PATH))
                .withCache("cache", cacheConfiguration1)
                .build(true);
        Cache<String, String> cache1 = cacheManager1.getCache("cache", String.class, String.class);
    }

    /**
     * Cache 创建示例
     *
     * @param args
     */
    public static void main(String[] args) {
        CacheConfiguration<String, String> cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(
                String.class, String.class,
                ResourcePoolsBuilder
                        .newResourcePoolsBuilder()
                        //设置缓存堆容纳元素个数(JVM内存空间)超出个数后会存到offheap中
                        .heap(1000, EntryUnit.ENTRIES)
                        //设置堆外储存大小(内存存储) 超出offheap的大小会淘汰规则被淘汰
                        .offheap(100, MemoryUnit.MB)
                        // 配置磁盘持久化储存(硬盘存储)用来持久化到磁盘,这里设置为false不启用
                        .disk(500, MemoryUnit.MB, false)
        ).withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(
                //设置缓存过期时间
                Duration.of(30000, ChronoUnit.MILLIS))
        ).withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(
                //设置被访问后过期时间(同时设置和TTL和TTI之后会被覆盖,这里TTI生效,之前版本xml配置后是两个配置了都会生效)
                Duration.of(3000, ChronoUnit.MILLIS))
        ).build();
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                // 硬盘持久化地址
                .with(CacheManagerBuilder.persistence(ROOT_PATH))
                // 设置一个默认缓存配置
                .withCache("cache", cacheConfiguration)
                //创建之后立即初始化
                .build(true);
        Cache<String, String> cache = cacheManager.getCache("cache", String.class, String.class);
        // 用完记得关闭管理器, 释放硬盘持久化地址文件
        cacheManager.close();
    }
}