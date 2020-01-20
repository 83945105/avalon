package pub.avalonframework.cloud.gar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pub.avalon.holygrail.response.utils.ExceptionUtil;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.spring.core.SpringJdbcEngine;
import pub.avalonframework.cloud.gar.entity.AutRole;
import pub.avalonframework.cloud.gar.model.AutRoleModel;
import pub.avalonframework.cloud.gar.utils.RequestUtils;
import pub.avalonframework.cloud.gar.utils.TableUtils;
import pub.avalonframework.core.beans.MessageConstant;
import pub.avalonframework.security.core.api.config.SecurityConfiguration;
import pub.avalonframework.security.core.beans.Principal;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author 白超
 * @date 2018/11/26
 */
@Service
public class GarServiceImpl extends DefaultGarServiceImpl {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    @Override
    public Map<String, Object> getOnline() throws Exception {
        if (!securityConfiguration.getEnabled()) {
            ExceptionUtil.throwInfoException("权限功能未开启,无法获取账户信息");
        }
        Principal principal = RequestUtils.getPrincipal();
        if (principal == null) {
            ExceptionUtil.throwNeedLoginException(MessageConstant.EXCEPTION_NEED_LOGIN_MESSAGE);
        }
        Map<String, Object> map = new HashMap<>(4);
        HttpSession session = RequestUtils.getCurrentHttpSession();
        map.put("sessionIdName", securityConfiguration.getSession().getSessionIdName());
        map.put("sessionIdValue", session.getId());
        map.put("user", principal);
        Collection<String> roleValues = principal.getRoleIdentifierSet();
        if (roleValues == null || roleValues.size() == 0) {
            map.put("roles", Collections.emptyList());
        } else {
            String roleTableName = TableUtils.getRoleTableName("gar");
            List<AutRole> roles = this.jdbcEngine.queryForList(AutRole.class, MySqlDynamicEngine.query(roleTableName, AutRoleModel.class)
                    .column(table -> table.id().name().value().type().status())
                    .where((condition, mainTable) -> condition
                            .and(mainTable.value().inS(roleValues))));
            if (roleValues.contains(TableUtils.GAR_DEVELOPER_ROLE_VALUE)) {
                roles.add(TableUtils.GAR_DEVELOPER_ROLE);
            }
            map.put("roles", roles);
        }
        return map;
    }

    @Override
    public Map<String, Object> getLogout() throws Exception {
        if (!securityConfiguration.getEnabled()) {
            ExceptionUtil.throwInfoException("权限功能未开启,无需退出");
        }
        RequestUtils.logout();
        return null;
    }
}