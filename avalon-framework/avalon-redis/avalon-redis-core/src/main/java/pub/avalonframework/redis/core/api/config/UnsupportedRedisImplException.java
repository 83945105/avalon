package pub.avalonframework.redis.core.api.config;

import pub.avalonframework.redis.core.RedisException;
import pub.avalonframework.redis.core.api.beans.RedisImpl;

import java.lang.reflect.Method;

/**
 * @author baichao
 */
public final class UnsupportedRedisImplException extends RedisException {

    private RedisImpl redisImpl;

    public UnsupportedRedisImplException(RedisImpl redisImpl, Class<?> exceptionClass, Method exceptionMethod) {
        super(exceptionClass, exceptionMethod, "Unsupported redis impl.");
        this.redisImpl = redisImpl;
    }

    public UnsupportedRedisImplException(RedisImpl redisImpl, Class<?> exceptionClass, Method exceptionMethod, Throwable cause) {
        super(exceptionClass, exceptionMethod, "Unsupported redis impl.", cause);
        this.redisImpl = redisImpl;
    }

    public RedisImpl getRedisImpl() {
        return redisImpl;
    }
}