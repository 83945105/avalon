package pub.avalonframework.cloud.gar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.avalon.holygrail.response.utils.DataViewUtil;
import pub.avalon.holygrail.response.utils.ExceptionUtil;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalon.holygrail.utils.StringUtil;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.spring.beans.PageResultForMap;
import pub.avalon.sqlhelper.spring.core.SpringJdbcEngine;
import pub.avalonframework.cloud.gar.api.GarRouteViewApi;
import pub.avalonframework.cloud.gar.dc.RouteViewGet;
import pub.avalonframework.cloud.gar.dc.RouteViewPost;
import pub.avalonframework.cloud.gar.dc.RouteViewPut;
import pub.avalonframework.cloud.gar.model.RouteViewModel;
import pub.avalonframework.cloud.gar.service.GarRouteViewService;
import pub.avalonframework.cloud.gar.utils.TableUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author 白超
 * @date 2018/12/6
 */
@RequestMapping(GarRouteViewApi.ROOT_PATH)
@RestController
public class GarRouteViewController implements GarRouteViewApi {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired
    private GarRouteViewService routeViewService;

    @Autowired
    private HttpServletRequest request;

    @Override
    @RequestMapping(value = "/get/validateValueCanUseByRouteId/{value}/{routeId}")
    public DataView getValidateValueCanUseByRouteId(@PathVariable String value, @PathVariable String routeId, String excludeValues, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        boolean canUse = this.routeViewService.getValidateValueCanUseByRouteId(moduleId, routeId, value, StringUtil.isEmpty(excludeValues) ? null : Arrays.asList(excludeValues.split(",")));
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("canUse", canUse));
    }

    @Override
    @RequestMapping(value = "/get/routeViewByRouteViewId/{routeViewId}")
    public DataView getRouteViewByRouteViewId(@PathVariable String routeViewId, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        Map<String, Object> routeView = this.jdbcEngine.queryOne(MySqlDynamicEngine.query(RouteViewModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(finalModuleId).id().equalTo(routeViewId))));
        if (routeView == null) {
            ExceptionUtil.throwFailException(40404, "路由视图不存在");
        }
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("routeView", routeView));
    }

    @Override
    @RequestMapping(value = "/get/list/routeView")
    public DataView getListRouteView(RouteViewGet record, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        List<Map<String, Object>> rows = this.jdbcEngine.queryForList(MySqlDynamicEngine.query(RouteViewModel.class)
                .where((condition, mainTable) -> condition
                        .and((cd, mt) -> cd.and(mt.name().like(record.getLikeText()))
                                .or(mt.value().like(record.getLikeText())))
                        .and(mainTable.moduleId().equalTo(finalModuleId)))
                .sort(table -> table.index().asc()));
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/get/pageList/routeView")
    public DataView getPageListRouteView(RouteViewGet record, Integer currentPage, Integer pageSize, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        currentPage = (currentPage == null || currentPage < 1) ? 1 : currentPage;
        pageSize = (pageSize == null || pageSize < 1 || pageSize > 500) ? 1 : pageSize;
        PageResultForMap pageResultForMap = this.jdbcEngine.pageQueryList(currentPage, pageSize, MySqlDynamicEngine.query(RouteViewModel.class)
                .where((condition, mainTable) -> condition
                        .and((cd, mt) -> cd.and(mt.name().like(record.getLikeText()))
                                .or(mt.value().like(record.getLikeText())))
                        .and(mainTable.moduleId().equalTo(finalModuleId)))
                .sort(table -> table.index().asc()));
        return DataViewUtil.getModelViewSuccess(pageResultForMap.getResult(), pageResultForMap.getLimit());
    }

    @Override
    @RequestMapping(value = "/post/routeView")
    public DataView postRouteView(RouteViewPost record, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        RouteViewGet routeView = this.routeViewService.postRouteView(moduleId, record);
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("routeView", routeView));
    }

    @Override
    @RequestMapping(value = "/put/routeViewByRouteViewId/{routeViewId}")
    public DataView putRouteViewByRouteViewId(@PathVariable String routeViewId, RouteViewPut record, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.routeViewService.putRouteViewByRouteViewId(moduleId, routeViewId, record);
        return DataViewUtil.getMessageViewSuccess("修改路由视图成功");
    }

    @Override
    @RequestMapping(value = "/put/switchRouteViewIndexByRouteViewId/{sourceRouteViewId}/{targetRouteViewId}")
    public DataView putSwitchRouteViewIndexByRouteViewId(@PathVariable String sourceRouteViewId, @PathVariable String targetRouteViewId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.routeViewService.putSwitchRouteViewIndexByRouteViewId(moduleId, sourceRouteViewId, targetRouteViewId);
        return DataViewUtil.getMessageViewSuccess("操作成功");
    }

    @Override
    @RequestMapping(value = "/delete/routeViewByRouteViewId/{routeViewId}")
    public DataView deleteRouteViewByRouteViewId(@PathVariable String routeViewId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.routeViewService.deleteRouteViewByRouteViewId(moduleId, routeViewId);
        return DataViewUtil.getMessageViewSuccess("删除路由视图成功");
    }

    @Override
    @RequestMapping(value = "/delete/list/routeViewByRouteViewIds/{routeViewIds}")
    public DataView deleteListRouteViewByRouteViewIds(@PathVariable String routeViewIds, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.routeViewService.deleteListRouteViewByRouteViewIds(moduleId, Arrays.asList(routeViewIds.split(",")));
        return DataViewUtil.getMessageViewSuccess("删除路由视图成功");
    }

}
