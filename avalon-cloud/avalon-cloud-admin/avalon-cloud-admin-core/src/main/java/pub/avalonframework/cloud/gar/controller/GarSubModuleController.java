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
import pub.avalonframework.cloud.gar.api.GarSubModuleApi;
import pub.avalonframework.cloud.gar.dc.SubModuleGet;
import pub.avalonframework.cloud.gar.dc.SubModulePost;
import pub.avalonframework.cloud.gar.dc.SubModulePut;
import pub.avalonframework.cloud.gar.model.MenuGroupModel;
import pub.avalonframework.cloud.gar.model.RouteModel;
import pub.avalonframework.cloud.gar.model.SubModuleModel;
import pub.avalonframework.cloud.gar.service.GarSubModuleService;
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
@RequestMapping(GarSubModuleApi.ROOT_PATH)
@RestController
public class GarSubModuleController implements GarSubModuleApi {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired
    private GarSubModuleService subModuleService;

    @Autowired
    private HttpServletRequest request;

    @Override
    @RequestMapping(value = "/get/validateValueCanUse/{value}")
    public DataView getValidateValueCanUse(@PathVariable String value, String excludeValues, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        boolean canUse = this.subModuleService.getValidateValueCanUse(moduleId, value, StringUtil.isEmpty(excludeValues) ? null : Arrays.asList(excludeValues.split(",")));
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("canUse", canUse));
    }

    @Override
    @RequestMapping(value = "/get/subModuleBySubModuleId/{subModuleId}")
    public DataView getSubModuleBySubModuleId(@PathVariable String subModuleId, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        Map<String, Object> subModule = this.jdbcEngine.queryOne(MySqlDynamicEngine.query(SubModuleModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(finalModuleId).id().equalTo(subModuleId))));
        if (subModule == null) {
            ExceptionUtil.throwFailException(40404, "子模块不存在");
        }
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("subModule", subModule));
    }

    @Override
    @RequestMapping(value = "/get/list/subModule")
    public DataView getListSubModule(SubModuleGet record, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        List<Map<String, Object>> rows = this.jdbcEngine.queryForList(MySqlDynamicEngine.query(SubModuleModel.class)
                .where((condition, mainTable) -> condition
                        .and((cd, mt) -> cd.and(mt.name().like(record.getLikeText()))
                                .or(mt.value().like(record.getLikeText())))
                        .and(mainTable.moduleId().equalTo(finalModuleId)))
                .sort(table -> table.index().asc()));
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/get/pageList/subModule")
    public DataView getPageListSubModule(SubModuleGet record, Integer currentPage, Integer pageSize, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        currentPage = (currentPage == null || currentPage < 1) ? 1 : currentPage;
        pageSize = (pageSize == null || pageSize < 1 || pageSize > 500) ? 1 : pageSize;
        PageResultForMap pageResultForMap = this.jdbcEngine.pageQueryList(currentPage, pageSize, MySqlDynamicEngine.query(SubModuleModel.class)
                .where((condition, mainTable) -> condition
                        .and((cd, mt) -> cd.and(mt.name().like(record.getLikeText()))
                                .or(mt.value().like(record.getLikeText())))
                        .and(mainTable.moduleId().equalTo(finalModuleId)))
                .sort(table -> table.index().asc()));
        return DataViewUtil.getModelViewSuccess(pageResultForMap.getResult(), pageResultForMap.getLimit());
    }

    @Override
    @RequestMapping(value = "/get/list/subModuleAndSubModuleRouteCount")
    public DataView getListSubModuleAndSubModuleRouteCount(SubModuleGet record, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        List<Map<String, Object>> rows = this.jdbcEngine.queryForList(MySqlDynamicEngine.query(SubModuleModel.class)
                .subQuery(RouteModel.class, (mainTable, query) -> query
                        .where((condition, subTable) -> condition
                                .and(subTable.moduleId().equalTo(moduleId)
                                        .subModuleId().equalTo(mainTable.id()))).queryCount(), "routeCount")
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and((cd, mt) -> cd.and(mt.name().like(record.getLikeText()))
                                .or(mt.value().like(record.getLikeText())))
                        .and(mainTable.moduleId().equalTo(finalModuleId).id().equalTo(record.getSubModuleId())))
                .sort(table -> table.index().asc()));
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/get/list/subModuleAndSubModuleMenuGroupCount")
    public DataView getListSubModuleAndSubModuleMenuGroupCount(SubModuleGet record, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        List<Map<String, Object>> rows = this.jdbcEngine.queryForList(MySqlDynamicEngine.query(SubModuleModel.class)
                .subQuery(MenuGroupModel.class, (mainTable, query) -> query
                        .where((condition, subTable) -> condition
                                .and(subTable.moduleId().equalTo(moduleId)
                                        .subModuleId().equalTo(mainTable.id()))).queryCount(), "menuGroupCount")
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and((cd, mt) -> cd.and(mt.name().like(record.getLikeText()))
                                .or(mt.value().like(record.getLikeText())))
                        .and(mainTable.moduleId().equalTo(finalModuleId).id().equalTo(record.getSubModuleId())))
                .sort(table -> table.index().asc()));
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/post/subModule")
    public DataView postSubModule(SubModulePost record, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        SubModuleGet subModule = this.subModuleService.postSubModule(moduleId, record);
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("subModule", subModule));
    }

    @Override
    @RequestMapping(value = "/put/subModuleBySubModuleId/{subModuleId}")
    public DataView putSubModuleBySubModuleId(@PathVariable String subModuleId, SubModulePut record, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.subModuleService.putSubModuleBySubModuleId(moduleId, subModuleId, record);
        return DataViewUtil.getMessageViewSuccess("修改子模块成功");
    }

    @Override
    @RequestMapping(value = "/put/switchSubModuleIndexBySubModuleId/{sourceSubModuleId}/{targetSubModuleId}")
    public DataView putSwitchSubModuleIndexBySubModuleId(@PathVariable String sourceSubModuleId, @PathVariable String targetSubModuleId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.subModuleService.putSwitchSubModuleIndexBySubModuleId(moduleId, sourceSubModuleId, targetSubModuleId);
        return DataViewUtil.getMessageViewSuccess("操作成功");
    }

    @Override
    @RequestMapping(value = "/delete/subModuleBySubModuleId/{subModuleId}")
    public DataView deleteSubModuleBySubModuleId(@PathVariable String subModuleId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.subModuleService.deleteSubModuleBySubModuleId(moduleId, subModuleId);
        return DataViewUtil.getMessageViewSuccess("删除子模块成功");
    }

    @Override
    @RequestMapping(value = "/delete/list/subModuleBySubModuleIds/{subModuleIds}")
    public DataView deleteListSubModuleBySubModuleIds(@PathVariable String subModuleIds, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.subModuleService.deleteListSubModuleBySubModuleIds(moduleId, Arrays.asList(subModuleIds.split(",")));
        return DataViewUtil.getMessageViewSuccess("删除子模块成功");
    }

}
