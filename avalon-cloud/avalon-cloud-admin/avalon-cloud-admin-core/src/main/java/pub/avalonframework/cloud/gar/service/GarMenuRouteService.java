package pub.avalonframework.cloud.gar.service;

import pub.avalonframework.cloud.gar.beans.MenuRoutePut;
import pub.avalonframework.cloud.gar.dc.MenuRouteGet;
import pub.avalonframework.cloud.gar.dc.MenuRoutePost;
import pub.avalonframework.cloud.gar.entity.MenuRoute;

import java.util.List;
import java.util.Set;

/**
 * @author 白超
 * @date 2018/7/23
 */
public interface GarMenuRouteService {

    /**
     * 根据菜单ID、路由ID新增菜单路由
     *
     * @param moduleId
     * @param menuId
     * @param routeId
     * @param record
     * @return
     * @throws Exception
     */
    MenuRouteGet postMenuRouteByMenuIdAndRouteId(String moduleId, String menuId, String routeId, MenuRoutePost record) throws Exception;

    /**
     * 根据路由ID修改菜单路由数据
     *
     * @param moduleId
     * @param menuRouteId
     * @param record
     * @throws Exception
     */
    void putMenuRouteByMenuRouteId(String moduleId, String menuRouteId, MenuRoutePut record) throws Exception;

    /**
     * 根据菜单ID、路由ID修改菜单路由数据
     *
     * @param moduleId
     * @param menuId
     * @param routeId
     * @param record
     * @throws Exception
     */
    void putMenuRouteByMenuIdAndRouteId(String moduleId, String menuId, String routeId, MenuRoutePut record) throws Exception;

    /**
     * 根据路由ID批量修改菜单路由数据
     *
     * @param moduleId
     * @param routeId
     * @param menuRouteUpdate
     * @throws Exception
     */
    void putListMenuRouteByRouteId(String moduleId, String routeId, MenuRoute menuRouteUpdate) throws Exception;

    /**
     * 根据菜单ID批量修改菜单路由数据
     *
     * @param moduleId
     * @param menuId
     * @param menuRouteUpdate
     * @throws Exception
     */
    void putListMenuRouteByMenuId(String moduleId, String menuId, MenuRoute menuRouteUpdate) throws Exception;

    /**
     * 根据菜单组ID批量删除菜单路由数据
     *
     * @param moduleId
     * @param menuGroupId
     * @throws Exception
     */
    void deleteListMenuRouteByMenuGroupId(String moduleId, String menuGroupId) throws Exception;

    /**
     * 根据菜单组ID批量删除菜单路由数据
     *
     * @param moduleId
     * @param menuGroupIds
     * @throws Exception
     */
    void deleteListMenuRouteByMenuGroupIds(String moduleId, List<String> menuGroupIds) throws Exception;

    /**
     * 根据菜单ID批量删除菜单路由数据
     *
     * @param moduleId
     * @param menuId
     * @throws Exception
     */
    void deleteListMenuRouteByMenuId(String moduleId, String menuId) throws Exception;

    /**
     * 根据菜单ID批量删除菜单路由数据
     *
     * @param moduleId
     * @param menuIds
     * @throws Exception
     */
    void deleteListMenuRouteByMenuIds(String moduleId, List<String> menuIds) throws Exception;

    /**
     * 根据路由ID批量删除菜单路由数据
     *
     * @param moduleId
     * @param routeId
     * @throws Exception
     */
    void deleteListMenuRouteByRouteId(String moduleId, String routeId) throws Exception;

    /**
     * 根据路由ID批量删除菜单路由数据
     *
     * @param moduleId
     * @param routeIds
     * @throws Exception
     */
    void deleteListMenuRouteByRouteIds(String moduleId, List<String> routeIds) throws Exception;

    /**
     * 根据菜单路由ID删除菜单路由数据
     *
     * @param moduleId
     * @param menuRouteId
     * @throws Exception
     */
    void deleteMenuRouteByMenuRouteId(String moduleId, String menuRouteId) throws Exception;

    /**
     * 根据菜单路由ID批量删除菜单路由数据
     *
     * @param moduleId
     * @param menuRouteIds
     * @throws Exception
     */
    void deleteListMenuRouteByMenuRouteIds(String moduleId, Set<String> menuRouteIds) throws Exception;

    /**
     * 根据菜单ID、路由ID删除菜单路由数据
     *
     * @param moduleId
     * @param menuId
     * @param routeId
     * @throws Exception
     */
    void deleteMenuRouteByMenuIdAndRouteId(String moduleId, String menuId, String routeId) throws Exception;

    /**
     * 根据菜单ID、路由ID、菜单路由关系删除菜单路由数据
     *
     * @param moduleId
     * @param menuId
     * @param routeId
     * @param menuRouteRelation
     * @throws Exception
     */
    void deleteMenuRouteByMenuIdAndRouteIdAndMenuRouteRelation(String moduleId, String menuId, String routeId, String menuRouteRelation) throws Exception;

    /**
     * 根据菜单ID、路由ID批量删除菜单路由数据
     *
     * @param moduleId
     * @param menuIds
     * @param routeIds
     * @throws Exception
     */
    void deleteListMenuRouteByMenuIdsAndRouteIds(String moduleId, Set<String> menuIds, Set<String> routeIds) throws Exception;
}