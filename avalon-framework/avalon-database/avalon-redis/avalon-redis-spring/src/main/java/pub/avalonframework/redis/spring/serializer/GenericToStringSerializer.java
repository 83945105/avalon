package pub.avalonframework.redis.spring.serializer;

import com.fasterxml.jackson.core.type.TypeReference;
import pub.avalonframework.core.beans.SerializationException;

import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author baichao
 */
public class GenericToStringSerializer<T> implements RedisSerializer<T> {

    private final org.springframework.data.redis.serializer.GenericToStringSerializer<T> genericToStringSerializer;

    private final Class<T> clazz;

    private final TypeReference<T> typeReference;

    public GenericToStringSerializer(final Class<T> type) {
        this(type, StandardCharsets.UTF_8);
    }

    public GenericToStringSerializer(final Class<T> type, final Charset charset) {
        this.clazz = type;
        this.typeReference = new TypeReference<T>() {
            @Override
            public Type getType() {
                return GenericToStringSerializer.this.clazz;
            }
        };
        this.genericToStringSerializer = new org.springframework.data.redis.serializer.GenericToStringSerializer<>(this.clazz, charset);
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        return this.genericToStringSerializer.serialize(t);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        return this.genericToStringSerializer.deserialize(bytes);
    }

    @Override
    public TypeReference<T> getTypeReference() {
        return this.typeReference;
    }
}