package pub.avalonframework.cloud.gar.service;

import pub.avalonframework.cloud.gar.entity.AutRoleRouteViewTemplate;

import java.util.List;

/**
 * @author 白超
 */
public interface GarRoleRouteViewTemplateService {

    /**
     * 根据角色ID、路由视图ID、模板ID新增角色路由视图模板数据
     *
     * @param moduleId
     * @param roleId
     * @param routeViewId
     * @param templateId
     * @throws Exception
     */
    void postRoleRouteViewTemplateByRoleIdAndRouteViewIdAndTemplateId(String moduleId, String roleId, String routeViewId, String templateId) throws Exception;

    /**
     * 根据角色ID(多条)、路由视图ID、模板ID批量新增角色路由视图模板数据
     *
     * @param moduleId
     * @param roleIds
     * @param routeViewId
     * @param templateId
     * @throws Exception
     */
    void postRoleRouteViewTemplateByRoleIdsAndRouteViewIdAndTemplateId(String moduleId, List<String> roleIds, String routeViewId, String templateId) throws Exception;

    /**
     * 根据角色ID批量修改角色路由视图模板数据
     *
     * @param moduleId
     * @param roleId
     * @param roleRouteViewTemplateUpdate
     * @throws Exception
     */
    void putListRoleRouteViewTemplateByRoleId(String moduleId, String roleId, AutRoleRouteViewTemplate roleRouteViewTemplateUpdate) throws Exception;

    /**
     * 根据路由ID批量修改角色路由视图模板数据
     *
     * @param moduleId
     * @param routeId
     * @param roleRouteViewTemplateUpdate
     * @throws Exception
     */
    void putListRoleRouteViewTemplateByRouteId(String moduleId, String routeId, AutRoleRouteViewTemplate roleRouteViewTemplateUpdate) throws Exception;

    /**
     * 根据路由视图ID批量修改角色路由视图模板数据
     *
     * @param moduleId
     * @param routeViewId
     * @param roleRouteViewTemplateUpdate
     * @throws Exception
     */
    void putListRoleRouteViewTemplateByRouteViewId(String moduleId, String routeViewId, AutRoleRouteViewTemplate roleRouteViewTemplateUpdate) throws Exception;

    /**
     * 根据模板ID批量修改角色路由视图模板数据
     *
     * @param moduleId
     * @param templateId
     * @param roleRouteViewTemplateUpdate
     * @throws Exception
     */
    void putListRoleRouteViewTemplateByTemplateId(String moduleId, String templateId, AutRoleRouteViewTemplate roleRouteViewTemplateUpdate) throws Exception;

    /**
     * 根据角色ID批量删除角色路由视图模板数据
     *
     * @param moduleId
     * @param roleId
     * @throws Exception
     */
    void deleteListRoleRouteViewTemplateByRoleId(String moduleId, String roleId) throws Exception;

    /**
     * 根据角色ID批量删除角色路由视图模板数据
     *
     * @param moduleId
     * @param roleIds
     * @throws Exception
     */
    void deleteListRoleRouteViewTemplateByRoleIds(String moduleId, List<String> roleIds) throws Exception;

    /**
     * 根据路由视图ID批量删除角色路由视图模板数据
     *
     * @param moduleId
     * @param routeViewId
     * @throws Exception
     */
    void deleteListRoleRouteViewTemplateByRouteViewId(String moduleId, String routeViewId) throws Exception;

    /**
     * 根据路由视图ID批量删除角色路由视图模板数据
     *
     * @param moduleId
     * @param routeViewIds
     * @throws Exception
     */
    void deleteListRoleRouteViewTemplateByRouteViewIds(String moduleId, List<String> routeViewIds) throws Exception;

    /**
     * 根据模板ID批量删除角色路由视图模板数据
     *
     * @param moduleId
     * @param templateId
     * @throws Exception
     */
    void deleteListRoleRouteViewTemplateByTemplateId(String moduleId, String templateId) throws Exception;

    /**
     * 根据模板ID批量删除角色路由视图模板数据
     *
     * @param moduleId
     * @param templateIds
     * @throws Exception
     */
    void deleteListRoleRouteViewTemplateByTemplateIds(String moduleId, List<String> templateIds) throws Exception;

    /**
     * 根据路由视图ID、模板ID删除角色路由视图模板数据
     *
     * @param moduleId
     * @param routeViewId
     * @param templateId
     * @throws Exception
     */
    void deleteListRoleRouteViewTemplateByRouteViewIdAndTemplateId(String moduleId, String routeViewId, String templateId) throws Exception;

    /**
     * 根据角色路由视图模板ID删除角色路由视图模板数据
     *
     * @param moduleId
     * @param roleRouteViewTemplateId
     * @throws Exception
     */
    void deleteRoleRouteViewTemplateByRoleRouteViewTemplateId(String moduleId, String roleRouteViewTemplateId) throws Exception;

    /**
     * 根据角色路由视图模板ID批量删除角色路由视图模板数据
     *
     * @param moduleId
     * @param roleRouteViewTemplateIds
     * @throws Exception
     */
    void deleteListRoleRouteViewTemplateByRoleRouteViewTemplateIds(String moduleId, List<String> roleRouteViewTemplateIds) throws Exception;

    /**
     * 根据角色ID、路由视图ID、模板ID删除角色路由视图模板数据
     *
     * @param moduleId
     * @param roleId
     * @param routeViewId
     * @param templateId
     * @throws Exception
     */
    void deleteRoleRouteViewTemplateByRoleIdAndRouteViewIdAndTemplateId(String moduleId, String roleId, String routeViewId, String templateId) throws Exception;
}