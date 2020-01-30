package pub.avalonframework.cloud.gar.api;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalonframework.cloud.gar.beans.TemplateDragParams;
import pub.avalonframework.cloud.gar.dc.TemplateGet;
import pub.avalonframework.cloud.gar.dc.TemplatePost;
import pub.avalonframework.cloud.gar.dc.TemplatePut;

/**
 * 模板接口
 *
 * @author 白超
 * @date 2018/12/7
 */
@FeignClient(name = "${feign.gar.template-api-service-name:gar-service}", path = "${feign.gar.template-api-service-path:/api-gar-template}")
public interface GarTemplateApi {

    String ROOT_PATH = "/gar/api-gar-template";

    /**
     * 根据子模块ID查询模板唯一标识符是否可用
     *
     * @param value
     * @param subModuleId
     * @param excludeValues
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/validateValueCanUseBySubModuleId/{value}/{subModuleId}")
    @RequestLine("GET " + ROOT_PATH + "/get/validateValueCanUseBySubModuleId/{value}//{subModuleId}?excludeValues={excludeValues}&moduleId={moduleId}")
    DataView getValidateValueCanUseBySubModuleId(@PathVariable("value") @Param("value") String value,
                                                 @PathVariable("subModuleId") @Param("subModuleId") String subModuleId,
                                                 @RequestParam("excludeValues") @Param("excludeValues") String excludeValues,
                                                 @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据模板ID获取模板详情
     *
     * @param templateId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/templateByTemplateId/{templateId}")
    @RequestLine("GET " + ROOT_PATH + "/get/templateByTemplateId/{templateId}?moduleId={moduleId}")
    DataView getTemplateByTemplateId(@PathVariable("templateId") @Param("templateId") String templateId,
                                     @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 条件查询模板集合
     *
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/template")
    @RequestLine("GET " + ROOT_PATH + "/get/list/template?moduleId={moduleId}")
    DataView getListTemplate(@RequestParam("record") @QueryMap TemplateGet record,
                             @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 分页条件查询模板集合
     *
     * @param record
     * @param currentPage
     * @param pageSize
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/pageList/template")
    @RequestLine("GET " + ROOT_PATH + "/get/pageList/template?currentPage={currentPage}&pageSize={pageSize}&moduleId={moduleId}")
    DataView getPageListTemplate(@RequestParam("record") @QueryMap TemplateGet record,
                                 @RequestParam("currentPage") @Param("currentPage") Integer currentPage,
                                 @RequestParam("pageSize") @Param("pageSize") Integer pageSize,
                                 @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 新增模板
     *
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/post/template")
    @RequestLine("POST " + ROOT_PATH + "/post/template?moduleId={moduleId}")
    DataView postTemplate(@RequestParam("record") @QueryMap TemplatePost record,
                          @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据模板ID修改模板数据
     *
     * @param templateId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/put/templateByTemplateId/{templateId}")
    @RequestLine("PUT " + ROOT_PATH + "/put/templateByTemplateId/{templateId}?moduleId={moduleId}")
    DataView putTemplateByTemplateId(@PathVariable("templateId") @Param("templateId") String templateId,
                                     @RequestParam("record") @QueryMap TemplatePut record,
                                     @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据模板ID交换模板index属性(排序用)
     *
     * @param sourceTemplateId
     * @param targetTemplateId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/put/switchTemplateIndexByTemplateId/{sourceTemplateId}/{targetTemplateId}")
    @RequestLine("PUT " + ROOT_PATH + "/put/switchTemplateIndexByTemplateId/{sourceTemplateId}/{targetTemplateId}?moduleId={moduleId}")
    DataView putSwitchTemplateIndexByTemplateId(@PathVariable("sourceTemplateId") @Param("sourceTemplateId") String sourceTemplateId,
                                                @PathVariable("targetTemplateId") @Param("targetTemplateId") String targetTemplateId,
                                                @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据模板ID删除模板数据
     *
     * @param templateId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/templateByTemplateId/{templateId}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/templateByTemplateId/{templateId}?moduleId={moduleId}")
    DataView deleteTemplateByTemplateId(@PathVariable("templateId") @Param("templateId") String templateId,
                                        @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据模板ID批量删除模板数据
     *
     * @param templateIds
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/list/templateByTemplateIds/{templateIds}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/list/templateByTemplateIds/{templateIds}?moduleId={moduleId}")
    DataView deleteListTemplateByTemplateIds(@PathVariable("templateIds") @Param("templateIds") String templateIds,
                                             @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 拖拽模板列表行
     *
     * @param dragTemplateId
     * @param dropTemplateId
     * @param dropType
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/put/dragTemplateListRow/{dragTemplateId}/{dropTemplateId}/{dropType}")
    @RequestLine("PUT " + ROOT_PATH + "/put/dragTemplateListRow/{dragTemplateId}/{dropTemplateId}/{dropType}?moduleId={moduleId}")
    DataView putDragTemplateListRow(@PathVariable("dragTemplateId") @Param("dragTemplateId") String dragTemplateId,
                                    @PathVariable("dropTemplateId") @Param("dropTemplateId") String dropTemplateId,
                                    @PathVariable("dropType") @Param("dropType") String dropType,
                                    @RequestParam("record") @QueryMap TemplateDragParams record,
                                    @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

}
