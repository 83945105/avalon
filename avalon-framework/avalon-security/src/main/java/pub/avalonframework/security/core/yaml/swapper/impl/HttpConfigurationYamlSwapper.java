package pub.avalonframework.security.core.yaml.swapper.impl;

import pub.avalonframework.security.core.api.config.http.HttpConfiguration;
import pub.avalonframework.security.core.yaml.config.http.YamlHttpConfiguration;
import pub.avalonframework.security.core.yaml.swapper.YamlSwapper;

/**
 * @author baichao
 */
public final class HttpConfigurationYamlSwapper implements YamlSwapper<YamlHttpConfiguration, HttpConfiguration> {

    private final static String DEFAULT_SESSION_ID_NAME = "SESSIONID";

    private final static long DEFAULT_SESSION_VALIDATION_INTERVAL = 1000 * 60 * 60;

    private final static long DEFAULT_SESSION_TIME_OUT = 1000 * 60 * 60 * 24;

    private final static long DEFAULT_COOKIE_MAX_AGE = -1;

    private final static String DEFAULT_AJAX_HEADER_IDENTIFICATION_KEY = "X-Requested-With";

    private final static String DEFAULT_AJAX_HEADER_IDENTIFICATION_VALUE = "XMLHttpRequest";

    @Override
    public YamlHttpConfiguration swap(HttpConfiguration data) {
        YamlHttpConfiguration configuration = new YamlHttpConfiguration();
        String sessionIdName = data.getSessionIdName();
        configuration.setSessionIdName(sessionIdName == null ? DEFAULT_SESSION_ID_NAME : sessionIdName);
        Long sessionValidationInterval = data.getSessionValidationInterval();
        configuration.setSessionValidationInterval(sessionValidationInterval == null ? DEFAULT_SESSION_VALIDATION_INTERVAL : sessionValidationInterval);
        Long sessionTimeout = data.getSessionTimeout();
        configuration.setSessionTimeout(sessionTimeout == null ? DEFAULT_SESSION_TIME_OUT : sessionTimeout);
        Long cookieMaxAge = data.getCookieMaxAge();
        configuration.setCookieMaxAge(cookieMaxAge == null ? DEFAULT_COOKIE_MAX_AGE : cookieMaxAge);
        String ajaxHeaderIdentificationKey = data.getAjaxHeaderIdentificationKey();
        configuration.setAjaxHeaderIdentificationKey(ajaxHeaderIdentificationKey == null ? DEFAULT_AJAX_HEADER_IDENTIFICATION_KEY : ajaxHeaderIdentificationKey);
        String ajaxHeaderIdentificationValue = data.getAjaxHeaderIdentificationValue();
        configuration.setAjaxHeaderIdentificationValue(ajaxHeaderIdentificationValue == null ? DEFAULT_AJAX_HEADER_IDENTIFICATION_VALUE : ajaxHeaderIdentificationValue);
        return configuration;
    }

    @Override
    public HttpConfiguration swap(YamlHttpConfiguration yamlConfiguration) {
        HttpConfiguration configuration = new HttpConfiguration();
        String sessionIdName = yamlConfiguration.getSessionIdName();
        configuration.setSessionIdName(sessionIdName == null ? DEFAULT_SESSION_ID_NAME : sessionIdName);
        Long sessionValidationInterval = yamlConfiguration.getSessionValidationInterval();
        configuration.setSessionValidationInterval(sessionValidationInterval == null ? DEFAULT_SESSION_VALIDATION_INTERVAL : sessionValidationInterval);
        Long sessionTimeout = yamlConfiguration.getSessionTimeout();
        configuration.setSessionTimeout(sessionTimeout == null ? DEFAULT_SESSION_TIME_OUT : sessionTimeout);
        Long cookieMaxAge = yamlConfiguration.getCookieMaxAge();
        configuration.setCookieMaxAge(cookieMaxAge == null ? DEFAULT_COOKIE_MAX_AGE : cookieMaxAge);
        String ajaxHeaderIdentificationKey = yamlConfiguration.getAjaxHeaderIdentificationKey();
        configuration.setAjaxHeaderIdentificationKey(ajaxHeaderIdentificationKey == null ? DEFAULT_AJAX_HEADER_IDENTIFICATION_KEY : ajaxHeaderIdentificationKey);
        String ajaxHeaderIdentificationValue = yamlConfiguration.getAjaxHeaderIdentificationValue();
        configuration.setAjaxHeaderIdentificationValue(ajaxHeaderIdentificationValue == null ? DEFAULT_AJAX_HEADER_IDENTIFICATION_VALUE : ajaxHeaderIdentificationValue);
        return configuration;
    }
}