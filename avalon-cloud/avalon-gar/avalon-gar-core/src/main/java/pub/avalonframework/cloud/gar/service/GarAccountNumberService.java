package pub.avalonframework.cloud.gar.service;

import org.apache.shiro.authc.AuthenticationException;
import pub.avalonframework.cloud.gar.beans.AccountNumber;

import java.util.Set;

/**
 * @author 白超
 * @date 2018/11/14
 */
public interface GarAccountNumberService {

    /**
     * 根据用户名、密码查询账号信息
     *
     * @param username
     * @param password
     * @return
     * @throws AuthenticationException
     */
    AccountNumber getAccountNumberByUsernameAndPassword(String username, String password) throws AuthenticationException;

    /**
     * 根据账户ID获取账户拥有的角色标识符
     *
     * @param accountId
     * @return
     * @throws Exception
     */
    Set<String> getListRoleValueByAccountId(String accountId) throws Exception;

    /**
     * 根据账户ID、账户拥有的角色ID获取账户拥有的资源Url
     *
     * @param accountId
     * @param roleValues
     * @return
     * @throws Exception
     */
    Set<String> getListResourceUrlByAccountIdAndRoleValues(String accountId, Set<String> roleValues) throws Exception;

}
