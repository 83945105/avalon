package pub.avalonframework.cloud.gar.api;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalonframework.cloud.gar.dc.MenuGroupGet;
import pub.avalonframework.cloud.gar.dc.MenuGroupPost;
import pub.avalonframework.cloud.gar.dc.MenuGroupPut;

/**
 * 菜单组接口
 *
 * @author baichao
 * @date 2018/12/7
 */
@FeignClient(name = "${feign.gar.menu-group-api-service-name:gar-service}", path = "${feign.gar.menu-group-api-service-path:/api-gar-menu-group}")
public interface GarMenuGroupApi {

    String ROOT_PATH = "/api-gar-menu-group";

    /**
     * 根据菜单组ID获取菜单组详情
     *
     * @param menuGroupId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/menuGroupByMenuGroupId/{menuGroupId}")
    @RequestLine("GET " + ROOT_PATH + "/get/menuGroupByMenuGroupId/{menuGroupId}?moduleId={moduleId}")
    DataView getMenuGroupByMenuGroupId(@PathVariable("menuGroupId") @Param("menuGroupId") String menuGroupId,
                                       @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 条件查询菜单组集合
     *
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/menuGroup")
    @RequestLine("GET " + ROOT_PATH + "/get/list/menuGroup?moduleId={moduleId}")
    DataView getListMenuGroup(@RequestParam("record") @QueryMap MenuGroupGet record,
                              @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 分页条件查询菜单组集合
     *
     * @param record
     * @param currentPage
     * @param pageSize
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/pageList/menuGroup")
    @RequestLine("GET " + ROOT_PATH + "/get/pageList/menuGroup?currentPage={currentPage}&pageSize={pageSize}&moduleId={moduleId}")
    DataView getPageListMenuGroup(@RequestParam("record") @QueryMap MenuGroupGet record,
                                  @RequestParam("currentPage") @Param("currentPage") Integer currentPage,
                                  @RequestParam("pageSize") @Param("pageSize") Integer pageSize,
                                  @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据子模块ID条件查询菜单组集合
     *
     * @param subModuleId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/menuGroupBySubModuleId/{subModuleId}")
    @RequestLine("GET " + ROOT_PATH + "/get/list/menuGroupBySubModuleId/{subModuleId}")
    DataView getListMenuGroupBySubModuleId(@PathVariable("subModuleId") @Param("subModuleId") String subModuleId,
                                           @RequestParam("record") @QueryMap MenuGroupGet record,
                                           @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 新增菜单组
     *
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/post/menuGroup")
    @RequestLine("POST " + ROOT_PATH + "/post/menuGroup?moduleId={moduleId}")
    DataView postMenuGroup(@RequestParam("record") @QueryMap MenuGroupPost record,
                           @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据菜单组ID修改菜单组数据
     *
     * @param menuGroupId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/put/menuGroupByMenuGroupId/{menuGroupId}")
    @RequestLine("PUT " + ROOT_PATH + "/put/menuGroupByMenuGroupId/{menuGroupId}?moduleId={moduleId}")
    DataView putMenuGroupByMenuGroupId(@PathVariable("menuGroupId") @Param("menuGroupId") String menuGroupId,
                                       @RequestParam("record") @QueryMap MenuGroupPut record,
                                       @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据菜单组ID修改菜单组是否使用
     *
     * @param menuGroupId
     * @param menuGroupUse
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/put/menuGroupUseByMenuGroupId/{menuGroupId}/{menuGroupUse}")
    @RequestLine("PUT " + ROOT_PATH + "/put/menuGroupUseByMenuGroupId/{menuGroupId}/{menuGroupUse}")
    DataView putMenuGroupUseByMenuGroupId(@PathVariable("menuGroupId") @Param("menuGroupId") String menuGroupId,
                                          @PathVariable("menuGroupUse") @Param("menuGroupUse") String menuGroupUse,
                                          @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据菜单组ID交换菜单组index属性(排序用)
     *
     * @param sourceMenuGroupId
     * @param targetMenuGroupId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/put/switchMenuGroupIndexByMenuGroupId/{sourceMenuGroupId}/{targetMenuGroupId}")
    @RequestLine("PUT " + ROOT_PATH + "/put/switchMenuGroupIndexByMenuGroupId/{sourceMenuGroupId}/{targetMenuGroupId}?moduleId={moduleId}")
    DataView putSwitchMenuGroupIndexByMenuGroupId(@PathVariable("sourceMenuGroupId") @Param("sourceMenuGroupId") String sourceMenuGroupId,
                                                  @PathVariable("targetMenuGroupId") @Param("targetMenuGroupId") String targetMenuGroupId,
                                                  @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据菜单组ID删除菜单组数据
     *
     * @param menuGroupId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/menuGroupByMenuGroupId/{menuGroupId}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/menuGroupByMenuGroupId/{menuGroupId}?moduleId={moduleId}")
    DataView deleteMenuGroupByMenuGroupId(@PathVariable("menuGroupId") @Param("menuGroupId") String menuGroupId,
                                          @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据菜单组ID批量删除菜单组数据
     *
     * @param menuGroupIds
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/list/menuGroupByMenuGroupIds/{menuGroupIds}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/list/menuGroupByMenuGroupIds/{menuGroupIds}?moduleId={moduleId}")
    DataView deleteListMenuGroupByMenuGroupIds(@PathVariable("menuGroupIds") @Param("menuGroupIds") String menuGroupIds,
                                               @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

}
