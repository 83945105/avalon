package pub.avalonframework.cloud.gar.service;

import pub.avalonframework.cloud.gar.entity.AutRoleResource;

import java.util.List;
import java.util.Set;

/**
 * @author 白超
 * @date 2018/7/23
 */
public interface GarRoleResourceService {

    /**
     * 根据角色ID、资源ID新增角色资源
     *
     * @param moduleId
     * @param roleId
     * @param resourceId
     * @throws Exception
     */
    void postRoleResourceByRoleIdAndResourceId(String moduleId, String roleId, String resourceId) throws Exception;

    /**
     * 根据角色ID、资源ID批量新增角色资源
     *
     * @param moduleId
     * @param roleIds
     * @param resourceIds
     * @throws Exception
     */
    void postListRoleResourceByRoleIdsAndResourceIds(String moduleId, Set<String> roleIds, Set<String> resourceIds) throws Exception;

    /**
     * 根据角色ID批量修改角色资源数据
     *
     * @param moduleId
     * @param roleId
     * @param roleResourceUpdate
     * @throws Exception
     */
    void putListRoleResourceByRoleId(String moduleId, String roleId, AutRoleResource roleResourceUpdate) throws Exception;

    /**
     * 根据资源ID批量修改角色资源数据
     *
     * @param moduleId
     * @param resourceId
     * @param roleResourceUpdate
     * @throws Exception
     */
    void putListRoleResourceByResourceId(String moduleId, String resourceId, AutRoleResource roleResourceUpdate) throws Exception;

    /**
     * 根据角色ID批量删除角色资源数据
     *
     * @param moduleId
     * @param roleId
     * @throws Exception
     */
    void deleteListRoleResourceByRoleId(String moduleId, String roleId) throws Exception;

    /**
     * 根据角色ID批量删除角色资源数据
     *
     * @param moduleId
     * @param roleIds
     * @throws Exception
     */
    void deleteListRoleResourceByRoleIds(String moduleId, List<String> roleIds) throws Exception;

    /**
     * 根据资源ID批量删除角色资源数据
     *
     * @param moduleId
     * @param resourceId
     * @throws Exception
     */
    void deleteListRoleResourceByResourceId(String moduleId, String resourceId) throws Exception;

    /**
     * 根据资源ID批量删除角色资源数据
     *
     * @param moduleId
     * @param resourceIds
     * @throws Exception
     */
    void deleteListRoleResourceByResourceIds(String moduleId, List<String> resourceIds) throws Exception;

    /**
     * 根据角色资源ID删除角色资源数据
     *
     * @param moduleId
     * @param roleResourceId
     * @throws Exception
     */
    void deleteRoleResourceByRoleResourceId(String moduleId, String roleResourceId) throws Exception;

    /**
     * 根据角色资源ID批量删除角色资源数据
     *
     * @param moduleId
     * @param roleResourceIds
     * @throws Exception
     */
    void deleteListRoleResourceByRoleResourceIds(String moduleId, Set<String> roleResourceIds) throws Exception;

    /**
     * 根据角色ID、资源ID删除角色资源
     *
     * @param moduleId
     * @param roleId
     * @param resourceId
     * @throws Exception
     */
    void deleteRoleResourceByRoleIdAndResourceId(String moduleId, String roleId, String resourceId) throws Exception;

    /**
     * 根据角色ID、资源ID批量删除角色资源
     *
     * @param moduleId
     * @param roleIds
     * @param resourceIds
     * @throws Exception
     */
    void deleteListRoleResourceByRoleIdsAndResourceIds(String moduleId, Set<String> roleIds, Set<String> resourceIds) throws Exception;

}
