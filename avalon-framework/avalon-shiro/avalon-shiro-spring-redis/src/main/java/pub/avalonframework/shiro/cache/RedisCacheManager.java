package pub.avalonframework.shiro.cache;

import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import pub.avalonframework.redis.core.RedisTemplate;

/**
 * @author baichao
 */
public class RedisCacheManager extends AbstractCacheManager {

    private String authenticationCacheName;

    private RedisTemplate<String, SimpleAuthenticationInfo, Object, Object> authenticationInfoRedisTemplate;

    private String authorizationCacheName;

    private RedisTemplate<String, SimpleAuthorizationInfo, Object, Object> authorizationRedisTemplate;

    private long expire;

    public RedisCacheManager(
            String authenticationCacheName,
            RedisTemplate<String, SimpleAuthenticationInfo, Object, Object> authenticationInfoRedisTemplate,
            String authorizationCacheName,
            RedisTemplate<String, SimpleAuthorizationInfo, Object, Object> authorizationRedisTemplate,
            long expire) {
        this.authenticationCacheName = authenticationCacheName;
        this.authenticationInfoRedisTemplate = authenticationInfoRedisTemplate;
        this.authorizationCacheName = authorizationCacheName;
        this.authorizationRedisTemplate = authorizationRedisTemplate;
        this.expire = expire;
    }

    @Override
    protected Cache createCache(String name) throws CacheException {
        if (name.equals(authenticationCacheName)) {
            return new RedisCache<>(authenticationInfoRedisTemplate, name, expire);
        }
        if (name.equals(authorizationCacheName)) {
            return new RedisCache<>(authorizationRedisTemplate, name, expire);
        }
        throw new IncorrectCacheException(name, RedisCacheManager.class, null);
    }
}