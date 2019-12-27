package pub.avalonframework.cloud.gar.service;

import pub.avalonframework.cloud.gar.dc.RouteGet;
import pub.avalonframework.cloud.gar.dc.RoutePost;
import pub.avalonframework.cloud.gar.dc.RoutePut;
import pub.avalonframework.cloud.gar.entity.Route;

import java.util.List;

/**
 * @author 白超
 * @date 2018/12/6
 */
public interface GarRouteService {

    /**
     * 查询路由唯一标识符是否可用
     *
     * @param moduleId
     * @param subModuleId
     * @param value
     * @param excludeValues
     * @return
     * @throws Exception
     */
    boolean getValidateValueCanUseBySubModuleId(String moduleId, String subModuleId, String value, List<String> excludeValues) throws Exception;

    /**
     * 查询路由地址是否可用
     *
     * @param moduleId
     * @param subModuleId
     * @param path
     * @param excludePaths
     * @return
     * @throws Exception
     */
    boolean getValidatePathCanUseBySubModuleId(String moduleId, String subModuleId, String path, List<String> excludePaths) throws Exception;

    /**
     * 查询路由别名是否可用
     *
     * @param moduleId
     * @param subModuleId
     * @param alias
     * @param excludeAliases
     * @return
     * @throws Exception
     */
    boolean getValidateAliasCanUseBySubModuleId(String moduleId, String subModuleId, String alias, List<String> excludeAliases) throws Exception;

    /**
     * 新增路由
     *
     * @param moduleId
     * @param record
     * @return
     * @throws Exception
     */
    RouteGet postRoute(String moduleId, RoutePost record) throws Exception;

    /**
     * 根据路由ID修改路由数据
     *
     * @param moduleId
     * @param routeId
     * @param record
     * @return
     * @throws Exception
     */
    void putRouteByRouteId(String moduleId, String routeId, RoutePut record) throws Exception;

    /**
     * 根据模块ID、子模块ID批量修改路由数据
     *
     * @param moduleId
     * @param subModuleId
     * @param routeUpdate
     * @throws Exception
     */
    void putListRouteByModuleIdAndSubModuleId(String moduleId, String subModuleId, Route routeUpdate) throws Exception;

    /**
     * 根据路由ID交换路由index属性(排序用)
     *
     * @param moduleId
     * @param sourceRouteId
     * @param targetRouteId
     * @throws Exception
     */
    void putSwitchRouteIndexByRouteId(String moduleId, String sourceRouteId, String targetRouteId) throws Exception;

    /**
     * 根据路由ID删除路由数据
     *
     * @param moduleId
     * @param routeId
     * @return
     * @throws Exception
     */
    void deleteRouteByRouteId(String moduleId, String routeId) throws Exception;

    /**
     * 根据子模块ID批量删除路由数据
     *
     * @param moduleId
     * @param subModuleId
     * @throws Exception
     */
    void deleteListRouteBySubModuleId(String moduleId, String subModuleId) throws Exception;

    /**
     * 根据子模块ID批量删除路由数据
     *
     * @param moduleId
     * @param subModuleIds
     * @throws Exception
     */
    void deleteListRouteBySubModuleIds(String moduleId, List<String> subModuleIds) throws Exception;


    /**
     * 根据路由ID批量删除路由数据
     *
     * @param moduleId
     * @param routeIds
     * @return
     * @throws Exception
     */
    void deleteListRouteByRouteIds(String moduleId, List<String> routeIds) throws Exception;
}