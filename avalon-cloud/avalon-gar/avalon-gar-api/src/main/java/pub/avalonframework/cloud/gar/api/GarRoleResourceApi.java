package pub.avalonframework.cloud.gar.api;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalonframework.cloud.gar.dc.AutRoleResourceGet;

/**
 * 角色资源接口
 *
 * @author 白超
 * @date 2018/7/23
 */
@FeignClient(name = "${feign.gar.role-resource-api-service-name:gar-service}", path = "${feign.gar.role-resource-api-service-path:/api-gar-role-resource}")
public interface GarRoleResourceApi {

    String ROOT_PATH = "/api-gar-role-resource";

    /**
     * 根据角色ID条件查询角色资源数据
     *
     * @param roleId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/roleResourceByRoleId/{roleId}")
    @RequestLine("GET " + ROOT_PATH + "/get/list/roleResourceByRoleId/{roleId}?moduleId={moduleId}")
    DataView getListRoleResourceByRoleId(@PathVariable("roleId") @Param("roleId") String roleId,
                                         @RequestParam("record") @QueryMap AutRoleResourceGet record,
                                         @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据资源ID条件查询角色资源数据
     *
     * @param resourceId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/roleResourceByResourceId/{resourceId}")
    @RequestLine("GET " + ROOT_PATH + "/get/list/roleResourceByResourceId/{resourceId}?moduleId={moduleId}")
    DataView getListRoleResourceByResourceId(@PathVariable("resourceId") @Param("resourceId") String resourceId,
                                             @RequestParam("record") @QueryMap AutRoleResourceGet record,
                                             @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据角色ID、资源ID新增角色资源数据
     *
     * @param roleId
     * @param resourceId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/post/roleResourceByRoleIdAndResourceId/{roleId}/{resourceId}")
    @RequestLine("POST " + ROOT_PATH + "/post/roleResourceByRoleIdAndResourceId/{roleId}/{resourceId}?moduleId={moduleId}")
    DataView postRoleResourceByRoleIdAndResourceId(@PathVariable("roleId") @Param("roleId") String roleId,
                                                   @PathVariable("resourceId") @Param("resourceId") String resourceId,
                                                   @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据角色ID、资源ID批量新增角色资源数据
     *
     * @param roleIds
     * @param resourceIds
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/post/list/roleResourceByRoleIdsAndResourceIds/{roleIds}/{resourceIds}")
    @RequestLine("POST " + ROOT_PATH + "/post/list/roleResourceByRoleIdsAndResourceIds/{roleIds}/{resourceIds}?moduleId={moduleId}")
    DataView postListRoleResourceByRoleIdsAndResourceIds(@PathVariable("roleIds") @Param("roleIds") String roleIds,
                                                         @PathVariable("resourceIds") @Param("resourceIds") String resourceIds,
                                                         @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据角色资源ID删除角色资源数据
     *
     * @param roleResourceId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/roleResourceByRoleResourceId/{roleResourceId}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/roleResourceByRoleResourceId/{roleResourceId}?moduleId={moduleId}")
    DataView deleteRoleResourceByRoleResourceId(@PathVariable("roleResourceId") @Param("roleResourceId") String roleResourceId,
                                                @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据角色资源ID批量删除角色资源数据
     *
     * @param roleResourceIds
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/list/roleResourceByRoleResourceIds/{roleResourceIds}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/list/roleResourceByRoleResourceIds/{roleResourceIds}?moduleId={moduleId}")
    DataView deleteListRoleResourceByRoleResourceIds(@PathVariable("roleResourceIds") @Param("roleResourceIds") String roleResourceIds,
                                                     @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;


    /**
     * 根据角色ID、资源ID删除角色资源数据
     *
     * @param roleId
     * @param resourceId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/roleResourceByRoleIdAndResourceId/{roleId}/{resourceId}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/roleResourceByRoleIdAndResourceId/{roleId}/{resourceId}?moduleId={moduleId}")
    DataView deleteRoleResourceByRoleIdAndResourceId(@PathVariable("roleId") @Param("roleId") String roleId,
                                                     @PathVariable("resourceId") @Param("resourceId") String resourceId,
                                                     @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据角色ID、资源ID批量删除角色资源数据
     *
     * @param roleIds
     * @param resourceIds
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/list/roleResourceByRoleIdsAndResourceIds/{roleIds}/{resourceIds}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/list/roleResourceByRoleIdsAndResourceIds/{roleIds}/{resourceIds}?moduleId={moduleId}")
    DataView deleteListRoleResourceByRoleIdsAndResourceIds(@PathVariable("roleIds") @Param("roleIds") String roleIds,
                                                           @PathVariable("resourceIds") @Param("resourceIds") String resourceIds,
                                                           @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

}
