package pub.avalonframework.security.core.yaml.config;

import pub.avalonframework.core.yaml.config.YamlConfiguration;

import java.util.Set;

/**
 * @author baichao
 */
public class YamlFilterConfiguration implements YamlConfiguration {

    private String loginUrl;

    private String unauthorizedUrl;

    private String formFilterName;

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