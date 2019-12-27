package pub.avalonframework.redis.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import pub.avalonframework.redis.beans.IllustratedHandbook;
import pub.avalonframework.redis.beans.Pikachu;
import pub.avalonframework.redis.serializer.GenericToStringSerializer;
import pub.avalonframework.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * Created by 白超 on 2019/11/27.
 */
public class RedisTemplateTest {

    private static RedisConnectionFactory redisConnectionFactory;

    @BeforeAll
    static void beforeAll() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("localhost");
        redisStandaloneConfiguration.setPort(6379);
        redisStandaloneConfiguration.setDatabase(0);
        redisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Test
    void TestRedisTemplate() {
        RedisTemplate<String, Object, String, Object> redisTemplate = new RedisTemplate<>
                (
                        redisConnectionFactory,
                        new Jackson2JsonRedisSerializer<>(Object.class),
                        new GenericToStringSerializer<>(String.class),
                        new Jackson2JsonRedisSerializer<>(Object.class),
                        new GenericToStringSerializer<>(String.class),
                        new Jackson2JsonRedisSerializer<>(Object.class)
                );
        IllustratedHandbook illustratedHandbook = new IllustratedHandbook();
        illustratedHandbook.addPokemon(new Pikachu());
        redisTemplate.set("全国图鉴", illustratedHandbook);
        illustratedHandbook = redisTemplate.get("全国图鉴", IllustratedHandbook.class);
        Assertions.assertTrue(illustratedHandbook.getPokemons().get(0) instanceof Pikachu);
    }
}
