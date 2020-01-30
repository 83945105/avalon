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
import pub.avalonframework.cloud.gar.api.GarMenuRouteApi;
import pub.avalonframework.cloud.gar.beans.MenuRoutePut;
import pub.avalonframework.cloud.gar.dc.MenuRouteGet;
import pub.avalonframework.cloud.gar.dc.MenuRoutePost;
import pub.avalonframework.cloud.gar.entity.MenuRoute;
import pub.avalonframework.cloud.gar.model.MenuRouteModel;
import pub.avalonframework.cloud.gar.service.GarMenuRouteService;
import pub.avalonframework.cloud.gar.utils.TableUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author baichao
 * @date 2018/7/23
 */
@RestController
@RequestMapping(GarMenuRouteApi.ROOT_PATH)
public class GarMenuRouteController implements GarMenuRouteApi {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired
    private GarMenuRouteService menuRouteService;

    @Autowired
    private HttpServletRequest request;

    @Override
    @RequestMapping(value = "/get/list/menuRoute")
    public DataView getListMenuRoute(MenuRouteGet record, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        List<Map<String, Object>> rows = this.jdbcEngine.queryForList(MySqlDynamicEngine.query(MenuRouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(finalModuleId))));
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/get/list/menuRouteByMenuId/{menuId}")
    public DataView getListMenuRouteByMenuId(@PathVariable String menuId, MenuRouteGet record, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        List<Map<String, Object>> rows = this.jdbcEngine.queryForList(MySqlDynamicEngine.query(MenuRouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(finalModuleId).menuId().equalTo(menuId))));
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/get/list/menuRouteByRouteId/{routeId}")
    public DataView getListMenuRouteByRouteId(@PathVariable String routeId, MenuRouteGet record, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        List<Map<String, Object>> rows = this.jdbcEngine.queryForList(MySqlDynamicEngine.query(MenuRouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(finalModuleId).routeId().equalTo(routeId))));
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/post/menuRouteByMenuIdAndRouteId/{menuId}/{routeId}")
    public DataView postMenuRouteByMenuIdAndRouteId(@PathVariable String menuId, @PathVariable String routeId, MenuRoutePost record, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        MenuRoute menuRoute = this.menuRouteService.postMenuRouteByMenuIdAndRouteId(moduleId, menuId, routeId, record);
        return DataViewUtil.getModelViewSuccess("新建菜单路由成功", Collections.singletonMap("menuRoute", menuRoute));
    }

    @Override
    @RequestMapping(value = "/put/menuRouteByMenuRouteId/{menuRouteId}")
    public DataView putMenuRouteByMenuRouteId(@PathVariable String menuRouteId, MenuRoutePut record, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.menuRouteService.putMenuRouteByMenuRouteId(moduleId, menuRouteId, record);
        return DataViewUtil.getMessageViewSuccess("修改菜单路由成功");
    }

    @Override
    @RequestMapping(value = "/put/menuRouteByMenuIdAndRouteId/{menuId}/{routeId}")
    public DataView putMenuRouteByMenuIdAndRouteId(@PathVariable String menuId, @PathVariable String routeId, MenuRoutePut record, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.menuRouteService.putMenuRouteByMenuIdAndRouteId(moduleId, menuId, routeId, record);
        return DataViewUtil.getMessageViewSuccess("修改菜单路由成功");
    }

    @Override
    @RequestMapping(value = "/delete/menuRouteByMenuRouteId/{menuRouteId}")
    public DataView deleteMenuRouteByMenuRouteId(@PathVariable String menuRouteId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.menuRouteService.deleteMenuRouteByMenuRouteId(moduleId, menuRouteId);
        return DataViewUtil.getMessageViewSuccess("删除菜单路由成功");
    }

    @Override
    @RequestMapping(value = "/delete/list/menuRouteByMenuRouteIds/{menuRouteIds}")
    public DataView deleteListMenuRouteByMenuRouteIds(@PathVariable String menuRouteIds, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.menuRouteService.deleteListMenuRouteByMenuRouteIds(moduleId,
                StringUtil.isEmpty(menuRouteIds) ? null : new HashSet<>(Arrays.asList(menuRouteIds.split(","))));
        return DataViewUtil.getMessageViewSuccess("删除菜单路由成功");
    }

    @Override
    @RequestMapping(value = "/delete/menuRouteByMenuIdAndRouteId/{menuId}/{routeId}")
    public DataView deleteMenuRouteByMenuIdAndRouteId(@PathVariable String menuId, @PathVariable String routeId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.menuRouteService.deleteMenuRouteByMenuIdAndRouteId(moduleId, menuId, routeId);
        return DataViewUtil.getMessageViewSuccess("删除菜单路由成功");
    }

    @Override
    @RequestMapping(value = "/delete/menuRouteByMenuIdAndRouteIdAndMenuRouteRelation/{menuId}/{routeId}/{menuRouteRelation}")
    public DataView deleteMenuRouteByMenuIdAndRouteIdAndMenuRouteRelation(@PathVariable String menuId, @PathVariable String routeId, @PathVariable String menuRouteRelation, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.menuRouteService.deleteMenuRouteByMenuIdAndRouteIdAndMenuRouteRelation(moduleId, menuId, routeId, menuRouteRelation);
        return DataViewUtil.getMessageViewSuccess("删除菜单路由成功");
    }

    @Override
    @RequestMapping(value = "/delete/list/menuRouteByMenuIdsAndRouteIds/{menuIds}/{routeIds}")
    public DataView deleteListMenuRouteByMenuIdsAndRouteIds(@PathVariable String menuIds, @PathVariable String routeIds, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.menuRouteService.deleteListMenuRouteByMenuIdsAndRouteIds(moduleId,
                StringUtil.isEmpty(menuIds) ? null : new HashSet<>(Arrays.asList(menuIds.split(","))),
                StringUtil.isEmpty(routeIds) ? null : new HashSet<>(Arrays.asList(routeIds.split(","))));
        return DataViewUtil.getMessageViewSuccess("删除菜单路由成功");
    }

}
