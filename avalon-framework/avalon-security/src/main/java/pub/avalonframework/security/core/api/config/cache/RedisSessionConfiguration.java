package pub.avalonframework.security.core.api.config.cache;

/**
 * Redis session configuration.
 *
 * @author baichao
 */
public class RedisSessionConfiguration {

    /**
     * The redis hostname.
     */
    private String hostName;

    /**
     * The redis port.
     */
    private Integer port;

    /**
     * The redis database num.
     */
    private Integer database;

    /**
     * The redis password.
     */
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