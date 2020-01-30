package pub.avalonframework.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import pub.avalonframework.security.core.api.config.SecurityConfiguration;
import pub.avalonframework.security.core.api.service.LoginAuthenticationService;
import pub.avalonframework.security.core.beans.IncorrectPrincipalTypeException;
import pub.avalonframework.security.core.beans.Principal;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * 用于用户登录授权的Realm
 *
 * @author baichao
 */
public class ShiroRealm extends AuthorizingRealm {

    private SecurityConfiguration securityConfiguration;

    private LoginAuthenticationService loginAuthenticationService;

    public ShiroRealm(SecurityConfiguration securityConfiguration, LoginAuthenticationService loginAuthenticationService) {
        this.securityConfiguration = securityConfiguration;
        this.loginAuthenticationService = loginAuthenticationService;
    }

    @Override
    protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
        Object primaryPrincipal = principals.getPrimaryPrincipal();
        if (primaryPrincipal instanceof Principal) {
            return ((Principal) primaryPrincipal).getId();
        }
        if (primaryPrincipal instanceof Map) {
            return ((Map) primaryPrincipal).get("id");
        }
        throw new IncorrectPrincipalTypeException(primaryPrincipal.getClass(), ShiroRealm.class, null);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Principal principal = (Principal) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roleIdentifierSet = principal.getRoleIdentifierSet();
        Set<String> urls = roleIdentifierSet.size() == 0 ? Collections.emptySet() : this.loginAuthenticationService.getAllowAccessUrlSet(principal, roleIdentifierSet, this.securityConfiguration);
        info.setRoles(roleIdentifierSet);
        info.setStringPermissions(urls == null ? Collections.emptySet() : urls);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = token.getPrincipal().toString();
        String password = new String((char[]) token.getCredentials());
        Principal principal = this.loginAuthenticationService.getPrincipal(username, password, this.securityConfiguration);
        if (principal == null) {
            throw new IncorrectCredentialsException();
        }
        Set<String> roleIdentifierSet = this.loginAuthenticationService.getRoleIdentifierSet(principal, this.securityConfiguration);
        if (roleIdentifierSet == null) {
            roleIdentifierSet = Collections.emptySet();
        }
        principal.setRoleIdentifierSet(roleIdentifierSet);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,
                password, this.getName());
        info.setCredentialsSalt(ByteSource.Util.bytes(principal.getSalt()));
        return info;
    }
}