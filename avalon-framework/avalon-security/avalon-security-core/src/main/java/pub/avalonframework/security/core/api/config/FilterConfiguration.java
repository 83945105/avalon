package pub.avalonframework.security.core.api.config;

import java.util.Set;

/**
 * Filter configuration.
 *
 * @author baichao
 */
public class FilterConfiguration {

    /**
     * Login URL for login without login.
     */
    private String loginUrl;

    /**
     * URLs that do not have permission to jump when accessing pages that require permissions.
     */
    private String unauthorizedUrl;

    /**
     * The form filter name.
     */
    private String formFilterName;

    /**
     * The release resources
     */
    private Set<String> releaseResources;

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getUnauthorizedUrl() {
        return unauthorizedUrl;
    }

    public void setUnauthorizedUrl(String unauthorizedUrl) {
        this.unauthorizedUrl = unauthorizedUrl;
    }

    public String getFormFilterName() {
        return formFilterName;
    }

    public void setFormFilterName(String formFilterName) {
        this.formFilterName = formFilterName;
    }

    public Set<String> getReleaseResources() {
        return releaseResources;
    }

    public void setReleaseResources(Set<String> releaseResources) {
        this.releaseResources = releaseResources;
    }
}