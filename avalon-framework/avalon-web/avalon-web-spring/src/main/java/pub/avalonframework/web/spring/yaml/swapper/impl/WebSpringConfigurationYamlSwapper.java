package pub.avalonframework.web.spring.yaml.swapper.impl;

import pub.avalonframework.core.yaml.swapper.YamlSwapper;
import pub.avalonframework.web.spring.api.config.CorsConfiguration;
import pub.avalonframework.web.spring.api.config.ResourceConfiguration;
import pub.avalonframework.web.spring.api.config.WebSpringConfiguration;
import pub.avalonframework.web.spring.yaml.config.YamlCorsConfiguration;
import pub.avalonframework.web.spring.yaml.config.YamlResourceConfiguration;
import pub.avalonframework.web.spring.yaml.config.YamlWebSpringConfiguration;

/**
 * @author baichao
 */
public final class WebSpringConfigurationYamlSwapper implements YamlSwapper<YamlWebSpringConfiguration, WebSpringConfiguration> {

    @Override
    public YamlWebSpringConfiguration swap(WebSpringConfiguration data) {
        data = data == null ? new WebSpringConfiguration() : data;
        YamlWebSpringConfiguration configuration = new YamlWebSpringConfiguration();
        CorsConfiguration cors = data.getCors();
        configuration.setCors(new CorsConfigurationYamlSwapper().swap(cors));
        ResourceConfiguration resource = data.getResource();
        configuration.setResource(new ResourceConfigurationYamlSwapper().swap(resource));
        return configuration;
    }

    @Override
    public WebSpringConfiguration swap(YamlWebSpringConfiguration yamlConfiguration) {
        yamlConfiguration = yamlConfiguration == null ? new YamlWebSpringConfiguration() : yamlConfiguration;
        WebSpringConfiguration configuration = new WebSpringConfiguration();
        YamlCorsConfiguration cors = yamlConfiguration.getCors();
        configuration.setCors(new CorsConfigurationYamlSwapper().swap(cors));
        YamlResourceConfiguration resource = yamlConfiguration.getResource();
        configuration.setResource(new ResourceConfigurationYamlSwapper().swap(resource));
        return configuration;
    }
}