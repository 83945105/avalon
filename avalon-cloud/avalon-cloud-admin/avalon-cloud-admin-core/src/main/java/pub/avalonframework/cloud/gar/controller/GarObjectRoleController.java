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
import pub.avalonframework.cloud.gar.api.GarObjectRoleApi;
import pub.avalonframework.cloud.gar.dc.AutObjectRoleGet;
import pub.avalonframework.cloud.gar.model.AutObjectRoleModel;
import pub.avalonframework.cloud.gar.service.GarObjectRoleService;
import pub.avalonframework.cloud.gar.utils.TableUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author 白超
 * @date 2018/7/25
 */
@RestController
@RequestMapping(GarObjectRoleApi.ROOT_PATH)
public class GarObjectRoleController implements GarObjectRoleApi {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired
    private GarObjectRoleService objectRoleService;

    @Autowired
    private HttpServletRequest request;

    @Override
    @RequestMapping(value = "/get/list/objectRoleByObjectId/{objectId}")
    public DataView getListObjectRoleByObjectId(@PathVariable String objectId, AutObjectRoleGet record, String moduleId) throws Exception {
        String objectRoleTableName = TableUtils.getObjectRoleTableName(moduleId, request);
        List<Map<String, Object>> rows = this.jdbcEngine.queryForList(MySqlDynamicEngine.query(objectRoleTableName, AutObjectRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.objectId().equalTo(objectId))));
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/get/list/objectRoleByRoleId/{roleId}")
    public DataView getListObjectRoleByRoleId(@PathVariable String roleId, AutObjectRoleGet record, String moduleId) throws Exception {
        String objectRoleTableName = TableUtils.getObjectRoleTableName(moduleId, request);
        List<Map<String, Object>> rows = this.jdbcEngine.queryForList(MySqlDynamicEngine.query(objectRoleTableName, AutObjectRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.roleId().equalTo(roleId))));
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/get/list/objectIdByRoleId/{roleId}")
    public DataView getListObjectIdByRoleId(@PathVariable String roleId, String moduleId) throws Exception {
        String objectRoleTableName = TableUtils.getObjectRoleTableName(moduleId, request);
        List<String> objectIds = this.jdbcEngine.queryColumnList(AutObjectRoleModel.objectId_alias, String.class, MySqlDynamicEngine.query(objectRoleTableName, AutObjectRoleModel.class)
                .column(AutObjectRoleModel.Column::objectId)
                .where((condition, mainTable) -> condition
                        .and(mainTable.roleId().equalTo(roleId))));
        return DataViewUtil.getModelViewSuccess(objectIds);
    }

    @Override
    @RequestMapping(value = "/get/list/objectIdByRoleValue/{roleValue}")
    public DataView getListObjectIdByRoleValue(@PathVariable String roleValue, String moduleId) throws Exception {
        String objectRoleTableName = TableUtils.getObjectRoleTableName(moduleId, request);
        List<String> objectIds = this.jdbcEngine.queryColumnList(AutObjectRoleModel.objectId_alias, String.class, MySqlDynamicEngine.query(objectRoleTableName, AutObjectRoleModel.class)
                .column(AutObjectRoleModel.Column::objectId)
                .where((condition, mainTable) -> condition
                        .and(mainTable.roleValue().equalTo(roleValue))));
        return DataViewUtil.getModelViewSuccess(objectIds);
    }

    @Override
    @RequestMapping(value = "/get/list/roleValueByObjectId/{objectId}")
    public DataView getListRoleValueByObjectId(@PathVariable String objectId, String moduleId) throws Exception {
        String objectRoleTableName = TableUtils.getObjectRoleTableName(moduleId, request);
        List<String> roleRoles = this.jdbcEngine.queryColumnList(AutObjectRoleModel.roleValue_alias, String.class, MySqlDynamicEngine.query(objectRoleTableName, AutObjectRoleModel.class)
                .column(AutObjectRoleModel.Column::roleValue)
                .where((condition, mainTable) -> condition
                        .and(mainTable.objectId().equalTo(objectId))));
        return DataViewUtil.getModelViewSuccess(roleRoles);
    }

    @Override
    @RequestMapping(value = "/get/list/roleByObjectId/{objectId}")
    public DataView getListRoleByObjectId(@PathVariable String objectId, String moduleId) throws Exception {
        String objectRoleTableName = TableUtils.getObjectRoleTableName(moduleId, request);
        List<Map<String, Object>> rows = this.jdbcEngine.queryForList(MySqlDynamicEngine.query(objectRoleTableName, AutObjectRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.objectId().equalTo(objectId))));
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/post/objectRoleByObjectIdAndRoleId/{objectId}/{roleId}")
    public DataView postObjectRoleByObjectIdAndRoleId(@PathVariable String objectId, @PathVariable String roleId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.objectRoleService.postObjectRoleByObjectIdAndRoleId(moduleId, objectId, roleId);
        return DataViewUtil.getMessageViewSuccess("新增对象角色成功");
    }

    @Override
    @RequestMapping(value = "/post/list/objectRoleByObjectIdsAndRoleIds/{objectIds}/{roleIds}")
    public DataView postListObjectRoleByObjectIdsAndRoleIds(@PathVariable String objectIds, @PathVariable String roleIds, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.objectRoleService.postListObjectRoleByObjectIdsAndRoleIds(moduleId,
                StringUtil.isEmpty(objectIds) ? null : new HashSet<>(Arrays.asList(objectIds.split(","))),
                StringUtil.isEmpty(roleIds) ? null : new HashSet<>(Arrays.asList(roleIds.split(","))));
        return DataViewUtil.getMessageViewSuccess("新增对象角色成功");
    }

    @Override
    @RequestMapping(value = "/post/objectRoleByObjectIdAndRoleValue/{objectId}/{roleValue}")
    public DataView postObjectRoleByObjectIdAndRoleValue(@PathVariable String objectId, @PathVariable String roleValue, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.objectRoleService.postObjectRoleByObjectIdAndRoleValue(moduleId, objectId, roleValue);
        return DataViewUtil.getMessageViewSuccess("新增对象角色成功");
    }

    @Override
    @RequestMapping(value = "/delete/objectRoleByObjectRoleId/{objectRoleId}")
    public DataView deleteObjectRoleByObjectRoleId(@PathVariable String objectRoleId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.objectRoleService.deleteObjectRoleByObjectRoleId(moduleId, objectRoleId);
        return DataViewUtil.getMessageViewSuccess("删除对象角色成功");
    }

    @Override
    @RequestMapping(value = "/delete/list/objectRoleByObjectRoleIds/{objectRoleIds}")
    public DataView deleteListObjectRoleByObjectRoleIds(@PathVariable String objectRoleIds, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.objectRoleService.deleteListObjectRoleByObjectRoleIds(moduleId,
                StringUtil.isEmpty(objectRoleIds) ? null : new HashSet<>(Arrays.asList(objectRoleIds.split(","))));
        return DataViewUtil.getMessageViewSuccess("删除对象角色成功");
    }

    @Override
    @RequestMapping(value = "/delete/objectRoleByObjectIdAndRoleId/{objectId}/{roleId}")
    public DataView deleteObjectRoleByObjectIdAndRoleId(@PathVariable String objectId, @PathVariable String roleId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.objectRoleService.deleteObjectRoleByObjectIdAndRoleId(moduleId, objectId, roleId);
        return DataViewUtil.getMessageViewSuccess("删除对象角色成功");
    }

    @Override
    @RequestMapping(value = "/delete/list/objectRoleByObjectIdsAndRoleIds/{objectIds}/{roleIds}")
    public DataView deleteListObjectRoleByObjectIdsAndRoleIds(@PathVariable String objectIds, @PathVariable String roleIds, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.objectRoleService.deleteListObjectRoleByObjectIdsAndRoleIds(moduleId,
                StringUtil.isEmpty(objectIds) ? null : new HashSet<>(Arrays.asList(objectIds.split(","))),
                StringUtil.isEmpty(roleIds) ? null : new HashSet<>(Arrays.asList(roleIds.split(","))));
        return DataViewUtil.getMessageViewSuccess("删除对象角色成功");
    }
}