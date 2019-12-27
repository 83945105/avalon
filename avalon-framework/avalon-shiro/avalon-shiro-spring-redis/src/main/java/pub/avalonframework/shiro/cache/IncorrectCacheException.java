package pub.avalonframework.shiro.cache;

import pub.avalonframework.core.beans.CacheException;

import java.lang.reflect.Method;

/**
 * @author baichao
 */
public final class IncorrectCacheException extends CacheException {

    private String cacheName;

    public IncorrectCacheException(String cacheName, Class<?> exceptionClass, Method exceptionMethod) {
        super(exceptionClass, exceptionMethod, "Incorrect cache: " + cacheName);
        this.cacheName = cacheName;
    }

    public IncorrectCacheException(String cacheName, Class<?> exceptionClass, Method exceptionMethod, Throwable cause) {
        super(exceptionClass, exceptionMethod, "Incorrect cache: " + cacheName, cause);
        this.cacheName = cacheName;
    }

    public String getCacheName() {
        return cacheName;
    }
}