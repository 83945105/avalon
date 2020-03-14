package pub.avalonframework.cloud.gar.service;

import pub.avalonframework.cloud.gar.dc.AutResourceGet;
import pub.avalonframework.cloud.gar.dc.AutResourcePost;
import pub.avalonframework.cloud.gar.dc.AutResourcePut;

import java.util.List;

/**
 * 资源服务
 *
 * @author 白超
 */
public interface GarResourceService {

    /**
     * 新增资源
     *
     * @param moduleId
     * @param record
     * @return
     * @throws Exception
     */
    List<AutResourceGet> postResource(String moduleId, AutResourcePost record) throws Exception;

    /**
     * 修改资源
     *
     * @param moduleId
     * @param resourceId
     * @param record
     * @throws Exception
     */
    void putResourceByResourceId(String moduleId, String resourceId, AutResourcePut record) throws Exception;

    /**
     * 根据资源ID删除资源
     *
     * @param moduleId
     * @param resourceId
     * @throws Exception
     */
    void deleteResourceByResourceId(String moduleId, String resourceId) throws Exception;

    /**
     * 根据资源ID批量删除资源
     *
     * @param moduleId
     * @param resourceIds
     * @throws Exception
     */
    void deleteListResourceByResourceIds(String moduleId, List<String> resourceIds) throws Exception;
}