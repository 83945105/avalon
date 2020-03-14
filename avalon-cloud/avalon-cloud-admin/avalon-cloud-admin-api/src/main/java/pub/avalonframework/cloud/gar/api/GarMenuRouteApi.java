package pub.avalonframework.cloud.gar.api;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalonframework.cloud.gar.beans.MenuRoutePut;
import pub.avalonframework.cloud.gar.dc.MenuRouteGet;
import pub.avalonframework.cloud.gar.dc.MenuRoutePost;

/**
 * 菜单路由接口
 *
 * @author 白超
 */
@FeignClient(name = "${feign.gar.menu-route-api-service-name:gar-service}", path = "${feign.gar.menu-route-api-service-path:/api-gar-menu-route}")
public interface GarMenuRouteApi {

    String ROOT_PATH = "/gar/api-gar-menu-route";

    /**
     * 条件查询菜单路由数据
     *
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/menuRoute")
    @RequestLine("GET " + ROOT_PATH + "/get/list/menuRoute?moduleId={moduleId}")
    DataView getListMenuRoute(@RequestParam("record") @QueryMap MenuRouteGet record,
                              @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据菜单ID条件查询菜单路由数据
     *
     * @param menuId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/menuRouteByMenuId/{menuId}")
    @RequestLine("GET " + ROOT_PATH + "/get/list/menuRouteByMenuId/{menuId}?moduleId={moduleId}")
    DataView getListMenuRouteByMenuId(@PathVariable("menuId") @Param("menuId") String menuId,
                                      @RequestParam("record") @QueryMap MenuRouteGet record,
                                      @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据路由ID条件查询菜单路由数据
     *
     * @param routeId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/menuRouteByRouteId/{routeId}")
    @RequestLine("GET " + ROOT_PATH + "/get/list/menuRouteByRouteId/{routeId}?moduleId={moduleId}")
    DataView getListMenuRouteByRouteId(@PathVariable("routeId") @Param("routeId") String routeId,
                                       @RequestParam("record") @QueryMap MenuRouteGet record,
                                       @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据菜单ID、路由ID新增菜单路由数据
     *
     * @param menuId
     * @param routeId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/post/menuRouteByMenuIdAndRouteId/{menuId}/{routeId}")
    @RequestLine("POST " + ROOT_PATH + "/post/menuRouteByMenuIdAndRouteId/{menuId}/{routeId}?moduleId={moduleId}")
    DataView postMenuRouteByMenuIdAndRouteId(@PathVariable("menuId") @Param("menuId") String menuId,
                                             @PathVariable("routeId") @Param("routeId") String routeId,
                                             @RequestParam("record") @QueryMap MenuRoutePost record,
                                             @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据菜单路由ID修改菜单路由数据
     *
     * @param menuRouteId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/put/menuRouteByMenuRouteId/{menuRouteId}")
    @RequestLine("PUT " + ROOT_PATH + "/put/menuRouteByMenuRouteId/{menuRouteId}?moduleId={moduleId}")
    DataView putMenuRouteByMenuRouteId(@PathVariable("menuRouteId") @Param("menuRouteId") String menuRouteId,
                                       @RequestParam("record") @QueryMap MenuRoutePut record,
                                       @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据菜单ID、路由ID修改菜单路由数据
     *
     * @param menuId
     * @param routeId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/put/menuRouteByMenuIdAndRouteId/{menuId}/{routeId}")
    @RequestLine("PUT " + ROOT_PATH + "/put/menuRouteByMenuIdAndRouteId/{menuId}/{routeId}?moduleId={moduleId}")
    DataView putMenuRouteByMenuIdAndRouteId(@PathVariable("menuId") @Param("menuId") String menuId,
                                            @PathVariable("routeId") @Param("routeId") String routeId,
                                            @RequestParam("record") @QueryMap MenuRoutePut record,
                                            @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据菜单路由ID删除菜单路由数据
     *
     * @param menuRouteId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/menuRouteByMenuRouteId/{menuRouteId}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/menuRouteByMenuRouteId/{menuRouteId}?moduleId={moduleId}")
    DataView deleteMenuRouteByMenuRouteId(@PathVariable("menuRouteId") @Param("menuRouteId") String menuRouteId,
                                          @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据菜单路由ID批量删除菜单路由数据
     *
     * @param menuRouteIds
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/list/menuRouteByMenuRouteIds/{menuRouteIds}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/list/menuRouteByMenuRouteIds/{menuRouteIds}?moduleId={moduleId}")
    DataView deleteListMenuRouteByMenuRouteIds(@PathVariable("menuRouteIds") @Param("menuRouteIds") String menuRouteIds,
                                               @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;


    /**
     * 根据菜单ID、路由ID删除菜单路由数据
     *
     * @param menuId
     * @param routeId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/menuRouteByMenuIdAndRouteId/{menuId}/{routeId}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/menuRouteByMenuIdAndRouteId/{menuId}/{routeId}?moduleId={moduleId}")
    DataView deleteMenuRouteByMenuIdAndRouteId(@PathVariable("menuId") @Param("menuId") String menuId,
                                               @PathVariable("routeId") @Param("routeId") String routeId,
                                               @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据菜单ID、路由ID、菜单路由关系删除菜单路由数据
     *
     * @param menuId
     * @param routeId
     * @param menuRouteRelation
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/menuRouteByMenuIdAndRouteIdAndMenuRouteRelation/{menuId}/{routeId}/{menuRouteRelation}")
    @RequestLine("GET " + ROOT_PATH + "/delete/menuRouteByMenuIdAndRouteIdAndMenuRouteRelation/{menuId}/{routeId}/{menuRouteRelation}")
    DataView deleteMenuRouteByMenuIdAndRouteIdAndMenuRouteRelation(@PathVariable("menuId") @Param("menuId") String menuId,
                                                                   @PathVariable("routeId") @Param("routeId") String routeId,
                                                                   @PathVariable("menuRouteRelation") @Param("menuRouteRelation") String menuRouteRelation,
                                                                   @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据菜单ID、路由ID批量删除菜单路由数据
     *
     * @param menuIds
     * @param routeIds
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/list/menuRouteByMenuIdsAndRouteIds/{menuIds}/{routeIds}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/list/menuRouteByMenuIdsAndRouteIds/{menuIds}/{routeIds}?moduleId={moduleId}")
    DataView deleteListMenuRouteByMenuIdsAndRouteIds(@PathVariable("menuIds") @Param("menuIds") String menuIds,
                                                     @PathVariable("routeIds") @Param("routeIds") String routeIds,
                                                     @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

}
