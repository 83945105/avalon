package pub.avalonframework.cloud.gar.service;

import pub.avalonframework.cloud.gar.dc.TemplateGet;
import pub.avalonframework.cloud.gar.dc.TemplatePost;
import pub.avalonframework.cloud.gar.dc.TemplatePut;
import pub.avalonframework.cloud.gar.entity.Template;

import java.util.List;

/**
 * @author 白超
 */
public interface GarTemplateService {

    /**
     * 查询模板唯一标识符是否可用
     *
     * @param moduleId
     * @param subModuleId
     * @param value
     * @param excludeValues
     * @return
     * @throws Exception
     */
    boolean getValidateValueCanUse(String moduleId, String subModuleId, String value, List<String> excludeValues) throws Exception;

    /**
     * 新增模板
     *
     * @param moduleId
     * @param record
     * @return
     * @throws Exception
     */
    TemplateGet postTemplate(String moduleId, TemplatePost record) throws Exception;

    /**
     * 根据模板ID修改模板数据
     *
     * @param moduleId
     * @param templateId
     * @param record
     * @return
     * @throws Exception
     */
    void putTemplateByTemplateId(String moduleId, String templateId, TemplatePut record) throws Exception;

    /**
     * 根据模块ID、子模块ID批量修改模板数据
     *
     * @param moduleId
     * @param subModuleId
     * @param templateUpdate
     * @throws Exception
     */
    void putListTemplateByModuleIdAndSubModuleId(String moduleId, String subModuleId, Template templateUpdate) throws Exception;

    /**
     * 根据模板ID交换模板index属性(排序用)
     *
     * @param moduleId
     * @param sourceTemplateId
     * @param targetTemplateId
     * @throws Exception
     */
    void putSwitchTemplateIndexByTemplateId(String moduleId, String sourceTemplateId, String targetTemplateId) throws Exception;

    /**
     * 根据模板ID删除模板数据
     *
     * @param moduleId
     * @param templateId
     * @return
     * @throws Exception
     */
    void deleteTemplateByTemplateId(String moduleId, String templateId) throws Exception;

    /**
     * 根据子模块ID批量删除模板数据
     *
     * @param moduleId
     * @param subModuleId
     * @throws Exception
     */
    void deleteListTemplateBySubModuleId(String moduleId, String subModuleId) throws Exception;

    /**
     * 根据子模块ID批量删除模板数据
     *
     * @param moduleId
     * @param subModuleIds
     * @throws Exception
     */
    void deleteListTemplateBySubModuleIds(String moduleId, List<String> subModuleIds) throws Exception;

    /**
     * 根据模板ID批量删除模板数据
     *
     * @param moduleId
     * @param templateIds
     * @return
     * @throws Exception
     */
    void deleteListTemplateByTemplateIds(String moduleId, List<String> templateIds) throws Exception;
}