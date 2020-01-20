package pub.avalonframework.redis.spring.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.lang.Nullable;
import pub.avalonframework.core.beans.OperationFailureException;
import pub.avalonframework.redis.spring.serializer.RedisSerializer;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author baichao
 */
public class RedisTemplate<K, V, HK, HV> implements RedisOperations<K, V, HK, HV> {

    private org.springframework.data.redis.core.RedisTemplate<K, V> redisTemplate;

    private final RedisConnectionFactory redisConnectionFactory;

    private final RedisSerializer<Object> defaultSerializer;

    private final RedisSerializer<K> keySerializer;

    private final RedisSerializer<V> valueSerializer;

    private final RedisSerializer<HK> hashKeySerializer;

    private final RedisSerializer<HV> hashValueSerializer;

    private final TypeReference<K> keyTypeReference;

    private final TypeReference<V> valueTypeReference;

    private final TypeReference<HK> hashKeyTypeReference;

    private final TypeReference<HV> hashValueTypeReference;

    private final static long NOT_EXPIRE = -1;

    private long defaultExpire = NOT_EXPIRE;

    private ObjectMapper objectMapper = new ObjectMapper();

    public RedisTemplate(RedisConnectionFactory redisConnectionFactory, RedisSerializer<Object> defaultSerializer, RedisSerializer<K> keySerializer, RedisSerializer<V> valueSerializer, RedisSerializer<HK> hashKeySerializer, RedisSerializer<HV> hashValueSerializer) {
        this.redisConnectionFactory = redisConnectionFactory;
        this.defaultSerializer = defaultSerializer;
        this.keySerializer = keySerializer;
        this.valueSerializer = valueSerializer;
        this.hashKeySerializer = hashKeySerializer;
        this.hashValueSerializer = hashValueSerializer;
        this.keyTypeReference = this.keySerializer.getTypeReference();
        this.valueTypeReference = this.valueSerializer.getTypeReference();
        this.hashKeyTypeReference = this.hashKeySerializer.getTypeReference();
        this.hashValueTypeReference = this.hashValueSerializer.getTypeReference();
        this.initRedisTemplate();
    }

    private void initRedisTemplate() {
        org.springframework.data.redis.core.RedisTemplate<K, V> redisTemplate = new org.springframework.data.redis.core.RedisTemplate<>();
        redisTemplate.setConnectionFactory(this.redisConnectionFactory);
        redisTemplate.setDefaultSerializer(new org.springframework.data.redis.serializer.RedisSerializer<Object>() {
            @Override
            public byte[] serialize(Object obj) throws SerializationException {
                return RedisTemplate.this.defaultSerializer.serialize(obj);
            }

            @Override
            public Object deserialize(byte[] bytes) throws SerializationException {
                return RedisTemplate.this.defaultSerializer.deserialize(bytes);
            }
        });
        redisTemplate.setKeySerializer(new org.springframework.data.redis.serializer.RedisSerializer<K>() {
            @Override
            public byte[] serialize(K key) throws SerializationException {
                return RedisTemplate.this.keySerializer.serialize(key);
            }

            @Override
            public K deserialize(byte[] bytes) throws SerializationException {
                return RedisTemplate.this.keySerializer.deserialize(bytes);
            }
        });
        redisTemplate.setValueSerializer(new org.springframework.data.redis.serializer.RedisSerializer<V>() {
            @Override
            public byte[] serialize(V value) throws SerializationException {
                return RedisTemplate.this.valueSerializer.serialize(value);
            }

            @Override
            public V deserialize(byte[] bytes) throws SerializationException {
                return RedisTemplate.this.valueSerializer.deserialize(bytes);
            }
        });
        redisTemplate.setHashKeySerializer(new org.springframework.data.redis.serializer.RedisSerializer<HK>() {
            @Override
            public byte[] serialize(HK hashKey) throws SerializationException {
                return RedisTemplate.this.hashKeySerializer.serialize(hashKey);
            }

            @Override
            public HK deserialize(byte[] bytes) throws SerializationException {
                return RedisTemplate.this.hashKeySerializer.deserialize(bytes);
            }
        });
        redisTemplate.setHashValueSerializer(new org.springframework.data.redis.serializer.RedisSerializer<HV>() {
            @Override
            public byte[] serialize(HV hashValue) throws SerializationException {
                return RedisTemplate.this.hashValueSerializer.serialize(hashValue);
            }

            @Override
            public HV deserialize(byte[] bytes) throws SerializationException {
                return RedisTemplate.this.hashValueSerializer.deserialize(bytes);
            }
        });
        redisTemplate.afterPropertiesSet();
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void setDefaultExpire(long defaultExpire) {
        if (defaultExpire > 0) {
            this.defaultExpire = defaultExpire;
        } else {
            this.defaultExpire = NOT_EXPIRE;
        }
    }

    @Override
    public Set<K> keys(K pattern) {
        return redisTemplate.keys(pattern);
    }

    @Override
    public boolean hasKey(K key) {
        Boolean exists = redisTemplate.hasKey(key);
        return exists != null && exists;
    }

    @Override
    public void renameKey(K oldKey, K newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    @Override
    public boolean renameKeyNotExist(K oldKey, K newKey) {
        Boolean success = redisTemplate.renameIfAbsent(oldKey, newKey);
        return success != null && success;
    }

    @Override
    public boolean deleteKey(K key) {
        Boolean success = redisTemplate.delete(key);
        return success != null && success;
    }

    @Override
    public long deleteKeys(K... keys) {
        Long result = redisTemplate.delete(Arrays.asList(keys));
        if (result == null) {
            throw new OperationFailureException(RedisTemplate.class, null, "result is null.");
        }
        return result;
    }

    @Override
    public long deleteKeys(Collection<K> keys) {
        Long result = redisTemplate.delete(keys);
        if (result == null) {
            throw new OperationFailureException(RedisTemplate.class, null, "result is null.");
        }
        return result;
    }

    @Override
    public void expireKey(K key, long timeout, TimeUnit timeUnit) {
        Boolean success = redisTemplate.expire(key, timeout, timeUnit);
        if (success == null) {
            throw new OperationFailureException(RedisTemplate.class, null, "result is null.");
        }
        if (!success) {
            throw new OperationFailureException(RedisTemplate.class, null, "result is false.");
        }
    }

    @Override
    public void expireKeyAt(K key, Date date) {
        Boolean success = redisTemplate.expireAt(key, date);
        if (success == null) {
            throw new OperationFailureException(RedisTemplate.class, null, "result is null.");
        }
        if (!success) {
            throw new OperationFailureException(RedisTemplate.class, null, "result is false.");
        }
    }

    @Override
    public long getKeyExpire(K key, TimeUnit timeUnit) {
        Long timeout = redisTemplate.getExpire(key, timeUnit);
        if (timeout == null) {
            throw new OperationFailureException(RedisTemplate.class, null, "result is null.");
        }
        return timeout;
    }

    @Override
    public boolean persistKey(K key) {
        Boolean success = redisTemplate.persist(key);
        return success != null && success;
    }

    @Override
    public void set(K key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(K key, V value, long seconds) {
        this.set(key, value);
        this.expireKey(key, seconds, TimeUnit.SECONDS);
    }

    private String toJSONString(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private <T> T caseValue(Object value, TypeReference<T> typeReference) {
        if (value == null) {
            return null;
        }
        if (value.getClass() == typeReference.getType()) {
            return (T) value;
        }
        try {
            return objectMapper.readValue(toJSONString(value), typeReference);
        } catch (IOException e) {
            e.printStackTrace();
            throw new OperationFailureException(RedisTemplate.class, null, "case value fail.");
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T caseValue(Object value, Class<T> valueType) {
        if (value == null) {
            return null;
        }
        if (value.getClass() == valueType) {
            return (T) value;
        }
        try {
            return objectMapper.readValue(toJSONString(value), valueType);
        } catch (IOException e) {
            e.printStackTrace();
            throw new OperationFailureException(RedisTemplate.class, null, "case value fail.");
        }
    }

    @Override
    public V get(K key) {
        return caseValue(redisTemplate.opsForValue().get(key), this.valueTypeReference);
    }

    @Override
    public <T> T get(K key, Class<T> valueType) {
        return caseValue(get(key), valueType);
    }

    @Override
    public <T> T get(K key, TypeReference<T> typeReference) {
        return caseValue(get(key), typeReference);
    }

    @Override
    public void setAll(Map<? extends K, ? extends V> map) {
        redisTemplate.opsForValue().multiSet(map);
    }

    @Override
    public void setAll(Map<? extends K, ? extends V> map, long seconds) {
        setAll(map);
        for (K key : map.keySet()) {
            expireKey(key, seconds, TimeUnit.SECONDS);
        }
    }

    @Override
    public List<V> getAll(Collection<K> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    @Override
    public <T> List<T> getAll(Collection<K> keys, Class<T> valueType) {
        List<V> values = getAll(keys);
        if (values == null) {
            return null;
        }
        return values.stream().map(value -> caseValue(value, valueType)).collect(Collectors.toList());
    }

    @Override
    public <T> List<T> getAll(Collection<K> keys, TypeReference<T> typeReference) {
        List<V> values = getAll(keys);
        if (values == null) {
            return null;
        }
        return values.stream().map(value -> caseValue(value, typeReference)).collect(Collectors.toList());
    }

    @Override
    public long increment(K key, long delta) {
        Long value = redisTemplate.opsForValue().increment(key, delta);
        if (value == null) {
            throw new OperationFailureException(RedisTemplate.class, null, "value is null.");
        }
        return value;
    }

    @Override
    public double increment(K key, double delta) {
        Double value = redisTemplate.opsForValue().increment(key, delta);
        if (value == null) {
            throw new OperationFailureException(RedisTemplate.class, null, "value is null.");
        }
        return value;
    }

    @Override
    public long decrement(K key, long delta) {
        Long value = redisTemplate.opsForValue().increment(key, -delta);
        if (value == null) {
            throw new OperationFailureException(RedisTemplate.class, null, "value is null.");
        }
        return value;
    }

    @Override
    public double decrement(K key, double delta) {
        Double value = redisTemplate.opsForValue().increment(key, -delta);
        if (value == null) {
            throw new OperationFailureException(RedisTemplate.class, null, "value is null.");
        }
        return value;
    }

    @Override
    public long lAdd(K key, V value) {
        Long result = redisTemplate.opsForList().rightPush(key, value);
        if (result == null) {
            throw new OperationFailureException(RedisTemplate.class, null, "result is null.");
        }
        return result;
    }

    @Override
    public V lGet(K key, long index) {
        return caseValue(redisTemplate.opsForList().index(key, index), this.valueTypeReference);
    }

    @Override
    public <T> T lGet(K key, long index, Class<T> valueType) {
        return caseValue(lGet(key, index), valueType);
    }

    @Override
    public <T> T lGet(K key, long index, TypeReference<T> typeReference) {
        return caseValue(lGet(key, index), typeReference);
    }

    @Override
    public long lSize(K key) {
        Long result = redisTemplate.opsForList().size(key);
        if (result == null) {
            throw new OperationFailureException(RedisTemplate.class, null, "result is null.");
        }
        return result;
    }

    @Override
    public long lAddAll(K key, V... values) {
        Long result = redisTemplate.opsForList().rightPushAll(key, values);
        if (result == null) {
            throw new OperationFailureException(RedisTemplate.class, null, "result is null.");
        }
        return result;
    }

    @Override
    public long lAddAll(K key, Collection<V> values) {
        Long result = redisTemplate.opsForList().rightPushAll(key, values);
        if (result == null) {
            throw new OperationFailureException(RedisTemplate.class, null, "result is null.");
        }
        return result;
    }

    @Override
    public boolean hHasKey(K key, HK hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    @Override
    public void hPut(K key, HK hashKey, HV hashValue) {
        redisTemplate.opsForHash().put(key, hashKey, hashValue);
    }

    @Override
    public boolean hPutIfAbsent(K key, HK hashKey, HV hashValue) {
        return redisTemplate.opsForHash().putIfAbsent(key, hashKey, hashValue);
    }

    @Override
    public HV hGet(K key, HK hashKey) {
        return caseValue(redisTemplate.opsForHash().get(key, hashKey), this.hashValueTypeReference);
    }

    @Override
    public <T> T hGet(K key, HK hashKey, Class<T> valueType) {
        return caseValue(hGet(key, hashKey), valueType);
    }

    @Override
    public <T> T hGet(K key, HK hashKey, TypeReference<T> typeReference) {
        return caseValue(hGet(key, hashKey), typeReference);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<HV> hGetAll(K key, Collection<HK> hashKeys) {
        return (List<HV>) redisTemplate.opsForHash().multiGet(key, (Collection<Object>) hashKeys);
    }

    @Override
    public <T> List<T> hGetAll(K key, Collection<HK> hashKeys, Class<T> valueType) {
        List<HV> values = hGetAll(key, hashKeys);
        if (values == null) {
            return null;
        }
        return values.stream().map(value -> caseValue(value, valueType)).collect(Collectors.toList());
    }

    @Override
    public <T> List<T> hGetAll(K key, Collection<HK> hashKeys, TypeReference<T> typeReference) {
        List<HV> values = hGetAll(key, hashKeys);
        if (values == null) {
            return null;
        }
        return values.stream().map(value -> caseValue(value, typeReference)).collect(Collectors.toList());
    }

    @Override
    public void hPutAll(K key, Map<? extends HK, ? extends HV> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<HK> hKeySet(K key) {
        return (Set<HK>) redisTemplate.opsForHash().keys(key);
    }

    @Override
    public <T> Set<T> hKeySet(K key, Class<T> keyType) {
        Set<HK> keySet = hKeySet(key);
        if (keySet == null) {
            return null;
        }
        return keySet.stream().map(k -> caseValue(k, keyType)).collect(Collectors.toSet());
    }

    @Override
    public <T> Set<T> hKeySet(K key, TypeReference<T> typeReference) {
        Set<HK> keySet = hKeySet(key);
        if (keySet == null) {
            return null;
        }
        return keySet.stream().map(k -> caseValue(k, typeReference)).collect(Collectors.toSet());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<HV> hValues(K key) {
        return (List<HV>) redisTemplate.opsForHash().values(key);
    }

    @Override
    public <T> List<T> hValues(K key, Class<T> keyType) {
        List<HV> values = hValues(key);
        if (values == null) {
            return null;
        }
        return values.stream().map(value -> caseValue(value, keyType)).collect(Collectors.toList());
    }

    @Override
    public <T> List<T> hValues(K key, TypeReference<T> typeReference) {
        List<HV> values = hValues(key);
        if (values == null) {
            return null;
        }
        return values.stream().map(value -> caseValue(value, typeReference)).collect(Collectors.toList());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<HK, HV> hEntries(K key) {
        return (Map<HK, HV>) redisTemplate.opsForHash().entries(key);
    }

    @Override
    public <T, S> Map<T, S> hEntries(K key, Class<T> keyType, Class<S> valueType) {
        Map<HK, HV> entries = hEntries(key);
        Map<T, S> map = new LinkedHashMap<>(entries.size());
        for (Map.Entry<HK, HV> entry : entries.entrySet()) {
            map.put(caseValue(entry.getKey(), keyType), caseValue(entry.getValue(), valueType));
        }
        return map;
    }

    @Override
    public <T, S> Map<T, S> hEntries(K key, TypeReference<T> keyTypeReference, TypeReference<S> valueTypeReference) {
        Map<HK, HV> entries = hEntries(key);
        Map<T, S> map = new LinkedHashMap<>(entries.size());
        for (Map.Entry<HK, HV> entry : entries.entrySet()) {
            map.put(caseValue(entry.getKey(), keyTypeReference), caseValue(entry.getValue(), valueTypeReference));
        }
        return map;
    }

    @Override
    public HV hRemove(K key, HK hashKey) {
        HV value = caseValue(redisTemplate.opsForHash().get(key, hashKey), this.hashValueTypeReference);
        if (value == null) {
            return null;
        }
        redisTemplate.opsForHash().delete(key, hashKey);
        return value;
    }

    @Override
    public boolean hRemove(K key, HK hashKey, HV value) {
        Object val = redisTemplate.opsForHash().get(key, hashKey);
        if (val == null) {
            return true;
        }
        if (Objects.equals(toJSONString(value), toJSONString(val))) {
            return redisTemplate.opsForHash().delete(key, hashKey) == 1;
        }
        return false;
    }

    @Override
    public long hRemoveAll(K key, HK... hashKeys) {
        if (hashKeys == null || hashKeys.length == 0) {
            return 0L;
        }
        return redisTemplate.opsForHash().delete(key, hashKeys);
    }

    @Override
    public long hRemoveAll(K key, Collection<HK> hashKeys) {
        if (hashKeys == null || hashKeys.size() == 0) {
            return 0L;
        }
        return redisTemplate.opsForHash().delete(key, hashKeys.toArray());
    }

    @Override
    public long hSize(K key) {
        return redisTemplate.opsForHash().size(key);
    }

    @Override
    public long hIncrement(K key, HK hashKey, long delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, delta);
    }

    @Override
    public double hIncrement(K key, HK hashKey, double delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, delta);
    }

    @Override
    public long hDecrement(K key, HK hashKey, long delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, -delta);
    }

    @Override
    public double hDecrement(K key, HK hashKey, double delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, -delta);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean sAdd(K key, V value) {
        Long result = redisTemplate.opsForSet().add(key, value);
        return result != null && result == 1;
    }

    @Override
    public long sSize(K key) {
        Long result = redisTemplate.opsForSet().size(key);
        if (result == null) {
            throw new OperationFailureException(RedisTemplate.class, null, "result is null.");
        }
        return result;
    }

    @Override
    public Set<V> sValues(K key) {
        return redisTemplate.opsForSet().members(key);
    }

    @Override
    public <T> Set<T> sValues(K key, Class<T> valueType) {
        Set<V> values = sValues(key);
        if (values == null) {
            return null;
        }
        return values.stream().map(value -> caseValue(value, valueType)).collect(Collectors.toSet());
    }

    @Override
    public <T> Set<T> sValues(K key, TypeReference<T> valueTypeReference) {
        Set<V> values = sValues(key);
        if (values == null) {
            return null;
        }
        return values.stream().map(value -> caseValue(value, valueTypeReference)).collect(Collectors.toSet());
    }

    @Override
    public long sAddAll(K key, V... values) {
        Long result = redisTemplate.opsForSet().add(key, values);
        if (result == null) {
            throw new OperationFailureException(RedisTemplate.class, null, "result is null.");
        }
        return result;
    }

    @Override
    public boolean sRemove(K key, V value) {
        Long result = redisTemplate.opsForSet().remove(key, value);
        return result != null && result == 1;
    }

    @Override
    public long sRemoveAll(K key, V... values) {
        if (values == null || values.length == 0) {
            return 0L;
        }
        Long result = redisTemplate.opsForSet().remove(key, values);
        if (result == null) {
            throw new OperationFailureException(RedisTemplate.class, null, "result is null.");
        }
        return result;
    }

    @Override
    public long sRemoveAll(K key, Collection<V> values) {
        if (values == null || values.size() == 0) {
            return 0L;
        }
        Long result = redisTemplate.opsForSet().remove(key, values.toArray());
        if (result == null) {
            throw new OperationFailureException(RedisTemplate.class, null, "result is null.");
        }
        return result;
    }

    @Override
    public <T> T execute(SessionCallback<T, K, V, HK, HV> session) {
        return session == null ? null : redisTemplate.execute(new org.springframework.data.redis.core.SessionCallback<T>() {
            @Override
            public <RK, RV> T execute(@Nullable org.springframework.data.redis.core.RedisOperations<RK, RV> operations) throws DataAccessException {
                return session.execute(RedisTemplate.this);
            }
        });
    }

    @Override
    public List<Object> executePipelined(PipelinedSessionCallback<K, V, HK, HV> session) {
        return session == null ? null : redisTemplate.executePipelined(new org.springframework.data.redis.core.SessionCallback<Object>() {
            @Override
            public <RK, RV> Object execute(@Nullable org.springframework.data.redis.core.RedisOperations<RK, RV> operations) throws DataAccessException {
                session.execute(RedisTemplate.this);
                return null;
            }
        });
    }

    @Override
    public void watch(K key) {
        redisTemplate.watch(key);
    }

    @Override
    public void watch(Collection<K> keys) {
        redisTemplate.watch(keys);
    }

    @Override
    public void unwatch() {
        redisTemplate.unwatch();
    }

    @Override
    public void multi() {
        redisTemplate.multi();
    }

    @Override
    public void discard() {
        redisTemplate.discard();
    }

    @Override
    public List<Object> exec() {
        return redisTemplate.exec();
    }
}