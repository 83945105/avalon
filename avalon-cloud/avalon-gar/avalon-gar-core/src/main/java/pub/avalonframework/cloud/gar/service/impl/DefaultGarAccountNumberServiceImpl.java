package pub.avalonframework.cloud.gar.service.impl;

import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import pub.avalon.holygrail.response.utils.ExceptionUtil;
import pub.avalon.holygrail.utils.StringUtil;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.spring.core.SpringJdbcEngine;
import pub.avalonframework.cloud.gar.beans.AccountNumber;
import pub.avalonframework.cloud.gar.dc.AutObjectRoleGet;
import pub.avalonframework.cloud.gar.dc.AutRoleResourceGet;
import pub.avalonframework.cloud.gar.model.AutObjectRoleModel;
import pub.avalonframework.cloud.gar.model.AutRoleResourceModel;
import pub.avalonframework.cloud.gar.service.GarAccountNumberService;
import pub.avalonframework.cloud.gar.utils.GarUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 白超
 * @date 2018/11/23
 */
public class DefaultGarAccountNumberServiceImpl implements GarAccountNumberService {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Override
    public AccountNumber getAccountNumberByUsernameAndPassword(String username, String password) throws AuthenticationException {
        ExceptionUtil.throwFailException("若想使用该功能请实现AccountNumberService接口");
        return null;
    }

    @Override
    public Set<String> getListRoleValueByAccountId(String accountId) throws Exception {
        Set<String> roleValues = new HashSet<>();
        for (String objectRoleTableName : GarUtils.OBJECT_ROLE_TABLE_NAMES) {
            List<AutObjectRoleGet> objectRoleList = this.jdbcEngine.queryForList(AutObjectRoleGet.class, MySqlDynamicEngine.query(objectRoleTableName, AutObjectRoleModel.class)
                    .where((condition, mainTable) -> condition
                            .and(mainTable.objectId().equalTo(accountId))));
            objectRoleList.forEach(objectRole -> roleValues.add(objectRole.getRoleValue()));
        }
        return roleValues;
    }

    @Override
    public Set<String> getListResourceUrlByAccountIdAndRoleValues(String accountId, Set<String> roleValues) throws Exception {
        Set<String> resourceUrls = new HashSet<>();
        if (StringUtil.isEmpty(roleValues)) {
            return resourceUrls;
        }
        for (String roleResourceTableNameBak : GarUtils.ROLE_RESOURCE_TABLE_NAMES) {
            List<AutRoleResourceGet> roleResourceList = this.jdbcEngine.queryForList(AutRoleResourceGet.class, MySqlDynamicEngine.query(roleResourceTableNameBak, AutRoleResourceModel.class)
                    .where((condition, mainTable) -> condition
                            .and(mainTable.roleValue().inS(roleValues))));
            roleResourceList.forEach(roleResource -> resourceUrls.add(roleResource.getResourceUrl()));
        }
        return resourceUrls;
    }

    public void setJdbcEngine(SpringJdbcEngine jdbcEngine) {
        this.jdbcEngine = jdbcEngine;
    }
}
