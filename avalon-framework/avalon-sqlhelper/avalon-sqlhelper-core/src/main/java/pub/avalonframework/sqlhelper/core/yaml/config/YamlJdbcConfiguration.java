package pub.avalonframework.sqlhelper.core.yaml.config;

import pub.avalonframework.core.yaml.config.YamlConfiguration;

/**
 * @author baichao
 */
public class YamlJdbcConfiguration implements YamlConfiguration {

    private Boolean allowReturnNull;

    public Boolean getAllowReturnNull() {
        return allowReturnNull;
    }

    public void setAllowReturnNull(Boolean allowReturnNull) {
        this.allowReturnNull = allowReturnNull;
    }
}