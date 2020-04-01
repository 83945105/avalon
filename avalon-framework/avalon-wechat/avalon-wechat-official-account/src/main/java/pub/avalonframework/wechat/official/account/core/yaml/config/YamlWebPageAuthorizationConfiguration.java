package pub.avalonframework.wechat.official.account.core.yaml.config;

import pub.avalonframework.core.yaml.config.YamlConfiguration;
import pub.avalonframework.core.yaml.config.YamlEhCacheConfiguration;
import pub.avalonframework.wechat.official.account.core.api.config.Scope;

/**
 * @author baichao
 */
public class YamlWebPageAuthorizationConfiguration implements YamlConfiguration {

    private String oauth2Path;

    private String redirectUri;

    private String responseType;

    private Scope scope;

    private String state;

    private String accessTokenGetUrl;

    private String accessTokenRefreshUrl;

    private String userInfoGetUrl;

    private String accessTokenValidationUrl;

    private String apiRootPath;

    private String apiGetOauth2Path;

    private String apiGetWebPageAccessTokenPath;

    private String apiGetUserInfoPath;

    private YamlEhCacheConfiguration apiOauth2StateCache;

    public String getOauth2Path() {
        return oauth2Path;
    }

    public void setOauth2Path(String oauth2Path) {
        this.oauth2Path = oauth2Path;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAccessTokenGetUrl() {
        return accessTokenGetUrl;
    }

    public void setAccessTokenGetUrl(String accessTokenGetUrl) {
        this.accessTokenGetUrl = accessTokenGetUrl;
    }

    public String getAccessTokenRefreshUrl() {
        return accessTokenRefreshUrl;
    }

    public void setAccessTokenRefreshUrl(String accessTokenRefreshUrl) {
        this.accessTokenRefreshUrl = accessTokenRefreshUrl;
    }

    public String getUserInfoGetUrl() {
        return userInfoGetUrl;
    }

    public void setUserInfoGetUrl(String userInfoGetUrl) {
        this.userInfoGetUrl = userInfoGetUrl;
    }

    public String getAccessTokenValidationUrl() {
        return accessTokenValidationUrl;
    }

    public void setAccessTokenValidationUrl(String accessTokenValidationUrl) {
        this.accessTokenValidationUrl = accessTokenValidationUrl;
    }

    public String getApiRootPath() {
        return apiRootPath;
    }

    public void setApiRootPath(String apiRootPath) {
        this.apiRootPath = apiRootPath;
    }

    public String getApiGetOauth2Path() {
        return apiGetOauth2Path;
    }

    public void setApiGetOauth2Path(String apiGetOauth2Path) {
        this.apiGetOauth2Path = apiGetOauth2Path;
    }

    public String getApiGetWebPageAccessTokenPath() {
        return apiGetWebPageAccessTokenPath;
    }

    public void setApiGetWebPageAccessTokenPath(String apiGetWebPageAccessTokenPath) {
        this.apiGetWebPageAccessTokenPath = apiGetWebPageAccessTokenPath;
    }

    public String getApiGetUserInfoPath() {
        return apiGetUserInfoPath;
    }

    public void setApiGetUserInfoPath(String apiGetUserInfoPath) {
        this.apiGetUserInfoPath = apiGetUserInfoPath;
    }

    public YamlEhCacheConfiguration getApiOauth2StateCache() {
        return apiOauth2StateCache;
    }

    public void setApiOauth2StateCache(YamlEhCacheConfiguration apiOauth2StateCache) {
        this.apiOauth2StateCache = apiOauth2StateCache;
    }
}