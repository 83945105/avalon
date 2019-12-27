package pub.avalonframework.cloud.gar.service;

import pub.avalonframework.cloud.gar.dc.ModulePost;
import pub.avalonframework.cloud.gar.dc.ModulePut;

import java.util.Set;

/**
 * 模块服务
 *
 * @author 白超
 * @since 2018/7/11
 */
public interface GarModuleService {

    /**
     * 验证模块ID是否可用
     *
     * @param moduleId
     * @param excludeModuleIds
     * @return
     * @throws Exception
     */
    boolean getValidateModuleIdCanUse(String moduleId, Set<String> excludeModuleIds) throws Exception;

    /**
     * 验证地址是否可用
     *
     * @param path
     * @param excludePaths
     * @return
     * @throws Exception
     */
    boolean getValidatePathCanUse(String path, Set<String> excludePaths) throws Exception;

    /**
     * 验证服务ID是否可用
     *
     * @param serviceId
     * @param excludeServiceIds
     * @return
     * @throws Exception
     */
    boolean getValidateServiceIdCanUse(String serviceId, Set<String> excludeServiceIds) throws Exception;

    /**
     * 新增模块
     *
     * @param record
     * @return
     * @throws Exception
     */
    String postModule(ModulePost record) throws Exception;

    /**
     * 根据模块ID修改模块数据
     *
     * @param moduleId
     * @param record
     * @throws Exception
     */
    void putModuleByModuleId(String moduleId, ModulePut record) throws Exception;

    /**
     * 根据模块ID删除模块信息
     *
     * @param moduleId
     * @throws Exception
     */
    void deleteModuleByModuleId(String moduleId) throws Exception;
}