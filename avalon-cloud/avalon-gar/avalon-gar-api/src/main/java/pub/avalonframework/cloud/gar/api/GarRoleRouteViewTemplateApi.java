package pub.avalonframework.cloud.gar.api;

import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pub.avalon.holygrail.response.views.DataView;

/**
 * 角色路由视图模板接口
 *
 * @author 白超
 * @date 2018/7/23
 */
@FeignClient(name = "${feign.gar.role-route-view-template-api-service-name:gar-service}", path = "${feign.gar.role-route-view-template-api-service-path:/api-gar-role-route-view-template}")
public interface GarRoleRouteViewTemplateApi {

    String ROOT_PATH = "/api-gar-role-route-view-template";

    /**
     * 根据路由视图ID查询该路由视图下已经授予的角色
     *
     * @param routeViewId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/roleByRouteViewId/{routeViewId}")
    @RequestLine("GET " + ROOT_PATH + "/get/list/roleByRouteViewId/{routeViewId}?moduleId={moduleId}")
    DataView getListRoleByRouteViewId(@PathVariable("routeViewId") @Param("routeViewId") String routeViewId,
                                      @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据角色ID、路由视图ID、模板ID新增角色路由视图模板数据
     *
     * @param roleId
     * @param routeViewId
     * @param templateId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/post/roleRouteViewTemplateByRoleIdAndRouteViewIdAndTemplateId/{roleId}/{routeViewId}/{templateId}")
    @RequestLine("POST " + ROOT_PATH + "/post/roleRouteViewTemplateByRoleIdAndRouteViewIdAndTemplateId/{roleId}/{routeViewId}/{templateId}?moduleId={moduleId}")
    DataView postRoleRouteViewTemplateByRoleIdAndRouteViewIdAndTemplateId(@PathVariable("roleId") @Param("roleId") String roleId,
                                                                          @PathVariable("routeViewId") @Param("routeViewId") String routeViewId,
                                                                          @PathVariable("templateId") @Param("templateId") String templateId,
                                                                          @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据角色ID(多条)、路由视图ID、模板ID批量新增角色路由视图模板数据
     *
     * @param roleIds
     * @param routeViewId
     * @param templateId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/post/roleRouteViewTemplateByRoleIdsAndRouteViewIdAndTemplateId/{roleIds}/{routeViewId}/{templateId}")
    @RequestLine("POST " + ROOT_PATH + "/post/roleRouteViewTemplateByRoleIdsAndRouteViewIdAndTemplateId/{roleIds}/{routeViewId}/{templateId}?moduleId={moduleId}")
    DataView postRoleRouteViewTemplateByRoleIdsAndRouteViewIdAndTemplateId(@PathVariable("roleIds") @Param("roleIds") String roleIds,
                                                                           @PathVariable("routeViewId") @Param("routeViewId") String routeViewId,
                                                                           @PathVariable("templateId") @Param("templateId") String templateId,
                                                                           @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据角色路由视图模板ID删除角色路由视图模板数据
     *
     * @param roleRouteViewTemplateId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/roleRouteViewTemplateByRoleRouteViewTemplateId/{roleRouteViewTemplateId}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/roleRouteViewTemplateByRoleRouteViewTemplateId/{roleRouteViewTemplateId}?moduleId={moduleId}")
    DataView deleteRoleRouteViewTemplateByRoleRouteViewTemplateId(@PathVariable("roleRouteViewTemplateId") @Param("roleRouteViewTemplateId") String roleRouteViewTemplateId,
                                                                  @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据角色路由视图模板ID批量删除角色路由视图模板数据
     *
     * @param roleRouteViewTemplateIds
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/list/roleRouteViewTemplateByRoleRouteViewTemplateIds/{roleRouteViewTemplateIds}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/list/roleRouteViewTemplateByRoleRouteViewTemplateIds/{roleRouteViewTemplateIds}?moduleId={moduleId}")
    DataView deleteListRoleRouteViewTemplateByRoleRouteViewTemplateIds(@PathVariable("roleRouteViewTemplateIds") @Param("roleRouteViewTemplateIds") String roleRouteViewTemplateIds,
                                                                       @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;


    /**
     * 根据角色ID、路由视图ID、模板ID删除路由视图模板数据
     *
     * @param roleId
     * @param routeViewId
     * @param templateId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/roleRouteViewTemplateByRoleIdAndRouteViewIdAndTemplateId/{roleId}/{routeViewId}/{templateId}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/roleRouteViewTemplateByRoleIdAndRouteViewIdAndTemplateId/{roleId}/{routeViewId}/{templateId}?moduleId={moduleId}")
    DataView deleteRoleRouteViewTemplateByRoleIdAndRouteViewIdAndTemplateId(@PathVariable("roleId") @Param("roleId") String roleId,
                                                                            @PathVariable("routeViewId") @Param("routeViewId") String routeViewId,
                                                                            @PathVariable("templateId") @Param("templateId") String templateId,
                                                                            @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

}
