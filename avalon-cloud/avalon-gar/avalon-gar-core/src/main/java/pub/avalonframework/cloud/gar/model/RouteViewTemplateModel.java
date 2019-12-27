package pub.avalonframework.cloud.gar.model;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("all")
public final class RouteViewTemplateModel implements Model<RouteViewTemplateModel, RouteViewTemplateModel.Column, RouteViewTemplateModel.On, RouteViewTemplateModel.Where, RouteViewTemplateModel.Sort, RouteViewTemplateModel.Group> {

    /**
     * 表名
     */
    public static final String tableName = "gar_route_view_template";
    /**
     * 表别名
     */
    public static final String tableAlias = "RouteViewTemplate";

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

    public static final class Column extends ColumnModel<RouteViewTemplateModel, Column, On, Where, Sort, Group> {

        private Column() {
        }

        /**
         * 主键
         */
        public Column primaryKey() {
            this.addColumnAlias(RouteViewTemplateModel.primaryKeyName, RouteViewTemplateModel.primaryKeyAlias);
            return this;
        }

        /**
         * 主键
         * @param alias 别名
         */
        public Column primaryKey(String alias) {
            this.addColumnAlias(RouteViewTemplateModel.primaryKeyName, alias);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Column id() {
            this.addColumnAlias(RouteViewTemplateModel.id, RouteViewTemplateModel.id_alias);
            return this;
        }

        /**
         * 主键ID
         * @param alias 别名
         */
        public Column id(String alias) {
            this.addColumnAlias(RouteViewTemplateModel.id, alias);
            return this;
        }

        /**
         * 所属模块ID
         */
        public Column moduleId() {
            this.addColumnAlias(RouteViewTemplateModel.moduleId, RouteViewTemplateModel.moduleId_alias);
            return this;
        }

        /**
         * 所属模块ID
         * @param alias 别名
         */
        public Column moduleId(String alias) {
            this.addColumnAlias(RouteViewTemplateModel.moduleId, alias);
            return this;
        }

        /**
         * 路由ID
         */
        public Column routeId() {
            this.addColumnAlias(RouteViewTemplateModel.routeId, RouteViewTemplateModel.routeId_alias);
            return this;
        }

        /**
         * 路由ID
         * @param alias 别名
         */
        public Column routeId(String alias) {
            this.addColumnAlias(RouteViewTemplateModel.routeId, alias);
            return this;
        }

        /**
         * 路由名称
         */
        public Column routeName() {
            this.addColumnAlias(RouteViewTemplateModel.routeName, RouteViewTemplateModel.routeName_alias);
            return this;
        }

        /**
         * 路由名称
         * @param alias 别名
         */
        public Column routeName(String alias) {
            this.addColumnAlias(RouteViewTemplateModel.routeName, alias);
            return this;
        }

        /**
         * 路由唯一标识符
         */
        public Column routeValue() {
            this.addColumnAlias(RouteViewTemplateModel.routeValue, RouteViewTemplateModel.routeValue_alias);
            return this;
        }

        /**
         * 路由唯一标识符
         * @param alias 别名
         */
        public Column routeValue(String alias) {
            this.addColumnAlias(RouteViewTemplateModel.routeValue, alias);
            return this;
        }

        /**
         * 路由地址
         */
        public Column routePath() {
            this.addColumnAlias(RouteViewTemplateModel.routePath, RouteViewTemplateModel.routePath_alias);
            return this;
        }

        /**
         * 路由地址
         * @param alias 别名
         */
        public Column routePath(String alias) {
            this.addColumnAlias(RouteViewTemplateModel.routePath, alias);
            return this;
        }

        /**
         * 路由视图ID
         */
        public Column routeViewId() {
            this.addColumnAlias(RouteViewTemplateModel.routeViewId, RouteViewTemplateModel.routeViewId_alias);
            return this;
        }

        /**
         * 路由视图ID
         * @param alias 别名
         */
        public Column routeViewId(String alias) {
            this.addColumnAlias(RouteViewTemplateModel.routeViewId, alias);
            return this;
        }

        /**
         * 路由视图名称
         */
        public Column routeViewName() {
            this.addColumnAlias(RouteViewTemplateModel.routeViewName, RouteViewTemplateModel.routeViewName_alias);
            return this;
        }

        /**
         * 路由视图名称
         * @param alias 别名
         */
        public Column routeViewName(String alias) {
            this.addColumnAlias(RouteViewTemplateModel.routeViewName, alias);
            return this;
        }

        /**
         * 路由视图唯一标识符
         */
        public Column routeViewValue() {
            this.addColumnAlias(RouteViewTemplateModel.routeViewValue, RouteViewTemplateModel.routeViewValue_alias);
            return this;
        }

        /**
         * 路由视图唯一标识符
         * @param alias 别名
         */
        public Column routeViewValue(String alias) {
            this.addColumnAlias(RouteViewTemplateModel.routeViewValue, alias);
            return this;
        }

        /**
         * 模板ID
         */
        public Column templateId() {
            this.addColumnAlias(RouteViewTemplateModel.templateId, RouteViewTemplateModel.templateId_alias);
            return this;
        }

        /**
         * 模板ID
         * @param alias 别名
         */
        public Column templateId(String alias) {
            this.addColumnAlias(RouteViewTemplateModel.templateId, alias);
            return this;
        }

        /**
         * 模板名称
         */
        public Column templateName() {
            this.addColumnAlias(RouteViewTemplateModel.templateName, RouteViewTemplateModel.templateName_alias);
            return this;
        }

        /**
         * 模板名称
         * @param alias 别名
         */
        public Column templateName(String alias) {
            this.addColumnAlias(RouteViewTemplateModel.templateName, alias);
            return this;
        }

        /**
         * 模板唯一标识符
         */
        public Column templateValue() {
            this.addColumnAlias(RouteViewTemplateModel.templateValue, RouteViewTemplateModel.templateValue_alias);
            return this;
        }

        /**
         * 模板唯一标识符
         * @param alias 别名
         */
        public Column templateValue(String alias) {
            this.addColumnAlias(RouteViewTemplateModel.templateValue, alias);
            return this;
        }

        /**
         * 状态
         */
        public Column status() {
            this.addColumnAlias(RouteViewTemplateModel.status, RouteViewTemplateModel.status_alias);
            return this;
        }

        /**
         * 状态
         * @param alias 别名
         */
        public Column status(String alias) {
            this.addColumnAlias(RouteViewTemplateModel.status, alias);
            return this;
        }

        /**
         * 创建时间
         */
        public Column createTime() {
            this.addColumnAlias(RouteViewTemplateModel.createTime, RouteViewTemplateModel.createTime_alias);
            return this;
        }

        /**
         * 创建时间
         * @param alias 别名
         */
        public Column createTime(String alias) {
            this.addColumnAlias(RouteViewTemplateModel.createTime, alias);
            return this;
        }

        /**
         * 修改时间
         */
        public Column updateTime() {
            this.addColumnAlias(RouteViewTemplateModel.updateTime, RouteViewTemplateModel.updateTime_alias);
            return this;
        }

        /**
         * 修改时间
         * @param alias 别名
         */
        public Column updateTime(String alias) {
            this.addColumnAlias(RouteViewTemplateModel.updateTime, alias);
            return this;
        }

        /**
         * 删除时间
         */
        public Column deleteTime() {
            this.addColumnAlias(RouteViewTemplateModel.deleteTime, RouteViewTemplateModel.deleteTime_alias);
            return this;
        }

        /**
         * 删除时间
         * @param alias 别名
         */
        public Column deleteTime(String alias) {
            this.addColumnAlias(RouteViewTemplateModel.deleteTime, alias);
            return this;
        }

        /**
         * 创建时间戳
         */
        public Column createTimeStamp() {
            this.addColumnAlias(RouteViewTemplateModel.createTimeStamp, RouteViewTemplateModel.createTimeStamp_alias);
            return this;
        }

        /**
         * 创建时间戳
         * @param alias 别名
         */
        public Column createTimeStamp(String alias) {
            this.addColumnAlias(RouteViewTemplateModel.createTimeStamp, alias);
            return this;
        }

        /**
         * 修改时间戳
         */
        public Column updateTimeStamp() {
            this.addColumnAlias(RouteViewTemplateModel.updateTimeStamp, RouteViewTemplateModel.updateTimeStamp_alias);
            return this;
        }

        /**
         * 修改时间戳
         * @param alias 别名
         */
        public Column updateTimeStamp(String alias) {
            this.addColumnAlias(RouteViewTemplateModel.updateTimeStamp, alias);
            return this;
        }

        /**
         * 删除时间戳
         */
        public Column deleteTimeStamp() {
            this.addColumnAlias(RouteViewTemplateModel.deleteTimeStamp, RouteViewTemplateModel.deleteTimeStamp_alias);
            return this;
        }

        /**
         * 删除时间戳
         * @param alias 别名
         */
        public Column deleteTimeStamp(String alias) {
            this.addColumnAlias(RouteViewTemplateModel.deleteTimeStamp, alias);
            return this;
        }

    }

    @Override
    public On getOnModel() {
        return new On();
    }

    public static final class On extends OnModel<RouteViewTemplateModel, Column, On, Where, Sort, Group> {

        private On() {
        }

        /**
         * 主键
         */
        public OnBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.onBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public OnBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> id() {
            return this.onBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.id);
        }

        /**
         * 所属模块ID
         */
        public OnBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> moduleId() {
            return this.onBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.moduleId);
        }

        /**
         * 路由ID
         */
        public OnBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> routeId() {
            return this.onBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.routeId);
        }

        /**
         * 路由名称
         */
        public OnBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> routeName() {
            return this.onBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.routeName);
        }

        /**
         * 路由唯一标识符
         */
        public OnBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> routeValue() {
            return this.onBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.routeValue);
        }

        /**
         * 路由地址
         */
        public OnBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> routePath() {
            return this.onBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.routePath);
        }

        /**
         * 路由视图ID
         */
        public OnBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> routeViewId() {
            return this.onBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.routeViewId);
        }

        /**
         * 路由视图名称
         */
        public OnBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> routeViewName() {
            return this.onBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.routeViewName);
        }

        /**
         * 路由视图唯一标识符
         */
        public OnBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> routeViewValue() {
            return this.onBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.routeViewValue);
        }

        /**
         * 模板ID
         */
        public OnBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> templateId() {
            return this.onBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.templateId);
        }

        /**
         * 模板名称
         */
        public OnBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> templateName() {
            return this.onBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.templateName);
        }

        /**
         * 模板唯一标识符
         */
        public OnBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> templateValue() {
            return this.onBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.templateValue);
        }

        /**
         * 状态
         */
        public OnBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> status() {
            return this.onBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.status);
        }

        /**
         * 创建时间
         */
        public OnBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> createTime() {
            return this.onBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.createTime);
        }

        /**
         * 修改时间
         */
        public OnBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> updateTime() {
            return this.onBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.updateTime);
        }

        /**
         * 删除时间
         */
        public OnBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.onBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public OnBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.onBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public OnBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.onBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public OnBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.onBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.deleteTimeStamp);
        }

    }

    @Override
    public Where getWhereModel() {
        return new Where();
    }

    public static final class Where extends WhereModel<RouteViewTemplateModel, Column, On, Where, Sort, Group> {

        private Where() {
        }

        /**
         * 主键
         */
        public WhereBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.whereBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public WhereBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> id() {
            return this.whereBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.id);
        }

        /**
         * 所属模块ID
         */
        public WhereBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> moduleId() {
            return this.whereBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.moduleId);
        }

        /**
         * 路由ID
         */
        public WhereBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> routeId() {
            return this.whereBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.routeId);
        }

        /**
         * 路由名称
         */
        public WhereBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> routeName() {
            return this.whereBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.routeName);
        }

        /**
         * 路由唯一标识符
         */
        public WhereBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> routeValue() {
            return this.whereBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.routeValue);
        }

        /**
         * 路由地址
         */
        public WhereBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> routePath() {
            return this.whereBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.routePath);
        }

        /**
         * 路由视图ID
         */
        public WhereBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> routeViewId() {
            return this.whereBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.routeViewId);
        }

        /**
         * 路由视图名称
         */
        public WhereBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> routeViewName() {
            return this.whereBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.routeViewName);
        }

        /**
         * 路由视图唯一标识符
         */
        public WhereBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> routeViewValue() {
            return this.whereBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.routeViewValue);
        }

        /**
         * 模板ID
         */
        public WhereBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> templateId() {
            return this.whereBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.templateId);
        }

        /**
         * 模板名称
         */
        public WhereBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> templateName() {
            return this.whereBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.templateName);
        }

        /**
         * 模板唯一标识符
         */
        public WhereBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> templateValue() {
            return this.whereBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.templateValue);
        }

        /**
         * 状态
         */
        public WhereBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> status() {
            return this.whereBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.status);
        }

        /**
         * 创建时间
         */
        public WhereBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> createTime() {
            return this.whereBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.createTime);
        }

        /**
         * 修改时间
         */
        public WhereBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> updateTime() {
            return this.whereBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.updateTime);
        }

        /**
         * 删除时间
         */
        public WhereBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.whereBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public WhereBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.whereBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public WhereBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.whereBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public WhereBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.whereBuilder.handler(RouteViewTemplateModel.tableName, RouteViewTemplateModel.tableAlias, RouteViewTemplateModel.deleteTimeStamp);
        }

    }

    @Override
    public Group getGroupModel() {
        return new Group();
    }

    public static final class Group extends GroupModel<RouteViewTemplateModel, Column, On, Where, Sort, Group> {

        private Group() {
        }

        /**
         * 主键
         */
        public Group primaryKey() {
            this.addColumn(RouteViewTemplateModel.primaryKeyName);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Group id() {
            this.addColumn(RouteViewTemplateModel.id);
            return this;
        }

        /**
         * 所属模块ID
         */
        public Group moduleId() {
            this.addColumn(RouteViewTemplateModel.moduleId);
            return this;
        }

        /**
         * 路由ID
         */
        public Group routeId() {
            this.addColumn(RouteViewTemplateModel.routeId);
            return this;
        }

        /**
         * 路由名称
         */
        public Group routeName() {
            this.addColumn(RouteViewTemplateModel.routeName);
            return this;
        }

        /**
         * 路由唯一标识符
         */
        public Group routeValue() {
            this.addColumn(RouteViewTemplateModel.routeValue);
            return this;
        }

        /**
         * 路由地址
         */
        public Group routePath() {
            this.addColumn(RouteViewTemplateModel.routePath);
            return this;
        }

        /**
         * 路由视图ID
         */
        public Group routeViewId() {
            this.addColumn(RouteViewTemplateModel.routeViewId);
            return this;
        }

        /**
         * 路由视图名称
         */
        public Group routeViewName() {
            this.addColumn(RouteViewTemplateModel.routeViewName);
            return this;
        }

        /**
         * 路由视图唯一标识符
         */
        public Group routeViewValue() {
            this.addColumn(RouteViewTemplateModel.routeViewValue);
            return this;
        }

        /**
         * 模板ID
         */
        public Group templateId() {
            this.addColumn(RouteViewTemplateModel.templateId);
            return this;
        }

        /**
         * 模板名称
         */
        public Group templateName() {
            this.addColumn(RouteViewTemplateModel.templateName);
            return this;
        }

        /**
         * 模板唯一标识符
         */
        public Group templateValue() {
            this.addColumn(RouteViewTemplateModel.templateValue);
            return this;
        }

        /**
         * 状态
         */
        public Group status() {
            this.addColumn(RouteViewTemplateModel.status);
            return this;
        }

        /**
         * 创建时间
         */
        public Group createTime() {
            this.addColumn(RouteViewTemplateModel.createTime);
            return this;
        }

        /**
         * 修改时间
         */
        public Group updateTime() {
            this.addColumn(RouteViewTemplateModel.updateTime);
            return this;
        }

        /**
         * 删除时间
         */
        public Group deleteTime() {
            this.addColumn(RouteViewTemplateModel.deleteTime);
            return this;
        }

        /**
         * 创建时间戳
         */
        public Group createTimeStamp() {
            this.addColumn(RouteViewTemplateModel.createTimeStamp);
            return this;
        }

        /**
         * 修改时间戳
         */
        public Group updateTimeStamp() {
            this.addColumn(RouteViewTemplateModel.updateTimeStamp);
            return this;
        }

        /**
         * 删除时间戳
         */
        public Group deleteTimeStamp() {
            this.addColumn(RouteViewTemplateModel.deleteTimeStamp);
            return this;
        }

    }

    @Override
    public Sort getSortModel() {
        return new Sort();
    }

    public static final class Sort extends SortModel<RouteViewTemplateModel, Column, On, Where, Sort, Group> {

        private Sort() {
        }

        /**
         * 主键
         */
        public SortBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.sortBuilder.handler(RouteViewTemplateModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public SortBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> id() {
            return this.sortBuilder.handler(RouteViewTemplateModel.id);
        }

        /**
         * 所属模块ID
         */
        public SortBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> moduleId() {
            return this.sortBuilder.handler(RouteViewTemplateModel.moduleId);
        }

        /**
         * 路由ID
         */
        public SortBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> routeId() {
            return this.sortBuilder.handler(RouteViewTemplateModel.routeId);
        }

        /**
         * 路由名称
         */
        public SortBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> routeName() {
            return this.sortBuilder.handler(RouteViewTemplateModel.routeName);
        }

        /**
         * 路由唯一标识符
         */
        public SortBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> routeValue() {
            return this.sortBuilder.handler(RouteViewTemplateModel.routeValue);
        }

        /**
         * 路由地址
         */
        public SortBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> routePath() {
            return this.sortBuilder.handler(RouteViewTemplateModel.routePath);
        }

        /**
         * 路由视图ID
         */
        public SortBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> routeViewId() {
            return this.sortBuilder.handler(RouteViewTemplateModel.routeViewId);
        }

        /**
         * 路由视图名称
         */
        public SortBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> routeViewName() {
            return this.sortBuilder.handler(RouteViewTemplateModel.routeViewName);
        }

        /**
         * 路由视图唯一标识符
         */
        public SortBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> routeViewValue() {
            return this.sortBuilder.handler(RouteViewTemplateModel.routeViewValue);
        }

        /**
         * 模板ID
         */
        public SortBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> templateId() {
            return this.sortBuilder.handler(RouteViewTemplateModel.templateId);
        }

        /**
         * 模板名称
         */
        public SortBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> templateName() {
            return this.sortBuilder.handler(RouteViewTemplateModel.templateName);
        }

        /**
         * 模板唯一标识符
         */
        public SortBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> templateValue() {
            return this.sortBuilder.handler(RouteViewTemplateModel.templateValue);
        }

        /**
         * 状态
         */
        public SortBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> status() {
            return this.sortBuilder.handler(RouteViewTemplateModel.status);
        }

        /**
         * 创建时间
         */
        public SortBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> createTime() {
            return this.sortBuilder.handler(RouteViewTemplateModel.createTime);
        }

        /**
         * 修改时间
         */
        public SortBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> updateTime() {
            return this.sortBuilder.handler(RouteViewTemplateModel.updateTime);
        }

        /**
         * 删除时间
         */
        public SortBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.sortBuilder.handler(RouteViewTemplateModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public SortBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.sortBuilder.handler(RouteViewTemplateModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public SortBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.sortBuilder.handler(RouteViewTemplateModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public SortBuilder<RouteViewTemplateModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.sortBuilder.handler(RouteViewTemplateModel.deleteTimeStamp);
        }

    }

}