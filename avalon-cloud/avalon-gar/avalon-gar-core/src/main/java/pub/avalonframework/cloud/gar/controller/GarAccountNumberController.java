package pub.avalonframework.cloud.gar.controller;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.avalon.holygrail.response.utils.DataViewUtil;
import pub.avalon.holygrail.response.utils.ExceptionUtil;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalon.holygrail.utils.StringUtil;
import pub.avalon.sqlhelper.spring.core.SpringJdbcEngine;
import pub.avalonframework.cloud.gar.api.GarAccountNumberApi;
import pub.avalonframework.cloud.gar.beans.AccountNumber;
import pub.avalonframework.cloud.gar.service.GarAccountNumberService;
import pub.avalonframework.cloud.gar.service.impl.DefaultGarAccountNumberServiceImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 白超
 * @date 2018/11/14
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
    public DataView getAccountNumberByUsernameAndPassword(@PathVariable String username, @PathVariable String password) throws Exception {
        AccountNumber row = this.accountNumberService.getAccountNumberByUsernameAndPassword(username, password);
        if (row == null) {
            ExceptionUtil.throwFailException(40404, "账号不存在");
        }
        return DataViewUtil.getModelViewSuccess(row);
    }

    @Override
    @RequestMapping(value = "/get/list/roleValueByAccountId/{accountId}")
    public DataView getListRoleValueByAccountId(@PathVariable String accountId) throws Exception {
        Set<String> roleValues = this.accountNumberService.getListRoleValueByAccountId(accountId);
        return DataViewUtil.getModelViewSuccess(roleValues);
    }

    @Override
    @RequestMapping(value = "/get/list/resourceUrlByAccountIdAndRoleValues/{accountId}/{roleValues}")
    public DataView getListResourceUrlByAccountIdAndRoleValues(@PathVariable String accountId, @PathVariable String roleValues) throws Exception {
        Set<String> resourceUrls = this.accountNumberService.getListResourceUrlByAccountIdAndRoleValues(accountId, StringUtil.isEmpty(roleValues) ? new HashSet<>() : new HashSet<>(Arrays.asList(roleValues.split(","))));
        return DataViewUtil.getModelViewSuccess(resourceUrls);
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
