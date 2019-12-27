package pub.avalonframework.cloud.gar.model;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("all")
public final class AutResourceModel implements Model<AutResourceModel, AutResourceModel.Column, AutResourceModel.On, AutResourceModel.Where, AutResourceModel.Sort, AutResourceModel.Group> {

    /**
     * 表名
     */
    public static final String tableName = "gar_aut_resource";
    /**
     * 表别名
     */
    public static final String tableAlias = "AutResource";

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
     * 批次ID
     */
    public static final String batchId = "batch_id";
    /**
     * 批次ID
     */
    public static final String batchId_alias = "batchId";
    /**
     * 名称
     */
    public static final String name = "name";
    /**
     * 名称
     */
    public static final String name_alias = "name";
    /**
     * 资源类型
     */
    public static final String type = "type";
    /**
     * 资源类型
     */
    public static final String type_alias = "type";
    /**
     * 资源地址
     */
    public static final String url = "url";
    /**
     * 资源地址
     */
    public static final String url_alias = "url";
    /**
     * 页面路径
     */
    public static final String path = "path";
    /**
     * 页面路径
     */
    public static final String path_alias = "path";
    /**
     * 资源描述
     */
    public static final String description = "description";
    /**
     * 资源描述
     */
    public static final String description_alias = "description";
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
     * 权限
     */
    public static final String permission = "permission";
    /**
     * 权限
     */
    public static final String permission_alias = "permission";
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
        COLUMN_ALIAS_MAP.put(batchId, batchId_alias);
        ALIAS_COLUMN_MAP.put(batchId_alias, batchId);
        COLUMN_ALIAS_MAP.put(name, name_alias);
        ALIAS_COLUMN_MAP.put(name_alias, name);
        COLUMN_ALIAS_MAP.put(type, type_alias);
        ALIAS_COLUMN_MAP.put(type_alias, type);
        COLUMN_ALIAS_MAP.put(url, url_alias);
        ALIAS_COLUMN_MAP.put(url_alias, url);
        COLUMN_ALIAS_MAP.put(path, path_alias);
        ALIAS_COLUMN_MAP.put(path_alias, path);
        COLUMN_ALIAS_MAP.put(description, description_alias);
        ALIAS_COLUMN_MAP.put(description_alias, description);
        COLUMN_ALIAS_MAP.put(parentId, parentId_alias);
        ALIAS_COLUMN_MAP.put(parentId_alias, parentId);
        COLUMN_ALIAS_MAP.put(parentIds, parentIds_alias);
        ALIAS_COLUMN_MAP.put(parentIds_alias, parentIds);
        COLUMN_ALIAS_MAP.put(permission, permission_alias);
        ALIAS_COLUMN_MAP.put(permission_alias, permission);
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

    public static final class Column extends ColumnModel<AutResourceModel, Column, On, Where, Sort, Group> {

        private Column() {
        }

        /**
         * 主键
         */
        public Column primaryKey() {
            this.addColumnAlias(AutResourceModel.primaryKeyName, AutResourceModel.primaryKeyAlias);
            return this;
        }

        /**
         * 主键
         * @param alias 别名
         */
        public Column primaryKey(String alias) {
            this.addColumnAlias(AutResourceModel.primaryKeyName, alias);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Column id() {
            this.addColumnAlias(AutResourceModel.id, AutResourceModel.id_alias);
            return this;
        }

        /**
         * 主键ID
         * @param alias 别名
         */
        public Column id(String alias) {
            this.addColumnAlias(AutResourceModel.id, alias);
            return this;
        }

        /**
         * 批次ID
         */
        public Column batchId() {
            this.addColumnAlias(AutResourceModel.batchId, AutResourceModel.batchId_alias);
            return this;
        }

        /**
         * 批次ID
         * @param alias 别名
         */
        public Column batchId(String alias) {
            this.addColumnAlias(AutResourceModel.batchId, alias);
            return this;
        }

        /**
         * 名称
         */
        public Column name() {
            this.addColumnAlias(AutResourceModel.name, AutResourceModel.name_alias);
            return this;
        }

        /**
         * 名称
         * @param alias 别名
         */
        public Column name(String alias) {
            this.addColumnAlias(AutResourceModel.name, alias);
            return this;
        }

        /**
         * 资源类型
         */
        public Column type() {
            this.addColumnAlias(AutResourceModel.type, AutResourceModel.type_alias);
            return this;
        }

        /**
         * 资源类型
         * @param alias 别名
         */
        public Column type(String alias) {
            this.addColumnAlias(AutResourceModel.type, alias);
            return this;
        }

        /**
         * 资源地址
         */
        public Column url() {
            this.addColumnAlias(AutResourceModel.url, AutResourceModel.url_alias);
            return this;
        }

        /**
         * 资源地址
         * @param alias 别名
         */
        public Column url(String alias) {
            this.addColumnAlias(AutResourceModel.url, alias);
            return this;
        }

        /**
         * 页面路径
         */
        public Column path() {
            this.addColumnAlias(AutResourceModel.path, AutResourceModel.path_alias);
            return this;
        }

        /**
         * 页面路径
         * @param alias 别名
         */
        public Column path(String alias) {
            this.addColumnAlias(AutResourceModel.path, alias);
            return this;
        }

        /**
         * 资源描述
         */
        public Column description() {
            this.addColumnAlias(AutResourceModel.description, AutResourceModel.description_alias);
            return this;
        }

        /**
         * 资源描述
         * @param alias 别名
         */
        public Column description(String alias) {
            this.addColumnAlias(AutResourceModel.description, alias);
            return this;
        }

        /**
         * 父id
         */
        public Column parentId() {
            this.addColumnAlias(AutResourceModel.parentId, AutResourceModel.parentId_alias);
            return this;
        }

        /**
         * 父id
         * @param alias 别名
         */
        public Column parentId(String alias) {
            this.addColumnAlias(AutResourceModel.parentId, alias);
            return this;
        }

        /**
         * 祖先id
         */
        public Column parentIds() {
            this.addColumnAlias(AutResourceModel.parentIds, AutResourceModel.parentIds_alias);
            return this;
        }

        /**
         * 祖先id
         * @param alias 别名
         */
        public Column parentIds(String alias) {
            this.addColumnAlias(AutResourceModel.parentIds, alias);
            return this;
        }

        /**
         * 权限
         */
        public Column permission() {
            this.addColumnAlias(AutResourceModel.permission, AutResourceModel.permission_alias);
            return this;
        }

        /**
         * 权限
         * @param alias 别名
         */
        public Column permission(String alias) {
            this.addColumnAlias(AutResourceModel.permission, alias);
            return this;
        }

        /**
         * 排序号
         */
        public Column index() {
            this.addColumnAlias(AutResourceModel.index, AutResourceModel.index_alias);
            return this;
        }

        /**
         * 排序号
         * @param alias 别名
         */
        public Column index(String alias) {
            this.addColumnAlias(AutResourceModel.index, alias);
            return this;
        }

        /**
         * 状态
         */
        public Column status() {
            this.addColumnAlias(AutResourceModel.status, AutResourceModel.status_alias);
            return this;
        }

        /**
         * 状态
         * @param alias 别名
         */
        public Column status(String alias) {
            this.addColumnAlias(AutResourceModel.status, alias);
            return this;
        }

        /**
         * 创建时间
         */
        public Column createTime() {
            this.addColumnAlias(AutResourceModel.createTime, AutResourceModel.createTime_alias);
            return this;
        }

        /**
         * 创建时间
         * @param alias 别名
         */
        public Column createTime(String alias) {
            this.addColumnAlias(AutResourceModel.createTime, alias);
            return this;
        }

        /**
         * 修改时间
         */
        public Column updateTime() {
            this.addColumnAlias(AutResourceModel.updateTime, AutResourceModel.updateTime_alias);
            return this;
        }

        /**
         * 修改时间
         * @param alias 别名
         */
        public Column updateTime(String alias) {
            this.addColumnAlias(AutResourceModel.updateTime, alias);
            return this;
        }

        /**
         * 删除时间
         */
        public Column deleteTime() {
            this.addColumnAlias(AutResourceModel.deleteTime, AutResourceModel.deleteTime_alias);
            return this;
        }

        /**
         * 删除时间
         * @param alias 别名
         */
        public Column deleteTime(String alias) {
            this.addColumnAlias(AutResourceModel.deleteTime, alias);
            return this;
        }

        /**
         * 创建时间戳
         */
        public Column createTimeStamp() {
            this.addColumnAlias(AutResourceModel.createTimeStamp, AutResourceModel.createTimeStamp_alias);
            return this;
        }

        /**
         * 创建时间戳
         * @param alias 别名
         */
        public Column createTimeStamp(String alias) {
            this.addColumnAlias(AutResourceModel.createTimeStamp, alias);
            return this;
        }

        /**
         * 修改时间戳
         */
        public Column updateTimeStamp() {
            this.addColumnAlias(AutResourceModel.updateTimeStamp, AutResourceModel.updateTimeStamp_alias);
            return this;
        }

        /**
         * 修改时间戳
         * @param alias 别名
         */
        public Column updateTimeStamp(String alias) {
            this.addColumnAlias(AutResourceModel.updateTimeStamp, alias);
            return this;
        }

        /**
         * 删除时间戳
         */
        public Column deleteTimeStamp() {
            this.addColumnAlias(AutResourceModel.deleteTimeStamp, AutResourceModel.deleteTimeStamp_alias);
            return this;
        }

        /**
         * 删除时间戳
         * @param alias 别名
         */
        public Column deleteTimeStamp(String alias) {
            this.addColumnAlias(AutResourceModel.deleteTimeStamp, alias);
            return this;
        }

    }

    @Override
    public On getOnModel() {
        return new On();
    }

    public static final class On extends OnModel<AutResourceModel, Column, On, Where, Sort, Group> {

        private On() {
        }

        /**
         * 主键
         */
        public OnBuilder<AutResourceModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.onBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public OnBuilder<AutResourceModel, Column, On, Where, Sort, Group> id() {
            return this.onBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.id);
        }

        /**
         * 批次ID
         */
        public OnBuilder<AutResourceModel, Column, On, Where, Sort, Group> batchId() {
            return this.onBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.batchId);
        }

        /**
         * 名称
         */
        public OnBuilder<AutResourceModel, Column, On, Where, Sort, Group> name() {
            return this.onBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.name);
        }

        /**
         * 资源类型
         */
        public OnBuilder<AutResourceModel, Column, On, Where, Sort, Group> type() {
            return this.onBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.type);
        }

        /**
         * 资源地址
         */
        public OnBuilder<AutResourceModel, Column, On, Where, Sort, Group> url() {
            return this.onBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.url);
        }

        /**
         * 页面路径
         */
        public OnBuilder<AutResourceModel, Column, On, Where, Sort, Group> path() {
            return this.onBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.path);
        }

        /**
         * 资源描述
         */
        public OnBuilder<AutResourceModel, Column, On, Where, Sort, Group> description() {
            return this.onBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.description);
        }

        /**
         * 父id
         */
        public OnBuilder<AutResourceModel, Column, On, Where, Sort, Group> parentId() {
            return this.onBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.parentId);
        }

        /**
         * 祖先id
         */
        public OnBuilder<AutResourceModel, Column, On, Where, Sort, Group> parentIds() {
            return this.onBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.parentIds);
        }

        /**
         * 权限
         */
        public OnBuilder<AutResourceModel, Column, On, Where, Sort, Group> permission() {
            return this.onBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.permission);
        }

        /**
         * 排序号
         */
        public OnBuilder<AutResourceModel, Column, On, Where, Sort, Group> index() {
            return this.onBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.index);
        }

        /**
         * 状态
         */
        public OnBuilder<AutResourceModel, Column, On, Where, Sort, Group> status() {
            return this.onBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.status);
        }

        /**
         * 创建时间
         */
        public OnBuilder<AutResourceModel, Column, On, Where, Sort, Group> createTime() {
            return this.onBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.createTime);
        }

        /**
         * 修改时间
         */
        public OnBuilder<AutResourceModel, Column, On, Where, Sort, Group> updateTime() {
            return this.onBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.updateTime);
        }

        /**
         * 删除时间
         */
        public OnBuilder<AutResourceModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.onBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public OnBuilder<AutResourceModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.onBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public OnBuilder<AutResourceModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.onBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public OnBuilder<AutResourceModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.onBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.deleteTimeStamp);
        }

    }

    @Override
    public Where getWhereModel() {
        return new Where();
    }

    public static final class Where extends WhereModel<AutResourceModel, Column, On, Where, Sort, Group> {

        private Where() {
        }

        /**
         * 主键
         */
        public WhereBuilder<AutResourceModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.whereBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public WhereBuilder<AutResourceModel, Column, On, Where, Sort, Group> id() {
            return this.whereBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.id);
        }

        /**
         * 批次ID
         */
        public WhereBuilder<AutResourceModel, Column, On, Where, Sort, Group> batchId() {
            return this.whereBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.batchId);
        }

        /**
         * 名称
         */
        public WhereBuilder<AutResourceModel, Column, On, Where, Sort, Group> name() {
            return this.whereBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.name);
        }

        /**
         * 资源类型
         */
        public WhereBuilder<AutResourceModel, Column, On, Where, Sort, Group> type() {
            return this.whereBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.type);
        }

        /**
         * 资源地址
         */
        public WhereBuilder<AutResourceModel, Column, On, Where, Sort, Group> url() {
            return this.whereBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.url);
        }

        /**
         * 页面路径
         */
        public WhereBuilder<AutResourceModel, Column, On, Where, Sort, Group> path() {
            return this.whereBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.path);
        }

        /**
         * 资源描述
         */
        public WhereBuilder<AutResourceModel, Column, On, Where, Sort, Group> description() {
            return this.whereBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.description);
        }

        /**
         * 父id
         */
        public WhereBuilder<AutResourceModel, Column, On, Where, Sort, Group> parentId() {
            return this.whereBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.parentId);
        }

        /**
         * 祖先id
         */
        public WhereBuilder<AutResourceModel, Column, On, Where, Sort, Group> parentIds() {
            return this.whereBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.parentIds);
        }

        /**
         * 权限
         */
        public WhereBuilder<AutResourceModel, Column, On, Where, Sort, Group> permission() {
            return this.whereBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.permission);
        }

        /**
         * 排序号
         */
        public WhereBuilder<AutResourceModel, Column, On, Where, Sort, Group> index() {
            return this.whereBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.index);
        }

        /**
         * 状态
         */
        public WhereBuilder<AutResourceModel, Column, On, Where, Sort, Group> status() {
            return this.whereBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.status);
        }

        /**
         * 创建时间
         */
        public WhereBuilder<AutResourceModel, Column, On, Where, Sort, Group> createTime() {
            return this.whereBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.createTime);
        }

        /**
         * 修改时间
         */
        public WhereBuilder<AutResourceModel, Column, On, Where, Sort, Group> updateTime() {
            return this.whereBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.updateTime);
        }

        /**
         * 删除时间
         */
        public WhereBuilder<AutResourceModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.whereBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public WhereBuilder<AutResourceModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.whereBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public WhereBuilder<AutResourceModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.whereBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public WhereBuilder<AutResourceModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.whereBuilder.handler(AutResourceModel.tableName, AutResourceModel.tableAlias, AutResourceModel.deleteTimeStamp);
        }

    }

    @Override
    public Group getGroupModel() {
        return new Group();
    }

    public static final class Group extends GroupModel<AutResourceModel, Column, On, Where, Sort, Group> {

        private Group() {
        }

        /**
         * 主键
         */
        public Group primaryKey() {
            this.addColumn(AutResourceModel.primaryKeyName);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Group id() {
            this.addColumn(AutResourceModel.id);
            return this;
        }

        /**
         * 批次ID
         */
        public Group batchId() {
            this.addColumn(AutResourceModel.batchId);
            return this;
        }

        /**
         * 名称
         */
        public Group name() {
            this.addColumn(AutResourceModel.name);
            return this;
        }

        /**
         * 资源类型
         */
        public Group type() {
            this.addColumn(AutResourceModel.type);
            return this;
        }

        /**
         * 资源地址
         */
        public Group url() {
            this.addColumn(AutResourceModel.url);
            return this;
        }

        /**
         * 页面路径
         */
        public Group path() {
            this.addColumn(AutResourceModel.path);
            return this;
        }

        /**
         * 资源描述
         */
        public Group description() {
            this.addColumn(AutResourceModel.description);
            return this;
        }

        /**
         * 父id
         */
        public Group parentId() {
            this.addColumn(AutResourceModel.parentId);
            return this;
        }

        /**
         * 祖先id
         */
        public Group parentIds() {
            this.addColumn(AutResourceModel.parentIds);
            return this;
        }

        /**
         * 权限
         */
        public Group permission() {
            this.addColumn(AutResourceModel.permission);
            return this;
        }

        /**
         * 排序号
         */
        public Group index() {
            this.addColumn(AutResourceModel.index);
            return this;
        }

        /**
         * 状态
         */
        public Group status() {
            this.addColumn(AutResourceModel.status);
            return this;
        }

        /**
         * 创建时间
         */
        public Group createTime() {
            this.addColumn(AutResourceModel.createTime);
            return this;
        }

        /**
         * 修改时间
         */
        public Group updateTime() {
            this.addColumn(AutResourceModel.updateTime);
            return this;
        }

        /**
         * 删除时间
         */
        public Group deleteTime() {
            this.addColumn(AutResourceModel.deleteTime);
            return this;
        }

        /**
         * 创建时间戳
         */
        public Group createTimeStamp() {
            this.addColumn(AutResourceModel.createTimeStamp);
            return this;
        }

        /**
         * 修改时间戳
         */
        public Group updateTimeStamp() {
            this.addColumn(AutResourceModel.updateTimeStamp);
            return this;
        }

        /**
         * 删除时间戳
         */
        public Group deleteTimeStamp() {
            this.addColumn(AutResourceModel.deleteTimeStamp);
            return this;
        }

    }

    @Override
    public Sort getSortModel() {
        return new Sort();
    }

    public static final class Sort extends SortModel<AutResourceModel, Column, On, Where, Sort, Group> {

        private Sort() {
        }

        /**
         * 主键
         */
        public SortBuilder<AutResourceModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.sortBuilder.handler(AutResourceModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public SortBuilder<AutResourceModel, Column, On, Where, Sort, Group> id() {
            return this.sortBuilder.handler(AutResourceModel.id);
        }

        /**
         * 批次ID
         */
        public SortBuilder<AutResourceModel, Column, On, Where, Sort, Group> batchId() {
            return this.sortBuilder.handler(AutResourceModel.batchId);
        }

        /**
         * 名称
         */
        public SortBuilder<AutResourceModel, Column, On, Where, Sort, Group> name() {
            return this.sortBuilder.handler(AutResourceModel.name);
        }

        /**
         * 资源类型
         */
        public SortBuilder<AutResourceModel, Column, On, Where, Sort, Group> type() {
            return this.sortBuilder.handler(AutResourceModel.type);
        }

        /**
         * 资源地址
         */
        public SortBuilder<AutResourceModel, Column, On, Where, Sort, Group> url() {
            return this.sortBuilder.handler(AutResourceModel.url);
        }

        /**
         * 页面路径
         */
        public SortBuilder<AutResourceModel, Column, On, Where, Sort, Group> path() {
            return this.sortBuilder.handler(AutResourceModel.path);
        }

        /**
         * 资源描述
         */
        public SortBuilder<AutResourceModel, Column, On, Where, Sort, Group> description() {
            return this.sortBuilder.handler(AutResourceModel.description);
        }

        /**
         * 父id
         */
        public SortBuilder<AutResourceModel, Column, On, Where, Sort, Group> parentId() {
            return this.sortBuilder.handler(AutResourceModel.parentId);
        }

        /**
         * 祖先id
         */
        public SortBuilder<AutResourceModel, Column, On, Where, Sort, Group> parentIds() {
            return this.sortBuilder.handler(AutResourceModel.parentIds);
        }

        /**
         * 权限
         */
        public SortBuilder<AutResourceModel, Column, On, Where, Sort, Group> permission() {
            return this.sortBuilder.handler(AutResourceModel.permission);
        }

        /**
         * 排序号
         */
        public SortBuilder<AutResourceModel, Column, On, Where, Sort, Group> index() {
            return this.sortBuilder.handler(AutResourceModel.index);
        }

        /**
         * 状态
         */
        public SortBuilder<AutResourceModel, Column, On, Where, Sort, Group> status() {
            return this.sortBuilder.handler(AutResourceModel.status);
        }

        /**
         * 创建时间
         */
        public SortBuilder<AutResourceModel, Column, On, Where, Sort, Group> createTime() {
            return this.sortBuilder.handler(AutResourceModel.createTime);
        }

        /**
         * 修改时间
         */
        public SortBuilder<AutResourceModel, Column, On, Where, Sort, Group> updateTime() {
            return this.sortBuilder.handler(AutResourceModel.updateTime);
        }

        /**
         * 删除时间
         */
        public SortBuilder<AutResourceModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.sortBuilder.handler(AutResourceModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public SortBuilder<AutResourceModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.sortBuilder.handler(AutResourceModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public SortBuilder<AutResourceModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.sortBuilder.handler(AutResourceModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public SortBuilder<AutResourceModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.sortBuilder.handler(AutResourceModel.deleteTimeStamp);
        }

    }

}