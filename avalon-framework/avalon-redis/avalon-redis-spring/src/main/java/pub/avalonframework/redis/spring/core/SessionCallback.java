package pub.avalonframework.redis.spring.core;

import org.springframework.dao.DataAccessException;

/**
 * @author baichao
 */
public interface SessionCallback<T, K, V, HK, HV> {

    T execute(RedisOperations<K, V, HK, HV> operations) throws DataAccessException;
}