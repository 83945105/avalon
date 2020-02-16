package pub.avalonframework.security.core.api.beans;

/**
 * Cache type.
 *
 * @author baichao
 */
public enum CacheType {
    /**
     * Use Ehcache cache.
     *
     * @see <a href="http://www.ehcache.org/">http://www.ehcache.org/</a>
     */
    EHCACHE,
    /**
     * Use Redis cache.
     *
     * @see <a href="https://redis.io/">https://redis.io/</a>
     */
    REDIS
}