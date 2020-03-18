package pub.avalonframework.wechat.official.account.core.yaml.swapper.impl;

import pub.avalonframework.core.yaml.swapper.YamlSwapper;
import pub.avalonframework.wechat.official.account.core.api.config.WebPageAuthorizationConfiguration;
import pub.avalonframework.wechat.official.account.core.api.config.WechatOfficialAccountConfiguration;
import pub.avalonframework.wechat.official.account.core.yaml.config.YamlWebPageAuthorizationConfiguration;
import pub.avalonframework.wechat.official.account.core.yaml.config.YamlWechatOfficialAccountConfiguration;

/**
 * @author baichao
 */
public final class WechatOfficialAccountConfigurationYamlSwapper implements YamlSwapper<YamlWechatOfficialAccountConfiguration, WechatOfficialAccountConfiguration> {

    private final static Boolean DEFAULT_ENABLED = Boolean.FALSE;

    private final static Boolean DEFAULT_AUTO_REFRESH_ACCESS_TOKEN = Boolean.TRUE;

    private final static Long DEFAULT_AUTO_REFRESH_ACCESS_TOKEN_INTERVAL = 2 * 60 * 60 * 1000L;

    private final static String DEFAULT_TOKEN = "WECHAT-OFFICIAL-ACCOUNT-TOKEN";

    private final static String DEFAULT_API_ENTRANCE_ROOT_PATH = "/wechat/official/account";

    private final static String DEFAULT_API_ENTRANCE_SUB_PATH = "/index";

    @Override
    public YamlWechatOfficialAccountConfiguration swap(WechatOfficialAccountConfiguration data) {
        data = data == null ? new WechatOfficialAccountConfiguration() : data;
        YamlWechatOfficialAccountConfiguration configuration = new YamlWechatOfficialAccountConfiguration();
        Boolean enabled = data.getEnabled();
        configuration.setEnabled(enabled == null ? DEFAULT_ENABLED : enabled);
        String appId = data.getAppId();
        configuration.setAppId(appId);
        String secret = data.getSecret();
        configuration.setSecret(secret);
        String accessTokenGetUrl = data.getAccessTokenGetUrl();
        configuration.setAccessTokenGetUrl(accessTokenGetUrl);
        Boolean autoRefreshAccessToken = data.getAutoRefreshAccessToken();
        configuration.setAutoRefreshAccessToken(autoRefreshAccessToken == null ? DEFAULT_AUTO_REFRESH_ACCESS_TOKEN : autoRefreshAccessToken);
        Long autoRefreshAccessTokenInterval = data.getAutoRefreshAccessTokenInterval();
        configuration.setAutoRefreshAccessTokenInterval(autoRefreshAccessTokenInterval == null ? DEFAULT_AUTO_REFRESH_ACCESS_TOKEN_INTERVAL : autoRefreshAccessTokenInterval);
        String token = data.getToken();
        configuration.setToken(token == null ? DEFAULT_TOKEN : token);
        String apiEntranceRootPath = data.getApiEntranceRootPath();
        configuration.setApiEntranceRootPath(apiEntranceRootPath == null ? DEFAULT_API_ENTRANCE_ROOT_PATH : apiEntranceRootPath);
        String apiEntranceSubPath = data.getApiEntranceSubPath();
        configuration.setApiEntranceSubPath(apiEntranceSubPath == null ? DEFAULT_API_ENTRANCE_SUB_PATH : apiEntranceSubPath);
        WebPageAuthorizationConfiguration webPageAuthorization = data.getWebPageAuthorization();
        configuration.setWebPageAuthorization(new WebPageAuthorizationConfigurationYamlSwapper().swap(webPageAuthorization));
        return configuration;
    }

    @Override
    public WechatOfficialAccountConfiguration swap(YamlWechatOfficialAccountConfiguration yamlConfiguration) {
        yamlConfiguration = yamlConfiguration == null ? new YamlWechatOfficialAccountConfiguration() : yamlConfiguration;
        WechatOfficialAccountConfiguration configuration = new WechatOfficialAccountConfiguration();
        Boolean enabled = yamlConfiguration.getEnabled();
        configuration.setEnabled(enabled == null ? DEFAULT_ENABLED : enabled);
        String appId = yamlConfiguration.getAppId();
        configuration.setAppId(appId);
        String secret = yamlConfiguration.getSecret();
        configuration.setSecret(secret);
        String accessTokenGetUrl = yamlConfiguration.getAccessTokenGetUrl();
        configuration.setAccessTokenGetUrl(accessTokenGetUrl);
        Boolean autoRefreshAccessToken = yamlConfiguration.getAutoRefreshAccessToken();
        configuration.setAutoRefreshAccessToken(autoRefreshAccessToken == null ? DEFAULT_AUTO_REFRESH_ACCESS_TOKEN : autoRefreshAccessToken);
        Long autoRefreshAccessTokenInterval = yamlConfiguration.getAutoRefreshAccessTokenInterval();
        configuration.setAutoRefreshAccessTokenInterval(autoRefreshAccessTokenInterval == null ? DEFAULT_AUTO_REFRESH_ACCESS_TOKEN_INTERVAL : autoRefreshAccessTokenInterval);
        String token = yamlConfiguration.getToken();
        configuration.setToken(token == null ? DEFAULT_TOKEN : token);
        String apiEntranceRootPath = yamlConfiguration.getApiEntranceRootPath();
        configuration.setApiEntranceRootPath(apiEntranceRootPath == null ? DEFAULT_API_ENTRANCE_ROOT_PATH : apiEntranceRootPath);
        String apiEntranceSubPath = yamlConfiguration.getApiEntranceSubPath();
        configuration.setApiEntranceSubPath(apiEntranceSubPath == null ? DEFAULT_API_ENTRANCE_SUB_PATH : apiEntranceSubPath);
        YamlWebPageAuthorizationConfiguration webPageAuthorization = yamlConfiguration.getWebPageAuthorization();
        configuration.setWebPageAuthorization(new WebPageAuthorizationConfigurationYamlSwapper().swap(webPageAuthorization));
        return configuration;
    }
}