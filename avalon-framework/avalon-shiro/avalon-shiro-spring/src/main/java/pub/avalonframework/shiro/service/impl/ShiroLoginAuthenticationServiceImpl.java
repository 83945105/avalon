package pub.avalonframework.shiro.service.impl;

import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import pub.avalonframework.security.core.api.config.SecurityConfiguration;
import pub.avalonframework.security.core.api.config.http.HttpConfiguration;
import pub.avalonframework.security.core.api.service.LoginAuthenticationService;
import pub.avalonframework.security.core.api.service.WebService;
import pub.avalonframework.security.core.beans.Principal;
import pub.avalonframework.security.core.spi.utils.SecurityUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author baichao
 */
public class ShiroLoginAuthenticationServiceImpl implements LoginAuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(ShiroLoginAuthenticationServiceImpl.class);

    private static final String SERVICE_NAME = "SHIRO";

    private final static HttpMessageConverter<Object> HTTP_MESSAGE_CONVERTER = new MappingJackson2HttpMessageConverter();

    @Override
    public String getServiceName() {
        return SERVICE_NAME;
    }

    @Override
    public boolean isAuthenticated(HttpServletRequest request, HttpServletResponse response, Map<String, Object> parameters, SecurityConfiguration securityConfiguration) {
        return SecurityUtils.isAuthenticated(request, response);
    }

    @Override
    public Principal getPrincipal(String username, String password, SecurityConfiguration securityConfiguration) throws AuthenticationException {
        logger.debug("You can implement the pub.avalonframework.security.core.api.service.LoginAuthenticationService interface and register as a spring bean to achieve this function.");
        return null;
    }

    @Override
    public Set<String> getRoleIdentifierSet(Principal principal, SecurityConfiguration securityConfiguration) {
        logger.debug("You can implement the pub.avalonframework.security.core.api.service.LoginAuthenticationService interface and register as a spring bean to achieve this function.");
        return null;
    }

    @Override
    public Set<String> getAllowAccessUrlSet(Principal principal, Set<String> roleIdentifierSet, SecurityConfiguration securityConfiguration) {
        logger.debug("You can implement the pub.avalonframework.security.core.api.service.LoginAuthenticationService interface and register as a spring bean to achieve this function.");
        return null;
    }

    @Override
    public void formLoginSuccess(WebService webService, HttpServletRequest request, HttpServletResponse response, String fallbackUrl, SecurityConfiguration securityConfiguration) {
        HttpSession session = SecurityUtils.getSession(request, response);
        if (session == null) {
            return;
        }
        Serializable sessionId = session.getId();
        HttpConfiguration httpConfiguration = securityConfiguration.getHttpConfiguration();
        String sessionIdName = httpConfiguration.getSessionIdName();
        Long cookieMaxAge = httpConfiguration.getCookieMaxAge();
        Cookie cookie = new Cookie(sessionIdName, sessionId.toString());
        cookie.setMaxAge(cookieMaxAge == null ? 0 : cookieMaxAge.intValue());
        cookie.setPath("/");
        response.addCookie(cookie);
        webService.redirectToSavedRequest(request, response, fallbackUrl == null ? "" : fallbackUrl, securityConfiguration);
    }

    @Override
    public void formLoginFail(WebService webService, HttpServletRequest request, HttpServletResponse response, Exception ex, String loginUrl, SecurityConfiguration securityConfiguration) {
        webService.redirectToLogin(request, response, loginUrl, securityConfiguration);
    }

    @Override
    public void ajaxLoginSuccess(WebService webService, HttpServletRequest request, HttpServletResponse response, SecurityConfiguration securityConfiguration) {
        HttpSession session = SecurityUtils.getSession(request, response);
        if (session == null) {
            return;
        }
        Serializable sessionId = session.getId();
        HttpConfiguration httpConfiguration = securityConfiguration.getHttpConfiguration();
        String sessionIdName = httpConfiguration.getSessionIdName();
        Long cookieMaxAge = httpConfiguration.getCookieMaxAge();
        Cookie cookie = new Cookie(sessionIdName, sessionId.toString());
        cookie.setMaxAge(cookieMaxAge == null ? 0 : cookieMaxAge.intValue());
        cookie.setPath("/");
        response.addCookie(cookie);
        Principal principal = SecurityUtils.getPrincipal(request, response);
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("success", true);
        map.put("principal", principal);
        try {
            HTTP_MESSAGE_CONVERTER.write(map, MediaType.APPLICATION_JSON, new ServletServerHttpResponse(response));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void ajaxLoginFail(WebService webService, HttpServletRequest request, HttpServletResponse response, Exception e, SecurityConfiguration securityConfiguration) {
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("success", false);
        map.put("message", e.getMessage());
        try {
            HTTP_MESSAGE_CONVERTER.write(map, MediaType.APPLICATION_JSON, new ServletServerHttpResponse(response));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onAjaxAccessDenied(WebService webService, HttpServletRequest request, HttpServletResponse response, SecurityConfiguration securityConfiguration) {
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("success", false);
        map.put("message", "Permission denied.");
        try {
            HTTP_MESSAGE_CONVERTER.write(map, MediaType.APPLICATION_JSON, new ServletServerHttpResponse(response));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAccessDenied(WebService webService, HttpServletRequest request, HttpServletResponse response, String loginUrl, SecurityConfiguration securityConfiguration) {
        String url = securityConfiguration.getAuthenticationConfiguration().getPageUrl();
        webService.redirectTo(request, response, url, null, true, securityConfiguration);
    }
}