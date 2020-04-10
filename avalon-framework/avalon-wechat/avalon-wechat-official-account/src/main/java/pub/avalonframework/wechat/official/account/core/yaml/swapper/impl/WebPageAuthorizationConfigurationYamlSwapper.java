package pub.avalonframework.wechat.official.account.core.yaml.swapper.impl;

import pub.avalonframework.core.api.config.EhCacheConfiguration;
import pub.avalonframework.core.yaml.config.YamlEhCacheConfiguration;
import pub.avalonframework.core.yaml.swapper.YamlSwapper;
import pub.avalonframework.core.yaml.swapper.impl.EhCacheConfigurationYamlSwapper;
import pub.avalonframework.wechat.official.account.core.api.config.Scope;
import pub.avalonframework.wechat.official.account.core.api.config.WebPageAuthorizationConfiguration;
import pub.avalonframework.wechat.official.account.core.yaml.config.YamlWebPageAuthorizationConfiguration;

/**
 * @author baichao
 */
public final class WebPageAuthorizationConfigurationYamlSwapper implements YamlSwapper<YamlWebPageAuthorizationConfiguration, WebPageAuthorizationConfiguration> {

    private final static String DEFAULT_API_ROOT_PATH = "/wechat/official/account/web/page/authorization";

    private final static String DEFAULT_API_GET_OAUTH_2_PATH = "/get/oauth2Path";

    private final static String DEFAULT_API_REDIRECT_OAUTH_2_PATH = "/redirect/oauth2Path";

    private final static String DEFAULT_API_ACCEPT_CODE_PATH = "/accept/code";

    private final static String DEFAULT_API_GET_WEB_PAGE_ACCESS_TOKEN_PATH = "/get/webPageAccessToken";

    private final static String DEFAULT_API_GET_USER_INFO_PATH = "/get/userInfo";

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
        String apiRootPath = data.getApiRootPath();
        configuration.setApiRootPath(apiRootPath == null ? DEFAULT_API_ROOT_PATH : apiRootPath);
        String apiGetOauth2Path = data.getApiGetOauth2Path();
        configuration.setApiGetOauth2Path(apiGetOauth2Path == null ? DEFAULT_API_GET_OAUTH_2_PATH : apiGetOauth2Path);
        String apiRedirectOauth2Path = data.getApiRedirectOauth2Path();
        configuration.setApiRedirectOauth2Path(apiRedirectOauth2Path == null ? DEFAULT_API_REDIRECT_OAUTH_2_PATH : apiRedirectOauth2Path);
        String apiAcceptCodePath = data.getApiAcceptCodePath();
        configuration.setApiAcceptCodePath(apiAcceptCodePath == null ? DEFAULT_API_ACCEPT_CODE_PATH : apiAcceptCodePath);
        String apiGetWebPageAccessTokenPath = data.getApiGetWebPageAccessTokenPath();
        configuration.setApiGetWebPageAccessTokenPath(apiGetWebPageAccessTokenPath == null ? DEFAULT_API_GET_WEB_PAGE_ACCESS_TOKEN_PATH : apiGetWebPageAccessTokenPath);
        EhCacheConfiguration apiOauth2StateCache = data.getApiOauth2StateCache();
        configuration.setApiOauth2StateCache(new EhCacheConfigurationYamlSwapper().swap(apiOauth2StateCache));
        String apiGetUserInfoPath = data.getApiGetUserInfoPath();
        configuration.setApiGetUserInfoPath(apiGetUserInfoPath == null ? DEFAULT_API_GET_USER_INFO_PATH : apiGetUserInfoPath);
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
        String apiRootPath = yamlConfiguration.getApiRootPath();
        configuration.setApiRootPath(apiRootPath == null ? DEFAULT_API_ROOT_PATH : apiRootPath);
        String apiGetOauth2Path = yamlConfiguration.getApiGetOauth2Path();
        configuration.setApiGetOauth2Path(apiGetOauth2Path == null ? DEFAULT_API_GET_OAUTH_2_PATH : apiGetOauth2Path);
        String apiRedirectOauth2Path = yamlConfiguration.getApiRedirectOauth2Path();
        configuration.setApiRedirectOauth2Path(apiRedirectOauth2Path == null ? DEFAULT_API_REDIRECT_OAUTH_2_PATH : apiRedirectOauth2Path);
        String apiAcceptCodePath = yamlConfiguration.getApiAcceptCodePath();
        configuration.setApiAcceptCodePath(apiAcceptCodePath == null ? DEFAULT_API_ACCEPT_CODE_PATH : apiAcceptCodePath);
        String apiGetWebPageAccessTokenPath = yamlConfiguration.getApiGetWebPageAccessTokenPath();
        configuration.setApiGetWebPageAccessTokenPath(apiGetWebPageAccessTokenPath == null ? DEFAULT_API_GET_WEB_PAGE_ACCESS_TOKEN_PATH : apiGetWebPageAccessTokenPath);
        YamlEhCacheConfiguration apiOauth2StateCache = yamlConfiguration.getApiOauth2StateCache();
        configuration.setApiOauth2StateCache(new EhCacheConfigurationYamlSwapper().swap(apiOauth2StateCache));
        String apiGetUserInfoPath = yamlConfiguration.getApiGetUserInfoPath();
        configuration.setApiGetUserInfoPath(apiGetUserInfoPath == null ? DEFAULT_API_GET_USER_INFO_PATH : apiGetUserInfoPath);
        return configuration;
    }
}