package pub.avalonframework.wechat.official.account.core.yaml.swapper.impl;

import pub.avalonframework.core.yaml.swapper.YamlSwapper;
import pub.avalonframework.wechat.official.account.core.api.config.Scope;
import pub.avalonframework.wechat.official.account.core.api.config.WebPageAuthorizationConfiguration;
import pub.avalonframework.wechat.official.account.core.yaml.config.YamlWebPageAuthorizationConfiguration;

/**
 * @author baichao
 */
public final class WebPageAuthorizationConfigurationYamlSwapper implements YamlSwapper<YamlWebPageAuthorizationConfiguration, WebPageAuthorizationConfiguration> {

    @Override
    public YamlWebPageAuthorizationConfiguration swap(WebPageAuthorizationConfiguration data) {
        data = data == null ? new WebPageAuthorizationConfiguration() : data;
        YamlWebPageAuthorizationConfiguration configuration = new YamlWebPageAuthorizationConfiguration();
        String oauth2Path = data.getOauth2Path();
        configuration.setOauth2Path(oauth2Path);
        String redirectUri = data.getRedirectUri();
        configuration.setRedirectUri(redirectUri);
        String responseType = data.getResponseType();
        configuration.setResponseType(responseType);
        Scope scope = data.getScope();
        configuration.setScope(scope);
        String state = data.getState();
        configuration.setState(state);
        String accessTokenGetUrl = data.getAccessTokenGetUrl();
        configuration.setAccessTokenGetUrl(accessTokenGetUrl);
        String accessTokenRefreshUrl = data.getAccessTokenRefreshUrl();
        configuration.setAccessTokenRefreshUrl(accessTokenRefreshUrl);
        String userInfoGetUrl = data.getUserInfoGetUrl();
        configuration.setUserInfoGetUrl(userInfoGetUrl);
        String accessTokenValidationUrl = data.getAccessTokenValidationUrl();
        configuration.setAccessTokenValidationUrl(accessTokenValidationUrl);
        return configuration;
    }

    @Override
    public WebPageAuthorizationConfiguration swap(YamlWebPageAuthorizationConfiguration yamlConfiguration) {
        yamlConfiguration = yamlConfiguration == null ? new YamlWebPageAuthorizationConfiguration() : yamlConfiguration;
        WebPageAuthorizationConfiguration configuration = new WebPageAuthorizationConfiguration();
        String oauth2Path = yamlConfiguration.getOauth2Path();
        configuration.setOauth2Path(oauth2Path);
        String redirectUri = yamlConfiguration.getRedirectUri();
        configuration.setRedirectUri(redirectUri);
        String responseType = yamlConfiguration.getResponseType();
        configuration.setResponseType(responseType);
        Scope scope = yamlConfiguration.getScope();
        configuration.setScope(scope);
        String state = yamlConfiguration.getState();
        configuration.setState(state);
        String accessTokenGetUrl = yamlConfiguration.getAccessTokenGetUrl();
        configuration.setAccessTokenGetUrl(accessTokenGetUrl);
        String accessTokenRefreshUrl = yamlConfiguration.getAccessTokenRefreshUrl();
        configuration.setAccessTokenRefreshUrl(accessTokenRefreshUrl);
        String userInfoGetUrl = yamlConfiguration.getUserInfoGetUrl();
        configuration.setUserInfoGetUrl(userInfoGetUrl);
        String accessTokenValidationUrl = yamlConfiguration.getAccessTokenValidationUrl();
        configuration.setAccessTokenValidationUrl(accessTokenValidationUrl);
        return configuration;
    }
}