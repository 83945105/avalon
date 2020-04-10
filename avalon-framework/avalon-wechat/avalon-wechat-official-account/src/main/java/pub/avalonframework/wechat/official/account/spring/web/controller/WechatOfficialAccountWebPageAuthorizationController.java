package pub.avalonframework.wechat.official.account.spring.web.controller;

import pub.avalonframework.wechat.official.account.core.api.config.WechatOfficialAccountConfiguration;
import pub.avalonframework.wechat.official.account.core.webpage.WebPageAccessTokenResponse;
import pub.avalonframework.wechat.official.account.spring.web.entity.CacheValue;
import pub.avalonframework.wechat.official.account.spring.web.service.WechatOfficialAccountWebPageAuthorizationService;
import pub.avalonframework.wechat.official.account.spring.web.utils.WOAWebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author baichao
 */
@RequestMapping("${spring.avalon.wechat.official-account.web-page-authorization.api-root-path}")
@RestController
public class WechatOfficialAccountWebPageAuthorizationController {

    @Autowired(required = false)
    protected WechatOfficialAccountConfiguration wechatOfficialAccountConfiguration;

    @Autowired
    private WechatOfficialAccountWebPageAuthorizationService wechatOfficialAccountWebPageAuthorizationService;

    @RequestMapping(value = {
            "${spring.avalon.wechat.official-account.web-page-authorization.api-get-oauth2-path}",
            "${spring.avalon.wechat.official-account.web-page-authorization.api-get-oauth2-path}/{param}"
    })
    public Object getOauth2Path(@PathVariable(required = false) String param, @RequestParam(required = false) Map<String, String> params, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return wechatOfficialAccountWebPageAuthorizationService.getOauth2Path(param, params, request, response);
    }

    @RequestMapping(value = {
            "${spring.avalon.wechat.official-account.web-page-authorization.api-redirect-oauth2-path}",
            "${spring.avalon.wechat.official-account.web-page-authorization.api-redirect-oauth2-path}/{param}"
    })
    public void redirectOauth2Path(@PathVariable(required = false) String param, @RequestParam(required = false) Map<String, String> params, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String oauth2Path;
        if (param == null && params == null) {
            oauth2Path = WOAWebUtils.getInstance(wechatOfficialAccountConfiguration).getAutoAssembleOauth2Path();
        } else if (param != null && params != null) {
            CacheValue value = new CacheValue();
            value.setParam(param);
            value.setParams(params);
            String key = wechatOfficialAccountWebPageAuthorizationService.setCache(value);
            oauth2Path = WOAWebUtils.getInstance(wechatOfficialAccountConfiguration).getAutoAssembleOauth2PathWithState(key);
        } else if (param != null) {
            CacheValue value = new CacheValue();
            value.setParam(param);
            String key = wechatOfficialAccountWebPageAuthorizationService.setCache(value);
            oauth2Path = WOAWebUtils.getInstance(wechatOfficialAccountConfiguration).getAutoAssembleOauth2PathWithState(key);
        } else {
            CacheValue value = new CacheValue();
            value.setParams(params);
            String key = wechatOfficialAccountWebPageAuthorizationService.setCache(value);
            oauth2Path = WOAWebUtils.getInstance(wechatOfficialAccountConfiguration).getAutoAssembleOauth2PathWithState(key);
        }
        response.sendRedirect(oauth2Path);
    }

    @RequestMapping(value = "${spring.avalon.wechat.official-account.web-page-authorization.api-get-web-page-access-token-path}")
    public Object getWebPageAccessToken(@RequestParam String code, @RequestParam(required = false) String key, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return wechatOfficialAccountWebPageAuthorizationService.getWebPageAccessToken(code, key, request, response);
    }

    @RequestMapping(value = "${spring.avalon.wechat.official-account.web-page-authorization.api-get-user-info-path}")
    public Object getUserInfo(@RequestParam String code, @RequestParam(required = false) String key, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return wechatOfficialAccountWebPageAuthorizationService.getUserInfo(code, key, request, response);
    }

    @RequestMapping(value = "${spring.avalon.wechat.official-account.web-page-authorization.api-accept-code-path}")
    public void acceptCode(@RequestParam String code, @RequestParam(required = false) String state, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WOAWebUtils woaWebUtils = WOAWebUtils.getInstance(wechatOfficialAccountConfiguration);
        WebPageAccessTokenResponse webPageAccessTokenResponse = woaWebUtils.getWebPageAccessToken(code);
        CacheValue cache = wechatOfficialAccountWebPageAuthorizationService.getCache(state);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        StringBuilder sb = new StringBuilder();
        sb.append("<script>");
        sb.append("sessionStorage.setItem('openid', '").append(webPageAccessTokenResponse.getOpenid()).append("');");
        Map<String, String> params = cache.getParams();
        if (params != null) {
            String redirectUri = params.get("redirect_uri");
            if (redirectUri != null) {
                sb.append("window.location.href = '").append(redirectUri).append("'");
            }
        }
        sb.append("</script>");
        out.print(sb.toString());
        out.close();
    }
}