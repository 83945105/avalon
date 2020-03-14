package pub.avalonframework.cloud.gar.service;

import pub.avalonframework.cloud.gar.dc.MenuGroupGet;
import pub.avalonframework.cloud.gar.dc.MenuGroupPost;
import pub.avalonframework.cloud.gar.dc.MenuGroupPut;

import java.util.List;

/**
 * @author 白超
 */
public interface GarMenuGroupService {

    /**
     * 新增菜单组
     *
     * @param moduleId
     * @param record
     * @return
     * @throws Exception
     */
    MenuGroupGet postMenuGroup(String moduleId, MenuGroupPost record) throws Exception;

    /**
     * 根据菜单组ID修改菜单组数据
     *
     * @param moduleId
     * @param menuGroupId
     * @param record
     * @return
     * @throws Exception
     */
    void putMenuGroupByMenuGroupId(String moduleId, String menuGroupId, MenuGroupPut record) throws Exception;

    /**
     * 根据菜单组ID修改菜单组是否使用
     *
     * @param moduleId
     * @param menuGroupId
     * @param menuGroupUse
     * @throws Exception
     */
    void putMenuGroupUseByMenuGroupId(String moduleId, String menuGroupId, String menuGroupUse) throws Exception;

    /**
     * 根据菜单组ID交换菜单组index属性(排序用)
     *
     * @param moduleId
     * @param sourceMenuGroupId
     * @param targetMenuGroupId
     * @throws Exception
     */
    void putSwitchMenuGroupIndexByMenuGroupId(String moduleId, String sourceMenuGroupId, String targetMenuGroupId) throws Exception;

    /**
     * 根据菜单组ID删除菜单组数据
     *
     * @param moduleId
     * @param menuGroupId
     * @return
     * @throws Exception
     */
    void deleteMenuGroupByMenuGroupId(String moduleId, String menuGroupId) throws Exception;

    /**
     * 根据子模块ID批量删除菜单组数据
     *
     * @param moduleId
     * @param subModuleId
     * @throws Exception
     */
    void deleteListMenuGroupBySubModuleId(String moduleId, String subModuleId) throws Exception;

    /**
     * 根据子模块ID批量删除菜单组数据
     *
     * @param moduleId
     * @param subModuleIds
     * @throws Exception
     */
    void deleteListMenuGroupBySubModuleIds(String moduleId, List<String> subModuleIds) throws Exception;


    /**
     * 根据菜单组ID批量删除菜单组数据
     *
     * @param moduleId
     * @param menuGroupIds
     * @return
     * @throws Exception
     */
    void deleteListMenuGroupByMenuGroupIds(String moduleId, List<String> menuGroupIds) throws Exception;

}
