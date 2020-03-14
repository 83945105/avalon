package pub.avalonframework.cloud.gar.api;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalonframework.cloud.gar.dc.AutRoleGet;
import pub.avalonframework.cloud.gar.dc.AutRolePost;
import pub.avalonframework.cloud.gar.dc.AutRolePut;

/**
 * 角色接口
 *
 * @author 白超
 */
@FeignClient(name = "${feign.gar.role-api-service-name:gar-service}", path = "${feign.gar.role-api-service-path:/api-gar-role}")
public interface GarRoleApi {

    String ROOT_PATH = "/gar/api-gar-role";

    /**
     * 查询角色唯一标识符是否可用
     *
     * @param value
     * @param excludeValues
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/validateValueCanUse/{value}")
    @RequestLine("GET " + ROOT_PATH + "/get/validateValueCanUse/{value}?excludeValues={excludeValues}&moduleId={moduleId}")
    DataView getValidateValueCanUse(@PathVariable("value") @Param("value") String value,
                                    @RequestParam("excludeValues") @Param("excludeValues") String excludeValues,
                                    @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 条件查询角色集合
     *
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/role")
    @RequestLine("GET " + ROOT_PATH + "/get/list/role?moduleId={moduleId}")
    DataView getListRole(@RequestParam("record") @QueryMap AutRoleGet record,
                         @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;


    /**
     * 分页条件查询角色集合
     *
     * @param record
     * @param currentPage
     * @param pageSize
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/pageList/role")
    @RequestLine("GET " + ROOT_PATH + "/get/pageList/role?currentPage={currentPage}&pageSize={pageSize}&moduleId={moduleId}")
    DataView getPageListRole(@RequestParam("record") @QueryMap AutRoleGet record,
                             @RequestParam("currentPage") @Param("currentPage") Integer currentPage,
                             @RequestParam("pageSize") @Param("pageSize") Integer pageSize,
                             @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 新增角色
     *
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/post/role")
    @RequestLine("POST " + ROOT_PATH + "/post/role?moduleId={moduleId}")
    DataView postRole(@RequestParam("record") @QueryMap AutRolePost record,
                      @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据角色ID修改角色数据
     *
     * @param roleId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/put/roleByRoleId/{roleId}")
    @RequestLine("PUT " + ROOT_PATH + "/put/roleByRoleId/{roleId}?moduleId={moduleId}")
    DataView putRoleByRoleId(@PathVariable("roleId") @Param("roleId") String roleId,
                             @RequestParam("record") @QueryMap AutRolePut record,
                             @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据角色ID删除角色数据
     *
     * @param roleId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/roleByRoleId/{roleId}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/roleByRoleId/{roleId}?moduleId={moduleId}")
    DataView deleteRoleByRoleId(@PathVariable("roleId") @Param("roleId") String roleId,
                                @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据角色ID批量删除角色数据
     *
     * @param roleIds
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/list/roleByRoleIds/{roleIds}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/list/roleByRoleIds/{roleIds}?moduleId={moduleId}")
    DataView deleteListRoleByRoleIds(@PathVariable("roleIds") @Param("roleIds") String roleIds,
                                     @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

}
