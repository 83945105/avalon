package pub.avalonframework.cloud.gar.controller;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.avalon.holygrail.utils.StringUtil;
import pub.avalon.sqlhelper.spring.core.SpringJdbcEngine;
import pub.avalonframework.cloud.gar.api.GarAccountNumberApi;
import pub.avalonframework.cloud.gar.beans.AccountNumber;
import pub.avalonframework.cloud.gar.service.GarAccountNumberService;
import pub.avalonframework.cloud.gar.service.impl.DefaultGarAccountNumberServiceImpl;
import pub.avalonframework.web.spring.http.response.HttpResultInfo;
import pub.avalonframework.web.spring.http.response.exception.impl.FailMessageException;
import pub.avalonframework.web.spring.http.response.view.impl.EntityMessageView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 白超
 */
@RequestMapping(GarAccountNumberApi.ROOT_PATH)
@RestController
public class GarAccountNumberController implements GarAccountNumberApi, InitializingBean {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired(required = false)
    private GarAccountNumberService accountNumberService;

    @Override
    @RequestMapping(value = "/get/accountNumberByUsernameAndPassword/{username}/{password}")
    public EntityMessageView<AccountNumber> getAccountNumberByUsernameAndPassword(@PathVariable String username, @PathVariable String password) throws Exception {
        AccountNumber row = this.accountNumberService.getAccountNumberByUsernameAndPassword(username, password);
        if (row == null) {
            throw new FailMessageException("账号不存在");
        }
        return new EntityMessageView<>(row, new HttpResultInfo(HttpStatus.OK));
    }

    @Override
    @RequestMapping(value = "/get/list/roleValueByAccountId/{accountId}")
    public EntityMessageView<Set<String>> getListRoleValueByAccountId(@PathVariable String accountId) throws Exception {
        Set<String> roleValues = this.accountNumberService.getListRoleValueByAccountId(accountId);
        return new EntityMessageView<>(roleValues, new HttpResultInfo(HttpStatus.OK));
    }

    @Override
    @RequestMapping(value = "/get/list/resourceUrlByAccountIdAndRoleValues/{accountId}/{roleValues}")
    public EntityMessageView<Set<String>> getListResourceUrlByAccountIdAndRoleValues(@PathVariable String accountId, @PathVariable String roleValues) throws Exception {
        Set<String> resourceUrls = this.accountNumberService.getListResourceUrlByAccountIdAndRoleValues(accountId, StringUtil.isEmpty(roleValues) ? new HashSet<>() : new HashSet<>(Arrays.asList(roleValues.split(","))));
        return new EntityMessageView<>(resourceUrls, new HttpResultInfo(HttpStatus.OK));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.accountNumberService == null) {
            DefaultGarAccountNumberServiceImpl defaultAccountNumberService = new DefaultGarAccountNumberServiceImpl();
            defaultAccountNumberService.setJdbcEngine(this.jdbcEngine);
            this.accountNumberService = defaultAccountNumberService;
        }
    }
}