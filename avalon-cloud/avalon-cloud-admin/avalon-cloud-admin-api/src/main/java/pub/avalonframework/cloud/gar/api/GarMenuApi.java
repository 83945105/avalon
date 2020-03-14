package pub.avalonframework.cloud.gar.api;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pub.avalon.holygrail.response.views.DataView;
import pub.avalonframework.cloud.gar.beans.MenuDragParams;
import pub.avalonframework.cloud.gar.dc.MenuGet;
import pub.avalonframework.cloud.gar.dc.MenuPost;
import pub.avalonframework.cloud.gar.dc.MenuPut;
import pub.avalonframework.cloud.gar.entity.Menu;
import pub.avalonframework.web.spring.http.response.view.impl.EntityLimitMessageView;
import pub.avalonframework.web.spring.http.response.view.impl.EntityMessageView;

import java.util.List;

/**
 * 菜单接口
 *
 * @author 白超
 */
@FeignClient(name = "${feign.gar.menu-api-service-name:gar-service}", path = "${feign.gar.menu-api-service-path:/api-gar-menu}")
public interface GarMenuApi {

    String ROOT_PATH = "/gar/api-gar-menu";

    /**
     * 根据子模块ID查询菜单唯一标识符是否可用
     *
     * @param value
     * @param subModuleId
     * @param excludeValues
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/validateValueCanUseBySubModuleId/{value}/{subModuleId}")
    @RequestLine("GET " + ROOT_PATH + "/get/validateValueCanUseBySubModuleId/{value}/{subModuleId}?excludeValues={excludeValues}&moduleId={moduleId}")
    EntityMessageView<Boolean> getValidateValueCanUseBySubModuleId(@PathVariable("value") @Param("value") String value,
                                                                   @PathVariable("subModuleId") @Param("subModuleId") String subModuleId,
                                                                   @RequestParam("excludeValues") @Param("excludeValues") String excludeValues,
                                                                   @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据菜单ID获取菜单详情
     *
     * @param menuId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/menuByMenuId/{menuId}")
    @RequestLine("GET " + ROOT_PATH + "/get/menuByMenuId/{menuId}?moduleId={moduleId}")
    EntityMessageView<Menu> getMenuByMenuId(@PathVariable("menuId") @Param("menuId") String menuId,
                                            @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 条件查询菜单集合
     *
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/menu")
    @RequestLine("GET " + ROOT_PATH + "/get/list/menu?moduleId={moduleId}")
    EntityMessageView<List<Menu>> getListMenu(@RequestParam("record") @QueryMap MenuGet record,
                                              @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 分页条件查询菜单集合
     *
     * @param record
     * @param currentPage
     * @param pageSize
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/pageList/menu")
    @RequestLine("GET " + ROOT_PATH + "/get/pageList/menu?currentPage={currentPage}&pageSize={pageSize}&moduleId={moduleId}")
    EntityLimitMessageView<List<Menu>> getPageListMenu(@RequestParam("record") @QueryMap MenuGet record,
                                                       @RequestParam("currentPage") @Param("currentPage") Integer currentPage,
                                                       @RequestParam("pageSize") @Param("pageSize") Integer pageSize,
                                                       @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据子模块ID条件查询菜单集合
     *
     * @param subModuleId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/menuBySubModuleId/{subModuleId}")
    @RequestLine("GET " + ROOT_PATH + "/get/list/menuBySubModuleId/{subModuleId}")
    DataView getListMenuBySubModuleId(@PathVariable("subModuleId") @Param("subModuleId") String subModuleId,
                                      @RequestParam("record") @QueryMap MenuGet record,
                                      @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据子模块ID、菜单组ID条件查询根菜单集合
     *
     * @param subModuleId
     * @param menuGroupId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/rootMenuBySubModuleIdAndMenuGroupId/{subModuleId}/{menuGroupId}")
    @RequestLine("GET " + ROOT_PATH + "/get/list/rootMenuBySubModuleIdAndMenuGroupId/{subModuleId}/{menuGroupId}")
    DataView getListRootMenuBySubModuleIdAndMenuGroupId(@PathVariable("subModuleId") @Param("subModuleId") String subModuleId,
                                                        @PathVariable("menuGroupId") @Param("menuGroupId") String menuGroupId,
                                                        @RequestParam("record") @QueryMap MenuGet record,
                                                        @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据父级菜单ID条件查询菜单集合
     *
     * @param parentMenuId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/list/menuByParentId/{parentMenuId}")
    @RequestLine("GET " + ROOT_PATH + "/get/list/menuByParentId/{parentMenuId}")
    DataView getListMenuByParentMenuId(@PathVariable("parentMenuId") @Param("parentMenuId") String parentMenuId,
                                       @RequestParam("record") @QueryMap MenuGet record,
                                       @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据子模块唯一标识符、菜单组类型获取菜单树数据
     *
     * @param subModuleValue
     * @param menuGroupType
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/menuTreeBySubModuleValueAndMenuGroupType/{subModuleValue}/{menuGroupType}")
    @RequestLine("GET " + ROOT_PATH + "/get/menuTreeBySubModuleValueAndMenuGroupType/{subModuleValue}/{menuGroupType}?moduleId={moduleId}")
    EntityMessageView<List<MenuGet>> getMenuTreeBySubModuleValueAndMenuGroupType(@PathVariable("subModuleValue") @Param("subModuleValue") String subModuleValue,
                                                                                 @PathVariable("menuGroupType") @Param("menuGroupType") String menuGroupType,
                                                                                 @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 新增菜单
     *
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/post/menu")
    @RequestLine("POST " + ROOT_PATH + "/post/menu?moduleId={moduleId}")
    DataView postMenu(@RequestParam("record") @QueryMap MenuPost record,
                      @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据菜单ID修改菜单数据
     *
     * @param menuId
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/put/menuByMenuId/{menuId}")
    @RequestLine("PUT " + ROOT_PATH + "/put/menuByMenuId/{menuId}?moduleId={moduleId}")
    DataView putMenuByMenuId(@PathVariable("menuId") @Param("menuId") String menuId,
                             @RequestParam("record") @QueryMap MenuPut record,
                             @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据菜单ID交换菜单index属性(排序用)
     *
     * @param sourceMenuId
     * @param targetMenuId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/put/switchMenuIndexByMenuId/{sourceMenuId}/{targetMenuId}")
    @RequestLine("PUT " + ROOT_PATH + "/put/switchMenuIndexByMenuId/{sourceMenuId}/{targetMenuId}?moduleId={moduleId}")
    DataView putSwitchMenuIndexByMenuId(@PathVariable("sourceMenuId") @Param("sourceMenuId") String sourceMenuId,
                                        @PathVariable("targetMenuId") @Param("targetMenuId") String targetMenuId,
                                        @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据菜单ID删除菜单数据
     *
     * @param menuId
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/menuByMenuId/{menuId}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/menuByMenuId/{menuId}?moduleId={moduleId}")
    DataView deleteMenuByMenuId(@PathVariable("menuId") @Param("menuId") String menuId,
                                @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 根据菜单ID批量删除菜单数据
     *
     * @param menuIds
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/list/menuByMenuIds/{menuIds}")
    @RequestLine("DELETE " + ROOT_PATH + "/delete/list/menuByMenuIds/{menuIds}?moduleId={moduleId}")
    DataView deleteListMenuByMenuIds(@PathVariable("menuIds") @Param("menuIds") String menuIds,
                                     @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;

    /**
     * 拖拽菜单树节点
     *
     * @param dragMenuId
     * @param dropMenuId
     * @param dropType
     * @param record
     * @param moduleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/put/dragMenuTreeNode/{dragMenuId}/{dropMenuId}/{dropType}")
    @RequestLine("PUT " + ROOT_PATH + "/put/dragMenuTreeNode/{dragMenuId}/{dropMenuId}/{dropType}?moduleId={moduleId}")
    DataView putDragMenuTreeNode(@PathVariable("dragMenuId") @Param("dragMenuId") String dragMenuId,
                                 @PathVariable("dropMenuId") @Param("dropMenuId") String dropMenuId,
                                 @PathVariable("dropType") @Param("dropType") String dropType,
                                 @RequestParam("record") @QueryMap MenuDragParams record,
                                 @RequestParam("moduleId") @Param("moduleId") String moduleId) throws Exception;
}
