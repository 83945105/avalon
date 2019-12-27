package pub.avalonframework.cloud.gar.model;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("all")
public final class AutRoleResourceModel implements Model<AutRoleResourceModel, AutRoleResourceModel.Column, AutRoleResourceModel.On, AutRoleResourceModel.Where, AutRoleResourceModel.Sort, AutRoleResourceModel.Group> {

    /**
     * 表名
     */
    public static final String tableName = "gar_aut_role_resource";
    /**
     * 表别名
     */
    public static final String tableAlias = "AutRoleResource";

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
     * 资源id
     */
    public static final String resourceId = "resource_id";
    /**
     * 资源id
     */
    public static final String resourceId_alias = "resourceId";
    /**
     * 资源批次号
     */
    public static final String resourceBatchId = "resource_batch_id";
    /**
     * 资源批次号
     */
    public static final String resourceBatchId_alias = "resourceBatchId";
    /**
     * 资源名称
     */
    public static final String resourceName = "resource_name";
    /**
     * 资源名称
     */
    public static final String resourceName_alias = "resourceName";
    /**
     * 资源地址
     */
    public static final String resourceUrl = "resource_url";
    /**
     * 资源地址
     */
    public static final String resourceUrl_alias = "resourceUrl";
    /**
     * 资源权限
     */
    public static final String resourcePermission = "resource_permission";
    /**
     * 资源权限
     */
    public static final String resourcePermission_alias = "resourcePermission";
    /**
     * 资源类型
     */
    public static final String resourceType = "resource_type";
    /**
     * 资源类型
     */
    public static final String resourceType_alias = "resourceType";
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
        COLUMN_ALIAS_MAP.put(roleId, roleId_alias);
        ALIAS_COLUMN_MAP.put(roleId_alias, roleId);
        COLUMN_ALIAS_MAP.put(roleValue, roleValue_alias);
        ALIAS_COLUMN_MAP.put(roleValue_alias, roleValue);
        COLUMN_ALIAS_MAP.put(roleName, roleName_alias);
        ALIAS_COLUMN_MAP.put(roleName_alias, roleName);
        COLUMN_ALIAS_MAP.put(roleType, roleType_alias);
        ALIAS_COLUMN_MAP.put(roleType_alias, roleType);
        COLUMN_ALIAS_MAP.put(resourceId, resourceId_alias);
        ALIAS_COLUMN_MAP.put(resourceId_alias, resourceId);
        COLUMN_ALIAS_MAP.put(resourceBatchId, resourceBatchId_alias);
        ALIAS_COLUMN_MAP.put(resourceBatchId_alias, resourceBatchId);
        COLUMN_ALIAS_MAP.put(resourceName, resourceName_alias);
        ALIAS_COLUMN_MAP.put(resourceName_alias, resourceName);
        COLUMN_ALIAS_MAP.put(resourceUrl, resourceUrl_alias);
        ALIAS_COLUMN_MAP.put(resourceUrl_alias, resourceUrl);
        COLUMN_ALIAS_MAP.put(resourcePermission, resourcePermission_alias);
        ALIAS_COLUMN_MAP.put(resourcePermission_alias, resourcePermission);
        COLUMN_ALIAS_MAP.put(resourceType, resourceType_alias);
        ALIAS_COLUMN_MAP.put(resourceType_alias, resourceType);
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

    public static final class Column extends ColumnModel<AutRoleResourceModel, Column, On, Where, Sort, Group> {

        private Column() {
        }

        /**
         * 主键
         */
        public Column primaryKey() {
            this.addColumnAlias(AutRoleResourceModel.primaryKeyName, AutRoleResourceModel.primaryKeyAlias);
            return this;
        }

        /**
         * 主键
         * @param alias 别名
         */
        public Column primaryKey(String alias) {
            this.addColumnAlias(AutRoleResourceModel.primaryKeyName, alias);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Column id() {
            this.addColumnAlias(AutRoleResourceModel.id, AutRoleResourceModel.id_alias);
            return this;
        }

        /**
         * 主键ID
         * @param alias 别名
         */
        public Column id(String alias) {
            this.addColumnAlias(AutRoleResourceModel.id, alias);
            return this;
        }

        /**
         * 角色id
         */
        public Column roleId() {
            this.addColumnAlias(AutRoleResourceModel.roleId, AutRoleResourceModel.roleId_alias);
            return this;
        }

        /**
         * 角色id
         * @param alias 别名
         */
        public Column roleId(String alias) {
            this.addColumnAlias(AutRoleResourceModel.roleId, alias);
            return this;
        }

        /**
         * 角色值
         */
        public Column roleValue() {
            this.addColumnAlias(AutRoleResourceModel.roleValue, AutRoleResourceModel.roleValue_alias);
            return this;
        }

        /**
         * 角色值
         * @param alias 别名
         */
        public Column roleValue(String alias) {
            this.addColumnAlias(AutRoleResourceModel.roleValue, alias);
            return this;
        }

        /**
         * 角色名称
         */
        public Column roleName() {
            this.addColumnAlias(AutRoleResourceModel.roleName, AutRoleResourceModel.roleName_alias);
            return this;
        }

        /**
         * 角色名称
         * @param alias 别名
         */
        public Column roleName(String alias) {
            this.addColumnAlias(AutRoleResourceModel.roleName, alias);
            return this;
        }

        /**
         * 角色类型
         */
        public Column roleType() {
            this.addColumnAlias(AutRoleResourceModel.roleType, AutRoleResourceModel.roleType_alias);
            return this;
        }

        /**
         * 角色类型
         * @param alias 别名
         */
        public Column roleType(String alias) {
            this.addColumnAlias(AutRoleResourceModel.roleType, alias);
            return this;
        }

        /**
         * 资源id
         */
        public Column resourceId() {
            this.addColumnAlias(AutRoleResourceModel.resourceId, AutRoleResourceModel.resourceId_alias);
            return this;
        }

        /**
         * 资源id
         * @param alias 别名
         */
        public Column resourceId(String alias) {
            this.addColumnAlias(AutRoleResourceModel.resourceId, alias);
            return this;
        }

        /**
         * 资源批次号
         */
        public Column resourceBatchId() {
            this.addColumnAlias(AutRoleResourceModel.resourceBatchId, AutRoleResourceModel.resourceBatchId_alias);
            return this;
        }

        /**
         * 资源批次号
         * @param alias 别名
         */
        public Column resourceBatchId(String alias) {
            this.addColumnAlias(AutRoleResourceModel.resourceBatchId, alias);
            return this;
        }

        /**
         * 资源名称
         */
        public Column resourceName() {
            this.addColumnAlias(AutRoleResourceModel.resourceName, AutRoleResourceModel.resourceName_alias);
            return this;
        }

        /**
         * 资源名称
         * @param alias 别名
         */
        public Column resourceName(String alias) {
            this.addColumnAlias(AutRoleResourceModel.resourceName, alias);
            return this;
        }

        /**
         * 资源地址
         */
        public Column resourceUrl() {
            this.addColumnAlias(AutRoleResourceModel.resourceUrl, AutRoleResourceModel.resourceUrl_alias);
            return this;
        }

        /**
         * 资源地址
         * @param alias 别名
         */
        public Column resourceUrl(String alias) {
            this.addColumnAlias(AutRoleResourceModel.resourceUrl, alias);
            return this;
        }

        /**
         * 资源权限
         */
        public Column resourcePermission() {
            this.addColumnAlias(AutRoleResourceModel.resourcePermission, AutRoleResourceModel.resourcePermission_alias);
            return this;
        }

        /**
         * 资源权限
         * @param alias 别名
         */
        public Column resourcePermission(String alias) {
            this.addColumnAlias(AutRoleResourceModel.resourcePermission, alias);
            return this;
        }

        /**
         * 资源类型
         */
        public Column resourceType() {
            this.addColumnAlias(AutRoleResourceModel.resourceType, AutRoleResourceModel.resourceType_alias);
            return this;
        }

        /**
         * 资源类型
         * @param alias 别名
         */
        public Column resourceType(String alias) {
            this.addColumnAlias(AutRoleResourceModel.resourceType, alias);
            return this;
        }

        /**
         * 状态
         */
        public Column status() {
            this.addColumnAlias(AutRoleResourceModel.status, AutRoleResourceModel.status_alias);
            return this;
        }

        /**
         * 状态
         * @param alias 别名
         */
        public Column status(String alias) {
            this.addColumnAlias(AutRoleResourceModel.status, alias);
            return this;
        }

        /**
         * 创建时间
         */
        public Column createTime() {
            this.addColumnAlias(AutRoleResourceModel.createTime, AutRoleResourceModel.createTime_alias);
            return this;
        }

        /**
         * 创建时间
         * @param alias 别名
         */
        public Column createTime(String alias) {
            this.addColumnAlias(AutRoleResourceModel.createTime, alias);
            return this;
        }

        /**
         * 修改时间
         */
        public Column updateTime() {
            this.addColumnAlias(AutRoleResourceModel.updateTime, AutRoleResourceModel.updateTime_alias);
            return this;
        }

        /**
         * 修改时间
         * @param alias 别名
         */
        public Column updateTime(String alias) {
            this.addColumnAlias(AutRoleResourceModel.updateTime, alias);
            return this;
        }

        /**
         * 删除时间
         */
        public Column deleteTime() {
            this.addColumnAlias(AutRoleResourceModel.deleteTime, AutRoleResourceModel.deleteTime_alias);
            return this;
        }

        /**
         * 删除时间
         * @param alias 别名
         */
        public Column deleteTime(String alias) {
            this.addColumnAlias(AutRoleResourceModel.deleteTime, alias);
            return this;
        }

        /**
         * 创建时间
         */
        public Column createTimeStamp() {
            this.addColumnAlias(AutRoleResourceModel.createTimeStamp, AutRoleResourceModel.createTimeStamp_alias);
            return this;
        }

        /**
         * 创建时间
         * @param alias 别名
         */
        public Column createTimeStamp(String alias) {
            this.addColumnAlias(AutRoleResourceModel.createTimeStamp, alias);
            return this;
        }

        /**
         * 修改时间戳
         */
        public Column updateTimeStamp() {
            this.addColumnAlias(AutRoleResourceModel.updateTimeStamp, AutRoleResourceModel.updateTimeStamp_alias);
            return this;
        }

        /**
         * 修改时间戳
         * @param alias 别名
         */
        public Column updateTimeStamp(String alias) {
            this.addColumnAlias(AutRoleResourceModel.updateTimeStamp, alias);
            return this;
        }

        /**
         * 删除时间戳
         */
        public Column deleteTimeStamp() {
            this.addColumnAlias(AutRoleResourceModel.deleteTimeStamp, AutRoleResourceModel.deleteTimeStamp_alias);
            return this;
        }

        /**
         * 删除时间戳
         * @param alias 别名
         */
        public Column deleteTimeStamp(String alias) {
            this.addColumnAlias(AutRoleResourceModel.deleteTimeStamp, alias);
            return this;
        }

    }

    @Override
    public On getOnModel() {
        return new On();
    }

    public static final class On extends OnModel<AutRoleResourceModel, Column, On, Where, Sort, Group> {

        private On() {
        }

        /**
         * 主键
         */
        public OnBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.onBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public OnBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> id() {
            return this.onBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.id);
        }

        /**
         * 角色id
         */
        public OnBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> roleId() {
            return this.onBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.roleId);
        }

        /**
         * 角色值
         */
        public OnBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> roleValue() {
            return this.onBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.roleValue);
        }

        /**
         * 角色名称
         */
        public OnBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> roleName() {
            return this.onBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.roleName);
        }

        /**
         * 角色类型
         */
        public OnBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> roleType() {
            return this.onBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.roleType);
        }

        /**
         * 资源id
         */
        public OnBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> resourceId() {
            return this.onBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.resourceId);
        }

        /**
         * 资源批次号
         */
        public OnBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> resourceBatchId() {
            return this.onBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.resourceBatchId);
        }

        /**
         * 资源名称
         */
        public OnBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> resourceName() {
            return this.onBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.resourceName);
        }

        /**
         * 资源地址
         */
        public OnBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> resourceUrl() {
            return this.onBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.resourceUrl);
        }

        /**
         * 资源权限
         */
        public OnBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> resourcePermission() {
            return this.onBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.resourcePermission);
        }

        /**
         * 资源类型
         */
        public OnBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> resourceType() {
            return this.onBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.resourceType);
        }

        /**
         * 状态
         */
        public OnBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> status() {
            return this.onBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.status);
        }

        /**
         * 创建时间
         */
        public OnBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> createTime() {
            return this.onBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.createTime);
        }

        /**
         * 修改时间
         */
        public OnBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> updateTime() {
            return this.onBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.updateTime);
        }

        /**
         * 删除时间
         */
        public OnBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.onBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.deleteTime);
        }

        /**
         * 创建时间
         */
        public OnBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.onBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public OnBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.onBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public OnBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.onBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.deleteTimeStamp);
        }

    }

    @Override
    public Where getWhereModel() {
        return new Where();
    }

    public static final class Where extends WhereModel<AutRoleResourceModel, Column, On, Where, Sort, Group> {

        private Where() {
        }

        /**
         * 主键
         */
        public WhereBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.whereBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public WhereBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> id() {
            return this.whereBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.id);
        }

        /**
         * 角色id
         */
        public WhereBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> roleId() {
            return this.whereBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.roleId);
        }

        /**
         * 角色值
         */
        public WhereBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> roleValue() {
            return this.whereBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.roleValue);
        }

        /**
         * 角色名称
         */
        public WhereBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> roleName() {
            return this.whereBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.roleName);
        }

        /**
         * 角色类型
         */
        public WhereBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> roleType() {
            return this.whereBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.roleType);
        }

        /**
         * 资源id
         */
        public WhereBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> resourceId() {
            return this.whereBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.resourceId);
        }

        /**
         * 资源批次号
         */
        public WhereBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> resourceBatchId() {
            return this.whereBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.resourceBatchId);
        }

        /**
         * 资源名称
         */
        public WhereBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> resourceName() {
            return this.whereBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.resourceName);
        }

        /**
         * 资源地址
         */
        public WhereBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> resourceUrl() {
            return this.whereBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.resourceUrl);
        }

        /**
         * 资源权限
         */
        public WhereBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> resourcePermission() {
            return this.whereBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.resourcePermission);
        }

        /**
         * 资源类型
         */
        public WhereBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> resourceType() {
            return this.whereBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.resourceType);
        }

        /**
         * 状态
         */
        public WhereBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> status() {
            return this.whereBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.status);
        }

        /**
         * 创建时间
         */
        public WhereBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> createTime() {
            return this.whereBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.createTime);
        }

        /**
         * 修改时间
         */
        public WhereBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> updateTime() {
            return this.whereBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.updateTime);
        }

        /**
         * 删除时间
         */
        public WhereBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.whereBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.deleteTime);
        }

        /**
         * 创建时间
         */
        public WhereBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.whereBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public WhereBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.whereBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public WhereBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.whereBuilder.handler(AutRoleResourceModel.tableName, AutRoleResourceModel.tableAlias, AutRoleResourceModel.deleteTimeStamp);
        }

    }

    @Override
    public Group getGroupModel() {
        return new Group();
    }

    public static final class Group extends GroupModel<AutRoleResourceModel, Column, On, Where, Sort, Group> {

        private Group() {
        }

        /**
         * 主键
         */
        public Group primaryKey() {
            this.addColumn(AutRoleResourceModel.primaryKeyName);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Group id() {
            this.addColumn(AutRoleResourceModel.id);
            return this;
        }

        /**
         * 角色id
         */
        public Group roleId() {
            this.addColumn(AutRoleResourceModel.roleId);
            return this;
        }

        /**
         * 角色值
         */
        public Group roleValue() {
            this.addColumn(AutRoleResourceModel.roleValue);
            return this;
        }

        /**
         * 角色名称
         */
        public Group roleName() {
            this.addColumn(AutRoleResourceModel.roleName);
            return this;
        }

        /**
         * 角色类型
         */
        public Group roleType() {
            this.addColumn(AutRoleResourceModel.roleType);
            return this;
        }

        /**
         * 资源id
         */
        public Group resourceId() {
            this.addColumn(AutRoleResourceModel.resourceId);
            return this;
        }

        /**
         * 资源批次号
         */
        public Group resourceBatchId() {
            this.addColumn(AutRoleResourceModel.resourceBatchId);
            return this;
        }

        /**
         * 资源名称
         */
        public Group resourceName() {
            this.addColumn(AutRoleResourceModel.resourceName);
            return this;
        }

        /**
         * 资源地址
         */
        public Group resourceUrl() {
            this.addColumn(AutRoleResourceModel.resourceUrl);
            return this;
        }

        /**
         * 资源权限
         */
        public Group resourcePermission() {
            this.addColumn(AutRoleResourceModel.resourcePermission);
            return this;
        }

        /**
         * 资源类型
         */
        public Group resourceType() {
            this.addColumn(AutRoleResourceModel.resourceType);
            return this;
        }

        /**
         * 状态
         */
        public Group status() {
            this.addColumn(AutRoleResourceModel.status);
            return this;
        }

        /**
         * 创建时间
         */
        public Group createTime() {
            this.addColumn(AutRoleResourceModel.createTime);
            return this;
        }

        /**
         * 修改时间
         */
        public Group updateTime() {
            this.addColumn(AutRoleResourceModel.updateTime);
            return this;
        }

        /**
         * 删除时间
         */
        public Group deleteTime() {
            this.addColumn(AutRoleResourceModel.deleteTime);
            return this;
        }

        /**
         * 创建时间
         */
        public Group createTimeStamp() {
            this.addColumn(AutRoleResourceModel.createTimeStamp);
            return this;
        }

        /**
         * 修改时间戳
         */
        public Group updateTimeStamp() {
            this.addColumn(AutRoleResourceModel.updateTimeStamp);
            return this;
        }

        /**
         * 删除时间戳
         */
        public Group deleteTimeStamp() {
            this.addColumn(AutRoleResourceModel.deleteTimeStamp);
            return this;
        }

    }

    @Override
    public Sort getSortModel() {
        return new Sort();
    }

    public static final class Sort extends SortModel<AutRoleResourceModel, Column, On, Where, Sort, Group> {

        private Sort() {
        }

        /**
         * 主键
         */
        public SortBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.sortBuilder.handler(AutRoleResourceModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public SortBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> id() {
            return this.sortBuilder.handler(AutRoleResourceModel.id);
        }

        /**
         * 角色id
         */
        public SortBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> roleId() {
            return this.sortBuilder.handler(AutRoleResourceModel.roleId);
        }

        /**
         * 角色值
         */
        public SortBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> roleValue() {
            return this.sortBuilder.handler(AutRoleResourceModel.roleValue);
        }

        /**
         * 角色名称
         */
        public SortBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> roleName() {
            return this.sortBuilder.handler(AutRoleResourceModel.roleName);
        }

        /**
         * 角色类型
         */
        public SortBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> roleType() {
            return this.sortBuilder.handler(AutRoleResourceModel.roleType);
        }

        /**
         * 资源id
         */
        public SortBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> resourceId() {
            return this.sortBuilder.handler(AutRoleResourceModel.resourceId);
        }

        /**
         * 资源批次号
         */
        public SortBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> resourceBatchId() {
            return this.sortBuilder.handler(AutRoleResourceModel.resourceBatchId);
        }

        /**
         * 资源名称
         */
        public SortBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> resourceName() {
            return this.sortBuilder.handler(AutRoleResourceModel.resourceName);
        }

        /**
         * 资源地址
         */
        public SortBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> resourceUrl() {
            return this.sortBuilder.handler(AutRoleResourceModel.resourceUrl);
        }

        /**
         * 资源权限
         */
        public SortBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> resourcePermission() {
            return this.sortBuilder.handler(AutRoleResourceModel.resourcePermission);
        }

        /**
         * 资源类型
         */
        public SortBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> resourceType() {
            return this.sortBuilder.handler(AutRoleResourceModel.resourceType);
        }

        /**
         * 状态
         */
        public SortBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> status() {
            return this.sortBuilder.handler(AutRoleResourceModel.status);
        }

        /**
         * 创建时间
         */
        public SortBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> createTime() {
            return this.sortBuilder.handler(AutRoleResourceModel.createTime);
        }

        /**
         * 修改时间
         */
        public SortBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> updateTime() {
            return this.sortBuilder.handler(AutRoleResourceModel.updateTime);
        }

        /**
         * 删除时间
         */
        public SortBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.sortBuilder.handler(AutRoleResourceModel.deleteTime);
        }

        /**
         * 创建时间
         */
        public SortBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.sortBuilder.handler(AutRoleResourceModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public SortBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.sortBuilder.handler(AutRoleResourceModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public SortBuilder<AutRoleResourceModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.sortBuilder.handler(AutRoleResourceModel.deleteTimeStamp);
        }

    }

}