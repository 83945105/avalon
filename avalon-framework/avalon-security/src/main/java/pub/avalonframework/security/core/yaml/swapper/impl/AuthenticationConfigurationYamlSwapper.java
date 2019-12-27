package pub.avalonframework.security.core.yaml.swapper.impl;

import pub.avalonframework.security.core.api.config.authentication.AuthenticationConfiguration;
import pub.avalonframework.security.core.yaml.config.authentication.YamlAuthenticationConfiguration;
import pub.avalonframework.security.core.yaml.swapper.YamlSwapper;

/**
 * @author baichao
 */
public final class AuthenticationConfigurationYamlSwapper implements YamlSwapper<YamlAuthenticationConfiguration, AuthenticationConfiguration> {

    private final static String DEFAULT_USERNAME_KEY = "username";

    private final static String DEFAULT_PASSWORD_KEY = "password";

    private final static String DEFAULT_URL = "/login";

    private final static String DEFAULT_PAGE_URL = "/page/login";

    private final static String DEFAULT_SUCCESS_URL = "/home";

    @Override
    public YamlAuthenticationConfiguration swap(AuthenticationConfiguration data) {
        YamlAuthenticationConfiguration configuration = new YamlAuthenticationConfiguration();
        String usernameKey = data.getUsernameKey();
        configuration.setUsernameKey(usernameKey == null ? DEFAULT_USERNAME_KEY : usernameKey);
        String passwordKey = data.getPasswordKey();
        configuration.setPasswordKey(passwordKey == null ? DEFAULT_PASSWORD_KEY : passwordKey);
        String url = data.getUrl();
        configuration.setUrl(url == null ? DEFAULT_URL : url);
        String pageUrl = data.getPageUrl();
        configuration.setPageUrl(pageUrl == null ? DEFAULT_PAGE_URL : pageUrl);
        String successUrl = data.getSuccessUrl();
        configuration.setSuccessUrl(successUrl == null ? DEFAULT_SUCCESS_URL : successUrl);
        return configuration;
    }

    @Override
    public AuthenticationConfiguration swap(YamlAuthenticationConfiguration yamlConfiguration) {
        AuthenticationConfiguration configuration = new AuthenticationConfiguration();
        String usernameKey = yamlConfiguration.getUsernameKey();
        configuration.setUsernameKey(usernameKey == null ? DEFAULT_USERNAME_KEY : usernameKey);
        String passwordKey = yamlConfiguration.getPasswordKey();
        configuration.setPasswordKey(passwordKey == null ? DEFAULT_PASSWORD_KEY : passwordKey);
        String url = yamlConfiguration.getUrl();
        configuration.setUrl(url == null ? DEFAULT_URL : url);
        String pageUrl = yamlConfiguration.getPageUrl();
        configuration.setPageUrl(pageUrl == null ? DEFAULT_PAGE_URL : pageUrl);
        String successUrl = yamlConfiguration.getSuccessUrl();
        configuration.setSuccessUrl(successUrl == null ? DEFAULT_SUCCESS_URL : successUrl);
        return configuration;
    }
}