package pub.avalonframework.cloud.gar.api;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalonframework.cloud.gar.dc.SubModuleGet;
import pub.avalonframework.cloud.gar.dc.SubModulePost;
import pub.avalonframework.cloud.gar.dc.SubModulePut;

/**
 * 子模块接口
 *
 * @author 白超
 */
@FeignClient(name = "${feign.gar.sub-module-api-service-name:gar-service}", path = "${feign.gar.sub-module-api-service-path:/api-gar-sub-module}")
public interface GarSubModuleApi {

    String ROOT_PATH = "/gar/api-gar-sub-module";

    /**
     * 查询子模块唯一标识符是否可用
     *
     * @param value
     * @param excludeValues
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/validateValueCanUse/{value}")
    @RequestLine("GET " + ROOT_PATH + "/get/validateValueCanUse/{value}?excludeValues={excludeValues}&moduleId={moduleId}")
    DataView getValidateValueCanUse(@PathVariable("value") @Param("value") String value,
                                    @RequestParam("excludeValues") @Param("excludeValues") String excludeValues,
                                    @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据子模块ID获取子模块详情
     *
     * @param subModuleId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/subModuleBySubModuleId/{subModuleId}")
    @RequestLine("GET " + ROOT_PATH + "/get/subModuleBySubModuleId/{subModuleId}?moduleId={moduleId}")
    DataView getSubModuleBySubModuleId(@PathVariable("subModuleId") @Param("subModuleId") String subModuleId,
                                       @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 条件查询子模块集合
     *
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/subModule")
    @RequestLine("GET " + ROOT_PATH + "/get/list/subModule?moduleId={moduleId}")
    DataView getListSubModule(@RequestParam("record") @QueryMap SubModuleGet record,
                              @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 分页条件查询子模块集合
     *
     * @param record
     * @param currentPage
     * @param pageSize
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/pageList/subModule")
    @RequestLine("GET " + ROOT_PATH + "/get/pageList/subModule?currentPage={currentPage}&pageSize={pageSize}&moduleId={moduleId}")
    DataView getPageListSubModule(@RequestParam("record") @QueryMap SubModuleGet record,
                                  @RequestParam("currentPage") @Param("currentPage") Integer currentPage,
                                  @RequestParam("pageSize") @Param("pageSize") Integer pageSize,
                                  @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 条件查询子模块及子模块下路由数量
     *
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/subModuleAndSubModuleRouteCount")
    @RequestLine("GET " + ROOT_PATH + "/get/list/subModuleAndSubModuleRouteCount?moduleId={moduleId}")
    DataView getListSubModuleAndSubModuleRouteCount(@RequestParam("record") @QueryMap SubModuleGet record,
                                                    @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 条件查询子模块及子模块下菜单组数量
     *
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/subModuleAndSubModuleMenuGroupCount")
    @RequestLine("GET " + ROOT_PATH + "/get/list/subModuleAndSubModuleMenuGroupCount?moduleId={moduleId}")
    DataView getListSubModuleAndSubModuleMenuGroupCount(@RequestParam("record") @QueryMap SubModuleGet record,
                                                        @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 新增子模块
     *
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/post/subModule")
    @RequestLine("POST " + ROOT_PATH + "/post/subModule?moduleId={moduleId}")
    DataView postSubModule(@RequestParam("record") @QueryMap SubModulePost record,
                           @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据子模块ID修改子模块数据
     *
     * @param subModuleId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/put/subModuleBySubModuleId/{subModuleId}")
    @RequestLine("PUT " + ROOT_PATH + "/put/subModuleBySubModuleId/{subModuleId}?moduleId={moduleId}")
    DataView putSubModuleBySubModuleId(@PathVariable("subModuleId") @Param("subModuleId") String subModuleId,
                                       @RequestParam("record") @QueryMap SubModulePut record,
                                       @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据子模块ID交换子模块index属性(排序用)
     *
     * @param sourceSubModuleId
     * @param targetSubModuleId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/put/switchSubModuleIndexBySubModuleId/{sourceSubModuleId}/{targetSubModuleId}")
    @RequestLine("PUT " + ROOT_PATH + "/put/switchSubModuleIndexBySubModuleId/{sourceSubModuleId}/{targetSubModuleId}?moduleId={moduleId}")
    DataView putSwitchSubModuleIndexBySubModuleId(@PathVariable("sourceSubModuleId") @Param("sourceSubModuleId") String sourceSubModuleId,
                                                  @PathVariable("targetSubModuleId") @Param("targetSubModuleId") String targetSubModuleId,
                                                  @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据子模块ID删除子模块数据
     *
     * @param subModuleId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/subModuleBySubModuleId/{subModuleId}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/subModuleBySubModuleId/{subModuleId}?moduleId={moduleId}")
    DataView deleteSubModuleBySubModuleId(@PathVariable("subModuleId") @Param("subModuleId") String subModuleId,
                                          @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据子模块ID批量删除子模块数据
     *
     * @param subModuleIds
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/list/subModuleBySubModuleIds/{subModuleIds}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/list/subModuleBySubModuleIds/{subModuleIds}?moduleId={moduleId}")
    DataView deleteListSubModuleBySubModuleIds(@PathVariable("subModuleIds") @Param("subModuleIds") String subModuleIds,
                                               @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

}
