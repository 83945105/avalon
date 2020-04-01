package pub.avalonframework.wechat.official.account.spring.web.controller;

import pub.avalonframework.wechat.official.account.spring.web.service.WechatOfficialAccountWebPageAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author baichao
 */
@RequestMapping("${spring.avalon.wechat.official-account.web-page-authorization.api-root-path}")
@RestController
public class WechatOfficialAccountWebPageAuthorizationController {

    @Autowired
    private WechatOfficialAccountWebPageAuthorizationService wechatOfficialAccountWebPageAuthorizationService;

    @RequestMapping(value = "${spring.avalon.wechat.official-account.web-page-authorization.api-get-oauth2-path}")
    public Object getOauth2Path(@RequestParam(required = false) String state, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return wechatOfficialAccountWebPageAuthorizationService.getOauth2Path(state, request, response);
    }

    @RequestMapping(value = "${spring.avalon.wechat.official-account.web-page-authorization.api-get-web-page-access-token-path}")
    public Object getWebPageAccessToken(@RequestParam String code, @RequestParam(required = false) String stateKey, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return wechatOfficialAccountWebPageAuthorizationService.getWebPageAccessToken(code, stateKey, request, response);
    }

    @RequestMapping(value = "${spring.avalon.wechat.official-account.web-page-authorization.api-get-user-info-path}")
    public Object getUserInfo(@RequestParam String code, @RequestParam(required = false) String stateKey, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return wechatOfficialAccountWebPageAuthorizationService.getUserInfo(code, stateKey, request, response);
    }
}