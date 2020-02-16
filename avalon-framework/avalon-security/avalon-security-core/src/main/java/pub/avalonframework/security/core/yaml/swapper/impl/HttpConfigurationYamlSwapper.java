package pub.avalonframework.security.core.yaml.swapper.impl;

import pub.avalonframework.core.yaml.swapper.YamlSwapper;
import pub.avalonframework.security.core.api.config.HttpConfiguration;
import pub.avalonframework.security.core.yaml.config.YamlHttpConfiguration;

/**
 * @author baichao
 */
public final class HttpConfigurationYamlSwapper implements YamlSwapper<YamlHttpConfiguration, HttpConfiguration> {

    private final static String DEFAULT_AJAX_HEADER_IDENTIFICATION_KEY = "X-Requested-With";

    private final static String DEFAULT_AJAX_HEADER_IDENTIFICATION_VALUE = "XMLHttpRequest";

    @Override
    public YamlHttpConfiguration swap(HttpConfiguration data) {
        YamlHttpConfiguration configuration = new YamlHttpConfiguration();
        String ajaxHeaderIdentificationKey = data.getAjaxHeaderIdentificationKey();
        configuration.setAjaxHeaderIdentificationKey(ajaxHeaderIdentificationKey == null ? DEFAULT_AJAX_HEADER_IDENTIFICATION_KEY : ajaxHeaderIdentificationKey);
        String ajaxHeaderIdentificationValue = data.getAjaxHeaderIdentificationValue();
        configuration.setAjaxHeaderIdentificationValue(ajaxHeaderIdentificationValue == null ? DEFAULT_AJAX_HEADER_IDENTIFICATION_VALUE : ajaxHeaderIdentificationValue);
        return configuration;
    }

    @Override
    public HttpConfiguration swap(YamlHttpConfiguration yamlConfiguration) {
        HttpConfiguration configuration = new HttpConfiguration();
        String ajaxHeaderIdentificationKey = yamlConfiguration.getAjaxHeaderIdentificationKey();
        configuration.setAjaxHeaderIdentificationKey(ajaxHeaderIdentificationKey == null ? DEFAULT_AJAX_HEADER_IDENTIFICATION_KEY : ajaxHeaderIdentificationKey);
        String ajaxHeaderIdentificationValue = yamlConfiguration.getAjaxHeaderIdentificationValue();
        configuration.setAjaxHeaderIdentificationValue(ajaxHeaderIdentificationValue == null ? DEFAULT_AJAX_HEADER_IDENTIFICATION_VALUE : ajaxHeaderIdentificationValue);
        return configuration;
    }
}