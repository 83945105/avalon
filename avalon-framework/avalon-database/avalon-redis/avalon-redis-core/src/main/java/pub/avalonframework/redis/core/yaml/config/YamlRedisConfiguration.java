package pub.avalonframework.redis.core.yaml.config;

import pub.avalonframework.core.yaml.config.YamlConfiguration;
import pub.avalonframework.redis.core.api.beans.RedisImpl;

/**
 * @author baichao
 */
public class YamlRedisConfiguration implements YamlConfiguration {

    private String hostName;

    private Integer port;

    private Integer database;

    private String password;

    private RedisImpl impl;

    private YamlJedisConfiguration jedis;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getDatabase() {
        return database;
    }

    public void setDatabase(Integer database) {
        this.database = database;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RedisImpl getImpl() {
        return impl;
    }

    public void setImpl(RedisImpl impl) {
        this.impl = impl;
    }

    public YamlJedisConfiguration getJedis() {
        return jedis;
    }

    public void setJedis(YamlJedisConfiguration jedis) {
        this.jedis = jedis;
    }
}