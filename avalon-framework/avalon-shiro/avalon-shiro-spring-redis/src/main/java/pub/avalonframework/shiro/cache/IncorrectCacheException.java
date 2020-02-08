package pub.avalonframework.shiro.cache;

import pub.avalonframework.core.beans.CacheException;

/**
 * @author baichao
 */
public final class IncorrectCacheException extends CacheException {

    private String cacheName;

    public IncorrectCacheException(String cacheName) {
        super("Incorrect cache: " + cacheName);
        this.cacheName = cacheName;
    }

    public IncorrectCacheException(String cacheName, Throwable cause) {
        super("Incorrect cache: " + cacheName, cause);
        this.cacheName = cacheName;
    }

    public String getCacheName() {
        return cacheName;
    }
}