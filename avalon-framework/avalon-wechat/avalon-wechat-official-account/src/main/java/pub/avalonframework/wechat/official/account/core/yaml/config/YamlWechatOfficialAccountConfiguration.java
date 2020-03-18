package pub.avalonframework.wechat.official.account.core.yaml.config;

import pub.avalonframework.core.yaml.config.YamlConfiguration;

/**
 * @author baichao
 */
public class YamlWechatOfficialAccountConfiguration implements YamlConfiguration {

    private Boolean enabled;

    private String appId;

    private String secret;

    private String accessTokenGetUrl;

    private Boolean autoRefreshAccessToken;

    private Long autoRefreshAccessTokenInterval;

    private String token;

    private String entranceRootPath;

    private String entranceSubPath;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getAccessTokenGetUrl() {
        return accessTokenGetUrl;
    }

    public void setAccessTokenGetUrl(String accessTokenGetUrl) {
        this.accessTokenGetUrl = accessTokenGetUrl;
    }

    public Boolean getAutoRefreshAccessToken() {
        return autoRefreshAccessToken;
    }

    public void setAutoRefreshAccessToken(Boolean autoRefreshAccessToken) {
        this.autoRefreshAccessToken = autoRefreshAccessToken;
    }

    public Long getAutoRefreshAccessTokenInterval() {
        return autoRefreshAccessTokenInterval;
    }

    public void setAutoRefreshAccessTokenInterval(Long autoRefreshAccessTokenInterval) {
        this.autoRefreshAccessTokenInterval = autoRefreshAccessTokenInterval;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEntranceRootPath() {
        return entranceRootPath;
    }

    public void setEntranceRootPath(String entranceRootPath) {
        this.entranceRootPath = entranceRootPath;
    }

    public String getEntranceSubPath() {
        return entranceSubPath;
    }

    public void setEntranceSubPath(String entranceSubPath) {
        this.entranceSubPath = entranceSubPath;
    }
}