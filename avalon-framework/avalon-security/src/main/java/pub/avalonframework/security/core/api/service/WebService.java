package pub.avalonframework.security.core.api.service;

import pub.avalonframework.core.spi.beans.SpiService;
import pub.avalonframework.security.core.api.config.SecurityConfiguration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Map;

/**
 * Web service.
 *
 * @author baichao
 */
public interface WebService extends SpiService {

    /**
     * Is it an ajax request.
     *
     * @param request               The servlet request.
     * @param response              The servlet response.
     * @param securityConfiguration The security configuration.
     * @return Is it an Ajax request.
     */
    boolean isAjaxRequest(ServletRequest request, ServletResponse response, SecurityConfiguration securityConfiguration);

    /**
     * Redirect to login.
     *
     * @param request               The servlet request.
     * @param response              The servlet response.
     * @param loginUrl              The login url.
     * @param securityConfiguration The security configuration.
     */
    void redirectToLogin(ServletRequest request, ServletResponse response, String loginUrl, SecurityConfiguration securityConfiguration);

    /**
     * Redirect to saved request.
     *
     * @param request               The servlet request.
     * @param response              The servlet response.
     * @param fallbackUrl           The fallback url to redirect to if there is no saved request available.
     * @param securityConfiguration The security configuration.
     */
    void redirectToSavedRequest(ServletRequest request, ServletResponse response, String fallbackUrl, SecurityConfiguration securityConfiguration);

    /**
     * Redirects the current request to a new URL based on the given parameters and default values
     * for unspecified parameters.
     *
     * @param request               The servlet request.
     * @param response              The servlet response.
     * @param url                   The URL to redirect to.
     * @param params                A map of parameters that should be set as request parameters for the new request.
     * @param contextRelative       True if the URL is relative to the servlet context path, or false if the URL is absolute.
     * @param securityConfiguration The security configuration.
     */
    void redirectTo(ServletRequest request, ServletResponse response, String url, Map<String, Object> params, boolean contextRelative, SecurityConfiguration securityConfiguration);

    /**
     * Save request path and redirect to login.
     *
     * @param request               The servlet request.
     * @param response              The servlet response.
     * @param loginUrl              The login url.
     * @param securityConfiguration The security configuration.
     */
    void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response, String loginUrl, SecurityConfiguration securityConfiguration);
}