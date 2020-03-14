package pub.avalonframework.cloud.gar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.avalon.holygrail.response.utils.DataViewUtil;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalon.holygrail.utils.StringUtil;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.spring.core.SpringJdbcEngine;
import pub.avalonframework.cloud.gar.api.GarRoleRouteViewTemplateApi;
import pub.avalonframework.cloud.gar.model.AutRoleModel;
import pub.avalonframework.cloud.gar.model.AutRoleRouteViewTemplateModel;
import pub.avalonframework.cloud.gar.service.GarRoleRouteViewTemplateService;
import pub.avalonframework.cloud.gar.utils.TableUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author 白超
 */
@RequestMapping(GarRoleRouteViewTemplateApi.ROOT_PATH)
@RestController
public class GarRoleRouteViewTemplateController implements GarRoleRouteViewTemplateApi {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired
    private GarRoleRouteViewTemplateService roleRouteViewTemplateService;

    @Autowired
    private HttpServletRequest request;

    @Override
    @RequestMapping(value = "/get/list/roleByRouteViewId/{routeViewId}")
    public DataView getListRoleByRouteViewId(@PathVariable String routeViewId, String moduleId) throws Exception {
        String roleRouteViewTemplateTableName = TableUtils.getRoleRouteViewTemplateTableName(moduleId, request);
        List<String> roleIds = this.jdbcEngine.queryColumnList(AutRoleRouteViewTemplateModel.roleId_alias, String.class, MySqlDynamicEngine.query(roleRouteViewTemplateTableName, AutRoleRouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId).routeViewId().equalTo(routeViewId))));

        if (StringUtil.isEmpty(roleIds)) {
            return DataViewUtil.getModelViewSuccess(new ArrayList<>(0));
        }

        String roleTableName = TableUtils.getRoleTableName(moduleId, request);
        List<Map<String, Object>> roles = this.jdbcEngine.queryForList(MySqlDynamicEngine.query(roleTableName, AutRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().inS(new HashSet<>(roleIds)))));

        return DataViewUtil.getModelViewSuccess(roles);
    }

    @Override
    @RequestMapping(value = "/post/roleRouteViewTemplateByRoleIdAndRouteViewIdAndTemplateId/{roleId}/{routeViewId}/{templateId}")
    public DataView postRoleRouteViewTemplateByRoleIdAndRouteViewIdAndTemplateId(@PathVariable String roleId, @PathVariable String routeViewId, @PathVariable String templateId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.roleRouteViewTemplateService.postRoleRouteViewTemplateByRoleIdAndRouteViewIdAndTemplateId(moduleId, roleId, routeViewId, templateId);
        return DataViewUtil.getMessageViewSuccess("新增角色路由视图模板成功");
    }

    @Override
    @RequestMapping(value = "/post/roleRouteViewTemplateByRoleIdsAndRouteViewIdAndTemplateId/{roleIds}/{routeViewId}/{templateId}")
    public DataView postRoleRouteViewTemplateByRoleIdsAndRouteViewIdAndTemplateId(@PathVariable String roleIds, @PathVariable String routeViewId, @PathVariable String templateId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.roleRouteViewTemplateService.postRoleRouteViewTemplateByRoleIdsAndRouteViewIdAndTemplateId(moduleId, Arrays.asList(roleIds.split(",")), routeViewId, templateId);
        return DataViewUtil.getMessageViewSuccess("新增角色路由视图模板成功");
    }

    @Override
    @RequestMapping(value = "/delete/roleRouteViewTemplateByRoleRouteViewTemplateId/{roleRouteViewTemplateId}")
    public DataView deleteRoleRouteViewTemplateByRoleRouteViewTemplateId(@PathVariable String roleRouteViewTemplateId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.roleRouteViewTemplateService.deleteRoleRouteViewTemplateByRoleRouteViewTemplateId(moduleId, roleRouteViewTemplateId);
        return DataViewUtil.getMessageViewSuccess("删除角色路由视图模板成功");
    }

    @Override
    @RequestMapping(value = "/delete/list/roleRouteViewTemplateByRoleRouteViewTemplateIds/{roleRouteViewTemplateIds}")
    public DataView deleteListRoleRouteViewTemplateByRoleRouteViewTemplateIds(@PathVariable String roleRouteViewTemplateIds, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.roleRouteViewTemplateService.deleteListRoleRouteViewTemplateByRoleRouteViewTemplateIds(moduleId, Arrays.asList(roleRouteViewTemplateIds.split(",")));
        return DataViewUtil.getMessageViewSuccess("删除角色路由视图模板成功");
    }

    @Override
    @RequestMapping(value = "/delete/roleRouteViewTemplateByRoleIdAndRouteViewIdAndTemplateId/{roleId}/{routeViewId}/{templateId}")
    public DataView deleteRoleRouteViewTemplateByRoleIdAndRouteViewIdAndTemplateId(@PathVariable String roleId, @PathVariable String routeViewId, @PathVariable String templateId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.roleRouteViewTemplateService.deleteRoleRouteViewTemplateByRoleIdAndRouteViewIdAndTemplateId(moduleId, roleId, routeViewId, templateId);
        return DataViewUtil.getMessageViewSuccess("删除角色路由视图模板成功");
    }
}
