package pub.avalonframework.wechat.official.account.spring.web.service;

import pub.avalonframework.wechat.official.account.spring.web.entity.CacheValue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author baichao
 */
public interface WechatOfficialAccountWebPageAuthorizationService {

    String setCache(CacheValue value);

    CacheValue getCache(String key);

    Object getOauth2Path(String param, Map<String, String> params, HttpServletRequest request, HttpServletResponse response) throws Exception;

    Object getWebPageAccessToken(String code, String key, HttpServletRequest request, HttpServletResponse response) throws Exception;

    Object getUserInfo(String code, String key, HttpServletRequest request, HttpServletResponse response) throws Exception;
}