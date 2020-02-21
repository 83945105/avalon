package pub.avalonframework.web.spring.yaml.config;

import pub.avalonframework.core.yaml.config.YamlConfiguration;

/**
 * @author baichao
 */
public class YamlWebSpringConfiguration implements YamlConfiguration {

    private YamlCorsConfiguration cors;

    private YamlResourceConfiguration resource;

    public YamlCorsConfiguration getCors() {
        return cors;
    }

    public void setCors(YamlCorsConfiguration cors) {
        this.cors = cors;
    }

    public YamlResourceConfiguration getResource() {
        return resource;
    }

    public void setResource(YamlResourceConfiguration resource) {
        this.resource = resource;
    }
}