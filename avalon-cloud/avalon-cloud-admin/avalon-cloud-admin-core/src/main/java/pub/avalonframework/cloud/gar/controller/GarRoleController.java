package pub.avalonframework.cloud.gar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.avalon.holygrail.response.utils.DataViewUtil;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalon.holygrail.utils.StringUtil;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.spring.beans.PageResultForMap;
import pub.avalon.sqlhelper.spring.core.SpringJdbcEngine;
import pub.avalonframework.cloud.gar.api.GarRoleApi;
import pub.avalonframework.cloud.gar.dc.AutRoleGet;
import pub.avalonframework.cloud.gar.dc.AutRolePost;
import pub.avalonframework.cloud.gar.dc.AutRolePut;
import pub.avalonframework.cloud.gar.model.AutRoleModel;
import pub.avalonframework.cloud.gar.service.GarRoleService;
import pub.avalonframework.cloud.gar.utils.TableUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author 白超
 * @version 1.0
 * @see
 * @since 2018/7/12
 */
@RestController
@RequestMapping(GarRoleApi.ROOT_PATH)
public class GarRoleController implements GarRoleApi {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired
    private GarRoleService roleService;

    @Autowired
    private HttpServletRequest request;

    @Override
    @RequestMapping(value = "/get/validateValueCanUse/{value}")
    public DataView getValidateValueCanUse(@PathVariable String value, String excludeValues, String moduleId) throws Exception {
        boolean canUse = this.roleService.getValidateValueCanUse(moduleId, value, StringUtil.isEmpty(excludeValues) ? null : Arrays.asList(excludeValues.split(",")));
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("canUse", canUse));
    }

    @Override
    @RequestMapping(value = "/get/list/role")
    public DataView getListRole(AutRoleGet record, String moduleId) throws Exception {
        String roleTableName = TableUtils.getRoleTableName(moduleId, request);
        List<Map<String, Object>> rows = this.jdbcEngine.queryForList(MySqlDynamicEngine.query(roleTableName, AutRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.name().like(record.getLikeText()))
                        .or(mainTable.value().like(record.getLikeText())))
                .sort(table -> table.index().asc()));
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/get/pageList/role")
    public DataView getPageListRole(AutRoleGet record, Integer currentPage, Integer pageSize, String moduleId) throws Exception {
        String roleTableName = TableUtils.getRoleTableName(moduleId, request);
        currentPage = (currentPage == null || currentPage < 1) ? 1 : currentPage;
        pageSize = (pageSize == null || pageSize < 1 || pageSize > 500) ? 1 : pageSize;
        PageResultForMap pageResult = this.jdbcEngine.pageQueryList(currentPage, pageSize, MySqlDynamicEngine.query(roleTableName, AutRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and((cd, mt) -> cd
                                .and(mt.name().like(record.getLikeText()))
                                .or(mt.value().like(record.getLikeText()))
                                .or(mt.id().like(record.getLikeText()))))
                .sort(table -> table.index().asc()));
        return DataViewUtil.getModelViewSuccess(
                pageResult.getResult(), pageResult.getLimit());
    }

    @Override
    @RequestMapping(value = "/post/role")
    public DataView postRole(AutRolePost record, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        AutRoleGet role = this.roleService.postRole(moduleId, record);
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("role", role));
    }

    @Override
    @RequestMapping(value = "/put/roleByRoleId/{roleId}")
    public DataView putRoleByRoleId(@PathVariable String roleId, AutRolePut record, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.roleService.putRoleByRoleId(moduleId, roleId, record);
        return DataViewUtil.getMessageViewSuccess("修改角色成功");
    }

    @Override
    @RequestMapping(value = "/delete/roleByRoleId/{roleId}")
    public DataView deleteRoleByRoleId(@PathVariable String roleId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.roleService.deleteRoleByRoleId(moduleId, roleId);
        return DataViewUtil.getMessageViewSuccess("删除角色成功");
    }

    @Override
    @RequestMapping(value = "/delete/list/roleByRoleIds/{roleIds}")
    public DataView deleteListRoleByRoleIds(@PathVariable String roleIds, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.roleService.deleteListRoleByRoleIds(moduleId, Arrays.asList(roleIds.split(",")));
        return DataViewUtil.getMessageViewSuccess("删除角色成功");
    }

}
