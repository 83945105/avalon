package pub.avalonframework.cloud.gar.model;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("all")
public final class RouteModel implements Model<RouteModel, RouteModel.Column, RouteModel.On, RouteModel.Where, RouteModel.Sort, RouteModel.Group> {

    /**
     * 表名
     */
    public static final String tableName = "gar_route";
    /**
     * 表别名
     */
    public static final String tableAlias = "Route";

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
     * 路由名称
     */
    public static final String name = "name";
    /**
     * 路由名称
     */
    public static final String name_alias = "name";
    /**
     * 路由唯一标识符
     */
    public static final String value = "value";
    /**
     * 路由唯一标识符
     */
    public static final String value_alias = "value";
    /**
     * 模块描述
     */
    public static final String description = "description";
    /**
     * 模块描述
     */
    public static final String description_alias = "description";
    /**
     * 路由地址
     */
    public static final String path = "path";
    /**
     * 路由地址
     */
    public static final String path_alias = "path";
    /**
     * 父id
     */
    public static final String parentId = "parent_id";
    /**
     * 父id
     */
    public static final String parentId_alias = "parentId";
    /**
     * 祖先id
     */
    public static final String parentIds = "parent_ids";
    /**
     * 祖先id
     */
    public static final String parentIds_alias = "parentIds";
    /**
     * 路由别名
     */
    public static final String alias = "alias";
    /**
     * 路由别名
     */
    public static final String alias_alias = "alias";
    /**
     * 是否缓存
     */
    public static final String cache = "cache";
    /**
     * 是否缓存
     */
    public static final String cache_alias = "cache";
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
        COLUMN_ALIAS_MAP.put(name, name_alias);
        ALIAS_COLUMN_MAP.put(name_alias, name);
        COLUMN_ALIAS_MAP.put(value, value_alias);
        ALIAS_COLUMN_MAP.put(value_alias, value);
        COLUMN_ALIAS_MAP.put(description, description_alias);
        ALIAS_COLUMN_MAP.put(description_alias, description);
        COLUMN_ALIAS_MAP.put(path, path_alias);
        ALIAS_COLUMN_MAP.put(path_alias, path);
        COLUMN_ALIAS_MAP.put(parentId, parentId_alias);
        ALIAS_COLUMN_MAP.put(parentId_alias, parentId);
        COLUMN_ALIAS_MAP.put(parentIds, parentIds_alias);
        ALIAS_COLUMN_MAP.put(parentIds_alias, parentIds);
        COLUMN_ALIAS_MAP.put(alias, alias_alias);
        ALIAS_COLUMN_MAP.put(alias_alias, alias);
        COLUMN_ALIAS_MAP.put(cache, cache_alias);
        ALIAS_COLUMN_MAP.put(cache_alias, cache);
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

    public static final class Column extends ColumnModel<RouteModel, Column, On, Where, Sort, Group> {

        private Column() {
        }

        /**
         * 主键
         */
        public Column primaryKey() {
            this.addColumnAlias(RouteModel.primaryKeyName, RouteModel.primaryKeyAlias);
            return this;
        }

        /**
         * 主键
         * @param alias 别名
         */
        public Column primaryKey(String alias) {
            this.addColumnAlias(RouteModel.primaryKeyName, alias);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Column id() {
            this.addColumnAlias(RouteModel.id, RouteModel.id_alias);
            return this;
        }

        /**
         * 主键ID
         * @param alias 别名
         */
        public Column id(String alias) {
            this.addColumnAlias(RouteModel.id, alias);
            return this;
        }

        /**
         * 所属模块ID
         */
        public Column moduleId() {
            this.addColumnAlias(RouteModel.moduleId, RouteModel.moduleId_alias);
            return this;
        }

        /**
         * 所属模块ID
         * @param alias 别名
         */
        public Column moduleId(String alias) {
            this.addColumnAlias(RouteModel.moduleId, alias);
            return this;
        }

        /**
         * 所属子模块ID
         */
        public Column subModuleId() {
            this.addColumnAlias(RouteModel.subModuleId, RouteModel.subModuleId_alias);
            return this;
        }

        /**
         * 所属子模块ID
         * @param alias 别名
         */
        public Column subModuleId(String alias) {
            this.addColumnAlias(RouteModel.subModuleId, alias);
            return this;
        }

        /**
         * 所属子模块名称
         */
        public Column subModuleName() {
            this.addColumnAlias(RouteModel.subModuleName, RouteModel.subModuleName_alias);
            return this;
        }

        /**
         * 所属子模块名称
         * @param alias 别名
         */
        public Column subModuleName(String alias) {
            this.addColumnAlias(RouteModel.subModuleName, alias);
            return this;
        }

        /**
         * 路由名称
         */
        public Column name() {
            this.addColumnAlias(RouteModel.name, RouteModel.name_alias);
            return this;
        }

        /**
         * 路由名称
         * @param alias 别名
         */
        public Column name(String alias) {
            this.addColumnAlias(RouteModel.name, alias);
            return this;
        }

        /**
         * 路由唯一标识符
         */
        public Column value() {
            this.addColumnAlias(RouteModel.value, RouteModel.value_alias);
            return this;
        }

        /**
         * 路由唯一标识符
         * @param alias 别名
         */
        public Column value(String alias) {
            this.addColumnAlias(RouteModel.value, alias);
            return this;
        }

        /**
         * 模块描述
         */
        public Column description() {
            this.addColumnAlias(RouteModel.description, RouteModel.description_alias);
            return this;
        }

        /**
         * 模块描述
         * @param alias 别名
         */
        public Column description(String alias) {
            this.addColumnAlias(RouteModel.description, alias);
            return this;
        }

        /**
         * 路由地址
         */
        public Column path() {
            this.addColumnAlias(RouteModel.path, RouteModel.path_alias);
            return this;
        }

        /**
         * 路由地址
         * @param alias 别名
         */
        public Column path(String alias) {
            this.addColumnAlias(RouteModel.path, alias);
            return this;
        }

        /**
         * 父id
         */
        public Column parentId() {
            this.addColumnAlias(RouteModel.parentId, RouteModel.parentId_alias);
            return this;
        }

        /**
         * 父id
         * @param alias 别名
         */
        public Column parentId(String alias) {
            this.addColumnAlias(RouteModel.parentId, alias);
            return this;
        }

        /**
         * 祖先id
         */
        public Column parentIds() {
            this.addColumnAlias(RouteModel.parentIds, RouteModel.parentIds_alias);
            return this;
        }

        /**
         * 祖先id
         * @param alias 别名
         */
        public Column parentIds(String alias) {
            this.addColumnAlias(RouteModel.parentIds, alias);
            return this;
        }

        /**
         * 路由别名
         */
        public Column alias() {
            this.addColumnAlias(RouteModel.alias, RouteModel.alias_alias);
            return this;
        }

        /**
         * 路由别名
         * @param alias 别名
         */
        public Column alias(String alias) {
            this.addColumnAlias(RouteModel.alias, alias);
            return this;
        }

        /**
         * 是否缓存
         */
        public Column cache() {
            this.addColumnAlias(RouteModel.cache, RouteModel.cache_alias);
            return this;
        }

        /**
         * 是否缓存
         * @param alias 别名
         */
        public Column cache(String alias) {
            this.addColumnAlias(RouteModel.cache, alias);
            return this;
        }

        /**
         * 排序号
         */
        public Column index() {
            this.addColumnAlias(RouteModel.index, RouteModel.index_alias);
            return this;
        }

        /**
         * 排序号
         * @param alias 别名
         */
        public Column index(String alias) {
            this.addColumnAlias(RouteModel.index, alias);
            return this;
        }

        /**
         * 状态
         */
        public Column status() {
            this.addColumnAlias(RouteModel.status, RouteModel.status_alias);
            return this;
        }

        /**
         * 状态
         * @param alias 别名
         */
        public Column status(String alias) {
            this.addColumnAlias(RouteModel.status, alias);
            return this;
        }

        /**
         * 创建时间
         */
        public Column createTime() {
            this.addColumnAlias(RouteModel.createTime, RouteModel.createTime_alias);
            return this;
        }

        /**
         * 创建时间
         * @param alias 别名
         */
        public Column createTime(String alias) {
            this.addColumnAlias(RouteModel.createTime, alias);
            return this;
        }

        /**
         * 修改时间
         */
        public Column updateTime() {
            this.addColumnAlias(RouteModel.updateTime, RouteModel.updateTime_alias);
            return this;
        }

        /**
         * 修改时间
         * @param alias 别名
         */
        public Column updateTime(String alias) {
            this.addColumnAlias(RouteModel.updateTime, alias);
            return this;
        }

        /**
         * 删除时间
         */
        public Column deleteTime() {
            this.addColumnAlias(RouteModel.deleteTime, RouteModel.deleteTime_alias);
            return this;
        }

        /**
         * 删除时间
         * @param alias 别名
         */
        public Column deleteTime(String alias) {
            this.addColumnAlias(RouteModel.deleteTime, alias);
            return this;
        }

        /**
         * 创建时间戳
         */
        public Column createTimeStamp() {
            this.addColumnAlias(RouteModel.createTimeStamp, RouteModel.createTimeStamp_alias);
            return this;
        }

        /**
         * 创建时间戳
         * @param alias 别名
         */
        public Column createTimeStamp(String alias) {
            this.addColumnAlias(RouteModel.createTimeStamp, alias);
            return this;
        }

        /**
         * 修改时间戳
         */
        public Column updateTimeStamp() {
            this.addColumnAlias(RouteModel.updateTimeStamp, RouteModel.updateTimeStamp_alias);
            return this;
        }

        /**
         * 修改时间戳
         * @param alias 别名
         */
        public Column updateTimeStamp(String alias) {
            this.addColumnAlias(RouteModel.updateTimeStamp, alias);
            return this;
        }

        /**
         * 删除时间戳
         */
        public Column deleteTimeStamp() {
            this.addColumnAlias(RouteModel.deleteTimeStamp, RouteModel.deleteTimeStamp_alias);
            return this;
        }

        /**
         * 删除时间戳
         * @param alias 别名
         */
        public Column deleteTimeStamp(String alias) {
            this.addColumnAlias(RouteModel.deleteTimeStamp, alias);
            return this;
        }

    }

    @Override
    public On getOnModel() {
        return new On();
    }

    public static final class On extends OnModel<RouteModel, Column, On, Where, Sort, Group> {

        private On() {
        }

        /**
         * 主键
         */
        public OnBuilder<RouteModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.onBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public OnBuilder<RouteModel, Column, On, Where, Sort, Group> id() {
            return this.onBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.id);
        }

        /**
         * 所属模块ID
         */
        public OnBuilder<RouteModel, Column, On, Where, Sort, Group> moduleId() {
            return this.onBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.moduleId);
        }

        /**
         * 所属子模块ID
         */
        public OnBuilder<RouteModel, Column, On, Where, Sort, Group> subModuleId() {
            return this.onBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.subModuleId);
        }

        /**
         * 所属子模块名称
         */
        public OnBuilder<RouteModel, Column, On, Where, Sort, Group> subModuleName() {
            return this.onBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.subModuleName);
        }

        /**
         * 路由名称
         */
        public OnBuilder<RouteModel, Column, On, Where, Sort, Group> name() {
            return this.onBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.name);
        }

        /**
         * 路由唯一标识符
         */
        public OnBuilder<RouteModel, Column, On, Where, Sort, Group> value() {
            return this.onBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.value);
        }

        /**
         * 模块描述
         */
        public OnBuilder<RouteModel, Column, On, Where, Sort, Group> description() {
            return this.onBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.description);
        }

        /**
         * 路由地址
         */
        public OnBuilder<RouteModel, Column, On, Where, Sort, Group> path() {
            return this.onBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.path);
        }

        /**
         * 父id
         */
        public OnBuilder<RouteModel, Column, On, Where, Sort, Group> parentId() {
            return this.onBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.parentId);
        }

        /**
         * 祖先id
         */
        public OnBuilder<RouteModel, Column, On, Where, Sort, Group> parentIds() {
            return this.onBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.parentIds);
        }

        /**
         * 路由别名
         */
        public OnBuilder<RouteModel, Column, On, Where, Sort, Group> alias() {
            return this.onBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.alias);
        }

        /**
         * 是否缓存
         */
        public OnBuilder<RouteModel, Column, On, Where, Sort, Group> cache() {
            return this.onBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.cache);
        }

        /**
         * 排序号
         */
        public OnBuilder<RouteModel, Column, On, Where, Sort, Group> index() {
            return this.onBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.index);
        }

        /**
         * 状态
         */
        public OnBuilder<RouteModel, Column, On, Where, Sort, Group> status() {
            return this.onBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.status);
        }

        /**
         * 创建时间
         */
        public OnBuilder<RouteModel, Column, On, Where, Sort, Group> createTime() {
            return this.onBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.createTime);
        }

        /**
         * 修改时间
         */
        public OnBuilder<RouteModel, Column, On, Where, Sort, Group> updateTime() {
            return this.onBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.updateTime);
        }

        /**
         * 删除时间
         */
        public OnBuilder<RouteModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.onBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public OnBuilder<RouteModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.onBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public OnBuilder<RouteModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.onBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public OnBuilder<RouteModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.onBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.deleteTimeStamp);
        }

    }

    @Override
    public Where getWhereModel() {
        return new Where();
    }

    public static final class Where extends WhereModel<RouteModel, Column, On, Where, Sort, Group> {

        private Where() {
        }

        /**
         * 主键
         */
        public WhereBuilder<RouteModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.whereBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public WhereBuilder<RouteModel, Column, On, Where, Sort, Group> id() {
            return this.whereBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.id);
        }

        /**
         * 所属模块ID
         */
        public WhereBuilder<RouteModel, Column, On, Where, Sort, Group> moduleId() {
            return this.whereBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.moduleId);
        }

        /**
         * 所属子模块ID
         */
        public WhereBuilder<RouteModel, Column, On, Where, Sort, Group> subModuleId() {
            return this.whereBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.subModuleId);
        }

        /**
         * 所属子模块名称
         */
        public WhereBuilder<RouteModel, Column, On, Where, Sort, Group> subModuleName() {
            return this.whereBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.subModuleName);
        }

        /**
         * 路由名称
         */
        public WhereBuilder<RouteModel, Column, On, Where, Sort, Group> name() {
            return this.whereBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.name);
        }

        /**
         * 路由唯一标识符
         */
        public WhereBuilder<RouteModel, Column, On, Where, Sort, Group> value() {
            return this.whereBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.value);
        }

        /**
         * 模块描述
         */
        public WhereBuilder<RouteModel, Column, On, Where, Sort, Group> description() {
            return this.whereBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.description);
        }

        /**
         * 路由地址
         */
        public WhereBuilder<RouteModel, Column, On, Where, Sort, Group> path() {
            return this.whereBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.path);
        }

        /**
         * 父id
         */
        public WhereBuilder<RouteModel, Column, On, Where, Sort, Group> parentId() {
            return this.whereBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.parentId);
        }

        /**
         * 祖先id
         */
        public WhereBuilder<RouteModel, Column, On, Where, Sort, Group> parentIds() {
            return this.whereBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.parentIds);
        }

        /**
         * 路由别名
         */
        public WhereBuilder<RouteModel, Column, On, Where, Sort, Group> alias() {
            return this.whereBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.alias);
        }

        /**
         * 是否缓存
         */
        public WhereBuilder<RouteModel, Column, On, Where, Sort, Group> cache() {
            return this.whereBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.cache);
        }

        /**
         * 排序号
         */
        public WhereBuilder<RouteModel, Column, On, Where, Sort, Group> index() {
            return this.whereBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.index);
        }

        /**
         * 状态
         */
        public WhereBuilder<RouteModel, Column, On, Where, Sort, Group> status() {
            return this.whereBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.status);
        }

        /**
         * 创建时间
         */
        public WhereBuilder<RouteModel, Column, On, Where, Sort, Group> createTime() {
            return this.whereBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.createTime);
        }

        /**
         * 修改时间
         */
        public WhereBuilder<RouteModel, Column, On, Where, Sort, Group> updateTime() {
            return this.whereBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.updateTime);
        }

        /**
         * 删除时间
         */
        public WhereBuilder<RouteModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.whereBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public WhereBuilder<RouteModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.whereBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public WhereBuilder<RouteModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.whereBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public WhereBuilder<RouteModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.whereBuilder.handler(RouteModel.tableName, RouteModel.tableAlias, RouteModel.deleteTimeStamp);
        }

    }

    @Override
    public Group getGroupModel() {
        return new Group();
    }

    public static final class Group extends GroupModel<RouteModel, Column, On, Where, Sort, Group> {

        private Group() {
        }

        /**
         * 主键
         */
        public Group primaryKey() {
            this.addColumn(RouteModel.primaryKeyName);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Group id() {
            this.addColumn(RouteModel.id);
            return this;
        }

        /**
         * 所属模块ID
         */
        public Group moduleId() {
            this.addColumn(RouteModel.moduleId);
            return this;
        }

        /**
         * 所属子模块ID
         */
        public Group subModuleId() {
            this.addColumn(RouteModel.subModuleId);
            return this;
        }

        /**
         * 所属子模块名称
         */
        public Group subModuleName() {
            this.addColumn(RouteModel.subModuleName);
            return this;
        }

        /**
         * 路由名称
         */
        public Group name() {
            this.addColumn(RouteModel.name);
            return this;
        }

        /**
         * 路由唯一标识符
         */
        public Group value() {
            this.addColumn(RouteModel.value);
            return this;
        }

        /**
         * 模块描述
         */
        public Group description() {
            this.addColumn(RouteModel.description);
            return this;
        }

        /**
         * 路由地址
         */
        public Group path() {
            this.addColumn(RouteModel.path);
            return this;
        }

        /**
         * 父id
         */
        public Group parentId() {
            this.addColumn(RouteModel.parentId);
            return this;
        }

        /**
         * 祖先id
         */
        public Group parentIds() {
            this.addColumn(RouteModel.parentIds);
            return this;
        }

        /**
         * 路由别名
         */
        public Group alias() {
            this.addColumn(RouteModel.alias);
            return this;
        }

        /**
         * 是否缓存
         */
        public Group cache() {
            this.addColumn(RouteModel.cache);
            return this;
        }

        /**
         * 排序号
         */
        public Group index() {
            this.addColumn(RouteModel.index);
            return this;
        }

        /**
         * 状态
         */
        public Group status() {
            this.addColumn(RouteModel.status);
            return this;
        }

        /**
         * 创建时间
         */
        public Group createTime() {
            this.addColumn(RouteModel.createTime);
            return this;
        }

        /**
         * 修改时间
         */
        public Group updateTime() {
            this.addColumn(RouteModel.updateTime);
            return this;
        }

        /**
         * 删除时间
         */
        public Group deleteTime() {
            this.addColumn(RouteModel.deleteTime);
            return this;
        }

        /**
         * 创建时间戳
         */
        public Group createTimeStamp() {
            this.addColumn(RouteModel.createTimeStamp);
            return this;
        }

        /**
         * 修改时间戳
         */
        public Group updateTimeStamp() {
            this.addColumn(RouteModel.updateTimeStamp);
            return this;
        }

        /**
         * 删除时间戳
         */
        public Group deleteTimeStamp() {
            this.addColumn(RouteModel.deleteTimeStamp);
            return this;
        }

    }

    @Override
    public Sort getSortModel() {
        return new Sort();
    }

    public static final class Sort extends SortModel<RouteModel, Column, On, Where, Sort, Group> {

        private Sort() {
        }

        /**
         * 主键
         */
        public SortBuilder<RouteModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.sortBuilder.handler(RouteModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public SortBuilder<RouteModel, Column, On, Where, Sort, Group> id() {
            return this.sortBuilder.handler(RouteModel.id);
        }

        /**
         * 所属模块ID
         */
        public SortBuilder<RouteModel, Column, On, Where, Sort, Group> moduleId() {
            return this.sortBuilder.handler(RouteModel.moduleId);
        }

        /**
         * 所属子模块ID
         */
        public SortBuilder<RouteModel, Column, On, Where, Sort, Group> subModuleId() {
            return this.sortBuilder.handler(RouteModel.subModuleId);
        }

        /**
         * 所属子模块名称
         */
        public SortBuilder<RouteModel, Column, On, Where, Sort, Group> subModuleName() {
            return this.sortBuilder.handler(RouteModel.subModuleName);
        }

        /**
         * 路由名称
         */
        public SortBuilder<RouteModel, Column, On, Where, Sort, Group> name() {
            return this.sortBuilder.handler(RouteModel.name);
        }

        /**
         * 路由唯一标识符
         */
        public SortBuilder<RouteModel, Column, On, Where, Sort, Group> value() {
            return this.sortBuilder.handler(RouteModel.value);
        }

        /**
         * 模块描述
         */
        public SortBuilder<RouteModel, Column, On, Where, Sort, Group> description() {
            return this.sortBuilder.handler(RouteModel.description);
        }

        /**
         * 路由地址
         */
        public SortBuilder<RouteModel, Column, On, Where, Sort, Group> path() {
            return this.sortBuilder.handler(RouteModel.path);
        }

        /**
         * 父id
         */
        public SortBuilder<RouteModel, Column, On, Where, Sort, Group> parentId() {
            return this.sortBuilder.handler(RouteModel.parentId);
        }

        /**
         * 祖先id
         */
        public SortBuilder<RouteModel, Column, On, Where, Sort, Group> parentIds() {
            return this.sortBuilder.handler(RouteModel.parentIds);
        }

        /**
         * 路由别名
         */
        public SortBuilder<RouteModel, Column, On, Where, Sort, Group> alias() {
            return this.sortBuilder.handler(RouteModel.alias);
        }

        /**
         * 是否缓存
         */
        public SortBuilder<RouteModel, Column, On, Where, Sort, Group> cache() {
            return this.sortBuilder.handler(RouteModel.cache);
        }

        /**
         * 排序号
         */
        public SortBuilder<RouteModel, Column, On, Where, Sort, Group> index() {
            return this.sortBuilder.handler(RouteModel.index);
        }

        /**
         * 状态
         */
        public SortBuilder<RouteModel, Column, On, Where, Sort, Group> status() {
            return this.sortBuilder.handler(RouteModel.status);
        }

        /**
         * 创建时间
         */
        public SortBuilder<RouteModel, Column, On, Where, Sort, Group> createTime() {
            return this.sortBuilder.handler(RouteModel.createTime);
        }

        /**
         * 修改时间
         */
        public SortBuilder<RouteModel, Column, On, Where, Sort, Group> updateTime() {
            return this.sortBuilder.handler(RouteModel.updateTime);
        }

        /**
         * 删除时间
         */
        public SortBuilder<RouteModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.sortBuilder.handler(RouteModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public SortBuilder<RouteModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.sortBuilder.handler(RouteModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public SortBuilder<RouteModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.sortBuilder.handler(RouteModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public SortBuilder<RouteModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.sortBuilder.handler(RouteModel.deleteTimeStamp);
        }

    }

}