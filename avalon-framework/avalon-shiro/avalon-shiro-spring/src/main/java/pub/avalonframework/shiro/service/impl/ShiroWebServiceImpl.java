package pub.avalonframework.shiro.service.impl;

import org.apache.shiro.web.util.WebUtils;
import pub.avalonframework.security.core.api.config.SecurityConfiguration;
import pub.avalonframework.security.core.api.config.http.HttpConfiguration;
import pub.avalonframework.security.core.api.service.WebService;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * @author baichao
 */
public class ShiroWebServiceImpl implements WebService {

    public final static String SERVICE_NAME = "SHIRO";

    @Override
    public String getServiceName() {
        return SERVICE_NAME;
    }

    @Override
    public boolean isAjaxRequest(ServletRequest request, ServletResponse response, SecurityConfiguration securityConfiguration) {
        HttpConfiguration httpConfiguration = securityConfiguration.getHttpConfiguration();
        if (httpConfiguration == null) {
            return false;
        }
        if (request instanceof HttpServletRequest) {
            return httpConfiguration.getAjaxHeaderIdentificationValue().equals(((HttpServletRequest) request).getHeader(httpConfiguration.getAjaxHeaderIdentificationKey()));
        }
        return false;
    }

    @Override
    public void redirectToLogin(ServletRequest request, ServletResponse response, String loginUrl, SecurityConfiguration securityConfiguration) {
        try {
            WebUtils.issueRedirect(request, response, loginUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void redirectToSavedRequest(ServletRequest request, ServletResponse response, String fallbackUrl, SecurityConfiguration securityConfiguration) {
        try {
            WebUtils.redirectToSavedRequest(request, response, fallbackUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void redirectTo(ServletRequest request, ServletResponse response, String url, Map<String, Object> params, boolean contextRelative, SecurityConfiguration securityConfiguration) {
        try {
            WebUtils.issueRedirect(request, response, url, params, contextRelative);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response, String loginUrl, SecurityConfiguration securityConfiguration) {
        WebUtils.saveRequest(request);
        redirectToLogin(request, response, loginUrl, securityConfiguration);
    }
}