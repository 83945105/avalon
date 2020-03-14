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
import pub.avalonframework.cloud.gar.entity.RouteView;
import pub.avalonframework.cloud.gar.entity.RouteViewTemplate;
import pub.avalonframework.cloud.gar.entity.Template;
import pub.avalonframework.cloud.gar.model.RouteViewModel;
import pub.avalonframework.cloud.gar.model.RouteViewTemplateModel;
import pub.avalonframework.cloud.gar.model.TemplateModel;
import pub.avalonframework.cloud.gar.service.GarRoleRouteViewTemplateService;
import pub.avalonframework.cloud.gar.service.GarRouteViewTemplateService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 白超
 */
@Service
public class GarRouteViewTemplateServiceImpl implements GarRouteViewTemplateService {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired
    private GarRoleRouteViewTemplateService roleRouteViewTemplateService;

    public boolean getValidateRouteViewTemplateExistByRouteViewIdAndTemplateId(String moduleId, String routeViewId, String templateId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        return this.jdbcEngine.queryCount(MySqlDynamicEngine.query(RouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .routeViewId().equalTo(routeViewId)
                                .templateId().equalTo(templateId)))) > 0;
    }

    @Override
    public void postRouteViewTemplateByRouteViewIdAndTemplateId(String moduleId, String routeViewId, String templateId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(routeViewId)) {
            ExceptionUtil.throwFailException("路由视图ID不能为空");
        }
        if (StringUtil.isEmpty(templateId)) {
            ExceptionUtil.throwFailException("模板ID不能为空");
        }
        if (this.getValidateRouteViewTemplateExistByRouteViewIdAndTemplateId(moduleId, routeViewId, templateId)) {
            ExceptionUtil.throwFailException("该路由视图已经拥有该模板");
        }
        RouteView routeView = this.jdbcEngine.queryByPrimaryKey(routeViewId, RouteView.class, MySqlDynamicEngine.query(RouteViewModel.class));
        if (routeView == null) {
            ExceptionUtil.throwFailException("路由视图不存在");
        }
        if (!EnumMethods.equalsTo(routeView.getStatus(), Status.NORMAL)) {
            ExceptionUtil.throwFailException("路由视图不可用");
        }
        Template template = this.jdbcEngine.queryByPrimaryKey(templateId, Template.class, MySqlDynamicEngine.query(TemplateModel.class));
        if (template == null) {
            ExceptionUtil.throwFailException("模板不存在");
        }
        if (!EnumMethods.equalsTo(template.getStatus(), Status.NORMAL)) {
            ExceptionUtil.throwFailException("模板不可用");
        }

        String timeString = Time.localDateTimeNow();
        long timeStamp = Time.timeStamp();
        RouteViewTemplate routeViewTemplateInsert = new RouteViewTemplate();

        routeViewTemplateInsert.setModuleId(moduleId);

        routeViewTemplateInsert.setRouteId(routeView.getRouteId());
        routeViewTemplateInsert.setRouteName(routeView.getRouteName());
        routeViewTemplateInsert.setRouteValue(routeView.getRouteValue());
        routeViewTemplateInsert.setRoutePath(routeView.getRoutePath());

        routeViewTemplateInsert.setRouteViewId(routeView.getId());
        routeViewTemplateInsert.setRouteViewName(routeView.getName());
        routeViewTemplateInsert.setRouteViewValue(routeView.getValue());

        routeViewTemplateInsert.setTemplateId(template.getId());
        routeViewTemplateInsert.setTemplateName(template.getName());
        routeViewTemplateInsert.setTemplateValue(template.getValue());

        routeViewTemplateInsert.setStatus(Status.NORMAL.name());
        routeViewTemplateInsert.setCreateTime(timeString);
        routeViewTemplateInsert.setCreateTimeStamp(timeStamp);

        int count = this.jdbcEngine.insertJavaBeanSelective(routeViewTemplateInsert, MySqlDynamicEngine.insert(RouteViewTemplateModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("新增路由视图模板失败");
        }
    }

    @Override
    public void postListRouteViewTemplateByRouteViewIdsAndTemplateIds(String moduleId, Set<String> routeViewIds, Set<String> templateIds) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(routeViewIds)) {
            ExceptionUtil.throwFailException("路由视图ID不能为空");
        }
        if (StringUtil.isEmpty(templateIds)) {
            ExceptionUtil.throwFailException("模板ID不能为空");
        }

        Map<Long, String> mapByRouteViewIds = this.jdbcEngine.queryPairColumnInMap(RouteViewTemplateModel.primaryKeyAlias, RouteViewTemplateModel.routeViewId_alias, MySqlDynamicEngine.query(RouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId).routeViewId().inS(routeViewIds))));
        Map<Long, Long> mapByTemplateIds = this.jdbcEngine.queryPairColumnInMap(RouteViewTemplateModel.primaryKeyAlias, RouteViewTemplateModel.templateId_alias, MySqlDynamicEngine.query(RouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(moduleId).templateId().inS(templateIds))));
        //如果存在交集表示已经存在
        for (Map.Entry<Long, String> routeViewEntry : mapByRouteViewIds.entrySet()) {
            for (Map.Entry<Long, Long> templateEntry : mapByTemplateIds.entrySet()) {
                if (routeViewEntry.getKey().equals(templateEntry.getKey())) {
                    ExceptionUtil.throwFailException("路由视图ID: [" + routeViewEntry.getValue() + "]已经拥有模板ID: [" + templateEntry.getValue() + "]");
                }
            }
        }
        Map<String, RouteView> routeViewMap = this.jdbcEngine.queryInMap(RouteViewModel.primaryKeyAlias, RouteView.class, MySqlDynamicEngine.query(RouteViewModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId).id().inS(routeViewIds))));
        if (StringUtil.isEmpty(routeViewMap)) {
            ExceptionUtil.throwFailException("路由视图不存在");
        }
        if (routeViewIds.size() != routeViewMap.size()) {
            ExceptionUtil.throwFailException("路由视图数量不符");
        }
        Map<String, Template> templateMap = this.jdbcEngine.queryInMap(TemplateModel.primaryKeyAlias, Template.class, MySqlDynamicEngine.query(TemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId).id().inS(templateIds))));
        if (StringUtil.isEmpty(templateMap)) {
            ExceptionUtil.throwFailException("模板不存在");
        }
        if (templateIds.size() != templateMap.size()) {
            ExceptionUtil.throwFailException("模板数量不符");
        }
        String timeString = Time.localDateTimeNow();
        long timeStamp = Time.timeStamp();

        List<RouteViewTemplate> routeViewTemplatesInsert = new ArrayList<>();
        RouteViewTemplate routeViewTemplateInsert;
        RouteView routeView;
        Template template;
        for (Map.Entry<String, RouteView> routeViewEntry : routeViewMap.entrySet()) {
            routeView = routeViewEntry.getValue();
            if (!EnumMethods.equalsTo(routeView.getStatus(), Status.NORMAL)) {
                ExceptionUtil.throwFailException("路由视图不可用");
            }
            for (Map.Entry<String, Template> resEntry : templateMap.entrySet()) {
                template = resEntry.getValue();
                if (!EnumMethods.equalsTo(template.getStatus(), Status.NORMAL)) {
                    ExceptionUtil.throwFailException("模板不可用");
                }

                routeViewTemplateInsert = new RouteViewTemplate();

                routeViewTemplateInsert.setModuleId(moduleId);

                routeViewTemplateInsert.setRouteId(routeView.getRouteId());
                routeViewTemplateInsert.setRouteName(routeView.getRouteName());
                routeViewTemplateInsert.setRouteValue(routeView.getRouteValue());
                routeViewTemplateInsert.setRoutePath(routeView.getRoutePath());

                routeViewTemplateInsert.setRouteViewId(routeView.getId());
                routeViewTemplateInsert.setRouteViewName(routeView.getName());
                routeViewTemplateInsert.setRouteViewValue(routeView.getValue());

                routeViewTemplateInsert.setTemplateId(template.getId());
                routeViewTemplateInsert.setTemplateName(template.getName());
                routeViewTemplateInsert.setTemplateValue(template.getValue());

                routeViewTemplateInsert.setStatus(Status.NORMAL.name());
                routeViewTemplateInsert.setCreateTime(timeString);
                routeViewTemplateInsert.setCreateTimeStamp(timeStamp);

                routeViewTemplatesInsert.add(routeViewTemplateInsert);
            }
        }
        int count = this.jdbcEngine.batchInsertJavaBeans(routeViewTemplatesInsert, MySqlDynamicEngine.insert(RouteViewTemplateModel.class));
        if (count != routeViewTemplatesInsert.size()) {
            ExceptionUtil.throwFailException("新增路由视图模板失败");
        }
    }

    @Override
    public void putListRouteViewTemplateByRouteId(String moduleId, String routeId, RouteViewTemplate routeViewTemplateUpdate) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(routeId)) {
            ExceptionUtil.throwFailException("路由ID不能为空");
        }
        this.jdbcEngine.updateJavaBeanSelective(routeViewTemplateUpdate, MySqlDynamicEngine.update(RouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .routeId().equalTo(routeId))));
    }

    @Override
    public void putListRouteViewTemplateByRouteViewId(String moduleId, String routeViewId, RouteViewTemplate routeViewTemplateUpdate) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(routeViewId)) {
            ExceptionUtil.throwFailException("路由视图ID不能为空");
        }
        this.jdbcEngine.updateJavaBeanSelective(routeViewTemplateUpdate, MySqlDynamicEngine.update(RouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .routeViewId().equalTo(routeViewId))));
    }

    @Override
    public void putListRouteViewTemplateByTemplateId(String moduleId, String templateId, RouteViewTemplate routeViewTemplateUpdate) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(templateId)) {
            ExceptionUtil.throwFailException("模板ID不能为空");
        }
        this.jdbcEngine.updateJavaBeanSelective(routeViewTemplateUpdate, MySqlDynamicEngine.update(RouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .templateId().equalTo(templateId))));
    }

    @Override
    public void deleteListRouteViewTemplateByRouteViewId(String moduleId, String routeViewId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(routeViewId)) {
            ExceptionUtil.throwFailException("路由视图ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(RouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .routeViewId().equalTo(routeViewId))));
        //删除角色路由视图模板数据
        this.roleRouteViewTemplateService.deleteListRoleRouteViewTemplateByRouteViewId(moduleId, routeViewId);
    }

    @Override
    public void deleteListRouteViewTemplateByRouteViewIds(String moduleId, List<String> routeViewIds) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(routeViewIds)) {
            ExceptionUtil.throwFailException("路由视图ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(RouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .routeViewId().inS(routeViewIds))));
        //删除角色路由视图模板数据
        this.roleRouteViewTemplateService.deleteListRoleRouteViewTemplateByRouteViewIds(moduleId, routeViewIds);
    }

    @Override
    public void deleteListRouteViewTemplateByTemplateId(String moduleId, String templateId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(templateId)) {
            ExceptionUtil.throwFailException("模板ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(RouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .templateId().equalTo(templateId))));
        //删除角色路由视图模板数据
        this.roleRouteViewTemplateService.deleteListRoleRouteViewTemplateByTemplateId(moduleId, templateId);
    }

    @Override
    public void deleteListRouteViewTemplateByTemplateIds(String moduleId, List<String> templateIds) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(templateIds)) {
            ExceptionUtil.throwFailException("模板ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(RouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .templateId().inS(templateIds))));
        //删除角色路由视图模板数据
        this.roleRouteViewTemplateService.deleteListRoleRouteViewTemplateByTemplateIds(moduleId, templateIds);
    }

    @Override
    public void deleteRouteViewTemplateByRouteViewTemplateId(String moduleId, String routeViewTemplateId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(routeViewTemplateId)) {
            ExceptionUtil.throwFailException("路由视图模板ID不能为空");
        }
        RouteViewTemplate routeViewTemplate = this.jdbcEngine.queryByPrimaryKey(routeViewTemplateId, RouteViewTemplate.class, MySqlDynamicEngine.query(RouteViewTemplateModel.class));
        if (routeViewTemplate == null) {
            ExceptionUtil.throwFailException(40404, "路由视图模板不存在");
        }
        int count = this.jdbcEngine.deleteByPrimaryKey(routeViewTemplateId, MySqlDynamicEngine.delete(RouteViewTemplateModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("删除路由视图模板失败");
        }
        //删除角色路由视图模板数据
        this.roleRouteViewTemplateService.deleteListRoleRouteViewTemplateByRouteViewIdAndTemplateId(moduleId, routeViewTemplate.getRouteViewId(), routeViewTemplate.getTemplateId());
    }

    @Override
    public void deleteListRouteViewTemplateByRouteViewTemplateIds(String moduleId, Set<String> routeViewTemplateIds) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(routeViewTemplateIds)) {
            ExceptionUtil.throwFailException("路由视图模板ID不能为空");
        }
        List<RouteViewTemplate> routeViewTemplateList = this.jdbcEngine.queryForList(RouteViewTemplate.class, MySqlDynamicEngine.query(RouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .id().inS(routeViewTemplateIds))));
        if (routeViewTemplateIds.size() != routeViewTemplateList.size()) {
            ExceptionUtil.throwFailException(40404, "路由视图模板不存在");
        }
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(RouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId).id().inS(routeViewTemplateIds))));
        if (count != routeViewTemplateIds.size()) {
            ExceptionUtil.throwFailException("删除路由视图模板失败");
        }
        for (RouteViewTemplate routeViewTemplate : routeViewTemplateList) {
            //删除角色路由视图模板数据
            this.roleRouteViewTemplateService.deleteListRoleRouteViewTemplateByRouteViewIdAndTemplateId(moduleId, routeViewTemplate.getRouteViewId(), routeViewTemplate.getTemplateId());
        }
    }

    @Override
    public void deleteRouteViewTemplateByRouteViewIdAndTemplateId(String moduleId, String routeViewId, String templateId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(routeViewId)) {
            ExceptionUtil.throwFailException("路由视图ID不能为空");
        }
        if (StringUtil.isEmpty(templateId)) {
            ExceptionUtil.throwFailException("模板ID不能为空");
        }
        RouteViewTemplate routeViewTemplate = this.jdbcEngine.queryOne(RouteViewTemplate.class, MySqlDynamicEngine.query(RouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .routeViewId().equalTo(routeViewId)
                                .templateId().equalTo(templateId))));
        if (routeViewTemplate == null) {
            ExceptionUtil.throwFailException(40404, "路由视图模板不存在");
        }
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(RouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .templateId().equalTo(templateId)
                                .routeViewId().equalTo(routeViewId))));
        if (count != 1) {
            ExceptionUtil.throwFailException("删除路由视图模板失败");
        }
        //删除角色路由视图模板数据
        this.roleRouteViewTemplateService.deleteListRoleRouteViewTemplateByRouteViewIdAndTemplateId(moduleId, routeViewTemplate.getRouteViewId(), routeViewTemplate.getTemplateId());
    }
}