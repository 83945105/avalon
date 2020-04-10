package pub.avalonframework.wechat.official.account.spring.web.service.impl;

import pub.avalonframework.cache.core.Cache;
import pub.avalonframework.cache.core.mgt.EhCacheManager;
import pub.avalonframework.wechat.official.account.core.api.config.WechatOfficialAccountConfiguration;
import pub.avalonframework.wechat.official.account.core.webpage.UserInfoResponse;
import pub.avalonframework.wechat.official.account.core.webpage.WebPageAccessTokenResponse;
import pub.avalonframework.wechat.official.account.spring.web.entity.CacheValue;
import pub.avalonframework.wechat.official.account.spring.web.entity.WebPageAuthorizationResponse;
import pub.avalonframework.wechat.official.account.spring.web.service.WechatOfficialAccountWebPageAuthorizationService;
import pub.avalonframework.wechat.official.account.spring.web.utils.WOAWebUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

/**
 * @author baichao
 */
public class WechatOfficialAccountWebPageAuthorizationServiceImpl implements WechatOfficialAccountWebPageAuthorizationService, InitializingBean {

    @Autowired(required = false)
    protected WechatOfficialAccountConfiguration wechatOfficialAccountConfiguration;

    protected Cache<String, CacheValue> cache;

    @Override
    public String setCache(CacheValue value) {
        String stateKey = UUID.randomUUID().toString().replace("-", "");
        cache.put(stateKey, value);
        return stateKey;
    }

    @Override
    public CacheValue getCache(String key) {
        return cache.get(key);
    }

    @Override
    public Object getOauth2Path(String param, Map<String, String> params, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String oauth2Path;
        if (param == null && params == null) {
            oauth2Path = WOAWebUtils.getInstance(wechatOfficialAccountConfiguration).getAutoAssembleOauth2Path();
        } else if (param != null && params != null) {
            CacheValue value = new CacheValue();
            value.setParam(param);
            value.setParams(params);
            String key = setCache(value);
            oauth2Path = WOAWebUtils.getInstance(wechatOfficialAccountConfiguration).getAutoAssembleOauth2PathWithState(key);
        } else if (param != null) {
            CacheValue value = new CacheValue();
            value.setParam(param);
            String key = setCache(value);
            oauth2Path = WOAWebUtils.getInstance(wechatOfficialAccountConfiguration).getAutoAssembleOauth2PathWithState(key);
        } else {
            CacheValue value = new CacheValue();
            value.setParams(params);
            String key = setCache(value);
            oauth2Path = WOAWebUtils.getInstance(wechatOfficialAccountConfiguration).getAutoAssembleOauth2PathWithState(key);
        }
        return new ResponseEntity<>(oauth2Path, HttpStatus.OK);
    }

    @Override
    public Object getWebPageAccessToken(String code, String key, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WebPageAuthorizationResponse<WebPageAccessTokenResponse> webPageAuthorizationResponse = new WebPageAuthorizationResponse<>();
        webPageAuthorizationResponse.setResponse(WOAWebUtils.getInstance(wechatOfficialAccountConfiguration).getWebPageAccessToken(code));
        if (key != null) {
            webPageAuthorizationResponse.setData(getCache(key));
        }
        return new ResponseEntity<>(webPageAuthorizationResponse, HttpStatus.OK);
    }

    @Override
    public Object getUserInfo(String code, String key, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WebPageAuthorizationResponse<UserInfoResponse> webPageAuthorizationResponse = new WebPageAuthorizationResponse<>();
        WOAWebUtils woaWebUtils = WOAWebUtils.getInstance(wechatOfficialAccountConfiguration);
        WebPageAccessTokenResponse webPageAccessTokenResponse = woaWebUtils.getWebPageAccessToken(code);
        webPageAuthorizationResponse.setResponse(woaWebUtils.getUserInfo(webPageAccessTokenResponse.getAccess_token(), webPageAccessTokenResponse.getOpenid()));
        if (key != null) {
            webPageAuthorizationResponse.setData(getCache(key));
        }
        return new ResponseEntity<>(webPageAuthorizationResponse, HttpStatus.OK);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (wechatOfficialAccountConfiguration != null) {
            cache = new EhCacheManager().createCache(String.class, CacheValue.class, wechatOfficialAccountConfiguration.getWebPageAuthorization().getApiOauth2StateCache());
        }
    }
}