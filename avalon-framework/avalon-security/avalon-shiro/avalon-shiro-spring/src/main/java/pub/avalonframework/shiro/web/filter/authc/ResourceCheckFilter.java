package pub.avalonframework.shiro.web.filter.authc;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import pub.avalonframework.security.core.api.config.SecurityConfiguration;
import pub.avalonframework.security.core.api.service.ResourceAuthenticationService;
import pub.avalonframework.security.core.api.service.WebService;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

/**
 * @author baichao
 */
public final class ResourceCheckFilter extends AccessControlFilter {

    private WebService webService;

    private ResourceAuthenticationService resourceAuthenticationService;

    private SecurityConfiguration securityConfiguration;

    public ResourceCheckFilter(WebService webService, ResourceAuthenticationService resourceAuthenticationService, SecurityConfiguration securityConfiguration) {
        this.webService = webService;
        this.resourceAuthenticationService = resourceAuthenticationService;
        this.securityConfiguration = securityConfiguration;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        // Rewrite this method to use custom permission authentication, that is, go to UrlPermission to verify if there is access permission.
        Subject subject = getSubject(request, response);
        String url = getPathWithinApplication(request);
        return subject.isPermitted(url);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        this.resourceAuthenticationService.onAccessDenied(webService, (HttpServletRequest) request, (HttpServletResponse) response, Collections.emptyMap(), this.securityConfiguration);
        return false;
    }
}