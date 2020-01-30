package pub.avalonframework.cloud.gar.api;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalonframework.cloud.gar.dc.AutObjectRoleGet;

/**
 * 对象角色接口
 *
 * @author 白超
 * @date 2018/7/25
 */
@FeignClient(name = "${feign.gar.object-role-api-service-name:gar-service}", path = "${feign.gar.object-role-api-service-path:/api-gar-object-role}")
public interface GarObjectRoleApi {

    String ROOT_PATH = "/gar/api-gar-object-role";

    /**
     * 根据对象ID条件查询对象角色集合
     *
     * @param objectId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/objectRoleByObjectId/{objectId}")
    @RequestLine("GET " + ROOT_PATH + "/get/list/objectRoleByObjectId/{objectId}?moduleId={moduleId}")
    DataView getListObjectRoleByObjectId(@PathVariable("objectId") @Param("objectId") String objectId,
                                         @RequestParam("record") @QueryMap AutObjectRoleGet record,
                                         @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据角色ID条件查询对象角色集合
     *
     * @param roleId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/objectRoleByRoleId/{roleId}")
    @RequestLine("GET " + ROOT_PATH + "/get/list/objectRoleByRoleId/{roleId}?moduleId={moduleId}")
    DataView getListObjectRoleByRoleId(@PathVariable("roleId") @Param("roleId") String roleId,
                                       @RequestParam("record") @QueryMap AutObjectRoleGet record,
                                       @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 批量查询拥有指定角色ID的对象ID集合
     *
     * @param roleId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/objectIdByRoleId/{roleId}")
    @RequestLine("GET " + ROOT_PATH + "/get/list/objectIdByRoleId/{roleId}?moduleId={moduleId}")
    DataView getListObjectIdByRoleId(@PathVariable("roleId") @Param("roleId") String roleId,
                                     @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 批量查询拥有指定角色标识符的对象ID集合
     *
     * @param roleValue
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/objectIdByRoleValue/{roleValue}")
    @RequestLine("GET " + ROOT_PATH + "/get/list/objectIdByRoleValue/{roleValue}?moduleId={moduleId}")
    DataView getListObjectIdByRoleValue(@PathVariable("roleValue") @Param("roleValue") String roleValue,
                                        @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 批量查询拥有指定对象ID的角色标识符
     *
     * @param objectId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/roleValueByObjectId/{objectId}")
    @RequestLine("GET " + ROOT_PATH + "/get/list/roleValueByObjectId/{objectId}?moduleId={moduleId}")
    DataView getListRoleValueByObjectId(@PathVariable("objectId") @Param("objectId") String objectId,
                                        @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 批量查询拥有指定对象ID的角色数据
     *
     * @param objectId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/roleByObjectId/{objectId}")
    @RequestLine("GET " + ROOT_PATH + "/get/list/roleByObjectId/{objectId}?moduleId={moduleId}")
    DataView getListRoleByObjectId(@PathVariable("objectId") @Param("objectId") String objectId,
                                   @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;


    /**
     * 根据对象ID、角色ID新增对象角色数据
     *
     * @param objectId
     * @param roleId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/post/objectRoleByObjectIdAndRoleId/{objectId}/{roleId}")
    @RequestLine("POST " + ROOT_PATH + "/post/objectRoleByObjectIdAndRoleId/{objectId}/{roleId}?moduleId={moduleId}")
    DataView postObjectRoleByObjectIdAndRoleId(@PathVariable("objectId") @Param("objectId") String objectId,
                                               @PathVariable("roleId") @Param("roleId") String roleId,
                                               @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;


    /**
     * 根据对象ID、角色ID批量新增对象角色数据
     *
     * @param objectIds
     * @param roleIds
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/post/list/objectRoleByObjectIdsAndRoleIds/{objectIds}/{roleIds}")
    @RequestLine("POST " + ROOT_PATH + "/post/objectRoleByObjectIdsAndRoleIds/{objectIds}/{roleIds}?moduleId={moduleId}")
    DataView postListObjectRoleByObjectIdsAndRoleIds(@PathVariable("objectIds") @Param("objectIds") String objectIds,
                                                     @PathVariable("roleIds") @Param("roleIds") String roleIds,
                                                     @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据对象ID、角色标识符新增对象角色数据
     *
     * @param objectId
     * @param roleValue
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/post/objectRoleByObjectIdAndRoleValue/{objectId}/{roleValue}")
    @RequestLine("POST " + ROOT_PATH + "/post/objectRoleByObjectIdAndRoleValue/{objectId}/{roleValue}?moduleId={moduleId}")
    DataView postObjectRoleByObjectIdAndRoleValue(@PathVariable("objectId") @Param("objectId") String objectId,
                                                  @PathVariable("roleValue") @Param("roleValue") String roleValue,
                                                  @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据对象角色ID删除对象角色数据
     *
     * @param objectRoleId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/objectRoleByObjectRoleId/{objectRoleId}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/objectRoleByObjectRoleId/{objectRoleId}?moduleId={moduleId}")
    DataView deleteObjectRoleByObjectRoleId(@PathVariable("objectRoleId") @Param("objectRoleId") String objectRoleId,
                                            @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据对象角色ID批量删除对象角色数据
     *
     * @param objectRoleIds
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/list/objectRoleByObjectRoleIds/{objectRoleIds}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/list/objectRoleByObjectRoleIds/{objectRoleIds}?moduleId={moduleId}")
    DataView deleteListObjectRoleByObjectRoleIds(@PathVariable("objectRoleIds") @Param("objectRoleIds") String objectRoleIds,
                                                 @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据对象ID、角色ID删除对象角色数据
     *
     * @param objectId
     * @param roleId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/objectRoleByObjectIdAndRoleId/{objectId}/{roleId}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/objectRoleByObjectIdAndRoleId/{objectId}/{roleId}?moduleId={moduleId}")
    DataView deleteObjectRoleByObjectIdAndRoleId(@PathVariable("objectId") @Param("objectId") String objectId,
                                                 @PathVariable("roleId") @Param("roleId") String roleId,
                                                 @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据对象ID、角色ID批量删除对象角色数据
     *
     * @param objectIds
     * @param roleIds
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/list/objectRoleByObjectIdsAndRoleIds/{objectIds}/{roleIds}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/list/objectRoleByObjectIdsAndRoleIds/{objectIds}/{roleIds}?moduleId={moduleId}")
    DataView deleteListObjectRoleByObjectIdsAndRoleIds(@PathVariable("objectIds") @Param("objectIds") String objectIds,
                                                       @PathVariable("roleIds") @Param("roleIds") String roleIds,
                                                       @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

}
