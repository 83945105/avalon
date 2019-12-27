package pub.avalonframework.cloud.gar.service.impl;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.util.AntPathMatcher;
import pub.avalon.holygrail.response.beans.ResultInfo;
import pub.avalon.holygrail.response.utils.ResultUtil;
import pub.avalon.holygrail.response.views.ExceptionView;
import pub.avalon.holygrail.response.views.ModelView;
import pub.avalon.holygrail.utils.StringUtil;
import pub.avalon.sqlhelper.spring.core.SpringJdbcEngine;
import pub.avalonframework.cloud.gar.beans.DeveloperAccountNumber;
import pub.avalonframework.cloud.gar.beans.DynamicRouteLocator;
import pub.avalonframework.cloud.gar.service.GarAccountNumberService;
import pub.avalonframework.cloud.gar.utils.Md5Utils;
import pub.avalonframework.cloud.gar.utils.RequestUtils;
import pub.avalonframework.cloud.gar.utils.TableUtils;
import pub.avalonframework.core.beans.MessageConstant;
import pub.avalonframework.security.core.api.config.SecurityConfiguration;
import pub.avalonframework.security.core.api.config.authentication.AuthenticationConfiguration;
import pub.avalonframework.security.core.api.config.http.HttpConfiguration;
import pub.avalonframework.security.core.api.service.LoginAuthenticationService;
import pub.avalonframework.security.core.api.service.WebService;
import pub.avalonframework.security.core.beans.Principal;
import pub.avalonframework.shiro.exception.UnimplementedInterfaceException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 白超
 * @date 2018/6/7
 */
public class GarLoginAuthenticationServiceImpl implements LoginAuthenticationService, InitializingBean {

    private final static String SERVICE_NAME = "GAR";

    private final static HttpMessageConverter<Object> HTTP_MESSAGE_CONVERTER = new MappingJackson2HttpMessageConverter();

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired(required = false)
    private GarAccountNumberService accountNumberService;

    @Override
    public String getServiceName() {
        return SERVICE_NAME;
    }

    @Override
    public boolean isAuthenticated(HttpServletRequest request, HttpServletResponse response, Map<String, Object> parameters, SecurityConfiguration securityConfiguration) {
        return RequestUtils.isAuthenticated();
    }

    @Override
    public Principal getPrincipal(String username, String password, SecurityConfiguration securityConfiguration) throws AuthenticationException {
        if (DeveloperAccountNumber.DEVELOPER_LOGIN_NAME.equals(username) && DeveloperAccountNumber.DEVELOPER_PASSWORD.equals(Md5Utils.md5(password, DeveloperAccountNumber.DEVELOPER_ID))) {
            return new DeveloperAccountNumber();
        }
        return this.accountNumberService.getAccountNumberByUsernameAndPassword(username, password);
    }

    @Override
    public Set<String> getRoleIdentifierSet(Principal principal, SecurityConfiguration securityConfiguration) {
        if (DeveloperAccountNumber.DEVELOPER_ID.equals(principal.getId())) {
            return Collections.singleton(TableUtils.GAR_DEVELOPER_ROLE_VALUE);
        }
        try {
            return this.accountNumberService.getListRoleValueByAccountId(principal.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptySet();
    }

    @Override
    public Set<String> getAllowAccessUrlSet(Principal principal, Set<String> roleIdentifierSet, SecurityConfiguration securityConfiguration) {
        if (DeveloperAccountNumber.DEVELOPER_ID.equals(principal.getId())) {
            return Collections.singleton("/**");
        }
        try {
            return this.accountNumberService.getListResourceUrlByAccountIdAndRoleValues(principal.getId(), roleIdentifierSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptySet();
    }

    @Override
    public void formLoginSuccess(WebService webService, HttpServletRequest request, HttpServletResponse response, String fallbackUrl, SecurityConfiguration securityConfiguration) {
        HttpSession session = RequestUtils.getHttpSession(request);
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
        try {
            webService.redirectToSavedRequest(request, response, fallbackUrl == null ? "" : fallbackUrl, securityConfiguration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void formLoginFail(WebService webService, HttpServletRequest request, HttpServletResponse response, Exception ex, String loginUrl, SecurityConfiguration securityConfiguration) {
        try {
            webService.redirectToLogin(request, response, loginUrl, securityConfiguration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ajaxLoginSuccess(WebService webService, HttpServletRequest request, HttpServletResponse response, SecurityConfiguration securityConfiguration) {
        HttpSession session = RequestUtils.getHttpSession(request);
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

        Principal user = RequestUtils.getPrincipal();

        ModelView modelView = new ModelView(0, ResultUtil.createSuccess("登录成功"));
        Map<String, Object> map = new HashMap<>(3);
        map.put("sessionIdName", securityConfiguration.getHttpConfiguration().getSessionIdName());
        map.put("sessionIdValue", sessionId.toString());
        map.put("user", user);
        modelView.setRecords(map);
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            HTTP_MESSAGE_CONVERTER.write(modelView, MediaType.APPLICATION_JSON, outputMessage);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void ajaxLoginFail(WebService webService, HttpServletRequest request, HttpServletResponse response, Exception e, SecurityConfiguration securityConfiguration) {
        ResultInfo resultInfo;
        if (e instanceof UnknownAccountException) {
            resultInfo = ResultUtil.createFail(MessageConstant.EXCEPTION_UNKNOWN_ACCOUNT_MESSAGE);
        } else if (e instanceof IncorrectCredentialsException) {
            resultInfo = ResultUtil.createFail(MessageConstant.EXCEPTION_INCORRECT_CREDENTIALS_MESSAGE);
        } else if (e instanceof UnsupportedTokenException) {
            resultInfo = ResultUtil.createFail(MessageConstant.EXCEPTION_UNSUPPORTED_TOKEN_MESSAGE);
        } else if (e instanceof LockedAccountException) {
            resultInfo = ResultUtil.createFail(MessageConstant.EXCEPTION_LOCKED_ACCOUNT_MESSAGE);
        } else if (e instanceof DisabledAccountException) {
            resultInfo = ResultUtil.createFail(MessageConstant.EXCEPTION_DISABLED_ACCOUNT_MESSAGE);
        } else if (e instanceof ExcessiveAttemptsException) {
            resultInfo = ResultUtil.createFail(MessageConstant.EXCEPTION_EXCESSIVE_ATTEMPTS_MESSAGE);
        } else if (e instanceof ConcurrentAccessException) {
            resultInfo = ResultUtil.createFail(MessageConstant.EXCEPTION_CONCURRENT_ACCESS_MESSAGE);
        } else if (e instanceof AccountException) {
            resultInfo = ResultUtil.createFail(MessageConstant.EXCEPTION_ACCOUNT_MESSAGE);
        } else if (e instanceof ExpiredCredentialsException) {
            resultInfo = ResultUtil.createFail(MessageConstant.EXCEPTION_EXPIRED_CREDENTIALS_MESSAGE);
        } else if (e instanceof CredentialsException) {
            resultInfo = ResultUtil.createFail(MessageConstant.EXCEPTION_CREDENTIALS_MESSAGE);
        } else if (e instanceof UnimplementedInterfaceException) {
            resultInfo = ResultUtil.createError(e.getMessage());
        } else if (e instanceof AuthenticationException) {
            resultInfo = ResultUtil.createFail(MessageConstant.EXCEPTION_AUTHENTICATION_MESSAGE);
        } else {
            e.printStackTrace();
            resultInfo = ResultUtil.createError(MessageConstant.EXCEPTION_DEFAULT_MESSAGE);
        }
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        try {
            HTTP_MESSAGE_CONVERTER.write(new ExceptionView(0, resultInfo), MediaType.APPLICATION_JSON, outputMessage);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private final static AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void onAjaxAccessDenied(WebService webService, HttpServletRequest request, HttpServletResponse response, SecurityConfiguration securityConfiguration) {
        String url = null;
        String uri = request.getRequestURI();
        if (!StringUtil.isEmpty(DynamicRouteLocator.DB_ROUTE_MAP)) {
            for (Map.Entry<String, DynamicRouteLocator.ZuulRoute> entry : DynamicRouteLocator.DB_ROUTE_MAP.entrySet()) {
                if (PATH_MATCHER.match(entry.getKey(), uri)) {
                    url = entry.getValue().getLoginUrl();
                    break;
                }
            }

        }
        AuthenticationConfiguration authenticationConfiguration = securityConfiguration.getAuthenticationConfiguration();
        if (StringUtil.isEmpty(url)) {
            url = authenticationConfiguration.getPageUrl();
        }
        HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
        Map<String, String> result = new HashMap<>(2);
        result.put("loginPageUrl", url);
        result.put("loginPostUrl", authenticationConfiguration.getUrl());
        try {
            HTTP_MESSAGE_CONVERTER.write(new ExceptionView(0, ResultUtil.createNeedLogin(MessageConstant.EXCEPTION_NEED_LOGIN_MESSAGE), result), MediaType.APPLICATION_JSON, outputMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAccessDenied(WebService webService, HttpServletRequest request, HttpServletResponse response, String loginUrl, SecurityConfiguration securityConfiguration) {
        String url = null;
        String uri = request.getRequestURI();
        if (!StringUtil.isEmpty(DynamicRouteLocator.DB_ROUTE_MAP)) {
            for (Map.Entry<String, DynamicRouteLocator.ZuulRoute> entry : DynamicRouteLocator.DB_ROUTE_MAP.entrySet()) {
                if (PATH_MATCHER.match(entry.getKey(), uri)) {
                    url = entry.getValue().getLoginUrl();
                    break;
                }
            }
        }
        if (StringUtil.isEmpty(url)) {
            url = securityConfiguration.getAuthenticationConfiguration().getPageUrl();
        }
        webService.redirectTo(request, response, url, null, true, securityConfiguration);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.accountNumberService == null) {
            DefaultGarAccountNumberServiceImpl defaultAccountNumberService = new DefaultGarAccountNumberServiceImpl();
            defaultAccountNumberService.setJdbcEngine(this.jdbcEngine);
            this.accountNumberService = defaultAccountNumberService;
        }
    }
}