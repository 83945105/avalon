package pub.avalonframework.shiro.cache;

import org.junit.jupiter.api.*;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import pub.avalonframework.redis.core.RedisTemplate;
import pub.avalonframework.redis.serializer.GenericToStringSerializer;
import pub.avalonframework.redis.serializer.Jackson2JsonRedisSerializer;
import pub.avalonframework.shiro.beans.Session;

import java.util.Collection;
import java.util.Set;

/**
 * Created by 白超 on 2019/11/18.
 */
public class RedisCacheTest {

    private static RedisTemplate<String, Session, Object, Object> redisTemplate;

    @BeforeAll
    static void beforeAll() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName("localhost");
        configuration.setPort(6379);
        configuration.setDatabase(0);
        redisTemplate = new RedisTemplate<>
                (
                        new JedisConnectionFactory(configuration),
                        new Jackson2JsonRedisSerializer<>(Object.class),
                        new GenericToStringSerializer<>(String.class),
                        new Jackson2JsonRedisSerializer<>(Session.class),
                        new Jackson2JsonRedisSerializer<>(Object.class),
                        new Jackson2JsonRedisSerializer<>(Object.class)
                );
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("init delete keys start.");
        redisTemplate.execute(operations -> {
            if (operations == null) {
                return null;
            }
            Set<String> keys = operations.keys("*");
            if (keys != null && keys.size() > 0) {
                operations.deleteKeys(keys);
            }
            return null;
        });
        System.out.println("init delete keys end.");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("destroy delete keys start.");
        redisTemplate.execute(operations -> {
            if (operations == null) {
                return null;
            }
            Set<String> keys = operations.keys("*");
            if (keys != null && keys.size() > 0) {
                operations.deleteKeys(keys);
            }
            return null;
        });
        System.out.println("destroy delete keys end.");
    }

    @Test
    void test() {
        RedisCache<String, Session> cache = new RedisCache<>(redisTemplate, "shiro:session:", 1000 * 60 * 60 * 24);
        Assertions.assertEquals(0, cache.size());
        Session session = new Session();
        session.setId("1");
        session.setValue("A-1");
        session = cache.put(session.getId(), session);
        Assertions.assertNull(session);
        Assertions.assertEquals(1, cache.size());
        Assertions.assertEquals("1", cache.get("1").getId());
        Assertions.assertEquals("A-1", cache.get("1").getValue());
        session = new Session();
        session.setId("1");
        session.setValue("A-2");
        session = cache.put(session.getId(), session);
        Assertions.assertEquals("A-1", session.getValue());
        Assertions.assertEquals(1, cache.size());
        Assertions.assertEquals("1", cache.get("1").getId());
        Assertions.assertEquals("A-2", cache.get("1").getValue());
        session = new Session();
        session.setId("2");
        session.setValue("B-1");
        session = cache.put(session.getId(), session);
        Assertions.assertNull(session);
        Assertions.assertEquals(2, cache.size());
        Assertions.assertEquals("2", cache.get("2").getId());
        Assertions.assertEquals("B-1", cache.get("2").getValue());
        Collection<Session> sessions = cache.values();
        Assertions.assertEquals(2, sessions.size());
        session = cache.remove("1");
        Assertions.assertEquals(1, cache.size());
        Assertions.assertEquals("1", session.getId());
        Assertions.assertEquals("A-2", session.getValue());
        cache.clear();
        Assertions.assertEquals(0, cache.size());
    }
}