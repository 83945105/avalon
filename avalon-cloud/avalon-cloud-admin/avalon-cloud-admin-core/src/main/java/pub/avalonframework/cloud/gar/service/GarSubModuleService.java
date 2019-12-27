package pub.avalonframework.cloud.gar.service;

import pub.avalonframework.cloud.gar.dc.SubModuleGet;
import pub.avalonframework.cloud.gar.dc.SubModulePost;
import pub.avalonframework.cloud.gar.dc.SubModulePut;
import pub.avalonframework.cloud.gar.entity.SubModule;

import java.util.List;

/**
 * @author 白超
 * @date 2018/12/6
 */
public interface GarSubModuleService {

    /**
     * 查询子模块唯一标识符是否可用
     *
     * @param moduleId
     * @param value
     * @param excludeValues
     * @return
     * @throws Exception
     */
    boolean getValidateValueCanUse(String moduleId, String value, List<String> excludeValues) throws Exception;

    /**
     * 新增子模块
     *
     * @param moduleId
     * @param record
     * @return
     * @throws Exception
     */
    SubModuleGet postSubModule(String moduleId, SubModulePost record) throws Exception;

    /**
     * 根据子模块ID修改子模块数据
     *
     * @param moduleId
     * @param subModuleId
     * @param record
     * @return
     * @throws Exception
     */
    void putSubModuleBySubModuleId(String moduleId, String subModuleId, SubModulePut record) throws Exception;

    /**
     * 根据模块ID批量修改子模块数据
     *
     * @param moduleId
     * @param subModuleUpdate
     * @throws Exception
     */
    void putListSubModuleByModuleId(String moduleId, SubModule subModuleUpdate) throws Exception;

    /**
     * 根据子模块ID交换子模块index属性(排序用)
     *
     * @param moduleId
     * @param sourceSubModuleId
     * @param targetSubModuleId
     * @throws Exception
     */
    void putSwitchSubModuleIndexBySubModuleId(String moduleId, String sourceSubModuleId, String targetSubModuleId) throws Exception;

    /**
     * 根据子模块ID删除子模块数据
     *
     * @param moduleId
     * @param subModuleId
     * @return
     * @throws Exception
     */
    void deleteSubModuleBySubModuleId(String moduleId, String subModuleId) throws Exception;

    /**
     * 根据子模块ID批量删除子模块数据
     *
     * @param moduleId
     * @param subModuleIds
     * @return
     * @throws Exception
     */
    void deleteListSubModuleBySubModuleIds(String moduleId, List<String> subModuleIds) throws Exception;
}