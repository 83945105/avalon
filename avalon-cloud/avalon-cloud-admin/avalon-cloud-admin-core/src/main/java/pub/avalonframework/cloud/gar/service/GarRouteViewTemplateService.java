package pub.avalonframework.cloud.gar.service;

import pub.avalonframework.cloud.gar.entity.RouteViewTemplate;

import java.util.List;
import java.util.Set;

/**
 * @author 白超
 */
public interface GarRouteViewTemplateService {

    /**
     * 根据路由视图ID、模板ID新增路由视图模板
     *
     * @param moduleId
     * @param routeViewId
     * @param templateId
     * @throws Exception
     */
    void postRouteViewTemplateByRouteViewIdAndTemplateId(String moduleId, String routeViewId, String templateId) throws Exception;

    /**
     * 根据路由视图ID、模板ID批量新增路由视图模板
     *
     * @param moduleId
     * @param routeViewIds
     * @param templateIds
     * @throws Exception
     */
    void postListRouteViewTemplateByRouteViewIdsAndTemplateIds(String moduleId, Set<String> routeViewIds, Set<String> templateIds) throws Exception;

    /**
     * 根据路由ID批量修改路由视图模板数据
     *
     * @param moduleId
     * @param routeId
     * @param routeViewTemplateUpdate
     * @throws Exception
     */
    void putListRouteViewTemplateByRouteId(String moduleId, String routeId, RouteViewTemplate routeViewTemplateUpdate) throws Exception;

    /**
     * 根据路由视图ID批量修改路由视图模板数据
     *
     * @param moduleId
     * @param routeViewId
     * @param routeViewTemplateUpdate
     * @throws Exception
     */
    void putListRouteViewTemplateByRouteViewId(String moduleId, String routeViewId, RouteViewTemplate routeViewTemplateUpdate) throws Exception;

    /**
     * 根据模板ID批量修改路由视图模板数据
     *
     * @param moduleId
     * @param templateId
     * @param routeViewTemplateUpdate
     * @throws Exception
     */
    void putListRouteViewTemplateByTemplateId(String moduleId, String templateId, RouteViewTemplate routeViewTemplateUpdate) throws Exception;

    /**
     * 根据路由视图ID批量删除路由视图模板数据
     *
     * @param moduleId
     * @param routeViewId
     * @throws Exception
     */
    void deleteListRouteViewTemplateByRouteViewId(String moduleId, String routeViewId) throws Exception;

    /**
     * 根据路由视图ID批量删除路由视图模板数据
     *
     * @param moduleId
     * @param routeViewIds
     * @throws Exception
     */
    void deleteListRouteViewTemplateByRouteViewIds(String moduleId, List<String> routeViewIds) throws Exception;

    /**
     * 根据模板ID批量删除路由视图模板数据
     *
     * @param moduleId
     * @param templateId
     * @throws Exception
     */
    void deleteListRouteViewTemplateByTemplateId(String moduleId, String templateId) throws Exception;

    /**
     * 根据模板ID批量删除路由视图模板数据
     *
     * @param moduleId
     * @param templateIds
     * @throws Exception
     */
    void deleteListRouteViewTemplateByTemplateIds(String moduleId, List<String> templateIds) throws Exception;

    /**
     * 根据路由视图模板ID删除路由视图模板数据
     *
     * @param moduleId
     * @param routeViewTemplateId
     * @throws Exception
     */
    void deleteRouteViewTemplateByRouteViewTemplateId(String moduleId, String routeViewTemplateId) throws Exception;

    /**
     * 根据路由视图模板ID批量删除路由视图模板数据
     *
     * @param moduleId
     * @param routeViewTemplateIds
     * @throws Exception
     */
    void deleteListRouteViewTemplateByRouteViewTemplateIds(String moduleId, Set<String> routeViewTemplateIds) throws Exception;

    /**
     * 根据路由视图ID、模板ID删除路由视图模板数据
     *
     * @param moduleId
     * @param routeViewId
     * @param templateId
     * @throws Exception
     */
    void deleteRouteViewTemplateByRouteViewIdAndTemplateId(String moduleId, String routeViewId, String templateId) throws Exception;
}