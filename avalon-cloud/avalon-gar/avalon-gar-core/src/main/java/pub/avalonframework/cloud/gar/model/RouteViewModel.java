package pub.avalonframework.cloud.gar.model;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("all")
public final class RouteViewModel implements Model<RouteViewModel, RouteViewModel.Column, RouteViewModel.On, RouteViewModel.Where, RouteViewModel.Sort, RouteViewModel.Group> {

    /**
     * 表名
     */
    public static final String tableName = "gar_route_view";
    /**
     * 表别名
     */
    public static final String tableAlias = "RouteView";

    /**
     * 主键名
     */
    public static final String primaryKeyName = "id";
    /**
     * 主键别名
     */
    public static final String primaryKeyAlias = "id";



    /**
     * 主键
     */
    public static final String id = "id";
    /**
     * 主键
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
     * 所属子模块ID
     */
    public static final String subModuleId = "sub_module_id";
    /**
     * 所属子模块ID
     */
    public static final String subModuleId_alias = "subModuleId";
    /**
     * 所属子模块名称
     */
    public static final String subModuleName = "sub_module_name";
    /**
     * 所属子模块名称
     */
    public static final String subModuleName_alias = "subModuleName";
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
     * 路由视图名称
     */
    public static final String name = "name";
    /**
     * 路由视图名称
     */
    public static final String name_alias = "name";
    /**
     * 路由视图唯一标识符
     */
    public static final String value = "value";
    /**
     * 路由视图唯一标识符
     */
    public static final String value_alias = "value";
    /**
     * 是否使用props
     */
    public static final String props = "props";
    /**
     * 是否使用props
     */
    public static final String props_alias = "props";
    /**
     * 路由视图描述
     */
    public static final String description = "description";
    /**
     * 路由视图描述
     */
    public static final String description_alias = "description";
    /**
     * 排序号
     */
    public static final String index = "index";
    /**
     * 排序号
     */
    public static final String index_alias = "index";
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
        COLUMN_ALIAS_MAP.put(subModuleId, subModuleId_alias);
        ALIAS_COLUMN_MAP.put(subModuleId_alias, subModuleId);
        COLUMN_ALIAS_MAP.put(subModuleName, subModuleName_alias);
        ALIAS_COLUMN_MAP.put(subModuleName_alias, subModuleName);
        COLUMN_ALIAS_MAP.put(routeId, routeId_alias);
        ALIAS_COLUMN_MAP.put(routeId_alias, routeId);
        COLUMN_ALIAS_MAP.put(routeName, routeName_alias);
        ALIAS_COLUMN_MAP.put(routeName_alias, routeName);
        COLUMN_ALIAS_MAP.put(routeValue, routeValue_alias);
        ALIAS_COLUMN_MAP.put(routeValue_alias, routeValue);
        COLUMN_ALIAS_MAP.put(routePath, routePath_alias);
        ALIAS_COLUMN_MAP.put(routePath_alias, routePath);
        COLUMN_ALIAS_MAP.put(name, name_alias);
        ALIAS_COLUMN_MAP.put(name_alias, name);
        COLUMN_ALIAS_MAP.put(value, value_alias);
        ALIAS_COLUMN_MAP.put(value_alias, value);
        COLUMN_ALIAS_MAP.put(props, props_alias);
        ALIAS_COLUMN_MAP.put(props_alias, props);
        COLUMN_ALIAS_MAP.put(description, description_alias);
        ALIAS_COLUMN_MAP.put(description_alias, description);
        COLUMN_ALIAS_MAP.put(index, index_alias);
        ALIAS_COLUMN_MAP.put(index_alias, index);
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

    public static final class Column extends ColumnModel<RouteViewModel, Column, On, Where, Sort, Group> {

        private Column() {
        }

        /**
         * 主键
         */
        public Column primaryKey() {
            this.addColumnAlias(RouteViewModel.primaryKeyName, RouteViewModel.primaryKeyAlias);
            return this;
        }

        /**
         * 主键
         * @param alias 别名
         */
        public Column primaryKey(String alias) {
            this.addColumnAlias(RouteViewModel.primaryKeyName, alias);
            return this;
        }
    
        /**
         * 主键
         */
        public Column id() {
            this.addColumnAlias(RouteViewModel.id, RouteViewModel.id_alias);
            return this;
        }

        /**
         * 主键
         * @param alias 别名
         */
        public Column id(String alias) {
            this.addColumnAlias(RouteViewModel.id, alias);
            return this;
        }

        /**
         * 所属模块ID
         */
        public Column moduleId() {
            this.addColumnAlias(RouteViewModel.moduleId, RouteViewModel.moduleId_alias);
            return this;
        }

        /**
         * 所属模块ID
         * @param alias 别名
         */
        public Column moduleId(String alias) {
            this.addColumnAlias(RouteViewModel.moduleId, alias);
            return this;
        }

        /**
         * 所属子模块ID
         */
        public Column subModuleId() {
            this.addColumnAlias(RouteViewModel.subModuleId, RouteViewModel.subModuleId_alias);
            return this;
        }

        /**
         * 所属子模块ID
         * @param alias 别名
         */
        public Column subModuleId(String alias) {
            this.addColumnAlias(RouteViewModel.subModuleId, alias);
            return this;
        }

        /**
         * 所属子模块名称
         */
        public Column subModuleName() {
            this.addColumnAlias(RouteViewModel.subModuleName, RouteViewModel.subModuleName_alias);
            return this;
        }

        /**
         * 所属子模块名称
         * @param alias 别名
         */
        public Column subModuleName(String alias) {
            this.addColumnAlias(RouteViewModel.subModuleName, alias);
            return this;
        }

        /**
         * 路由ID
         */
        public Column routeId() {
            this.addColumnAlias(RouteViewModel.routeId, RouteViewModel.routeId_alias);
            return this;
        }

        /**
         * 路由ID
         * @param alias 别名
         */
        public Column routeId(String alias) {
            this.addColumnAlias(RouteViewModel.routeId, alias);
            return this;
        }

        /**
         * 路由名称
         */
        public Column routeName() {
            this.addColumnAlias(RouteViewModel.routeName, RouteViewModel.routeName_alias);
            return this;
        }

        /**
         * 路由名称
         * @param alias 别名
         */
        public Column routeName(String alias) {
            this.addColumnAlias(RouteViewModel.routeName, alias);
            return this;
        }

        /**
         * 路由唯一标识符
         */
        public Column routeValue() {
            this.addColumnAlias(RouteViewModel.routeValue, RouteViewModel.routeValue_alias);
            return this;
        }

        /**
         * 路由唯一标识符
         * @param alias 别名
         */
        public Column routeValue(String alias) {
            this.addColumnAlias(RouteViewModel.routeValue, alias);
            return this;
        }

        /**
         * 路由地址
         */
        public Column routePath() {
            this.addColumnAlias(RouteViewModel.routePath, RouteViewModel.routePath_alias);
            return this;
        }

        /**
         * 路由地址
         * @param alias 别名
         */
        public Column routePath(String alias) {
            this.addColumnAlias(RouteViewModel.routePath, alias);
            return this;
        }

        /**
         * 路由视图名称
         */
        public Column name() {
            this.addColumnAlias(RouteViewModel.name, RouteViewModel.name_alias);
            return this;
        }

        /**
         * 路由视图名称
         * @param alias 别名
         */
        public Column name(String alias) {
            this.addColumnAlias(RouteViewModel.name, alias);
            return this;
        }

        /**
         * 路由视图唯一标识符
         */
        public Column value() {
            this.addColumnAlias(RouteViewModel.value, RouteViewModel.value_alias);
            return this;
        }

        /**
         * 路由视图唯一标识符
         * @param alias 别名
         */
        public Column value(String alias) {
            this.addColumnAlias(RouteViewModel.value, alias);
            return this;
        }

        /**
         * 是否使用props
         */
        public Column props() {
            this.addColumnAlias(RouteViewModel.props, RouteViewModel.props_alias);
            return this;
        }

        /**
         * 是否使用props
         * @param alias 别名
         */
        public Column props(String alias) {
            this.addColumnAlias(RouteViewModel.props, alias);
            return this;
        }

        /**
         * 路由视图描述
         */
        public Column description() {
            this.addColumnAlias(RouteViewModel.description, RouteViewModel.description_alias);
            return this;
        }

        /**
         * 路由视图描述
         * @param alias 别名
         */
        public Column description(String alias) {
            this.addColumnAlias(RouteViewModel.description, alias);
            return this;
        }

        /**
         * 排序号
         */
        public Column index() {
            this.addColumnAlias(RouteViewModel.index, RouteViewModel.index_alias);
            return this;
        }

        /**
         * 排序号
         * @param alias 别名
         */
        public Column index(String alias) {
            this.addColumnAlias(RouteViewModel.index, alias);
            return this;
        }

        /**
         * 状态
         */
        public Column status() {
            this.addColumnAlias(RouteViewModel.status, RouteViewModel.status_alias);
            return this;
        }

        /**
         * 状态
         * @param alias 别名
         */
        public Column status(String alias) {
            this.addColumnAlias(RouteViewModel.status, alias);
            return this;
        }

        /**
         * 创建时间
         */
        public Column createTime() {
            this.addColumnAlias(RouteViewModel.createTime, RouteViewModel.createTime_alias);
            return this;
        }

        /**
         * 创建时间
         * @param alias 别名
         */
        public Column createTime(String alias) {
            this.addColumnAlias(RouteViewModel.createTime, alias);
            return this;
        }

        /**
         * 修改时间
         */
        public Column updateTime() {
            this.addColumnAlias(RouteViewModel.updateTime, RouteViewModel.updateTime_alias);
            return this;
        }

        /**
         * 修改时间
         * @param alias 别名
         */
        public Column updateTime(String alias) {
            this.addColumnAlias(RouteViewModel.updateTime, alias);
            return this;
        }

        /**
         * 删除时间
         */
        public Column deleteTime() {
            this.addColumnAlias(RouteViewModel.deleteTime, RouteViewModel.deleteTime_alias);
            return this;
        }

        /**
         * 删除时间
         * @param alias 别名
         */
        public Column deleteTime(String alias) {
            this.addColumnAlias(RouteViewModel.deleteTime, alias);
            return this;
        }

        /**
         * 创建时间戳
         */
        public Column createTimeStamp() {
            this.addColumnAlias(RouteViewModel.createTimeStamp, RouteViewModel.createTimeStamp_alias);
            return this;
        }

        /**
         * 创建时间戳
         * @param alias 别名
         */
        public Column createTimeStamp(String alias) {
            this.addColumnAlias(RouteViewModel.createTimeStamp, alias);
            return this;
        }

        /**
         * 修改时间戳
         */
        public Column updateTimeStamp() {
            this.addColumnAlias(RouteViewModel.updateTimeStamp, RouteViewModel.updateTimeStamp_alias);
            return this;
        }

        /**
         * 修改时间戳
         * @param alias 别名
         */
        public Column updateTimeStamp(String alias) {
            this.addColumnAlias(RouteViewModel.updateTimeStamp, alias);
            return this;
        }

        /**
         * 删除时间戳
         */
        public Column deleteTimeStamp() {
            this.addColumnAlias(RouteViewModel.deleteTimeStamp, RouteViewModel.deleteTimeStamp_alias);
            return this;
        }

        /**
         * 删除时间戳
         * @param alias 别名
         */
        public Column deleteTimeStamp(String alias) {
            this.addColumnAlias(RouteViewModel.deleteTimeStamp, alias);
            return this;
        }

    }

    @Override
    public On getOnModel() {
        return new On();
    }

    public static final class On extends OnModel<RouteViewModel, Column, On, Where, Sort, Group> {

        private On() {
        }

        /**
         * 主键
         */
        public OnBuilder<RouteViewModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.onBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.primaryKeyName);
        }
    
        /**
         * 主键
         */
        public OnBuilder<RouteViewModel, Column, On, Where, Sort, Group> id() {
            return this.onBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.id);
        }

        /**
         * 所属模块ID
         */
        public OnBuilder<RouteViewModel, Column, On, Where, Sort, Group> moduleId() {
            return this.onBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.moduleId);
        }

        /**
         * 所属子模块ID
         */
        public OnBuilder<RouteViewModel, Column, On, Where, Sort, Group> subModuleId() {
            return this.onBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.subModuleId);
        }

        /**
         * 所属子模块名称
         */
        public OnBuilder<RouteViewModel, Column, On, Where, Sort, Group> subModuleName() {
            return this.onBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.subModuleName);
        }

        /**
         * 路由ID
         */
        public OnBuilder<RouteViewModel, Column, On, Where, Sort, Group> routeId() {
            return this.onBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.routeId);
        }

        /**
         * 路由名称
         */
        public OnBuilder<RouteViewModel, Column, On, Where, Sort, Group> routeName() {
            return this.onBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.routeName);
        }

        /**
         * 路由唯一标识符
         */
        public OnBuilder<RouteViewModel, Column, On, Where, Sort, Group> routeValue() {
            return this.onBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.routeValue);
        }

        /**
         * 路由地址
         */
        public OnBuilder<RouteViewModel, Column, On, Where, Sort, Group> routePath() {
            return this.onBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.routePath);
        }

        /**
         * 路由视图名称
         */
        public OnBuilder<RouteViewModel, Column, On, Where, Sort, Group> name() {
            return this.onBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.name);
        }

        /**
         * 路由视图唯一标识符
         */
        public OnBuilder<RouteViewModel, Column, On, Where, Sort, Group> value() {
            return this.onBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.value);
        }

        /**
         * 是否使用props
         */
        public OnBuilder<RouteViewModel, Column, On, Where, Sort, Group> props() {
            return this.onBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.props);
        }

        /**
         * 路由视图描述
         */
        public OnBuilder<RouteViewModel, Column, On, Where, Sort, Group> description() {
            return this.onBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.description);
        }

        /**
         * 排序号
         */
        public OnBuilder<RouteViewModel, Column, On, Where, Sort, Group> index() {
            return this.onBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.index);
        }

        /**
         * 状态
         */
        public OnBuilder<RouteViewModel, Column, On, Where, Sort, Group> status() {
            return this.onBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.status);
        }

        /**
         * 创建时间
         */
        public OnBuilder<RouteViewModel, Column, On, Where, Sort, Group> createTime() {
            return this.onBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.createTime);
        }

        /**
         * 修改时间
         */
        public OnBuilder<RouteViewModel, Column, On, Where, Sort, Group> updateTime() {
            return this.onBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.updateTime);
        }

        /**
         * 删除时间
         */
        public OnBuilder<RouteViewModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.onBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public OnBuilder<RouteViewModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.onBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public OnBuilder<RouteViewModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.onBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public OnBuilder<RouteViewModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.onBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.deleteTimeStamp);
        }

    }

    @Override
    public Where getWhereModel() {
        return new Where();
    }

    public static final class Where extends WhereModel<RouteViewModel, Column, On, Where, Sort, Group> {

        private Where() {
        }

        /**
         * 主键
         */
        public WhereBuilder<RouteViewModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.whereBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.primaryKeyName);
        }
    
        /**
         * 主键
         */
        public WhereBuilder<RouteViewModel, Column, On, Where, Sort, Group> id() {
            return this.whereBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.id);
        }

        /**
         * 所属模块ID
         */
        public WhereBuilder<RouteViewModel, Column, On, Where, Sort, Group> moduleId() {
            return this.whereBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.moduleId);
        }

        /**
         * 所属子模块ID
         */
        public WhereBuilder<RouteViewModel, Column, On, Where, Sort, Group> subModuleId() {
            return this.whereBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.subModuleId);
        }

        /**
         * 所属子模块名称
         */
        public WhereBuilder<RouteViewModel, Column, On, Where, Sort, Group> subModuleName() {
            return this.whereBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.subModuleName);
        }

        /**
         * 路由ID
         */
        public WhereBuilder<RouteViewModel, Column, On, Where, Sort, Group> routeId() {
            return this.whereBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.routeId);
        }

        /**
         * 路由名称
         */
        public WhereBuilder<RouteViewModel, Column, On, Where, Sort, Group> routeName() {
            return this.whereBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.routeName);
        }

        /**
         * 路由唯一标识符
         */
        public WhereBuilder<RouteViewModel, Column, On, Where, Sort, Group> routeValue() {
            return this.whereBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.routeValue);
        }

        /**
         * 路由地址
         */
        public WhereBuilder<RouteViewModel, Column, On, Where, Sort, Group> routePath() {
            return this.whereBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.routePath);
        }

        /**
         * 路由视图名称
         */
        public WhereBuilder<RouteViewModel, Column, On, Where, Sort, Group> name() {
            return this.whereBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.name);
        }

        /**
         * 路由视图唯一标识符
         */
        public WhereBuilder<RouteViewModel, Column, On, Where, Sort, Group> value() {
            return this.whereBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.value);
        }

        /**
         * 是否使用props
         */
        public WhereBuilder<RouteViewModel, Column, On, Where, Sort, Group> props() {
            return this.whereBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.props);
        }

        /**
         * 路由视图描述
         */
        public WhereBuilder<RouteViewModel, Column, On, Where, Sort, Group> description() {
            return this.whereBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.description);
        }

        /**
         * 排序号
         */
        public WhereBuilder<RouteViewModel, Column, On, Where, Sort, Group> index() {
            return this.whereBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.index);
        }

        /**
         * 状态
         */
        public WhereBuilder<RouteViewModel, Column, On, Where, Sort, Group> status() {
            return this.whereBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.status);
        }

        /**
         * 创建时间
         */
        public WhereBuilder<RouteViewModel, Column, On, Where, Sort, Group> createTime() {
            return this.whereBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.createTime);
        }

        /**
         * 修改时间
         */
        public WhereBuilder<RouteViewModel, Column, On, Where, Sort, Group> updateTime() {
            return this.whereBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.updateTime);
        }

        /**
         * 删除时间
         */
        public WhereBuilder<RouteViewModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.whereBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public WhereBuilder<RouteViewModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.whereBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public WhereBuilder<RouteViewModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.whereBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public WhereBuilder<RouteViewModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.whereBuilder.handler(RouteViewModel.tableName, RouteViewModel.tableAlias, RouteViewModel.deleteTimeStamp);
        }

    }

    @Override
    public Group getGroupModel() {
        return new Group();
    }

    public static final class Group extends GroupModel<RouteViewModel, Column, On, Where, Sort, Group> {

        private Group() {
        }

        /**
         * 主键
         */
        public Group primaryKey() {
            this.addColumn(RouteViewModel.primaryKeyName);
            return this;
        }
    
        /**
         * 主键
         */
        public Group id() {
            this.addColumn(RouteViewModel.id);
            return this;
        }

        /**
         * 所属模块ID
         */
        public Group moduleId() {
            this.addColumn(RouteViewModel.moduleId);
            return this;
        }

        /**
         * 所属子模块ID
         */
        public Group subModuleId() {
            this.addColumn(RouteViewModel.subModuleId);
            return this;
        }

        /**
         * 所属子模块名称
         */
        public Group subModuleName() {
            this.addColumn(RouteViewModel.subModuleName);
            return this;
        }

        /**
         * 路由ID
         */
        public Group routeId() {
            this.addColumn(RouteViewModel.routeId);
            return this;
        }

        /**
         * 路由名称
         */
        public Group routeName() {
            this.addColumn(RouteViewModel.routeName);
            return this;
        }

        /**
         * 路由唯一标识符
         */
        public Group routeValue() {
            this.addColumn(RouteViewModel.routeValue);
            return this;
        }

        /**
         * 路由地址
         */
        public Group routePath() {
            this.addColumn(RouteViewModel.routePath);
            return this;
        }

        /**
         * 路由视图名称
         */
        public Group name() {
            this.addColumn(RouteViewModel.name);
            return this;
        }

        /**
         * 路由视图唯一标识符
         */
        public Group value() {
            this.addColumn(RouteViewModel.value);
            return this;
        }

        /**
         * 是否使用props
         */
        public Group props() {
            this.addColumn(RouteViewModel.props);
            return this;
        }

        /**
         * 路由视图描述
         */
        public Group description() {
            this.addColumn(RouteViewModel.description);
            return this;
        }

        /**
         * 排序号
         */
        public Group index() {
            this.addColumn(RouteViewModel.index);
            return this;
        }

        /**
         * 状态
         */
        public Group status() {
            this.addColumn(RouteViewModel.status);
            return this;
        }

        /**
         * 创建时间
         */
        public Group createTime() {
            this.addColumn(RouteViewModel.createTime);
            return this;
        }

        /**
         * 修改时间
         */
        public Group updateTime() {
            this.addColumn(RouteViewModel.updateTime);
            return this;
        }

        /**
         * 删除时间
         */
        public Group deleteTime() {
            this.addColumn(RouteViewModel.deleteTime);
            return this;
        }

        /**
         * 创建时间戳
         */
        public Group createTimeStamp() {
            this.addColumn(RouteViewModel.createTimeStamp);
            return this;
        }

        /**
         * 修改时间戳
         */
        public Group updateTimeStamp() {
            this.addColumn(RouteViewModel.updateTimeStamp);
            return this;
        }

        /**
         * 删除时间戳
         */
        public Group deleteTimeStamp() {
            this.addColumn(RouteViewModel.deleteTimeStamp);
            return this;
        }

    }

    @Override
    public Sort getSortModel() {
        return new Sort();
    }

    public static final class Sort extends SortModel<RouteViewModel, Column, On, Where, Sort, Group> {

        private Sort() {
        }

        /**
         * 主键
         */
        public SortBuilder<RouteViewModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.sortBuilder.handler(RouteViewModel.primaryKeyName);
        }
    
        /**
         * 主键
         */
        public SortBuilder<RouteViewModel, Column, On, Where, Sort, Group> id() {
            return this.sortBuilder.handler(RouteViewModel.id);
        }

        /**
         * 所属模块ID
         */
        public SortBuilder<RouteViewModel, Column, On, Where, Sort, Group> moduleId() {
            return this.sortBuilder.handler(RouteViewModel.moduleId);
        }

        /**
         * 所属子模块ID
         */
        public SortBuilder<RouteViewModel, Column, On, Where, Sort, Group> subModuleId() {
            return this.sortBuilder.handler(RouteViewModel.subModuleId);
        }

        /**
         * 所属子模块名称
         */
        public SortBuilder<RouteViewModel, Column, On, Where, Sort, Group> subModuleName() {
            return this.sortBuilder.handler(RouteViewModel.subModuleName);
        }

        /**
         * 路由ID
         */
        public SortBuilder<RouteViewModel, Column, On, Where, Sort, Group> routeId() {
            return this.sortBuilder.handler(RouteViewModel.routeId);
        }

        /**
         * 路由名称
         */
        public SortBuilder<RouteViewModel, Column, On, Where, Sort, Group> routeName() {
            return this.sortBuilder.handler(RouteViewModel.routeName);
        }

        /**
         * 路由唯一标识符
         */
        public SortBuilder<RouteViewModel, Column, On, Where, Sort, Group> routeValue() {
            return this.sortBuilder.handler(RouteViewModel.routeValue);
        }

        /**
         * 路由地址
         */
        public SortBuilder<RouteViewModel, Column, On, Where, Sort, Group> routePath() {
            return this.sortBuilder.handler(RouteViewModel.routePath);
        }

        /**
         * 路由视图名称
         */
        public SortBuilder<RouteViewModel, Column, On, Where, Sort, Group> name() {
            return this.sortBuilder.handler(RouteViewModel.name);
        }

        /**
         * 路由视图唯一标识符
         */
        public SortBuilder<RouteViewModel, Column, On, Where, Sort, Group> value() {
            return this.sortBuilder.handler(RouteViewModel.value);
        }

        /**
         * 是否使用props
         */
        public SortBuilder<RouteViewModel, Column, On, Where, Sort, Group> props() {
            return this.sortBuilder.handler(RouteViewModel.props);
        }

        /**
         * 路由视图描述
         */
        public SortBuilder<RouteViewModel, Column, On, Where, Sort, Group> description() {
            return this.sortBuilder.handler(RouteViewModel.description);
        }

        /**
         * 排序号
         */
        public SortBuilder<RouteViewModel, Column, On, Where, Sort, Group> index() {
            return this.sortBuilder.handler(RouteViewModel.index);
        }

        /**
         * 状态
         */
        public SortBuilder<RouteViewModel, Column, On, Where, Sort, Group> status() {
            return this.sortBuilder.handler(RouteViewModel.status);
        }

        /**
         * 创建时间
         */
        public SortBuilder<RouteViewModel, Column, On, Where, Sort, Group> createTime() {
            return this.sortBuilder.handler(RouteViewModel.createTime);
        }

        /**
         * 修改时间
         */
        public SortBuilder<RouteViewModel, Column, On, Where, Sort, Group> updateTime() {
            return this.sortBuilder.handler(RouteViewModel.updateTime);
        }

        /**
         * 删除时间
         */
        public SortBuilder<RouteViewModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.sortBuilder.handler(RouteViewModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public SortBuilder<RouteViewModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.sortBuilder.handler(RouteViewModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public SortBuilder<RouteViewModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.sortBuilder.handler(RouteViewModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public SortBuilder<RouteViewModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.sortBuilder.handler(RouteViewModel.deleteTimeStamp);
        }

    }

}