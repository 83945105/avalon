package pub.avalonframework.redis.core.yaml.swapper.impl;

import org.apache.commons.pool2.impl.BaseObjectPoolConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import pub.avalonframework.core.yaml.swapper.YamlSwapper;
import pub.avalonframework.redis.core.api.config.JedisConfiguration;
import pub.avalonframework.redis.core.yaml.config.YamlJedisConfiguration;

/**
 * @author baichao
 */
public final class JedisConfigurationYamlSwapper implements YamlSwapper<YamlJedisConfiguration, JedisConfiguration> {

    public final static boolean DEFAULT_LIFO = BaseObjectPoolConfig.DEFAULT_LIFO;

    public final static boolean DEFAULT_FAIRNESS = BaseObjectPoolConfig.DEFAULT_FAIRNESS;

    public final static int DEFAULT_MAX_TOTAL = GenericObjectPoolConfig.DEFAULT_MAX_TOTAL;

    public final static int DEFAULT_MAX_IDLE = GenericObjectPoolConfig.DEFAULT_MAX_IDLE;

    public final static int DEFAULT_MIN_IDLE = GenericObjectPoolConfig.DEFAULT_MIN_IDLE;

    public final static long DEFAULT_MAX_WAIT_MILLIS = BaseObjectPoolConfig.DEFAULT_MAX_WAIT_MILLIS;

    public final static boolean DEFAULT_BLOCK_WHEN_EXHAUSTED = BaseObjectPoolConfig.DEFAULT_BLOCK_WHEN_EXHAUSTED;

    public final static boolean DEFAULT_TEST_ON_CREATE = BaseObjectPoolConfig.DEFAULT_TEST_ON_CREATE;

    public final static boolean DEFAULT_TEST_ON_BORROW = BaseObjectPoolConfig.DEFAULT_TEST_ON_BORROW;

    public final static boolean DEFAULT_TEST_ON_RETURN = BaseObjectPoolConfig.DEFAULT_TEST_ON_RETURN;

    public final static boolean DEFAULT_TEST_WHILE_IDLE = BaseObjectPoolConfig.DEFAULT_TEST_WHILE_IDLE;

    public final static long DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS = BaseObjectPoolConfig.DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS;

    public final static int DEFAULT_NUM_TESTS_PER_EVICTION_RUN = BaseObjectPoolConfig.DEFAULT_NUM_TESTS_PER_EVICTION_RUN;

    public final static long DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS = BaseObjectPoolConfig.DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS;

    public final static long DEFAULT_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS = BaseObjectPoolConfig.DEFAULT_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS;

    public final static String DEFAULT_EVICTION_POLICY_CLASS_NAME = BaseObjectPoolConfig.DEFAULT_EVICTION_POLICY_CLASS_NAME;

    @Override
    public YamlJedisConfiguration swap(JedisConfiguration data) {
        data = data == null ? new JedisConfiguration() : data;
        YamlJedisConfiguration configuration = new YamlJedisConfiguration();
        Boolean lifo = data.getLifo();
        configuration.setLifo(lifo == null ? DEFAULT_LIFO : lifo);
        Boolean fairness = data.getFairness();
        configuration.setFairness(fairness == null ? DEFAULT_FAIRNESS : fairness);
        Integer maxTotal = data.getMaxTotal();
        configuration.setMaxTotal(maxTotal == null ? DEFAULT_MAX_TOTAL : maxTotal);
        Integer maxIdle = data.getMaxIdle();
        configuration.setMaxIdle(maxIdle == null ? DEFAULT_MAX_IDLE : maxIdle);
        Integer minIdle = data.getMinIdle();
        configuration.setMinIdle(minIdle == null ? DEFAULT_MIN_IDLE : minIdle);
        Long maxWaitMillis = data.getMaxWaitMillis();
        configuration.setMaxWaitMillis(maxWaitMillis == null ? DEFAULT_MAX_WAIT_MILLIS : maxWaitMillis);
        Boolean blockWhenExhausted = data.getBlockWhenExhausted();
        configuration.setBlockWhenExhausted(blockWhenExhausted == null ? DEFAULT_BLOCK_WHEN_EXHAUSTED : blockWhenExhausted);
        Boolean testOnCreate = data.getTestOnCreate();
        configuration.setTestOnCreate(testOnCreate == null ? DEFAULT_TEST_ON_CREATE : testOnCreate);
        Boolean testOnBorrow = data.getTestOnBorrow();
        configuration.setTestOnBorrow(testOnBorrow == null ? DEFAULT_TEST_ON_BORROW : testOnBorrow);
        Boolean testOnReturn = data.getTestOnReturn();
        configuration.setTestOnReturn(testOnReturn == null ? DEFAULT_TEST_ON_RETURN : testOnReturn);
        Boolean testWhileIdle = data.getTestWhileIdle();
        configuration.setTestWhileIdle(testWhileIdle == null ? DEFAULT_TEST_WHILE_IDLE : testWhileIdle);
        Long timeBetweenEvictionRunsMillis = data.getTimeBetweenEvictionRunsMillis();
        configuration.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis == null ? DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS : timeBetweenEvictionRunsMillis);
        Integer numTestsPerEvictionRun = data.getNumTestsPerEvictionRun();
        configuration.setNumTestsPerEvictionRun(numTestsPerEvictionRun == null ? DEFAULT_NUM_TESTS_PER_EVICTION_RUN : numTestsPerEvictionRun);
        Long minEvictableIdleTimeMillis = data.getMinEvictableIdleTimeMillis();
        configuration.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis == null ? DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS : minEvictableIdleTimeMillis);
        Long softMinEvictableIdleTimeMillis = data.getSoftMinEvictableIdleTimeMillis();
        configuration.setSoftMinEvictableIdleTimeMillis(softMinEvictableIdleTimeMillis == null ? DEFAULT_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS : softMinEvictableIdleTimeMillis);
        String evictionPolicyClassName = data.getEvictionPolicyClassName();
        configuration.setEvictionPolicyClassName(evictionPolicyClassName == null ? DEFAULT_EVICTION_POLICY_CLASS_NAME : evictionPolicyClassName);
        return configuration;
    }

    @Override
    public JedisConfiguration swap(YamlJedisConfiguration yamlConfiguration) {
        yamlConfiguration = yamlConfiguration == null ? new YamlJedisConfiguration() : yamlConfiguration;
        JedisConfiguration configuration = new JedisConfiguration();
        Boolean lifo = yamlConfiguration.getLifo();
        configuration.setLifo(lifo == null ? DEFAULT_LIFO : lifo);
        Boolean fairness = yamlConfiguration.getFairness();
        configuration.setFairness(fairness == null ? DEFAULT_FAIRNESS : fairness);
        Integer maxTotal = yamlConfiguration.getMaxTotal();
        configuration.setMaxTotal(maxTotal == null ? DEFAULT_MAX_TOTAL : maxTotal);
        Integer maxIdle = yamlConfiguration.getMaxIdle();
        configuration.setMaxIdle(maxIdle == null ? DEFAULT_MAX_IDLE : maxIdle);
        Integer minIdle = yamlConfiguration.getMinIdle();
        configuration.setMinIdle(minIdle == null ? DEFAULT_MIN_IDLE : minIdle);
        Long maxWaitMillis = yamlConfiguration.getMaxWaitMillis();
        configuration.setMaxWaitMillis(maxWaitMillis == null ? DEFAULT_MAX_WAIT_MILLIS : maxWaitMillis);
        Boolean blockWhenExhausted = yamlConfiguration.getBlockWhenExhausted();
        configuration.setBlockWhenExhausted(blockWhenExhausted == null ? DEFAULT_BLOCK_WHEN_EXHAUSTED : blockWhenExhausted);
        Boolean testOnCreate = yamlConfiguration.getTestOnCreate();
        configuration.setTestOnCreate(testOnCreate == null ? DEFAULT_TEST_ON_CREATE : testOnCreate);
        Boolean testOnBorrow = yamlConfiguration.getTestOnBorrow();
        configuration.setTestOnBorrow(testOnBorrow == null ? DEFAULT_TEST_ON_BORROW : testOnBorrow);
        Boolean testOnReturn = yamlConfiguration.getTestOnReturn();
        configuration.setTestOnReturn(testOnReturn == null ? DEFAULT_TEST_ON_RETURN : testOnReturn);
        Boolean testWhileIdle = yamlConfiguration.getTestWhileIdle();
        configuration.setTestWhileIdle(testWhileIdle == null ? DEFAULT_TEST_WHILE_IDLE : testWhileIdle);
        Long timeBetweenEvictionRunsMillis = yamlConfiguration.getTimeBetweenEvictionRunsMillis();
        configuration.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis == null ? DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS : timeBetweenEvictionRunsMillis);
        Integer numTestsPerEvictionRun = yamlConfiguration.getNumTestsPerEvictionRun();
        configuration.setNumTestsPerEvictionRun(numTestsPerEvictionRun == null ? DEFAULT_NUM_TESTS_PER_EVICTION_RUN : numTestsPerEvictionRun);
        Long minEvictableIdleTimeMillis = yamlConfiguration.getMinEvictableIdleTimeMillis();
        configuration.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis == null ? DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS : minEvictableIdleTimeMillis);
        Long softMinEvictableIdleTimeMillis = yamlConfiguration.getSoftMinEvictableIdleTimeMillis();
        configuration.setSoftMinEvictableIdleTimeMillis(softMinEvictableIdleTimeMillis == null ? DEFAULT_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS : softMinEvictableIdleTimeMillis);
        String evictionPolicyClassName = yamlConfiguration.getEvictionPolicyClassName();
        configuration.setEvictionPolicyClassName(evictionPolicyClassName == null ? DEFAULT_EVICTION_POLICY_CLASS_NAME : evictionPolicyClassName);
        return configuration;
    }
}