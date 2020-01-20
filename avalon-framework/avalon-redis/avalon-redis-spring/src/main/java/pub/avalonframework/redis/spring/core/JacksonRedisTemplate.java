package pub.avalonframework.redis.spring.core;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import pub.avalonframework.redis.spring.serializer.GenericToStringSerializer;
import pub.avalonframework.redis.spring.serializer.Jackson2JsonRedisSerializer;

/**
 * Jackson redis template
 *
 * @author baichao
 */
public final class JacksonRedisTemplate<K, V> extends RedisTemplate<K, V, K, V> {

    public JacksonRedisTemplate(RedisConnectionFactory redisConnectionFactory, Class<K> keyType, Class<V> valueType) {
        super(redisConnectionFactory, new Jackson2JsonRedisSerializer<>(Object.class), new GenericToStringSerializer<>(keyType), new Jackson2JsonRedisSerializer<>(valueType), new GenericToStringSerializer<>(keyType), new Jackson2JsonRedisSerializer<>(valueType));
    }

    public JacksonRedisTemplate(RedisConnectionFactory redisConnectionFactory, TypeReference<K> keyTypeReference, TypeReference<V> valueTypeReference) {
        super(redisConnectionFactory, new Jackson2JsonRedisSerializer<>(Object.class), new Jackson2JsonRedisSerializer<>(keyTypeReference), new Jackson2JsonRedisSerializer<>(valueTypeReference), new Jackson2JsonRedisSerializer<>(keyTypeReference), new Jackson2JsonRedisSerializer<>(valueTypeReference));
    }
}