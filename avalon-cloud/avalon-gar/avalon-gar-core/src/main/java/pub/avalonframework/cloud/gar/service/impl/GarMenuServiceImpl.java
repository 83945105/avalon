package pub.avalonframework.cloud.gar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pub.avalon.beans.EnumMethods;
import pub.avalon.beans.Time;
import pub.avalon.holygrail.response.beans.Status;
import pub.avalon.holygrail.response.utils.ExceptionUtil;
import pub.avalon.holygrail.utils.StringUtil;
import pub.avalon.sqlhelper.core.beans.FunctionColumnType;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.spring.core.SpringJdbcEngine;
import pub.avalonframework.cloud.gar.beans.MenuUseTab;
import pub.avalonframework.cloud.gar.dc.MenuGet;
import pub.avalonframework.cloud.gar.dc.MenuPost;
import pub.avalonframework.cloud.gar.dc.MenuPut;
import pub.avalonframework.cloud.gar.entity.*;
import pub.avalonframework.cloud.gar.model.MenuModel;
import pub.avalonframework.cloud.gar.model.ModuleModel;
import pub.avalonframework.cloud.gar.model.SubModuleModel;
import pub.avalonframework.cloud.gar.service.GarMenuRouteService;
import pub.avalonframework.cloud.gar.service.GarMenuService;
import pub.avalonframework.cloud.gar.service.GarRoleMenuService;
import pub.avalonframework.cloud.gar.utils.TableUtils;

import java.util.*;

/**
 * @author 白超
 * @date 2018/12/6
 */
@Service
public class GarMenuServiceImpl implements GarMenuService {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired
    private GarRoleMenuService roleMenuService;
    @Autowired
    private GarMenuRouteService menuRouteService;

    @Override
    public boolean getValidateValueCanUseBySubModuleId(String moduleId, String subModuleId, String value, List<String> excludeValues) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(subModuleId)) {
            ExceptionUtil.throwFailException("子模块ID不能为空");
        }
        if (StringUtil.isEmpty(value)) {
            ExceptionUtil.throwFailException("菜单唯一标识符不能为空");
        }
        if (!value.matches(TableUtils.MENU_VALUE_REGEX)) {
            ExceptionUtil.throwFailException("菜单唯一标识符格式不正确");
        }
        return this.jdbcEngine.queryCount(MySqlDynamicEngine.query(MenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .subModuleId().equalTo(subModuleId)
                                .value().equalTo(value)
                                .value().notInS(excludeValues)))) == 0;
    }

    public Set<String> getChildrenIds(String id) {
        if (StringUtil.isEmpty(id)) {
            return new HashSet<>(0);
        }
        Set<String> ids = new HashSet<>();
        // 查出子级
        ids.addAll(this.jdbcEngine.queryColumnList(MenuModel.id_alias, String.class, MySqlDynamicEngine.query(MenuModel.class)
                .column(MenuModel.Column::id)
                .where((condition, mainTable) -> condition.and(mainTable.parentId().equalTo(id)))));
        // 查出子级下所有的子级
        ids.addAll(this.jdbcEngine.queryColumnList(MenuModel.id_alias, String.class, MySqlDynamicEngine.query(MenuModel.class)
                .column(MenuModel.Column::id)
                .where((condition, mainTable) -> condition.and(mainTable.parentIds().like("%/" + id + "/%")))));
        return ids;
    }

    @Override
    public MenuGet postMenu(String moduleId, MenuPost record) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(record.getSubModuleId())) {
            ExceptionUtil.throwFailException("子模块ID不能为空");
        }
        if (StringUtil.isEmpty(record.getMenuGroupId())) {
            ExceptionUtil.throwFailException("菜单组ID不能为空");
        }
        if (StringUtil.isEmpty(record.getName())) {
            ExceptionUtil.throwFailException("菜单名称不能为空");
        }
        if (StringUtil.isEmpty(record.getValue())) {
            ExceptionUtil.throwFailException("菜单唯一标识符不能为空");
        }
        if (!this.getValidateValueCanUseBySubModuleId(moduleId, record.getSubModuleId(), record.getValue(), null)) {
            ExceptionUtil.throwFailException("菜单唯一标识符不可用");
        }
        if (StringUtil.isEmpty(record.getUseTab())) {
            ExceptionUtil.throwFailException("是否使用选项卡不能为空");
        }
        if (!EnumMethods.contains(record.getUseTab(), MenuUseTab.values())) {
            ExceptionUtil.throwFailException("是否使用选项卡值不正确");
        }
        if (EnumMethods.equalsTo(record.getUseTab(), MenuUseTab.TRUE)) {
            if (StringUtil.isEmpty(record.getInitOpenInTab())) {
                record.setInitOpenInTab("true");
            }
            if (StringUtil.isEmpty(record.getClosableInTab())) {
                record.setClosableInTab("true");
            }
            if (StringUtil.isEmpty(record.getCacheInTab())) {
                record.setCacheInTab("true");
            }
        } else if (EnumMethods.equalsTo(record.getUseTab(), MenuUseTab.FALSE)) {
            if (StringUtil.isEmpty(record.getInitOpenInTab())) {
                record.setInitOpenInTab("false");
            }
            if (StringUtil.isEmpty(record.getClosableInTab())) {
                record.setClosableInTab("false");
            }
            if (StringUtil.isEmpty(record.getCacheInTab())) {
                record.setCacheInTab("false");
            }
        }
        if (StringUtil.isEmpty(record.getStatus())) {
            record.setStatus(Status.NORMAL.name());
        } else if (!EnumMethods.contains(record.getStatus(), Status.values())) {
            ExceptionUtil.throwFailException("状态类型不正确");
        }

        Module module = this.jdbcEngine.queryByPrimaryKey(moduleId, Module.class, MySqlDynamicEngine.query(ModuleModel.class));
        if (module == null) {
            ExceptionUtil.throwFailException("模块不存在");
        }

        SubModule subModule = this.jdbcEngine.queryByPrimaryKey(record.getSubModuleId(), SubModule.class, MySqlDynamicEngine.query(SubModuleModel.class));
        if (subModule == null) {
            ExceptionUtil.throwFailException("子模块不存在");
        }

        String id = subModule.getId() + "-" + record.getValue();

        Menu menuInsert = new Menu();

        menuInsert.setId(id);
        menuInsert.setModuleId(moduleId);
        menuInsert.setSubModuleId(subModule.getId());
        menuInsert.setSubModuleName(subModule.getName());
        menuInsert.setMenuGroupId(record.getMenuGroupId());
        menuInsert.setName(record.getName());
        menuInsert.setValue(record.getValue());
        menuInsert.setIconName(record.getIconName());
        menuInsert.setUseTab(record.getUseTab());
        menuInsert.setCacheInTab(record.getCacheInTab());
        menuInsert.setClosableInTab(record.getClosableInTab());
        menuInsert.setInitOpenInTab(record.getInitOpenInTab());
        menuInsert.setDescription(record.getDescription());

        if (StringUtil.isEmpty(record.getParentId())) {
            //没有父级菜单
            menuInsert.setParentId("");
            menuInsert.setParentIds("");
        } else {
            //有父级菜单
            Menu parentMenu = this.jdbcEngine.queryOne(Menu.class, MySqlDynamicEngine.query(MenuModel.class)
                    .where((condition, mainTable) -> condition
                            .and(mainTable.moduleId().equalTo(moduleId)
                                    .id().equalTo(record.getParentId()))));
            if (parentMenu == null) {
                ExceptionUtil.throwFailException("父级菜单不存在");
            }
            menuInsert.setParentId(parentMenu.getId());
            String parentIds = parentMenu.getParentIds();
            if (StringUtil.isEmpty(parentIds)) {
                parentIds = "";
            }
            menuInsert.setParentIds(parentIds + "/" + parentMenu.getId());
        }

        menuInsert.setStatus(record.getStatus());
        menuInsert.setCreateTime(Time.localDateTimeNow());
        menuInsert.setCreateTimeStamp(Time.timeStamp());

        Long index = this.jdbcEngine.queryColumnOne(1, Long.class, MySqlDynamicEngine.query(MenuModel.class)
                .functionColumn(FunctionColumnType.MAX, MenuModel.Column::index));
        menuInsert.setIndex(TableUtils.getMenuIndex(index));

        int count = this.jdbcEngine.insertJavaBeanSelective(menuInsert, MySqlDynamicEngine.insert(MenuModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("新增菜单失败");
        }
        return this.jdbcEngine.queryByPrimaryKey(id, MenuGet.class, MySqlDynamicEngine.query(MenuModel.class));
    }

    @Override
    public void putMenuByMenuId(String moduleId, String menuId, MenuPut record) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(menuId)) {
            ExceptionUtil.throwFailException("菜单ID不能为空");
        }
        Menu menu = this.jdbcEngine.queryOne(Menu.class, MySqlDynamicEngine.query(MenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId).id().equalTo(menuId))));
        if (menu == null) {
            ExceptionUtil.throwFailException("菜单不存在");
        }

        Menu menuUpdate = new Menu();
        AutRoleMenu roleMenuUpdate = null;
        MenuRoute menuRouteUpdate = null;

        if (!StringUtil.isEmpty(record.getName()) && !record.getName().equals(menu.getName())) {
            menuUpdate.setName(record.getName());
            roleMenuUpdate = new AutRoleMenu();
            roleMenuUpdate.setMenuName(record.getName());
            menuRouteUpdate = new MenuRoute();
            menuRouteUpdate.setMenuName(record.getName());
        }
        if (!StringUtil.isEmpty(record.getValue()) && !record.getValue().equals(menu.getValue())) {
            if (!getValidateValueCanUseBySubModuleId(moduleId, menu.getSubModuleId(), record.getValue(), Collections.singletonList(menu.getValue()))) {
                ExceptionUtil.throwFailException("菜单唯一标识符不可用");
            }
            menuUpdate.setValue(record.getValue());
            if (roleMenuUpdate == null) {
                roleMenuUpdate = new AutRoleMenu();
            }
            roleMenuUpdate.setMenuValue(record.getValue());
            if (menuRouteUpdate == null) {
                menuRouteUpdate = new MenuRoute();
            }
            menuRouteUpdate.setMenuValue(record.getValue());
        }
        menuUpdate.setIconName(record.getIconName());
        menuUpdate.setDescription(record.getDescription());
        if (!StringUtil.isEmpty(record.getUseTab()) && !record.getUseTab().equals(menu.getUseTab())) {
            if (!EnumMethods.contains(record.getUseTab(), MenuUseTab.values())) {
                ExceptionUtil.throwFailException("是否使用选项卡值不正确");
            }
            menuUpdate.setUseTab(record.getUseTab());
        }
        if (!StringUtil.isEmpty(record.getInitOpenInTab()) && !record.getInitOpenInTab().equals(menu.getInitOpenInTab())) {
            menuUpdate.setInitOpenInTab(record.getInitOpenInTab());
        }
        if (!StringUtil.isEmpty(record.getClosableInTab()) && !record.getClosableInTab().equals(menu.getClosableInTab())) {
            menuUpdate.setClosableInTab(record.getClosableInTab());
        }
        if (!StringUtil.isEmpty(record.getCacheInTab()) && !record.getCacheInTab().equals(menu.getCacheInTab())) {
            menuUpdate.setCacheInTab(record.getCacheInTab());
        }

        if (!StringUtil.isEmpty(record.getStatus()) && !record.getStatus().equals(menu.getStatus())) {
            if (!EnumMethods.contains(record.getStatus(), Status.values())) {
                ExceptionUtil.throwFailException("状态类型不正确");
            }
            menuUpdate.setStatus(record.getStatus());
        }

        String timeString = Time.localDateTimeNow();
        Long timeStamp = Time.timeStamp();

        menuUpdate.setUpdateTime(timeString);
        menuUpdate.setUpdateTimeStamp(timeStamp);

        int count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(menuId, menuUpdate, MySqlDynamicEngine.update(MenuModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("更新菜单失败");
        }

        //更新角色菜单视图
        if (roleMenuUpdate != null) {
            roleMenuUpdate.setUpdateTime(timeString);
            roleMenuUpdate.setUpdateTimeStamp(timeStamp);
            this.roleMenuService.putListRoleMenuByMenuId(moduleId, menuId, roleMenuUpdate);
        }

        //更新菜单路由
        if (menuRouteUpdate != null) {
            menuRouteUpdate.setUpdateTime(timeString);
            menuRouteUpdate.setUpdateTimeStamp(timeStamp);
            this.menuRouteService.putListMenuRouteByMenuId(moduleId, menuId, menuRouteUpdate);
        }

    }

    @Override
    public void putListMenuByModuleIdAndSubModuleId(String moduleId, String subModuleId, Menu menuUpdate) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(subModuleId)) {
            ExceptionUtil.throwFailException("子模块ID不能为空");
        }
        this.jdbcEngine.updateJavaBeanSelective(menuUpdate, MySqlDynamicEngine.update(MenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .subModuleId().equalTo(subModuleId))));
    }

    @Override
    public void putSwitchMenuIndexByMenuId(String moduleId, String sourceMenuId, String targetMenuId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        Menu sourceMenu = this.jdbcEngine.queryOne(Menu.class, MySqlDynamicEngine.query(MenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .id().equalTo(sourceMenuId))));
        if (sourceMenu == null) {
            ExceptionUtil.throwFailException(40404, "菜单不存在");
        }
        Menu targetMenu = this.jdbcEngine.queryOne(Menu.class, MySqlDynamicEngine.query(MenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .id().equalTo(targetMenuId))));
        if (targetMenu == null) {
            ExceptionUtil.throwFailException(40404, "菜单不存在");
        }
        Menu menuUpdate = new Menu();
        menuUpdate.setIndex(targetMenu.getIndex());
        menuUpdate.setUpdateTime(Time.localDateTimeNow());
        menuUpdate.setUpdateTimeStamp(Time.timeStamp());

        int count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(sourceMenuId, menuUpdate, MySqlDynamicEngine.update(MenuModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("操作失败");
        }
        menuUpdate.setIndex(sourceMenu.getIndex());
        count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(targetMenuId, menuUpdate, MySqlDynamicEngine.update(MenuModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("操作失败");
        }
    }

    @Override
    public void deleteMenuByMenuId(String moduleId, String menuId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(menuId)) {
            ExceptionUtil.throwFailException("菜单ID不能为空");
        }
        //查询出所有子菜单的ID
        Set<String> ids = this.getChildrenIds(menuId);
        ids.add(menuId);
        //删除菜单
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(MenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .id().inS(ids))));
        if (count != ids.size()) {
            ExceptionUtil.throwFailException("删除菜单失败");
        }
        //删除角色菜单数据
        this.roleMenuService.deleteListRoleMenuByMenuIds(moduleId, new ArrayList<>(ids));
        //删除菜单路由数据
        this.menuRouteService.deleteListMenuRouteByMenuIds(moduleId, new ArrayList<>(ids));
    }

    @Override
    public void deleteListMenuBySubModuleId(String moduleId, String subModuleId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(subModuleId)) {
            ExceptionUtil.throwFailException("子模块ID不能为空");
        }
        //查询子模块下拥有的菜单ID
        List<String> menuIds = this.jdbcEngine.queryColumnList(MenuModel.id_alias, String.class, MySqlDynamicEngine.query(MenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .subModuleId().equalTo(subModuleId))));
        if (menuIds.size() == 0) {
            return;
        }
        //查询出所有子菜单的ID
        Set<String> ids = new HashSet<>();
        for (String menuId : menuIds) {
            ids.addAll(this.getChildrenIds(menuId));
            ids.add(menuId);
        }
        //删除菜单
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(MenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .id().inS(ids))));
        if (count != ids.size()) {
            ExceptionUtil.throwFailException("删除菜单失败");
        }
        //删除角色菜单数据
        this.roleMenuService.deleteListRoleMenuByMenuIds(moduleId, new ArrayList<>(ids));
        //删除菜单路由数据
        this.menuRouteService.deleteListMenuRouteByMenuIds(moduleId, new ArrayList<>(ids));
    }

    @Override
    public void deleteListMenuBySubModuleIds(String moduleId, List<String> subModuleIds) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(subModuleIds)) {
            ExceptionUtil.throwFailException("子模块ID不能为空");
        }
        //查询子模块下拥有的菜单ID
        List<String> menuIds = this.jdbcEngine.queryColumnList(MenuModel.id_alias, String.class, MySqlDynamicEngine.query(MenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .subModuleId().inS(subModuleIds))));
        if (menuIds.size() == 0) {
            return;
        }
        //查询出所有子菜单的ID
        Set<String> ids = new HashSet<>();
        for (String menuId : menuIds) {
            ids.addAll(this.getChildrenIds(menuId));
            ids.add(menuId);
        }
        //删除菜单
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(MenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .id().inS(ids))));
        if (count != ids.size()) {
            ExceptionUtil.throwFailException("删除菜单失败");
        }
        //删除角色菜单数据
        this.roleMenuService.deleteListRoleMenuByMenuIds(moduleId, new ArrayList<>(ids));
        //删除菜单路由数据
        this.menuRouteService.deleteListMenuRouteByMenuIds(moduleId, new ArrayList<>(ids));
    }

    @Override
    public void deleteListMenuByMenuGroupId(String moduleId, String menuGroupId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(menuGroupId)) {
            ExceptionUtil.throwFailException("菜单组ID不能为空");
        }
        //删除菜单
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(MenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .menuGroupId().equalTo(menuGroupId))));
        //删除角色菜单数据
        this.roleMenuService.deleteListRoleMenuByMenuGroupId(moduleId, menuGroupId);
        //删除菜单路由数据
        this.menuRouteService.deleteListMenuRouteByMenuGroupId(moduleId, menuGroupId);
    }

    @Override
    public void deleteListMenuByMenuGroupIds(String moduleId, List<String> menuGroupIds) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(menuGroupIds)) {
            ExceptionUtil.throwFailException("菜单组ID不能为空");
        }
        //删除菜单
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(MenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .menuGroupId().inS(menuGroupIds))));
        //删除角色菜单数据
        this.roleMenuService.deleteListRoleMenuByMenuGroupIds(moduleId, menuGroupIds);
        //删除菜单路由数据
        this.menuRouteService.deleteListMenuRouteByMenuGroupIds(moduleId, menuGroupIds);
    }

    @Override
    public void deleteListMenuByMenuIds(String moduleId, List<String> menuIds) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(menuIds)) {
            ExceptionUtil.throwFailException("菜单ID不能为空");
        }
        //查询出所有子菜单的ID
        Set<String> ids = new HashSet<>();
        for (String menuId : menuIds) {
            ids.addAll(this.getChildrenIds(menuId));
            ids.add(menuId);
        }
        //删除菜单
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(MenuModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .id().inS(ids))));
        if (count != ids.size()) {
            ExceptionUtil.throwFailException("删除菜单失败");
        }
        //删除角色菜单数据
        this.roleMenuService.deleteListRoleMenuByMenuIds(moduleId, new ArrayList<>(ids));
        //删除菜单路由数据
        this.menuRouteService.deleteListMenuRouteByMenuIds(moduleId, new ArrayList<>(ids));
    }

}
