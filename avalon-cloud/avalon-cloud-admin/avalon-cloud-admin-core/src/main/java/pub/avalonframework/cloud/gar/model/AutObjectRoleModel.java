package pub.avalonframework.cloud.gar.model;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("all")
public final class AutObjectRoleModel implements Model<AutObjectRoleModel, AutObjectRoleModel.Column, AutObjectRoleModel.On, AutObjectRoleModel.Where, AutObjectRoleModel.Sort, AutObjectRoleModel.Group> {

    /**
     * 表名
     */
    public static final String tableName = "gar_aut_object_role";
    /**
     * 表别名
     */
    public static final String tableAlias = "AutObjectRole";

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
     * 角色id
     */
    public static final String roleId = "role_id";
    /**
     * 角色id
     */
    public static final String roleId_alias = "roleId";
    /**
     * 角色
     */
    public static final String roleValue = "role_value";
    /**
     * 角色
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
     * 对象ID
     */
    public static final String objectId = "object_id";
    /**
     * 对象ID
     */
    public static final String objectId_alias = "objectId";
    /**
     * 对象名称
     */
    public static final String objectName = "object_name";
    /**
     * 对象名称
     */
    public static final String objectName_alias = "objectName";
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
     * 创建时间
     */
    public static final String createTimeStamp = "create_time_stamp";
    /**
     * 创建时间
     */
    public static final String createTimeStamp_alias = "createTimeStamp";
    /**
     * 修改时间
     */
    public static final String updateTimeStamp = "update_time_stamp";
    /**
     * 修改时间
     */
    public static final String updateTimeStamp_alias = "updateTimeStamp";
    /**
     * 删除时间
     */
    public static final String deleteTimeStamp = "delete_time_stamp";
    /**
     * 删除时间
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
        COLUMN_ALIAS_MAP.put(roleId, roleId_alias);
        ALIAS_COLUMN_MAP.put(roleId_alias, roleId);
        COLUMN_ALIAS_MAP.put(roleValue, roleValue_alias);
        ALIAS_COLUMN_MAP.put(roleValue_alias, roleValue);
        COLUMN_ALIAS_MAP.put(roleName, roleName_alias);
        ALIAS_COLUMN_MAP.put(roleName_alias, roleName);
        COLUMN_ALIAS_MAP.put(roleType, roleType_alias);
        ALIAS_COLUMN_MAP.put(roleType_alias, roleType);
        COLUMN_ALIAS_MAP.put(objectId, objectId_alias);
        ALIAS_COLUMN_MAP.put(objectId_alias, objectId);
        COLUMN_ALIAS_MAP.put(objectName, objectName_alias);
        ALIAS_COLUMN_MAP.put(objectName_alias, objectName);
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

    public static final class Column extends ColumnModel<AutObjectRoleModel, Column, On, Where, Sort, Group> {

        private Column() {
        }

        /**
         * 主键
         */
        public Column primaryKey() {
            this.addColumnAlias(AutObjectRoleModel.primaryKeyName, AutObjectRoleModel.primaryKeyAlias);
            return this;
        }

        /**
         * 主键
         * @param alias 别名
         */
        public Column primaryKey(String alias) {
            this.addColumnAlias(AutObjectRoleModel.primaryKeyName, alias);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Column id() {
            this.addColumnAlias(AutObjectRoleModel.id, AutObjectRoleModel.id_alias);
            return this;
        }

        /**
         * 主键ID
         * @param alias 别名
         */
        public Column id(String alias) {
            this.addColumnAlias(AutObjectRoleModel.id, alias);
            return this;
        }

        /**
         * 角色id
         */
        public Column roleId() {
            this.addColumnAlias(AutObjectRoleModel.roleId, AutObjectRoleModel.roleId_alias);
            return this;
        }

        /**
         * 角色id
         * @param alias 别名
         */
        public Column roleId(String alias) {
            this.addColumnAlias(AutObjectRoleModel.roleId, alias);
            return this;
        }

        /**
         * 角色
         */
        public Column roleValue() {
            this.addColumnAlias(AutObjectRoleModel.roleValue, AutObjectRoleModel.roleValue_alias);
            return this;
        }

        /**
         * 角色
         * @param alias 别名
         */
        public Column roleValue(String alias) {
            this.addColumnAlias(AutObjectRoleModel.roleValue, alias);
            return this;
        }

        /**
         * 角色名称
         */
        public Column roleName() {
            this.addColumnAlias(AutObjectRoleModel.roleName, AutObjectRoleModel.roleName_alias);
            return this;
        }

        /**
         * 角色名称
         * @param alias 别名
         */
        public Column roleName(String alias) {
            this.addColumnAlias(AutObjectRoleModel.roleName, alias);
            return this;
        }

        /**
         * 角色类型
         */
        public Column roleType() {
            this.addColumnAlias(AutObjectRoleModel.roleType, AutObjectRoleModel.roleType_alias);
            return this;
        }

        /**
         * 角色类型
         * @param alias 别名
         */
        public Column roleType(String alias) {
            this.addColumnAlias(AutObjectRoleModel.roleType, alias);
            return this;
        }

        /**
         * 对象ID
         */
        public Column objectId() {
            this.addColumnAlias(AutObjectRoleModel.objectId, AutObjectRoleModel.objectId_alias);
            return this;
        }

        /**
         * 对象ID
         * @param alias 别名
         */
        public Column objectId(String alias) {
            this.addColumnAlias(AutObjectRoleModel.objectId, alias);
            return this;
        }

        /**
         * 对象名称
         */
        public Column objectName() {
            this.addColumnAlias(AutObjectRoleModel.objectName, AutObjectRoleModel.objectName_alias);
            return this;
        }

        /**
         * 对象名称
         * @param alias 别名
         */
        public Column objectName(String alias) {
            this.addColumnAlias(AutObjectRoleModel.objectName, alias);
            return this;
        }

        /**
         * 状态
         */
        public Column status() {
            this.addColumnAlias(AutObjectRoleModel.status, AutObjectRoleModel.status_alias);
            return this;
        }

        /**
         * 状态
         * @param alias 别名
         */
        public Column status(String alias) {
            this.addColumnAlias(AutObjectRoleModel.status, alias);
            return this;
        }

        /**
         * 创建时间
         */
        public Column createTime() {
            this.addColumnAlias(AutObjectRoleModel.createTime, AutObjectRoleModel.createTime_alias);
            return this;
        }

        /**
         * 创建时间
         * @param alias 别名
         */
        public Column createTime(String alias) {
            this.addColumnAlias(AutObjectRoleModel.createTime, alias);
            return this;
        }

        /**
         * 修改时间
         */
        public Column updateTime() {
            this.addColumnAlias(AutObjectRoleModel.updateTime, AutObjectRoleModel.updateTime_alias);
            return this;
        }

        /**
         * 修改时间
         * @param alias 别名
         */
        public Column updateTime(String alias) {
            this.addColumnAlias(AutObjectRoleModel.updateTime, alias);
            return this;
        }

        /**
         * 删除时间
         */
        public Column deleteTime() {
            this.addColumnAlias(AutObjectRoleModel.deleteTime, AutObjectRoleModel.deleteTime_alias);
            return this;
        }

        /**
         * 删除时间
         * @param alias 别名
         */
        public Column deleteTime(String alias) {
            this.addColumnAlias(AutObjectRoleModel.deleteTime, alias);
            return this;
        }

        /**
         * 创建时间
         */
        public Column createTimeStamp() {
            this.addColumnAlias(AutObjectRoleModel.createTimeStamp, AutObjectRoleModel.createTimeStamp_alias);
            return this;
        }

        /**
         * 创建时间
         * @param alias 别名
         */
        public Column createTimeStamp(String alias) {
            this.addColumnAlias(AutObjectRoleModel.createTimeStamp, alias);
            return this;
        }

        /**
         * 修改时间
         */
        public Column updateTimeStamp() {
            this.addColumnAlias(AutObjectRoleModel.updateTimeStamp, AutObjectRoleModel.updateTimeStamp_alias);
            return this;
        }

        /**
         * 修改时间
         * @param alias 别名
         */
        public Column updateTimeStamp(String alias) {
            this.addColumnAlias(AutObjectRoleModel.updateTimeStamp, alias);
            return this;
        }

        /**
         * 删除时间
         */
        public Column deleteTimeStamp() {
            this.addColumnAlias(AutObjectRoleModel.deleteTimeStamp, AutObjectRoleModel.deleteTimeStamp_alias);
            return this;
        }

        /**
         * 删除时间
         * @param alias 别名
         */
        public Column deleteTimeStamp(String alias) {
            this.addColumnAlias(AutObjectRoleModel.deleteTimeStamp, alias);
            return this;
        }

    }

    @Override
    public On getOnModel() {
        return new On();
    }

    public static final class On extends OnModel<AutObjectRoleModel, Column, On, Where, Sort, Group> {

        private On() {
        }

        /**
         * 主键
         */
        public OnBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.onBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public OnBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> id() {
            return this.onBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.id);
        }

        /**
         * 角色id
         */
        public OnBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> roleId() {
            return this.onBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.roleId);
        }

        /**
         * 角色
         */
        public OnBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> roleValue() {
            return this.onBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.roleValue);
        }

        /**
         * 角色名称
         */
        public OnBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> roleName() {
            return this.onBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.roleName);
        }

        /**
         * 角色类型
         */
        public OnBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> roleType() {
            return this.onBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.roleType);
        }

        /**
         * 对象ID
         */
        public OnBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> objectId() {
            return this.onBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.objectId);
        }

        /**
         * 对象名称
         */
        public OnBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> objectName() {
            return this.onBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.objectName);
        }

        /**
         * 状态
         */
        public OnBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> status() {
            return this.onBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.status);
        }

        /**
         * 创建时间
         */
        public OnBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> createTime() {
            return this.onBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.createTime);
        }

        /**
         * 修改时间
         */
        public OnBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> updateTime() {
            return this.onBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.updateTime);
        }

        /**
         * 删除时间
         */
        public OnBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.onBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.deleteTime);
        }

        /**
         * 创建时间
         */
        public OnBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.onBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.createTimeStamp);
        }

        /**
         * 修改时间
         */
        public OnBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.onBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.updateTimeStamp);
        }

        /**
         * 删除时间
         */
        public OnBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.onBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.deleteTimeStamp);
        }

    }

    @Override
    public Where getWhereModel() {
        return new Where();
    }

    public static final class Where extends WhereModel<AutObjectRoleModel, Column, On, Where, Sort, Group> {

        private Where() {
        }

        /**
         * 主键
         */
        public WhereBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.whereBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public WhereBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> id() {
            return this.whereBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.id);
        }

        /**
         * 角色id
         */
        public WhereBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> roleId() {
            return this.whereBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.roleId);
        }

        /**
         * 角色
         */
        public WhereBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> roleValue() {
            return this.whereBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.roleValue);
        }

        /**
         * 角色名称
         */
        public WhereBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> roleName() {
            return this.whereBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.roleName);
        }

        /**
         * 角色类型
         */
        public WhereBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> roleType() {
            return this.whereBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.roleType);
        }

        /**
         * 对象ID
         */
        public WhereBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> objectId() {
            return this.whereBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.objectId);
        }

        /**
         * 对象名称
         */
        public WhereBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> objectName() {
            return this.whereBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.objectName);
        }

        /**
         * 状态
         */
        public WhereBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> status() {
            return this.whereBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.status);
        }

        /**
         * 创建时间
         */
        public WhereBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> createTime() {
            return this.whereBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.createTime);
        }

        /**
         * 修改时间
         */
        public WhereBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> updateTime() {
            return this.whereBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.updateTime);
        }

        /**
         * 删除时间
         */
        public WhereBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.whereBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.deleteTime);
        }

        /**
         * 创建时间
         */
        public WhereBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.whereBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.createTimeStamp);
        }

        /**
         * 修改时间
         */
        public WhereBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.whereBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.updateTimeStamp);
        }

        /**
         * 删除时间
         */
        public WhereBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.whereBuilder.handler(AutObjectRoleModel.tableName, AutObjectRoleModel.tableAlias, AutObjectRoleModel.deleteTimeStamp);
        }

    }

    @Override
    public Group getGroupModel() {
        return new Group();
    }

    public static final class Group extends GroupModel<AutObjectRoleModel, Column, On, Where, Sort, Group> {

        private Group() {
        }

        /**
         * 主键
         */
        public Group primaryKey() {
            this.addColumn(AutObjectRoleModel.primaryKeyName);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Group id() {
            this.addColumn(AutObjectRoleModel.id);
            return this;
        }

        /**
         * 角色id
         */
        public Group roleId() {
            this.addColumn(AutObjectRoleModel.roleId);
            return this;
        }

        /**
         * 角色
         */
        public Group roleValue() {
            this.addColumn(AutObjectRoleModel.roleValue);
            return this;
        }

        /**
         * 角色名称
         */
        public Group roleName() {
            this.addColumn(AutObjectRoleModel.roleName);
            return this;
        }

        /**
         * 角色类型
         */
        public Group roleType() {
            this.addColumn(AutObjectRoleModel.roleType);
            return this;
        }

        /**
         * 对象ID
         */
        public Group objectId() {
            this.addColumn(AutObjectRoleModel.objectId);
            return this;
        }

        /**
         * 对象名称
         */
        public Group objectName() {
            this.addColumn(AutObjectRoleModel.objectName);
            return this;
        }

        /**
         * 状态
         */
        public Group status() {
            this.addColumn(AutObjectRoleModel.status);
            return this;
        }

        /**
         * 创建时间
         */
        public Group createTime() {
            this.addColumn(AutObjectRoleModel.createTime);
            return this;
        }

        /**
         * 修改时间
         */
        public Group updateTime() {
            this.addColumn(AutObjectRoleModel.updateTime);
            return this;
        }

        /**
         * 删除时间
         */
        public Group deleteTime() {
            this.addColumn(AutObjectRoleModel.deleteTime);
            return this;
        }

        /**
         * 创建时间
         */
        public Group createTimeStamp() {
            this.addColumn(AutObjectRoleModel.createTimeStamp);
            return this;
        }

        /**
         * 修改时间
         */
        public Group updateTimeStamp() {
            this.addColumn(AutObjectRoleModel.updateTimeStamp);
            return this;
        }

        /**
         * 删除时间
         */
        public Group deleteTimeStamp() {
            this.addColumn(AutObjectRoleModel.deleteTimeStamp);
            return this;
        }

    }

    @Override
    public Sort getSortModel() {
        return new Sort();
    }

    public static final class Sort extends SortModel<AutObjectRoleModel, Column, On, Where, Sort, Group> {

        private Sort() {
        }

        /**
         * 主键
         */
        public SortBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.sortBuilder.handler(AutObjectRoleModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public SortBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> id() {
            return this.sortBuilder.handler(AutObjectRoleModel.id);
        }

        /**
         * 角色id
         */
        public SortBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> roleId() {
            return this.sortBuilder.handler(AutObjectRoleModel.roleId);
        }

        /**
         * 角色
         */
        public SortBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> roleValue() {
            return this.sortBuilder.handler(AutObjectRoleModel.roleValue);
        }

        /**
         * 角色名称
         */
        public SortBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> roleName() {
            return this.sortBuilder.handler(AutObjectRoleModel.roleName);
        }

        /**
         * 角色类型
         */
        public SortBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> roleType() {
            return this.sortBuilder.handler(AutObjectRoleModel.roleType);
        }

        /**
         * 对象ID
         */
        public SortBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> objectId() {
            return this.sortBuilder.handler(AutObjectRoleModel.objectId);
        }

        /**
         * 对象名称
         */
        public SortBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> objectName() {
            return this.sortBuilder.handler(AutObjectRoleModel.objectName);
        }

        /**
         * 状态
         */
        public SortBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> status() {
            return this.sortBuilder.handler(AutObjectRoleModel.status);
        }

        /**
         * 创建时间
         */
        public SortBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> createTime() {
            return this.sortBuilder.handler(AutObjectRoleModel.createTime);
        }

        /**
         * 修改时间
         */
        public SortBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> updateTime() {
            return this.sortBuilder.handler(AutObjectRoleModel.updateTime);
        }

        /**
         * 删除时间
         */
        public SortBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.sortBuilder.handler(AutObjectRoleModel.deleteTime);
        }

        /**
         * 创建时间
         */
        public SortBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.sortBuilder.handler(AutObjectRoleModel.createTimeStamp);
        }

        /**
         * 修改时间
         */
        public SortBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.sortBuilder.handler(AutObjectRoleModel.updateTimeStamp);
        }

        /**
         * 删除时间
         */
        public SortBuilder<AutObjectRoleModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.sortBuilder.handler(AutObjectRoleModel.deleteTimeStamp);
        }

    }

}