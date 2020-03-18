package pub.avalonframework.wechat.official.account.core.api.config;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import pub.avalonframework.common.utils.HttpUtils;
import pub.avalonframework.wechat.official.account.core.AccessTokenResponse;
import pub.avalonframework.wechat.official.account.core.utils.ResponseUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author baichao
 */
public class WechatOfficialAccountConfiguration implements InitializingBean, Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatOfficialAccountConfiguration.class);

    private Boolean enabled;

    private String appId;

    private String secret;

    private String accessTokenGetUrl;

    private Boolean autoRefreshAccessToken;

    private Long autoRefreshAccessTokenInterval;

    private String token;

    private String apiEntranceRootPath;

    private String apiEntranceSubPath;

    private WebPageAuthorizationConfiguration webPageAuthorization;

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
        StringBuilder sb = new StringBuilder(accessTokenGetUrl);
        if (!accessTokenGetUrl.contains("?")) {
            sb.append("?");
        }
        String appId = getAppId();
        if (!accessTokenGetUrl.contains("appid=") && appId != null && !appId.isEmpty()) {
            sb.append("&appid=").append(appId);
        }
        String secret = getSecret();
        if (!accessTokenGetUrl.contains("secret=") && secret != null && !secret.isEmpty()) {
            sb.append("&secret=").append(secret);
        }
        return sb.toString();
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

    public String getApiEntranceRootPath() {
        return apiEntranceRootPath;
    }

    public void setApiEntranceRootPath(String apiEntranceRootPath) {
        this.apiEntranceRootPath = apiEntranceRootPath;
    }

    public String getApiEntranceSubPath() {
        return apiEntranceSubPath;
    }

    public void setApiEntranceSubPath(String apiEntranceSubPath) {
        this.apiEntranceSubPath = apiEntranceSubPath;
    }

    public WebPageAuthorizationConfiguration getWebPageAuthorization() {
        return webPageAuthorization;
    }

    public void setWebPageAuthorization(WebPageAuthorizationConfiguration webPageAuthorization) {
        this.webPageAuthorization = webPageAuthorization;
    }

    @Override
    public void afterPropertiesSet() {
        Thread thread = new Thread(this, "AccessToken Refresh Guard Thread");
        thread.setDaemon(true);
        thread.start();
    }

    private void autoRefreshAccessTokenRun() throws Exception {
        String accessTokenGetUrl = getAccessTokenGetUrl();
        HttpResponse httpResponse = HttpUtils.getInstance().doGet(accessTokenGetUrl);
        HttpEntity httpEntity = httpResponse.getEntity();
        String body = EntityUtils.toString(httpEntity);
        AccessTokenResponse accessTokenResponse = ResponseUtils.parseAccessTokenResponse(body);
        setToken(accessTokenResponse.getAccess_token());
        setAutoRefreshAccessTokenInterval(accessTokenResponse.getExpires_in() * 1000L);
        LOGGER.info("Get access_token success, expires in {} seconds, access_token: {}", accessTokenResponse.getExpires_in(), accessTokenResponse.getAccess_token());
    }

    @Override
    public void run() {
        // 异常线程睡眠时间
        long exceptionSleepMilliSeconds = 10 * 1000L;
        while (true) {
            while (true) {
                try {
                    autoRefreshAccessTokenRun();
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
                try {
                    Long autoRefreshAccessTokenInterval = getAutoRefreshAccessTokenInterval();
                    autoRefreshAccessTokenInterval = autoRefreshAccessTokenInterval == null ? 2 * 60 * 60 * 1000L : autoRefreshAccessTokenInterval;
                    TimeUnit.MILLISECONDS.sleep(autoRefreshAccessTokenInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(exceptionSleepMilliSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}