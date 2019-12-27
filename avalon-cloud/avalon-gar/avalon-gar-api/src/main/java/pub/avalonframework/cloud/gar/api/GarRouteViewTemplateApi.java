package pub.avalonframework.cloud.gar.api;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalonframework.cloud.gar.dc.RouteViewTemplateGet;

/**
 * 路由视图模板接口
 *
 * @author 白超
 * @date 2018/7/23
 */
@FeignClient(name = "${feign.gar.route-view-template-api-service-name:gar-service}", path = "${feign.gar.route-view-template-api-service-path:/api-gar-route-view-template}")
public interface GarRouteViewTemplateApi {

    String ROOT_PATH = "/api-gar-route-view-template";

    /**
     * 根据路由视图ID条件查询路由视图模板数据
     *
     * @param routeViewId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/routeViewTemplateByRouteViewId/{routeViewId}")
    @RequestLine("GET " + ROOT_PATH + "/get/list/routeViewTemplateByRouteViewId/{routeViewId}?moduleId={moduleId}")
    DataView getListRouteViewTemplateByRouteViewId(@PathVariable("routeViewId") @Param("routeViewId") String routeViewId,
                                                   @RequestParam("record") @QueryMap RouteViewTemplateGet record,
                                                   @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据路由视图ID分页条件查询路由视图模板数据
     *
     * @param routeViewId
     * @param record
     * @param currentPage
     * @param pageSize
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/pageList/routeViewTemplateByRouteViewId/{routeViewId}")
    @RequestLine("GET " + ROOT_PATH + "/get/pageList/routeViewTemplateByRouteViewId/{routeViewId}?currentPage={currentPage}&pageSize={pageSize}&moduleId={moduleId}")
    DataView getPageListRouteViewTemplateByRouteViewId(@PathVariable("routeViewId") @Param("routeViewId") String routeViewId,
                                                       @RequestParam("record") @QueryMap RouteViewTemplateGet record,
                                                       @RequestParam("currentPage") @Param("currentPage") Integer currentPage,
                                                       @RequestParam("pageSize") @Param("pageSize") Integer pageSize,
                                                       @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据模板ID条件查询路由视图模板数据
     *
     * @param templateId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/routeViewTemplateByTemplateId/{templateId}")
    @RequestLine("GET " + ROOT_PATH + "/get/list/routeViewTemplateByTemplateId/{templateId}?moduleId={moduleId}")
    DataView getListRouteViewTemplateByTemplateId(@PathVariable("templateId") @Param("templateId") String templateId,
                                                  @RequestParam("record") @QueryMap RouteViewTemplateGet record,
                                                  @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据模板ID分页条件查询路由视图模板数据
     *
     * @param templateId
     * @param record
     * @param currentPage
     * @param pageSize
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/pageList/routeViewTemplateByTemplateId/{templateId}")
    @RequestLine("GET " + ROOT_PATH + "/get/pageList/routeViewTemplateByTemplateId/{templateId}?currentPage={currentPage}&pageSize={pageSize}&moduleId={moduleId}")
    DataView getPageListRouteViewTemplateByTemplateId(@PathVariable("templateId") @Param("templateId") String templateId,
                                                      @RequestParam("record") @QueryMap RouteViewTemplateGet record,
                                                      @RequestParam("currentPage") @Param("currentPage") Integer currentPage,
                                                      @RequestParam("pageSize") @Param("pageSize") Integer pageSize,
                                                      @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据路由视图ID、模板ID新增路由视图模板数据
     *
     * @param routeViewId
     * @param templateId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/post/routeViewTemplateByRouteViewIdAndTemplateId/{routeViewId}/{templateId}")
    @RequestLine("POST " + ROOT_PATH + "/post/routeViewTemplateByRouteViewIdAndTemplateId/{routeViewId}/{templateId}?moduleId={moduleId}")
    DataView postRouteViewTemplateByRouteViewIdAndTemplateId(@PathVariable("routeViewId") @Param("routeViewId") String routeViewId,
                                                             @PathVariable("templateId") @Param("templateId") String templateId,
                                                             @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据路由视图ID、模板ID批量新增路由视图模板数据
     *
     * @param routeViewIds
     * @param templateIds
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/post/list/routeViewTemplateByRouteViewIdsAndTemplateIds/{routeViewIds}/{templateIds}")
    @RequestLine("POST " + ROOT_PATH + "/post/list/routeViewTemplateByRouteViewIdsAndTemplateIds/{routeViewIds}/{templateIds}?moduleId={moduleId}")
    DataView postListRouteViewTemplateByRouteViewIdsAndTemplateIds(@PathVariable("routeViewIds") @Param("routeViewIds") String routeViewIds,
                                                                   @PathVariable("templateIds") @Param("templateIds") String templateIds,
                                                                   @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据路由视图模板ID删除路由视图模板数据
     *
     * @param routeViewTemplateId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/routeViewTemplateByRouteViewTemplateId/{routeViewTemplateId}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/routeViewTemplateByRouteViewTemplateId/{routeViewTemplateId}?moduleId={moduleId}")
    DataView deleteRouteViewTemplateByRouteViewTemplateId(@PathVariable("routeViewTemplateId") @Param("routeViewTemplateId") String routeViewTemplateId,
                                                          @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据路由视图模板ID批量删除路由视图模板数据
     *
     * @param routeViewTemplateIds
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/list/routeViewTemplateByRouteViewTemplateIds/{routeViewTemplateIds}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/list/routeViewTemplateByRouteViewTemplateIds/{routeViewTemplateIds}?moduleId={moduleId}")
    DataView deleteListRouteViewTemplateByRouteViewTemplateIds(@PathVariable("routeViewTemplateIds") @Param("routeViewTemplateIds") String routeViewTemplateIds,
                                                               @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;


    /**
     * 根据路由视图ID、模板ID删除路由视图模板数据
     *
     * @param routeViewId
     * @param templateId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/routeViewTemplateByRouteViewIdAndTemplateId/{routeViewId}/{templateId}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/routeViewTemplateByRouteViewIdAndTemplateId/{routeViewId}/{templateId}?moduleId={moduleId}")
    DataView deleteRouteViewTemplateByRouteViewIdAndTemplateId(@PathVariable("routeViewId") @Param("routeViewId") String routeViewId,
                                                               @PathVariable("templateId") @Param("templateId") String templateId,
                                                               @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

}
