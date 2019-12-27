package pub.avalonframework.cloud.gar.model;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("all")
public final class AutRoleModel implements Model<AutRoleModel, AutRoleModel.Column, AutRoleModel.On, AutRoleModel.Where, AutRoleModel.Sort, AutRoleModel.Group> {

    /**
     * 表名
     */
    public static final String tableName = "gar_aut_role";
    /**
     * 表别名
     */
    public static final String tableAlias = "AutRole";

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
     * 角色名称
     */
    public static final String name = "name";
    /**
     * 角色名称
     */
    public static final String name_alias = "name";
    /**
     * 角色标识符
     */
    public static final String value = "value";
    /**
     * 角色标识符
     */
    public static final String value_alias = "value";
    /**
     * 角色描述
     */
    public static final String description = "description";
    /**
     * 角色描述
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
     * 角色类型
     */
    public static final String type = "type";
    /**
     * 角色类型
     */
    public static final String type_alias = "type";
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
        COLUMN_ALIAS_MAP.put(name, name_alias);
        ALIAS_COLUMN_MAP.put(name_alias, name);
        COLUMN_ALIAS_MAP.put(value, value_alias);
        ALIAS_COLUMN_MAP.put(value_alias, value);
        COLUMN_ALIAS_MAP.put(description, description_alias);
        ALIAS_COLUMN_MAP.put(description_alias, description);
        COLUMN_ALIAS_MAP.put(parentId, parentId_alias);
        ALIAS_COLUMN_MAP.put(parentId_alias, parentId);
        COLUMN_ALIAS_MAP.put(parentIds, parentIds_alias);
        ALIAS_COLUMN_MAP.put(parentIds_alias, parentIds);
        COLUMN_ALIAS_MAP.put(type, type_alias);
        ALIAS_COLUMN_MAP.put(type_alias, type);
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

    public static final class Column extends ColumnModel<AutRoleModel, Column, On, Where, Sort, Group> {

        private Column() {
        }

        /**
         * 主键
         */
        public Column primaryKey() {
            this.addColumnAlias(AutRoleModel.primaryKeyName, AutRoleModel.primaryKeyAlias);
            return this;
        }

        /**
         * 主键
         * @param alias 别名
         */
        public Column primaryKey(String alias) {
            this.addColumnAlias(AutRoleModel.primaryKeyName, alias);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Column id() {
            this.addColumnAlias(AutRoleModel.id, AutRoleModel.id_alias);
            return this;
        }

        /**
         * 主键ID
         * @param alias 别名
         */
        public Column id(String alias) {
            this.addColumnAlias(AutRoleModel.id, alias);
            return this;
        }

        /**
         * 角色名称
         */
        public Column name() {
            this.addColumnAlias(AutRoleModel.name, AutRoleModel.name_alias);
            return this;
        }

        /**
         * 角色名称
         * @param alias 别名
         */
        public Column name(String alias) {
            this.addColumnAlias(AutRoleModel.name, alias);
            return this;
        }

        /**
         * 角色标识符
         */
        public Column value() {
            this.addColumnAlias(AutRoleModel.value, AutRoleModel.value_alias);
            return this;
        }

        /**
         * 角色标识符
         * @param alias 别名
         */
        public Column value(String alias) {
            this.addColumnAlias(AutRoleModel.value, alias);
            return this;
        }

        /**
         * 角色描述
         */
        public Column description() {
            this.addColumnAlias(AutRoleModel.description, AutRoleModel.description_alias);
            return this;
        }

        /**
         * 角色描述
         * @param alias 别名
         */
        public Column description(String alias) {
            this.addColumnAlias(AutRoleModel.description, alias);
            return this;
        }

        /**
         * 父id
         */
        public Column parentId() {
            this.addColumnAlias(AutRoleModel.parentId, AutRoleModel.parentId_alias);
            return this;
        }

        /**
         * 父id
         * @param alias 别名
         */
        public Column parentId(String alias) {
            this.addColumnAlias(AutRoleModel.parentId, alias);
            return this;
        }

        /**
         * 祖先id
         */
        public Column parentIds() {
            this.addColumnAlias(AutRoleModel.parentIds, AutRoleModel.parentIds_alias);
            return this;
        }

        /**
         * 祖先id
         * @param alias 别名
         */
        public Column parentIds(String alias) {
            this.addColumnAlias(AutRoleModel.parentIds, alias);
            return this;
        }

        /**
         * 角色类型
         */
        public Column type() {
            this.addColumnAlias(AutRoleModel.type, AutRoleModel.type_alias);
            return this;
        }

        /**
         * 角色类型
         * @param alias 别名
         */
        public Column type(String alias) {
            this.addColumnAlias(AutRoleModel.type, alias);
            return this;
        }

        /**
         * 排序号
         */
        public Column index() {
            this.addColumnAlias(AutRoleModel.index, AutRoleModel.index_alias);
            return this;
        }

        /**
         * 排序号
         * @param alias 别名
         */
        public Column index(String alias) {
            this.addColumnAlias(AutRoleModel.index, alias);
            return this;
        }

        /**
         * 状态
         */
        public Column status() {
            this.addColumnAlias(AutRoleModel.status, AutRoleModel.status_alias);
            return this;
        }

        /**
         * 状态
         * @param alias 别名
         */
        public Column status(String alias) {
            this.addColumnAlias(AutRoleModel.status, alias);
            return this;
        }

        /**
         * 创建时间
         */
        public Column createTime() {
            this.addColumnAlias(AutRoleModel.createTime, AutRoleModel.createTime_alias);
            return this;
        }

        /**
         * 创建时间
         * @param alias 别名
         */
        public Column createTime(String alias) {
            this.addColumnAlias(AutRoleModel.createTime, alias);
            return this;
        }

        /**
         * 修改时间
         */
        public Column updateTime() {
            this.addColumnAlias(AutRoleModel.updateTime, AutRoleModel.updateTime_alias);
            return this;
        }

        /**
         * 修改时间
         * @param alias 别名
         */
        public Column updateTime(String alias) {
            this.addColumnAlias(AutRoleModel.updateTime, alias);
            return this;
        }

        /**
         * 删除时间
         */
        public Column deleteTime() {
            this.addColumnAlias(AutRoleModel.deleteTime, AutRoleModel.deleteTime_alias);
            return this;
        }

        /**
         * 删除时间
         * @param alias 别名
         */
        public Column deleteTime(String alias) {
            this.addColumnAlias(AutRoleModel.deleteTime, alias);
            return this;
        }

        /**
         * 创建时间戳
         */
        public Column createTimeStamp() {
            this.addColumnAlias(AutRoleModel.createTimeStamp, AutRoleModel.createTimeStamp_alias);
            return this;
        }

        /**
         * 创建时间戳
         * @param alias 别名
         */
        public Column createTimeStamp(String alias) {
            this.addColumnAlias(AutRoleModel.createTimeStamp, alias);
            return this;
        }

        /**
         * 修改时间戳
         */
        public Column updateTimeStamp() {
            this.addColumnAlias(AutRoleModel.updateTimeStamp, AutRoleModel.updateTimeStamp_alias);
            return this;
        }

        /**
         * 修改时间戳
         * @param alias 别名
         */
        public Column updateTimeStamp(String alias) {
            this.addColumnAlias(AutRoleModel.updateTimeStamp, alias);
            return this;
        }

        /**
         * 删除时间戳
         */
        public Column deleteTimeStamp() {
            this.addColumnAlias(AutRoleModel.deleteTimeStamp, AutRoleModel.deleteTimeStamp_alias);
            return this;
        }

        /**
         * 删除时间戳
         * @param alias 别名
         */
        public Column deleteTimeStamp(String alias) {
            this.addColumnAlias(AutRoleModel.deleteTimeStamp, alias);
            return this;
        }

    }

    @Override
    public On getOnModel() {
        return new On();
    }

    public static final class On extends OnModel<AutRoleModel, Column, On, Where, Sort, Group> {

        private On() {
        }

        /**
         * 主键
         */
        public OnBuilder<AutRoleModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.onBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public OnBuilder<AutRoleModel, Column, On, Where, Sort, Group> id() {
            return this.onBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.id);
        }

        /**
         * 角色名称
         */
        public OnBuilder<AutRoleModel, Column, On, Where, Sort, Group> name() {
            return this.onBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.name);
        }

        /**
         * 角色标识符
         */
        public OnBuilder<AutRoleModel, Column, On, Where, Sort, Group> value() {
            return this.onBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.value);
        }

        /**
         * 角色描述
         */
        public OnBuilder<AutRoleModel, Column, On, Where, Sort, Group> description() {
            return this.onBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.description);
        }

        /**
         * 父id
         */
        public OnBuilder<AutRoleModel, Column, On, Where, Sort, Group> parentId() {
            return this.onBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.parentId);
        }

        /**
         * 祖先id
         */
        public OnBuilder<AutRoleModel, Column, On, Where, Sort, Group> parentIds() {
            return this.onBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.parentIds);
        }

        /**
         * 角色类型
         */
        public OnBuilder<AutRoleModel, Column, On, Where, Sort, Group> type() {
            return this.onBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.type);
        }

        /**
         * 排序号
         */
        public OnBuilder<AutRoleModel, Column, On, Where, Sort, Group> index() {
            return this.onBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.index);
        }

        /**
         * 状态
         */
        public OnBuilder<AutRoleModel, Column, On, Where, Sort, Group> status() {
            return this.onBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.status);
        }

        /**
         * 创建时间
         */
        public OnBuilder<AutRoleModel, Column, On, Where, Sort, Group> createTime() {
            return this.onBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.createTime);
        }

        /**
         * 修改时间
         */
        public OnBuilder<AutRoleModel, Column, On, Where, Sort, Group> updateTime() {
            return this.onBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.updateTime);
        }

        /**
         * 删除时间
         */
        public OnBuilder<AutRoleModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.onBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public OnBuilder<AutRoleModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.onBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public OnBuilder<AutRoleModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.onBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public OnBuilder<AutRoleModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.onBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.deleteTimeStamp);
        }

    }

    @Override
    public Where getWhereModel() {
        return new Where();
    }

    public static final class Where extends WhereModel<AutRoleModel, Column, On, Where, Sort, Group> {

        private Where() {
        }

        /**
         * 主键
         */
        public WhereBuilder<AutRoleModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.whereBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public WhereBuilder<AutRoleModel, Column, On, Where, Sort, Group> id() {
            return this.whereBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.id);
        }

        /**
         * 角色名称
         */
        public WhereBuilder<AutRoleModel, Column, On, Where, Sort, Group> name() {
            return this.whereBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.name);
        }

        /**
         * 角色标识符
         */
        public WhereBuilder<AutRoleModel, Column, On, Where, Sort, Group> value() {
            return this.whereBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.value);
        }

        /**
         * 角色描述
         */
        public WhereBuilder<AutRoleModel, Column, On, Where, Sort, Group> description() {
            return this.whereBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.description);
        }

        /**
         * 父id
         */
        public WhereBuilder<AutRoleModel, Column, On, Where, Sort, Group> parentId() {
            return this.whereBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.parentId);
        }

        /**
         * 祖先id
         */
        public WhereBuilder<AutRoleModel, Column, On, Where, Sort, Group> parentIds() {
            return this.whereBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.parentIds);
        }

        /**
         * 角色类型
         */
        public WhereBuilder<AutRoleModel, Column, On, Where, Sort, Group> type() {
            return this.whereBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.type);
        }

        /**
         * 排序号
         */
        public WhereBuilder<AutRoleModel, Column, On, Where, Sort, Group> index() {
            return this.whereBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.index);
        }

        /**
         * 状态
         */
        public WhereBuilder<AutRoleModel, Column, On, Where, Sort, Group> status() {
            return this.whereBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.status);
        }

        /**
         * 创建时间
         */
        public WhereBuilder<AutRoleModel, Column, On, Where, Sort, Group> createTime() {
            return this.whereBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.createTime);
        }

        /**
         * 修改时间
         */
        public WhereBuilder<AutRoleModel, Column, On, Where, Sort, Group> updateTime() {
            return this.whereBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.updateTime);
        }

        /**
         * 删除时间
         */
        public WhereBuilder<AutRoleModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.whereBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public WhereBuilder<AutRoleModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.whereBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public WhereBuilder<AutRoleModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.whereBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public WhereBuilder<AutRoleModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.whereBuilder.handler(AutRoleModel.tableName, AutRoleModel.tableAlias, AutRoleModel.deleteTimeStamp);
        }

    }

    @Override
    public Group getGroupModel() {
        return new Group();
    }

    public static final class Group extends GroupModel<AutRoleModel, Column, On, Where, Sort, Group> {

        private Group() {
        }

        /**
         * 主键
         */
        public Group primaryKey() {
            this.addColumn(AutRoleModel.primaryKeyName);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Group id() {
            this.addColumn(AutRoleModel.id);
            return this;
        }

        /**
         * 角色名称
         */
        public Group name() {
            this.addColumn(AutRoleModel.name);
            return this;
        }

        /**
         * 角色标识符
         */
        public Group value() {
            this.addColumn(AutRoleModel.value);
            return this;
        }

        /**
         * 角色描述
         */
        public Group description() {
            this.addColumn(AutRoleModel.description);
            return this;
        }

        /**
         * 父id
         */
        public Group parentId() {
            this.addColumn(AutRoleModel.parentId);
            return this;
        }

        /**
         * 祖先id
         */
        public Group parentIds() {
            this.addColumn(AutRoleModel.parentIds);
            return this;
        }

        /**
         * 角色类型
         */
        public Group type() {
            this.addColumn(AutRoleModel.type);
            return this;
        }

        /**
         * 排序号
         */
        public Group index() {
            this.addColumn(AutRoleModel.index);
            return this;
        }

        /**
         * 状态
         */
        public Group status() {
            this.addColumn(AutRoleModel.status);
            return this;
        }

        /**
         * 创建时间
         */
        public Group createTime() {
            this.addColumn(AutRoleModel.createTime);
            return this;
        }

        /**
         * 修改时间
         */
        public Group updateTime() {
            this.addColumn(AutRoleModel.updateTime);
            return this;
        }

        /**
         * 删除时间
         */
        public Group deleteTime() {
            this.addColumn(AutRoleModel.deleteTime);
            return this;
        }

        /**
         * 创建时间戳
         */
        public Group createTimeStamp() {
            this.addColumn(AutRoleModel.createTimeStamp);
            return this;
        }

        /**
         * 修改时间戳
         */
        public Group updateTimeStamp() {
            this.addColumn(AutRoleModel.updateTimeStamp);
            return this;
        }

        /**
         * 删除时间戳
         */
        public Group deleteTimeStamp() {
            this.addColumn(AutRoleModel.deleteTimeStamp);
            return this;
        }

    }

    @Override
    public Sort getSortModel() {
        return new Sort();
    }

    public static final class Sort extends SortModel<AutRoleModel, Column, On, Where, Sort, Group> {

        private Sort() {
        }

        /**
         * 主键
         */
        public SortBuilder<AutRoleModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.sortBuilder.handler(AutRoleModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public SortBuilder<AutRoleModel, Column, On, Where, Sort, Group> id() {
            return this.sortBuilder.handler(AutRoleModel.id);
        }

        /**
         * 角色名称
         */
        public SortBuilder<AutRoleModel, Column, On, Where, Sort, Group> name() {
            return this.sortBuilder.handler(AutRoleModel.name);
        }

        /**
         * 角色标识符
         */
        public SortBuilder<AutRoleModel, Column, On, Where, Sort, Group> value() {
            return this.sortBuilder.handler(AutRoleModel.value);
        }

        /**
         * 角色描述
         */
        public SortBuilder<AutRoleModel, Column, On, Where, Sort, Group> description() {
            return this.sortBuilder.handler(AutRoleModel.description);
        }

        /**
         * 父id
         */
        public SortBuilder<AutRoleModel, Column, On, Where, Sort, Group> parentId() {
            return this.sortBuilder.handler(AutRoleModel.parentId);
        }

        /**
         * 祖先id
         */
        public SortBuilder<AutRoleModel, Column, On, Where, Sort, Group> parentIds() {
            return this.sortBuilder.handler(AutRoleModel.parentIds);
        }

        /**
         * 角色类型
         */
        public SortBuilder<AutRoleModel, Column, On, Where, Sort, Group> type() {
            return this.sortBuilder.handler(AutRoleModel.type);
        }

        /**
         * 排序号
         */
        public SortBuilder<AutRoleModel, Column, On, Where, Sort, Group> index() {
            return this.sortBuilder.handler(AutRoleModel.index);
        }

        /**
         * 状态
         */
        public SortBuilder<AutRoleModel, Column, On, Where, Sort, Group> status() {
            return this.sortBuilder.handler(AutRoleModel.status);
        }

        /**
         * 创建时间
         */
        public SortBuilder<AutRoleModel, Column, On, Where, Sort, Group> createTime() {
            return this.sortBuilder.handler(AutRoleModel.createTime);
        }

        /**
         * 修改时间
         */
        public SortBuilder<AutRoleModel, Column, On, Where, Sort, Group> updateTime() {
            return this.sortBuilder.handler(AutRoleModel.updateTime);
        }

        /**
         * 删除时间
         */
        public SortBuilder<AutRoleModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.sortBuilder.handler(AutRoleModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public SortBuilder<AutRoleModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.sortBuilder.handler(AutRoleModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public SortBuilder<AutRoleModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.sortBuilder.handler(AutRoleModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public SortBuilder<AutRoleModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.sortBuilder.handler(AutRoleModel.deleteTimeStamp);
        }

    }

}