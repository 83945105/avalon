package pub.avalonframework.wechat.official.account.core.api.config;

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

    public String getOauth2Path() {
        StringBuilder sb = new StringBuilder(oauth2Path);
        String redirectUri = getRedirectUri();
        if (redirectUri != null && !redirectUri.isEmpty()) {
            sb.append("&redirect_uri=").append(redirectUri);
        }
        String responseType = getResponseType();
        if (responseType != null && !responseType.isEmpty()) {
            sb.append("&response_type=").append(responseType);
        }
        Scope scope = getScope();
        if (scope != null) {
            sb.append("&scope").append(scope.name());
        }
        String state = getState();
        if (state != null && !state.isEmpty()) {
            sb.append("&state").append(state);
        }
        sb.append("#wechat_redirect");
        return sb.toString();
    }

    public void setOauth2Path(String oauth2Path) {
        this.oauth2Path = oauth2Path;
    }

    public String getRedirectUri() {
        try {
            return URLEncoder.encode(redirectUri, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
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

    public String getAccessTokenGetUrl(String code) {
        return getAccessTokenGetUrl(null, null, code);
    }

    public String getAccessTokenGetUrl(WechatOfficialAccountConfiguration wechatOfficialAccountConfiguration, String code) {
        return getAccessTokenGetUrl(wechatOfficialAccountConfiguration.getAppId(), wechatOfficialAccountConfiguration.getSecret(), code);
    }

    public String getAccessTokenGetUrl(String appId, String secret, String code) {
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

    public void setAccessTokenGetUrl(String accessTokenGetUrl) {
        this.accessTokenGetUrl = accessTokenGetUrl;
    }

    public String getAccessTokenRefreshUrl() {
        return accessTokenRefreshUrl;
    }

    public String getAccessTokenRefreshUrl(String refreshToken) {
        return getAccessTokenRefreshUrl((String) null, refreshToken);
    }

    public String getAccessTokenRefreshUrl(WechatOfficialAccountConfiguration wechatOfficialAccountConfiguration, String refreshToken) {
        return getAccessTokenRefreshUrl(wechatOfficialAccountConfiguration.getAppId(), refreshToken);
    }

    public String getAccessTokenRefreshUrl(String appId, String refreshToken) {
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

    public void setAccessTokenRefreshUrl(String accessTokenRefreshUrl) {
        this.accessTokenRefreshUrl = accessTokenRefreshUrl;
    }

    public String getUserInfoGetUrl() {
        return userInfoGetUrl;
    }

    public String getUserInfoGetUrl(String accessToken, String openId) {
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

    public void setUserInfoGetUrl(String userInfoGetUrl) {
        this.userInfoGetUrl = userInfoGetUrl;
    }

    public String getAccessTokenValidationUrl() {
        return accessTokenValidationUrl;
    }

    public String getAccessTokenValidationUrl(String accessToken, String openId) {
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

    public void setAccessTokenValidationUrl(String accessTokenValidationUrl) {
        this.accessTokenValidationUrl = accessTokenValidationUrl;
    }
}