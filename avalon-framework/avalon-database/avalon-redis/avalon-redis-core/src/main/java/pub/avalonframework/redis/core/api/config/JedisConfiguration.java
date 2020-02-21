package pub.avalonframework.redis.core.api.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * The jedis configuration.
 *
 * @author baichao
 */
public class JedisConfiguration {

    private Boolean lifo;

    private Boolean fairness;

    private Integer maxTotal;

    private Integer maxIdle;

    private Integer minIdle;

    private Long maxWaitMillis;

    private Boolean blockWhenExhausted;

    private Boolean testOnCreate;

    private Boolean testOnBorrow;

    private Boolean testOnReturn;

    private Boolean testWhileIdle;

    private Long timeBetweenEvictionRunsMillis;

    private Integer numTestsPerEvictionRun;

    private Long minEvictableIdleTimeMillis;

    private Long softMinEvictableIdleTimeMillis;

    private String evictionPolicyClassName;

    public Boolean getLifo() {
        return lifo;
    }

    public void setLifo(Boolean lifo) {
        this.lifo = lifo;
    }

    public Boolean getFairness() {
        return fairness;
    }

    public void setFairness(Boolean fairness) {
        this.fairness = fairness;
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Long getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(Long maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public Boolean getBlockWhenExhausted() {
        return blockWhenExhausted;
    }

    public void setBlockWhenExhausted(Boolean blockWhenExhausted) {
        this.blockWhenExhausted = blockWhenExhausted;
    }

    public Boolean getTestOnCreate() {
        return testOnCreate;
    }

    public void setTestOnCreate(Boolean testOnCreate) {
        this.testOnCreate = testOnCreate;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public Boolean getTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(Boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public Boolean getTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(Boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public Long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(Long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public Integer getNumTestsPerEvictionRun() {
        return numTestsPerEvictionRun;
    }

    public void setNumTestsPerEvictionRun(Integer numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }

    public Long getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(Long minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public Long getSoftMinEvictableIdleTimeMillis() {
        return softMinEvictableIdleTimeMillis;
    }

    public void setSoftMinEvictableIdleTimeMillis(Long softMinEvictableIdleTimeMillis) {
        this.softMinEvictableIdleTimeMillis = softMinEvictableIdleTimeMillis;
    }

    public String getEvictionPolicyClassName() {
        return evictionPolicyClassName;
    }

    public void setEvictionPolicyClassName(String evictionPolicyClassName) {
        this.evictionPolicyClassName = evictionPolicyClassName;
    }

    public void injectPoolConfig(GenericObjectPoolConfig poolConfig) {
        poolConfig.setLifo(this.getLifo());
        poolConfig.setFairness(this.getFairness());
        poolConfig.setMaxTotal(this.getMaxTotal());
        poolConfig.setMaxIdle(this.getMaxIdle());
        poolConfig.setMinIdle(this.getMinIdle());
        poolConfig.setMaxWaitMillis(this.getMaxWaitMillis());
        poolConfig.setBlockWhenExhausted(this.getBlockWhenExhausted());
        poolConfig.setTestOnCreate(this.getTestOnCreate());
        poolConfig.setTestOnBorrow(this.getTestOnBorrow());
        poolConfig.setTestOnReturn(this.getTestOnReturn());
        poolConfig.setTestWhileIdle(this.getTestWhileIdle());
        poolConfig.setTimeBetweenEvictionRunsMillis(this.getTimeBetweenEvictionRunsMillis());
        poolConfig.setNumTestsPerEvictionRun(this.getNumTestsPerEvictionRun());
        poolConfig.setMinEvictableIdleTimeMillis(this.getMinEvictableIdleTimeMillis());
        poolConfig.setSoftMinEvictableIdleTimeMillis(this.getSoftMinEvictableIdleTimeMillis());
        poolConfig.setEvictionPolicyClassName(this.getEvictionPolicyClassName());
    }
}