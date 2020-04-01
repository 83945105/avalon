package pub.avalonframework.wechat.official.account.spring.web.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author baichao
 */
public interface WechatOfficialAccountWebPageAuthorizationService {

    Object getOauth2Path(String state, HttpServletRequest request, HttpServletResponse response) throws Exception;

    Object getWebPageAccessToken(String code, String stateKey, HttpServletRequest request, HttpServletResponse response) throws Exception;

    Object getUserInfo(String code, String stateKey, HttpServletRequest request, HttpServletResponse response) throws Exception;
}