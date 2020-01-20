package pub.avalonframework.redis.spring.serializer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.type.TypeFactory;
import pub.avalonframework.core.beans.SerializationException;

import java.lang.reflect.Type;

/**
 * @author baichao
 */
public class Jackson2JsonRedisSerializer<T> implements RedisSerializer<T> {

    private final org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer<T> jackson2JsonRedisSerializer;

    private final TypeReference<T> typeReference;

    public Jackson2JsonRedisSerializer(final Class<T> type) {
        this.typeReference = new TypeReference<T>() {
            @Override
            public Type getType() {
                return type;
            }
        };
        this.jackson2JsonRedisSerializer = new org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer<>(TypeFactory.defaultInstance().constructType(this.typeReference));
    }

    public Jackson2JsonRedisSerializer(final TypeReference<T> typeReference) {
        this.typeReference = typeReference;
        this.jackson2JsonRedisSerializer = new org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer<>(TypeFactory.defaultInstance().constructType(this.typeReference));
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        return this.jackson2JsonRedisSerializer.serialize(t);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        return this.jackson2JsonRedisSerializer.deserialize(bytes);
    }

    @Override
    public TypeReference<T> getTypeReference() {
        return this.typeReference;
    }
}