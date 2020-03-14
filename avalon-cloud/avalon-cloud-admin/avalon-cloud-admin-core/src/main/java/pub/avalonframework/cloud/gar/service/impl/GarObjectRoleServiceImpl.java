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
import pub.avalonframework.cloud.gar.entity.AutObjectRole;
import pub.avalonframework.cloud.gar.entity.AutRole;
import pub.avalonframework.cloud.gar.model.AutObjectRoleModel;
import pub.avalonframework.cloud.gar.model.AutRoleModel;
import pub.avalonframework.cloud.gar.service.GarObjectRoleService;
import pub.avalonframework.cloud.gar.utils.TableUtils;

import java.util.*;

/**
 * @author baichao
 */
@Service
public class GarObjectRoleServiceImpl implements GarObjectRoleService {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    public boolean getValidateObjectRoleExistByObjectIdAndRoleId(String moduleId, String objectId, String roleId) throws Exception {
        String objectRoleTableName = TableUtils.getObjectRoleTableName(moduleId);
        return this.jdbcEngine.queryCount(MySqlDynamicEngine.query(objectRoleTableName, AutObjectRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.roleId().equalTo(roleId).objectId().equalTo(objectId)))) > 0;
    }

    public boolean getValidateObjectRoleExistByObjectIdAndRoleValue(String moduleId, String objectId, String roleValue) throws Exception {
        String objectRoleTableName = TableUtils.getObjectRoleTableName(moduleId);
        return this.jdbcEngine.queryCount(MySqlDynamicEngine.query(objectRoleTableName, AutObjectRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.roleValue().equalTo(roleValue).objectId().equalTo(objectId)))) > 0;
    }

    @Override
    public void postObjectRoleByObjectIdAndRoleId(String moduleId, String objectId, String roleId) throws Exception {
        String roleTableName = TableUtils.getRoleTableName(moduleId);
        String objectRoleTableName = TableUtils.getObjectRoleTableName(moduleId);
        if (StringUtil.isEmpty(objectId)) {
            ExceptionUtil.throwFailException("对象ID不能为空");
        }
        if (StringUtil.isEmpty(roleId)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        if (this.getValidateObjectRoleExistByObjectIdAndRoleId(moduleId, objectId, roleId)) {
            ExceptionUtil.throwFailException("该对象已经拥有该角色");
        }
        AutRole role = this.jdbcEngine.queryByPrimaryKey(roleId, AutRole.class, MySqlDynamicEngine.query(roleTableName, AutRoleModel.class));
        if (role == null) {
            ExceptionUtil.throwFailException("角色不存在");
        }
        if (!EnumMethods.equalsTo(role.getStatus(), Status.NORMAL)) {
            ExceptionUtil.throwFailException("角色不可用");
        }

        String timeString = Time.localDateTimeNow();
        long timeStamp = Time.timeStamp();

        AutObjectRole objectRoleInsert = new AutObjectRole();

        objectRoleInsert.setRoleId(role.getId());
        objectRoleInsert.setRoleValue(role.getValue());
        objectRoleInsert.setRoleName(role.getName());
        objectRoleInsert.setRoleType(role.getType());

        objectRoleInsert.setObjectId(objectId);
        objectRoleInsert.setObjectName("");

        objectRoleInsert.setStatus(Status.NORMAL.name());
        objectRoleInsert.setCreateTime(timeString);
        objectRoleInsert.setCreateTimeStamp(timeStamp);

        int count = this.jdbcEngine.insertJavaBeanSelective(objectRoleInsert, MySqlDynamicEngine.insert(objectRoleTableName, AutObjectRoleModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("新增对象角色失败");
        }
    }

    @Override
    public void postListObjectRoleByObjectIdsAndRoleIds(String moduleId, Set<String> objectIds, Set<String> roleIds) throws Exception {
        String objectRoleTableName = TableUtils.getObjectRoleTableName(moduleId);
        String roleTableName = TableUtils.getRoleTableName(moduleId);
        if (StringUtil.isEmpty(objectIds)) {
            ExceptionUtil.throwFailException("对象ID不能为空");
        }
        if (StringUtil.isEmpty(roleIds)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }

        Map<Long, String> mapByObjectIds = this.jdbcEngine.queryPairColumnInMap(AutObjectRoleModel.primaryKeyAlias, AutObjectRoleModel.objectId_alias, MySqlDynamicEngine.query(objectRoleTableName, AutObjectRoleModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.objectId().inS(objectIds))));
        Map<Long, String> mapByRoleIds = this.jdbcEngine.queryPairColumnInMap(AutObjectRoleModel.primaryKeyAlias, AutObjectRoleModel.roleId_alias, MySqlDynamicEngine.query(objectRoleTableName, AutObjectRoleModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.roleId().inS(roleIds))));

        //如果存在交集表示已经存在
        for (Map.Entry<Long, String> objectEntry : mapByObjectIds.entrySet()) {
            for (Map.Entry<Long, String> roleEntry : mapByRoleIds.entrySet()) {
                if (objectEntry.getKey().equals(roleEntry.getKey())) {
                    ExceptionUtil.throwFailException("对象ID: [" + objectEntry.getValue() + "]已经拥有角色ID: [" + roleEntry.getValue() + "]");
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
        String timeString = Time.localDateTimeNow();
        long timeStamp = Time.timeStamp();

        List<AutObjectRole> objectRolesInsert = new ArrayList<>();
        AutObjectRole objectRoleInsert;
        AutRole role;
        for (Map.Entry<String, AutRole> roleEntry : roleMap.entrySet()) {
            role = roleEntry.getValue();
            if (!EnumMethods.equalsTo(role.getStatus(), Status.NORMAL)) {
                ExceptionUtil.throwFailException("角色不可用");
            }
            for (String objectId : objectIds) {
                objectRoleInsert = new AutObjectRole();

                objectRoleInsert.setRoleId(role.getId());
                objectRoleInsert.setRoleValue(role.getValue());
                objectRoleInsert.setRoleName(role.getName());
                objectRoleInsert.setRoleType(role.getType());

                objectRoleInsert.setObjectId(objectId);
                objectRoleInsert.setObjectName("");

                objectRoleInsert.setStatus(Status.NORMAL.name());
                objectRoleInsert.setCreateTime(timeString);
                objectRoleInsert.setCreateTimeStamp(timeStamp);

                objectRolesInsert.add(objectRoleInsert);
            }
        }
        int count = this.jdbcEngine.batchInsertJavaBeans(objectRolesInsert, MySqlDynamicEngine.insert(objectRoleTableName, AutObjectRoleModel.class));
        if (count != objectRolesInsert.size()) {
            ExceptionUtil.throwFailException("新增对象角色失败");
        }
    }

    @Override
    public void postObjectRoleByObjectIdAndRoleValue(String moduleId, String objectId, String roleValue) throws Exception {
        String roleTableName = TableUtils.getRoleTableName(moduleId);
        String objectRoleTableName = TableUtils.getObjectRoleTableName(moduleId);
        if (StringUtil.isEmpty(objectId)) {
            ExceptionUtil.throwFailException("对象ID不能为空");
        }
        if (StringUtil.isEmpty(roleValue)) {
            ExceptionUtil.throwFailException("角色标识符不能为空");
        }
        if (this.getValidateObjectRoleExistByObjectIdAndRoleValue(moduleId, objectId, roleValue)) {
            ExceptionUtil.throwFailException("该对象已经拥有该角色");
        }
        AutRole role = this.jdbcEngine.queryOne(AutRole.class, MySqlDynamicEngine.query(roleTableName, AutRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.value().equalTo(roleValue))));
        if (role == null) {
            ExceptionUtil.throwFailException("角色不存在");
        }
        if (!EnumMethods.equalsTo(role.getStatus(), Status.NORMAL)) {
            ExceptionUtil.throwFailException("角色不可用");
        }

        String timeString = Time.localDateTimeNow();
        long timeStamp = Time.timeStamp();

        AutObjectRole objectRoleInsert = new AutObjectRole();

        objectRoleInsert.setRoleId(role.getId());
        objectRoleInsert.setRoleValue(role.getValue());
        objectRoleInsert.setRoleName(role.getName());
        objectRoleInsert.setRoleType(role.getType());

        objectRoleInsert.setObjectId(objectId);
        objectRoleInsert.setObjectName("");

        objectRoleInsert.setStatus(Status.NORMAL.name());
        objectRoleInsert.setCreateTime(timeString);
        objectRoleInsert.setCreateTimeStamp(timeStamp);

        int count = this.jdbcEngine.insertJavaBeanSelective(objectRoleInsert, MySqlDynamicEngine.insert(objectRoleTableName, AutObjectRoleModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("新增对象角色失败");
        }
    }

    @Override
    public void putListObjectRoleByRoleId(String moduleId, String roleId, AutObjectRole objectRoleUpdate) throws Exception {
        String objectRoleTableName = TableUtils.getObjectRoleTableName(moduleId);
        if (StringUtil.isEmpty(roleId)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        this.jdbcEngine.updateJavaBeanSelective(objectRoleUpdate, MySqlDynamicEngine.update(objectRoleTableName, AutObjectRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.roleId().equalTo(roleId))));
    }

    @Override
    public void deleteListObjectRoleByRoleId(String moduleId, String roleId) throws Exception {
        String objectRoleTableName = TableUtils.getObjectRoleTableName(moduleId);
        if (StringUtil.isEmpty(roleId)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(objectRoleTableName, AutObjectRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.roleId().equalTo(roleId))));
    }

    @Override
    public void deleteListObjectRoleByRoleIds(String moduleId, List<String> roleIds) throws Exception {
        String objectRoleTableName = TableUtils.getObjectRoleTableName(moduleId);
        if (StringUtil.isEmpty(roleIds)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(objectRoleTableName, AutObjectRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.roleId().inS(roleIds))));
    }

    @Override
    public void deleteObjectRoleByObjectRoleId(String moduleId, String objectRoleId) throws Exception {
        String objectRoleTableName = TableUtils.getObjectRoleTableName(moduleId);
        if (StringUtil.isEmpty(objectRoleId)) {
            ExceptionUtil.throwFailException("对象角色ID不能为空");
        }
        int count = this.jdbcEngine.deleteByPrimaryKey(objectRoleId, MySqlDynamicEngine.delete(objectRoleTableName, AutObjectRoleModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("删除对象角色失败");
        }
    }

    @Override
    public void deleteListObjectRoleByObjectRoleIds(String moduleId, Set<String> objectRoleIds) throws Exception {
        String objectRoleTableName = TableUtils.getObjectRoleTableName(moduleId);
        if (StringUtil.isEmpty(objectRoleIds)) {
            ExceptionUtil.throwFailException("对象角色ID不能为空");
        }
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(objectRoleTableName, AutObjectRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().inS(objectRoleIds))));
        if (count != objectRoleIds.size()) {
            ExceptionUtil.throwFailException("删除对象角色失败");
        }
    }

    @Override
    public void deleteObjectRoleByObjectIdAndRoleId(String moduleId, String objectId, String roleId) throws Exception {
        String objectRoleTableName = TableUtils.getObjectRoleTableName(moduleId);
        if (StringUtil.isEmpty(objectId)) {
            ExceptionUtil.throwFailException("对象ID不能为空");
        }
        if (StringUtil.isEmpty(roleId)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(objectRoleTableName, AutObjectRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.objectId().equalTo(objectId)
                                .roleId().equalTo(roleId))));
        if (count != 1) {
            ExceptionUtil.throwFailException("删除对象角色失败");
        }
    }

    @Override
    public void deleteListObjectRoleByObjectIdsAndRoleIds(String moduleId, Set<String> objectIds, Set<String> roleIds) throws Exception {
        String objectRoleTableName = TableUtils.getObjectRoleTableName(moduleId);
        if (StringUtil.isEmpty(objectIds)) {
            ExceptionUtil.throwFailException("对象ID不能为空");
        }
        if (StringUtil.isEmpty(roleIds)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        List<String> objectRoleIdsByObjectIds = this.jdbcEngine.queryColumnList(AutObjectRoleModel.primaryKeyAlias, String.class, MySqlDynamicEngine.query(objectRoleTableName, AutObjectRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.objectId().inS(objectIds))));
        List<String> objectRoleIdsByRoleIds = this.jdbcEngine.queryColumnList(AutObjectRoleModel.primaryKeyAlias, String.class, MySqlDynamicEngine.query(objectRoleTableName, AutObjectRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.roleId().inS(roleIds))));

        //找出要删除的ID
        Set<String> ids = new HashSet<>();
        for (String objectRoleIdsByObjectId : objectRoleIdsByObjectIds) {
            for (String objectRoleIdsByRoleId : objectRoleIdsByRoleIds) {
                if (objectRoleIdsByObjectId.equals(objectRoleIdsByRoleId)) {
                    ids.add(objectRoleIdsByRoleId);
                }
            }
        }
        if (StringUtil.isEmpty(ids)) {
            ExceptionUtil.throwFailException("没有可以删除的对象角色");
        }
        //执行删除
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(objectRoleTableName, AutObjectRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().inS(ids))));
        if (count != ids.size()) {
            ExceptionUtil.throwFailException("删除对象角色失败");
        }
    }
}
