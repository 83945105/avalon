package pub.avalonframework.cloud.gar.api;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalonframework.cloud.gar.dc.AutObjectGet;

/**
 * 对象接口
 *
 * @author 白超
 */
@FeignClient(name = "${feign.gar.object-api-service-name:gar-service}", path = "${feign.gar.object-api-service-path:/api-gar-object}")
public interface GarObjectApi {

    String ROOT_PATH = "/gar/api-gar-object";

    /**
     * 获取表格配置
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/tableOptions")
    @RequestLine("GET " + ROOT_PATH + "/get/tableOptions")
    DataView getTableOptions() throws Exception;

    /**
     * 根据对象ID查询对象数据
     *
     * @param objectId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/objectByObjectId/{objectId}")
    @RequestLine("GET " + ROOT_PATH + "/get/objectByObjectId/{objectId}")
    DataView getObjectByObjectId(@PathVariable("objectId") @Param("objectId") String objectId) throws Exception;

    /**
     * 分页条件查询对象集合
     *
     * @param record
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/pageList/object")
    @RequestLine("GET " + ROOT_PATH + "/get/pageList/object?currentPage={currentPage}&pageSize={pageSize}")
    DataView getPageListObject(@RequestParam("record") @QueryMap AutObjectGet record,
                               @RequestParam("currentPage") @Param("currentPage") Integer currentPage,
                               @RequestParam("pageSize") @Param("pageSize") Integer pageSize) throws Exception;

    /**
     * 启用对象
     *
     * @param objectId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/put/objectEnabledByObjectId/{objectId}")
    @RequestLine("PUT " + ROOT_PATH + "/put/objectEnabledByObjectId/{objectId}")
    DataView putObjectEnabledByObjectId(@PathVariable("objectId") @Param("objectId") String objectId) throws Exception;

    /**
     * 禁用对象
     *
     * @param objectId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/put/objectDisabledByObjectId/{objectId}")
    @RequestLine("PUT " + ROOT_PATH + "/put/objectDisabledByObjectId/{objectId}")
    DataView putObjectDisabledByObjectId(@PathVariable("objectId") @Param("objectId") String objectId) throws Exception;

}
