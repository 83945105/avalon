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
import pub.avalonframework.cloud.gar.beans.TemplateType;
import pub.avalonframework.cloud.gar.dc.TemplateGet;
import pub.avalonframework.cloud.gar.dc.TemplatePost;
import pub.avalonframework.cloud.gar.dc.TemplatePut;
import pub.avalonframework.cloud.gar.entity.*;
import pub.avalonframework.cloud.gar.model.ModuleModel;
import pub.avalonframework.cloud.gar.model.SubModuleModel;
import pub.avalonframework.cloud.gar.model.TemplateModel;
import pub.avalonframework.cloud.gar.service.GarRoleRouteViewTemplateService;
import pub.avalonframework.cloud.gar.service.GarRouteViewTemplateService;
import pub.avalonframework.cloud.gar.service.GarTemplateService;
import pub.avalonframework.cloud.gar.utils.TableUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author 白超
 * @date 2018/12/6
 */
@Service
public class GarTemplateServiceImpl implements GarTemplateService {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired
    private GarRouteViewTemplateService routeViewTemplateService;
    @Autowired
    private GarRoleRouteViewTemplateService roleRouteViewTemplateService;

    @Override
    public boolean getValidateValueCanUse(String moduleId, String subModuleId, String value, List<String> excludeValues) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(subModuleId)) {
            ExceptionUtil.throwFailException("子模块ID不能为空");
        }
        return this.jdbcEngine.queryCount(MySqlDynamicEngine.query(TemplateModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId)
                        .subModuleId().equalTo(subModuleId)
                        .value().equalTo(value)
                        .value().notInS(excludeValues)))) == 0;
    }

    @Override
    public TemplateGet postTemplate(String moduleId, TemplatePost record) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(record.getSubModuleId())) {
            ExceptionUtil.throwFailException("子模块ID不能为空");
        }
        if (StringUtil.isEmpty(record.getName())) {
            ExceptionUtil.throwFailException("模板名称不能为空");
        }
        if (StringUtil.isEmpty(record.getValue())) {
            ExceptionUtil.throwFailException("模板唯一标识符不能为空");
        }
        if (!this.getValidateValueCanUse(moduleId, record.getSubModuleId(), record.getValue(), null)) {
            ExceptionUtil.throwFailException("模板唯一标识符不可用");
        }
        if (StringUtil.isEmpty(record.getType())) {
            ExceptionUtil.throwFailException("模板类型不能为空");
        }
        if (!EnumMethods.contains(record.getType(), TemplateType.values())) {
            ExceptionUtil.throwFailException("模板类型不正确");
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

        Template templateInsert = new Template();

        templateInsert.setId(id);
        templateInsert.setModuleId(moduleId);
        templateInsert.setSubModuleId(subModule.getId());
        templateInsert.setSubModuleName(subModule.getName());
        templateInsert.setName(record.getName());
        templateInsert.setValue(record.getValue());
        templateInsert.setDescription(record.getDescription());
        templateInsert.setType(record.getType());

        templateInsert.setParentId("");
        templateInsert.setParentIds("");

        templateInsert.setStatus(record.getStatus());
        templateInsert.setCreateTime(Time.localDateTimeNow());
        templateInsert.setCreateTimeStamp(Time.timeStamp());

        Long index = this.jdbcEngine.queryColumnOne(1, Long.class, MySqlDynamicEngine.query(TemplateModel.class)
                .functionColumn(FunctionColumnType.MAX, TemplateModel.Column::index));
        templateInsert.setIndex(TableUtils.getTemplateIndex(index));

        int count = this.jdbcEngine.insertJavaBeanSelective(templateInsert, MySqlDynamicEngine.insert(TemplateModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("新增模板失败");
        }
        return this.jdbcEngine.queryByPrimaryKey(id, TemplateGet.class, MySqlDynamicEngine.query(TemplateModel.class));
    }

    @Override
    public void putTemplateByTemplateId(String moduleId, String templateId, TemplatePut record) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(templateId)) {
            ExceptionUtil.throwFailException("模板ID不能为空");
        }
        Template template = this.jdbcEngine.queryOne(Template.class, MySqlDynamicEngine.query(TemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId).id().equalTo(templateId))));
        if (template == null) {
            ExceptionUtil.throwFailException("模板不存在");
        }

        Template templateUpdate = new Template();
        RouteViewTemplate routeViewTemplateUpdate = null;
        AutRoleRouteViewTemplate roleRouteViewTemplateUpdate = null;

        if (!StringUtil.isEmpty(record.getName()) && !record.getName().equals(template.getName())) {
            templateUpdate.setName(record.getName());
            routeViewTemplateUpdate = new RouteViewTemplate();
            routeViewTemplateUpdate.setTemplateName(record.getName());
            roleRouteViewTemplateUpdate = new AutRoleRouteViewTemplate();
            roleRouteViewTemplateUpdate.setTemplateName(record.getName());
        }
        if (!StringUtil.isEmpty(record.getValue()) && !record.getValue().equals(template.getValue())) {
            if (!getValidateValueCanUse(moduleId, record.getSubModuleId(), record.getValue(), Collections.singletonList(template.getValue()))) {
                ExceptionUtil.throwFailException("模板唯一标识符不可用");
            }
            templateUpdate.setValue(record.getValue());
            if (routeViewTemplateUpdate == null) {
                routeViewTemplateUpdate = new RouteViewTemplate();
            }
            routeViewTemplateUpdate.setTemplateValue(record.getValue());
            if (roleRouteViewTemplateUpdate == null) {
                roleRouteViewTemplateUpdate = new AutRoleRouteViewTemplate();
            }
            roleRouteViewTemplateUpdate.setTemplateValue(record.getValue());
        }
        if (!StringUtil.isEmpty(record.getType()) && !record.getType().equals(template.getType())) {
            if (!EnumMethods.contains(record.getType(), TemplateType.values())) {
                ExceptionUtil.throwFailException("模板类型不正确");
            }
            templateUpdate.setType(record.getType());
        }
        templateUpdate.setDescription(record.getDescription());
        if (!StringUtil.isEmpty(record.getStatus()) && !record.getStatus().equals(template.getStatus())) {
            if (!EnumMethods.contains(record.getStatus(), Status.values())) {
                ExceptionUtil.throwFailException("状态类型不正确");
            }
            templateUpdate.setStatus(record.getStatus());
        }

        String timeString = Time.localDateTimeNow();
        Long timeStamp = Time.timeStamp();

        templateUpdate.setUpdateTime(timeString);
        templateUpdate.setUpdateTimeStamp(timeStamp);

        int count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(templateId, templateUpdate, MySqlDynamicEngine.update(TemplateModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("更新模板失败");
        }
        //更新路由视图模板数据
        if (routeViewTemplateUpdate != null) {
            routeViewTemplateUpdate.setUpdateTime(timeString);
            routeViewTemplateUpdate.setUpdateTimeStamp(timeStamp);
            this.routeViewTemplateService.putListRouteViewTemplateByTemplateId(moduleId, templateId, routeViewTemplateUpdate);
        }
        //更新角色路由视图模板数据
        if (roleRouteViewTemplateUpdate != null) {
            roleRouteViewTemplateUpdate.setUpdateTime(timeString);
            roleRouteViewTemplateUpdate.setUpdateTimeStamp(timeStamp);
            this.roleRouteViewTemplateService.putListRoleRouteViewTemplateByTemplateId(moduleId, templateId, roleRouteViewTemplateUpdate);
        }

    }

    @Override
    public void putListTemplateByModuleIdAndSubModuleId(String moduleId, String subModuleId, Template templateUpdate) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(subModuleId)) {
            ExceptionUtil.throwFailException("子模块ID不能为空");
        }
        this.jdbcEngine.updateJavaBeanSelective(templateUpdate, MySqlDynamicEngine.update(TemplateModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId).subModuleId().equalTo(subModuleId))));
    }

    @Override
    public void putSwitchTemplateIndexByTemplateId(String moduleId, String sourceTemplateId, String targetTemplateId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        Template sourceTemplate = this.jdbcEngine.queryOne(Template.class, MySqlDynamicEngine.query(TemplateModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId).id().equalTo(sourceTemplateId))));
        if (sourceTemplate == null) {
            ExceptionUtil.throwFailException(40404, "模板不存在");
        }
        Template targetTemplate = this.jdbcEngine.queryOne(Template.class, MySqlDynamicEngine.query(TemplateModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId).id().equalTo(targetTemplateId))));
        if (targetTemplate == null) {
            ExceptionUtil.throwFailException(40404, "模板不存在");
        }
        Template templateUpdate = new Template();
        templateUpdate.setIndex(targetTemplate.getIndex());
        templateUpdate.setUpdateTime(Time.localDateTimeNow());
        templateUpdate.setUpdateTimeStamp(Time.timeStamp());

        int count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(sourceTemplateId, templateUpdate, MySqlDynamicEngine.update(TemplateModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("操作失败");
        }
        templateUpdate.setIndex(sourceTemplate.getIndex());
        count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(targetTemplateId, templateUpdate, MySqlDynamicEngine.update(TemplateModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("操作失败");
        }
    }

    @Override
    public void deleteTemplateByTemplateId(String moduleId, String templateId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(templateId)) {
            ExceptionUtil.throwFailException("模板ID不能为空");
        }
        //删除模板数据
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(TemplateModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId).id().equalTo(templateId))));
        if (count != 1) {
            ExceptionUtil.throwFailException("删除模板失败");
        }
        //删除路由视图模板数据(会同时删除角色路由视图模板数据)
        this.routeViewTemplateService.deleteListRouteViewTemplateByTemplateId(moduleId, templateId);
    }

    @Override
    public void deleteListTemplateBySubModuleId(String moduleId, String subModuleId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(subModuleId)) {
            ExceptionUtil.throwFailException("子模块ID不能为空");
        }
        //查询子模块下拥有的模板ID
        List<String> templateIds = this.jdbcEngine.queryColumnList(TemplateModel.id_alias, String.class, MySqlDynamicEngine.query(TemplateModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId).subModuleId().equalTo(subModuleId))));
        if (templateIds.size() == 0) {
            return;
        }
        //删除模板数据
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(TemplateModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId).subModuleId().equalTo(subModuleId))));
        if (count != templateIds.size()) {
            ExceptionUtil.throwFailException("删除模板失败");
        }
        //删除路由视图模板数据(会同时删除角色路由视图模板数据)
        this.routeViewTemplateService.deleteListRouteViewTemplateByTemplateIds(moduleId, templateIds);
    }

    @Override
    public void deleteListTemplateBySubModuleIds(String moduleId, List<String> subModuleIds) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(subModuleIds)) {
            ExceptionUtil.throwFailException("子模块ID不能为空");
        }
        //查询子模块下拥有的模板ID
        List<String> templateIds = this.jdbcEngine.queryColumnList(TemplateModel.id_alias, String.class, MySqlDynamicEngine.query(TemplateModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId).subModuleId().inS(subModuleIds))));
        if (templateIds.size() == 0) {
            return;
        }
        //删除模板数据
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(TemplateModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId).subModuleId().inS(subModuleIds))));
        if (count != templateIds.size()) {
            ExceptionUtil.throwFailException("删除模板失败");
        }
        //删除路由视图模板数据(会同时删除角色路由视图模板数据)
        this.routeViewTemplateService.deleteListRouteViewTemplateByTemplateIds(moduleId, templateIds);
    }

    @Override
    public void deleteListTemplateByTemplateIds(String moduleId, List<String> templateIds) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(templateIds)) {
            ExceptionUtil.throwFailException("模板ID不能为空");
        }
        //删除模板数据
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(TemplateModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId).id().inS(templateIds))));
        if (count == 0) {
            ExceptionUtil.throwFailException("删除模板失败");
        }
        //删除路由视图模板数据(会同时删除角色路由视图模板数据)
        this.routeViewTemplateService.deleteListRouteViewTemplateByTemplateIds(moduleId, templateIds);
    }
}
