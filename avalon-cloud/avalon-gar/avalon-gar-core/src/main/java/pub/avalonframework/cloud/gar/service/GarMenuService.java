package pub.avalonframework.cloud.gar.service;

import pub.avalonframework.cloud.gar.dc.MenuGet;
import pub.avalonframework.cloud.gar.dc.MenuPost;
import pub.avalonframework.cloud.gar.dc.MenuPut;
import pub.avalonframework.cloud.gar.entity.Menu;

import java.util.List;

/**
 * @author 白超
 * @date 2018/12/6
 */
public interface GarMenuService {

    /**
     * 查询菜单唯一标识符是否可用
     *
     * @param moduleId
     * @param subModuleId
     * @param value
     * @param excludeValues
     * @return
     * @throws Exception
     */
    boolean getValidateValueCanUseBySubModuleId(String moduleId, String subModuleId, String value, List<String> excludeValues) throws Exception;

    /**
     * 新增菜单
     *
     * @param moduleId
     * @param record
     * @return
     * @throws Exception
     */
    MenuGet postMenu(String moduleId, MenuPost record) throws Exception;

    /**
     * 根据菜单ID修改菜单数据
     *
     * @param moduleId
     * @param menuId
     * @param record
     * @return
     * @throws Exception
     */
    void putMenuByMenuId(String moduleId, String menuId, MenuPut record) throws Exception;

    /**
     * 根据模块ID、子模块ID批量修改菜单数据
     *
     * @param moduleId
     * @param subModuleId
     * @param menuUpdate
     * @throws Exception
     */
    void putListMenuByModuleIdAndSubModuleId(String moduleId, String subModuleId, Menu menuUpdate) throws Exception;

    /**
     * 根据菜单ID交换菜单index属性(排序用)
     *
     * @param moduleId
     * @param sourceMenuId
     * @param targetMenuId
     * @throws Exception
     */
    void putSwitchMenuIndexByMenuId(String moduleId, String sourceMenuId, String targetMenuId) throws Exception;

    /**
     * 根据菜单ID删除菜单数据
     *
     * @param moduleId
     * @param menuId
     * @return
     * @throws Exception
     */
    void deleteMenuByMenuId(String moduleId, String menuId) throws Exception;

    /**
     * 根据子模块ID批量删除菜单数据
     *
     * @param moduleId
     * @param subModuleId
     * @throws Exception
     */
    void deleteListMenuBySubModuleId(String moduleId, String subModuleId) throws Exception;

    /**
     * 根据子模块ID批量删除菜单数据
     *
     * @param moduleId
     * @param subModuleIds
     * @throws Exception
     */
    void deleteListMenuBySubModuleIds(String moduleId, List<String> subModuleIds) throws Exception;

    /**
     * 根据菜单组ID批量删除菜单数据
     *
     * @param moduleId
     * @param menuGroupId
     * @throws Exception
     */
    void deleteListMenuByMenuGroupId(String moduleId, String menuGroupId) throws Exception;

    /**
     * 根据菜单组ID批量删除菜单数据
     *
     * @param moduleId
     * @param menuGroupIds
     * @throws Exception
     */
    void deleteListMenuByMenuGroupIds(String moduleId, List<String> menuGroupIds) throws Exception;


    /**
     * 根据菜单ID批量删除菜单数据
     *
     * @param moduleId
     * @param menuIds
     * @return
     * @throws Exception
     */
    void deleteListMenuByMenuIds(String moduleId, List<String> menuIds) throws Exception;
}