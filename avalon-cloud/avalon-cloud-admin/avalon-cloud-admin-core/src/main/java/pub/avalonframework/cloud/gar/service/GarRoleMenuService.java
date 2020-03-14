package pub.avalonframework.cloud.gar.service;

import pub.avalonframework.cloud.gar.dc.AutRoleMenuGet;
import pub.avalonframework.cloud.gar.entity.AutRoleMenu;

import java.util.List;
import java.util.Set;

/**
 * @author 白超
 */
public interface GarRoleMenuService {

    /**
     * 根据角色ID、菜单ID新增角色菜单
     *
     * @param moduleId
     * @param roleId
     * @param menuId
     * @return
     * @throws Exception
     */
    AutRoleMenuGet postRoleMenuByRoleIdAndMenuId(String moduleId, String roleId, String menuId) throws Exception;

    /**
     * 根据角色ID批量修改角色菜单数据
     *
     * @param moduleId
     * @param roleId
     * @param roleMenuUpdate
     * @throws Exception
     */
    void putListRoleMenuByRoleId(String moduleId, String roleId, AutRoleMenu roleMenuUpdate) throws Exception;

    /**
     * 根据菜单ID批量修改角色菜单数据
     *
     * @param moduleId
     * @param menuId
     * @param roleMenuUpdate
     * @throws Exception
     */
    void putListRoleMenuByMenuId(String moduleId, String menuId, AutRoleMenu roleMenuUpdate) throws Exception;

    /**
     * 根据菜单组ID批量删除角色菜单数据
     *
     * @param moduleId
     * @param menuGroupId
     * @throws Exception
     */
    void deleteListRoleMenuByMenuGroupId(String moduleId, String menuGroupId) throws Exception;

    /**
     * 根据菜单组ID批量删除角色菜单数据
     *
     * @param moduleId
     * @param menuGroupIds
     * @throws Exception
     */
    void deleteListRoleMenuByMenuGroupIds(String moduleId, List<String> menuGroupIds) throws Exception;

    /**
     * 根据角色ID批量删除角色菜单数据
     *
     * @param moduleId
     * @param roleId
     * @throws Exception
     */
    void deleteListRoleMenuByRoleId(String moduleId, String roleId) throws Exception;

    /**
     * 根据角色ID批量删除角色菜单数据
     *
     * @param moduleId
     * @param roleIds
     * @throws Exception
     */
    void deleteListRoleMenuByRoleIds(String moduleId, List<String> roleIds) throws Exception;

    /**
     * 根据菜单ID批量删除角色菜单数据
     *
     * @param moduleId
     * @param menuId
     * @throws Exception
     */
    void deleteListRoleMenuByMenuId(String moduleId, String menuId) throws Exception;

    /**
     * 根据菜单ID批量删除角色菜单数据
     *
     * @param moduleId
     * @param menuIds
     * @throws Exception
     */
    void deleteListRoleMenuByMenuIds(String moduleId, List<String> menuIds) throws Exception;

    /**
     * 根据角色菜单ID删除角色菜单数据
     *
     * @param moduleId
     * @param roleMenuId
     * @throws Exception
     */
    void deleteRoleMenuByRoleMenuId(String moduleId, String roleMenuId) throws Exception;

    /**
     * 根据角色菜单ID批量删除角色菜单数据
     *
     * @param moduleId
     * @param roleMenuIds
     * @throws Exception
     */
    void deleteListRoleMenuByRoleMenuIds(String moduleId, Set<String> roleMenuIds) throws Exception;

    /**
     * 根据角色ID、菜单ID删除角色菜单
     *
     * @param moduleId
     * @param roleId
     * @param menuId
     * @throws Exception
     */
    void deleteRoleMenuByRoleIdAndMenuId(String moduleId, String roleId, String menuId) throws Exception;

    /**
     * 根据角色ID、菜单ID批量删除角色菜单
     *
     * @param moduleId
     * @param roleIds
     * @param menuIds
     * @throws Exception
     */
    void deleteListRoleMenuByRoleIdsAndMenuIds(String moduleId, Set<String> roleIds, Set<String> menuIds) throws Exception;
}