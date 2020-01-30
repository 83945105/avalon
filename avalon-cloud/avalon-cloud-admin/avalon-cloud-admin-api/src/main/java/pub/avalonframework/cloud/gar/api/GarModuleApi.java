package pub.avalonframework.cloud.gar.api;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalonframework.cloud.gar.dc.ModuleGet;
import pub.avalonframework.cloud.gar.dc.ModulePost;
import pub.avalonframework.cloud.gar.dc.ModulePut;

/**
 * 模块接口
 *
 * @author 白超
 * @date 2018/7/11
 */
@FeignClient(name = "${feign.gar.module-api-service-name:gar-service}", path = "${feign.gar.module-api-service-path:/api-gar-module}")
public interface GarModuleApi {

    String ROOT_PATH = "/gar/api-gar-module";

    /**
     * 根据模块ID查询模块数据
     *
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/moduleByModuleId/{moduleId}")
    @RequestLine("GET " + ROOT_PATH + "/get/moduleByModuleId/{moduleId}")
    DataView getModuleByModuleId(@PathVariable("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 查询模块ID是否可用
     *
     * @param moduleId
     * @param excludeModuleIds
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/validateModuleIdCanUse/{moduleId}")
    @RequestLine("GET " + ROOT_PATH + "/get/validateModuleIdCanUse/{moduleId}")
    DataView getValidateModuleIdCanUse(@PathVariable("moduleId") @Param("moduleId") String moduleId,
                                       @RequestParam("excludeModuleIds") @Param("excludeModuleIds") String excludeModuleIds) throws Exception;

    /**
     * 查询路由地址是否可用
     *
     * @param path
     * @param excludePaths
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/validatePathCanUse/{path}")
    @RequestLine("GET " + ROOT_PATH + "/get/validatePathCanUse/{path}")
    DataView getValidatePathCanUse(@PathVariable("path") @Param("path") String path,
                                   @RequestParam("excludePaths") @Param("excludePaths") String excludePaths) throws Exception;

    /**
     * 查询服务ID是否可用
     *
     * @param serviceId
     * @param excludeServiceIds
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/validateServiceIdCanUse/{serviceId}")
    @RequestLine("GET " + ROOT_PATH + "/get/validateServiceIdCanUse/{serviceId}")
    DataView getValidateServiceIdCanUse(@PathVariable("serviceId") @Param("serviceId") String serviceId,
                                        @RequestParam("excludeServiceIds") @Param("excludeServiceIds") String excludeServiceIds) throws Exception;

    /**
     * 条件查询模块集合
     *
     * @param record
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/module")
    @RequestLine("GET " + ROOT_PATH + "/get/list/module")
    DataView getListModule(@RequestParam("record") @QueryMap ModuleGet record) throws Exception;

    /**
     * 新增模块(会创建相关表)
     *
     * @param record
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/post/module")
    @RequestLine("POST " + ROOT_PATH + "/post/module")
    DataView postModule(@RequestParam("record") @QueryMap ModulePost record) throws Exception;

    /**
     * 根据模块ID修改模块数据
     *
     * @param moduleId
     * @param record
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/put/moduleByModuleId/{moduleId}")
    @RequestLine("PUT " + ROOT_PATH + "/put/moduleByModuleId/{moduleId}")
    DataView putModuleByModuleId(@PathVariable("moduleId") @Param("moduleId") String moduleId,
                                 @RequestParam("record") @QueryMap ModulePut record) throws Exception;

    /**
     * 根据模块ID删除模块数据(会将相关表重命名为历史表)
     *
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/moduleByModuleId/{moduleId}")
    DataView deleteModuleByModuleId(@PathVariable("moduleId") @Param("moduleId") String moduleId) throws Exception;

}
