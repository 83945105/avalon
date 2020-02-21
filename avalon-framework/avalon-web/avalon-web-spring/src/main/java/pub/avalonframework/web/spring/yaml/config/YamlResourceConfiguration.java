package pub.avalonframework.web.spring.yaml.config;

import pub.avalonframework.core.yaml.config.YamlConfiguration;

/**
 * @author baichao
 */
public class YamlResourceConfiguration implements YamlConfiguration {

    private String uploadAddress;

    public String getUploadAddress() {
        return uploadAddress;
    }

    public void setUploadAddress(String uploadAddress) {
        this.uploadAddress = uploadAddress;
    }
}