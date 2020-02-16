package pub.avalonframework.shiro.authz.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * URL permission resolver.
 *
 * @author baichao
 */
public class UrlPermissionResolver implements PermissionResolver {

    public final static String URL_PREFIX = "/";

    @Override
    public Permission resolvePermission(String permissionString) {
        if (permissionString.startsWith(URL_PREFIX)) {
            return new UrlPermission(permissionString);
        }
        return new WildcardPermission(permissionString);
    }
}