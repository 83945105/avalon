package pub.avalonframework.shiro.authz.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.PatternMatcher;

/**
 * URL permission.
 *
 * @author baichao
 */
public class UrlPermission implements Permission {

    private String url;

    public UrlPermission(String url) {
        this.url = url;
    }

    @Override
    public boolean implies(Permission p) {
        if (!(p instanceof UrlPermission)) {
            return false;
        }
        UrlPermission up = (UrlPermission) p;
        PatternMatcher patternMatcher = new AntPathMatcher();
        return patternMatcher.matches(this.getUrl(), up.getUrl());
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}