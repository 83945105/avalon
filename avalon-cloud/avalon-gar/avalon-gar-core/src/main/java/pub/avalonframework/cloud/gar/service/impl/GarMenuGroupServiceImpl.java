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
import pub.avalonframework.cloud.gar.beans.MenuGroupType;
import pub.avalonframework.cloud.gar.beans.MenuGroupUse;
import pub.avalonframework.cloud.gar.dc.MenuGroupGet;
import pub.avalonframework.cloud.gar.dc.MenuGroupPost;
import pub.avalonframework.cloud.gar.dc.MenuGroupPut;
import pub.avalonframework.cloud.gar.entity.MenuGroup;
import pub.avalonframework.cloud.gar.entity.Module;
import pub.avalonframework.cloud.gar.entity.SubModule;
import pub.avalonframework.cloud.gar.model.MenuGroupModel;
import pub.avalonframework.cloud.gar.model.ModuleModel;
import pub.avalonframework.cloud.gar.model.SubModuleModel;
import pub.avalonframework.cloud.gar.service.GarMenuGroupService;
import pub.avalonframework.cloud.gar.service.GarMenuRouteService;
import pub.avalonframework.cloud.gar.service.GarMenuService;
import pub.avalonframework.cloud.gar.utils.TableUtils;

import java.util.List;
import java.util.UUID;

/**
 * @author 白超
 * @date 2018/12/6
 */
@Service
public class GarMenuGroupServiceImpl implements GarMenuGroupService {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired
    private GarMenuService menuService;
    @Autowired
    private GarMenuRouteService menuRouteService;

    @Override
    public MenuGroupGet postMenuGroup(String moduleId, MenuGroupPost record) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(record.getSubModuleId())) {
            ExceptionUtil.throwFailException("子模块ID不能为空");
        }
        if (StringUtil.isEmpty(record.getName())) {
            ExceptionUtil.throwFailException("菜单组名称不能为空");
        }
        if (StringUtil.isEmpty(record.getType())) {
            ExceptionUtil.throwFailException("菜单组类型不能为空");
        }
        if (!EnumMethods.contains(record.getType(), MenuGroupType.values())) {
            ExceptionUtil.throwFailException("菜单组类型不正确");
        }
        if (StringUtil.isEmpty(record.getUse())) {
            record.setUse(MenuGroupUse.FALSE.name());
        } else if (!EnumMethods.contains(record.getUse(), MenuGroupUse.values())) {
            ExceptionUtil.throwFailException("菜单组是否使用值不正确");
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

        String id = UUID.randomUUID().toString();

        MenuGroup menuGroupInsert = new MenuGroup();

        menuGroupInsert.setId(id);
        menuGroupInsert.setModuleId(moduleId);
        menuGroupInsert.setSubModuleId(subModule.getId());
        menuGroupInsert.setSubModuleName(subModule.getName());
        menuGroupInsert.setName(record.getName());
        menuGroupInsert.setDescription(record.getDescription());
        menuGroupInsert.setType(record.getType());
        menuGroupInsert.setUse(record.getUse());

        menuGroupInsert.setStatus(record.getStatus());
        menuGroupInsert.setCreateTime(Time.localDateTimeNow());
        menuGroupInsert.setCreateTimeStamp(Time.timeStamp());

        Long index = this.jdbcEngine.queryColumnOne(1, Long.class, MySqlDynamicEngine.query(MenuGroupModel.class)
                .functionColumn(FunctionColumnType.MAX, MenuGroupModel.Column::index));
        menuGroupInsert.setIndex(TableUtils.getMenuGroupIndex(index));

        int count = this.jdbcEngine.insertJavaBeanSelective(menuGroupInsert, MySqlDynamicEngine.insert(MenuGroupModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("新增菜单组失败");
        }
        return this.jdbcEngine.queryByPrimaryKey(id, MenuGroupGet.class, MySqlDynamicEngine.query(MenuGroupModel.class));
    }

    @Override
    public void putMenuGroupByMenuGroupId(String moduleId, String menuGroupId, MenuGroupPut record) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(menuGroupId)) {
            ExceptionUtil.throwFailException("菜单组ID不能为空");
        }
        MenuGroup menuGroup = this.jdbcEngine.queryOne(MenuGroup.class, MySqlDynamicEngine.query(MenuGroupModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId).id().equalTo(menuGroupId))));
        if (menuGroup == null) {
            ExceptionUtil.throwFailException("菜单组不存在");
        }

        MenuGroup menuGroupUpdate = new MenuGroup();

        if (!StringUtil.isEmpty(record.getName()) && !record.getName().equals(menuGroup.getName())) {
            menuGroupUpdate.setName(record.getName());
        }
        menuGroupUpdate.setDescription(record.getDescription());

        if (!StringUtil.isEmpty(record.getType()) && !record.getType().equals(menuGroup.getType())) {
            if (!EnumMethods.contains(record.getType(), MenuGroupType.values())) {
                ExceptionUtil.throwFailException("菜单组类型不正确");
            }
            menuGroupUpdate.setType(record.getType());
        }

        if (!StringUtil.isEmpty(record.getUse()) && !record.getUse().equals(menuGroup.getUse())) {
            if (!EnumMethods.contains(record.getUse(), MenuGroupUse.values())) {
                ExceptionUtil.throwFailException("菜单组是否使用值不正确");
            }
            menuGroupUpdate.setUse(record.getUse());
            if (EnumMethods.equalsTo(record.getUse(), MenuGroupUse.FALSE)) {
                // 如果取消使用菜单组, 要删除菜单组下所有菜单与路由之间的关系
                this.menuRouteService.deleteListMenuRouteByMenuGroupId(moduleId, menuGroupId);
            }
        }

        String timeString = Time.localDateTimeNow();
        Long timeStamp = Time.timeStamp();

        if (!StringUtil.isEmpty(record.getStatus()) && !record.getStatus().equals(menuGroup.getStatus())) {
            if (!EnumMethods.contains(record.getStatus(), Status.values())) {
                ExceptionUtil.throwFailException("状态类型不正确");
            }
            menuGroupUpdate.setStatus(record.getStatus());
        }

        menuGroupUpdate.setUpdateTime(timeString);
        menuGroupUpdate.setUpdateTimeStamp(timeStamp);

        int count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(menuGroupId, menuGroupUpdate, MySqlDynamicEngine.update(MenuGroupModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("更新菜单组失败");
        }

    }

    @Override
    public void putMenuGroupUseByMenuGroupId(String moduleId, String menuGroupId, String menuGroupUse) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(menuGroupId)) {
            ExceptionUtil.throwFailException("菜单组ID不能为空");
        }
        if (StringUtil.isEmpty(menuGroupUse)) {
            ExceptionUtil.throwFailException("菜单组是否使用不能为空");
        }
        if (!EnumMethods.contains(menuGroupUse, MenuGroupUse.values())) {
            ExceptionUtil.throwFailException("菜单组是否使用值不正确");
        }
        MenuGroup menuGroup = this.jdbcEngine.queryOne(MenuGroup.class, MySqlDynamicEngine.query(MenuGroupModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .id().equalTo(menuGroupId))));
        if (menuGroup == null) {
            ExceptionUtil.throwFailException("菜单组不存在");
        }

        if (menuGroupUse.equals(menuGroup.getUse())) {
            ExceptionUtil.throwFailException("该菜单组已经是该使用状态");
        }

        if (EnumMethods.equalsTo(menuGroupUse, MenuGroupUse.FALSE)) {
            // 如果取消使用菜单组, 要删除菜单组下所有菜单与路由之间的关系
            this.menuRouteService.deleteListMenuRouteByMenuGroupId(moduleId, menuGroupId);
        }

        MenuGroup menuGroupUpdate = new MenuGroup();

        menuGroupUpdate.setUpdateTime(Time.localDateTimeNow());
        menuGroupUpdate.setUpdateTimeStamp(Time.timeStamp());

        menuGroupUpdate.setUse(menuGroupUse);
        int count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(menuGroupId, menuGroupUpdate, MySqlDynamicEngine.update(MenuGroupModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("修改菜单组是否使用失败");
        }
    }

    @Override
    public void putSwitchMenuGroupIndexByMenuGroupId(String moduleId, String sourceMenuGroupId, String targetMenuGroupId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        MenuGroup sourceMenuGroup = this.jdbcEngine.queryOne(MenuGroup.class, MySqlDynamicEngine.query(MenuGroupModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .id().equalTo(sourceMenuGroupId))));
        if (sourceMenuGroup == null) {
            ExceptionUtil.throwFailException(40404, "菜单组不存在");
        }
        MenuGroup targetMenuGroup = this.jdbcEngine.queryOne(MenuGroup.class, MySqlDynamicEngine.query(MenuGroupModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .id().equalTo(targetMenuGroupId))));
        if (targetMenuGroup == null) {
            ExceptionUtil.throwFailException(40404, "菜单组不存在");
        }
        MenuGroup menuGroupUpdate = new MenuGroup();
        menuGroupUpdate.setIndex(targetMenuGroup.getIndex());
        menuGroupUpdate.setUpdateTime(Time.localDateTimeNow());
        menuGroupUpdate.setUpdateTimeStamp(Time.timeStamp());

        int count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(sourceMenuGroupId, menuGroupUpdate, MySqlDynamicEngine.update(MenuGroupModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("操作失败");
        }
        menuGroupUpdate.setIndex(sourceMenuGroup.getIndex());
        count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(targetMenuGroupId, menuGroupUpdate, MySqlDynamicEngine.update(MenuGroupModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("操作失败");
        }
    }

    @Override
    public void deleteMenuGroupByMenuGroupId(String moduleId, String menuGroupId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(menuGroupId)) {
            ExceptionUtil.throwFailException("菜单组ID不能为空");
        }
        //删除菜单组
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(MenuGroupModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .id().equalTo(menuGroupId))));
        if (count != 1) {
            ExceptionUtil.throwFailException("删除菜单组失败");
        }
        //删除菜单数据(会同时删除角色菜单数据、菜单路由数据)
        this.menuService.deleteListMenuByMenuGroupId(moduleId, menuGroupId);
    }

    @Override
    public void deleteListMenuGroupBySubModuleId(String moduleId, String subModuleId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(subModuleId)) {
            ExceptionUtil.throwFailException("子模块ID不能为空");
        }
        //查询子模块下拥有的菜单组ID
        List<String> menuGroupIds = this.jdbcEngine.queryColumnList(MenuGroupModel.id_alias, String.class, MySqlDynamicEngine.query(MenuGroupModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .subModuleId().equalTo(subModuleId))));
        if (menuGroupIds.size() == 0) {
            return;
        }
        //删除菜单组
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(MenuGroupModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .subModuleId().equalTo(subModuleId))));
        if (count != menuGroupIds.size()) {
            ExceptionUtil.throwFailException("删除菜单组失败");
        }
        //删除菜单数据(会同时删除角色菜单数据、菜单路由数据)
        this.menuService.deleteListMenuByMenuGroupIds(moduleId, menuGroupIds);
    }

    @Override
    public void deleteListMenuGroupBySubModuleIds(String moduleId, List<String> subModuleIds) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(subModuleIds)) {
            ExceptionUtil.throwFailException("子模块ID不能为空");
        }
        //查询子模块下拥有的菜单组ID
        List<String> menuGroupIds = this.jdbcEngine.queryColumnList(MenuGroupModel.id_alias, String.class, MySqlDynamicEngine.query(MenuGroupModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .subModuleId().inS(subModuleIds))));
        if (menuGroupIds.size() == 0) {
            return;
        }
        //删除菜单组
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(MenuGroupModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .subModuleId().inS(subModuleIds))));
        if (count != menuGroupIds.size()) {
            ExceptionUtil.throwFailException("删除菜单组失败");
        }
        //删除菜单数据(会同时删除角色菜单数据、菜单路由数据)
        this.menuService.deleteListMenuByMenuGroupIds(moduleId, menuGroupIds);
    }

    @Override
    public void deleteListMenuGroupByMenuGroupIds(String moduleId, List<String> menuGroupIds) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(menuGroupIds)) {
            ExceptionUtil.throwFailException("菜单组ID不能为空");
        }
        //删除菜单组
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(MenuGroupModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .id().inS(menuGroupIds))));
        if (count == 0) {
            ExceptionUtil.throwFailException("删除菜单组失败");
        }
        //删除菜单数据(会同时删除角色菜单数据、菜单路由数据)
        this.menuService.deleteListMenuByMenuGroupIds(moduleId, menuGroupIds);
    }

}
