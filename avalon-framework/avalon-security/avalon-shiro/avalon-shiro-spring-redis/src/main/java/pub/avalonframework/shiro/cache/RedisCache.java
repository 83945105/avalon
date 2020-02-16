package pub.avalonframework.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import pub.avalonframework.redis.spring.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author baichao
 */
public class RedisCache<K, V> implements Cache<K, V> {

    private final static String REDIS_CACHE_PREFIX = "shiro:cache:";

    private RedisTemplate<K, V, Object, Object> redisTemplate;

    private String cachePrefix;

    private long expire;

    public RedisCache(RedisTemplate<K, V, Object, Object> redisTemplate, String name, long expire) {
        this.redisTemplate = redisTemplate;
        this.cachePrefix = REDIS_CACHE_PREFIX + name + ":";
        this.expire = expire;
    }

    @SuppressWarnings("unchecked")
    private K getKey(Object key) {
        return (K) (this.cachePrefix + key);
    }

    @Override
    public V get(K k) throws CacheException {
        return this.redisTemplate.get(getKey(k));
    }

    @Override
    @SuppressWarnings("unchecked")
    public V put(K k, V v) throws CacheException {
        return this.redisTemplate.execute(operations -> {
            if (operations == null) {
                return null;
            }
            K key = getKey(k);
            V value = operations.get(key);
            operations.set(key, v);
            if (this.expire > -1) {
                operations.expireKey(key, RedisCache.this.expire, TimeUnit.MILLISECONDS);
            }
            return value;
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public V remove(K k) throws CacheException {
        return this.redisTemplate.execute(operations -> {
            if (operations == null) {
                return null;
            }
            K key = getKey(k);
            V value = operations.get(key);
            operations.deleteKey(key);
            return value;
        });
    }

    @Override
    public void clear() throws CacheException {
        this.redisTemplate.deleteKeys(keys());
    }

    @Override
    public int size() {
        return keys().size();
    }

    @Override
    public Set<K> keys() {
        return this.redisTemplate.keys(getKey("*"));
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<V> values() {
        final Set<K> keys = keys();
        return (Collection<V>) this.redisTemplate.executePipelined(operations -> {
            if (operations == null) {
                return;
            }
            for (K key : keys) {
                operations.get(key);
            }
        });
    }
}