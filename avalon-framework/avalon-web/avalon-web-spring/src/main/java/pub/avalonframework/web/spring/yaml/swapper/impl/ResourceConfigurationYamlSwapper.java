package pub.avalonframework.web.spring.yaml.swapper.impl;

import pub.avalonframework.core.yaml.swapper.YamlSwapper;
import pub.avalonframework.web.spring.api.config.ResourceConfiguration;
import pub.avalonframework.web.spring.yaml.config.YamlResourceConfiguration;

/**
 * @author baichao
 */
public final class ResourceConfigurationYamlSwapper implements YamlSwapper<YamlResourceConfiguration, ResourceConfiguration> {
    @Override
    public YamlResourceConfiguration swap(ResourceConfiguration data) {
        data = data == null ? new ResourceConfiguration() : data;
        YamlResourceConfiguration configuration = new YamlResourceConfiguration();
        String accessAddress = data.getAccessAddress();
        configuration.setAccessAddress(accessAddress);
        String uploadAddress = data.getUploadAddress();
        configuration.setUploadAddress(uploadAddress);
        return configuration;
    }

    @Override
    public ResourceConfiguration swap(YamlResourceConfiguration yamlConfiguration) {
        yamlConfiguration = yamlConfiguration == null ? new YamlResourceConfiguration() : yamlConfiguration;
        ResourceConfiguration configuration = new ResourceConfiguration();
        String accessAddress = yamlConfiguration.getAccessAddress();
        configuration.setAccessAddress(accessAddress);
        String uploadAddress = yamlConfiguration.getUploadAddress();
        configuration.setUploadAddress(uploadAddress);
        return configuration;
    }
}