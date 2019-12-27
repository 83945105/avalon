package pub.avalonframework.security.core.api.service;

import pub.avalonframework.security.core.api.config.SecurityConfiguration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;

/**
 * Resource authentication service.
 *
 * @author baichao
 */
public interface ResourceAuthenticationService extends AuthenticationService {

    /**
     * Get release resources.
     *
     * @param parameters            The passed parameters.
     * @param securityConfiguration The security configuration.
     * @return The release resources.
     */
    Set<String> getReleaseResources(Map<String, Object> parameters, SecurityConfiguration securityConfiguration);

    /**
     * Access denied
     *
     * @param webService            The web service.
     * @param request               The http servlet request.
     * @param response              The http servlet response.
     * @param parameters            The passed parameters.
     * @param securityConfiguration The security configuration.
     */
    void onAccessDenied(WebService webService, HttpServletRequest request, HttpServletResponse response, Map<String, Object> parameters, SecurityConfiguration securityConfiguration);
}