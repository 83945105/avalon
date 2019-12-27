package pub.avalonframework.cloud.gar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.avalon.beans.EnumMethods;
import pub.avalon.holygrail.function.beans.DropType;
import pub.avalon.holygrail.response.beans.Status;
import pub.avalon.holygrail.response.utils.DataViewUtil;
import pub.avalon.holygrail.response.utils.ExceptionUtil;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalon.holygrail.utils.StringUtil;
import pub.avalon.sqlhelper.core.beans.ComparisonRule;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.spring.beans.PageResultForMap;
import pub.avalon.sqlhelper.spring.core.SpringJdbcEngine;
import pub.avalonframework.cloud.gar.api.GarRouteApi;
import pub.avalonframework.cloud.gar.beans.RouteDragParams;
import pub.avalonframework.cloud.gar.dc.*;
import pub.avalonframework.cloud.gar.entity.Route;
import pub.avalonframework.cloud.gar.entity.SubModule;
import pub.avalonframework.cloud.gar.model.*;
import pub.avalonframework.cloud.gar.service.GarRouteNodeDraggableService;
import pub.avalonframework.cloud.gar.service.GarRouteService;
import pub.avalonframework.cloud.gar.utils.TableUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 白超
 * @date 2018/12/6
 */
@RequestMapping(GarRouteApi.ROOT_PATH)
@RestController
public class GarRouteController implements GarRouteApi {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired
    private GarRouteService routeService;

    @Autowired
    private GarRouteNodeDraggableService routeNodeDraggableService;

    @Autowired
    private HttpServletRequest request;

    @Override
    @RequestMapping(value = "/get/validateValueCanUseBySubModuleId/{value}/{subModuleId}")
    public DataView getValidateValueCanUseBySubModuleId(@PathVariable String value, @PathVariable String subModuleId, String excludeValues, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        boolean canUse = this.routeService.getValidateValueCanUseBySubModuleId(moduleId, subModuleId, value, StringUtil.isEmpty(excludeValues) ? null : Arrays.asList(excludeValues.split(",")));
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("canUse", canUse));
    }

    @Override
    @RequestMapping(value = "/get/validatePathCanUseBySubModuleId/{subModuleId}")
    public DataView getValidatePathCanUseBySubModuleId(String path, @PathVariable String subModuleId, String excludePaths, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        boolean canUse = this.routeService.getValidatePathCanUseBySubModuleId(moduleId, subModuleId, path, StringUtil.isEmpty(excludePaths) ? null : Arrays.asList(excludePaths.split(",")));
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("canUse", canUse));
    }

    @Override
    @RequestMapping(value = "/get/validateAliasCanUseBySubModuleId/{alias}/{subModuleId}")
    public DataView getValidateAliasCanUseBySubModuleId(@PathVariable String alias, @PathVariable String subModuleId, String excludeAliases, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        boolean canUse = this.routeService.getValidateAliasCanUseBySubModuleId(moduleId, subModuleId, alias, StringUtil.isEmpty(excludeAliases) ? null : Arrays.asList(excludeAliases.split(",")));
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("canUse", canUse));
    }

    @Override
    @RequestMapping(value = "/get/routeByRouteId/{routeId}")
    public DataView getRouteByRouteId(@PathVariable String routeId, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        Map<String, Object> route = this.jdbcEngine.queryOne(MySqlDynamicEngine.query(RouteModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(finalModuleId).id().equalTo(routeId))));
        if (route == null) {
            ExceptionUtil.throwFailException(40404, "路由不存在");
        }
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("route", route));
    }

    @Override
    @RequestMapping(value = "/get/list/route")
    public DataView getListRoute(RouteGet record, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        List<Map<String, Object>> rows = this.jdbcEngine.queryForList(MySqlDynamicEngine.query(RouteModel.class)
                .where((condition, mainTable) -> condition
                        .and((cd, mt) -> cd.and(mt.name().like(record.getLikeText()))
                                .or(mt.value().like(record.getLikeText())))
                        .and(mainTable.moduleId().equalTo(finalModuleId)))
                .sort(table -> table.index().asc()));
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/get/pageList/route")
    public DataView getPageListRoute(RouteGet record, Integer currentPage, Integer pageSize, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        currentPage = (currentPage == null || currentPage < 1) ? 1 : currentPage;
        pageSize = (pageSize == null || pageSize < 1 || pageSize > 500) ? 1 : pageSize;
        PageResultForMap pageResultForMap = this.jdbcEngine.pageQueryList(currentPage, pageSize, MySqlDynamicEngine.query(RouteModel.class)
                .where((condition, mainTable) -> condition
                        .and((cd, mt) -> cd.and(mt.name().like(record.getLikeText()))
                                .or(mt.value().like(record.getLikeText())))
                        .and(mainTable.moduleId().equalTo(finalModuleId)))
                .sort(table -> table.index().asc()));
        return DataViewUtil.getModelViewSuccess(pageResultForMap.getResult(), pageResultForMap.getLimit());
    }

    @Override
    @RequestMapping(value = "/get/list/routeBySubModuleId/{subModuleId}")
    public DataView getListRouteBySubModuleId(@PathVariable String subModuleId, RouteGet record, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        List<RouteGet> rows = this.jdbcEngine.queryForList(RouteGet.class, MySqlDynamicEngine.query(RouteModel.class)
                .subQuery(RouteModel.class, "subRoute", (mainTable, query) -> query
                        .where((condition, subTable) -> condition
                                .and(subTable.moduleId().equalTo(moduleId).parentId().equalTo(mainTable.id()))).queryCount(), "childCount")
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and((cd, mt) -> cd.and(mt.name().like(record.getLikeText()))
                                .or(mt.value().like(record.getLikeText())))
                        .and(mainTable.moduleId().equalTo(finalModuleId)
                                .subModuleId().equalTo(subModuleId)))
                .sort(table -> table.index().asc()));

        Object[] routeIds = rows.stream().map(Route::getId).toArray();
        if (!StringUtil.isEmpty(routeIds)) {
            List<RouteViewGet> routeViewList = this.jdbcEngine.queryForList(RouteViewGet.class, MySqlDynamicEngine.query(RouteViewModel.class)
                    .where((condition, mainTable) -> condition
                            .and(mainTable.moduleId().equalTo(moduleId)
                                    .routeId().inValue(routeIds, ComparisonRule.NULL_SKIP)))
                    .sort(table -> table.index().asc()));
            for (RouteViewGet routeView : routeViewList) {
                for (RouteGet row : rows) {
                    if (row.getId().equals(routeView.getRouteId())) {
                        row.addRouteView(routeView);
                        break;
                    }
                }
            }
        }

        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/get/list/rootRouteBySubModuleId/{subModuleId}")
    public DataView getListRootRouteBySubModuleId(@PathVariable String subModuleId, RouteGet record, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        List<RouteGet> rows = this.jdbcEngine.queryForList(RouteGet.class, MySqlDynamicEngine.query(RouteModel.class)
                .subQuery(RouteModel.class, "subRoute", (mainTable, query) -> query
                        .where((condition, subTable) -> condition
                                .and(subTable.moduleId().equalTo(moduleId).parentId().equalTo(mainTable.id()))).queryCount(), "childCount")
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and((cd, mt) -> cd.and(mt.name().like(record.getLikeText()))
                                .or(mt.value().like(record.getLikeText())))
                        .and(mainTable.moduleId().equalTo(finalModuleId)
                                .parentId().equalTo("")
                                .subModuleId().equalTo(subModuleId)))
                .sort(table -> table.index().asc()));

        Object[] routeIds = rows.stream().map(Route::getId).toArray();
        if (!StringUtil.isEmpty(routeIds)) {
            List<RouteViewGet> routeViewList = this.jdbcEngine.queryForList(RouteViewGet.class, MySqlDynamicEngine.query(RouteViewModel.class)
                    .where((condition, mainTable) -> condition
                            .and(mainTable.moduleId().equalTo(moduleId)
                                    .routeId().inValue(routeIds, ComparisonRule.NULL_SKIP)))
                    .sort(table -> table.index().asc()));
            for (RouteViewGet routeView : routeViewList) {
                for (RouteGet row : rows) {
                    if (row.getId().equals(routeView.getRouteId())) {
                        row.addRouteView(routeView);
                        break;
                    }
                }
            }
        }

        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/get/list/routeByParentId/{parentRouteId}")
    public DataView getListRouteByParentRouteId(@PathVariable String parentRouteId, RouteGet record, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        List<RouteGet> rows = this.jdbcEngine.queryForList(RouteGet.class, MySqlDynamicEngine.query(RouteModel.class)
                .innerJoin(RouteModel.class, "parentRoute", (on, joinTable, mainTable) -> on
                        .and(joinTable.id().equalTo(mainTable.parentId())))
                .subQuery(RouteModel.class, "subRoute", (mainTable, query) -> query
                        .where((condition, subTable) -> condition
                                .and(subTable.moduleId().equalTo(moduleId).parentId().equalTo(mainTable.id()))).queryCount(), "childCount")
                .column(RouteModel.class, "parentRoute", table -> table.name("parentRouteName"))
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and((cd, mt) -> cd.and(mt.name().like(record.getLikeText()))
                                .or(mt.value().like(record.getLikeText())))
                        .and(mainTable.moduleId().equalTo(finalModuleId)
                                .parentId().equalTo(parentRouteId)))
                .sort(table -> table.index().asc()));

        Object[] routeIds = rows.stream().map(Route::getId).toArray();
        if (!StringUtil.isEmpty(routeIds)) {
            List<RouteViewGet> routeViewList = this.jdbcEngine.queryForList(RouteViewGet.class, MySqlDynamicEngine.query(RouteViewModel.class)
                    .where((condition, mainTable) -> condition
                            .and(mainTable.moduleId().equalTo(moduleId)
                                    .routeId().inValue(routeIds, ComparisonRule.NULL_SKIP)))
                    .sort(table -> table.index().asc()));
            for (RouteViewGet routeView : routeViewList) {
                for (RouteGet row : rows) {
                    if (row.getId().equals(routeView.getRouteId())) {
                        row.addRouteView(routeView);
                        break;
                    }
                }
            }
        }

        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/get/routeTreeBySubModuleValue/{subModuleValue}")
    public DataView getRouteTreeBySubModuleValue(@PathVariable String subModuleValue, String moduleId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(subModuleValue)) {
            ExceptionUtil.throwFailException("子模块唯一标识符不能为空");
        }

        SubModule subModule = this.jdbcEngine.queryOne(SubModule.class, MySqlDynamicEngine.query(SubModuleModel.class)
                .column(table -> table.id().status())
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .value().equalTo(subModuleValue))));

        if (subModule == null) {
            ExceptionUtil.throwFailException(40404, "子模块不存在");
        }

        if (!EnumMethods.equalsTo(subModule.getStatus(), Status.NORMAL)) {
            ExceptionUtil.throwFailException("您所访问的子模块不可用");
        }

        List<RouteGet> routeList = this.jdbcEngine.queryForList(RouteGet.class, MySqlDynamicEngine.query(RouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .subModuleId().equalTo(subModule.getId())))
                .sort(table -> table.index().asc()));

        if (StringUtil.isEmpty(routeList)) {
            return DataViewUtil.getModelViewSuccess(new ArrayList<>(0));
        }

        Set<String> routeIds = routeList.stream().map(Route::getId).collect(Collectors.toSet());

        //查询出子模块ID-value键值对
        Map<String, String> subModuleIdValueMap = this.jdbcEngine.queryPairColumnInMap(SubModuleModel.id_alias, SubModuleModel.value_alias, MySqlDynamicEngine.query(SubModuleModel.class));

        // 查询拥有的路由视图
        Map<String, List<RouteViewGet>> routeViewMap = this.jdbcEngine.queryListInMap(RouteViewModel.routeId_alias, RouteViewGet.class, MySqlDynamicEngine.query(RouteViewModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .subModuleId().equalTo(subModule.getId())
                                .routeId().inS(routeIds)))
                .sort(table -> table.index().asc()));

        // 查询关联的模板
        Map<String, List<RouteViewTemplateGet>> routeViewTemplateMap = this.jdbcEngine.queryListInMap(RouteViewTemplateModel.routeId_alias, RouteViewTemplateGet.class, MySqlDynamicEngine.query(RouteViewTemplateModel.class)
                .innerJoin(TemplateModel.class, (on, joinTable, mainTable) -> on
                        .and(joinTable.id().equalTo(mainTable.templateId())))
                .column(TemplateModel.class, table -> table.subModuleId("templateSubModuleId"))
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .routeId().inS(routeIds)))
                .sort(table -> table.createTimeStamp().asc()));

        // 查询模板授予角色
        String roleRouteViewTemplateTableName = TableUtils.getRoleRouteViewTemplateTableName(moduleId);
        Map<String, List<AutRoleRouteViewTemplateGet>> roleRouteViewTemplateMap = this.jdbcEngine.queryListInMap(AutRoleRouteViewTemplateModel.routeId_alias, AutRoleRouteViewTemplateGet.class, MySqlDynamicEngine.query(roleRouteViewTemplateTableName, AutRoleRouteViewTemplateModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .routeId().inS(routeIds))));

        List<RouteGet> rootRouteList = new ArrayList<>();
        List<RouteGet> subRouteList = new ArrayList<>();
        // 用于记录已经找到的路由
        Map<String, RouteGet> routeCache = new LinkedHashMap<>();

        List<RouteViewGet> routeViewList;
        List<RouteViewTemplateGet> routeViewTemplateList;
        List<AutRoleRouteViewTemplateGet> roleRouteViewTemplateList;
        TemplateGet templateGet;
        AutRoleGet roleGet;
        for (RouteGet routeGet : routeList) {
            routeCache.put(routeGet.getId(), routeGet);
            if ("".equals(routeGet.getParentId())) {
                // 加入到根路由
                rootRouteList.add(routeGet);
            } else {
                // 加入到子路由
                subRouteList.add(routeGet);
            }

            routeViewList = routeViewMap.get(routeGet.getId());
            if (StringUtil.isEmpty(routeViewList)) {
                continue;
            }
            routeViewTemplateList = routeViewTemplateMap.get(routeGet.getId());
            roleRouteViewTemplateList = roleRouteViewTemplateMap.get(routeGet.getId());
            for (RouteViewGet routeViewGet : routeViewList) {
                routeGet.addRouteView(routeViewGet);

                if (!StringUtil.isEmpty(routeViewTemplateList)) {
                    for (RouteViewTemplateGet routeViewTemplateGet : routeViewTemplateList) {
                        if (routeViewGet.getId().equals(routeViewTemplateGet.getRouteViewId())) {
                            templateGet = new TemplateGet();
                            templateGet.setId(routeViewTemplateGet.getTemplateId());
                            templateGet.setName(routeViewTemplateGet.getRouteName());
                            templateGet.setValue(routeViewTemplateGet.getTemplateValue());
                            templateGet.setSubModuleValue(subModuleIdValueMap.get(routeViewTemplateGet.getTemplateSubModuleId()));

                            if (!StringUtil.isEmpty(roleRouteViewTemplateList)) {
                                for (AutRoleRouteViewTemplateGet autRoleRouteViewTemplateGet : roleRouteViewTemplateList) {
                                    if (routeViewGet.getId().equals(autRoleRouteViewTemplateGet.getRouteViewId()) && routeViewTemplateGet.getTemplateId().equals(autRoleRouteViewTemplateGet.getTemplateId())) {
                                        roleGet = new AutRoleGet();
                                        roleGet.setId(autRoleRouteViewTemplateGet.getRoleId());
                                        roleGet.setName(autRoleRouteViewTemplateGet.getRoleName());
                                        roleGet.setValue(autRoleRouteViewTemplateGet.getRoleValue());
                                        roleGet.setType(autRoleRouteViewTemplateGet.getRoleType());

                                        templateGet.addRole(roleGet);
                                    }
                                }
                            }

                            routeViewGet.addTemplate(templateGet);
                        }
                    }
                }

            }
        }
        // 将子路由放到父级路由中
        for (Route route : subRouteList) {
            routeCache.get(route.getParentId()).addSubRoute(route);
        }
        return DataViewUtil.getModelViewSuccess(rootRouteList);
    }

    @Override
    @RequestMapping(value = "/post/route")
    public DataView postRoute(RoutePost record, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        RouteGet route = this.routeService.postRoute(moduleId, record);
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("route", route));
    }

    @Override
    @RequestMapping(value = "/put/routeByRouteId/{routeId}")
    public DataView putRouteByRouteId(@PathVariable String routeId, RoutePut record, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.routeService.putRouteByRouteId(moduleId, routeId, record);
        return DataViewUtil.getMessageViewSuccess("修改路由成功");
    }

    @Override
    @RequestMapping(value = "/put/switchRouteIndexByRouteId/{sourceRouteId}/{targetRouteId}")
    public DataView putSwitchRouteIndexByRouteId(@PathVariable String sourceRouteId, @PathVariable String targetRouteId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.routeService.putSwitchRouteIndexByRouteId(moduleId, sourceRouteId, targetRouteId);
        return DataViewUtil.getMessageViewSuccess("操作成功");
    }

    @Override
    @RequestMapping(value = "/delete/routeByRouteId/{routeId}")
    public DataView deleteRouteByRouteId(@PathVariable String routeId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.routeService.deleteRouteByRouteId(moduleId, routeId);
        return DataViewUtil.getMessageViewSuccess("删除路由成功");
    }

    @Override
    @RequestMapping(value = "/delete/list/routeByRouteIds/{routeIds}")
    public DataView deleteListRouteByRouteIds(@PathVariable String routeIds, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.routeService.deleteListRouteByRouteIds(moduleId, Arrays.asList(routeIds.split(",")));
        return DataViewUtil.getMessageViewSuccess("删除路由成功");
    }

    @Override
    @RequestMapping(value = "/put/dragRouteTreeNode/{dragRouteId}/{dropRouteId}/{dropType}")
    public DataView putDragRouteTreeNode(@PathVariable String dragRouteId, @PathVariable String dropRouteId, @PathVariable String dropType, RouteDragParams record, String moduleId) throws Exception {
        if (StringUtil.isEmpty(record.getDragSubModuleId())) {
            ExceptionUtil.throwFailException("拖拽节点所属子模块ID不能为空");
        }
        if (StringUtil.isEmpty(record.getDropSubModuleId())) {
            ExceptionUtil.throwFailException("放置节点所属子模块ID不能为空");
        }
        moduleId = TableUtils.getModuleId(moduleId, request);
        record.setModuleId(moduleId);
        DropType dt = null;
        switch (dropType) {
            case "before":
                dt = DropType.BEFORE;
                break;
            case "inner":
                dt = DropType.INNER;
                break;
            case "after":
                dt = DropType.AFTER;
                break;
            default:
                ExceptionUtil.throwFailException("放置类型不正确");
        }
        RouteTreeNode dragNode = this.jdbcEngine.queryByPrimaryKey(dragRouteId, RouteTreeNode.class, MySqlDynamicEngine.query(RouteModel.class));
        if (dragNode == null) {
            ExceptionUtil.throwFailException("拖拽路由不存在");
        }
        RouteTreeNode dropNode = this.jdbcEngine.queryByPrimaryKey(dropRouteId, RouteTreeNode.class, MySqlDynamicEngine.query(RouteModel.class));
        if (dropNode == null) {
            ExceptionUtil.throwFailException("放置路由不存在");
        }
        this.routeNodeDraggableService.draggableNode(dragNode, dropNode, dt, record);
        return DataViewUtil.getMessageViewSuccess("拖拽成功");
    }

}
