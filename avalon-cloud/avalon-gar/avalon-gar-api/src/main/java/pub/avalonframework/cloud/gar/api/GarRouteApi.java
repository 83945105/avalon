package pub.avalonframework.cloud.gar.api;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalonframework.cloud.gar.beans.RouteDragParams;
import pub.avalonframework.cloud.gar.dc.RouteGet;
import pub.avalonframework.cloud.gar.dc.RoutePost;
import pub.avalonframework.cloud.gar.dc.RoutePut;

/**
 * 路由接口
 *
 * @author 白超
 * @date 2018/12/7
 */
@FeignClient(name = "${feign.gar.route-api-service-name:gar-service}", path = "${feign.gar.route-api-service-path:/api-gar-route}")
public interface GarRouteApi {

    String ROOT_PATH = "/api-gar-route";

    /**
     * 根据子模块ID查询路由唯一标识符是否可用
     *
     * @param value
     * @param subModuleId
     * @param excludeValues
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/validateValueCanUseBySubModuleId/{value}/{subModuleId}")
    @RequestLine("GET " + ROOT_PATH + "/get/validateValueCanUseBySubModuleId/{value}/{subModuleId}?excludeValues={excludeValues}&moduleId={moduleId}")
    DataView getValidateValueCanUseBySubModuleId(@PathVariable("value") @Param("value") String value,
                                                 @PathVariable("subModuleId") @Param("subModuleId") String subModuleId,
                                                 @RequestParam("excludeValues") @Param("excludeValues") String excludeValues,
                                                 @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据子模块ID查询路由地址是否可用
     *
     * @param path
     * @param subModuleId
     * @param excludePaths
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/validatePathCanUseBySubModuleId/{subModuleId}")
    @RequestLine("GET " + ROOT_PATH + "/get/validatePathCanUseBySubModuleId/{subModuleId}?path={path}&excludePaths={excludePaths}&moduleId={moduleId}")
    DataView getValidatePathCanUseBySubModuleId(@RequestParam("path") @Param("path") String path,
                                                @PathVariable("subModuleId") @Param("subModuleId") String subModuleId,
                                                @RequestParam("excludePaths") @Param("excludePaths") String excludePaths,
                                                @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据子模块ID查询路由别名是否可用
     *
     * @param alias
     * @param subModuleId
     * @param excludeAliases
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/validateAliasCanUseBySubModuleId/{alias}/{subModuleId}")
    @RequestLine("GET " + ROOT_PATH + "/get/validateAliasCanUseBySubModuleId/{alias}/{subModuleId}?excludeAliass={excludeAliases}&moduleId={moduleId}")
    DataView getValidateAliasCanUseBySubModuleId(@PathVariable("alias") @Param("alias") String alias,
                                                 @PathVariable("subModuleId") @Param("subModuleId") String subModuleId,
                                                 @RequestParam("excludeAliases") @Param("excludeAliases") String excludeAliases,
                                                 @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据路由ID获取路由详情
     *
     * @param routeId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/routeByRouteId/{routeId}")
    @RequestLine("GET " + ROOT_PATH + "/get/routeByRouteId/{routeId}?moduleId={moduleId}")
    DataView getRouteByRouteId(@PathVariable("routeId") @Param("routeId") String routeId,
                               @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 条件查询路由集合
     *
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/route")
    @RequestLine("GET " + ROOT_PATH + "/get/list/route?moduleId={moduleId}")
    DataView getListRoute(@RequestParam("record") @QueryMap RouteGet record,
                          @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 分页条件查询路由集合
     *
     * @param record
     * @param currentPage
     * @param pageSize
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/pageList/route")
    @RequestLine("GET " + ROOT_PATH + "/get/pageList/route?currentPage={currentPage}&pageSize={pageSize}&moduleId={moduleId}")
    DataView getPageListRoute(@RequestParam("record") @QueryMap RouteGet record,
                              @RequestParam("currentPage") @Param("currentPage") Integer currentPage,
                              @RequestParam("pageSize") @Param("pageSize") Integer pageSize,
                              @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据子模块ID条件查询路由集合
     *
     * @param subModuleId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/routeBySubModuleId/{subModuleId}")
    @RequestLine("GET " + ROOT_PATH + "/get/list/routeBySubModuleId/{subModuleId}")
    DataView getListRouteBySubModuleId(@PathVariable("subModuleId") @Param("subModuleId") String subModuleId,
                                       @RequestParam("record") @QueryMap RouteGet record,
                                       @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据子模块ID条件查询根路由集合
     *
     * @param subModuleId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/rootRouteBySubModuleId/{subModuleId}")
    @RequestLine("GET " + ROOT_PATH + "/get/list/rootRouteBySubModuleId/{subModuleId}")
    DataView getListRootRouteBySubModuleId(@PathVariable("subModuleId") @Param("subModuleId") String subModuleId,
                                           @RequestParam("record") @QueryMap RouteGet record,
                                           @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据父级路由ID条件查询路由集合
     *
     * @param parentRouteId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/routeByParentId/{parentRouteId}")
    @RequestLine("GET " + ROOT_PATH + "/get/list/routeByParentId/{parentRouteId}")
    DataView getListRouteByParentRouteId(@PathVariable("parentRouteId") @Param("parentRouteId") String parentRouteId,
                                         @RequestParam("record") @QueryMap RouteGet record,
                                         @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据子模块唯一标识符获取路由树数据
     *
     * @param subModuleValue
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/routeTreeBySubModuleValue/{subModuleValue}")
    @RequestLine("GET " + ROOT_PATH + "/get/routeTreeBySubModuleValue/{subModuleValue}?moduleId={moduleId}")
    DataView getRouteTreeBySubModuleValue(@PathVariable("subModuleValue") @Param("subModuleValue") String subModuleValue,
                                          @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 新增路由
     *
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/post/route")
    @RequestLine("POST " + ROOT_PATH + "/post/route?moduleId={moduleId}")
    DataView postRoute(@RequestParam("record") @QueryMap RoutePost record,
                       @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据路由ID修改路由数据
     *
     * @param routeId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/put/routeByRouteId/{routeId}")
    @RequestLine("PUT " + ROOT_PATH + "/put/routeByRouteId/{routeId}?moduleId={moduleId}")
    DataView putRouteByRouteId(@PathVariable("routeId") @Param("routeId") String routeId,
                               @RequestParam("record") @QueryMap RoutePut record,
                               @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据路由ID交换路由index属性(排序用)
     *
     * @param sourceRouteId
     * @param targetRouteId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/put/switchRouteIndexByRouteId/{sourceRouteId}/{targetRouteId}")
    @RequestLine("PUT " + ROOT_PATH + "/put/switchRouteIndexByRouteId/{sourceRouteId}/{targetRouteId}?moduleId={moduleId}")
    DataView putSwitchRouteIndexByRouteId(@PathVariable("sourceRouteId") @Param("sourceRouteId") String sourceRouteId,
                                          @PathVariable("targetRouteId") @Param("targetRouteId") String targetRouteId,
                                          @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据路由ID删除路由数据
     *
     * @param routeId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/routeByRouteId/{routeId}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/routeByRouteId/{routeId}?moduleId={moduleId}")
    DataView deleteRouteByRouteId(@PathVariable("routeId") @Param("routeId") String routeId,
                                  @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据路由ID批量删除路由数据
     *
     * @param routeIds
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/list/routeByRouteIds/{routeIds}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/list/routeByRouteIds/{routeIds}?moduleId={moduleId}")
    DataView deleteListRouteByRouteIds(@PathVariable("routeIds") @Param("routeIds") String routeIds,
                                       @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 拖拽路由树节点
     *
     * @param dragRouteId
     * @param dropRouteId
     * @param dropType
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/put/dragRouteTreeNode/{dragRouteId}/{dropRouteId}/{dropType}")
    @RequestLine("PUT " + ROOT_PATH + "/put/dragRouteTreeNode/{dragRouteId}/{dropRouteId}/{dropType}?moduleId={moduleId}")
    DataView putDragRouteTreeNode(@PathVariable("dragRouteId") @Param("dragRouteId") String dragRouteId,
                                  @PathVariable("dropRouteId") @Param("dropRouteId") String dropRouteId,
                                  @PathVariable("dropType") @Param("dropType") String dropType,
                                  @RequestParam("record") @QueryMap RouteDragParams record,
                                  @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

}
