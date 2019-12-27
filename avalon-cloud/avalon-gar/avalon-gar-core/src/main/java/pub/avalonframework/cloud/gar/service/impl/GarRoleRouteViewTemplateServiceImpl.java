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
import pub.avalonframework.cloud.gar.entity.AutRole;
import pub.avalonframework.cloud.gar.entity.AutRoleRouteViewTemplate;
import pub.avalonframework.cloud.gar.entity.RouteView;
import pub.avalonframework.cloud.gar.entity.Template;
import pub.avalonframework.cloud.gar.model.AutRoleModel;
import pub.avalonframework.cloud.gar.model.AutRoleRouteViewTemplateModel;
import pub.avalonframework.cloud.gar.model.RouteViewModel;
import pub.avalonframework.cloud.gar.model.TemplateModel;
import pub.avalonframework.cloud.gar.service.GarRoleRouteViewTemplateService;
import pub.avalonframework.cloud.gar.utils.TableUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 白超
 * @date 2018/12/7
 */
@Service
public class GarRoleRouteViewTemplateServiceImpl implements GarRoleRouteViewTemplateService {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    public boolean getValidateRoleRouteViewTemplateExistByRoleIdAndRouteViewIdAndTemplateId(String moduleId, String roleId, String routeViewId, String templateId) throws Exception {
        String roleRouteViewTemplateTableName = TableUtils.getRoleRouteViewTemplateTableName(moduleId);
        return this.jdbcEngine.queryCount(MySqlDynamicEngine.query(roleRouteViewTemplateTableName, AutRoleRouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .roleId().equalTo(roleId)
                                .routeViewId().equalTo(routeViewId)
                                .templateId().equalTo(templateId)))) > 0;
    }

    @Override
    public void postRoleRouteViewTemplateByRoleIdAndRouteViewIdAndTemplateId(String moduleId, String roleId, String routeViewId, String templateId) throws Exception {
        String roleTableName = TableUtils.getRoleTableName(moduleId);
        String roleRouteViewTemplateTableName = TableUtils.getRoleRouteViewTemplateTableName(moduleId);
        if (StringUtil.isEmpty(roleId)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        if (StringUtil.isEmpty(routeViewId)) {
            ExceptionUtil.throwFailException("路由视图ID不能为空");
        }
        if (StringUtil.isEmpty(templateId)) {
            ExceptionUtil.throwFailException("模板ID不能为空");
        }
        if (this.getValidateRoleRouteViewTemplateExistByRoleIdAndRouteViewIdAndTemplateId(moduleId, roleId, routeViewId, templateId)) {
            ExceptionUtil.throwFailException("该角色已经拥有该路由视图的该模板");
        }
        AutRole role = this.jdbcEngine.queryByPrimaryKey(roleId, AutRole.class, MySqlDynamicEngine.query(roleTableName, AutRoleModel.class));
        if (role == null) {
            ExceptionUtil.throwFailException("角色不存在");
        }
        if (!EnumMethods.equalsTo(role.getStatus(), Status.NORMAL)) {
            ExceptionUtil.throwFailException("角色不可用");
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
        AutRoleRouteViewTemplate roleRouteViewTemplateInsert = new AutRoleRouteViewTemplate();

        roleRouteViewTemplateInsert.setModuleId(moduleId);

        roleRouteViewTemplateInsert.setRoleId(role.getId());
        roleRouteViewTemplateInsert.setRoleValue(role.getValue());
        roleRouteViewTemplateInsert.setRoleName(role.getName());
        roleRouteViewTemplateInsert.setRoleType(role.getType());

        roleRouteViewTemplateInsert.setRouteId(routeView.getRouteId());
        roleRouteViewTemplateInsert.setRouteName(routeView.getRouteName());
        roleRouteViewTemplateInsert.setRouteValue(routeView.getRouteValue());
        roleRouteViewTemplateInsert.setRoutePath(routeView.getRoutePath());

        roleRouteViewTemplateInsert.setRouteViewId(routeView.getId());
        roleRouteViewTemplateInsert.setRouteViewName(routeView.getName());
        roleRouteViewTemplateInsert.setRouteViewValue(routeView.getValue());

        roleRouteViewTemplateInsert.setTemplateId(template.getId());
        roleRouteViewTemplateInsert.setTemplateName(template.getName());
        roleRouteViewTemplateInsert.setTemplateValue(template.getValue());

        roleRouteViewTemplateInsert.setStatus(Status.NORMAL.name());
        roleRouteViewTemplateInsert.setCreateTime(timeString);
        roleRouteViewTemplateInsert.setCreateTimeStamp(timeStamp);

        int count = this.jdbcEngine.insertJavaBeanSelective(roleRouteViewTemplateInsert, MySqlDynamicEngine.insert(roleRouteViewTemplateTableName, AutRoleRouteViewTemplateModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("新增路由视图模板失败");
        }
    }

    @Override
    public void postRoleRouteViewTemplateByRoleIdsAndRouteViewIdAndTemplateId(String moduleId, List<String> roleIds, String routeViewId, String templateId) throws Exception {
        String roleTableName = TableUtils.getRoleTableName(moduleId);
        String roleRouteViewTemplateTableName = TableUtils.getRoleRouteViewTemplateTableName(moduleId);
        if (StringUtil.isEmpty(roleIds)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        if (StringUtil.isEmpty(routeViewId)) {
            ExceptionUtil.throwFailException("路由视图ID不能为空");
        }
        if (StringUtil.isEmpty(templateId)) {
            ExceptionUtil.throwFailException("模板ID不能为空");
        }
        Map<String, AutRole> roleMap = this.jdbcEngine.queryInMap(AutRoleModel.id_alias, AutRole.class, MySqlDynamicEngine.query(roleTableName, AutRoleModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.id().inS(roleIds))));
        if (roleMap.size() != roleIds.size()) {
            ExceptionUtil.throwFailException("角色数量不符");
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

        List<AutRoleRouteViewTemplate> roleRouteViewTemplatesInsert = new ArrayList<>();
        AutRoleRouteViewTemplate roleRouteViewTemplateInsert;
        AutRole role;
        for (String roleId : roleIds) {
            roleRouteViewTemplateInsert = new AutRoleRouteViewTemplate();
            role = roleMap.get(roleId);
            if (role == null) {
                ExceptionUtil.throwFailException("角色不存在");
            }
            if (!EnumMethods.equalsTo(role.getStatus(), Status.NORMAL)) {
                ExceptionUtil.throwFailException("角色不可用");
            }
            roleRouteViewTemplateInsert.setModuleId(moduleId);

            roleRouteViewTemplateInsert.setRoleId(role.getId());
            roleRouteViewTemplateInsert.setRoleValue(role.getValue());
            roleRouteViewTemplateInsert.setRoleName(role.getName());
            roleRouteViewTemplateInsert.setRoleType(role.getType());

            roleRouteViewTemplateInsert.setRouteId(routeView.getRouteId());
            roleRouteViewTemplateInsert.setRouteName(routeView.getRouteName());
            roleRouteViewTemplateInsert.setRouteValue(routeView.getRouteValue());
            roleRouteViewTemplateInsert.setRoutePath(routeView.getRoutePath());

            roleRouteViewTemplateInsert.setRouteViewId(routeView.getId());
            roleRouteViewTemplateInsert.setRouteViewName(routeView.getName());
            roleRouteViewTemplateInsert.setRouteViewValue(routeView.getValue());

            roleRouteViewTemplateInsert.setTemplateId(template.getId());
            roleRouteViewTemplateInsert.setTemplateName(template.getName());
            roleRouteViewTemplateInsert.setTemplateValue(template.getValue());

            roleRouteViewTemplateInsert.setStatus(Status.NORMAL.name());
            roleRouteViewTemplateInsert.setCreateTime(timeString);
            roleRouteViewTemplateInsert.setCreateTimeStamp(timeStamp);

            roleRouteViewTemplatesInsert.add(roleRouteViewTemplateInsert);
        }


        int count = this.jdbcEngine.batchInsertJavaBeans(roleRouteViewTemplatesInsert, MySqlDynamicEngine.insert(roleRouteViewTemplateTableName, AutRoleRouteViewTemplateModel.class));
        if (count == 0) {
            ExceptionUtil.throwFailException("新增路由视图模板失败");
        }
    }

    @Override
    public void putListRoleRouteViewTemplateByRoleId(String moduleId, String roleId, AutRoleRouteViewTemplate roleRouteViewTemplateUpdate) throws Exception {
        String roleRouteViewTemplateTableName = TableUtils.getRoleRouteViewTemplateTableName(moduleId);
        if (StringUtil.isEmpty(roleId)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        this.jdbcEngine.updateJavaBeanSelective(roleRouteViewTemplateUpdate, MySqlDynamicEngine.update(roleRouteViewTemplateTableName, AutRoleRouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .roleId().equalTo(roleId))));
    }

    @Override
    public void putListRoleRouteViewTemplateByRouteId(String moduleId, String routeId, AutRoleRouteViewTemplate roleRouteViewTemplateUpdate) throws Exception {
        String roleRouteViewTemplateTableName = TableUtils.getRoleRouteViewTemplateTableName(moduleId);
        if (StringUtil.isEmpty(routeId)) {
            ExceptionUtil.throwFailException("路由ID不能为空");
        }
        this.jdbcEngine.updateJavaBeanSelective(roleRouteViewTemplateUpdate, MySqlDynamicEngine.update(roleRouteViewTemplateTableName, AutRoleRouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .routeId().equalTo(routeId))));
    }

    @Override
    public void putListRoleRouteViewTemplateByRouteViewId(String moduleId, String routeViewId, AutRoleRouteViewTemplate roleRouteViewTemplateUpdate) throws Exception {
        String roleRouteViewTemplateTableName = TableUtils.getRoleRouteViewTemplateTableName(moduleId);
        if (StringUtil.isEmpty(routeViewId)) {
            ExceptionUtil.throwFailException("路由视图ID不能为空");
        }
        this.jdbcEngine.updateJavaBeanSelective(roleRouteViewTemplateUpdate, MySqlDynamicEngine.update(roleRouteViewTemplateTableName, AutRoleRouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .routeViewId().equalTo(routeViewId))));
    }

    @Override
    public void putListRoleRouteViewTemplateByTemplateId(String moduleId, String templateId, AutRoleRouteViewTemplate roleRouteViewTemplateUpdate) throws Exception {
        String roleRouteViewTemplateTableName = TableUtils.getRoleRouteViewTemplateTableName(moduleId);
        if (StringUtil.isEmpty(templateId)) {
            ExceptionUtil.throwFailException("模板ID不能为空");
        }
        this.jdbcEngine.updateJavaBeanSelective(roleRouteViewTemplateUpdate, MySqlDynamicEngine.update(roleRouteViewTemplateTableName, AutRoleRouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .templateId().equalTo(templateId))));
    }

    @Override
    public void deleteListRoleRouteViewTemplateByRoleId(String moduleId, String roleId) throws Exception {
        String roleRouteViewTemplateTableName = TableUtils.getRoleRouteViewTemplateTableName(moduleId);
        if (StringUtil.isEmpty(roleId)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(roleRouteViewTemplateTableName, AutRoleRouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .roleId().equalTo(roleId))));
    }

    @Override
    public void deleteListRoleRouteViewTemplateByRoleIds(String moduleId, List<String> roleIds) throws Exception {
        String roleRouteViewTemplateTableName = TableUtils.getRoleRouteViewTemplateTableName(moduleId);
        if (StringUtil.isEmpty(roleIds)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(roleRouteViewTemplateTableName, AutRoleRouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .roleId().inS(roleIds))));
    }

    @Override
    public void deleteListRoleRouteViewTemplateByRouteViewId(String moduleId, String routeViewId) throws Exception {
        String roleRouteViewTemplateTableName = TableUtils.getRoleRouteViewTemplateTableName(moduleId);
        if (StringUtil.isEmpty(routeViewId)) {
            ExceptionUtil.throwFailException("路由视图ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(roleRouteViewTemplateTableName, AutRoleRouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .routeViewId().equalTo(routeViewId))));
    }

    @Override
    public void deleteListRoleRouteViewTemplateByRouteViewIds(String moduleId, List<String> routeViewIds) throws Exception {
        String roleRouteViewTemplateTableName = TableUtils.getRoleRouteViewTemplateTableName(moduleId);
        if (StringUtil.isEmpty(routeViewIds)) {
            ExceptionUtil.throwFailException("路由视图ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(roleRouteViewTemplateTableName, AutRoleRouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .routeViewId().inS(routeViewIds))));
    }

    @Override
    public void deleteListRoleRouteViewTemplateByTemplateId(String moduleId, String templateId) throws Exception {
        String roleRouteViewTemplateTableName = TableUtils.getRoleRouteViewTemplateTableName(moduleId);
        if (StringUtil.isEmpty(templateId)) {
            ExceptionUtil.throwFailException("模板ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(roleRouteViewTemplateTableName, AutRoleRouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .templateId().equalTo(templateId))));
    }

    @Override
    public void deleteListRoleRouteViewTemplateByTemplateIds(String moduleId, List<String> templateIds) throws Exception {
        String roleRouteViewTemplateTableName = TableUtils.getRoleRouteViewTemplateTableName(moduleId);
        if (StringUtil.isEmpty(templateIds)) {
            ExceptionUtil.throwFailException("模板ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(roleRouteViewTemplateTableName, AutRoleRouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .templateId().inS(templateIds))));
    }

    @Override
    public void deleteListRoleRouteViewTemplateByRouteViewIdAndTemplateId(String moduleId, String routeViewId, String templateId) throws Exception {
        String roleRouteViewTemplateTableName = TableUtils.getRoleRouteViewTemplateTableName(moduleId);
        if (StringUtil.isEmpty(routeViewId)) {
            ExceptionUtil.throwFailException("路由视图ID不能为空");
        }
        if (StringUtil.isEmpty(templateId)) {
            ExceptionUtil.throwFailException("模板ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(roleRouteViewTemplateTableName, AutRoleRouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .routeViewId().equalTo(routeViewId)
                                .templateId().equalTo(templateId))));
    }

    @Override
    public void deleteRoleRouteViewTemplateByRoleRouteViewTemplateId(String moduleId, String roleRouteViewTemplateId) throws Exception {
        String roleRouteViewTemplateTableName = TableUtils.getRoleRouteViewTemplateTableName(moduleId);
        if (StringUtil.isEmpty(roleRouteViewTemplateId)) {
            ExceptionUtil.throwFailException("角色路由视图模板ID不能为空");
        }
        int count = this.jdbcEngine.deleteByPrimaryKey(roleRouteViewTemplateId, MySqlDynamicEngine.delete(roleRouteViewTemplateTableName, AutRoleRouteViewTemplateModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("删除角色路由视图模板失败");
        }
    }

    @Override
    public void deleteListRoleRouteViewTemplateByRoleRouteViewTemplateIds(String moduleId, List<String> roleRouteViewTemplateIds) throws Exception {
        String roleRouteViewTemplateTableName = TableUtils.getRoleRouteViewTemplateTableName(moduleId);
        if (StringUtil.isEmpty(roleRouteViewTemplateIds)) {
            ExceptionUtil.throwFailException("角色路由视图模板ID不能为空");
        }
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(roleRouteViewTemplateTableName, AutRoleRouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId).id().inS(roleRouteViewTemplateIds))));
        if (count != roleRouteViewTemplateIds.size()) {
            ExceptionUtil.throwFailException("删除角色路由视图模板失败");
        }
    }

    @Override
    public void deleteRoleRouteViewTemplateByRoleIdAndRouteViewIdAndTemplateId(String moduleId, String roleId, String routeViewId, String templateId) throws Exception {
        String roleRouteViewTemplateTableName = TableUtils.getRoleRouteViewTemplateTableName(moduleId);
        if (StringUtil.isEmpty(roleId)) {
            ExceptionUtil.throwFailException("角色ID不能为空");
        }
        if (StringUtil.isEmpty(routeViewId)) {
            ExceptionUtil.throwFailException("路由视图ID不能为空");
        }
        if (StringUtil.isEmpty(templateId)) {
            ExceptionUtil.throwFailException("模板ID不能为空");
        }
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(roleRouteViewTemplateTableName, AutRoleRouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .roleId().equalTo(roleId)
                                .templateId().equalTo(templateId)
                                .routeViewId().equalTo(routeViewId))));
        if (count != 1) {
            ExceptionUtil.throwFailException("删除路由视图模板失败");
        }
    }

}
