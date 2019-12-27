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
import pub.avalonframework.cloud.gar.dc.SubModuleGet;
import pub.avalonframework.cloud.gar.dc.SubModulePost;
import pub.avalonframework.cloud.gar.dc.SubModulePut;
import pub.avalonframework.cloud.gar.entity.Module;
import pub.avalonframework.cloud.gar.entity.SubModule;
import pub.avalonframework.cloud.gar.model.ModuleModel;
import pub.avalonframework.cloud.gar.model.SubModuleModel;
import pub.avalonframework.cloud.gar.service.GarMenuGroupService;
import pub.avalonframework.cloud.gar.service.GarRouteService;
import pub.avalonframework.cloud.gar.service.GarSubModuleService;
import pub.avalonframework.cloud.gar.service.GarTemplateService;
import pub.avalonframework.cloud.gar.utils.TableUtils;

import java.util.List;

/**
 * @author 白超
 * @date 2018/12/6
 */
@Service
public class GarSubModuleServiceImpl implements GarSubModuleService {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired
    private GarRouteService routeService;
    @Autowired
    private GarTemplateService templateService;
    @Autowired
    private GarMenuGroupService menuGroupService;

    @Override
    public boolean getValidateValueCanUse(String moduleId, String value, List<String> excludeValues) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        return this.jdbcEngine.queryCount(MySqlDynamicEngine.query(SubModuleModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId)
                        .value().equalTo(value)
                        .value().notInS(excludeValues)))) == 0;
    }

    @Override
    public SubModuleGet postSubModule(String moduleId, SubModulePost record) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(record.getName())) {
            ExceptionUtil.throwFailException("子模块名称不能为空");
        }
        if (StringUtil.isEmpty(record.getValue())) {
            ExceptionUtil.throwFailException("子模块唯一标识符不能为空");
        }
        if (!this.getValidateValueCanUse(moduleId, record.getValue(), null)) {
            ExceptionUtil.throwFailException("子模块唯一标识符不可用");
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

        String id = moduleId + "-" + record.getValue();

        SubModule subModuleInsert = new SubModule();

        subModuleInsert.setId(id);
        subModuleInsert.setModuleId(moduleId);
        subModuleInsert.setModuleName(module.getServiceName());
        subModuleInsert.setName(record.getName());
        subModuleInsert.setValue(record.getValue());
        subModuleInsert.setDescription(record.getDescription());

        subModuleInsert.setStatus(record.getStatus());
        subModuleInsert.setCreateTime(Time.localDateTimeNow());
        subModuleInsert.setCreateTimeStamp(Time.timeStamp());

        Long index = this.jdbcEngine.queryColumnOne(1, Long.class, MySqlDynamicEngine.query(SubModuleModel.class)
                .functionColumn(FunctionColumnType.MAX, SubModuleModel.Column::index));
        subModuleInsert.setIndex(TableUtils.getSubModuleIndex(index));

        int count = this.jdbcEngine.insertJavaBeanSelective(subModuleInsert, MySqlDynamicEngine.insert(SubModuleModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("新增子模块失败");
        }
        return this.jdbcEngine.queryByPrimaryKey(id, SubModuleGet.class, MySqlDynamicEngine.query(SubModuleModel.class));
    }

    @Override
    public void putSubModuleBySubModuleId(String moduleId, String subModuleId, SubModulePut record) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(subModuleId)) {
            ExceptionUtil.throwFailException("子模块ID不能为空");
        }
        SubModule subModule = this.jdbcEngine.queryOne(SubModule.class, MySqlDynamicEngine.query(SubModuleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId).id().equalTo(subModuleId))));
        if (subModule == null) {
            ExceptionUtil.throwFailException("子模块不存在");
        }

        SubModule subModuleUpdate = new SubModule();

        subModuleUpdate.setDescription(record.getDescription());
        if (!StringUtil.isEmpty(record.getStatus()) && !record.getStatus().equals(subModule.getStatus())) {
            if (!EnumMethods.contains(record.getStatus(), Status.values())) {
                ExceptionUtil.throwFailException("状态类型不正确");
            }
            subModuleUpdate.setStatus(record.getStatus());
        }

        String timeString = Time.localDateTimeNow();
        Long timeStamp = Time.timeStamp();

        subModuleUpdate.setUpdateTime(timeString);
        subModuleUpdate.setUpdateTimeStamp(timeStamp);

        int count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(subModuleId, subModuleUpdate, MySqlDynamicEngine.update(SubModuleModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("更新子模块失败");
        }

    }

    @Override
    public void putListSubModuleByModuleId(String moduleId, SubModule subModuleUpdate) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        this.jdbcEngine.updateJavaBeanSelective(subModuleUpdate, MySqlDynamicEngine.update(SubModuleModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId))));
    }

    @Override
    public void putSwitchSubModuleIndexBySubModuleId(String moduleId, String sourceSubModuleId, String targetSubModuleId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        SubModule sourceSubModule = this.jdbcEngine.queryOne(SubModule.class, MySqlDynamicEngine.query(SubModuleModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId).id().equalTo(sourceSubModuleId))));
        if (sourceSubModule == null) {
            ExceptionUtil.throwFailException(40404, "子模块不存在");
        }
        SubModule targetSubModule = this.jdbcEngine.queryOne(SubModule.class, MySqlDynamicEngine.query(SubModuleModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId).id().equalTo(targetSubModuleId))));
        if (targetSubModule == null) {
            ExceptionUtil.throwFailException(40404, "子模块不存在");
        }
        SubModule subModuleUpdate = new SubModule();
        subModuleUpdate.setIndex(targetSubModule.getIndex());
        subModuleUpdate.setUpdateTime(Time.localDateTimeNow());
        subModuleUpdate.setUpdateTimeStamp(Time.timeStamp());

        int count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(sourceSubModuleId, subModuleUpdate, MySqlDynamicEngine.update(SubModuleModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("操作失败");
        }
        subModuleUpdate.setIndex(sourceSubModule.getIndex());
        count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(targetSubModuleId, subModuleUpdate, MySqlDynamicEngine.update(SubModuleModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("操作失败");
        }
    }

    @Override
    public void deleteSubModuleBySubModuleId(String moduleId, String subModuleId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(subModuleId)) {
            ExceptionUtil.throwFailException("子模块ID不能为空");
        }
        //删除子模块
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(SubModuleModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId).id().equalTo(subModuleId))));
        if (count != 1) {
            ExceptionUtil.throwFailException("删除子模块失败");
        }
        //删除路由数据(会同时删除路由视图模板数据、角色路由视图模板数据)
        this.routeService.deleteListRouteBySubModuleId(moduleId, subModuleId);
        //删除模板数据(会同时删除路由视图模板数据、角色路由视图模板数据)
        this.templateService.deleteListTemplateBySubModuleId(moduleId, subModuleId);
        //删除菜单组数据(会同时删除菜单数据、角色菜单数据、菜单路由数据)
        this.menuGroupService.deleteListMenuGroupBySubModuleId(moduleId, subModuleId);
    }

    @Override
    public void deleteListSubModuleBySubModuleIds(String moduleId, List<String> subModuleIds) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(subModuleIds)) {
            ExceptionUtil.throwFailException("子模块ID不能为空");
        }
        //删除子模块
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(SubModuleModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId).id().inS(subModuleIds))));
        if (count == 0) {
            ExceptionUtil.throwFailException("删除子模块失败");
        }
        //删除路由数据(会同时删除路由视图模板数据、角色路由视图模板数据)
        this.routeService.deleteListRouteBySubModuleIds(moduleId, subModuleIds);
        //删除模板数据(会同时删除路由视图模板数据、角色路由视图模板数据)
        this.templateService.deleteListTemplateBySubModuleIds(moduleId, subModuleIds);
        //删除菜单组数据(会同时删除菜单数据、角色菜单数据、菜单路由数据)
        this.menuGroupService.deleteListMenuGroupBySubModuleIds(moduleId, subModuleIds);
    }
}
