package pub.avalonframework.security.core.api.service;

import pub.avalonframework.security.core.api.config.SecurityConfiguration;
import pub.avalonframework.security.core.beans.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;

/**
 * Login authentication service.
 *
 * @author baichao
 */
public interface LoginAuthenticationService extends AuthenticationService {

    /**
     * Is authenticated
     *
     * @param request               The http servlet request.
     * @param response              The http servlet response.
     * @param parameters            The passed parameters.
     * @param securityConfiguration The security configuration.
     * @return Is authenticated
     */
    boolean isAuthenticated(HttpServletRequest request, HttpServletResponse response, Map<String, Object> parameters, SecurityConfiguration securityConfiguration);

    /**
     * Get principal.
     *
     * @param username              The username.
     * @param password              The password.
     * @param securityConfiguration The security configuration.
     * @return principal the {@link Principal}.
     */
    Principal getPrincipal(String username, String password, SecurityConfiguration securityConfiguration);

    /**
     * Get role identifier set.
     *
     * @param principal             The principal {@link Principal}.
     * @param securityConfiguration The security configuration.
     * @return role identifier set.
     */
    Set<String> getRoleIdentifierSet(Principal principal, SecurityConfiguration securityConfiguration);

    /**
     * Get allow access url set.
     *
     * @param principal             The  principal {@link Principal}.
     * @param roleIdentifierSet     The {@link LoginAuthenticationService#getRoleIdentifierSet(Principal, SecurityConfiguration)}.
     * @param securityConfiguration The security configuration.
     * @return allow access url set.
     */
    Set<String> getAllowAccessUrlSet(Principal principal, Set<String> roleIdentifierSet, SecurityConfiguration securityConfiguration);

    /**
     * Form login succeeded.
     *
     * @param webService            The web service.
     * @param request               The http servlet request.
     * @param response              The http servlet response.
     * @param fallbackUrl           The fallback url.
     * @param securityConfiguration The security configuration.
     */
    void formLoginSuccess(WebService webService, HttpServletRequest request, HttpServletResponse response, String fallbackUrl, SecurityConfiguration securityConfiguration);

    /**
     * form login fail
     *
     * @param webService            The web service.
     * @param request               The http servlet request.
     * @param response              The http servlet response.
     * @param ex                    The exception that caused the login to fail
     * @param loginUrl              The login url.
     * @param securityConfiguration The security configuration.
     */
    void formLoginFail(WebService webService, HttpServletRequest request, HttpServletResponse response, Exception ex, String loginUrl, SecurityConfiguration securityConfiguration);

    /**
     * Ajax login succeeded
     *
     * @param webService            The web service.
     * @param request               The http servlet request.
     * @param response              The http servlet response.
     * @param securityConfiguration The security configuration.
     */
    void ajaxLoginSuccess(WebService webService, HttpServletRequest request, HttpServletResponse response, SecurityConfiguration securityConfiguration);

    /**
     * Ajax login failed
     *
     * @param webService            The web service.
     * @param request               The http servlet request.
     * @param response              The http servlet response.
     * @param ex                    The exception that caused the login to fail
     * @param securityConfiguration The security configuration.
     */
    void ajaxLoginFail(WebService webService, HttpServletRequest request, HttpServletResponse response, Exception ex, SecurityConfiguration securityConfiguration);

    /**
     * Ajax access is denied
     *
     * @param webService            The web service.
     * @param request               The http servlet request.
     * @param response              The http servlet response.
     * @param securityConfiguration The security configuration.
     */
    void onAjaxAccessDenied(WebService webService, HttpServletRequest request, HttpServletResponse response, SecurityConfiguration securityConfiguration);

    /**
     * Access denied
     *
     * @param webService            The web service.
     * @param request               The http servlet request.
     * @param response              The http servlet response.
     * @param loginUrl              The login url.
     * @param securityConfiguration The security configuration.
     */
    void onAccessDenied(WebService webService, HttpServletRequest request, HttpServletResponse response, String loginUrl, SecurityConfiguration securityConfiguration);
}