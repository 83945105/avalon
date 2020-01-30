package pub.avalonframework.cloud.gar.service;

import pub.avalonframework.cloud.gar.entity.AutObjectRole;

import java.util.List;
import java.util.Set;

/**
 * @author baichao
 * @date 2018/7/25
 */
public interface GarObjectRoleService {

    /**
     * 根据对象ID、角色ID新增对象角色数据
     *
     * @param moduleId
     * @param objectId
     * @param roleId
     * @throws Exception
     */
    void postObjectRoleByObjectIdAndRoleId(String moduleId, String objectId, String roleId) throws Exception;

    /**
     * 根据对象ID、角色ID批量新增对象角色数据
     *
     * @param moduleId
     * @param objectIds
     * @param roleIds
     * @throws Exception
     */
    void postListObjectRoleByObjectIdsAndRoleIds(String moduleId, Set<String> objectIds, Set<String> roleIds) throws Exception;

    /**
     * 根据对象ID、角色标识符新增对象角色数据
     *
     * @param moduleId
     * @param objectId
     * @param roleValue
     * @throws Exception
     */
    void postObjectRoleByObjectIdAndRoleValue(String moduleId, String objectId, String roleValue) throws Exception;

    /**
     * 根据角色ID批量修改对象角色数据
     *
     * @param moduleId
     * @param roleId
     * @param objectRoleUpdate
     * @throws Exception
     */
    void putListObjectRoleByRoleId(String moduleId, String roleId, AutObjectRole objectRoleUpdate) throws Exception;

    /**
     * 根据角色ID批量删除对象角色数据
     *
     * @param moduleId
     * @param roleId
     * @throws Exception
     */
    void deleteListObjectRoleByRoleId(String moduleId, String roleId) throws Exception;

    /**
     * 根据角色ID批量删除对象角色数据
     *
     * @param moduleId
     * @param roleIds
     * @throws Exception
     */
    void deleteListObjectRoleByRoleIds(String moduleId, List<String> roleIds) throws Exception;

    /**
     * 根据对象角色ID删除对象角色数据
     *
     * @param moduleId
     * @param objectRoleId
     * @throws Exception
     */
    void deleteObjectRoleByObjectRoleId(String moduleId, String objectRoleId) throws Exception;

    /**
     * 根据对象角色ID批量删除对象角色数据
     *
     * @param moduleId
     * @param objectRoleIds
     * @throws Exception
     */
    void deleteListObjectRoleByObjectRoleIds(String moduleId, Set<String> objectRoleIds) throws Exception;

    /**
     * 根据对象ID、角色ID删除对象角色数据
     *
     * @param moduleId
     * @param objectId
     * @param roleId
     * @throws Exception
     */
    void deleteObjectRoleByObjectIdAndRoleId(String moduleId, String objectId, String roleId) throws Exception;

    /**
     * 根据对象ID、角色ID批量删除对象角色数据
     *
     * @param moduleId
     * @param objectIds
     * @param roleIds
     * @throws Exception
     */
    void deleteListObjectRoleByObjectIdsAndRoleIds(String moduleId, Set<String> objectIds, Set<String> roleIds) throws Exception;
}