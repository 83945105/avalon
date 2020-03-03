package pub.avalonframework.cache.core;

import java.util.Map;
import java.util.Set;

/**
 * @author baichao
 */
public final class EhCacheWrapper<K, V> implements Cache<K, V> {

    private org.ehcache.Cache<K, V> cache;

    public EhCacheWrapper(org.ehcache.Cache<K, V> cache) {
        this.cache = cache;
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
    }

    @Override
    public Map<K, V> getAll(Set<? extends K> keys) {
        return cache.getAll(keys);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> entries) {
        cache.putAll(entries);
    }

    @Override
    public void removeAll(Set<? extends K> keys) {
        cache.removeAll(keys);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public V putIfAbsent(K key, V value) {
        return cache.putIfAbsent(key, value);
    }

    @Override
    public boolean remove(K key, V value) {
        return cache.remove(key, value);
    }

    @Override
    public V replace(K key, V value) {
        return cache.replace(key, value);
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return cache.replace(key, oldValue, newValue);
    }
}