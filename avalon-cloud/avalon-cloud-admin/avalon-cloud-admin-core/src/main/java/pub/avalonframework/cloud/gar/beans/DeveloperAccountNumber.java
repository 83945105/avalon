package pub.avalonframework.cloud.gar.beans;

import java.util.HashSet;
import java.util.Set;

/**
 * @author baichao
 * @date 2019/11/26
 */
public final class DeveloperAccountNumber implements AccountNumber {

    public final static String DEVELOPER_ID = "developer";
    public final static String DEVELOPER_LOGIN_NAME = "developer";
    public final static String DEVELOPER_PASSWORD = "2be8ff5d46e62386a3583db51e23b777";
    public final static String DEVELOPER_USERNAME = "开发者";
    public final static Set<String> ROLE_IDENTIFIER_SET = new HashSet<>(0);

    private String id = DEVELOPER_ID;
    private String loginName = DEVELOPER_LOGIN_NAME;
    private String password = DEVELOPER_PASSWORD;
    private String username = DEVELOPER_USERNAME;
    private Set<String> roleIdentifierSet = ROLE_IDENTIFIER_SET;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Object getSalt() {
        return getId();
    }

    @Override
    public String getName() {
        return getUsername();
    }

    @Override
    public void setName(String name) {
        setUsername(name);
    }

    @Override
    public Set<String> getRoleIdentifierSet() {
        return roleIdentifierSet;
    }

    @Override
    public void setRoleIdentifierSet(Set<String> roleIdentifierSet) {
        this.roleIdentifierSet = roleIdentifierSet;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
