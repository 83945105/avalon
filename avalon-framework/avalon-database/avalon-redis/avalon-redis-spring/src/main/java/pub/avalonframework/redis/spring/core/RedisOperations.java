package pub.avalonframework.redis.spring.core;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.lang.Nullable;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author baichao
 */
public interface RedisOperations<K, V, HK, HV> {

    /**
     * Set default expire
     *
     * @param defaultExpire Validity setting > 0 , Indefinitely set <= 0
     */
    void setDefaultExpire(long defaultExpire);

    /**
     * Match to get the key collection.
     *
     * @param pattern The pattern.
     * @return The key set.
     */
    Set<K> keys(K pattern);

    /**
     * Determine if the key exists.
     *
     * @param key The key.
     * @return Exist return true, Does not exist return false.
     */
    boolean hasKey(K key);

    /**
     * Rename key.
     * If newKey already exists, the original value of newKey is overwritten.
     *
     * @param oldKey The old key.
     * @param newKey The new key.
     */
    void renameKey(K oldKey, K newKey);

    /**
     * Rename key.
     * Rename when newKey does not exist.
     *
     * @param oldKey The old key.
     * @param newKey The new key.
     * @return Rename successfully returns true, Rename failed to return false.
     */
    boolean renameKeyNotExist(K oldKey, K newKey);

    /**
     * Delete key.
     *
     * @param key The key.
     * @return Delete successfully returns true, Delete failed to return false.
     */
    boolean deleteKey(K key);

    /**
     * Delete multiple keys.
     *
     * @param keys The keys for indefinite parameters.
     * @return Delete successful number.
     */
    long deleteKeys(K... keys);

    /**
     * Delete multiple keys.
     *
     * @param keys The keys for collection.
     * @return Delete successful number.
     */
    long deleteKeys(Collection<K> keys);

    /**
     * Set the life cycle of the key.
     *
     * @param key      The key.
     * @param timeout  The time for expired.
     * @param timeUnit The time unit.
     */
    void expireKey(K key, long timeout, TimeUnit timeUnit);

    /**
     * The specified key expires on the specified date.
     *
     * @param key  The key.
     * @param date Expiration date.
     */
    void expireKeyAt(K key, Date date);

    /**
     * Query the life cycle of the key.
     *
     * @param key      The key.
     * @param timeUnit The time unit.
     * @return Return -2 means no such key, Return -1 means permanent valid.
     */
    long getKeyExpire(K key, TimeUnit timeUnit);

    /**
     * Set the key to permanent.
     *
     * @param key The key.
     * @return Return true if the setting is successful, Setting failed to return false, If the key itself is permanently valid, it also returns false.
     */
    boolean persistKey(K key);

    /**
     * Setting value.
     *
     * @param key   The key.
     * @param value The value.
     */
    void set(K key, V value);

    /**
     * Setting value and expiration time.
     *
     * @param key     The key.
     * @param value   The value.
     * @param seconds Timeout in seconds.
     */
    void set(K key, V value, long seconds);

    /**
     * Get value.
     *
     * @param key The key.
     * @return The value.
     */
    V get(K key);

    /**
     * Get value.
     *
     * @param key       The key.
     * @param valueType The value type.
     * @return Converted value after type.
     */
    <T> T get(K key, Class<T> valueType);

    /**
     * Get value.
     *
     * @param key           The key.
     * @param typeReference The value type.
     * @return Converted value after type.
     */
    <T> T get(K key, TypeReference<T> typeReference);

    /**
     * Batch setting value.
     *
     * @param map The keys and values.
     */
    void setAll(Map<? extends K, ? extends V> map);

    /**
     * Batch setting value.
     *
     * @param map     The keys and values.
     * @param seconds Timeout in seconds.
     */
    void setAll(Map<? extends K, ? extends V> map, long seconds);

    /**
     * Batch get value.
     * If the key does not have a corresponding value, it will return null.
     *
     * @param keys The keys.
     * @return The values.
     */
    List<V> getAll(Collection<K> keys);

    /**
     * Batch get value.
     * If the key does not have a corresponding value, it will return null.
     *
     * @param keys      The keys.
     * @param valueType The value type.
     * @return Converted values after type.
     */
    <T> List<T> getAll(Collection<K> keys, Class<T> valueType);

    /**
     * Batch get value.
     * If the key does not have a corresponding value, it will return null.
     *
     * @param keys          The keys.
     * @param typeReference The value type.
     * @return Converted values after type.
     */
    <T> List<T> getAll(Collection<K> keys, TypeReference<T> typeReference);

    /**
     * Increment.
     *
     * @param key   The key.
     * @param delta The delta.
     * @return Incremented value.
     */
    long increment(K key, long delta);

    /**
     * Increment.
     *
     * @param key   The key.
     * @param delta The delta.
     * @return Incremented value.
     */
    double increment(K key, double delta);

    /**
     * Decrement.
     *
     * @param key   The key.
     * @param delta The delta.
     * @return Decrement value.
     */
    long decrement(K key, long delta);

    /**
     * Decrement.
     *
     * @param key   The key.
     * @param delta The delta.
     * @return Decrement value.
     */
    double decrement(K key, double delta);

    /**
     * Add value to list.
     *
     * @param key   The key.
     * @param value The value.
     * @return The list size.
     */
    long lAdd(K key, V value);

    /**
     * Get value form list.
     *
     * @param key   The key.
     * @param index The index for value.
     * @return The value.
     */
    V lGet(K key, long index);

    /**
     * Get value form list.
     *
     * @param key       The key.
     * @param index     The index for value.
     * @param valueType The value type.
     * @return Converted value after type.
     */
    <T> T lGet(K key, long index, Class<T> valueType);

    /**
     * Get value form list.
     *
     * @param key           The key.
     * @param index         The index for value.
     * @param typeReference The value type.
     * @return Converted value after type.
     */
    <T> T lGet(K key, long index, TypeReference<T> typeReference);

    /**
     * Get list size.
     *
     * @param key The key.
     * @return The list size.
     */
    long lSize(K key);

    /**
     * Batch add value to list.
     *
     * @param key    The key.
     * @param values The values for indefinite parameters.
     * @return Number added.
     */
    long lAddAll(K key, V... values);

    /**
     * Batch add value to list.
     *
     * @param key    The key.
     * @param values The values for collection.
     * @return Number added.
     */
    long lAddAll(K key, Collection<V> values);

    /**
     * Determine whether the key exists from the hash.
     *
     * @param key     The key.
     * @param hashKey The hashKey.
     * @return Present returns true, Does not exist return false.
     */
    boolean hHasKey(K key, HK hashKey);

    /**
     * Put value to hash.
     *
     * @param key       The key.
     * @param hashKey   The hashKey.
     * @param hashValue The hashValue.
     */
    void hPut(K key, HK hashKey, HV hashValue);

    /**
     * Set to hash if it does not exist
     *
     * @param key       The key.
     * @param hashKey   The hashKey.
     * @param hashValue The hashValue.
     * @return Setting success returns true, Setting failed to return false.
     */
    boolean hPutIfAbsent(K key, HK hashKey, HV hashValue);

    /**
     * Get value from hash.
     *
     * @param key     The key.
     * @param hashKey The hashKey.
     * @return The hashValue.
     */
    HV hGet(K key, HK hashKey);

    /**
     * Get value from hash.
     *
     * @param key       The key.
     * @param hashKey   The hashKey.
     * @param valueType The value type.
     * @return Converted value after type.
     */
    <T> T hGet(K key, HK hashKey, Class<T> valueType);

    /**
     * Get value from hash.
     *
     * @param key           The key.
     * @param hashKey       The hashKey.
     * @param typeReference The value type.
     * @return Converted value after type.
     */
    <T> T hGet(K key, HK hashKey, TypeReference<T> typeReference);

    /**
     * Batch get value from hash.
     *
     * @param key      The key.
     * @param hashKeys The hashKeys.
     * @return The hashValues.
     */
    List<HV> hGetAll(K key, Collection<HK> hashKeys);

    /**
     * Batch get value from hash.
     *
     * @param key       The key.
     * @param hashKeys  The hashKeys.
     * @param valueType The valueType.
     * @return Converted values after type.
     */
    <T> List<T> hGetAll(K key, Collection<HK> hashKeys, Class<T> valueType);

    /**
     * Batch get value from hash.
     *
     * @param key           The key.
     * @param hashKeys      The hashKeys.
     * @param typeReference The valueType.
     * @return Converted values after type.
     */
    <T> List<T> hGetAll(K key, Collection<HK> hashKeys, TypeReference<T> typeReference);

    /**
     * Batch set to hash.
     *
     * @param key The Key.
     * @param map The hashKeys and HashValues.
     */
    void hPutAll(K key, Map<? extends HK, ? extends HV> map);

    /**
     * Get all hashKeys from hash.
     *
     * @param key The key.
     * @return The hashKeys.
     */
    Set<HK> hKeySet(K key);

    /**
     * Get all hashKeys from hash.
     *
     * @param key     The key.
     * @param keyType The key type.
     * @return Converted value after type.
     */
    <T> Set<T> hKeySet(K key, Class<T> keyType);

    /**
     * Get all hashKeys from hash.
     *
     * @param key           The key.
     * @param typeReference The key type.
     * @return Converted value after type.
     */
    <T> Set<T> hKeySet(K key, TypeReference<T> typeReference);

    /**
     * Get all hashValues from hash.
     *
     * @param key The key.
     * @return The hashValues.
     */
    List<HV> hValues(K key);

    /**
     * Get all hashValues from hash.
     *
     * @param key     The key.
     * @param keyType The key type.
     * @return Converted value after type.
     */
    <T> List<T> hValues(K key, Class<T> keyType);

    /**
     * Get all hashValues from hash.
     *
     * @param key           The key.
     * @param typeReference The key type.
     * @return Converted value after type.
     */
    <T> List<T> hValues(K key, TypeReference<T> typeReference);

    /**
     * Get hashKeys and hashValues from hash.
     *
     * @param key The key.
     * @return The hashKeys and hashValues.
     */
    Map<HK, HV> hEntries(K key);

    /**
     * Get hashKeys and hashValues from hash.
     *
     * @param key       The key.
     * @param keyType   The key type.
     * @param valueType The value type.
     * @return Converted key and value after type.
     */
    <T, S> Map<T, S> hEntries(K key, Class<T> keyType, Class<S> valueType);

    /**
     * Get hashKeys and hashValues from hash.
     *
     * @param key                The key.
     * @param keyTypeReference   The key type.
     * @param valueTypeReference The value type.
     * @return Converted key and value after type.
     */
    <T, S> Map<T, S> hEntries(K key, TypeReference<T> keyTypeReference, TypeReference<S> valueTypeReference);

    /**
     * Delete hashKey from hash.
     *
     * @param key     The key.
     * @param hashKey The hashKey.
     * @return The deleted hashValue.
     */
    HV hRemove(K key, HK hashKey);

    /**
     * Delete hashKey from hash.
     *
     * @param key       The key.
     * @param hashKey   The hashKey.
     * @param hashValue The hashValue.
     * @return Whether to delete successfully.
     */
    boolean hRemove(K key, HK hashKey, HV hashValue);

    /**
     * Batch delete from hash.
     *
     * @param key      The key.
     * @param hashKeys The hashKeys for indefinite parameters.
     * @return Number of successfully deleted.
     */
    long hRemoveAll(K key, HK... hashKeys);

    /**
     * Batch delete from hash.
     *
     * @param key      The key.
     * @param hashKeys The hashKeys for collection.
     * @return Number of successfully deleted.
     */
    long hRemoveAll(K key, Collection<HK> hashKeys);

    /**
     * Get size from hash.
     *
     * @param key The key.
     * @return The size.
     */
    long hSize(K key);

    /**
     * Increment for hash.
     *
     * @param key     The key.
     * @param hashKey The hashKey.
     * @param delta   The delta.
     * @return Increment value.
     */
    long hIncrement(K key, HK hashKey, long delta);

    /**
     * Increment for hash.
     *
     * @param key     The key.
     * @param hashKey The hashKey.
     * @param delta   The delta.
     * @return Increment value.
     */
    double hIncrement(K key, HK hashKey, double delta);

    /**
     * Decrement for hash.
     *
     * @param key     The key.
     * @param hashKey The hashKey.
     * @param delta   The delta.
     * @return Decrement value.
     */
    long hDecrement(K key, HK hashKey, long delta);

    /**
     * Decrement for hash.
     *
     * @param key     The key.
     * @param hashKey The hashKey.
     * @param delta   The delta.
     * @return Decrement value.
     */
    double hDecrement(K key, HK hashKey, double delta);

    /**
     * Add value to set.
     *
     * @param key   The key.
     * @param value The value.
     * @return Return true for success, false for failure.
     */
    boolean sAdd(K key, V value);

    /**
     * Get size for set.
     *
     * @param key The key.
     * @return The size.
     */
    long sSize(K key);

    /**
     * Get all values for set.
     *
     * @param key The key.
     * @return The values.
     */
    Set<V> sValues(K key);

    /**
     * Get all values for set.
     *
     * @param key       The key.
     * @param valueType The value type.
     * @return Converted key and value after type.
     */
    <T> Set<T> sValues(K key, Class<T> valueType);

    /**
     * Get all values for set.
     *
     * @param key                The key.
     * @param valueTypeReference The value type.
     * @return Converted key and value after type.
     */
    <T> Set<T> sValues(K key, TypeReference<T> valueTypeReference);

    /**
     * Batch add values to set.
     *
     * @param key    The key.
     * @param values The values
     * @return Number of successfully added.
     */
    long sAddAll(K key, V... values);

    /**
     * Delete value for set.
     *
     * @param key   The key.
     * @param value The value.
     * @return Return true for success, false for failure.
     */
    boolean sRemove(K key, V value);

    /**
     * Batch delete value for set.
     *
     * @param key    The key.
     * @param values The values for indefinite parameter.
     * @return Successfully deleted quantity.
     */
    long sRemoveAll(K key, V... values);

    /**
     * Batch delete value for set.
     *
     * @param key    The key.
     * @param values The values for collection.
     * @return Successfully deleted quantity.
     */
    long sRemoveAll(K key, Collection<V> values);

    /**
     * Batch execution instructions.
     *
     * @param session The callback for {@link SessionCallback}
     * @return The result.
     */
    @Nullable
    <T> T execute(SessionCallback<T, K, V, HK, HV> session);

    /**
     * Execute instructions in batches using pipes
     *
     * @param session The callback for {@link SessionCallback}
     * @return The result.
     */
    List<Object> executePipelined(PipelinedSessionCallback<K, V, HK, HV> session);

    /**
     * Watch key.
     *
     * @param key The key.
     */
    void watch(K key);

    /**
     * Watch keys.
     *
     * @param keys The keys.
     */
    void watch(Collection<K> keys);

    /**
     * Unwatch all keys.
     */
    void unwatch();

    /**
     * Open transaction.
     */
    void multi();

    /**
     * Cancel transaction.
     */
    void discard();

    /**
     * Executive transaction.
     *
     * @return The result.
     */
    List<Object> exec();
}