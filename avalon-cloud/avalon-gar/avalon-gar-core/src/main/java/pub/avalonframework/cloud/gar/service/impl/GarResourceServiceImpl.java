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
import pub.avalonframework.cloud.gar.beans.AutResourceType;
import pub.avalonframework.cloud.gar.dc.AutResourceGet;
import pub.avalonframework.cloud.gar.dc.AutResourcePost;
import pub.avalonframework.cloud.gar.dc.AutResourcePut;
import pub.avalonframework.cloud.gar.entity.AutResource;
import pub.avalonframework.cloud.gar.entity.AutRole;
import pub.avalonframework.cloud.gar.entity.AutRoleResource;
import pub.avalonframework.cloud.gar.model.AutResourceModel;
import pub.avalonframework.cloud.gar.model.AutRoleModel;
import pub.avalonframework.cloud.gar.model.AutRoleResourceModel;
import pub.avalonframework.cloud.gar.service.GarResourceService;
import pub.avalonframework.cloud.gar.service.GarRoleResourceService;
import pub.avalonframework.cloud.gar.utils.TableUtils;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author 白超
 * @date 2018/6/13
 */
@Service
public class GarResourceServiceImpl implements GarResourceService {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired
    private GarRoleResourceService roleResourceService;

    public Set<String> getChildrenIds(String id, String moduleId) throws Exception {
        if (StringUtil.isEmpty(id)) {
            return new HashSet<>(0);
        }
        String resourceTableName = TableUtils.getResourceTableName(moduleId);
        Set<String> ids = new HashSet<>();
        // 查出子级
        ids.addAll(this.jdbcEngine.queryColumnList(AutResourceModel.id_alias, String.class, MySqlDynamicEngine.query(resourceTableName, AutResourceModel.class)
                .column(AutResourceModel.Column::id)
                .where((condition, mainTable) -> condition.and(mainTable.parentId().equalTo(id)))));
        // 查出子级下所有的子级
        ids.addAll(this.jdbcEngine.queryColumnList(AutResourceModel.id_alias, String.class, MySqlDynamicEngine.query(resourceTableName, AutResourceModel.class)
                .column(AutResourceModel.Column::id)
                .where((condition, mainTable) -> condition.and(mainTable.parentIds().like("%/" + id + "/%")))));
        return ids;
    }

    private List<AutRoleResource> buildRoleResourceListData(List<AutRole> roleList, List<? extends AutResource> resourceList, Consumer<AutRoleResource> formatter) {
        if (StringUtil.isEmpty(roleList) || StringUtil.isEmpty(resourceList)) {
            return null;
        }
        List<AutRoleResource> roleResourcesInsert = new ArrayList<>();
        AutRoleResource roleResourceInsert;
        for (AutRole role : roleList) {
            for (AutResource resource : resourceList) {
                roleResourceInsert = new AutRoleResource();
                formatter.accept(roleResourceInsert);
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

                roleResourcesInsert.add(roleResourceInsert);
            }
        }
        return roleResourcesInsert;
    }

    private List<AutResourceGet> postNodeResource(String moduleId, AutResourcePost record) throws Exception {
        String resourceTableName = TableUtils.getResourceTableName(moduleId);
        if (!AutResourceType.NODE.equalsTo(record.getType())) {
            ExceptionUtil.throwFailException("资源类型不是节点类型");
        }
        if (StringUtil.isEmpty(record.getStatus())) {
            record.setStatus(Status.NORMAL.name());
        }
        String timeString = Time.localDateTimeNow();
        long timeStamp = Time.timeStamp();
        String batchId = TableUtils.buildResourceBatchId();

        Set<String> parentIdSet = record.getParentIdSet();
        Long index = this.jdbcEngine.queryColumnOne(1, Long.class, MySqlDynamicEngine.query(resourceTableName, AutResourceModel.class)
                .functionColumn(FunctionColumnType.MAX, AutResourceModel.Column::index));
        AutResource resourceInsert;
        if (StringUtil.isEmpty(parentIdSet)) {
            resourceInsert = new AutResource();
            resourceInsert.setBatchId(batchId);
            resourceInsert.setName(record.getName());
            resourceInsert.setType(record.getType());
            resourceInsert.setUrl("");
            resourceInsert.setPath("");
            resourceInsert.setDescription(record.getDescription());
            resourceInsert.setParentId("");
            resourceInsert.setParentIds("");
            resourceInsert.setPermission(record.getPermission());
            resourceInsert.setIndex(TableUtils.getResourceIndex(index));
            resourceInsert.setStatus(record.getStatus());
            resourceInsert.setCreateTime(timeString);
            resourceInsert.setCreateTimeStamp(timeStamp);
            int count = this.jdbcEngine.insertJavaBeanSelective(resourceInsert, MySqlDynamicEngine.insert(resourceTableName, AutResourceModel.class));
            if (count != 1) {
                ExceptionUtil.throwFailException("新增资源失败");
            }
        } else {
            // 有父节点
            Map<Long, AutResource> parentResourceMap = this.jdbcEngine.queryInMap(AutResourceModel.primaryKeyAlias, AutResource.class, MySqlDynamicEngine.query(resourceTableName, AutResourceModel.class)
                    .where((condition, mainTable) -> condition
                            .and(mainTable.id().inS(parentIdSet))));
            AutResource parentResource;
            List<AutResource> resourcesInsert = new ArrayList<>();
            for (String parentId : parentIdSet) {
                parentResource = parentResourceMap.get(Long.parseLong(parentId));
                if (parentResource == null) {
                    ExceptionUtil.throwFailException("父资源不存在");
                }
                resourceInsert = new AutResource();
                resourceInsert.setBatchId(batchId);
                resourceInsert.setName(record.getName());
                resourceInsert.setType(record.getType());
                resourceInsert.setUrl("");
                resourceInsert.setPath("");
                resourceInsert.setDescription(record.getDescription());
                resourceInsert.setParentId(parentId);
                String parentIds = parentResource.getParentIds();
                if (StringUtil.isEmpty(parentIds)) {
                    parentIds = "";
                }
                resourceInsert.setParentIds(parentIds + "/" + parentId);
                resourceInsert.setPermission(record.getPermission());
                resourceInsert.setIndex(TableUtils.getResourceIndex(index));
                resourceInsert.setStatus(record.getStatus());
                resourceInsert.setCreateTime(timeString);
                resourceInsert.setCreateTimeStamp(timeStamp);
                resourcesInsert.add(resourceInsert);
            }
            int count = this.jdbcEngine.batchInsertJavaBeans(resourcesInsert, MySqlDynamicEngine.insert(resourceTableName, AutResourceModel.class));
            if (count != resourcesInsert.size()) {
                ExceptionUtil.throwFailException("新增资源失败");
            }
        }
        return this.jdbcEngine.queryForList(AutResourceGet.class, MySqlDynamicEngine.query(resourceTableName, AutResourceModel.class)
                .leftJoin(resourceTableName, AutResourceModel.class, "JurResLeft", (on, joinTable, mainTable) -> on
                        .and(joinTable.parentId().equalTo(mainTable.id())))
                .functionColumn(AutResourceModel.class, "JurResLeft", FunctionColumnType.COUNT, table -> table.id("childCount"))
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.batchId().equalTo(batchId)))
                .group(AutResourceModel.Group::id));
    }

    @Override
    public List<AutResourceGet> postResource(String moduleId, AutResourcePost record) throws Exception {
        String resourceTableName = TableUtils.getResourceTableName(moduleId);
        String roleTableName = TableUtils.getRoleTableName(moduleId);
        String roleResourceTableName = TableUtils.getRoleResourceTableName(moduleId);
        if (StringUtil.isEmpty(record.getName())) {
            ExceptionUtil.throwFailException("资源名称不能为空");
        }
        if (StringUtil.isEmpty(record.getType())) {
            ExceptionUtil.throwFailException("资源类型不能为空");
        }
        if (!EnumMethods.contains(record.getType(), AutResourceType.values())) {
            ExceptionUtil.throwFailException("资源类型不正确");
        }
        if (EnumMethods.equalsTo(record.getType(), AutResourceType.NODE)) {
            return this.postNodeResource(moduleId, record);
        }
        if (AutResourceType.URL.equalsTo(record.getType()) && StringUtil.isEmpty(record.getUrl())) {
            ExceptionUtil.throwFailException("资源请求地址不能为空");
        }
        if (AutResourceType.PERMISSION.equalsTo(record.getType()) && StringUtil.isEmpty(record.getPermission())) {
            ExceptionUtil.throwFailException("资源权限不能为空");
        }
        if (StringUtil.isEmpty(record.getStatus())) {
            record.setStatus(Status.NORMAL.name());
        } else if (!EnumMethods.contains(record.getStatus(), Status.values())) {
            ExceptionUtil.throwFailException("状态类型不正确");
        }

        String timeString = Time.localDateTimeNow();
        long timeStamp = Time.timeStamp();
        String batchId = TableUtils.buildResourceBatchId();

        Set<String> parentIdSet = record.getParentIdSet();
        Long index = this.jdbcEngine.queryColumnOne(1, Long.class, MySqlDynamicEngine.query(resourceTableName, AutResourceModel.class)
                .functionColumn(FunctionColumnType.MAX, AutResourceModel.Column::index));
        AutResource resourceInsert;
        //没有父节点
        if (StringUtil.isEmpty(parentIdSet)) {
            resourceInsert = new AutResource();
            resourceInsert.setBatchId(batchId);
            resourceInsert.setName(record.getName());
            resourceInsert.setType(record.getType());
            resourceInsert.setUrl(record.getUrl());
            resourceInsert.setPath(record.getPath());
            resourceInsert.setDescription(record.getDescription());
            resourceInsert.setParentId("");
            resourceInsert.setParentIds("");
            resourceInsert.setPermission(record.getPermission());
            resourceInsert.setIndex(TableUtils.getResourceIndex(index));
            resourceInsert.setStatus(record.getStatus());
            resourceInsert.setCreateTime(timeString);
            resourceInsert.setCreateTimeStamp(timeStamp);
            int count = this.jdbcEngine.insertJavaBeanSelective(resourceInsert, MySqlDynamicEngine.insert(resourceTableName, AutResourceModel.class));
            if (count != 1) {
                ExceptionUtil.throwFailException("新增资源失败");
            }
        } else {
            // 有父节点
            Map<Long, AutResource> parentResourceMap = this.jdbcEngine.queryInMap(AutResourceModel.primaryKeyAlias, AutResource.class, MySqlDynamicEngine.query(resourceTableName, AutResourceModel.class)
                    .where((condition, mainTable) -> condition
                            .and(mainTable.id().inS(parentIdSet))));
            AutResource parentResource;
            List<AutResource> resourcesInsert = new ArrayList<>();
            for (String parentId : parentIdSet) {
                parentResource = parentResourceMap.get(Long.parseLong(parentId));
                if (parentResource == null) {
                    ExceptionUtil.throwFailException("父资源不存在");
                }
                resourceInsert = new AutResource();
                resourceInsert.setBatchId(batchId);
                resourceInsert.setName(record.getName());
                resourceInsert.setType(record.getType());
                resourceInsert.setUrl(record.getUrl());
                resourceInsert.setPath(record.getPath());
                resourceInsert.setDescription(record.getDescription());
                resourceInsert.setParentId(parentId);
                String parentIds = parentResource.getParentIds();
                if (StringUtil.isEmpty(parentIds)) {
                    parentIds = "";
                }
                resourceInsert.setParentIds(parentIds + "/" + parentId);
                resourceInsert.setPermission(record.getPermission());
                resourceInsert.setIndex(TableUtils.getResourceIndex(index));
                resourceInsert.setStatus(record.getStatus());
                resourceInsert.setCreateTime(timeString);
                resourceInsert.setCreateTimeStamp(timeStamp);
                resourcesInsert.add(resourceInsert);
            }
            int count = this.jdbcEngine.batchInsertJavaBeans(resourcesInsert, MySqlDynamicEngine.insert(resourceTableName, AutResourceModel.class));
            if (count != resourcesInsert.size()) {
                ExceptionUtil.throwFailException("新增资源失败");
            }
        }
        List<AutResourceGet> rows = this.jdbcEngine.queryForList(AutResourceGet.class, MySqlDynamicEngine.query(resourceTableName, AutResourceModel.class)
                .leftJoin(resourceTableName, AutResourceModel.class, "JurResLeft", (on, joinTable, mainTable) -> on
                        .and(joinTable.parentId().equalTo(mainTable.id())))
                .functionColumn(AutResourceModel.class, "JurResLeft", FunctionColumnType.COUNT, table -> table.id("childCount"))
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.batchId().equalTo(batchId)))
                .group(AutResourceModel.Group::id));
        //新增角色资源数据
        Set<String> roleIdSet = record.getRoleIdSet();
        if (StringUtil.isEmpty(roleIdSet)) {
            return rows;
        }
        List<AutRole> roleList = this.jdbcEngine.queryForList(AutRole.class, MySqlDynamicEngine.query(roleTableName, AutRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().inS(roleIdSet))));
        if (roleIdSet.size() != roleList.size()) {
            ExceptionUtil.throwFailException("授予的角色不存在");
        }
        List<AutRoleResource> roleResourcesInsert = this.buildRoleResourceListData(roleList, rows, roleRes -> {
            roleRes.setCreateTime(timeString);
            roleRes.setCreateTimeStamp(timeStamp);
            roleRes.setStatus(Status.NORMAL.name());
        });
        if (StringUtil.isEmpty(roleResourcesInsert)) {
            return rows;
        }
        int count = this.jdbcEngine.batchInsertJavaBeans(roleResourcesInsert, MySqlDynamicEngine.insert(roleResourceTableName, AutRoleResourceModel.class));
        if (count != roleResourcesInsert.size()) {
            ExceptionUtil.throwFailException("创建角色资源失败");
        }
        return rows;
    }

    @Override
    public void putResourceByResourceId(String moduleId, String resourceId, AutResourcePut record) throws Exception {
        String resourceTableName = TableUtils.getResourceTableName(moduleId);
        if (StringUtil.isEmpty(resourceId)) {
            ExceptionUtil.throwFailException("资源ID不能为空");
        }
        AutResource resource = this.jdbcEngine.queryByPrimaryKey(resourceId, AutResource.class, MySqlDynamicEngine.query(resourceTableName, AutResourceModel.class));
        if (resource == null) {
            ExceptionUtil.throwFailException(40404, "资源不存在");
        }
        AutResource resourceUpdate = new AutResource();
        AutRoleResource roleResourceUpdate = null;

        if (!StringUtil.isEmpty(record.getName()) && !record.getName().equals(resource.getName())) {
            resourceUpdate.setName(record.getName());
            roleResourceUpdate = new AutRoleResource();
            roleResourceUpdate.setResourceName(record.getName());
        }
        if (!StringUtil.isEmpty(record.getType()) && !record.getType().equals(resource.getType())) {
            if (!EnumMethods.contains(record.getType(), AutResourceType.values())) {
                ExceptionUtil.throwFailException("资源类型不正确");
            }
            resourceUpdate.setType(record.getType());
            if (roleResourceUpdate == null) {
                roleResourceUpdate = new AutRoleResource();
            }
            roleResourceUpdate.setResourceType(record.getType());
        }
        if (!StringUtil.isEmpty(record.getUrl()) && !record.getUrl().equals(resource.getUrl())) {
            resourceUpdate.setUrl(record.getUrl());
            if (roleResourceUpdate == null) {
                roleResourceUpdate = new AutRoleResource();
            }
            roleResourceUpdate.setResourceUrl(record.getUrl());
        }
        if (!StringUtil.isEmpty(record.getPath()) && !record.getPath().equals(resource.getPath())) {
            resourceUpdate.setPath(record.getPath());
        }
        resourceUpdate.setDescription(record.getDescription());
        if (!StringUtil.isEmpty(record.getPermission()) && !record.getPermission().equals(resource.getPermission())) {
            resourceUpdate.setPermission(record.getPermission());
            if (roleResourceUpdate == null) {
                roleResourceUpdate = new AutRoleResource();
            }
            roleResourceUpdate.setResourcePermission(record.getPermission());
        }
        if (!StringUtil.isEmpty(record.getStatus()) && !record.getStatus().equals(resource.getStatus())) {
            if (!EnumMethods.contains(record.getStatus(), Status.values())) {
                ExceptionUtil.throwFailException("状态类型不正确");
            }
            resourceUpdate.setStatus(record.getStatus());
        }
        String timeString = Time.localDateTimeNow();
        long timeStamp = Time.timeStamp();
        resourceUpdate.setUpdateTime(timeString);
        resourceUpdate.setUpdateTimeStamp(timeStamp);
        //更新资源
        int count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(resourceId, resourceUpdate, MySqlDynamicEngine.update(resourceTableName, AutResourceModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("更新资源失败");
        }
        if (roleResourceUpdate != null) {
            roleResourceUpdate.setUpdateTime(timeString);
            roleResourceUpdate.setUpdateTimeStamp(timeStamp);
            this.roleResourceService.putListRoleResourceByResourceId(moduleId, resourceId, roleResourceUpdate);
        }
    }

    @Override
    public void deleteResourceByResourceId(String moduleId, String resourceId) throws Exception {
        String resourceTableName = TableUtils.getResourceTableName(moduleId);
        if (StringUtil.isEmpty(resourceId)) {
            ExceptionUtil.throwFailException("资源ID不能为空");
        }
        //查询出所有子资源的ID
        Set<String> ids = this.getChildrenIds(resourceId, moduleId);
        ids.add(resourceId);
        //删除资源
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(resourceTableName, AutResourceModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().inS(ids))));
        if (count != ids.size()) {
            ExceptionUtil.throwFailException("删除资源失败");
        }
        //删除角色资源数据
        this.roleResourceService.deleteListRoleResourceByResourceIds(moduleId, new ArrayList<>(ids));
    }

    @Override
    public void deleteListResourceByResourceIds(String moduleId, List<String> resourceIds) throws Exception {
        String resourceTableName = TableUtils.getResourceTableName(moduleId);
        if (StringUtil.isEmpty(resourceIds)) {
            ExceptionUtil.throwFailException("资源ID不能为空");
        }
        //查询出所有子资源的ID
        Set<String> ids = new HashSet<>();
        for (String resourceId : resourceIds) {
            this.getChildrenIds(resourceId, moduleId);
            ids.add(resourceId);
        }
        //删除资源
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(resourceTableName, AutResourceModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().inS(ids))));
        if (count != ids.size()) {
            ExceptionUtil.throwFailException("删除资源失败");
        }
        //删除角色资源数据
        this.roleResourceService.deleteListRoleResourceByResourceIds(moduleId, new ArrayList<>(ids));
    }

}
