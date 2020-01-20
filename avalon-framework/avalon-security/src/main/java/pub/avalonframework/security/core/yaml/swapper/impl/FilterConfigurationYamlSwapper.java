package pub.avalonframework.security.core.yaml.swapper.impl;

import pub.avalonframework.core.yaml.swapper.YamlSwapper;
import pub.avalonframework.security.core.api.config.FilterConfiguration;
import pub.avalonframework.security.core.yaml.config.YamlFilterConfiguration;

import java.util.Collections;
import java.util.Set;

/**
 * @author baichao
 */
public final class FilterConfigurationYamlSwapper implements YamlSwapper<YamlFilterConfiguration, FilterConfiguration> {

    private final static String DEFAULT_LOGIN_URL = "/login";

    private final static String DEFAULT_UNAUTHORIZED_URL = "/unauthorized";

    private final static String DEFAULT_FORM_FILTER_NAME = "/authc";

    @Override
    public YamlFilterConfiguration swap(FilterConfiguration data) {
        YamlFilterConfiguration configuration = new YamlFilterConfiguration();
        String loginUrl = data.getLoginUrl();
        configuration.setLoginUrl(loginUrl == null ? DEFAULT_LOGIN_URL : loginUrl);
        String unauthorizedUrl = data.getUnauthorizedUrl();
        configuration.setUnauthorizedUrl(unauthorizedUrl == null ? DEFAULT_UNAUTHORIZED_URL : unauthorizedUrl);
        String formFilterName = data.getFormFilterName();
        configuration.setFormFilterName(formFilterName == null ? DEFAULT_FORM_FILTER_NAME : formFilterName);
        Set<String> releaseResources = data.getReleaseResources();
        configuration.setReleaseResources(releaseResources == null ? Collections.emptySet() : releaseResources);
        return configuration;
    }

    @Override
    public FilterConfiguration swap(YamlFilterConfiguration yamlConfiguration) {
        FilterConfiguration configuration = new FilterConfiguration();
        String loginUrl = yamlConfiguration.getLoginUrl();
        configuration.setLoginUrl(loginUrl == null ? DEFAULT_LOGIN_URL : loginUrl);
        String unauthorizedUrl = yamlConfiguration.getUnauthorizedUrl();
        configuration.setUnauthorizedUrl(unauthorizedUrl == null ? DEFAULT_UNAUTHORIZED_URL : unauthorizedUrl);
        String formFilterName = yamlConfiguration.getFormFilterName();
        configuration.setFormFilterName(formFilterName == null ? DEFAULT_FORM_FILTER_NAME : formFilterName);
        Set<String> releaseResources = yamlConfiguration.getReleaseResources();
        configuration.setReleaseResources(releaseResources == null ? Collections.emptySet() : releaseResources);
        return configuration;
    }
}