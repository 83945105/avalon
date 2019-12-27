package pub.avalonframework.cloud.gar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pub.avalon.holygrail.response.utils.DataViewUtil;
import pub.avalon.holygrail.response.utils.ExceptionUtil;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.spring.beans.PageResultForMap;
import pub.avalon.sqlhelper.spring.core.SpringJdbcEngine;
import pub.avalonframework.cloud.gar.api.GarMenuGroupApi;
import pub.avalonframework.cloud.gar.dc.MenuGroupGet;
import pub.avalonframework.cloud.gar.dc.MenuGroupPost;
import pub.avalonframework.cloud.gar.dc.MenuGroupPut;
import pub.avalonframework.cloud.gar.model.MenuGroupModel;
import pub.avalonframework.cloud.gar.model.MenuModel;
import pub.avalonframework.cloud.gar.service.GarMenuGroupService;
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
@RequestMapping(GarMenuGroupApi.ROOT_PATH)
@RestController
public class GarMenuGroupController implements GarMenuGroupApi {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired
    private GarMenuGroupService menuGroupService;

    @Autowired
    private HttpServletRequest request;

    @Override
    @RequestMapping(value = "/get/menuGroupByMenuGroupId/{menuGroupId}")
    public DataView getMenuGroupByMenuGroupId(@PathVariable String menuGroupId, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        Map<String, Object> menuGroup = this.jdbcEngine.queryOne(MySqlDynamicEngine.query(MenuGroupModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(finalModuleId).id().equalTo(menuGroupId))));
        if (menuGroup == null) {
            ExceptionUtil.throwFailException(40404, "菜单组不存在");
        }
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("menuGroup", menuGroup));
    }

    @Override
    @RequestMapping(value = "/get/list/menuGroup")
    public DataView getListMenuGroup(MenuGroupGet record, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        List<Map<String, Object>> rows = this.jdbcEngine.queryForList(MySqlDynamicEngine.query(MenuGroupModel.class)
                .where((condition, mainTable) -> condition
                        .and((cd, mt) -> cd.and(mt.name().like(record.getLikeText())))
                        .and(mainTable.moduleId().equalTo(finalModuleId)))
                .sort(table -> table.index().asc()));
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/get/pageList/menuGroup")
    public DataView getPageListMenuGroup(MenuGroupGet record, Integer currentPage, Integer pageSize, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        currentPage = (currentPage == null || currentPage < 1) ? 1 : currentPage;
        pageSize = (pageSize == null || pageSize < 1 || pageSize > 500) ? 1 : pageSize;
        PageResultForMap pageResultForMap = this.jdbcEngine.pageQueryList(currentPage, pageSize, MySqlDynamicEngine.query(MenuGroupModel.class)
                .where((condition, mainTable) -> condition
                        .and((cd, mt) -> cd.and(mt.name().like(record.getLikeText())))
                        .and(mainTable.moduleId().equalTo(finalModuleId)))
                .sort(table -> table.index().asc()));
        return DataViewUtil.getModelViewSuccess(pageResultForMap.getResult(), pageResultForMap.getLimit());
    }

    @Override
    @RequestMapping(value = "/get/list/menuGroupBySubModuleId/{subModuleId}")
    public DataView getListMenuGroupBySubModuleId(@PathVariable String subModuleId, MenuGroupGet record, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        List<MenuGroupGet> rows = this.jdbcEngine.queryForList(MenuGroupGet.class, MySqlDynamicEngine.query(MenuGroupModel.class)
                .subQuery(MenuModel.class, "Menu", (mainTable, query) -> query
                                .where((condition, subTable) -> condition
                                        .and(subTable.moduleId().equalTo(moduleId)
                                                .parentId().equalTo("")
                                                .menuGroupId().equalTo(mainTable.id()))).queryCount(),
                        "rootMenuCount")
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and((cd, mt) -> cd.and(mt.name().like(record.getLikeText())))
                        .and(mainTable.moduleId().equalTo(finalModuleId)
                                .subModuleId().equalTo(subModuleId)))
                .sort(table -> table.index().asc()));
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/post/menuGroup")
    public DataView postMenuGroup(MenuGroupPost record, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        MenuGroupGet menuGroup = this.menuGroupService.postMenuGroup(moduleId, record);
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("menuGroup", menuGroup));
    }

    @Override
    @RequestMapping(value = "/put/menuGroupByMenuGroupId/{menuGroupId}")
    public DataView putMenuGroupByMenuGroupId(@PathVariable String menuGroupId, MenuGroupPut record, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.menuGroupService.putMenuGroupByMenuGroupId(moduleId, menuGroupId, record);
        return DataViewUtil.getMessageViewSuccess("修改菜单组成功");
    }

    @Override
    @RequestMapping(value = "/put/menuGroupUseByMenuGroupId/{menuGroupId}/{menuGroupUse}")
    public DataView putMenuGroupUseByMenuGroupId(@PathVariable String menuGroupId, @PathVariable String menuGroupUse, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.menuGroupService.putMenuGroupUseByMenuGroupId(moduleId, menuGroupId, menuGroupUse);
        return DataViewUtil.getMessageViewSuccess("修改菜单组是否使用成功");
    }

    @Override
    @RequestMapping(value = "/put/switchMenuGroupIndexByMenuGroupId/{sourceMenuGroupId}/{targetMenuGroupId}")
    public DataView putSwitchMenuGroupIndexByMenuGroupId(@PathVariable String sourceMenuGroupId, @PathVariable String targetMenuGroupId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.menuGroupService.putSwitchMenuGroupIndexByMenuGroupId(moduleId, sourceMenuGroupId, targetMenuGroupId);
        return DataViewUtil.getMessageViewSuccess("操作成功");
    }

    @Override
    @RequestMapping(value = "/delete/menuGroupByMenuGroupId/{menuGroupId}")
    public DataView deleteMenuGroupByMenuGroupId(@PathVariable String menuGroupId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.menuGroupService.deleteMenuGroupByMenuGroupId(moduleId, menuGroupId);
        return DataViewUtil.getMessageViewSuccess("删除菜单组成功");
    }

    @Override
    @RequestMapping(value = "/delete/list/menuGroupByMenuGroupIds/{menuGroupIds}")
    public DataView deleteListMenuGroupByMenuGroupIds(@PathVariable String menuGroupIds, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.menuGroupService.deleteListMenuGroupByMenuGroupIds(moduleId, Arrays.asList(menuGroupIds.split(",")));
        return DataViewUtil.getMessageViewSuccess("删除菜单组成功");
    }

}
