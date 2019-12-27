package pub.avalonframework.cloud.gar.utils;

import pub.avalon.beans.Time;
import pub.avalon.holygrail.response.beans.Status;
import pub.avalon.holygrail.response.utils.ExceptionUtil;
import pub.avalon.holygrail.utils.StringUtil;
import pub.avalonframework.cloud.gar.beans.AutRoleType;
import pub.avalonframework.cloud.gar.entity.AutRole;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author 白超
 * @version 1.0
 * @since 2018/7/11
 */
public class TableUtils {

    /**
     * 本项目表名前缀
     */
    public static final String TABLE_PREFIX = "gar";

    /**
     * 模块ID正则匹配符
     */
    public static final String MODULE_ID_REGEX = "^[a-zA-Z]+(-[a-zA-Z]+|[a-zA-Z]*)$";

    /**
     * 路由地址正则匹配符
     */
    public static final String ZUUL_PATH_REGEX = "^/([a-zA-Z]+[a-zA-Z-_]*)/\\*\\*$";

    /**
     * 服务ID正则匹配符
     */
    public static final String SERVICE_ID_REGEX = "^[a-zA-Z]+(-[a-zA-Z]+|[a-zA-Z]*)-service$";

    /**
     * 路由唯一标识符正则匹配符
     */
    public static final String ROUTE_VALUE_REGEX = "^([a-zA-Z0-9_]+)";

    /**
     * 路由地址正则匹配符
     */
    public static final String ROUTE_PATH_REGEX = "^(([a-zA-Z0-9_\\-]*|\\/|\\/:)([a-zA-Z0-9_\\-])+)+$";

    /**
     * 菜单唯一标识符正则匹配符
     */
    public static final String MENU_VALUE_REGEX = "^([a-zA-Z0-9_]+)";

    /**
     * 基础模块ID
     */
    public static final String GAR_MODULE_ID = "gar";

    /**
     * 基础网关地址
     */
    public static final String GAR_ZUUL_PATH = "/route-gar/**";

    /**
     * 基础服务ID
     */
    public static final String GAR_ZUUL_SERVICE_ID = "gar-service";

    /**
     * 模块ID键值
     * 用于从请求参数中获取模块ID,从而计算出要操作的表名
     */
    public static final String MODULE_ID_KEY = "moduleId";

    /**
     * 资源表表名正则匹配符
     */
    public static final String RESOURCE_TABLE_NAME_REGEX = "^" + TABLE_PREFIX + "_(.*?)_aut_resource$";

    /**
     * 角色表表名正则匹配符
     */
    public static final String ROLE_TABLE_NAME_REGEX = "^" + TABLE_PREFIX + "_(.*?)_aut_role$";

    /**
     * 角色资源表表名正则匹配符
     */
    public static final String ROLE_RESOURCE_TABLE_NAME_REGEX = "^" + TABLE_PREFIX + "_(.*?)_aut_role_resource$";

    /**
     * 对象角色表表名正则匹配符
     */
    public static final String OBJECT_ROLE_TABLE_NAME_REGEX = "^" + TABLE_PREFIX + "_(.*?)_aut_object_role$";

    /**
     * 角色菜单表表名正则匹配符
     */
    public static final String ROLE_MENU_TABLE_NAME_REGEX = "^" + TABLE_PREFIX + "_(.*?)_aut_role_menu$";

    /**
     * 角色路由视图模板表表名正则匹配符
     */
    public static final String ROLE_ROUTE_VIEW_TEMPLATE_TABLE_NAME_REGEX = "^" + TABLE_PREFIX + "_(.*?)_aut_role_route_view_template$";

    /**
     * 基础资源表表名
     */
    public static final String GAR_RESOURCE_TABLE_NAME = getResourceTableName(GAR_MODULE_ID);

    /**
     * 基础角色表表名
     */
    public static final String GAR_ROLE_TABLE_NAME = getRoleTableName(GAR_MODULE_ID);

    /**
     * 基础角色资源表表名
     */
    public static final String GAR_ROLE_RESOURCE_TABLE_NAME = getRoleResourceTableName(GAR_MODULE_ID);

    /**
     * 基础对象角色表表名
     */
    public static final String GAR_OBJECT_ROLE_TABLE_NAME = getObjectRoleTableName(GAR_MODULE_ID);

    /**
     * 基础角色菜单表表名
     */
    public static final String GAR_ROLE_MENU_TABLE_NAME = getRoleMenuTableName(GAR_MODULE_ID);

    /**
     * 基础角色路由视图模板表表名
     */
    public static final String GAR_ROLE_ROUTE_VIEW_TEMPLATE_TABLE_NAME = getRoleRouteViewTemplateTableName(GAR_MODULE_ID);

    /**
     * 开发者角色唯一标识符
     */
    public static final String GAR_DEVELOPER_ROLE_VALUE = "gar_developer";

    /**
     * 开发者角色
     */
    public static final AutRole GAR_DEVELOPER_ROLE = new AutRole();

    static {
        GAR_DEVELOPER_ROLE.setId(GAR_DEVELOPER_ROLE_VALUE);
        GAR_DEVELOPER_ROLE.setName("开发者");
        GAR_DEVELOPER_ROLE.setValue(GAR_DEVELOPER_ROLE_VALUE);
        GAR_DEVELOPER_ROLE.setType(AutRoleType.LOCAL.name());
        GAR_DEVELOPER_ROLE.setStatus(Status.NORMAL.name());
    }


    /**
     * 获取重命名表名
     *
     * @param tableName
     * @return
     */
    public static String getRenameTableName(String tableName) {
        return tableName + "_bak_" + Time.localDateNow().replaceAll("-", "");
    }

    /**
     * 获取模块ID
     *
     * @param moduleId 模块ID
     * @param request
     * @return
     */
    public static String getModuleId(String moduleId, HttpServletRequest request) {
        if (StringUtil.isEmpty(moduleId)) {
            moduleId = request.getHeader(MODULE_ID_KEY);
        }
        if (StringUtil.isEmpty(moduleId)) {
            moduleId = request.getParameter(MODULE_ID_KEY);
        }
        if (StringUtil.isEmpty(moduleId)) {
            ExceptionUtil.throwFailException("未设置模块ID");
        }
        return moduleId;
    }

    /**
     * 获取资源表表名
     *
     * @param moduleId 模块ID
     * @return
     */
    public static String getResourceTableName(String moduleId) {
        return TABLE_PREFIX + "_" + moduleId + "_aut_resource";
    }

    /**
     * 获取资源表表名
     *
     * @param moduleId 模块ID
     * @param request
     * @return
     * @throws Exception
     */
    public static String getResourceTableName(String moduleId, HttpServletRequest request) throws Exception {
        return TableUtils.getResourceTableName(getModuleId(moduleId, request));
    }

    /**
     * 获取角色表表名
     *
     * @param moduleId 模块ID
     * @return
     */
    public static String getRoleTableName(String moduleId) {
        return TABLE_PREFIX + "_" + moduleId + "_aut_role";
    }

    /**
     * 获取角色表表名
     *
     * @param moduleId 模块ID
     * @param request
     * @return
     * @throws Exception
     */
    public static String getRoleTableName(String moduleId, HttpServletRequest request) throws Exception {
        return TableUtils.getRoleTableName(getModuleId(moduleId, request));
    }

    /**
     * 获取角色资源表表名
     *
     * @param moduleId 模块ID
     * @return
     */
    public static String getRoleResourceTableName(String moduleId) {
        return TABLE_PREFIX + "_" + moduleId + "_aut_role_resource";
    }

    /**
     * 获取角色资源表表名
     *
     * @param moduleId 模块ID
     * @param request
     * @return
     * @throws Exception
     */
    public static String getRoleResourceTableName(String moduleId, HttpServletRequest request) throws Exception {
        return TableUtils.getRoleResourceTableName(getModuleId(moduleId, request));
    }

    /**
     * 获取对象角色表表名
     *
     * @param moduleId 模块ID
     * @return
     */
    public static String getObjectRoleTableName(String moduleId) {
        return TABLE_PREFIX + "_" + moduleId + "_aut_object_role";
    }

    /**
     * 获取对象角色表表名
     *
     * @param moduleId 模块ID
     * @param request
     * @return
     * @throws Exception
     */
    public static String getObjectRoleTableName(String moduleId, HttpServletRequest request) throws Exception {
        return TableUtils.getObjectRoleTableName(getModuleId(moduleId, request));
    }

    /**
     * 获取角色菜单表表名
     *
     * @param moduleId 模块ID
     * @return
     */
    public static String getRoleMenuTableName(String moduleId) {
        return TABLE_PREFIX + "_" + moduleId + "_aut_role_menu";
    }

    /**
     * 获取角色菜单表表名
     *
     * @param moduleId 模块ID
     * @param request
     * @return
     */
    public static String getRoleMenuTableName(String moduleId, HttpServletRequest request) {
        return TableUtils.getRoleMenuTableName(getModuleId(moduleId, request));
    }

    /**
     * 获取角色路由视图模板表表名
     *
     * @param moduleId 模块ID
     * @return
     */
    public static String getRoleRouteViewTemplateTableName(String moduleId) {
        return TABLE_PREFIX + "_" + moduleId + "_aut_role_route_view_template";
    }

    /**
     * 获取角色路由视图模板表表名
     *
     * @param moduleId 模块ID
     * @param request
     * @return
     */
    public static String getRoleRouteViewTemplateTableName(String moduleId, HttpServletRequest request) {
        return TableUtils.getRoleRouteViewTemplateTableName(getModuleId(moduleId, request));
    }

    /**
     * 模块表排序号间隔
     */
    public static final Long MODULE_INDEX_INTERVAL = 100L;

    /**
     * 获取模块表排序号
     *
     * @param index
     * @return
     */
    public static long getModuleIndex(Long index) {
        return index == null ? 0 : index + MODULE_INDEX_INTERVAL;
    }

    /**
     * 资源表排序号间隔
     */
    public static final Long RESOURCE_INDEX_INTERVAL = 100L;

    /**
     * 获取资源表排序号
     *
     * @param index
     * @return
     */
    public static long getResourceIndex(Long index) {
        return index == null ? 0 : index + RESOURCE_INDEX_INTERVAL;
    }

    /**
     * 角色表排序号间隔
     */
    public static final Long ROLE_INDEX_INTERVAL = 100L;

    /**
     * 获取角色表排序号
     *
     * @param index
     * @return
     */
    public static long getRoleIndex(Long index) {
        return index == null ? 0 : index + ROLE_INDEX_INTERVAL;
    }

    /**
     * 子模块表排序号间隔
     */
    public static final Long SUB_MODULE_INDEX_INTERVAL = 100L;

    /**
     * 获取子模块表排序号
     *
     * @param index
     * @return
     */
    public static long getSubModuleIndex(Long index) {
        return index == null ? 0 : index + SUB_MODULE_INDEX_INTERVAL;
    }

    /**
     * 模板表排序号间隔
     */
    public static final Long TEMPLATE_INDEX_INTERVAL = 100L;

    /**
     * 获取模板表排序号
     *
     * @param index
     * @return
     */
    public static long getTemplateIndex(Long index) {
        return index == null ? 0 : index + TEMPLATE_INDEX_INTERVAL;
    }

    /**
     * 路由表排序号间隔
     */
    public static final Long ROUTE_INDEX_INTERVAL = 100L;

    /**
     * 获取路由表排序号
     *
     * @param index
     * @return
     */
    public static long getRouteIndex(Long index) {
        return index == null ? 0 : index + ROUTE_INDEX_INTERVAL;
    }

    /**
     * 路由视图表排序号间隔
     */
    public static final Long ROUTE_VIEW_INDEX_INTERVAL = 100L;

    /**
     * 获取路由视图表排序号
     *
     * @param index
     * @return
     */
    public static long getRouteViewIndex(Long index) {
        return index == null ? 0 : index + ROUTE_VIEW_INDEX_INTERVAL;
    }

    /**
     * 菜单组表排序号间隔
     */
    public static final Long MENU_GROUP_INDEX_INTERVAL = 100L;

    /**
     * 获取菜单组表排序号
     *
     * @param index
     * @return
     */
    public static long getMenuGroupIndex(Long index) {
        return index == null ? 0 : index + MENU_GROUP_INDEX_INTERVAL;
    }

    /**
     * 菜单表排序号间隔
     */
    public static final Long MENU_INDEX_INTERVAL = 100L;

    /**
     * 获取菜单表排序号
     *
     * @param index
     * @return
     */
    public static long getMenuIndex(Long index) {
        return index == null ? 0 : index + MENU_INDEX_INTERVAL;
    }

    /**
     * 构建资源表批次号
     *
     * @return
     */
    public static String buildResourceBatchId() {
        return UUID.randomUUID().toString();
    }

}
