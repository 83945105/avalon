package pub.avalonframework.cloud.gar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.avalon.holygrail.response.utils.DataViewUtil;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalon.holygrail.utils.StringUtil;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.spring.beans.PageResultForBean;
import pub.avalon.sqlhelper.spring.core.SpringJdbcEngine;
import pub.avalonframework.cloud.gar.api.GarRouteViewTemplateApi;
import pub.avalonframework.cloud.gar.dc.AutRoleRouteViewTemplateGet;
import pub.avalonframework.cloud.gar.dc.RouteViewTemplateGet;
import pub.avalonframework.cloud.gar.entity.AutRole;
import pub.avalonframework.cloud.gar.entity.RouteViewTemplate;
import pub.avalonframework.cloud.gar.model.AutRoleRouteViewTemplateModel;
import pub.avalonframework.cloud.gar.model.RouteViewTemplateModel;
import pub.avalonframework.cloud.gar.model.TemplateModel;
import pub.avalonframework.cloud.gar.service.GarRouteViewTemplateService;
import pub.avalonframework.cloud.gar.utils.TableUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 白超
 */
@RestController
@RequestMapping(GarRouteViewTemplateApi.ROOT_PATH)
public class GarRouteViewTemplateController implements GarRouteViewTemplateApi {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired
    private GarRouteViewTemplateService routeViewTemplateService;

    @Autowired
    private HttpServletRequest request;

    @Override
    @RequestMapping(value = "/get/list/routeViewTemplateByRouteViewId/{routeViewId}")
    public DataView getListRouteViewTemplateByRouteViewId(@PathVariable String routeViewId, RouteViewTemplateGet record, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        List<RouteViewTemplateGet> rows = this.jdbcEngine.queryForList(RouteViewTemplateGet.class, MySqlDynamicEngine.query(RouteViewTemplateModel.class)
                .innerJoin(TemplateModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.id().equalTo(mainTable.templateId())))
                .column(TemplateModel.class, table -> table.type("templateType").description("templateDescription"))
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(finalModuleId).routeViewId().equalTo(routeViewId))));

        Set<String> templateIds = rows.stream().map(RouteViewTemplate::getTemplateId).collect(Collectors.toSet());
        if (!StringUtil.isEmpty(templateIds)) {
            String roleRouteViewTemplateTableName = TableUtils.getRoleRouteViewTemplateTableName(finalModuleId);
            List<AutRoleRouteViewTemplateGet> roleRouteViewTemplateList = this.jdbcEngine.queryForList(AutRoleRouteViewTemplateGet.class, MySqlDynamicEngine.query(roleRouteViewTemplateTableName, AutRoleRouteViewTemplateModel.class)
                    .where((condition, mainTable) -> condition
                            .and(mainTable.moduleId().equalTo(moduleId)
                                    .routeViewId().equalTo(routeViewId)
                                    .templateId().inS(templateIds)))
                    .sort(table -> table.createTimeStamp().asc()));
            AutRole role;
            for (AutRoleRouteViewTemplateGet roleRouteViewTemplate : roleRouteViewTemplateList) {
                for (RouteViewTemplateGet row : rows) {
                    if (row.getTemplateId().equals(roleRouteViewTemplate.getTemplateId())) {
                        role = new AutRole();
                        role.setId(roleRouteViewTemplate.getRoleId());
                        role.setName(roleRouteViewTemplate.getRoleName());
                        role.setValue(roleRouteViewTemplate.getRoleValue());
                        role.setType(roleRouteViewTemplate.getRoleType());
                        row.addRole(role);
                    }
                }
            }
        }
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/get/pageList/routeViewTemplateByRouteViewId/{routeViewId}")
    public DataView getPageListRouteViewTemplateByRouteViewId(@PathVariable String routeViewId, RouteViewTemplateGet record, Integer currentPage, Integer pageSize, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        currentPage = (currentPage == null || currentPage < 1) ? 1 : currentPage;
        pageSize = (pageSize == null || pageSize < 1 || pageSize > 500) ? 1 : pageSize;

        PageResultForBean<RouteViewTemplateGet> pageResultForBean = this.jdbcEngine.pageQueryList(RouteViewTemplateGet.class, currentPage, pageSize, MySqlDynamicEngine.query(RouteViewTemplateModel.class)
                .innerJoin(TemplateModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.id().equalTo(mainTable.templateId())))
                .column(TemplateModel.class, table -> table.type("templateType").description("templateDescription"))
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(finalModuleId).routeViewId().equalTo(routeViewId))));

        Set<String> templateIds = pageResultForBean.getResult().stream().map(RouteViewTemplate::getTemplateId).collect(Collectors.toSet());
        if (!StringUtil.isEmpty(templateIds)) {
            String roleRouteViewTemplateTableName = TableUtils.getRoleRouteViewTemplateTableName(finalModuleId);
            List<AutRoleRouteViewTemplateGet> roleRouteViewTemplateList = this.jdbcEngine.queryForList(AutRoleRouteViewTemplateGet.class, MySqlDynamicEngine.query(roleRouteViewTemplateTableName, AutRoleRouteViewTemplateModel.class)
                    .where((condition, mainTable) -> condition
                            .and(mainTable.moduleId().equalTo(moduleId)
                                    .routeViewId().equalTo(routeViewId)
                                    .templateId().inS(templateIds)))
                    .sort(table -> table.createTimeStamp().asc()));
            AutRole role;
            for (AutRoleRouteViewTemplateGet roleRouteViewTemplate : roleRouteViewTemplateList) {
                for (RouteViewTemplateGet row : pageResultForBean.getResult()) {
                    if (row.getTemplateId().equals(roleRouteViewTemplate.getTemplateId())) {
                        role = new AutRole();
                        role.setId(roleRouteViewTemplate.getRoleId());
                        role.setName(roleRouteViewTemplate.getRoleName());
                        role.setValue(roleRouteViewTemplate.getRoleValue());
                        role.setType(roleRouteViewTemplate.getRoleType());
                        row.addRole(role);
                    }
                }
            }
        }
        return DataViewUtil.getModelViewSuccess(pageResultForBean.getResult(), pageResultForBean.getLimit());
    }

    @Override
    @RequestMapping(value = "/get/list/routeViewTemplateByTemplateId/{templateId}")
    public DataView getListRouteViewTemplateByTemplateId(@PathVariable String templateId, RouteViewTemplateGet record, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        List<RouteViewTemplateGet> rows = this.jdbcEngine.queryForList(RouteViewTemplateGet.class, MySqlDynamicEngine.query(RouteViewTemplateModel.class)
                .innerJoin(TemplateModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.id().equalTo(mainTable.templateId())))
                .column(TemplateModel.class, table -> table.type("templateType").description("templateDescription"))
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(finalModuleId).templateId().equalTo(templateId))));

        Set<String> routeViewIds = rows.stream().map(RouteViewTemplate::getRouteViewId).collect(Collectors.toSet());
        if (!StringUtil.isEmpty(routeViewIds)) {
            String roleRouteViewTemplateTableName = TableUtils.getRoleRouteViewTemplateTableName(finalModuleId);
            List<AutRoleRouteViewTemplateGet> roleRouteViewTemplateList = this.jdbcEngine.queryForList(AutRoleRouteViewTemplateGet.class, MySqlDynamicEngine.query(roleRouteViewTemplateTableName, AutRoleRouteViewTemplateModel.class)
                    .where((condition, mainTable) -> condition
                            .and(mainTable.moduleId().equalTo(moduleId)
                                    .templateId().equalTo(templateId)
                                    .routeViewId().inS(routeViewIds)))
                    .sort(table -> table.createTimeStamp().asc()));
            AutRole role;
            for (AutRoleRouteViewTemplateGet roleRouteViewTemplate : roleRouteViewTemplateList) {
                for (RouteViewTemplateGet row : rows) {
                    if (row.getRouteViewId().equals(roleRouteViewTemplate.getRouteViewId())) {
                        role = new AutRole();
                        role.setId(roleRouteViewTemplate.getRoleId());
                        role.setName(roleRouteViewTemplate.getRoleName());
                        role.setValue(roleRouteViewTemplate.getRoleValue());
                        role.setType(roleRouteViewTemplate.getRoleType());
                        row.addRole(role);
                    }
                }
            }
        }
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/get/pageList/routeViewTemplateByTemplateId/{templateId}")
    public DataView getPageListRouteViewTemplateByTemplateId(@PathVariable String templateId, RouteViewTemplateGet record, Integer currentPage, Integer pageSize, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        currentPage = (currentPage == null || currentPage < 1) ? 1 : currentPage;
        pageSize = (pageSize == null || pageSize < 1 || pageSize > 500) ? 1 : pageSize;

        PageResultForBean<RouteViewTemplateGet> pageResultForBean = this.jdbcEngine.pageQueryList(RouteViewTemplateGet.class, currentPage, pageSize, MySqlDynamicEngine.query(RouteViewTemplateModel.class)
                .innerJoin(TemplateModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.id().equalTo(mainTable.templateId())))
                .column(TemplateModel.class, table -> table.type("templateType").description("templateDescription"))
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(finalModuleId).templateId().equalTo(templateId))));

        Set<String> routeViewIds = pageResultForBean.getResult().stream().map(RouteViewTemplate::getRouteViewId).collect(Collectors.toSet());
        if (!StringUtil.isEmpty(routeViewIds)) {
            String roleRouteViewTemplateTableName = TableUtils.getRoleRouteViewTemplateTableName(finalModuleId);
            List<AutRoleRouteViewTemplateGet> roleRouteViewTemplateList = this.jdbcEngine.queryForList(AutRoleRouteViewTemplateGet.class, MySqlDynamicEngine.query(roleRouteViewTemplateTableName, AutRoleRouteViewTemplateModel.class)
                    .where((condition, mainTable) -> condition
                            .and(mainTable.moduleId().equalTo(moduleId)
                                    .templateId().equalTo(templateId)
                                    .routeViewId().inS(routeViewIds)))
                    .sort(table -> table.createTimeStamp().asc()));
            AutRole role;
            for (AutRoleRouteViewTemplateGet roleRouteViewTemplate : roleRouteViewTemplateList) {
                for (RouteViewTemplateGet row : pageResultForBean.getResult()) {
                    if (row.getRouteViewId().equals(roleRouteViewTemplate.getRouteViewId())) {
                        role = new AutRole();
                        role.setId(roleRouteViewTemplate.getRoleId());
                        role.setName(roleRouteViewTemplate.getRoleName());
                        role.setValue(roleRouteViewTemplate.getRoleValue());
                        role.setType(roleRouteViewTemplate.getRoleType());
                        row.addRole(role);
                    }
                }
            }
        }
        return DataViewUtil.getModelViewSuccess(pageResultForBean.getResult(), pageResultForBean.getLimit());
    }

    @Override
    @RequestMapping(value = "/post/routeViewTemplateByRouteViewIdAndTemplateId/{routeViewId}/{templateId}")
    public DataView postRouteViewTemplateByRouteViewIdAndTemplateId(@PathVariable String routeViewId, @PathVariable String templateId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.routeViewTemplateService.postRouteViewTemplateByRouteViewIdAndTemplateId(moduleId, routeViewId, templateId);
        return DataViewUtil.getMessageViewSuccess("新建路由视图模板成功");
    }

    @Override
    @RequestMapping(value = "/post/list/routeViewTemplateByRouteViewIdsAndTemplateIds/{routeViewIds}/{templateIds}")
    public DataView postListRouteViewTemplateByRouteViewIdsAndTemplateIds(@PathVariable String routeViewIds, @PathVariable String templateIds, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.routeViewTemplateService.postListRouteViewTemplateByRouteViewIdsAndTemplateIds(moduleId,
                StringUtil.isEmpty(routeViewIds) ? null : new HashSet<>(Arrays.asList(routeViewIds.split(","))),
                StringUtil.isEmpty(templateIds) ? null : new HashSet<>(Arrays.asList(templateIds.split(","))));
        return DataViewUtil.getMessageViewSuccess("新建路由视图模板成功");
    }

    @Override
    @RequestMapping(value = "/delete/routeViewTemplateByRouteViewTemplateId/{routeViewTemplateId}")
    public DataView deleteRouteViewTemplateByRouteViewTemplateId(@PathVariable String routeViewTemplateId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.routeViewTemplateService.deleteRouteViewTemplateByRouteViewTemplateId(moduleId, routeViewTemplateId);
        return DataViewUtil.getMessageViewSuccess("删除路由视图模板成功");
    }

    @Override
    @RequestMapping(value = "/delete/list/routeViewTemplateByRouteViewTemplateIds/{routeViewTemplateIds}")
    public DataView deleteListRouteViewTemplateByRouteViewTemplateIds(@PathVariable String routeViewTemplateIds, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.routeViewTemplateService.deleteListRouteViewTemplateByRouteViewTemplateIds(moduleId,
                StringUtil.isEmpty(routeViewTemplateIds) ? null : new HashSet<>(Arrays.asList(routeViewTemplateIds.split(","))));
        return DataViewUtil.getMessageViewSuccess("删除路由视图模板成功");
    }

    @Override
    @RequestMapping(value = "/delete/routeViewTemplateByRouteViewIdAndTemplateId/{routeViewId}/{templateId}")
    public DataView deleteRouteViewTemplateByRouteViewIdAndTemplateId(@PathVariable String routeViewId, @PathVariable String templateId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.routeViewTemplateService.deleteRouteViewTemplateByRouteViewIdAndTemplateId(moduleId, routeViewId, templateId);
        return DataViewUtil.getMessageViewSuccess("删除路由视图模板成功");
    }

}
