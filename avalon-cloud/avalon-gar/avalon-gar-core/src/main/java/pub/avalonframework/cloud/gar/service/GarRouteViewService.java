package pub.avalonframework.cloud.gar.service;

import pub.avalonframework.cloud.gar.dc.RouteViewGet;
import pub.avalonframework.cloud.gar.dc.RouteViewPost;
import pub.avalonframework.cloud.gar.dc.RouteViewPut;
import pub.avalonframework.cloud.gar.entity.RouteView;

import java.util.List;

/**
 * @author 白超
 * @date 2018/12/6
 */
public interface GarRouteViewService {

    /**
     * 查询路由视图唯一标识符是否可用
     *
     * @param moduleId
     * @param routeId
     * @param value
     * @param excludeValues
     * @return
     * @throws Exception
     */
    boolean getValidateValueCanUseByRouteId(String moduleId, String routeId, String value, List<String> excludeValues) throws Exception;

    /**
     * 新增路由视图
     *
     * @param moduleId
     * @param record
     * @return
     * @throws Exception
     */
    RouteViewGet postRouteView(String moduleId, RouteViewPost record) throws Exception;

    /**
     * 批量新增路由视图
     *
     * @param moduleId
     * @param records
     * @throws Exception
     */
    void postListRouteView(String moduleId, List<RouteViewPost> records) throws Exception;

    /**
     * 根据路由视图ID修改路由视图数据
     *
     * @param moduleId
     * @param routeViewId
     * @param record
     * @return
     * @throws Exception
     */
    void putRouteViewByRouteViewId(String moduleId, String routeViewId, RouteViewPut record) throws Exception;

    /**
     * 根据模块ID、路由ID批量修改路由视图数据
     *
     * @param moduleId
     * @param routeId
     * @param routeViewUpdate
     * @throws Exception
     */
    void putListRouteViewByModuleIdAndRouteId(String moduleId, String routeId, RouteView routeViewUpdate) throws Exception;

    /**
     * 根据模块ID、路由ID批量修改路由视图数据
     *
     * @param moduleId
     * @param routeIds
     * @param routeViewUpdate
     * @throws Exception
     */
    void putListRouteViewByModuleIdAndRouteIds(String moduleId, List<String> routeIds, RouteView routeViewUpdate) throws Exception;

    /**
     * 根据路由视图ID交换路由视图index属性(排序用)
     *
     * @param moduleId
     * @param sourceRouteViewId
     * @param targetRouteViewId
     * @throws Exception
     */
    void putSwitchRouteViewIndexByRouteViewId(String moduleId, String sourceRouteViewId, String targetRouteViewId) throws Exception;

    /**
     * 根据路由视图ID删除路由视图数据
     *
     * @param moduleId
     * @param routeViewId
     * @return
     * @throws Exception
     */
    void deleteRouteViewByRouteViewId(String moduleId, String routeViewId) throws Exception;

    /**
     * 根据路由ID批量删除路由视图数据
     *
     * @param moduleId
     * @param routeId
     * @throws Exception
     */
    void deleteListRouteViewByRouteId(String moduleId, String routeId) throws Exception;

    /**
     * 根据路由ID批量删除路由视图数据
     *
     * @param moduleId
     * @param routeIds
     * @throws Exception
     */
    void deleteListRouteViewByRouteIds(String moduleId, List<String> routeIds) throws Exception;


    /**
     * 根据路由视图ID批量删除路由视图数据
     *
     * @param moduleId
     * @param routeViewIds
     * @return
     * @throws Exception
     */
    void deleteListRouteViewByRouteViewIds(String moduleId, List<String> routeViewIds) throws Exception;
}