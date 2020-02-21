package pub.avalonframework.redis.spring.serializer;

import com.fasterxml.jackson.core.type.TypeReference;
import pub.avalonframework.core.beans.SerializationException;

/**
 * @author baichao
 */
public class StringRedisSerializer implements RedisSerializer<String> {

    private final org.springframework.data.redis.serializer.StringRedisSerializer stringRedisSerializer;

    private final TypeReference<String> typeReference;

    public StringRedisSerializer() {
        this.stringRedisSerializer = new org.springframework.data.redis.serializer.StringRedisSerializer();
        this.typeReference = new TypeReference<String>() {
        };
    }

    @Override
    public byte[] serialize(String s) throws SerializationException {
        return this.stringRedisSerializer.serialize(s);
    }

    @Override
    public String deserialize(byte[] bytes) throws SerializationException {
        return this.stringRedisSerializer.deserialize(bytes);
    }

    @Override
    public TypeReference<String> getTypeReference() {
        return this.typeReference;
    }
}