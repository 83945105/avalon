package pub.avalonframework.cloud.gar.api;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalonframework.cloud.gar.dc.AutRoleMenuGet;

/**
 * 角色菜单接口
 *
 * @author 白超
 */
@FeignClient(name = "${feign.gar.role-menu-api-service-name:gar-service}", path = "${feign.gar.role-menu-api-service-path:/api-gar-role-menu}")
public interface GarRoleMenuApi {

    String ROOT_PATH = "/gar/api-gar-role-menu";

    /**
     * 条件查询角色菜单数据
     *
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/roleMenu")
    @RequestLine("GET " + ROOT_PATH + "/get/list/roleMenu?moduleId={moduleId}")
    DataView getListRoleMenu(@RequestParam("record") @QueryMap AutRoleMenuGet record,
                             @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据角色ID条件查询角色菜单数据
     *
     * @param roleId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/roleMenuByRoleId/{roleId}")
    @RequestLine("GET " + ROOT_PATH + "/get/list/roleMenuByRoleId/{roleId}?moduleId={moduleId}")
    DataView getListRoleMenuByRoleId(@PathVariable("roleId") @Param("roleId") String roleId,
                                     @RequestParam("record") @QueryMap AutRoleMenuGet record,
                                     @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据菜单ID条件查询角色菜单数据
     *
     * @param menuId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/roleMenuByMenuId/{menuId}")
    @RequestLine("GET " + ROOT_PATH + "/get/list/roleMenuByMenuId/{menuId}?moduleId={moduleId}")
    DataView getListRoleMenuByMenuId(@PathVariable("menuId") @Param("menuId") String menuId,
                                     @RequestParam("record") @QueryMap AutRoleMenuGet record,
                                     @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据角色ID、菜单ID新增角色菜单数据
     *
     * @param roleId
     * @param menuId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/post/roleMenuByRoleIdAndMenuId/{roleId}/{menuId}")
    @RequestLine("POST " + ROOT_PATH + "/post/roleMenuByRoleIdAndMenuId/{roleId}/{menuId}?moduleId={moduleId}")
    DataView postRoleMenuByRoleIdAndMenuId(@PathVariable("roleId") @Param("roleId") String roleId,
                                           @PathVariable("menuId") @Param("menuId") String menuId,
                                           @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据角色菜单ID删除角色菜单数据
     *
     * @param roleMenuId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/roleMenuByRoleMenuId/{roleMenuId}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/roleMenuByRoleMenuId/{roleMenuId}?moduleId={moduleId}")
    DataView deleteRoleMenuByRoleMenuId(@PathVariable("roleMenuId") @Param("roleMenuId") String roleMenuId,
                                        @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据角色菜单ID批量删除角色菜单数据
     *
     * @param roleMenuIds
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/list/roleMenuByRoleMenuIds/{roleMenuIds}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/list/roleMenuByRoleMenuIds/{roleMenuIds}?moduleId={moduleId}")
    DataView deleteListRoleMenuByRoleMenuIds(@PathVariable("roleMenuIds") @Param("roleMenuIds") String roleMenuIds,
                                             @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;


    /**
     * 根据角色ID、菜单ID删除角色菜单数据
     *
     * @param roleId
     * @param menuId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/roleMenuByRoleIdAndMenuId/{roleId}/{menuId}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/roleMenuByRoleIdAndMenuId/{roleId}/{menuId}?moduleId={moduleId}")
    DataView deleteRoleMenuByRoleIdAndMenuId(@PathVariable("roleId") @Param("roleId") String roleId,
                                             @PathVariable("menuId") @Param("menuId") String menuId,
                                             @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据角色ID、菜单ID批量删除角色菜单数据
     *
     * @param roleIds
     * @param menuIds
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/list/roleMenuByRoleIdsAndMenuIds/{roleIds}/{menuIds}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/list/roleMenuByRoleIdsAndMenuIds/{roleIds}/{menuIds}?moduleId={moduleId}")
    DataView deleteListRoleMenuByRoleIdsAndMenuIds(@PathVariable("roleIds") @Param("roleIds") String roleIds,
                                                   @PathVariable("menuIds") @Param("menuIds") String menuIds,
                                                   @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

}
