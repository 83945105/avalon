package pub.avalonframework.security.core.yaml.config;

import pub.avalonframework.core.yaml.config.YamlConfiguration;

/**
 * @author baichao
 */
public class YamlHttpConfiguration implements YamlConfiguration {

    private String ajaxHeaderIdentificationKey;

    private String ajaxHeaderIdentificationValue;

    public String getAjaxHeaderIdentificationKey() {
        return ajaxHeaderIdentificationKey;
    }

    public void setAjaxHeaderIdentificationKey(String ajaxHeaderIdentificationKey) {
        this.ajaxHeaderIdentificationKey = ajaxHeaderIdentificationKey;
    }

    public String getAjaxHeaderIdentificationValue() {
        return ajaxHeaderIdentificationValue;
    }

    public void setAjaxHeaderIdentificationValue(String ajaxHeaderIdentificationValue) {
        this.ajaxHeaderIdentificationValue = ajaxHeaderIdentificationValue;
    }
}