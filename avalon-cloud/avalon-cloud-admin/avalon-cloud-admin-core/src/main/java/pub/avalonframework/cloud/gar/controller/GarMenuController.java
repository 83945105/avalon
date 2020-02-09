package pub.avalonframework.cloud.gar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import pub.avalon.sqlhelper.factory.MySqlDynamicEngine;
import pub.avalon.sqlhelper.spring.beans.PageResultForBean;
import pub.avalon.sqlhelper.spring.beans.PageResultForMap;
import pub.avalon.sqlhelper.spring.core.SpringJdbcEngine;
import pub.avalonframework.cloud.gar.api.GarMenuApi;
import pub.avalonframework.cloud.gar.beans.MenuDragParams;
import pub.avalonframework.cloud.gar.beans.MenuGroupType;
import pub.avalonframework.cloud.gar.beans.MenuGroupUse;
import pub.avalonframework.cloud.gar.beans.MenuRouteRelation;
import pub.avalonframework.cloud.gar.dc.*;
import pub.avalonframework.cloud.gar.entity.AutRole;
import pub.avalonframework.cloud.gar.entity.Menu;
import pub.avalonframework.cloud.gar.entity.SubModule;
import pub.avalonframework.cloud.gar.model.*;
import pub.avalonframework.cloud.gar.service.GarMenuNodeDraggableService;
import pub.avalonframework.cloud.gar.service.GarMenuService;
import pub.avalonframework.cloud.gar.utils.TableUtils;
import pub.avalonframework.web.spring.http.response.HttpResultInfo;
import pub.avalonframework.web.spring.http.response.exception.impl.FailMessageException;
import pub.avalonframework.web.spring.http.response.view.impl.EntityLimitMessageView;
import pub.avalonframework.web.spring.http.response.view.impl.EntityMessageView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 白超
 * @date 2018/12/6
 */
@RequestMapping(GarMenuApi.ROOT_PATH)
@RestController
public class GarMenuController implements GarMenuApi {

    @Autowired
    private SpringJdbcEngine jdbcEngine;

    @Autowired
    private GarMenuService menuService;

    @Autowired
    private GarMenuNodeDraggableService menuNodeDraggableService;

    @Autowired
    private HttpServletRequest request;

    @Override
    @RequestMapping(value = "/get/validateValueCanUseBySubModuleId/{value}/{subModuleId}")
    public EntityMessageView<Boolean> getValidateValueCanUseBySubModuleId(@PathVariable String value, @PathVariable String subModuleId, String excludeValues, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        boolean canUse = this.menuService.getValidateValueCanUseBySubModuleId(moduleId, subModuleId, value, StringUtil.isEmpty(excludeValues) ? null : Arrays.asList(excludeValues.split(",")));
        return new EntityMessageView<>(canUse, new HttpResultInfo(HttpStatus.OK));
    }

    @Override
    @RequestMapping(value = "/get/menuByMenuId/{menuId}")
    public EntityMessageView<Menu> getMenuByMenuId(@PathVariable String menuId, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        Menu menu = this.jdbcEngine.queryOne(Menu.class, MySqlDynamicEngine.query(MenuModel.class)
                .where((condition, mainTable) -> condition.and(mainTable.moduleId().equalTo(finalModuleId).id().equalTo(menuId))));
        if (menu == null) {
            throw new FailMessageException("菜单不存在");
        }
        return new EntityMessageView<>(menu, new HttpResultInfo(HttpStatus.OK));
    }

    @Override
    @RequestMapping(value = "/get/list/menu")
    public EntityMessageView<List<Menu>> getListMenu(MenuGet record, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        List<Menu> rows = this.jdbcEngine.queryForList(Menu.class, MySqlDynamicEngine.query(MenuModel.class)
                .where((condition, mainTable) -> condition
                        .and((cd, mt) -> cd.and(mt.name().like(record.getLikeText()))
                                .or(mt.value().like(record.getLikeText())))
                        .and(mainTable.moduleId().equalTo(finalModuleId)))
                .sort(table -> table.index().asc()));
        return new EntityMessageView<>(rows, new HttpResultInfo(HttpStatus.OK));
    }

    @Override
    @RequestMapping(value = "/get/pageList/menu")
    public EntityLimitMessageView<List<Menu>> getPageListMenu(MenuGet record, Integer currentPage, Integer pageSize, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        currentPage = (currentPage == null || currentPage < 1) ? 1 : currentPage;
        pageSize = (pageSize == null || pageSize < 1 || pageSize > 500) ? 1 : pageSize;
        PageResultForBean<Menu> pageResultForBean = this.jdbcEngine.pageQueryList(Menu.class, currentPage, pageSize, MySqlDynamicEngine.query(MenuModel.class)
                .where((condition, mainTable) -> condition
                        .and((cd, mt) -> cd.and(mt.name().like(record.getLikeText()))
                                .or(mt.value().like(record.getLikeText())))
                        .and(mainTable.moduleId().equalTo(finalModuleId)))
                .sort(table -> table.index().asc()));
        return new EntityLimitMessageView<>(pageResultForBean.getResult(), pageResultForBean.getLimit(), new HttpResultInfo(HttpStatus.OK));
    }

    @Override
    @RequestMapping(value = "/get/list/menuBySubModuleId/{subModuleId}")
    public DataView getListMenuBySubModuleId(@PathVariable String subModuleId, MenuGet record, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        List<MenuGet> rows = this.jdbcEngine.queryForList(MenuGet.class, MySqlDynamicEngine.query(MenuModel.class)
                .subQuery(MenuModel.class, "subMenu", (mainTable, query) -> query
                        .where((condition, subTable) -> condition
                                .and(subTable.moduleId().equalTo(moduleId).parentId().equalTo(mainTable.id()))).queryCount(), "childCount")
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and((cd, mt) -> cd.and(mt.name().like(record.getLikeText()))
                                .or(mt.value().like(record.getLikeText())))
                        .and(mainTable.moduleId().equalTo(finalModuleId)
                                .subModuleId().equalTo(subModuleId)))
                .sort(table -> table.index().asc()));
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/get/list/rootMenuBySubModuleIdAndMenuGroupId/{subModuleId}/{menuGroupId}")
    public DataView getListRootMenuBySubModuleIdAndMenuGroupId(@PathVariable String subModuleId, @PathVariable String menuGroupId, MenuGet record, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        List<MenuGet> rows = this.jdbcEngine.queryForList(MenuGet.class, MySqlDynamicEngine.query(MenuModel.class)
                .subQuery(MenuModel.class, "subMenu", (mainTable, query) -> query
                        .where((condition, subTable) -> condition
                                .and(subTable.moduleId().equalTo(moduleId).parentId().equalTo(mainTable.id()))).queryCount(), "childCount")
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and((cd, mt) -> cd.and(mt.name().like(record.getLikeText()))
                                .or(mt.value().like(record.getLikeText())))
                        .and(mainTable.moduleId().equalTo(finalModuleId)
                                .parentId().equalTo("")
                                .menuGroupId().equalTo(menuGroupId)
                                .subModuleId().equalTo(subModuleId)))
                .sort(table -> table.index().asc()));
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/get/list/menuByParentId/{parentMenuId}")
    public DataView getListMenuByParentMenuId(@PathVariable String parentMenuId, MenuGet record, String moduleId) throws Exception {
        String finalModuleId = TableUtils.getModuleId(moduleId, request);
        List<MenuGet> rows = this.jdbcEngine.queryForList(MenuGet.class, MySqlDynamicEngine.query(MenuModel.class)
                .innerJoin(MenuModel.class, "parentMenu", (on, joinTable, mainTable) -> on
                        .and(joinTable.id().equalTo(mainTable.parentId())))
                .subQuery(MenuModel.class, "subMenu", (mainTable, query) -> query
                        .where((condition, subTable) -> condition
                                .and(subTable.moduleId().equalTo(moduleId).parentId().equalTo(mainTable.id()))).queryCount(), "childCount")
                .column(MenuModel.class, "parentMenu", table -> table.name("parentMenuName"))
                .column(table -> table)
                .where((condition, mainTable) -> condition
                        .and((cd, mt) -> cd.and(mt.name().like(record.getLikeText()))
                                .or(mt.value().like(record.getLikeText())))
                        .and(mainTable.moduleId().equalTo(finalModuleId)
                                .parentId().equalTo(parentMenuId)))
                .sort(table -> table.index().asc()));
        return DataViewUtil.getModelViewSuccess(rows);
    }

    @Override
    @RequestMapping(value = "/get/menuTreeBySubModuleValueAndMenuGroupType/{subModuleValue}/{menuGroupType}")
    public EntityMessageView<List<MenuGet>> getMenuTreeBySubModuleValueAndMenuGroupType(@PathVariable String subModuleValue, @PathVariable String menuGroupType, String moduleId) throws Exception {
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("模块ID不能为空");
        }
        if (StringUtil.isEmpty(subModuleValue)) {
            ExceptionUtil.throwFailException("子模块唯一标识符不能为空");
        }
        if (!EnumMethods.contains(menuGroupType, MenuGroupType.values())) {
            ExceptionUtil.throwFailException("菜单组类型不正确");
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

        List<String> menuGroupIds = this.jdbcEngine.queryColumnList(MenuGroupModel.id_alias, String.class, MySqlDynamicEngine.query(MenuGroupModel.class)
                .where((condition, mainTable) -> condition
                        .and(mainTable.type().equalTo(menuGroupType)
                                .moduleId().equalTo(moduleId)
                                .subModuleId().equalTo(subModule.getId())
                                .use().equalTo(MenuGroupUse.TRUE.name())))
                .sort(table -> table.index().asc()));

        if (menuGroupIds.size() == 0) {
            return new EntityMessageView<>(Collections.emptyList(), new HttpResultInfo(HttpStatus.OK));
        }

        String roleMenuTableName = TableUtils.getRoleMenuTableName(moduleId);
        // 根菜单
        List<MenuGet> rootMenus = new ArrayList<>();
        List<MenuGet> subMenus = new ArrayList<>();
        // 用于记录已经找到的菜单
        Map<String, MenuGet> menuCache = new LinkedHashMap<>();
        RouteGet route;
        AutRole role;
        for (String menuGroupId : menuGroupIds) {
            //查询出所有菜单数据
            List<MenuGet> menuList = this.jdbcEngine.queryForList(MenuGet.class, MySqlDynamicEngine.query(MenuModel.class)
                    .where((condition, mainTable) -> condition
                            .and(mainTable.moduleId().equalTo(moduleId)
                                    .menuGroupId().equalTo(menuGroupId)))
                    .sort(table -> table.index().asc()));

            if (StringUtil.isEmpty(menuList)) {
                continue;
            }

            Set<String> menuIds = menuList.stream().map(Menu::getId).collect(Collectors.toSet());

            //查询出所有菜单关联的路由
            Map<String, List<MenuRouteGet>> menuRoutesMap = this.jdbcEngine.queryListInMap(MenuRouteModel.menuId_alias, MenuRouteGet.class, MySqlDynamicEngine.query(MenuRouteModel.class)
                    .where((condition, mainTable) -> condition
                            .and(mainTable.moduleId().equalTo(moduleId)
                                    .menuId().inS(menuIds))));

            //查询出所有菜单授予的角色
            Map<String, List<AutRoleMenuGet>> roleMenusMap = this.jdbcEngine.queryListInMap(AutRoleMenuModel.menuId_alias, AutRoleMenuGet.class, MySqlDynamicEngine.query(roleMenuTableName, AutRoleMenuModel.class)
                    .where((condition, mainTable) -> condition
                            .and(mainTable.menuId().inS(menuIds))));

            //组建菜单关系
            List<MenuRouteGet> menuRouteList;
            List<AutRoleMenuGet> roleMenuList;
            for (MenuGet menuGet : menuList) {
                menuRouteList = menuRoutesMap.get(menuGet.getId());
                if (!StringUtil.isEmpty(menuRouteList)) {
                    for (MenuRouteGet menuRouteGet : menuRouteList) {
                        route = new RouteGet();
                        route.setId(menuRouteGet.getRouteId());
                        route.setName(menuRouteGet.getRouteName());
                        route.setValue(menuRouteGet.getRouteValue());
                        route.setPath(menuRouteGet.getRoutePath());
                        route.setMenuRelation(menuRouteGet.getRelation());
                        if (EnumMethods.equalsTo(menuRouteGet.getRelation(), MenuRouteRelation.MENU_SELECTED)) {
                            menuGet.addMenuSelectedRoute(route);
                        } else if (EnumMethods.equalsTo(menuRouteGet.getRelation(), MenuRouteRelation.MENU_CLICK_TO_ROUTE)) {
                            if (menuGet.getMenuClickToRoute() != null) {
                                ExceptionUtil.throwFailException("菜单 " + menuGet.getName() + "拥有多个点击点击跳转路由 menuId：" + menuGet.getId());
                            }
                            menuGet.setMenuClickToRoute(route);
                        }
                    }
                }
                roleMenuList = roleMenusMap.get(menuGet.getId());
                if (!StringUtil.isEmpty(roleMenuList)) {
                    for (AutRoleMenuGet autRoleMenuGet : roleMenuList) {
                        role = new AutRole();
                        role.setId(autRoleMenuGet.getRoleId());
                        role.setName(autRoleMenuGet.getRoleName());
                        role.setValue(autRoleMenuGet.getRoleValue());
                        role.setType(autRoleMenuGet.getRoleType());
                        menuGet.addRole(role);
                    }
                }
                // 加入到缓存
                menuCache.put(menuGet.getId(), menuGet);
                if ("".equals(menuGet.getParentId())) {
                    // 根菜单
                    rootMenus.add(menuGet);
                } else {
                    //子菜单
                    subMenus.add(menuGet);
                }
            }
        }
        // 将子菜单加入到父级菜单中
        for (MenuGet subMenu : subMenus) {
            menuCache.get(subMenu.getParentId()).addSubMenu(subMenu);
        }
        return new EntityMessageView<>(rootMenus, new HttpResultInfo(HttpStatus.OK));
    }

    @Override
    @RequestMapping(value = "/post/menu")
    public DataView postMenu(MenuPost record, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        MenuGet menu = this.menuService.postMenu(moduleId, record);
        return DataViewUtil.getModelViewSuccess(Collections.singletonMap("menu", menu));
    }

    @Override
    @RequestMapping(value = "/put/menuByMenuId/{menuId}")
    public DataView putMenuByMenuId(@PathVariable String menuId, MenuPut record, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.menuService.putMenuByMenuId(moduleId, menuId, record);
        return DataViewUtil.getMessageViewSuccess("修改菜单成功");
    }

    @Override
    @RequestMapping(value = "/put/switchMenuIndexByMenuId/{sourceMenuId}/{targetMenuId}")
    public DataView putSwitchMenuIndexByMenuId(@PathVariable String sourceMenuId, @PathVariable String targetMenuId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.menuService.putSwitchMenuIndexByMenuId(moduleId, sourceMenuId, targetMenuId);
        return DataViewUtil.getMessageViewSuccess("操作成功");
    }

    @Override
    @RequestMapping(value = "/delete/menuByMenuId/{menuId}")
    public DataView deleteMenuByMenuId(@PathVariable String menuId, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.menuService.deleteMenuByMenuId(moduleId, menuId);
        return DataViewUtil.getMessageViewSuccess("删除菜单成功");
    }

    @Override
    @RequestMapping(value = "/delete/list/menuByMenuIds/{menuIds}")
    public DataView deleteListMenuByMenuIds(@PathVariable String menuIds, String moduleId) throws Exception {
        moduleId = TableUtils.getModuleId(moduleId, request);
        this.menuService.deleteListMenuByMenuIds(moduleId, Arrays.asList(menuIds.split(",")));
        return DataViewUtil.getMessageViewSuccess("删除菜单成功");
    }

    @Override
    @RequestMapping(value = "/put/dragMenuTreeNode/{dragMenuId}/{dropMenuId}/{dropType}")
    public DataView putDragMenuTreeNode(@PathVariable String dragMenuId, @PathVariable String dropMenuId, @PathVariable String dropType, MenuDragParams record, String moduleId) throws Exception {
        if (StringUtil.isEmpty(record.getDragSubModuleId())) {
            ExceptionUtil.throwFailException("拖拽节点所属子模块ID不能为空");
        }
        if (StringUtil.isEmpty(record.getDropSubModuleId())) {
            ExceptionUtil.throwFailException("放置节点所属子模块ID不能为空");
        }
        if (StringUtil.isEmpty(record.getDragMenuGroupId())) {
            ExceptionUtil.throwFailException("拖拽节点所属菜单组ID不能为空");
        }
        if (StringUtil.isEmpty(record.getDropMenuGroupId())) {
            ExceptionUtil.throwFailException("放置节点所属菜单组ID不能为空");
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
        MenuTreeNode dragNode = this.jdbcEngine.queryByPrimaryKey(dragMenuId, MenuTreeNode.class, MySqlDynamicEngine.query(MenuModel.class));
        if (dragNode == null) {
            ExceptionUtil.throwFailException("拖拽路由不存在");
        }
        MenuTreeNode dropNode = this.jdbcEngine.queryByPrimaryKey(dropMenuId, MenuTreeNode.class, MySqlDynamicEngine.query(MenuModel.class));
        if (dropNode == null) {
            ExceptionUtil.throwFailException("放置路由不存在");
        }
        this.menuNodeDraggableService.draggableNode(dragNode, dropNode, dt, record);
        return DataViewUtil.getMessageViewSuccess("拖拽成功");
    }

}
