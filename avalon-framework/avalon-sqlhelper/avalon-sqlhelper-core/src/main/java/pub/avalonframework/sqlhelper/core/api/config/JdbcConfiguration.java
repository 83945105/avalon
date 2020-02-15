package pub.avalonframework.sqlhelper.core.api.config;

/**
 * @author baichao
 */
public class JdbcConfiguration {

    private Boolean allowReturnNull;

    public Boolean getAllowReturnNull() {
        return allowReturnNull;
    }

    public void setAllowReturnNull(Boolean allowReturnNull) {
        this.allowReturnNull = allowReturnNull;
    }
}