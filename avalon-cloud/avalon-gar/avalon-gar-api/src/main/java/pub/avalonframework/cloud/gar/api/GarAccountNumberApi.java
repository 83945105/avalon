package pub.avalonframework.cloud.gar.api;

import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pub.avalon.holygrail.response.views.DataView;

/**
 * 账户接口
 *
 * @author baichao
 * @date 2018/11/13
 */
@FeignClient(name = "${feign.gar.account-number-api-service-name:gar-service}", path = "${feign.gar.account-number-api-service-path:/api-gar-account-number}")
public interface GarAccountNumberApi {

    String ROOT_PATH = "/api-gar-account-number";

    /**
     * 根据用户名密码获取账号信息
     *
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/accountNumberByUsernameAndPassword/{username}/{password}")
    @RequestLine("GET " + ROOT_PATH + "/get/accountNumberByUsernameAndPassword/{username}/{password}")
    DataView getAccountNumberByUsernameAndPassword(@PathVariable("username") @Param("username") String username,
                                                   @PathVariable("password") @Param("password") String password) throws Exception;

    /**
     * 根据账户ID获取账户拥有的角色标识符
     *
     * @param accountId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/roleValueByAccountId/{accountId}")
    @RequestLine("GET " + ROOT_PATH + "/get/list/roleValueByAccountId/{accountId}")
    DataView getListRoleValueByAccountId(@PathVariable("accountId") @Param("accountId") String accountId) throws Exception;

    /**
     * 根据账户ID、账户拥有的角色标识符获取账户拥有的资源地址
     *
     * @param accountId
     * @param roleValues
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/resourceUrlByAccountIdAndRoleValues/{accountId}/{roleValues}")
    @RequestLine("GET " + ROOT_PATH + "/get/list/resourceUrlByAccountIdAndRoleValues/{accountId}/{roleValues}")
    DataView getListResourceUrlByAccountIdAndRoleValues(@PathVariable("accountId") @Param("accountId") String accountId,
                                                        @PathVariable("roleValues") @Param("roleValues") String roleValues) throws Exception;
}
