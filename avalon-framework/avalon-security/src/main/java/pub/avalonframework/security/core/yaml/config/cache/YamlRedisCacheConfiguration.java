package pub.avalonframework.security.core.yaml.config.cache;

import pub.avalonframework.security.core.yaml.config.YamlConfiguration;

/**
 * @author baichao
 */
public class YamlRedisCacheConfiguration implements YamlConfiguration {

    private String hostName;

    private Integer port;

    private Integer database;

    private String password;

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
}