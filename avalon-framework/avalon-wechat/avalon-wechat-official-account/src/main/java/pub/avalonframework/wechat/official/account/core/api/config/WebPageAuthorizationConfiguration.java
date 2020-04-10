package pub.avalonframework.wechat.official.account.core.api.config;

import pub.avalonframework.core.api.config.EhCacheConfiguration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author baichao
 */
public class WebPageAuthorizationConfiguration {

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

    private String apiRedirectOauth2Path;

    private String apiAcceptCodePath;

    private String apiGetWebPageAccessTokenPath;

    private String apiGetUserInfoPath;

    private EhCacheConfiguration apiOauth2StateCache;

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

    public String getApiRedirectOauth2Path() {
        return apiRedirectOauth2Path;
    }

    public void setApiRedirectOauth2Path(String apiRedirectOauth2Path) {
        this.apiRedirectOauth2Path = apiRedirectOauth2Path;
    }

    public String getApiAcceptCodePath() {
        return apiAcceptCodePath;
    }

    public void setApiAcceptCodePath(String apiAcceptCodePath) {
        this.apiAcceptCodePath = apiAcceptCodePath;
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

    public EhCacheConfiguration getApiOauth2StateCache() {
        return apiOauth2StateCache;
    }

    public void setApiOauth2StateCache(EhCacheConfiguration apiOauth2StateCache) {
        this.apiOauth2StateCache = apiOauth2StateCache;
    }

    public String getAutoAssembleOauth2PathWithState(String state) {
        StringBuilder sb = new StringBuilder(oauth2Path);
        String redirectUri = getURLEncoderRedirectUri();
        if (redirectUri != null && !redirectUri.isEmpty()) {
            sb.append("&redirect_uri=").append(redirectUri);
        }
        String responseType = getResponseType();
        if (responseType != null && !responseType.isEmpty()) {
            sb.append("&response_type=").append(responseType);
        }
        Scope scope = getScope();
        if (scope != null) {
            sb.append("&scope=").append(scope.name());
        }
        if (state != null && !state.isEmpty()) {
            sb.append("&state=").append(state);
        }
        sb.append("#wechat_redirect");
        return sb.toString();
    }

    public String getAutoAssembleOauth2Path() {
        StringBuilder sb = new StringBuilder(oauth2Path);
        String redirectUri = getURLEncoderRedirectUri();
        if (redirectUri != null && !redirectUri.isEmpty()) {
            sb.append("&redirect_uri=").append(redirectUri);
        }
        String responseType = getResponseType();
        if (responseType != null && !responseType.isEmpty()) {
            sb.append("&response_type=").append(responseType);
        }
        Scope scope = getScope();
        if (scope != null) {
            sb.append("&scope=").append(scope.name());
        }
        String state = getState();
        if (state != null && !state.isEmpty()) {
            sb.append("&state=").append(state);
        }
        sb.append("#wechat_redirect");
        return sb.toString();
    }

    public String getURLEncoderRedirectUri() {
        try {
            return URLEncoder.encode(redirectUri, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getAutoAssembleAccessTokenGetUrlWithCode(String code) {
        return getAutoAssembleAccessTokenGetUrlWithAppIdAndSecretAndCode(null, null, code);
    }

    public String getAutoAssembleAccessTokenGetUrlWithWechatOfficialAccountConfigurationAndCode(WechatOfficialAccountConfiguration wechatOfficialAccountConfiguration, String code) {
        return getAutoAssembleAccessTokenGetUrlWithAppIdAndSecretAndCode(wechatOfficialAccountConfiguration.getAppId(), wechatOfficialAccountConfiguration.getSecret(), code);
    }

    public String getAutoAssembleAccessTokenGetUrlWithAppIdAndSecretAndCode(String appId, String secret, String code) {
        StringBuilder sb = new StringBuilder(accessTokenGetUrl);
        if (!accessTokenGetUrl.contains("?")) {
            sb.append("?");
        }
        if (!accessTokenGetUrl.contains("appid=") && appId != null && !appId.isEmpty()) {
            sb.append("&appid=").append(appId);
        }
        if (!accessTokenGetUrl.contains("secret=") && secret != null && !secret.isEmpty()) {
            sb.append("&secret=").append(secret);
        }
        if (!accessTokenGetUrl.contains("code=") && code != null && !code.isEmpty()) {
            sb.append("&code=").append(code);
        }
        if (!accessTokenGetUrl.contains("grant_type=")) {
            sb.append("&grant_type=").append("authorization_code");
        }
        return sb.toString();
    }

    public String getAutoAssembleAccessTokenRefreshUrlWithRefreshToken(String refreshToken) {
        return getAutoAssembleAccessTokenRefreshUrlWithAppIdAndRefreshToken(null, refreshToken);
    }

    public String getAutoAssembleAccessTokenRefreshUrlWithWechatOfficialAccountConfigurationAndRefreshToken(WechatOfficialAccountConfiguration wechatOfficialAccountConfiguration, String refreshToken) {
        return getAutoAssembleAccessTokenRefreshUrlWithAppIdAndRefreshToken(wechatOfficialAccountConfiguration.getAppId(), refreshToken);
    }

    public String getAutoAssembleAccessTokenRefreshUrlWithAppIdAndRefreshToken(String appId, String refreshToken) {
        StringBuilder sb = new StringBuilder(accessTokenRefreshUrl);
        if (!accessTokenRefreshUrl.contains("?")) {
            sb.append("?");
        }
        if (!accessTokenRefreshUrl.contains("appid=") && appId != null && !appId.isEmpty()) {
            sb.append("&appid=").append(appId);
        }
        if (!accessTokenRefreshUrl.contains("refresh_token=") && refreshToken != null && !refreshToken.isEmpty()) {
            sb.append("&refresh_token=").append(refreshToken);
        }
        if (!accessTokenRefreshUrl.contains("grant_type=")) {
            sb.append("&grant_type=").append("refresh_token");
        }
        return sb.toString();
    }

    public String getAutoAssembleUserInfoGetUrlWithAccessTokenAndOpenId(String accessToken, String openId) {
        StringBuilder sb = new StringBuilder(userInfoGetUrl);
        if (!userInfoGetUrl.contains("?")) {
            sb.append("?");
        }
        if (!userInfoGetUrl.contains("access_token=") && accessToken != null && !accessToken.isEmpty()) {
            sb.append("&access_token=").append(accessToken);
        }
        if (!userInfoGetUrl.contains("openid=") && openId != null && !openId.isEmpty()) {
            sb.append("&openid=").append(openId);
        }
        if (!userInfoGetUrl.contains("lang=")) {
            sb.append("&lang=").append("zh_CN");
        }
        return sb.toString();
    }

    public String getAutoAssembleAccessTokenValidationUrlWithAccessTokenAndOpenId(String accessToken, String openId) {
        StringBuilder sb = new StringBuilder(accessTokenValidationUrl);
        if (!accessTokenValidationUrl.contains("?")) {
            sb.append("?");
        }
        if (!accessTokenValidationUrl.contains("access_token=") && accessToken != null && !accessToken.isEmpty()) {
            sb.append("&access_token=").append(accessToken);
        }
        if (!accessTokenValidationUrl.contains("openid=") && openId != null && !openId.isEmpty()) {
            sb.append("&openid=").append(openId);
        }
        return sb.toString();
    }
}