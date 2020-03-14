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
import pub.avalonframework.cloud.gar.api.GarRoleMenuApi;
import pub.avalonframework.cloud.gar.dc.AutRoleMenuGet;
import pub.avalonframework.cloud.gar.entity.AutRoleMenu;
import pub.avalonframework.cloud.gar.model.AutRoleMenuModel;
import pub.avalonframework.cloud.gar.service.GarRoleMenuService;
import pub.avalonframework.cloud.gar.utils.TableUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author 白超
 */
@RestController
@RequestMapping(GarRoleMenuApi.ROOT_PATH)
public class GarRoleMenuController implements GarRoleMenuApi {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired
    private GarRoleMenuService roleMenuService;

    @Autowired
    private HttpServletRequest request;

    @Override
    @RequestMapping(value = "/get/list/roleMenu")
    public DataView getListRoleMenu(AutRoleMenuGet record, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        String roleMenuTableName = TableUtils.getRoleMenuTableName(finalModuleId);
        List<Map<String, Object>> rows = this.jdbcEngine.queryForList(MySqlDynamicEngine.query(roleMenuTableName, AutRoleMenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(finalModuleId))));
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/get/list/roleMenuByRoleId/{roleId}")
    public DataView getListRoleMenuByRoleId(@PathVariable String roleId, AutRoleMenuGet record, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        String roleMenuTableName = TableUtils.getRoleMenuTableName(finalModuleId);
        List<Map<String, Object>> rows = this.jdbcEngine.queryForList(MySqlDynamicEngine.query(roleMenuTableName, AutRoleMenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(finalModuleId)
                                .roleId().equalTo(roleId))));
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/get/list/roleMenuByMenuId/{menuId}")
    public DataView getListRoleMenuByMenuId(@PathVariable String menuId, AutRoleMenuGet record, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        String roleMenuTableName = TableUtils.getRoleMenuTableName(finalModuleId);
        List<Map<String, Object>> rows = this.jdbcEngine.queryForList(MySqlDynamicEngine.query(roleMenuTableName, AutRoleMenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(finalModuleId)
                                .menuId().equalTo(menuId))));
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/post/roleMenuByRoleIdAndMenuId/{roleId}/{menuId}")
    public DataView postRoleMenuByRoleIdAndMenuId(@PathVariable String roleId, @PathVariable String menuId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        AutRoleMenu roleMenu = this.roleMenuService.postRoleMenuByRoleIdAndMenuId(moduleId, roleId, menuId);
        return DataViewUtil.getModelViewSuccess("新建角色菜单成功", Collections.singletonMap("roleMenu", roleMenu));
    }

    @Override
    @RequestMapping(value = "/delete/roleMenuByRoleMenuId/{roleMenuId}")
    public DataView deleteRoleMenuByRoleMenuId(@PathVariable String roleMenuId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.roleMenuService.deleteRoleMenuByRoleMenuId(moduleId, roleMenuId);
        return DataViewUtil.getMessageViewSuccess("删除角色菜单成功");
    }

    @Override
    @RequestMapping(value = "/delete/list/roleMenuByRoleMenuIds/{roleMenuIds}")
    public DataView deleteListRoleMenuByRoleMenuIds(@PathVariable String roleMenuIds, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.roleMenuService.deleteListRoleMenuByRoleMenuIds(moduleId,
                StringUtil.isEmpty(roleMenuIds) ? null : new HashSet<>(Arrays.asList(roleMenuIds.split(","))));
        return DataViewUtil.getMessageViewSuccess("删除角色菜单成功");
    }

    @Override
    @RequestMapping(value = "/delete/roleMenuByRoleIdAndMenuId/{roleId}/{menuId}")
    public DataView deleteRoleMenuByRoleIdAndMenuId(@PathVariable String roleId, @PathVariable String menuId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.roleMenuService.deleteRoleMenuByRoleIdAndMenuId(moduleId, roleId, menuId);
        return DataViewUtil.getMessageViewSuccess("删除角色菜单成功");
    }

    @Override
    @RequestMapping(value = "/delete/list/roleMenuByRoleIdsAndMenuIds/{roleIds}/{menuIds}")
    public DataView deleteListRoleMenuByRoleIdsAndMenuIds(@PathVariable String roleIds, @PathVariable String menuIds, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.roleMenuService.deleteListRoleMenuByRoleIdsAndMenuIds(moduleId,
                StringUtil.isEmpty(roleIds) ? null : new HashSet<>(Arrays.asList(roleIds.split(","))),
                StringUtil.isEmpty(menuIds) ? null : new HashSet<>(Arrays.asList(menuIds.split(","))));
        return DataViewUtil.getMessageViewSuccess("删除角色菜单成功");
    }
}