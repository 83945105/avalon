package pub.avalonframework.cloud.gar.api;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalonframework.cloud.gar.dc.AutResourceGet;
import pub.avalonframework.cloud.gar.dc.AutResourcePost;
import pub.avalonframework.cloud.gar.dc.AutResourcePut;

/**
 * 资源接口
 *
 * @author 白超
 */
@FeignClient(name = "${feign.gar.resource-api-service-name:gar-service}", path = "${feign.gar.resource-api-service-path:/api-gar-resource}")
public interface GarResourceApi {

    String ROOT_PATH = "/gar/api-gar-resource";

    /**
     * 条件查询资源集合
     *
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/resource")
    @RequestLine("GET " + ROOT_PATH + "/get/list/resource?moduleId={moduleId}")
    DataView getListResource(@RequestParam("record") @QueryMap AutResourceGet record,
                             @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;


    /**
     * 分页条件查询资源集合
     *
     * @param record
     * @param currentPage
     * @param pageSize
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/pageList/resource")
    @RequestLine("GET " + ROOT_PATH + "/get/pageList/resource?currentPage={currentPage}&pageSize={pageSize}&moduleId={moduleId}")
    DataView getPageListResource(@RequestParam("record") @QueryMap AutResourceGet record,
                                 @RequestParam("currentPage") @Param("currentPage") Integer currentPage,
                                 @RequestParam("pageSize") @Param("pageSize") Integer pageSize,
                                 @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 条件查询根资源集合
     *
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/rootResource")
    @RequestLine("GET " + ROOT_PATH + "/get/list/rootResource?moduleId={moduleId}")
    DataView getListRootResource(@RequestParam("record") @QueryMap AutResourceGet record,
                                 @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;


    /**
     * 分页条件查询根资源集合
     *
     * @param record
     * @param currentPage
     * @param pageSize
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/pageList/rootResource")
    @RequestLine("GET " + ROOT_PATH + "/get/pageList/rootResource?currentPage={currentPage}&pageSize={pageSize}&moduleId={moduleId}")
    DataView getPageListRootResource(@RequestParam("record") @QueryMap AutResourceGet record,
                                     @RequestParam("currentPage") @Param("currentPage") Integer currentPage,
                                     @RequestParam("pageSize") @Param("pageSize") Integer pageSize,
                                     @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据父ID条件查询资源集合
     *
     * @param parentId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/resourceByParentId/{parentId}")
    @RequestLine("GET " + ROOT_PATH + "/get/list/resourceByParentId/{parentId}?moduleId={moduleId}")
    DataView getListResourceByParentId(@PathVariable("parentId") @Param("parentId") String parentId,
                                       @RequestParam("record") @QueryMap AutResourceGet record,
                                       @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据资源ID批量查询资源ID、名称集合
     *
     * @param resourceIds
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/resourceIdAndNameByResourceIds/{resourceIds}")
    @RequestLine("GET " + ROOT_PATH + "/get/list/resourceIdAndNameByResourceIds/{resourceIds}")
    DataView getListResourceIdAndNameByResourceIds(@PathVariable("resourceIds") @Param("resourceIds") String resourceIds,
                                                   @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 新增资源
     *
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/post/resource")
    @RequestLine("POST " + ROOT_PATH + "/post/resource?moduleId={moduleId}")
    DataView postResource(@RequestParam("record") @QueryMap AutResourcePost record,
                          @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据资源ID修改资源数据
     *
     * @param resourceId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/put/resourceByResourceId/{resourceId}")
    @RequestLine("PUT " + ROOT_PATH + "/put/resourceByResourceId/{resourceId}?moduleId={moduleId}")
    DataView putResourceByResourceId(@PathVariable("resourceId") @Param("resourceId") String resourceId,
                                     @RequestParam("record") @QueryMap AutResourcePut record,
                                     @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据资源ID删除资源数据
     *
     * @param resourceId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/resourceByResourceId/{resourceId}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/resourceByResourceId/{resourceId}?moduleId={moduleId}")
    DataView deleteResourceByResourceId(@PathVariable("resourceId") @Param("resourceId") String resourceId,
                                        @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据资源ID批量删除资源数据
     *
     * @param resourceIds
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/list/resourceByResourceIds/{resourceIds}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/list/resourceByResourceIds/{resourceIds}?moduleId={moduleId}")
    DataView deleteListResourceByResourceIds(@PathVariable("resourceIds") @Param("resourceIds") String resourceIds,
                                             @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;
}
