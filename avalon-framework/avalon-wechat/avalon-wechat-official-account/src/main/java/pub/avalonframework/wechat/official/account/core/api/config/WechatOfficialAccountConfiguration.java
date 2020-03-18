package pub.avalonframework.wechat.official.account.core.api.config;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.InitializingBean;
import pub.avalonframework.common.utils.HttpUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author baichao
 */
public class WechatOfficialAccountConfiguration implements InitializingBean, Runnable {

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

    @Override
    public void afterPropertiesSet() throws Exception {
        Thread thread = new Thread(this, "AccessToken Refresh Guard Thread");
        thread.setDaemon(true);
        thread.start();
    }

    private void autoRefreshAccessTokenRun() throws Exception {
        String accessTokenGetUrl = getAccessTokenGetUrl();
        HttpResponse httpResponse = HttpUtils.getInstance().doGet(accessTokenGetUrl);
        HttpEntity httpEntity = httpResponse.getEntity();
        String body = EntityUtils.toString(httpEntity);
    }

    @Override
    public void run() {
        // 异常线程睡眠时间
        long exceptionSleepMilliSeconds = 10 * 1000L;
        Long autoRefreshAccessTokenInterval = getAutoRefreshAccessTokenInterval();
        autoRefreshAccessTokenInterval = autoRefreshAccessTokenInterval == null ? 2 * 60 * 60 * 1000L : autoRefreshAccessTokenInterval;
        while (true) {
            while (true) {
                try {
                    autoRefreshAccessTokenRun();
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
                try {
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