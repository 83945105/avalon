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
import pub.avalonframework.cloud.gar.api.GarRoleResourceApi;
import pub.avalonframework.cloud.gar.dc.AutRoleResourceGet;
import pub.avalonframework.cloud.gar.model.AutRoleResourceModel;
import pub.avalonframework.cloud.gar.service.GarRoleResourceService;
import pub.avalonframework.cloud.gar.utils.TableUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author 白超
 * @date 2018/7/23
 */
@RestController
@RequestMapping(GarRoleResourceApi.ROOT_PATH)
public class GarRoleResourceController implements GarRoleResourceApi {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired
    private GarRoleResourceService roleResourceService;

    @Autowired
    private HttpServletRequest request;

    @Override
    @RequestMapping(value = "/get/list/roleResourceByRoleId/{roleId}")
    public DataView getListRoleResourceByRoleId(@PathVariable String roleId, AutRoleResourceGet record, String moduleId) throws Exception {
        String roleResourceTableName = TableUtils.getRoleResourceTableName(moduleId, request);
        List<Map<String, Object>> rows = this.jdbcEngine.queryForList(MySqlDynamicEngine.query(roleResourceTableName, AutRoleResourceModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.roleId().equalTo(roleId))));
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/get/list/roleResourceByResourceId/{resourceId}")
    public DataView getListRoleResourceByResourceId(@PathVariable String resourceId, AutRoleResourceGet record, String moduleId) throws Exception {
        String roleResourceTableName = TableUtils.getRoleResourceTableName(moduleId, request);
        List<Map<String, Object>> rows = this.jdbcEngine.queryForList(MySqlDynamicEngine.query(roleResourceTableName, AutRoleResourceModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.resourceId().equalTo(resourceId))));
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/post/roleResourceByRoleIdAndResourceId/{roleId}/{resourceId}")
    public DataView postRoleResourceByRoleIdAndResourceId(@PathVariable String roleId, @PathVariable String resourceId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.roleResourceService.postRoleResourceByRoleIdAndResourceId(moduleId, roleId, resourceId);
        return DataViewUtil.getMessageViewSuccess("新建角色资源成功");
    }

    @Override
    @RequestMapping(value = "/post/list/roleResourceByRoleIdsAndResourceIds/{roleIds}/{resourceIds}")
    public DataView postListRoleResourceByRoleIdsAndResourceIds(@PathVariable String roleIds, @PathVariable String resourceIds, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.roleResourceService.postListRoleResourceByRoleIdsAndResourceIds(moduleId,
                StringUtil.isEmpty(roleIds) ? null : new HashSet<>(Arrays.asList(roleIds.split(","))),
                StringUtil.isEmpty(resourceIds) ? null : new HashSet<>(Arrays.asList(resourceIds.split(","))));
        return DataViewUtil.getMessageViewSuccess("新建角色资源成功");
    }

    @Override
    @RequestMapping(value = "/delete/roleResourceByRoleResourceId/{roleResourceId}")
    public DataView deleteRoleResourceByRoleResourceId(@PathVariable String roleResourceId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.roleResourceService.deleteRoleResourceByRoleResourceId(moduleId, roleResourceId);
        return DataViewUtil.getMessageViewSuccess("删除角色资源成功");
    }

    @Override
    @RequestMapping(value = "/delete/list/roleResourceByRoleResourceIds/{roleResourceIds}")
    public DataView deleteListRoleResourceByRoleResourceIds(@PathVariable String roleResourceIds, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.roleResourceService.deleteListRoleResourceByRoleResourceIds(moduleId,
                StringUtil.isEmpty(roleResourceIds) ? null : new HashSet<>(Arrays.asList(roleResourceIds.split(","))));
        return DataViewUtil.getMessageViewSuccess("删除角色资源成功");
    }

    @Override
    @RequestMapping(value = "/delete/roleResourceByRoleIdAndResourceId/{roleId}/{resourceId}")
    public DataView deleteRoleResourceByRoleIdAndResourceId(@PathVariable String roleId, @PathVariable String resourceId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.roleResourceService.deleteRoleResourceByRoleIdAndResourceId(moduleId, roleId, resourceId);
        return DataViewUtil.getMessageViewSuccess("删除角色资源成功");
    }

    @Override
    @RequestMapping(value = "/delete/list/roleResourceByRoleIdsAndResourceIds/{roleIds}/{resourceIds}")
    public DataView deleteListRoleResourceByRoleIdsAndResourceIds(@PathVariable String roleIds, @PathVariable String resourceIds, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.roleResourceService.deleteListRoleResourceByRoleIdsAndResourceIds(moduleId,
                StringUtil.isEmpty(roleIds) ? null : new HashSet<>(Arrays.asList(roleIds.split(","))),
                StringUtil.isEmpty(resourceIds) ? null : new HashSet<>(Arrays.asList(resourceIds.split(","))));
        return DataViewUtil.getMessageViewSuccess("删除角色资源成功");
    }

}
