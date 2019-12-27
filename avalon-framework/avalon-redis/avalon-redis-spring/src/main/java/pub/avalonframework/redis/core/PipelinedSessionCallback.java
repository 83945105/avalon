package pub.avalonframework.redis.core;

import org.springframework.dao.DataAccessException;

/**
 * @author baichao
 */
public interface PipelinedSessionCallback<K, V, HK, HV> {

    void execute(RedisOperations<K, V, HK, HV> operations) throws DataAccessException;
}