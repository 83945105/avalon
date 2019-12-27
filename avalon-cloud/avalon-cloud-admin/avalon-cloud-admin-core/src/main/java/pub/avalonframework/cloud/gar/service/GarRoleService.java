package pub.avalonframework.cloud.gar.service;

import pub.avalonframework.cloud.gar.dc.AutRoleGet;
import pub.avalonframework.cloud.gar.dc.AutRolePost;
import pub.avalonframework.cloud.gar.dc.AutRolePut;

import java.util.List;

/**
 * @author 白超
 * @since 2018/7/12
 */
public interface GarRoleService {

    /**
     * 校验角色标识符是否可用
     *
     * @param moduleId
     * @param value
     * @param excludeValues
     * @return
     * @throws Exception
     */
    boolean getValidateValueCanUse(String moduleId, String value, List<String> excludeValues) throws Exception;

    /**
     * 新增角色
     *
     * @param moduleId
     * @param record
     * @return
     * @throws Exception
     */
    AutRoleGet postRole(String moduleId, AutRolePost record) throws Exception;

    /**
     * 根据角色ID修改角色
     *
     * @param moduleId
     * @param roleId
     * @param record
     * @throws Exception
     */
    void putRoleByRoleId(String moduleId, String roleId, AutRolePut record) throws Exception;

    /**
     * 根据角色ID删除角色数据
     *
     * @param roleId
     * @param moduleId
     * @throws Exception
     */
    void deleteRoleByRoleId(String moduleId, String roleId) throws Exception;

    /**
     * 根据角色ID批量删除角色数据
     *
     * @param moduleId
     * @param roleIds
     * @throws Exception
     */
    void deleteListRoleByRoleIds(String moduleId, List<String> roleIds) throws Exception;
}