package pub.avalonframework.cloud.gar.model;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("all")
public final class AutRoleRouteViewTemplateModel implements Model<AutRoleRouteViewTemplateModel, AutRoleRouteViewTemplateModel.Column, AutRoleRouteViewTemplateModel.On, AutRoleRouteViewTemplateModel.Where, AutRoleRouteViewTemplateModel.Sort, AutRoleRouteViewTemplateModel.Group> {

    /**
     * 表名
     */
    public static final String tableName = "gar_aut_role_route_view_template";
    /**
     * 表别名
     */
    public static final String tableAlias = "AutRoleRouteViewTemplate";

    /**
     * 主键名
     */
    public static final String primaryKeyName = "id";
    /**
     * 主键别名
     */
    public static final String primaryKeyAlias = "id";



    /**
     * 主键ID
     */
    public static final String id = "id";
    /**
     * 主键ID
     */
    public static final String id_alias = "id";
    /**
     * 所属模块ID
     */
    public static final String moduleId = "module_id";
    /**
     * 所属模块ID
     */
    public static final String moduleId_alias = "moduleId";
    /**
     * 角色id
     */
    public static final String roleId = "role_id";
    /**
     * 角色id
     */
    public static final String roleId_alias = "roleId";
    /**
     * 角色值
     */
    public static final String roleValue = "role_value";
    /**
     * 角色值
     */
    public static final String roleValue_alias = "roleValue";
    /**
     * 角色名称
     */
    public static final String roleName = "role_name";
    /**
     * 角色名称
     */
    public static final String roleName_alias = "roleName";
    /**
     * 角色类型
     */
    public static final String roleType = "role_type";
    /**
     * 角色类型
     */
    public static final String roleType_alias = "roleType";
    /**
     * 路由ID
     */
    public static final String routeId = "route_id";
    /**
     * 路由ID
     */
    public static final String routeId_alias = "routeId";
    /**
     * 路由名称
     */
    public static final String routeName = "route_name";
    /**
     * 路由名称
     */
    public static final String routeName_alias = "routeName";
    /**
     * 路由唯一标识符
     */
    public static final String routeValue = "route_value";
    /**
     * 路由唯一标识符
     */
    public static final String routeValue_alias = "routeValue";
    /**
     * 路由地址
     */
    public static final String routePath = "route_path";
    /**
     * 路由地址
     */
    public static final String routePath_alias = "routePath";
    /**
     * 路由视图ID
     */
    public static final String routeViewId = "route_view_id";
    /**
     * 路由视图ID
     */
    public static final String routeViewId_alias = "routeViewId";
    /**
     * 路由视图名称
     */
    public static final String routeViewName = "route_view_name";
    /**
     * 路由视图名称
     */
    public static final String routeViewName_alias = "routeViewName";
    /**
     * 路由视图唯一标识符
     */
    public static final String routeViewValue = "route_view_value";
    /**
     * 路由视图唯一标识符
     */
    public static final String routeViewValue_alias = "routeViewValue";
    /**
     * 模板ID
     */
    public static final String templateId = "template_id";
    /**
     * 模板ID
     */
    public static final String templateId_alias = "templateId";
    /**
     * 模板名称
     */
    public static final String templateName = "template_name";
    /**
     * 模板名称
     */
    public static final String templateName_alias = "templateName";
    /**
     * 模板唯一标识符
     */
    public static final String templateValue = "template_value";
    /**
     * 模板唯一标识符
     */
    public static final String templateValue_alias = "templateValue";
    /**
     * 状态
     */
    public static final String status = "status";
    /**
     * 状态
     */
    public static final String status_alias = "status";
    /**
     * 创建时间
     */
    public static final String createTime = "create_time";
    /**
     * 创建时间
     */
    public static final String createTime_alias = "createTime";
    /**
     * 修改时间
     */
    public static final String updateTime = "update_time";
    /**
     * 修改时间
     */
    public static final String updateTime_alias = "updateTime";
    /**
     * 删除时间
     */
    public static final String deleteTime = "delete_time";
    /**
     * 删除时间
     */
    public static final String deleteTime_alias = "deleteTime";
    /**
     * 创建时间戳
     */
    public static final String createTimeStamp = "create_time_stamp";
    /**
     * 创建时间戳
     */
    public static final String createTimeStamp_alias = "createTimeStamp";
    /**
     * 修改时间戳
     */
    public static final String updateTimeStamp = "update_time_stamp";
    /**
     * 修改时间戳
     */
    public static final String updateTimeStamp_alias = "updateTimeStamp";
    /**
     * 删除时间戳
     */
    public static final String deleteTimeStamp = "delete_time_stamp";
    /**
     * 删除时间戳
     */
    public static final String deleteTimeStamp_alias = "deleteTimeStamp";

    /**
     * 字段-别名 集合
     */
    public static final Map<String, String> COLUMN_ALIAS_MAP = new LinkedHashMap<>();
    /**
     * 别名-字段 集合
     */
    public static final Map<String, String> ALIAS_COLUMN_MAP = new LinkedHashMap<>();

    static {
        COLUMN_ALIAS_MAP.put(id, id_alias);
        ALIAS_COLUMN_MAP.put(id_alias, id);
        COLUMN_ALIAS_MAP.put(moduleId, moduleId_alias);
        ALIAS_COLUMN_MAP.put(moduleId_alias, moduleId);
        COLUMN_ALIAS_MAP.put(roleId, roleId_alias);
        ALIAS_COLUMN_MAP.put(roleId_alias, roleId);
        COLUMN_ALIAS_MAP.put(roleValue, roleValue_alias);
        ALIAS_COLUMN_MAP.put(roleValue_alias, roleValue);
        COLUMN_ALIAS_MAP.put(roleName, roleName_alias);
        ALIAS_COLUMN_MAP.put(roleName_alias, roleName);
        COLUMN_ALIAS_MAP.put(roleType, roleType_alias);
        ALIAS_COLUMN_MAP.put(roleType_alias, roleType);
        COLUMN_ALIAS_MAP.put(routeId, routeId_alias);
        ALIAS_COLUMN_MAP.put(routeId_alias, routeId);
        COLUMN_ALIAS_MAP.put(routeName, routeName_alias);
        ALIAS_COLUMN_MAP.put(routeName_alias, routeName);
        COLUMN_ALIAS_MAP.put(routeValue, routeValue_alias);
        ALIAS_COLUMN_MAP.put(routeValue_alias, routeValue);
        COLUMN_ALIAS_MAP.put(routePath, routePath_alias);
        ALIAS_COLUMN_MAP.put(routePath_alias, routePath);
        COLUMN_ALIAS_MAP.put(routeViewId, routeViewId_alias);
        ALIAS_COLUMN_MAP.put(routeViewId_alias, routeViewId);
        COLUMN_ALIAS_MAP.put(routeViewName, routeViewName_alias);
        ALIAS_COLUMN_MAP.put(routeViewName_alias, routeViewName);
        COLUMN_ALIAS_MAP.put(routeViewValue, routeViewValue_alias);
        ALIAS_COLUMN_MAP.put(routeViewValue_alias, routeViewValue);
        COLUMN_ALIAS_MAP.put(templateId, templateId_alias);
        ALIAS_COLUMN_MAP.put(templateId_alias, templateId);
        COLUMN_ALIAS_MAP.put(templateName, templateName_alias);
        ALIAS_COLUMN_MAP.put(templateName_alias, templateName);
        COLUMN_ALIAS_MAP.put(templateValue, templateValue_alias);
        ALIAS_COLUMN_MAP.put(templateValue_alias, templateValue);
        COLUMN_ALIAS_MAP.put(status, status_alias);
        ALIAS_COLUMN_MAP.put(status_alias, status);
        COLUMN_ALIAS_MAP.put(createTime, createTime_alias);
        ALIAS_COLUMN_MAP.put(createTime_alias, createTime);
        COLUMN_ALIAS_MAP.put(updateTime, updateTime_alias);
        ALIAS_COLUMN_MAP.put(updateTime_alias, updateTime);
        COLUMN_ALIAS_MAP.put(deleteTime, deleteTime_alias);
        ALIAS_COLUMN_MAP.put(deleteTime_alias, deleteTime);
        COLUMN_ALIAS_MAP.put(createTimeStamp, createTimeStamp_alias);
        ALIAS_COLUMN_MAP.put(createTimeStamp_alias, createTimeStamp);
        COLUMN_ALIAS_MAP.put(updateTimeStamp, updateTimeStamp_alias);
        ALIAS_COLUMN_MAP.put(updateTimeStamp_alias, updateTimeStamp);
        COLUMN_ALIAS_MAP.put(deleteTimeStamp, deleteTimeStamp_alias);
        ALIAS_COLUMN_MAP.put(deleteTimeStamp_alias, deleteTimeStamp);
    }

    /**
     * 表名
     */
    @Override
    public String getTableName() {
        return tableName;
    }

    /**
     * 表别名
     */
    @Override
    public String getTableAlias() {
        return tableAlias;
    }

    /**
     * 主键名
     */
    @Override
    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    /**
     * 主键别名
     */
    @Override
    public String getPrimaryKeyAlias() {
        return primaryKeyAlias;
    }

    /**
     * 列名-别名 集合
     */
    @Override
    public Map<String, String> getColumnAliasMap() {
        return COLUMN_ALIAS_MAP;
    }

    /**
     * 别名-列名 集合
     */
    @Override
    public Map<String, String> getAliasColumnMap() {
        return ALIAS_COLUMN_MAP;
    }

    @Override
    public Column getColumnModel() {
        return new Column();
    }

    public static final class Column extends ColumnModel<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> {

        private Column() {
        }

        /**
         * 主键
         */
        public Column primaryKey() {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.primaryKeyName, AutRoleRouteViewTemplateModel.primaryKeyAlias);
            return this;
        }

        /**
         * 主键
         * @param alias 别名
         */
        public Column primaryKey(String alias) {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.primaryKeyName, alias);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Column id() {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.id, AutRoleRouteViewTemplateModel.id_alias);
            return this;
        }

        /**
         * 主键ID
         * @param alias 别名
         */
        public Column id(String alias) {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.id, alias);
            return this;
        }

        /**
         * 所属模块ID
         */
        public Column moduleId() {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.moduleId, AutRoleRouteViewTemplateModel.moduleId_alias);
            return this;
        }

        /**
         * 所属模块ID
         * @param alias 别名
         */
        public Column moduleId(String alias) {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.moduleId, alias);
            return this;
        }

        /**
         * 角色id
         */
        public Column roleId() {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.roleId, AutRoleRouteViewTemplateModel.roleId_alias);
            return this;
        }

        /**
         * 角色id
         * @param alias 别名
         */
        public Column roleId(String alias) {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.roleId, alias);
            return this;
        }

        /**
         * 角色值
         */
        public Column roleValue() {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.roleValue, AutRoleRouteViewTemplateModel.roleValue_alias);
            return this;
        }

        /**
         * 角色值
         * @param alias 别名
         */
        public Column roleValue(String alias) {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.roleValue, alias);
            return this;
        }

        /**
         * 角色名称
         */
        public Column roleName() {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.roleName, AutRoleRouteViewTemplateModel.roleName_alias);
            return this;
        }

        /**
         * 角色名称
         * @param alias 别名
         */
        public Column roleName(String alias) {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.roleName, alias);
            return this;
        }

        /**
         * 角色类型
         */
        public Column roleType() {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.roleType, AutRoleRouteViewTemplateModel.roleType_alias);
            return this;
        }

        /**
         * 角色类型
         * @param alias 别名
         */
        public Column roleType(String alias) {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.roleType, alias);
            return this;
        }

        /**
         * 路由ID
         */
        public Column routeId() {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.routeId, AutRoleRouteViewTemplateModel.routeId_alias);
            return this;
        }

        /**
         * 路由ID
         * @param alias 别名
         */
        public Column routeId(String alias) {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.routeId, alias);
            return this;
        }

        /**
         * 路由名称
         */
        public Column routeName() {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.routeName, AutRoleRouteViewTemplateModel.routeName_alias);
            return this;
        }

        /**
         * 路由名称
         * @param alias 别名
         */
        public Column routeName(String alias) {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.routeName, alias);
            return this;
        }

        /**
         * 路由唯一标识符
         */
        public Column routeValue() {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.routeValue, AutRoleRouteViewTemplateModel.routeValue_alias);
            return this;
        }

        /**
         * 路由唯一标识符
         * @param alias 别名
         */
        public Column routeValue(String alias) {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.routeValue, alias);
            return this;
        }

        /**
         * 路由地址
         */
        public Column routePath() {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.routePath, AutRoleRouteViewTemplateModel.routePath_alias);
            return this;
        }

        /**
         * 路由地址
         * @param alias 别名
         */
        public Column routePath(String alias) {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.routePath, alias);
            return this;
        }

        /**
         * 路由视图ID
         */
        public Column routeViewId() {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.routeViewId, AutRoleRouteViewTemplateModel.routeViewId_alias);
            return this;
        }

        /**
         * 路由视图ID
         * @param alias 别名
         */
        public Column routeViewId(String alias) {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.routeViewId, alias);
            return this;
        }

        /**
         * 路由视图名称
         */
        public Column routeViewName() {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.routeViewName, AutRoleRouteViewTemplateModel.routeViewName_alias);
            return this;
        }

        /**
         * 路由视图名称
         * @param alias 别名
         */
        public Column routeViewName(String alias) {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.routeViewName, alias);
            return this;
        }

        /**
         * 路由视图唯一标识符
         */
        public Column routeViewValue() {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.routeViewValue, AutRoleRouteViewTemplateModel.routeViewValue_alias);
            return this;
        }

        /**
         * 路由视图唯一标识符
         * @param alias 别名
         */
        public Column routeViewValue(String alias) {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.routeViewValue, alias);
            return this;
        }

        /**
         * 模板ID
         */
        public Column templateId() {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.templateId, AutRoleRouteViewTemplateModel.templateId_alias);
            return this;
        }

        /**
         * 模板ID
         * @param alias 别名
         */
        public Column templateId(String alias) {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.templateId, alias);
            return this;
        }

        /**
         * 模板名称
         */
        public Column templateName() {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.templateName, AutRoleRouteViewTemplateModel.templateName_alias);
            return this;
        }

        /**
         * 模板名称
         * @param alias 别名
         */
        public Column templateName(String alias) {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.templateName, alias);
            return this;
        }

        /**
         * 模板唯一标识符
         */
        public Column templateValue() {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.templateValue, AutRoleRouteViewTemplateModel.templateValue_alias);
            return this;
        }

        /**
         * 模板唯一标识符
         * @param alias 别名
         */
        public Column templateValue(String alias) {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.templateValue, alias);
            return this;
        }

        /**
         * 状态
         */
        public Column status() {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.status, AutRoleRouteViewTemplateModel.status_alias);
            return this;
        }

        /**
         * 状态
         * @param alias 别名
         */
        public Column status(String alias) {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.status, alias);
            return this;
        }

        /**
         * 创建时间
         */
        public Column createTime() {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.createTime, AutRoleRouteViewTemplateModel.createTime_alias);
            return this;
        }

        /**
         * 创建时间
         * @param alias 别名
         */
        public Column createTime(String alias) {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.createTime, alias);
            return this;
        }

        /**
         * 修改时间
         */
        public Column updateTime() {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.updateTime, AutRoleRouteViewTemplateModel.updateTime_alias);
            return this;
        }

        /**
         * 修改时间
         * @param alias 别名
         */
        public Column updateTime(String alias) {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.updateTime, alias);
            return this;
        }

        /**
         * 删除时间
         */
        public Column deleteTime() {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.deleteTime, AutRoleRouteViewTemplateModel.deleteTime_alias);
            return this;
        }

        /**
         * 删除时间
         * @param alias 别名
         */
        public Column deleteTime(String alias) {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.deleteTime, alias);
            return this;
        }

        /**
         * 创建时间戳
         */
        public Column createTimeStamp() {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.createTimeStamp, AutRoleRouteViewTemplateModel.createTimeStamp_alias);
            return this;
        }

        /**
         * 创建时间戳
         * @param alias 别名
         */
        public Column createTimeStamp(String alias) {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.createTimeStamp, alias);
            return this;
        }

        /**
         * 修改时间戳
         */
        public Column updateTimeStamp() {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.updateTimeStamp, AutRoleRouteViewTemplateModel.updateTimeStamp_alias);
            return this;
        }

        /**
         * 修改时间戳
         * @param alias 别名
         */
        public Column updateTimeStamp(String alias) {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.updateTimeStamp, alias);
            return this;
        }

        /**
         * 删除时间戳
         */
        public Column deleteTimeStamp() {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.deleteTimeStamp, AutRoleRouteViewTemplateModel.deleteTimeStamp_alias);
            return this;
        }

        /**
         * 删除时间戳
         * @param alias 别名
         */
        public Column deleteTimeStamp(String alias) {
            this.addColumnAlias(AutRoleRouteViewTemplateModel.deleteTimeStamp, alias);
            return this;
        }

    }

    @Override
    public On getOnModel() {
        return new On();
    }

    public static final class On extends OnModel<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> {

        private On() {
        }

        /**
         * 主键
         */
        public OnBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.onBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public OnBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> id() {
            return this.onBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.id);
        }

        /**
         * 所属模块ID
         */
        public OnBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> moduleId() {
            return this.onBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.moduleId);
        }

        /**
         * 角色id
         */
        public OnBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> roleId() {
            return this.onBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.roleId);
        }

        /**
         * 角色值
         */
        public OnBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> roleValue() {
            return this.onBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.roleValue);
        }

        /**
         * 角色名称
         */
        public OnBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> roleName() {
            return this.onBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.roleName);
        }

        /**
         * 角色类型
         */
        public OnBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> roleType() {
            return this.onBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.roleType);
        }

        /**
         * 路由ID
         */
        public OnBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> routeId() {
            return this.onBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.routeId);
        }

        /**
         * 路由名称
         */
        public OnBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> routeName() {
            return this.onBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.routeName);
        }

        /**
         * 路由唯一标识符
         */
        public OnBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> routeValue() {
            return this.onBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.routeValue);
        }

        /**
         * 路由地址
         */
        public OnBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> routePath() {
            return this.onBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.routePath);
        }

        /**
         * 路由视图ID
         */
        public OnBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> routeViewId() {
            return this.onBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.routeViewId);
        }

        /**
         * 路由视图名称
         */
        public OnBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> routeViewName() {
            return this.onBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.routeViewName);
        }

        /**
         * 路由视图唯一标识符
         */
        public OnBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> routeViewValue() {
            return this.onBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.routeViewValue);
        }

        /**
         * 模板ID
         */
        public OnBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> templateId() {
            return this.onBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.templateId);
        }

        /**
         * 模板名称
         */
        public OnBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> templateName() {
            return this.onBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.templateName);
        }

        /**
         * 模板唯一标识符
         */
        public OnBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> templateValue() {
            return this.onBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.templateValue);
        }

        /**
         * 状态
         */
        public OnBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> status() {
            return this.onBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.status);
        }

        /**
         * 创建时间
         */
        public OnBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> createTime() {
            return this.onBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.createTime);
        }

        /**
         * 修改时间
         */
        public OnBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> updateTime() {
            return this.onBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.updateTime);
        }

        /**
         * 删除时间
         */
        public OnBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.onBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public OnBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.onBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public OnBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.onBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public OnBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.onBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.deleteTimeStamp);
        }

    }

    @Override
    public Where getWhereModel() {
        return new Where();
    }

    public static final class Where extends WhereModel<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> {

        private Where() {
        }

        /**
         * 主键
         */
        public WhereBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.whereBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public WhereBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> id() {
            return this.whereBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.id);
        }

        /**
         * 所属模块ID
         */
        public WhereBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> moduleId() {
            return this.whereBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.moduleId);
        }

        /**
         * 角色id
         */
        public WhereBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> roleId() {
            return this.whereBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.roleId);
        }

        /**
         * 角色值
         */
        public WhereBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> roleValue() {
            return this.whereBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.roleValue);
        }

        /**
         * 角色名称
         */
        public WhereBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> roleName() {
            return this.whereBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.roleName);
        }

        /**
         * 角色类型
         */
        public WhereBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> roleType() {
            return this.whereBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.roleType);
        }

        /**
         * 路由ID
         */
        public WhereBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> routeId() {
            return this.whereBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.routeId);
        }

        /**
         * 路由名称
         */
        public WhereBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> routeName() {
            return this.whereBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.routeName);
        }

        /**
         * 路由唯一标识符
         */
        public WhereBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> routeValue() {
            return this.whereBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.routeValue);
        }

        /**
         * 路由地址
         */
        public WhereBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> routePath() {
            return this.whereBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.routePath);
        }

        /**
         * 路由视图ID
         */
        public WhereBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> routeViewId() {
            return this.whereBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.routeViewId);
        }

        /**
         * 路由视图名称
         */
        public WhereBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> routeViewName() {
            return this.whereBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.routeViewName);
        }

        /**
         * 路由视图唯一标识符
         */
        public WhereBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> routeViewValue() {
            return this.whereBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.routeViewValue);
        }

        /**
         * 模板ID
         */
        public WhereBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> templateId() {
            return this.whereBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.templateId);
        }

        /**
         * 模板名称
         */
        public WhereBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> templateName() {
            return this.whereBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.templateName);
        }

        /**
         * 模板唯一标识符
         */
        public WhereBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> templateValue() {
            return this.whereBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.templateValue);
        }

        /**
         * 状态
         */
        public WhereBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> status() {
            return this.whereBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.status);
        }

        /**
         * 创建时间
         */
        public WhereBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> createTime() {
            return this.whereBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.createTime);
        }

        /**
         * 修改时间
         */
        public WhereBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> updateTime() {
            return this.whereBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.updateTime);
        }

        /**
         * 删除时间
         */
        public WhereBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.whereBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public WhereBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.whereBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public WhereBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.whereBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public WhereBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.whereBuilder.handler(AutRoleRouteViewTemplateModel.tableName, AutRoleRouteViewTemplateModel.tableAlias, AutRoleRouteViewTemplateModel.deleteTimeStamp);
        }

    }

    @Override
    public Group getGroupModel() {
        return new Group();
    }

    public static final class Group extends GroupModel<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> {

        private Group() {
        }

        /**
         * 主键
         */
        public Group primaryKey() {
            this.addColumn(AutRoleRouteViewTemplateModel.primaryKeyName);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Group id() {
            this.addColumn(AutRoleRouteViewTemplateModel.id);
            return this;
        }

        /**
         * 所属模块ID
         */
        public Group moduleId() {
            this.addColumn(AutRoleRouteViewTemplateModel.moduleId);
            return this;
        }

        /**
         * 角色id
         */
        public Group roleId() {
            this.addColumn(AutRoleRouteViewTemplateModel.roleId);
            return this;
        }

        /**
         * 角色值
         */
        public Group roleValue() {
            this.addColumn(AutRoleRouteViewTemplateModel.roleValue);
            return this;
        }

        /**
         * 角色名称
         */
        public Group roleName() {
            this.addColumn(AutRoleRouteViewTemplateModel.roleName);
            return this;
        }

        /**
         * 角色类型
         */
        public Group roleType() {
            this.addColumn(AutRoleRouteViewTemplateModel.roleType);
            return this;
        }

        /**
         * 路由ID
         */
        public Group routeId() {
            this.addColumn(AutRoleRouteViewTemplateModel.routeId);
            return this;
        }

        /**
         * 路由名称
         */
        public Group routeName() {
            this.addColumn(AutRoleRouteViewTemplateModel.routeName);
            return this;
        }

        /**
         * 路由唯一标识符
         */
        public Group routeValue() {
            this.addColumn(AutRoleRouteViewTemplateModel.routeValue);
            return this;
        }

        /**
         * 路由地址
         */
        public Group routePath() {
            this.addColumn(AutRoleRouteViewTemplateModel.routePath);
            return this;
        }

        /**
         * 路由视图ID
         */
        public Group routeViewId() {
            this.addColumn(AutRoleRouteViewTemplateModel.routeViewId);
            return this;
        }

        /**
         * 路由视图名称
         */
        public Group routeViewName() {
            this.addColumn(AutRoleRouteViewTemplateModel.routeViewName);
            return this;
        }

        /**
         * 路由视图唯一标识符
         */
        public Group routeViewValue() {
            this.addColumn(AutRoleRouteViewTemplateModel.routeViewValue);
            return this;
        }

        /**
         * 模板ID
         */
        public Group templateId() {
            this.addColumn(AutRoleRouteViewTemplateModel.templateId);
            return this;
        }

        /**
         * 模板名称
         */
        public Group templateName() {
            this.addColumn(AutRoleRouteViewTemplateModel.templateName);
            return this;
        }

        /**
         * 模板唯一标识符
         */
        public Group templateValue() {
            this.addColumn(AutRoleRouteViewTemplateModel.templateValue);
            return this;
        }

        /**
         * 状态
         */
        public Group status() {
            this.addColumn(AutRoleRouteViewTemplateModel.status);
            return this;
        }

        /**
         * 创建时间
         */
        public Group createTime() {
            this.addColumn(AutRoleRouteViewTemplateModel.createTime);
            return this;
        }

        /**
         * 修改时间
         */
        public Group updateTime() {
            this.addColumn(AutRoleRouteViewTemplateModel.updateTime);
            return this;
        }

        /**
         * 删除时间
         */
        public Group deleteTime() {
            this.addColumn(AutRoleRouteViewTemplateModel.deleteTime);
            return this;
        }

        /**
         * 创建时间戳
         */
        public Group createTimeStamp() {
            this.addColumn(AutRoleRouteViewTemplateModel.createTimeStamp);
            return this;
        }

        /**
         * 修改时间戳
         */
        public Group updateTimeStamp() {
            this.addColumn(AutRoleRouteViewTemplateModel.updateTimeStamp);
            return this;
        }

        /**
         * 删除时间戳
         */
        public Group deleteTimeStamp() {
            this.addColumn(AutRoleRouteViewTemplateModel.deleteTimeStamp);
            return this;
        }

    }

    @Override
    public Sort getSortModel() {
        return new Sort();
    }

    public static final class Sort extends SortModel<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> {

        private Sort() {
        }

        /**
         * 主键
         */
        public SortBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.sortBuilder.handler(AutRoleRouteViewTemplateModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public SortBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> id() {
            return this.sortBuilder.handler(AutRoleRouteViewTemplateModel.id);
        }

        /**
         * 所属模块ID
         */
        public SortBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> moduleId() {
            return this.sortBuilder.handler(AutRoleRouteViewTemplateModel.moduleId);
        }

        /**
         * 角色id
         */
        public SortBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> roleId() {
            return this.sortBuilder.handler(AutRoleRouteViewTemplateModel.roleId);
        }

        /**
         * 角色值
         */
        public SortBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> roleValue() {
            return this.sortBuilder.handler(AutRoleRouteViewTemplateModel.roleValue);
        }

        /**
         * 角色名称
         */
        public SortBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> roleName() {
            return this.sortBuilder.handler(AutRoleRouteViewTemplateModel.roleName);
        }

        /**
         * 角色类型
         */
        public SortBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> roleType() {
            return this.sortBuilder.handler(AutRoleRouteViewTemplateModel.roleType);
        }

        /**
         * 路由ID
         */
        public SortBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> routeId() {
            return this.sortBuilder.handler(AutRoleRouteViewTemplateModel.routeId);
        }

        /**
         * 路由名称
         */
        public SortBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> routeName() {
            return this.sortBuilder.handler(AutRoleRouteViewTemplateModel.routeName);
        }

        /**
         * 路由唯一标识符
         */
        public SortBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> routeValue() {
            return this.sortBuilder.handler(AutRoleRouteViewTemplateModel.routeValue);
        }

        /**
         * 路由地址
         */
        public SortBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> routePath() {
            return this.sortBuilder.handler(AutRoleRouteViewTemplateModel.routePath);
        }

        /**
         * 路由视图ID
         */
        public SortBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> routeViewId() {
            return this.sortBuilder.handler(AutRoleRouteViewTemplateModel.routeViewId);
        }

        /**
         * 路由视图名称
         */
        public SortBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> routeViewName() {
            return this.sortBuilder.handler(AutRoleRouteViewTemplateModel.routeViewName);
        }

        /**
         * 路由视图唯一标识符
         */
        public SortBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> routeViewValue() {
            return this.sortBuilder.handler(AutRoleRouteViewTemplateModel.routeViewValue);
        }

        /**
         * 模板ID
         */
        public SortBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> templateId() {
            return this.sortBuilder.handler(AutRoleRouteViewTemplateModel.templateId);
        }

        /**
         * 模板名称
         */
        public SortBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> templateName() {
            return this.sortBuilder.handler(AutRoleRouteViewTemplateModel.templateName);
        }

        /**
         * 模板唯一标识符
         */
        public SortBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> templateValue() {
            return this.sortBuilder.handler(AutRoleRouteViewTemplateModel.templateValue);
        }

        /**
         * 状态
         */
        public SortBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> status() {
            return this.sortBuilder.handler(AutRoleRouteViewTemplateModel.status);
        }

        /**
         * 创建时间
         */
        public SortBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> createTime() {
            return this.sortBuilder.handler(AutRoleRouteViewTemplateModel.createTime);
        }

        /**
         * 修改时间
         */
        public SortBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> updateTime() {
            return this.sortBuilder.handler(AutRoleRouteViewTemplateModel.updateTime);
        }

        /**
         * 删除时间
         */
        public SortBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.sortBuilder.handler(AutRoleRouteViewTemplateModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public SortBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.sortBuilder.handler(AutRoleRouteViewTemplateModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public SortBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.sortBuilder.handler(AutRoleRouteViewTemplateModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public SortBuilder<AutRoleRouteViewTemplateModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.sortBuilder.handler(AutRoleRouteViewTemplateModel.deleteTimeStamp);
        }

    }

}