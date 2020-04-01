package pub.avalonframework.wechat.official.account.spring.web.service.impl;

import pub.avalonframework.cache.core.Cache;
import pub.avalonframework.cache.core.mgt.EhCacheManager;
import pub.avalonframework.wechat.official.account.core.UserInfoResponse;
import pub.avalonframework.wechat.official.account.core.WebPageAccessTokenResponse;
import pub.avalonframework.wechat.official.account.core.api.config.WechatOfficialAccountConfiguration;
import pub.avalonframework.wechat.official.account.spring.web.entity.WebPageAuthorizationResponse;
import pub.avalonframework.wechat.official.account.spring.web.service.WechatOfficialAccountWebPageAuthorizationService;
import pub.avalonframework.wechat.official.account.spring.web.utils.WOAWebUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author baichao
 */
public class WechatOfficialAccountWebPageAuthorizationServiceImpl implements WechatOfficialAccountWebPageAuthorizationService, InitializingBean {

    @Autowired(required = false)
    protected WechatOfficialAccountConfiguration wechatOfficialAccountConfiguration;

    protected Cache<String, String> oauth2StateCache;

    @Override
    public Object getOauth2Path(String state, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String oauthPath;
        if (state == null) {
            oauthPath = WOAWebUtils.getInstance(wechatOfficialAccountConfiguration).getOAuth2Path();
        } else {
            String stateKey = UUID.randomUUID().toString().replace("-", "");
            oauth2StateCache.put(stateKey, state);
            oauthPath = WOAWebUtils.getInstance(wechatOfficialAccountConfiguration).getOauth2PathWithState(stateKey);
        }
        return new ResponseEntity<>(oauthPath, HttpStatus.OK);
    }

    @Override
    public Object getWebPageAccessToken(String code, String stateKey, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WebPageAuthorizationResponse<WebPageAccessTokenResponse> webPageAuthorizationResponse = new WebPageAuthorizationResponse<>();
        webPageAuthorizationResponse.setResponse(WOAWebUtils.getInstance(wechatOfficialAccountConfiguration).getWebPageAccessToken(code));
        if (stateKey != null) {
            webPageAuthorizationResponse.setState(oauth2StateCache.get(stateKey));
        }
        return new ResponseEntity<>(webPageAuthorizationResponse, HttpStatus.OK);
    }

    @Override
    public Object getUserInfo(String code, String stateKey, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WebPageAuthorizationResponse<UserInfoResponse> webPageAuthorizationResponse = new WebPageAuthorizationResponse<>();
        WOAWebUtils woaWebUtils = WOAWebUtils.getInstance(wechatOfficialAccountConfiguration);
        WebPageAccessTokenResponse webPageAccessTokenResponse = woaWebUtils.getWebPageAccessToken(code);
        webPageAuthorizationResponse.setResponse(woaWebUtils.getUserInfo(webPageAccessTokenResponse.getAccess_token(), webPageAccessTokenResponse.getOpenid()));
        if (stateKey != null) {
            webPageAuthorizationResponse.setState(oauth2StateCache.get(stateKey));
        }
        return new ResponseEntity<>(webPageAuthorizationResponse, HttpStatus.OK);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (wechatOfficialAccountConfiguration != null) {
            oauth2StateCache = new EhCacheManager().createCache(String.class, String.class, wechatOfficialAccountConfiguration.getWebPageAuthorization().getApiOauth2StateCache());
        }
    }
}