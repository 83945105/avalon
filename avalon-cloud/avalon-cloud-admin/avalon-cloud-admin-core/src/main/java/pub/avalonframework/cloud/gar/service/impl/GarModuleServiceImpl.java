package pub.avalonframework.cloud.gar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pub.avalon.beans.Time;
import pub.avalon.holygrail.response.beans.Status;
import pub.avalon.holygrail.response.utils.ExceptionUtil;
import pub.avalon.holygrail.utils.StringUtil;
import pub.avalon.sqlhelper.core.beans.FunctionColumnType;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.spring.core.SpringJdbcEngine;
import pub.avalonframework.cloud.gar.dc.ModulePost;
import pub.avalonframework.cloud.gar.dc.ModulePut;
import pub.avalonframework.cloud.gar.entity.Module;
import pub.avalonframework.cloud.gar.entity.SubModule;
import pub.avalonframework.cloud.gar.model.*;
import pub.avalonframework.cloud.gar.service.GarModuleService;
import pub.avalonframework.cloud.gar.service.GarSubModuleService;
import pub.avalonframework.cloud.gar.utils.GarUtils;
import pub.avalonframework.cloud.gar.utils.TableUtils;

import java.util.List;
import java.util.Set;

/**
 * @author 白超
 * @since 2018/7/11
 */
@Service
public class GarModuleServiceImpl implements GarModuleService {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired
    private GarSubModuleService subModuleService;

    @Override
    public boolean getValidateModuleIdCanUse(String moduleId, Set<String> excludeModuleIds) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (!moduleId.matches(TableUtils.MODULE_ID_REGEX)) {
            ExceptionUtil.throwFailException("模块ID格式不正确");
        }
        if (TableUtils.GAR_MODULE_ID.equalsIgnoreCase(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不可用");
        }
        return this.jdbcEngine.queryCount(MySqlDynamicEngine.query(ModuleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().equalTo(moduleId)
                                .id().notInS(excludeModuleIds)))) == 0;
    }

    @Override
    public boolean getValidatePathCanUse(String path, Set<String> excludePaths) throws Exception {
        if (StringUtil.isEmpty(path)) {
            ExceptionUtil.throwFailException("网关地址不能为空");
        }
        if (!path.matches(TableUtils.ZUUL_PATH_REGEX)) {
            ExceptionUtil.throwFailException("网关地址格式不正确");
        }
        if (TableUtils.GAR_ZUUL_PATH.equalsIgnoreCase(path)) {
            ExceptionUtil.throwFailException("网关地址不可用");
        }
        return this.jdbcEngine.queryCount(MySqlDynamicEngine.query(ModuleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.path().equalTo(path)
                                .path().notInS(excludePaths)))) == 0;
    }

    @Override
    public boolean getValidateServiceIdCanUse(String serviceId, Set<String> excludeServiceIds) throws Exception {
        if (StringUtil.isEmpty(serviceId)) {
            ExceptionUtil.throwFailException("服务ID不能为空");
        }
        if (!serviceId.matches(TableUtils.SERVICE_ID_REGEX)) {
            ExceptionUtil.throwFailException("服务ID格式不正确");
        }
        if (TableUtils.GAR_ZUUL_SERVICE_ID.equalsIgnoreCase(serviceId)) {
            ExceptionUtil.throwFailException("服务ID不可用");
        }
        return this.jdbcEngine.queryCount(MySqlDynamicEngine.query(ModuleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.serviceId().equalTo(serviceId)
                                .serviceId().notInS(excludeServiceIds)))) == 0;
    }

    @Override
    public String postModule(ModulePost record) throws Exception {
        if (StringUtil.isEmpty(record.getId())) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (!getValidateModuleIdCanUse(record.getId(), null)) {
            ExceptionUtil.throwFailException("该模块ID不可用");
        }
        if (StringUtil.isEmpty(record.getPath())) {
            ExceptionUtil.throwFailException("路由地址不能为空");
        }
        if (!getValidatePathCanUse(record.getPath(), null)) {
            ExceptionUtil.throwFailException("该路由地址不可用");
        }
        if (StringUtil.isEmpty(record.getServiceId())) {
            ExceptionUtil.throwFailException("服务ID不能为空");
        }
        if (!getValidateServiceIdCanUse(record.getServiceId(), null)) {
            ExceptionUtil.throwFailException("该服务ID不可用");
        }
        if (StringUtil.isEmpty(record.getServiceName())) {
            ExceptionUtil.throwFailException("服务名称不能为空");
        }
        if (StringUtil.isEmpty(record.getStatus())) {
            record.setStatus(Status.NORMAL.name());
        }

        Module moduleInsert = new Module();
        moduleInsert.setId(record.getId());
        moduleInsert.setPath(record.getPath());
        moduleInsert.setServiceId(record.getServiceId());
        moduleInsert.setServiceName(record.getServiceName());
        moduleInsert.setUrl(record.getUrl());
        moduleInsert.setLoginUrl(record.getLoginUrl());

        Long index = this.jdbcEngine.queryColumnOne(1, Long.class, MySqlDynamicEngine.query(ModuleModel.class)
                .functionColumn(FunctionColumnType.MAX, ModuleModel.Column::index));
        moduleInsert.setIndex(TableUtils.getModuleIndex(index));

        moduleInsert.setStatus(record.getStatus());
        moduleInsert.setCreateTime(Time.localDateTimeNow());
        moduleInsert.setCreateTimeStamp(Time.timeStamp());

        //创建表
        String resourceTableName = TableUtils.getResourceTableName(record.getId());
        String roleTableName = TableUtils.getRoleTableName(record.getId());
        String roleResourceTableName = TableUtils.getRoleResourceTableName(record.getId());
        String objectRoleTableName = TableUtils.getObjectRoleTableName(record.getId());
        String roleMenuTableName = TableUtils.getRoleMenuTableName(record.getId());
        String roleRouteViewTemplateTableName = TableUtils.getRoleRouteViewTemplateTableName(record.getId());

        if (this.jdbcEngine.isTableExist(MySqlDynamicEngine.table(resourceTableName, AutResourceModel.class))) {
            ExceptionUtil.throwFailException("资源表 [" + resourceTableName + "] 已存在, 无法创建");
        }
        if (this.jdbcEngine.isTableExist(MySqlDynamicEngine.table(roleTableName, AutRoleModel.class))) {
            ExceptionUtil.throwFailException("角色表 [" + roleTableName + "] 已存在, 无法创建");
        }
        if (this.jdbcEngine.isTableExist(MySqlDynamicEngine.table(roleResourceTableName, AutRoleResourceModel.class))) {
            ExceptionUtil.throwFailException("角色资源表 [" + roleResourceTableName + "] 已存在, 无法创建");
        }
        if (this.jdbcEngine.isTableExist(MySqlDynamicEngine.table(objectRoleTableName, AutObjectRoleModel.class))) {
            ExceptionUtil.throwFailException("对象角色表 [" + objectRoleTableName + "] 已存在, 无法创建");
        }
        if (this.jdbcEngine.isTableExist(MySqlDynamicEngine.table(roleMenuTableName, AutRoleMenuModel.class))) {
            ExceptionUtil.throwFailException("角色菜单表 [" + roleMenuTableName + "] 已存在, 无法创建");
        }
        if (this.jdbcEngine.isTableExist(MySqlDynamicEngine.table(roleRouteViewTemplateTableName, AutRoleRouteViewTemplateModel.class))) {
            ExceptionUtil.throwFailException("角色路由视图模板表 [" + roleRouteViewTemplateTableName + "] 已存在, 无法创建");
        }

        this.jdbcEngine.copyTable(resourceTableName, false, MySqlDynamicEngine.table(AutResourceModel.class));
        this.jdbcEngine.copyTable(roleTableName, false, MySqlDynamicEngine.table(AutRoleModel.class));
        this.jdbcEngine.copyTable(roleResourceTableName, false, MySqlDynamicEngine.table(AutRoleResourceModel.class));
        this.jdbcEngine.copyTable(objectRoleTableName, false, MySqlDynamicEngine.table(AutObjectRoleModel.class));
        this.jdbcEngine.copyTable(roleMenuTableName, false, MySqlDynamicEngine.table(AutRoleMenuModel.class));
        this.jdbcEngine.copyTable(roleRouteViewTemplateTableName, false, MySqlDynamicEngine.table(AutRoleRouteViewTemplateModel.class));
        GarUtils.RESOURCE_TABLE_NAMES.add(resourceTableName);
        GarUtils.ROLE_TABLE_NAMES.add(roleTableName);
        GarUtils.ROLE_RESOURCE_TABLE_NAMES.add(roleResourceTableName);
        GarUtils.OBJECT_ROLE_TABLE_NAMES.add(objectRoleTableName);
        GarUtils.ROLE_MENU_TABLE_NAMES.add(roleMenuTableName);
        GarUtils.ROLE_ROUTE_VIEW_TABLE_NAMES.add(roleRouteViewTemplateTableName);

        int count = this.jdbcEngine.insertJavaBeanSelective(moduleInsert, MySqlDynamicEngine.insert(ModuleModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("新增模块失败");
        }
        return record.getId();
    }

    @Override
    public void putModuleByModuleId(String moduleId, ModulePut record) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        Module route = this.jdbcEngine.queryByPrimaryKey(moduleId, Module.class, MySqlDynamicEngine.query(ModuleModel.class)
                .column(ModuleModel.Column::id));
        if (route == null) {
            ExceptionUtil.throwFailException("模块不存在");
        }
        Module moduleUpdate = new Module();
        SubModule subModuleUpdate = null;

        if (!StringUtil.isEmpty(record.getServiceName()) && !record.getServiceName().equals(route.getServiceName())) {
            moduleUpdate.setServiceName(record.getServiceName());
            subModuleUpdate = new SubModule();
            subModuleUpdate.setModuleName(record.getServiceName());
        }
        if (!StringUtil.isEmpty(record.getStatus()) && !record.getStatus().equals(route.getStatus())) {
            moduleUpdate.setStatus(record.getStatus());
        }
        moduleUpdate.setUrl(record.getUrl());
        moduleUpdate.setLoginUrl(record.getLoginUrl());

        String timeString = Time.localDateTimeNow();
        long timeStamp = Time.timeStamp();

        moduleUpdate.setUpdateTime(timeString);
        moduleUpdate.setUpdateTimeStamp(timeStamp);

        int count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(moduleId, moduleUpdate, MySqlDynamicEngine.update(ModuleModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("修改模块失败");
        }
        //修改相关数据
        if (subModuleUpdate != null) {
            subModuleUpdate.setUpdateTime(timeString);
            subModuleUpdate.setUpdateTimeStamp(timeStamp);
            this.subModuleService.putListSubModuleByModuleId(moduleId, subModuleUpdate);
        }
    }

    @Override
    public void deleteModuleByModuleId(String moduleId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        Module route = this.jdbcEngine.queryByPrimaryKey(moduleId, Module.class, MySqlDynamicEngine.query(ModuleModel.class)
                .column(ModuleModel.Column::id));
        if (route == null) {
            ExceptionUtil.throwFailException("模块不存在");
        }

        //逻辑删除表
        String resourceTableName = TableUtils.getResourceTableName(moduleId);
        String roleTableName = TableUtils.getRoleTableName(moduleId);
        String roleResourceTableName = TableUtils.getRoleResourceTableName(moduleId);
        String objectRoleTableName = TableUtils.getObjectRoleTableName(moduleId);
        String roleMenuTableName = TableUtils.getRoleMenuTableName(moduleId);
        String roleRouteViewTemplateTableName = TableUtils.getRoleRouteViewTemplateTableName(moduleId);

        String resourceTableNameBak = TableUtils.getRenameTableName(resourceTableName);
        String roleTableNameBak = TableUtils.getRenameTableName(roleTableName);
        String roleResourceTableNameBak = TableUtils.getRenameTableName(roleResourceTableName);
        String roleUserTableNameBak = TableUtils.getRenameTableName(objectRoleTableName);
        String roleMenuTableNameBak = TableUtils.getRenameTableName(roleMenuTableName);
        String roleRouteViewTemplateTableNameBak = TableUtils.getRenameTableName(roleRouteViewTemplateTableName);

        this.jdbcEngine.renameTable(resourceTableNameBak, MySqlDynamicEngine.table(resourceTableName, AutResourceModel.class));
        this.jdbcEngine.renameTable(roleTableNameBak, MySqlDynamicEngine.table(roleTableName, AutRoleModel.class));
        this.jdbcEngine.renameTable(roleResourceTableNameBak, MySqlDynamicEngine.table(roleResourceTableName, AutRoleResourceModel.class));
        this.jdbcEngine.renameTable(roleUserTableNameBak, MySqlDynamicEngine.table(objectRoleTableName, AutObjectRoleModel.class));
        this.jdbcEngine.renameTable(roleMenuTableNameBak, MySqlDynamicEngine.table(roleMenuTableName, AutRoleMenuModel.class));
        this.jdbcEngine.renameTable(roleRouteViewTemplateTableNameBak, MySqlDynamicEngine.table(roleRouteViewTemplateTableName, AutRoleRouteViewTemplateModel.class));

        GarUtils.RESOURCE_TABLE_NAMES.remove(resourceTableName);
        GarUtils.ROLE_TABLE_NAMES.remove(roleTableName);
        GarUtils.ROLE_RESOURCE_TABLE_NAMES.remove(roleResourceTableName);
        GarUtils.OBJECT_ROLE_TABLE_NAMES.remove(objectRoleTableName);
        GarUtils.ROLE_MENU_TABLE_NAMES.remove(roleMenuTableName);
        GarUtils.ROLE_ROUTE_VIEW_TABLE_NAMES.remove(roleRouteViewTemplateTableName);

        int count = this.jdbcEngine.deleteByPrimaryKey(moduleId, MySqlDynamicEngine.delete(ModuleModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("删除模块失败");
        }

        //删除相关数据
        List<String> subModuleIds = this.jdbcEngine.queryColumnList(SubModuleModel.id_alias, String.class, MySqlDynamicEngine.query(SubModuleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId))));
        if (!StringUtil.isEmpty(subModuleIds)) {
            this.subModuleService.deleteListSubModuleBySubModuleIds(moduleId, subModuleIds);
        }
    }

}
