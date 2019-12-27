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
import pub.avalonframework.cloud.gar.beans.AutRoleType;
import pub.avalonframework.cloud.gar.dc.AutRoleGet;
import pub.avalonframework.cloud.gar.dc.AutRolePost;
import pub.avalonframework.cloud.gar.dc.AutRolePut;
import pub.avalonframework.cloud.gar.entity.*;
import pub.avalonframework.cloud.gar.model.AutRoleModel;
import pub.avalonframework.cloud.gar.service.*;
import pub.avalonframework.cloud.gar.utils.TableUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author 白超
 * @version 1.0
 * @see
 * @since 2018/7/12
 */
@Service
public class GarRoleServiceImpl implements GarRoleService {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired
    private GarRoleResourceService roleResourceService;
    @Autowired
    private GarObjectRoleService objectRoleService;
    @Autowired
    private GarRoleRouteViewTemplateService roleRouteViewTemplateService;
    @Autowired
    private GarRoleMenuService roleMenuService;

    @Override
    public boolean getValidateValueCanUse(String moduleId, String value, List<String> excludeValues) throws Exception {
        String tableName = TableUtils.getRoleTableName(moduleId);
        if (TableUtils.GAR_DEVELOPER_ROLE_VALUE.equalsIgnoreCase(value)) {
            ExceptionUtil.throwFailException("角色唯一标识符不可用");
        }
        return this.jdbcEngine.queryCount(MySqlDynamicEngine.query(tableName, AutRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.value().equalTo(value)
                                .value().notInS(excludeValues)))) == 0;
    }

    @Override
    public AutRoleGet postRole(String moduleId, AutRolePost record) throws Exception {
        String tableName = TableUtils.getRoleTableName(moduleId);
        if (StringUtil.isEmpty(record.getName())) {
            ExceptionUtil.throwFailException("角色名称不能为空");
        }
        if (StringUtil.isEmpty(record.getValue())) {
            ExceptionUtil.throwFailException("角色唯一标识符不能为空");
        }
        if (!this.getValidateValueCanUse(moduleId, record.getValue(), null)) {
            ExceptionUtil.throwFailException("角色唯一标识符不可用");
        }
        if (StringUtil.isEmpty(record.getType())) {
            ExceptionUtil.throwFailException("角色类型不能为空");
        }
        if (!EnumMethods.contains(record.getType(), AutRoleType.values())) {
            ExceptionUtil.throwFailException("角色类型不正确");
        }
        if (StringUtil.isEmpty(record.getStatus())) {
            record.setStatus(Status.NORMAL.name());
        } else if (!EnumMethods.contains(record.getStatus(), Status.values())) {
            ExceptionUtil.throwFailException("状态类型不正确");
        }

        String id = record.getValue();

        AutRole roleInsert = new AutRole();

        roleInsert.setId(id);
        roleInsert.setName(record.getName());
        roleInsert.setValue(record.getValue());
        roleInsert.setType(record.getType());
        roleInsert.setDescription(record.getDescription());

        roleInsert.setParentId("");
        roleInsert.setParentIds("");

        roleInsert.setStatus(record.getStatus());
        roleInsert.setCreateTime(Time.localDateTimeNow());
        roleInsert.setCreateTimeStamp(Time.timeStamp());

        Long index = this.jdbcEngine.queryColumnOne(1, Long.class, MySqlDynamicEngine.query(tableName, AutRoleModel.class)
                .functionColumn(FunctionColumnType.MAX, AutRoleModel.Column::index));
        roleInsert.setIndex(TableUtils.getRoleIndex(index));
        int count = this.jdbcEngine.insertJavaBeanSelective(roleInsert, MySqlDynamicEngine.insert(tableName, AutRoleModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("新增角色失败");
        }
        return this.jdbcEngine.queryByPrimaryKey(id, AutRoleGet.class, MySqlDynamicEngine.query(tableName, AutRoleModel.class));
    }

    @Override
    public void putRoleByRoleId(String moduleId, String roleId, AutRolePut record) throws Exception {
        if (StringUtil.isEmpty(roleId)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        String roleTableName = TableUtils.getRoleTableName(moduleId);
        AutRole role = this.jdbcEngine.queryByPrimaryKey(roleId, AutRole.class, MySqlDynamicEngine.query(roleTableName, AutRoleModel.class));
        if (role == null) {
            ExceptionUtil.throwFailException(40404, "角色不存在");
        }
        AutRole roleUpdate = new AutRole();
        AutRoleResource roleResourceUpdate = null;
        AutObjectRole objectRoleUpdate = null;
        AutRoleRouteViewTemplate roleRouteViewTemplateUpdate = null;
        AutRoleMenu roleMenuUpdate = null;
        if (!StringUtil.isEmpty(record.getName()) && !record.getName().equals(role.getName())) {
            roleUpdate.setName(record.getName());
            roleResourceUpdate = new AutRoleResource();
            roleResourceUpdate.setRoleName(record.getName());
            objectRoleUpdate = new AutObjectRole();
            objectRoleUpdate.setRoleName(record.getName());
            roleRouteViewTemplateUpdate = new AutRoleRouteViewTemplate();
            roleRouteViewTemplateUpdate.setRoleName(record.getName());
            roleMenuUpdate = new AutRoleMenu();
            roleMenuUpdate.setRoleName(record.getName());
        }
        if (!StringUtil.isEmpty(record.getValue()) && !record.getValue().equals(role.getValue())) {
            if (!this.getValidateValueCanUse(moduleId, record.getValue(), Collections.singletonList(role.getValue()))) {
                ExceptionUtil.throwFailException("角色唯一标识符不可用");
            }
            roleUpdate.setValue(record.getValue());
            if (roleResourceUpdate == null) {
                roleResourceUpdate = new AutRoleResource();
            }
            roleResourceUpdate.setRoleValue(record.getValue());
            if (objectRoleUpdate == null) {
                objectRoleUpdate = new AutObjectRole();
            }
            objectRoleUpdate.setRoleValue(record.getValue());
            if (roleRouteViewTemplateUpdate == null) {
                roleRouteViewTemplateUpdate = new AutRoleRouteViewTemplate();
            }
            roleRouteViewTemplateUpdate.setRoleValue(record.getValue());
            if (roleMenuUpdate == null) {
                roleMenuUpdate = new AutRoleMenu();
            }
            roleMenuUpdate.setRoleValue(record.getValue());
        }
        if (!StringUtil.isEmpty(record.getType()) && !record.getType().equals(role.getType())) {
            if (!EnumMethods.contains(record.getType(), AutRoleType.values())) {
                ExceptionUtil.throwFailException("角色类型不正确");
            }
            roleUpdate.setType(record.getType());
            if (roleResourceUpdate == null) {
                roleResourceUpdate = new AutRoleResource();
            }
            roleResourceUpdate.setRoleType(record.getType());
            if (objectRoleUpdate == null) {
                objectRoleUpdate = new AutObjectRole();
            }
            objectRoleUpdate.setRoleType(record.getType());
            if (roleRouteViewTemplateUpdate == null) {
                roleRouteViewTemplateUpdate = new AutRoleRouteViewTemplate();
            }
            roleRouteViewTemplateUpdate.setRoleType(record.getType());
            if (roleMenuUpdate == null) {
                roleMenuUpdate = new AutRoleMenu();
            }
            roleMenuUpdate.setRoleType(record.getType());
        }
        roleUpdate.setDescription(record.getDescription());
        if (!StringUtil.isEmpty(record.getStatus()) && !record.getStatus().equals(role.getStatus())) {
            if (!EnumMethods.contains(record.getStatus(), Status.values())) {
                ExceptionUtil.throwFailException("状态类型不正确");
            }
            roleUpdate.setStatus(record.getStatus());
        }
        String timeString = Time.localDateTimeNow();
        Long timeStamp = Time.timeStamp();
        roleUpdate.setUpdateTime(timeString);
        roleUpdate.setUpdateTimeStamp(timeStamp);

        //更新角色数据
        int count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(roleId, roleUpdate, MySqlDynamicEngine.update(roleTableName, AutRoleModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("更新角色失败");
        }

        //更新角色资源数据
        if (roleResourceUpdate != null) {
            roleResourceUpdate.setUpdateTime(timeString);
            roleResourceUpdate.setUpdateTimeStamp(timeStamp);
            this.roleResourceService.putListRoleResourceByRoleId(moduleId, roleId, roleResourceUpdate);
        }

        //更新对象角色数据
        if (objectRoleUpdate != null) {
            objectRoleUpdate.setUpdateTime(timeString);
            objectRoleUpdate.setUpdateTimeStamp(timeStamp);
            this.objectRoleService.putListObjectRoleByRoleId(moduleId, roleId, objectRoleUpdate);
        }

        //更新角色路由视图模板数据
        if (roleRouteViewTemplateUpdate != null) {
            roleRouteViewTemplateUpdate.setUpdateTime(timeString);
            roleRouteViewTemplateUpdate.setUpdateTimeStamp(timeStamp);
            this.roleRouteViewTemplateService.putListRoleRouteViewTemplateByRoleId(moduleId, roleId, roleRouteViewTemplateUpdate);
        }

        //更新角色菜单数据
        if (roleMenuUpdate != null) {
            roleMenuUpdate.setUpdateTime(timeString);
            roleMenuUpdate.setUpdateTimeStamp(timeStamp);
            this.roleMenuService.putListRoleMenuByRoleId(moduleId, roleId, roleMenuUpdate);
        }
    }

    @Override
    public void deleteRoleByRoleId(String moduleId, String roleId) throws Exception {
        if (StringUtil.isEmpty(roleId)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        String roleTableName = TableUtils.getRoleTableName(moduleId);

        //删除角色
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(roleTableName, AutRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().equalTo(roleId))));
        if (count != 1) {
            ExceptionUtil.throwFailException("删除角色失败");
        }
        //删除角色资源数据
        this.roleResourceService.deleteListRoleResourceByRoleId(moduleId, roleId);
        //删除对象角色数据
        this.objectRoleService.deleteListObjectRoleByRoleId(moduleId, roleId);
        //删除角色路由视图模板数据
        this.roleRouteViewTemplateService.deleteListRoleRouteViewTemplateByRoleId(moduleId, roleId);
        //删除角色菜单数据
        this.roleMenuService.deleteListRoleMenuByRoleId(moduleId, roleId);
    }

    @Override
    public void deleteListRoleByRoleIds(String moduleId, List<String> roleIds) throws Exception {
        if (StringUtil.isEmpty(roleIds)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        String roleTableName = TableUtils.getRoleTableName(moduleId);

        //删除角色
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(roleTableName, AutRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().inS(roleIds))));
        if (count != roleIds.size()) {
            ExceptionUtil.throwFailException("删除角色失败");
        }
        //删除角色资源数据
        this.roleResourceService.deleteListRoleResourceByRoleIds(moduleId, roleIds);
        //删除角色用户数据
        this.objectRoleService.deleteListObjectRoleByRoleIds(moduleId, roleIds);
        //删除角色路由视图模板数据
        this.roleRouteViewTemplateService.deleteListRoleRouteViewTemplateByRoleIds(moduleId, roleIds);
        //删除角色菜单数据
        this.roleMenuService.deleteListRoleMenuByRoleIds(moduleId, roleIds);
    }
}