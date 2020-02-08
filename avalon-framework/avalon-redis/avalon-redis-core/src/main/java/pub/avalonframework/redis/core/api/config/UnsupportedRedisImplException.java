package pub.avalonframework.redis.core.api.config;

import pub.avalonframework.redis.core.RedisException;
import pub.avalonframework.redis.core.api.beans.RedisImpl;

/**
 * @author baichao
 */
public final class UnsupportedRedisImplException extends RedisException {

    private RedisImpl redisImpl;

    public UnsupportedRedisImplException(RedisImpl redisImpl) {
        super("Unsupported redis impl.");
        this.redisImpl = redisImpl;
    }

    public UnsupportedRedisImplException(RedisImpl redisImpl, Throwable cause) {
        super("Unsupported redis impl.", cause);
        this.redisImpl = redisImpl;
    }

    public RedisImpl getRedisImpl() {
        return redisImpl;
    }
}