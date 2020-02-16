package pub.avalonframework.shiro.web.filter.authc;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.avalonframework.security.core.api.config.SecurityConfiguration;
import pub.avalonframework.security.core.api.service.LoginAuthenticationService;
import pub.avalonframework.security.core.api.service.WebService;
import pub.avalonframework.security.core.spi.utils.SecurityUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Ajax from authentication filter
 *
 * @author baichao
 */
public final class AjaxFormAuthenticationFilter extends FormAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(AjaxFormAuthenticationFilter.class);

    private WebService webService;

    private LoginAuthenticationService loginAuthenticationService;

    private SecurityConfiguration securityConfiguration;

    public AjaxFormAuthenticationFilter(WebService webService, LoginAuthenticationService loginAuthenticationService, SecurityConfiguration securityConfiguration) {
        this.webService = webService;
        this.loginAuthenticationService = loginAuthenticationService;
        this.securityConfiguration = securityConfiguration;
    }

    private boolean crossDomain;

    private static final String HTTP_METHOD_OPTIONS_NAME = "OPTIONS";

    private static final String HTTP_METHOD_POST_NAME = "POST";

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (HTTP_METHOD_OPTIONS_NAME.equalsIgnoreCase(req.getMethod()) && this.crossDomain) {
            return true;
        }
        if (!isLoginRequest(request, response)) {
            return super.onPreHandle(request, response, mappedValue);
        }
        if (!HTTP_METHOD_POST_NAME.equalsIgnoreCase(req.getMethod())) {
            return true;
        }
        if (this.webService.isAjaxRequest(req, resp, this.securityConfiguration)) {
            return ajax2login(req, resp);
        }
        return form2Login(req, resp);
    }

    /**
     * Form login.
     *
     * @param request  the servlet request.
     * @param response the servlet response.
     * @return Is login success.
     */
    private boolean form2Login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter(this.getUsernameParam());
        String password = request.getParameter(this.getPasswordParam());
        try {
            SecurityUtils.login(username, password, request, response);
        } catch (Exception ex) {
            this.loginAuthenticationService.formLoginFail(this.webService, request, response, ex, getLoginUrl(), this.securityConfiguration);
            return false;
        }
        // 解决集成spring boot登录后不执行realm中授权方法
        org.apache.shiro.SecurityUtils.getSubject().hasRole("admin");
        this.loginAuthenticationService.formLoginSuccess(this.webService, request, response, getSuccessUrl(), this.securityConfiguration);
        return false;
    }

    /**
     * Ajax login.
     *
     * @param request  the servlet request.
     * @param response the servlet response.
     * @return Is login success.
     */
    private boolean ajax2login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter(this.getUsernameParam());
        String password = request.getParameter(this.getPasswordParam());
        try {
            SecurityUtils.login(username, password, request, response);
        } catch (Exception ex) {
            this.loginAuthenticationService.ajaxLoginFail(this.webService, request, response, ex, this.securityConfiguration);
            return false;
        }
        SecurityUtils.login(username, password, request, response);
        this.loginAuthenticationService.ajaxLoginSuccess(this.webService, request, response, this.securityConfiguration);
        return false;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return this.loginAuthenticationService.isAuthenticated((HttpServletRequest) request, (HttpServletResponse) response, new HashMap<String, Object>(1) {{
            put("mappedValue", mappedValue);
        }}, this.securityConfiguration);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (logger.isTraceEnabled()) {
                logger.trace("Login request detected...Try to log in");
            }
            String username = request.getParameter(this.getUsernameParam());
            String password = request.getParameter(this.getPasswordParam());
            boolean isLoginSubmission = isLoginSubmission(request, response);
            if (isLoginSubmission) {
                if (logger.isTraceEnabled()) {
                    logger.trace("Post login request detected...Try to log in");
                }
                try {
                    SecurityUtils.login(username, password, (HttpServletRequest) request, (HttpServletResponse) response);
                } catch (Exception ex) {
                    return false;
                }
                return true;
            } else {
                if (logger.isTraceEnabled()) {
                    logger.trace("Post login request not detected...");
                }
                return false;
            }
        } else {
            if (this.webService.isAjaxRequest(request, response, this.securityConfiguration)) {
                this.loginAuthenticationService.onAjaxAccessDenied(this.webService, (HttpServletRequest) request, (HttpServletResponse) response, this.securityConfiguration);
                return false;
            }
            this.loginAuthenticationService.onAccessDenied(this.webService, (HttpServletRequest) request, (HttpServletResponse) response, getLoginUrl(), this.securityConfiguration);
            return false;
        }
    }

    public void setCrossDomain(boolean crossDomain) {
        this.crossDomain = crossDomain;
    }
}