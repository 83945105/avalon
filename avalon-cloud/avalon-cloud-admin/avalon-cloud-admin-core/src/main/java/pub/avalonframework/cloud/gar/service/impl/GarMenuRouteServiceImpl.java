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
import pub.avalonframework.cloud.gar.beans.MenuRoutePut;
import pub.avalonframework.cloud.gar.beans.MenuRouteRelation;
import pub.avalonframework.cloud.gar.dc.MenuRouteGet;
import pub.avalonframework.cloud.gar.dc.MenuRoutePost;
import pub.avalonframework.cloud.gar.entity.Menu;
import pub.avalonframework.cloud.gar.entity.MenuRoute;
import pub.avalonframework.cloud.gar.entity.Route;
import pub.avalonframework.cloud.gar.model.MenuModel;
import pub.avalonframework.cloud.gar.model.MenuRouteModel;
import pub.avalonframework.cloud.gar.model.RouteModel;
import pub.avalonframework.cloud.gar.service.GarMenuRouteService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 白超
 */
@Service
public class GarMenuRouteServiceImpl implements GarMenuRouteService {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    public boolean getValidateMenuRouteExistByRouteIdAndMenuRouteRelation(String moduleId, String routeId, String menuRouteRelation) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(routeId)) {
            ExceptionUtil.throwFailException("路由ID不能为空");
        }
        if (StringUtil.isEmpty(menuRouteRelation)) {
            ExceptionUtil.throwFailException("菜单路由关系不能为空");
        }
        return this.jdbcEngine.queryCount(MySqlDynamicEngine.query(MenuRouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .relation().equalTo(menuRouteRelation)
                                .routeId().equalTo(routeId)))) > 0;
    }

    @Override
    public MenuRouteGet postMenuRouteByMenuIdAndRouteId(String moduleId, String menuId, String routeId, MenuRoutePost record) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(menuId)) {
            ExceptionUtil.throwFailException("菜单ID不能为空");
        }
        if (StringUtil.isEmpty(routeId)) {
            ExceptionUtil.throwFailException("路由ID不能为空");
        }
        Menu menu = this.jdbcEngine.queryByPrimaryKey(menuId, Menu.class, MySqlDynamicEngine.query(MenuModel.class));
        if (menu == null) {
            ExceptionUtil.throwFailException("菜单不存在");
        }
        if (!EnumMethods.equalsTo(menu.getStatus(), Status.NORMAL)) {
            ExceptionUtil.throwFailException("菜单不可用");
        }
        Route route = this.jdbcEngine.queryByPrimaryKey(routeId, Route.class, MySqlDynamicEngine.query(RouteModel.class));
        if (route == null) {
            ExceptionUtil.throwFailException("路由不存在");
        }
        if (!EnumMethods.equalsTo(route.getStatus(), Status.NORMAL)) {
            ExceptionUtil.throwFailException("路由不可用");
        }
        if (StringUtil.isEmpty(record.getRelation())) {
            ExceptionUtil.throwFailException("菜单路由关系不能为空");
        } else if (!EnumMethods.contains(record.getRelation(), MenuRouteRelation.values())) {
            ExceptionUtil.throwFailException("菜单路由关系不正确");
        }
        if (EnumMethods.equalsTo(record.getRelation(), MenuRouteRelation.MENU_CLICK_TO_ROUTE)) {
            // 新增的是跳转路由, 将其余跳转路由删除
            this.jdbcEngine.delete(MySqlDynamicEngine.delete(MenuRouteModel.class)
                    .where((condition, mainTable) -> condition
                            .and(mainTable.moduleId().equalTo(moduleId)
                                    .menuId().equalTo(menuId)
                                    .relation().equalTo(MenuRouteRelation.MENU_CLICK_TO_ROUTE.name()))));
        }
        if (this.getValidateMenuRouteExistByRouteIdAndMenuRouteRelation(moduleId, routeId, record.getRelation())) {
            ExceptionUtil.throwFailException("该菜单路由不可用");
        }

        String timeString = Time.localDateTimeNow();
        long timeStamp = Time.timeStamp();

        MenuRoute menuRouteInsert = new MenuRoute();

        menuRouteInsert.setModuleId(moduleId);

        menuRouteInsert.setMenuGroupId(menu.getMenuGroupId());
        menuRouteInsert.setMenuId(menu.getId());
        menuRouteInsert.setMenuName(menu.getName());
        menuRouteInsert.setMenuValue(menu.getValue());

        menuRouteInsert.setRouteId(route.getId());
        menuRouteInsert.setRouteName(route.getName());
        menuRouteInsert.setRouteValue(route.getValue());
        menuRouteInsert.setRoutePath(route.getPath());

        menuRouteInsert.setRelation(record.getRelation());

        menuRouteInsert.setStatus(Status.NORMAL.name());
        menuRouteInsert.setCreateTime(timeString);
        menuRouteInsert.setCreateTimeStamp(timeStamp);

        int count = this.jdbcEngine.insertJavaBeanSelective(menuRouteInsert, MySqlDynamicEngine.insert(MenuRouteModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("新增菜单路由失败");
        }
        return this.jdbcEngine.queryOne(MenuRouteGet.class, MySqlDynamicEngine.query(MenuRouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .menuId().equalTo(menuId)
                                .routeId().equalTo(routeId)
                                .relation().equalTo(record.getRelation()))));
    }

    @Override
    public void putMenuRouteByMenuRouteId(String moduleId, String menuRouteId, MenuRoutePut record) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(menuRouteId)) {
            ExceptionUtil.throwFailException("菜单路由ID不能为空");
        }
        if (StringUtil.isEmpty(record)) {
            ExceptionUtil.throwFailException("菜单路由数据不能为空");
        }

        MenuRoute menuRoute = this.jdbcEngine.queryByPrimaryKey(menuRouteId, MenuRoute.class, MySqlDynamicEngine.query(MenuRouteModel.class));
        if (menuRoute == null) {
            ExceptionUtil.throwFailException(40404, "菜单路由不存在");
        }

        MenuRoute menuRouteUpdate = new MenuRoute();
        menuRouteUpdate.setUpdateTime(Time.localDateTimeNow());
        menuRouteUpdate.setUpdateTimeStamp(Time.timeStamp());

        int count = this.jdbcEngine.updateJavaBeanByPrimaryKeySelective(menuRouteId, menuRouteUpdate, MySqlDynamicEngine.update(MenuRouteModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("修改菜单路由失败");
        }
    }

    @Override
    public void putMenuRouteByMenuIdAndRouteId(String moduleId, String menuId, String routeId, MenuRoutePut record) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(menuId)) {
            ExceptionUtil.throwFailException("菜单ID不能为空");
        }
        if (StringUtil.isEmpty(routeId)) {
            ExceptionUtil.throwFailException("路由ID不能为空");
        }

        MenuRoute menuRoute = this.jdbcEngine.queryOne(MenuRoute.class, MySqlDynamicEngine.query(MenuRouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.menuId().equalTo(menuId).routeId().equalTo(routeId))));
        if (menuRoute == null) {
            ExceptionUtil.throwFailException(40404, "菜单路由不存在");
        }

        MenuRoute menuRouteUpdate = new MenuRoute();
        menuRouteUpdate.setUpdateTime(Time.localDateTimeNow());
        menuRouteUpdate.setUpdateTimeStamp(Time.timeStamp());

        int count = this.jdbcEngine.updateJavaBeanSelective(menuRouteUpdate, MySqlDynamicEngine.update(MenuRouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .menuId().equalTo(menuId)
                                .routeId().equalTo(routeId))));
        if (count != 1) {
            ExceptionUtil.throwFailException("修改菜单路由失败");
        }

    }

    @Override
    public void putListMenuRouteByRouteId(String moduleId, String routeId, MenuRoute menuRouteUpdate) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(routeId)) {
            ExceptionUtil.throwFailException("路由ID不能为空");
        }
        this.jdbcEngine.updateJavaBeanSelective(menuRouteUpdate, MySqlDynamicEngine.update(MenuRouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .routeId().equalTo(routeId))));
    }

    @Override
    public void putListMenuRouteByMenuId(String moduleId, String menuId, MenuRoute menuRouteUpdate) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(menuId)) {
            ExceptionUtil.throwFailException("菜单ID不能为空");
        }
        this.jdbcEngine.updateJavaBeanSelective(menuRouteUpdate, MySqlDynamicEngine.update(MenuRouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .menuId().equalTo(menuId))));
    }

    @Override
    public void deleteListMenuRouteByMenuGroupId(String moduleId, String menuGroupId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(menuGroupId)) {
            ExceptionUtil.throwFailException("菜单组ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(MenuRouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .menuGroupId().equalTo(menuGroupId))));
    }

    @Override
    public void deleteListMenuRouteByMenuGroupIds(String moduleId, List<String> menuGroupIds) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(menuGroupIds)) {
            ExceptionUtil.throwFailException("菜单组ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(MenuRouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .menuGroupId().inS(menuGroupIds))));
    }

    @Override
    public void deleteListMenuRouteByMenuId(String moduleId, String menuId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(menuId)) {
            ExceptionUtil.throwFailException("菜单ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(MenuRouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .menuId().equalTo(menuId))));
    }

    @Override
    public void deleteListMenuRouteByMenuIds(String moduleId, List<String> menuIds) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(menuIds)) {
            ExceptionUtil.throwFailException("菜单ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(MenuRouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .menuId().inS(menuIds))));
    }

    @Override
    public void deleteListMenuRouteByRouteId(String moduleId, String routeId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(routeId)) {
            ExceptionUtil.throwFailException("路由ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(MenuRouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .routeId().equalTo(routeId))));
    }

    @Override
    public void deleteListMenuRouteByRouteIds(String moduleId, List<String> routeIds) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(routeIds)) {
            ExceptionUtil.throwFailException("路由ID不能为空");
        }
        this.jdbcEngine.delete(MySqlDynamicEngine.delete(MenuRouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .routeId().inS(routeIds))));
    }

    @Override
    public void deleteMenuRouteByMenuRouteId(String moduleId, String menuRouteId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(menuRouteId)) {
            ExceptionUtil.throwFailException("菜单路由ID不能为空");
        }
        int count = this.jdbcEngine.deleteByPrimaryKey(menuRouteId, MySqlDynamicEngine.delete(MenuRouteModel.class));
        if (count != 1) {
            ExceptionUtil.throwFailException("删除菜单路由失败");
        }
    }

    @Override
    public void deleteListMenuRouteByMenuRouteIds(String moduleId, Set<String> menuRouteIds) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(menuRouteIds)) {
            ExceptionUtil.throwFailException("菜单路由ID不能为空");
        }
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(MenuRouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .id().inS(menuRouteIds))));
        if (count != menuRouteIds.size()) {
            ExceptionUtil.throwFailException("删除菜单路由失败");
        }
    }

    @Override
    public void deleteMenuRouteByMenuIdAndRouteId(String moduleId, String menuId, String routeId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(menuId)) {
            ExceptionUtil.throwFailException("菜单ID不能为空");
        }
        if (StringUtil.isEmpty(routeId)) {
            ExceptionUtil.throwFailException("路由ID不能为空");
        }
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(MenuRouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .routeId().equalTo(routeId)
                                .menuId().equalTo(menuId))));
        if (count != 1) {
            ExceptionUtil.throwFailException("删除菜单路由失败");
        }
    }

    @Override
    public void deleteMenuRouteByMenuIdAndRouteIdAndMenuRouteRelation(String moduleId, String menuId, String routeId, String menuRouteRelation) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(menuId)) {
            ExceptionUtil.throwFailException("菜单ID不能为空");
        }
        if (StringUtil.isEmpty(routeId)) {
            ExceptionUtil.throwFailException("路由ID不能为空");
        }
        if (StringUtil.isEmpty(menuRouteRelation)) {
            ExceptionUtil.throwFailException("菜单路由关系不能为空");
        }
        if (!EnumMethods.contains(menuRouteRelation, MenuRouteRelation.values())) {
            ExceptionUtil.throwFailException("菜单路由关系不正确");
        }
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(MenuRouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .relation().equalTo(menuRouteRelation)
                                .routeId().equalTo(routeId)
                                .menuId().equalTo(menuId))));
        if (count == 0) {
            ExceptionUtil.throwFailException("删除菜单路由失败");
        }
    }

    @Override
    public void deleteListMenuRouteByMenuIdsAndRouteIds(String moduleId, Set<String> menuIds, Set<String> routeIds) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(menuIds)) {
            ExceptionUtil.throwFailException("菜单ID不能为空");
        }
        if (StringUtil.isEmpty(routeIds)) {
            ExceptionUtil.throwFailException("路由ID不能为空");
        }
        List<String> menuRouteIdsByMenuIds = this.jdbcEngine.queryColumnList(MenuRouteModel.primaryKeyAlias, String.class, MySqlDynamicEngine.query(MenuRouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .menuId().inS(menuIds))));
        List<String> menuRouteIdsByRouteIds = this.jdbcEngine.queryColumnList(MenuRouteModel.primaryKeyAlias, String.class, MySqlDynamicEngine.query(MenuRouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .routeId().inS(routeIds))));
        //找出要删除的ID
        Set<String> ids = new HashSet<>();
        for (String menuRouteIdsByMenuId : menuRouteIdsByMenuIds) {
            for (String menuRouteIdsByRouteId : menuRouteIdsByRouteIds) {
                if (menuRouteIdsByMenuId.equals(menuRouteIdsByRouteId)) {
                    ids.add(menuRouteIdsByMenuId);
                }
            }
        }
        if (StringUtil.isEmpty(ids)) {
            ExceptionUtil.throwFailException("没有可以删除的菜单路由");
        }
        //执行删除
        int count = this.jdbcEngine.delete(MySqlDynamicEngine.delete(MenuRouteModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.moduleId().equalTo(moduleId)
                                .id().inS(ids))));
        if (count != ids.size()) {
            ExceptionUtil.throwFailException("删除菜单路由失败");
        }
    }

}
