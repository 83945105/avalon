package pub.avalonframework.cloud.gar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pub.avalon.beans.EnumMethods;
import pub.avalon.beans.Time;
import pub.avalon.holygrail.response.beans.Status;
import pub.avalon.holygrail.response.utils.ExceptionUtil;
import pub.avalon.holygrail.utils.StringUtil;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.spring.core.SpringJdbcEngine;
import pub.avalonframework.cloud.gar.dc.AutRoleMenuGet;
import pub.avalonframework.cloud.gar.entity.AutRole;
import pub.avalonframework.cloud.gar.entity.AutRoleMenu;
import pub.avalonframework.cloud.gar.entity.Menu;
import pub.avalonframework.cloud.gar.model.AutRoleMenuModel;
import pub.avalonframework.cloud.gar.model.AutRoleModel;
import pub.avalonframework.cloud.gar.model.MenuModel;
import pub.avalonframework.cloud.gar.service.GarRoleMenuService;
import pub.avalonframework.cloud.gar.utils.TableUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 白超
 */
@Service
public class GarRoleMenuServiceImpl implements GarRoleMenuService {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    public boolean getValidateRoleMenuExistByRoleIdAndMenuId(String moduleId, String roleId, String menuId) throws Exception {
        String roleMenuTableName = TableUtils.getRoleMenuTableName(moduleId);
        return this.jdbcEngine.queryCount(MySqlDynamicEngine.query(roleMenuTableName, AutRoleMenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.roleId().equalTo(roleId).menuId().equalTo(menuId)))) > 0;
    }

    @Override
    public AutRoleMenuGet postRoleMenuByRoleIdAndMenuId(String moduleId, String roleId, String menuId) throws Exception {
        String roleTableName = TableUtils.getRoleTableName(moduleId);
        String roleMenuTableName = TableUtils.getRoleMenuTableName(moduleId);
        if (StringUtil.isEmpty(roleId)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        if (StringUtil.isEmpty(menuId)) {
            ExceptionUtil.throwFailException("菜单ID不能为空");
        }
        if (this.getValidateRoleMenuExistByRoleIdAndMenuId(moduleId, roleId, menuId)) {
            ExceptionUtil.throwFailException("该角色已经拥有该菜单");
        }
        AutRole role = this.jdbcEngine.queryByPrimaryKey(roleId, AutRole.class, MySqlDynamicEngine.query(roleTableName, AutRoleModel.class));
        if (role == null) {
            ExceptionUtil.throwFailException("角色不存在");
        }
        if (!EnumMethods.equalsTo(role.getStatus(), Status.NORMAL)) {
            ExceptionUtil.throwFailException("角色不可用");
        }
        Menu menu = this.jdbcEngine.queryByPrimaryKey(menuId, Menu.class, MySqlDynamicEngine.query(MenuModel.class));
        if (menu == null) {
            ExceptionUtil.throwFailException("菜单不存在");
        }
        if (!EnumMethods.equalsTo(menu.getStatus(), Status.NORMAL)) {
            ExceptionUtil.throwFailException("菜单不可用");
        }

        String timeString = Time.localDateTimeNow();
        long timeStamp = Time.timeStamp();
        AutRoleMenu roleMenuInsert = new AutRoleMenu();

        roleMenuInsert.setModuleId(moduleId);

        roleMenuInsert.setRoleId(role.getId());
        roleMenuInsert.setRoleValue(role.getValue());
        roleMenuInsert.setRoleName(role.getName());
        roleMenuInsert.setRoleType(role.getType());

        roleMenuInsert.setMenuGroupId(menu.getMenuGroupId());
        roleMenuInsert.setMenuId(menu.getId());
        roleMenuInsert.setMenuName(menu.getName());
        roleMenuInsert.setMenuValue(menu.getValue());

        roleMenuInsert.setStatus(Status.NORMAL.name());
        roleMenuInsert.setCreateTime(timeString);
        roleMenuInsert.setCreateTimeStamp(timeStamp);

        int count = this.jdbcEngine.insertJavaBeanSelective(roleMenuInsert, MySqlDynamicEngine.insert(roleMenuTableName, AutRoleMenuModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("新增角色菜单失败");
        }
        return this.jdbcEngine.queryOne(AutRoleMenuGet.class, MySqlDynamicEngine.query(roleMenuTableName, AutRoleMenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .menuId().equalTo(menuId)
                                .roleId().equalTo(roleId))));
    }

    @Override
    public void putListRoleMenuByRoleId(String moduleId, String roleId, AutRoleMenu roleMenuUpdate) throws Exception {
        String roleMenuTableName = TableUtils.getRoleMenuTableName(moduleId);
        if (StringUtil.isEmpty(roleId)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        this.jdbcEngine.updateJavaBeanSelective(roleMenuUpdate, MySqlDynamicEngine.update(roleMenuTableName, AutRoleMenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.roleId().equalTo(roleId))));
    }

    @Override
    public void putListRoleMenuByMenuId(String moduleId, String menuId, AutRoleMenu roleMenuUpdate) throws Exception {
        String roleMenuTableName = TableUtils.getRoleMenuTableName(moduleId);
        if (StringUtil.isEmpty(menuId)) {
            ExceptionUtil.throwFailException("菜单ID不能为空");
        }
        this.jdbcEngine.updateJavaBeanSelective(roleMenuUpdate, MySqlDynamicEngine.update(roleMenuTableName, AutRoleMenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.menuId().equalTo(menuId))));
    }

    @Override
    public void deleteListRoleMenuByMenuGroupId(String moduleId, String menuGroupId) throws Exception {
        String roleMenuTableName = TableUtils.getRoleMenuTableName(moduleId);
        if (StringUtil.isEmpty(menuGroupId)) {
            ExceptionUtil.throwFailException("菜单组ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(roleMenuTableName, AutRoleMenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.menuGroupId().equalTo(menuGroupId))));
    }

    @Override
    public void deleteListRoleMenuByMenuGroupIds(String moduleId, List<String> menuGroupIds) throws Exception {
        String roleMenuTableName = TableUtils.getRoleMenuTableName(moduleId);
        if (StringUtil.isEmpty(menuGroupIds)) {
            ExceptionUtil.throwFailException("菜单组ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(roleMenuTableName, AutRoleMenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.menuGroupId().inS(menuGroupIds))));
    }

    @Override
    public void deleteListRoleMenuByRoleId(String moduleId, String roleId) throws Exception {
        String roleMenuTableName = TableUtils.getRoleMenuTableName(moduleId);
        if (StringUtil.isEmpty(roleId)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(roleMenuTableName, AutRoleMenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.roleId().equalTo(roleId))));
    }

    @Override
    public void deleteListRoleMenuByRoleIds(String moduleId, List<String> roleIds) throws Exception {
        String roleMenuTableName = TableUtils.getRoleMenuTableName(moduleId);
        if (StringUtil.isEmpty(roleIds)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(roleMenuTableName, AutRoleMenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.roleId().inS(roleIds))));
    }

    @Override
    public void deleteListRoleMenuByMenuId(String moduleId, String menuId) throws Exception {
        String roleMenuTableName = TableUtils.getRoleMenuTableName(moduleId);
        if (StringUtil.isEmpty(menuId)) {
            ExceptionUtil.throwFailException("菜单ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(roleMenuTableName, AutRoleMenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.menuId().equalTo(menuId))));
    }

    @Override
    public void deleteListRoleMenuByMenuIds(String moduleId, List<String> menuIds) throws Exception {
        String roleMenuTableName = TableUtils.getRoleMenuTableName(moduleId);
        if (StringUtil.isEmpty(menuIds)) {
            ExceptionUtil.throwFailException("菜单ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(roleMenuTableName, AutRoleMenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.menuId().inS(menuIds))));
    }

    @Override
    public void deleteRoleMenuByRoleMenuId(String moduleId, String roleMenuId) throws Exception {
        String roleMenuTableName = TableUtils.getRoleMenuTableName(moduleId);
        if (StringUtil.isEmpty(roleMenuId)) {
            ExceptionUtil.throwFailException("角色菜单ID不能为空");
        }
        int count = this.jdbcEngine.deleteByPrimaryKey(roleMenuId, MySqlDynamicEngine.delete(roleMenuTableName, AutRoleMenuModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("删除角色菜单失败");
        }
    }

    @Override
    public void deleteListRoleMenuByRoleMenuIds(String moduleId, Set<String> roleMenuIds) throws Exception {
        String roleMenuTableName = TableUtils.getRoleMenuTableName(moduleId);
        if (StringUtil.isEmpty(roleMenuIds)) {
            ExceptionUtil.throwFailException("角色菜单ID不能为空");
        }
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(roleMenuTableName, AutRoleMenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().inS(roleMenuIds))));
        if (count != roleMenuIds.size()) {
            ExceptionUtil.throwFailException("删除角色菜单失败");
        }
    }

    @Override
    public void deleteRoleMenuByRoleIdAndMenuId(String moduleId, String roleId, String menuId) throws Exception {
        String roleMenuTableName = TableUtils.getRoleMenuTableName(moduleId);
        if (StringUtil.isEmpty(roleId)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        if (StringUtil.isEmpty(menuId)) {
            ExceptionUtil.throwFailException("菜单ID不能为空");
        }
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(roleMenuTableName, AutRoleMenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.menuId().equalTo(menuId)
                                .roleId().equalTo(roleId))));
        if (count != 1) {
            ExceptionUtil.throwFailException("删除角色菜单失败");
        }
    }

    @Override
    public void deleteListRoleMenuByRoleIdsAndMenuIds(String moduleId, Set<String> roleIds, Set<String> menuIds) throws Exception {
        String roleMenuTableName = TableUtils.getRoleMenuTableName(moduleId);
        if (StringUtil.isEmpty(roleIds)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        if (StringUtil.isEmpty(menuIds)) {
            ExceptionUtil.throwFailException("菜单ID不能为空");
        }
        List<String> roleMenuIdsByRoleIds = this.jdbcEngine.queryColumnList(AutRoleMenuModel.primaryKeyAlias, String.class, MySqlDynamicEngine.query(roleMenuTableName, AutRoleMenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.roleId().inS(roleIds))));
        List<String> roleMenuIdsByMenuIds = this.jdbcEngine.queryColumnList(AutRoleMenuModel.primaryKeyAlias, String.class, MySqlDynamicEngine.query(roleMenuTableName, AutRoleMenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.menuId().inS(menuIds))));
        //找出要删除的ID
        Set<String> ids = new HashSet<>();
        for (String roleMenuIdsByRoleId : roleMenuIdsByRoleIds) {
            for (String roleMenuIdsByMenuId : roleMenuIdsByMenuIds) {
                if (roleMenuIdsByRoleId.equals(roleMenuIdsByMenuId)) {
                    ids.add(roleMenuIdsByRoleId);
                }
            }
        }
        if (StringUtil.isEmpty(ids)) {
            ExceptionUtil.throwFailException("没有可以删除的角色菜单");
        }
        //执行删除
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(roleMenuTableName, AutRoleMenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().inS(ids))));
        if (count != ids.size()) {
            ExceptionUtil.throwFailException("删除角色菜单失败");
        }
    }

}
