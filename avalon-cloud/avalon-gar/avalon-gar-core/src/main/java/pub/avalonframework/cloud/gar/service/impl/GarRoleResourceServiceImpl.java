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
import pub.avalonframework.cloud.gar.entity.AutResource;
import pub.avalonframework.cloud.gar.entity.AutRole;
import pub.avalonframework.cloud.gar.entity.AutRoleResource;
import pub.avalonframework.cloud.gar.model.AutResourceModel;
import pub.avalonframework.cloud.gar.model.AutRoleModel;
import pub.avalonframework.cloud.gar.model.AutRoleResourceModel;
import pub.avalonframework.cloud.gar.service.GarRoleResourceService;
import pub.avalonframework.cloud.gar.utils.TableUtils;

import java.util.*;

/**
 * @author 白超
 * @date 2018/7/23
 */
@Service
public class GarRoleResourceServiceImpl implements GarRoleResourceService {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    public boolean getValidateRoleResourceExistByRoleIdAndResourceId(String moduleId, String roleId, String resourceId) throws Exception {
        String roleResourceTableName = TableUtils.getRoleResourceTableName(moduleId);
        return this.jdbcEngine.queryCount(MySqlDynamicEngine.query(roleResourceTableName, AutRoleResourceModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.roleId().equalTo(roleId)
                                .resourceId().equalTo(resourceId)))) > 0;
    }

    @Override
    public void postRoleResourceByRoleIdAndResourceId(String moduleId, String roleId, String resourceId) throws Exception {
        String roleTableName = TableUtils.getRoleTableName(moduleId);
        String resourceTableName = TableUtils.getResourceTableName(moduleId);
        String roleResourceTableName = TableUtils.getRoleResourceTableName(moduleId);
        if (StringUtil.isEmpty(roleId)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        if (StringUtil.isEmpty(resourceId)) {
            ExceptionUtil.throwFailException("资源ID不能为空");
        }
        if (this.getValidateRoleResourceExistByRoleIdAndResourceId(moduleId, roleId, resourceId)) {
            ExceptionUtil.throwFailException("该角色已经拥有该资源");
        }
        AutRole role = this.jdbcEngine.queryByPrimaryKey(roleId, AutRole.class, MySqlDynamicEngine.query(roleTableName, AutRoleModel.class));
        if (role == null) {
            ExceptionUtil.throwFailException("角色不存在");
        }
        if (!EnumMethods.equalsTo(role.getStatus(), Status.NORMAL)) {
            ExceptionUtil.throwFailException("角色不可用");
        }
        AutResource resource = this.jdbcEngine.queryByPrimaryKey(resourceId, AutResource.class, MySqlDynamicEngine.query(resourceTableName, AutResourceModel.class));
        if (resource == null) {
            ExceptionUtil.throwFailException("资源不存在");
        }
        if (!EnumMethods.equalsTo(resource.getStatus(), Status.NORMAL)) {
            ExceptionUtil.throwFailException("资源不可用");
        }

        String timeString = Time.localDateTimeNow();
        long timeStamp = Time.timeStamp();
        AutRoleResource roleResourceInsert = new AutRoleResource();

        roleResourceInsert.setRoleId(role.getId());
        roleResourceInsert.setRoleValue(role.getValue());
        roleResourceInsert.setRoleName(role.getName());
        roleResourceInsert.setRoleType(role.getType());

        roleResourceInsert.setResourceId(resource.getId());
        roleResourceInsert.setResourceBatchId(resource.getBatchId());
        roleResourceInsert.setResourceName(resource.getName());
        roleResourceInsert.setResourceUrl(resource.getUrl());
        roleResourceInsert.setResourcePermission(resource.getPermission());
        roleResourceInsert.setResourceType(resource.getType());

        roleResourceInsert.setStatus(Status.NORMAL.name());
        roleResourceInsert.setCreateTime(timeString);
        roleResourceInsert.setCreateTimeStamp(timeStamp);

        int count = this.jdbcEngine.insertJavaBeanSelective(roleResourceInsert, MySqlDynamicEngine.insert(roleResourceTableName, AutRoleResourceModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("新增角色资源失败");
        }
    }

    @Override
    public void postListRoleResourceByRoleIdsAndResourceIds(String moduleId, Set<String> roleIds, Set<String> resourceIds) throws Exception {
        String roleTableName = TableUtils.getRoleTableName(moduleId);
        String resourceTableName = TableUtils.getResourceTableName(moduleId);
        String roleResourceTableName = TableUtils.getRoleResourceTableName(moduleId);
        if (StringUtil.isEmpty(roleIds)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        if (StringUtil.isEmpty(resourceIds)) {
            ExceptionUtil.throwFailException("资源ID不能为空");
        }

        Map<Long, String> mapByRoleIds = this.jdbcEngine.queryPairColumnInMap(AutRoleResourceModel.primaryKeyAlias, AutRoleResourceModel.roleId_alias, MySqlDynamicEngine.query(roleResourceTableName, AutRoleResourceModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.roleId().inS(roleIds))));
        Map<Long, Long> mapByResourceIds = this.jdbcEngine.queryPairColumnInMap(AutRoleResourceModel.primaryKeyAlias, AutRoleResourceModel.resourceId_alias, MySqlDynamicEngine.query(roleResourceTableName, AutRoleResourceModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.resourceId().inS(resourceIds))));
        //如果存在交集表示已经存在
        for (Map.Entry<Long, String> roleEntry : mapByRoleIds.entrySet()) {
            for (Map.Entry<Long, Long> resourceEntry : mapByResourceIds.entrySet()) {
                if (roleEntry.getKey().equals(resourceEntry.getKey())) {
                    ExceptionUtil.throwFailException("角色ID: [" + roleEntry.getValue() + "]已经拥有资源ID: [" + resourceEntry.getValue() + "]");
                }
            }
        }
        Map<String, AutRole> roleMap = this.jdbcEngine.queryInMap(AutRoleModel.primaryKeyAlias, AutRole.class, MySqlDynamicEngine.query(roleTableName, AutRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().inS(roleIds))));
        if (StringUtil.isEmpty(roleMap)) {
            ExceptionUtil.throwFailException("角色不存在");
        }
        if (roleIds.size() != roleMap.size()) {
            ExceptionUtil.throwFailException("角色数量不符");
        }
        Map<String, AutResource> resourceMap = this.jdbcEngine.queryInMap(AutResourceModel.primaryKeyAlias, AutResource.class, MySqlDynamicEngine.query(resourceTableName, AutResourceModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().inS(resourceIds))));
        if (StringUtil.isEmpty(resourceMap)) {
            ExceptionUtil.throwFailException("资源不存在");
        }
        if (resourceIds.size() != resourceMap.size()) {
            ExceptionUtil.throwFailException("资源数量不符");
        }
        String timeString = Time.localDateTimeNow();
        long timeStamp = Time.timeStamp();

        List<AutRoleResource> roleResourcesInsert = new ArrayList<>();
        AutRoleResource roleResourceInsert;
        AutRole role;
        AutResource resource;
        for (Map.Entry<String, AutRole> roleEntry : roleMap.entrySet()) {
            role = roleEntry.getValue();
            if (!EnumMethods.equalsTo(role.getStatus(), Status.NORMAL)) {
                ExceptionUtil.throwFailException("角色不可用");
            }
            for (Map.Entry<String, AutResource> resEntry : resourceMap.entrySet()) {
                resource = resEntry.getValue();
                if (!EnumMethods.equalsTo(resource.getStatus(), Status.NORMAL)) {
                    ExceptionUtil.throwFailException("资源不可用");
                }

                roleResourceInsert = new AutRoleResource();

                roleResourceInsert.setRoleId(role.getId());
                roleResourceInsert.setRoleValue(role.getValue());
                roleResourceInsert.setRoleName(role.getName());
                roleResourceInsert.setRoleType(role.getType());

                roleResourceInsert.setResourceId(resource.getId());
                roleResourceInsert.setResourceBatchId(resource.getBatchId());
                roleResourceInsert.setResourceName(resource.getName());
                roleResourceInsert.setResourceUrl(resource.getUrl());
                roleResourceInsert.setResourcePermission(resource.getPermission());
                roleResourceInsert.setResourceType(resource.getType());

                roleResourceInsert.setStatus(Status.NORMAL.name());
                roleResourceInsert.setCreateTime(timeString);
                roleResourceInsert.setCreateTimeStamp(timeStamp);

                roleResourcesInsert.add(roleResourceInsert);
            }
        }
        int count = this.jdbcEngine.batchInsertJavaBeans(roleResourcesInsert, MySqlDynamicEngine.insert(roleResourceTableName, AutRoleResourceModel.class));
        if (count != roleResourcesInsert.size()) {
            ExceptionUtil.throwFailException("新增角色资源失败");
        }
    }

    @Override
    public void putListRoleResourceByRoleId(String moduleId, String roleId, AutRoleResource roleResourceUpdate) throws Exception {
        String roleResourceTableName = TableUtils.getRoleResourceTableName(moduleId);
        if (StringUtil.isEmpty(roleId)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        this.jdbcEngine.updateJavaBeanSelective(roleResourceUpdate, MySqlDynamicEngine.update(roleResourceTableName, AutRoleResourceModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.roleId().equalTo(roleId))));
    }

    @Override
    public void putListRoleResourceByResourceId(String moduleId, String resourceId, AutRoleResource roleResourceUpdate) throws Exception {
        String roleResourceTableName = TableUtils.getRoleResourceTableName(moduleId);
        if (StringUtil.isEmpty(resourceId)) {
            ExceptionUtil.throwFailException("资源ID不能为空");
        }
        this.jdbcEngine.updateJavaBeanSelective(roleResourceUpdate, MySqlDynamicEngine.update(roleResourceTableName, AutRoleResourceModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.resourceId().equalTo(resourceId))));
    }

    @Override
    public void deleteListRoleResourceByRoleId(String moduleId, String roleId) throws Exception {
        String roleResourceTableName = TableUtils.getRoleResourceTableName(moduleId);
        if (StringUtil.isEmpty(roleId)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(roleResourceTableName, AutRoleResourceModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.roleId().equalTo(roleId))));
    }

    @Override
    public void deleteListRoleResourceByRoleIds(String moduleId, List<String> roleIds) throws Exception {
        String roleResourceTableName = TableUtils.getRoleResourceTableName(moduleId);
        if (StringUtil.isEmpty(roleIds)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(roleResourceTableName, AutRoleResourceModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.roleId().inS(roleIds))));
    }

    @Override
    public void deleteListRoleResourceByResourceId(String moduleId, String resourceId) throws Exception {
        String roleResourceTableName = TableUtils.getRoleResourceTableName(moduleId);
        if (StringUtil.isEmpty(resourceId)) {
            ExceptionUtil.throwFailException("资源ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(roleResourceTableName, AutRoleResourceModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.resourceId().equalTo(resourceId))));
    }

    @Override
    public void deleteListRoleResourceByResourceIds(String moduleId, List<String> resourceIds) throws Exception {
        String roleResourceTableName = TableUtils.getRoleResourceTableName(moduleId);
        if (StringUtil.isEmpty(resourceIds)) {
            ExceptionUtil.throwFailException("资源ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(roleResourceTableName, AutRoleResourceModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.resourceId().inS(resourceIds))));
    }

    @Override
    public void deleteRoleResourceByRoleResourceId(String moduleId, String roleResourceId) throws Exception {
        String roleResourceTableName = TableUtils.getRoleResourceTableName(moduleId);
        if (StringUtil.isEmpty(roleResourceId)) {
            ExceptionUtil.throwFailException("角色资源ID不能为空");
        }
        int count = this.jdbcEngine.deleteByPrimaryKey(roleResourceId, MySqlDynamicEngine.delete(roleResourceTableName, AutRoleResourceModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("删除角色资源失败");
        }
    }

    @Override
    public void deleteListRoleResourceByRoleResourceIds(String moduleId, Set<String> roleResourceIds) throws Exception {
        String roleResourceTableName = TableUtils.getRoleResourceTableName(moduleId);
        if (StringUtil.isEmpty(roleResourceIds)) {
            ExceptionUtil.throwFailException("角色资源ID不能为空");
        }
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(roleResourceTableName, AutRoleResourceModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().inS(roleResourceIds))));
        if (count != roleResourceIds.size()) {
            ExceptionUtil.throwFailException("删除角色资源失败");
        }
    }

    @Override
    public void deleteRoleResourceByRoleIdAndResourceId(String moduleId, String roleId, String resourceId) throws Exception {
        String roleResourceTableName = TableUtils.getRoleResourceTableName(moduleId);
        if (StringUtil.isEmpty(roleId)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        if (StringUtil.isEmpty(resourceId)) {
            ExceptionUtil.throwFailException("资源ID不能为空");
        }
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(roleResourceTableName, AutRoleResourceModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.resourceId().equalTo(resourceId)
                                .roleId().equalTo(roleId))));
        if (count != 1) {
            ExceptionUtil.throwFailException("删除角色资源失败");
        }
    }

    @Override
    public void deleteListRoleResourceByRoleIdsAndResourceIds(String moduleId, Set<String> roleIds, Set<String> resourceIds) throws Exception {
        String roleResourceTableName = TableUtils.getRoleResourceTableName(moduleId);
        if (StringUtil.isEmpty(roleIds)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        if (StringUtil.isEmpty(resourceIds)) {
            ExceptionUtil.throwFailException("资源ID不能为空");
        }
        List<String> roleResourceIdsByRoleIds = this.jdbcEngine.queryColumnList(AutRoleResourceModel.primaryKeyAlias, String.class, MySqlDynamicEngine.query(roleResourceTableName, AutRoleResourceModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.roleId().inS(roleIds))));
        List<String> roleResourceIdsByResourceIds = this.jdbcEngine.queryColumnList(AutRoleResourceModel.primaryKeyAlias, String.class, MySqlDynamicEngine.query(roleResourceTableName, AutRoleResourceModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.resourceId().inS(resourceIds))));
        //找出要删除的ID
        Set<String> ids = new HashSet<>();
        for (String roleResourceIdsByRoleId : roleResourceIdsByRoleIds) {
            for (String roleResourceIdsByResourceId : roleResourceIdsByResourceIds) {
                if (roleResourceIdsByRoleId.equals(roleResourceIdsByResourceId)) {
                    ids.add(roleResourceIdsByRoleId);
                }
            }
        }
        if (StringUtil.isEmpty(ids)) {
            ExceptionUtil.throwFailException("没有可以删除的角色资源");
        }
        //执行删除
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(roleResourceTableName, AutRoleResourceModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.id().inS(ids))));
        if (count != ids.size()) {
            ExceptionUtil.throwFailException("删除角色资源失败");
        }
    }
}