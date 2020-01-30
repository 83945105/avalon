package pub.avalonframework.cloud.gar.api;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalonframework.cloud.gar.dc.RouteViewGet;
import pub.avalonframework.cloud.gar.dc.RouteViewPost;
import pub.avalonframework.cloud.gar.dc.RouteViewPut;

/**
 * 路由视图接口
 *
 * @author 白超
 * @date 2018/12/7
 */
@FeignClient(name = "${feign.gar.route-view-api-service-name:gar-service}", path = "${feign.gar.route-view-api-service-path:/api-gar-route-view}")
public interface GarRouteViewApi {

    String ROOT_PATH = "/gar/api-gar-route-view";

    /**
     * 根据路由ID查询路由视图唯一标识符是否可用
     *
     * @param value
     * @param routeId
     * @param excludeValues
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/validateValueCanUseByRouteId/{value}/{routeId}")
    @RequestLine("GET " + ROOT_PATH + "/get/validateValueCanUseByRouteId/{value}/{routeId}?excludeValues={excludeValues}&moduleId={moduleId}")
    DataView getValidateValueCanUseByRouteId(@PathVariable("value") @Param("value") String value,
                                             @PathVariable("routeId") @Param("routeId") String routeId,
                                             @RequestParam("excludeValues") @Param("excludeValues") String excludeValues,
                                             @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据路由视图ID获取路由视图详情
     *
     * @param routeViewId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/routeViewByRouteViewId/{routeViewId}")
    @RequestLine("GET " + ROOT_PATH + "/get/routeViewByRouteViewId/{routeViewId}?moduleId={moduleId}")
    DataView getRouteViewByRouteViewId(@PathVariable("routeViewId") @Param("routeViewId") String routeViewId,
                                       @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 条件查询路由视图集合
     *
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/routeView")
    @RequestLine("GET " + ROOT_PATH + "/get/list/routeView?moduleId={moduleId}")
    DataView getListRouteView(@RequestParam("record") @QueryMap RouteViewGet record,
                              @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 分页条件查询路由视图集合
     *
     * @param record
     * @param currentPage
     * @param pageSize
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/pageList/routeView")
    @RequestLine("GET " + ROOT_PATH + "/get/pageList/routeView?currentPage={currentPage}&pageSize={pageSize}&moduleId={moduleId}")
    DataView getPageListRouteView(@RequestParam("record") @QueryMap RouteViewGet record,
                                  @RequestParam("currentPage") @Param("currentPage") Integer currentPage,
                                  @RequestParam("pageSize") @Param("pageSize") Integer pageSize,
                                  @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 新增路由视图
     *
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/post/routeView")
    @RequestLine("POST " + ROOT_PATH + "/post/routeView?moduleId={moduleId}")
    DataView postRouteView(@RequestParam("record") @QueryMap RouteViewPost record,
                           @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据路由视图ID修改路由视图数据
     *
     * @param routeViewId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/put/routeViewByRouteViewId/{routeViewId}")
    @RequestLine("PUT " + ROOT_PATH + "/put/routeViewByRouteViewId/{routeViewId}?moduleId={moduleId}")
    DataView putRouteViewByRouteViewId(@PathVariable("routeViewId") @Param("routeViewId") String routeViewId,
                                       @RequestParam("record") @QueryMap RouteViewPut record,
                                       @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据路由视图ID交换路由视图index属性(排序用)
     *
     * @param sourceRouteViewId
     * @param targetRouteViewId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/put/switchRouteViewIndexByRouteViewId/{sourceRouteViewId}/{targetRouteViewId}")
    @RequestLine("PUT " + ROOT_PATH + "/put/switchRouteViewIndexByRouteViewId/{sourceRouteViewId}/{targetRouteViewId}?moduleId={moduleId}")
    DataView putSwitchRouteViewIndexByRouteViewId(@PathVariable("sourceRouteViewId") @Param("sourceRouteViewId") String sourceRouteViewId,
                                                  @PathVariable("targetRouteViewId") @Param("targetRouteViewId") String targetRouteViewId,
                                                  @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据路由视图ID删除路由视图数据
     *
     * @param routeViewId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/routeViewByRouteViewId/{routeViewId}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/routeViewByRouteViewId/{routeViewId}?moduleId={moduleId}")
    DataView deleteRouteViewByRouteViewId(@PathVariable("routeViewId") @Param("routeViewId") String routeViewId,
                                          @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据路由视图ID批量删除路由视图数据
     *
     * @param routeViewIds
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/list/routeViewByRouteViewIds/{routeViewIds}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/list/routeViewByRouteViewIds/{routeViewIds}?moduleId={moduleId}")
    DataView deleteListRouteViewByRouteViewIds(@PathVariable("routeViewIds") @Param("routeViewIds") String routeViewIds,
                                               @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

}
