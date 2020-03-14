package pub.avalonframework.redis.spring.serializer;

import com.fasterxml.jackson.core.type.TypeReference;
import pub.avalonframework.core.beans.SerializationException;

/**
 * @author 白超
 */
public interface RedisSerializer<T> {

    byte[] serialize(T t) throws SerializationException;

    T deserialize(byte[] bytes) throws SerializationException;

    TypeReference<T> getTypeReference();
}