package pub.avalonframework.cloud.gar.model;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("all")
public final class AutRoleMenuModel implements Model<AutRoleMenuModel, AutRoleMenuModel.Column, AutRoleMenuModel.On, AutRoleMenuModel.Where, AutRoleMenuModel.Sort, AutRoleMenuModel.Group> {

    /**
     * 表名
     */
    public static final String tableName = "gar_aut_role_menu";
    /**
     * 表别名
     */
    public static final String tableAlias = "AutRoleMenu";

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
     * 所属菜单组ID
     */
    public static final String menuGroupId = "menu_group_id";
    /**
     * 所属菜单组ID
     */
    public static final String menuGroupId_alias = "menuGroupId";
    /**
     * 菜单ID
     */
    public static final String menuId = "menu_id";
    /**
     * 菜单ID
     */
    public static final String menuId_alias = "menuId";
    /**
     * 菜单名称
     */
    public static final String menuName = "menu_name";
    /**
     * 菜单名称
     */
    public static final String menuName_alias = "menuName";
    /**
     * 菜单唯一标识符
     */
    public static final String menuValue = "menu_value";
    /**
     * 菜单唯一标识符
     */
    public static final String menuValue_alias = "menuValue";
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
        COLUMN_ALIAS_MAP.put(menuGroupId, menuGroupId_alias);
        ALIAS_COLUMN_MAP.put(menuGroupId_alias, menuGroupId);
        COLUMN_ALIAS_MAP.put(menuId, menuId_alias);
        ALIAS_COLUMN_MAP.put(menuId_alias, menuId);
        COLUMN_ALIAS_MAP.put(menuName, menuName_alias);
        ALIAS_COLUMN_MAP.put(menuName_alias, menuName);
        COLUMN_ALIAS_MAP.put(menuValue, menuValue_alias);
        ALIAS_COLUMN_MAP.put(menuValue_alias, menuValue);
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

    public static final class Column extends ColumnModel<AutRoleMenuModel, Column, On, Where, Sort, Group> {

        private Column() {
        }

        /**
         * 主键
         */
        public Column primaryKey() {
            this.addColumnAlias(AutRoleMenuModel.primaryKeyName, AutRoleMenuModel.primaryKeyAlias);
            return this;
        }

        /**
         * 主键
         * @param alias 别名
         */
        public Column primaryKey(String alias) {
            this.addColumnAlias(AutRoleMenuModel.primaryKeyName, alias);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Column id() {
            this.addColumnAlias(AutRoleMenuModel.id, AutRoleMenuModel.id_alias);
            return this;
        }

        /**
         * 主键ID
         * @param alias 别名
         */
        public Column id(String alias) {
            this.addColumnAlias(AutRoleMenuModel.id, alias);
            return this;
        }

        /**
         * 所属模块ID
         */
        public Column moduleId() {
            this.addColumnAlias(AutRoleMenuModel.moduleId, AutRoleMenuModel.moduleId_alias);
            return this;
        }

        /**
         * 所属模块ID
         * @param alias 别名
         */
        public Column moduleId(String alias) {
            this.addColumnAlias(AutRoleMenuModel.moduleId, alias);
            return this;
        }

        /**
         * 角色id
         */
        public Column roleId() {
            this.addColumnAlias(AutRoleMenuModel.roleId, AutRoleMenuModel.roleId_alias);
            return this;
        }

        /**
         * 角色id
         * @param alias 别名
         */
        public Column roleId(String alias) {
            this.addColumnAlias(AutRoleMenuModel.roleId, alias);
            return this;
        }

        /**
         * 角色值
         */
        public Column roleValue() {
            this.addColumnAlias(AutRoleMenuModel.roleValue, AutRoleMenuModel.roleValue_alias);
            return this;
        }

        /**
         * 角色值
         * @param alias 别名
         */
        public Column roleValue(String alias) {
            this.addColumnAlias(AutRoleMenuModel.roleValue, alias);
            return this;
        }

        /**
         * 角色名称
         */
        public Column roleName() {
            this.addColumnAlias(AutRoleMenuModel.roleName, AutRoleMenuModel.roleName_alias);
            return this;
        }

        /**
         * 角色名称
         * @param alias 别名
         */
        public Column roleName(String alias) {
            this.addColumnAlias(AutRoleMenuModel.roleName, alias);
            return this;
        }

        /**
         * 角色类型
         */
        public Column roleType() {
            this.addColumnAlias(AutRoleMenuModel.roleType, AutRoleMenuModel.roleType_alias);
            return this;
        }

        /**
         * 角色类型
         * @param alias 别名
         */
        public Column roleType(String alias) {
            this.addColumnAlias(AutRoleMenuModel.roleType, alias);
            return this;
        }

        /**
         * 所属菜单组ID
         */
        public Column menuGroupId() {
            this.addColumnAlias(AutRoleMenuModel.menuGroupId, AutRoleMenuModel.menuGroupId_alias);
            return this;
        }

        /**
         * 所属菜单组ID
         * @param alias 别名
         */
        public Column menuGroupId(String alias) {
            this.addColumnAlias(AutRoleMenuModel.menuGroupId, alias);
            return this;
        }

        /**
         * 菜单ID
         */
        public Column menuId() {
            this.addColumnAlias(AutRoleMenuModel.menuId, AutRoleMenuModel.menuId_alias);
            return this;
        }

        /**
         * 菜单ID
         * @param alias 别名
         */
        public Column menuId(String alias) {
            this.addColumnAlias(AutRoleMenuModel.menuId, alias);
            return this;
        }

        /**
         * 菜单名称
         */
        public Column menuName() {
            this.addColumnAlias(AutRoleMenuModel.menuName, AutRoleMenuModel.menuName_alias);
            return this;
        }

        /**
         * 菜单名称
         * @param alias 别名
         */
        public Column menuName(String alias) {
            this.addColumnAlias(AutRoleMenuModel.menuName, alias);
            return this;
        }

        /**
         * 菜单唯一标识符
         */
        public Column menuValue() {
            this.addColumnAlias(AutRoleMenuModel.menuValue, AutRoleMenuModel.menuValue_alias);
            return this;
        }

        /**
         * 菜单唯一标识符
         * @param alias 别名
         */
        public Column menuValue(String alias) {
            this.addColumnAlias(AutRoleMenuModel.menuValue, alias);
            return this;
        }

        /**
         * 状态
         */
        public Column status() {
            this.addColumnAlias(AutRoleMenuModel.status, AutRoleMenuModel.status_alias);
            return this;
        }

        /**
         * 状态
         * @param alias 别名
         */
        public Column status(String alias) {
            this.addColumnAlias(AutRoleMenuModel.status, alias);
            return this;
        }

        /**
         * 创建时间
         */
        public Column createTime() {
            this.addColumnAlias(AutRoleMenuModel.createTime, AutRoleMenuModel.createTime_alias);
            return this;
        }

        /**
         * 创建时间
         * @param alias 别名
         */
        public Column createTime(String alias) {
            this.addColumnAlias(AutRoleMenuModel.createTime, alias);
            return this;
        }

        /**
         * 修改时间
         */
        public Column updateTime() {
            this.addColumnAlias(AutRoleMenuModel.updateTime, AutRoleMenuModel.updateTime_alias);
            return this;
        }

        /**
         * 修改时间
         * @param alias 别名
         */
        public Column updateTime(String alias) {
            this.addColumnAlias(AutRoleMenuModel.updateTime, alias);
            return this;
        }

        /**
         * 删除时间
         */
        public Column deleteTime() {
            this.addColumnAlias(AutRoleMenuModel.deleteTime, AutRoleMenuModel.deleteTime_alias);
            return this;
        }

        /**
         * 删除时间
         * @param alias 别名
         */
        public Column deleteTime(String alias) {
            this.addColumnAlias(AutRoleMenuModel.deleteTime, alias);
            return this;
        }

        /**
         * 创建时间戳
         */
        public Column createTimeStamp() {
            this.addColumnAlias(AutRoleMenuModel.createTimeStamp, AutRoleMenuModel.createTimeStamp_alias);
            return this;
        }

        /**
         * 创建时间戳
         * @param alias 别名
         */
        public Column createTimeStamp(String alias) {
            this.addColumnAlias(AutRoleMenuModel.createTimeStamp, alias);
            return this;
        }

        /**
         * 修改时间戳
         */
        public Column updateTimeStamp() {
            this.addColumnAlias(AutRoleMenuModel.updateTimeStamp, AutRoleMenuModel.updateTimeStamp_alias);
            return this;
        }

        /**
         * 修改时间戳
         * @param alias 别名
         */
        public Column updateTimeStamp(String alias) {
            this.addColumnAlias(AutRoleMenuModel.updateTimeStamp, alias);
            return this;
        }

        /**
         * 删除时间戳
         */
        public Column deleteTimeStamp() {
            this.addColumnAlias(AutRoleMenuModel.deleteTimeStamp, AutRoleMenuModel.deleteTimeStamp_alias);
            return this;
        }

        /**
         * 删除时间戳
         * @param alias 别名
         */
        public Column deleteTimeStamp(String alias) {
            this.addColumnAlias(AutRoleMenuModel.deleteTimeStamp, alias);
            return this;
        }

    }

    @Override
    public On getOnModel() {
        return new On();
    }

    public static final class On extends OnModel<AutRoleMenuModel, Column, On, Where, Sort, Group> {

        private On() {
        }

        /**
         * 主键
         */
        public OnBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.onBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public OnBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> id() {
            return this.onBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.id);
        }

        /**
         * 所属模块ID
         */
        public OnBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> moduleId() {
            return this.onBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.moduleId);
        }

        /**
         * 角色id
         */
        public OnBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> roleId() {
            return this.onBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.roleId);
        }

        /**
         * 角色值
         */
        public OnBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> roleValue() {
            return this.onBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.roleValue);
        }

        /**
         * 角色名称
         */
        public OnBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> roleName() {
            return this.onBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.roleName);
        }

        /**
         * 角色类型
         */
        public OnBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> roleType() {
            return this.onBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.roleType);
        }

        /**
         * 所属菜单组ID
         */
        public OnBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> menuGroupId() {
            return this.onBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.menuGroupId);
        }

        /**
         * 菜单ID
         */
        public OnBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> menuId() {
            return this.onBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.menuId);
        }

        /**
         * 菜单名称
         */
        public OnBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> menuName() {
            return this.onBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.menuName);
        }

        /**
         * 菜单唯一标识符
         */
        public OnBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> menuValue() {
            return this.onBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.menuValue);
        }

        /**
         * 状态
         */
        public OnBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> status() {
            return this.onBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.status);
        }

        /**
         * 创建时间
         */
        public OnBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> createTime() {
            return this.onBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.createTime);
        }

        /**
         * 修改时间
         */
        public OnBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> updateTime() {
            return this.onBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.updateTime);
        }

        /**
         * 删除时间
         */
        public OnBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.onBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public OnBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.onBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public OnBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.onBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public OnBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.onBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.deleteTimeStamp);
        }

    }

    @Override
    public Where getWhereModel() {
        return new Where();
    }

    public static final class Where extends WhereModel<AutRoleMenuModel, Column, On, Where, Sort, Group> {

        private Where() {
        }

        /**
         * 主键
         */
        public WhereBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.whereBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public WhereBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> id() {
            return this.whereBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.id);
        }

        /**
         * 所属模块ID
         */
        public WhereBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> moduleId() {
            return this.whereBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.moduleId);
        }

        /**
         * 角色id
         */
        public WhereBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> roleId() {
            return this.whereBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.roleId);
        }

        /**
         * 角色值
         */
        public WhereBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> roleValue() {
            return this.whereBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.roleValue);
        }

        /**
         * 角色名称
         */
        public WhereBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> roleName() {
            return this.whereBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.roleName);
        }

        /**
         * 角色类型
         */
        public WhereBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> roleType() {
            return this.whereBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.roleType);
        }

        /**
         * 所属菜单组ID
         */
        public WhereBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> menuGroupId() {
            return this.whereBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.menuGroupId);
        }

        /**
         * 菜单ID
         */
        public WhereBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> menuId() {
            return this.whereBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.menuId);
        }

        /**
         * 菜单名称
         */
        public WhereBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> menuName() {
            return this.whereBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.menuName);
        }

        /**
         * 菜单唯一标识符
         */
        public WhereBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> menuValue() {
            return this.whereBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.menuValue);
        }

        /**
         * 状态
         */
        public WhereBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> status() {
            return this.whereBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.status);
        }

        /**
         * 创建时间
         */
        public WhereBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> createTime() {
            return this.whereBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.createTime);
        }

        /**
         * 修改时间
         */
        public WhereBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> updateTime() {
            return this.whereBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.updateTime);
        }

        /**
         * 删除时间
         */
        public WhereBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.whereBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public WhereBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.whereBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public WhereBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.whereBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public WhereBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.whereBuilder.handler(AutRoleMenuModel.tableName, AutRoleMenuModel.tableAlias, AutRoleMenuModel.deleteTimeStamp);
        }

    }

    @Override
    public Group getGroupModel() {
        return new Group();
    }

    public static final class Group extends GroupModel<AutRoleMenuModel, Column, On, Where, Sort, Group> {

        private Group() {
        }

        /**
         * 主键
         */
        public Group primaryKey() {
            this.addColumn(AutRoleMenuModel.primaryKeyName);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Group id() {
            this.addColumn(AutRoleMenuModel.id);
            return this;
        }

        /**
         * 所属模块ID
         */
        public Group moduleId() {
            this.addColumn(AutRoleMenuModel.moduleId);
            return this;
        }

        /**
         * 角色id
         */
        public Group roleId() {
            this.addColumn(AutRoleMenuModel.roleId);
            return this;
        }

        /**
         * 角色值
         */
        public Group roleValue() {
            this.addColumn(AutRoleMenuModel.roleValue);
            return this;
        }

        /**
         * 角色名称
         */
        public Group roleName() {
            this.addColumn(AutRoleMenuModel.roleName);
            return this;
        }

        /**
         * 角色类型
         */
        public Group roleType() {
            this.addColumn(AutRoleMenuModel.roleType);
            return this;
        }

        /**
         * 所属菜单组ID
         */
        public Group menuGroupId() {
            this.addColumn(AutRoleMenuModel.menuGroupId);
            return this;
        }

        /**
         * 菜单ID
         */
        public Group menuId() {
            this.addColumn(AutRoleMenuModel.menuId);
            return this;
        }

        /**
         * 菜单名称
         */
        public Group menuName() {
            this.addColumn(AutRoleMenuModel.menuName);
            return this;
        }

        /**
         * 菜单唯一标识符
         */
        public Group menuValue() {
            this.addColumn(AutRoleMenuModel.menuValue);
            return this;
        }

        /**
         * 状态
         */
        public Group status() {
            this.addColumn(AutRoleMenuModel.status);
            return this;
        }

        /**
         * 创建时间
         */
        public Group createTime() {
            this.addColumn(AutRoleMenuModel.createTime);
            return this;
        }

        /**
         * 修改时间
         */
        public Group updateTime() {
            this.addColumn(AutRoleMenuModel.updateTime);
            return this;
        }

        /**
         * 删除时间
         */
        public Group deleteTime() {
            this.addColumn(AutRoleMenuModel.deleteTime);
            return this;
        }

        /**
         * 创建时间戳
         */
        public Group createTimeStamp() {
            this.addColumn(AutRoleMenuModel.createTimeStamp);
            return this;
        }

        /**
         * 修改时间戳
         */
        public Group updateTimeStamp() {
            this.addColumn(AutRoleMenuModel.updateTimeStamp);
            return this;
        }

        /**
         * 删除时间戳
         */
        public Group deleteTimeStamp() {
            this.addColumn(AutRoleMenuModel.deleteTimeStamp);
            return this;
        }

    }

    @Override
    public Sort getSortModel() {
        return new Sort();
    }

    public static final class Sort extends SortModel<AutRoleMenuModel, Column, On, Where, Sort, Group> {

        private Sort() {
        }

        /**
         * 主键
         */
        public SortBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.sortBuilder.handler(AutRoleMenuModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public SortBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> id() {
            return this.sortBuilder.handler(AutRoleMenuModel.id);
        }

        /**
         * 所属模块ID
         */
        public SortBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> moduleId() {
            return this.sortBuilder.handler(AutRoleMenuModel.moduleId);
        }

        /**
         * 角色id
         */
        public SortBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> roleId() {
            return this.sortBuilder.handler(AutRoleMenuModel.roleId);
        }

        /**
         * 角色值
         */
        public SortBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> roleValue() {
            return this.sortBuilder.handler(AutRoleMenuModel.roleValue);
        }

        /**
         * 角色名称
         */
        public SortBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> roleName() {
            return this.sortBuilder.handler(AutRoleMenuModel.roleName);
        }

        /**
         * 角色类型
         */
        public SortBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> roleType() {
            return this.sortBuilder.handler(AutRoleMenuModel.roleType);
        }

        /**
         * 所属菜单组ID
         */
        public SortBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> menuGroupId() {
            return this.sortBuilder.handler(AutRoleMenuModel.menuGroupId);
        }

        /**
         * 菜单ID
         */
        public SortBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> menuId() {
            return this.sortBuilder.handler(AutRoleMenuModel.menuId);
        }

        /**
         * 菜单名称
         */
        public SortBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> menuName() {
            return this.sortBuilder.handler(AutRoleMenuModel.menuName);
        }

        /**
         * 菜单唯一标识符
         */
        public SortBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> menuValue() {
            return this.sortBuilder.handler(AutRoleMenuModel.menuValue);
        }

        /**
         * 状态
         */
        public SortBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> status() {
            return this.sortBuilder.handler(AutRoleMenuModel.status);
        }

        /**
         * 创建时间
         */
        public SortBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> createTime() {
            return this.sortBuilder.handler(AutRoleMenuModel.createTime);
        }

        /**
         * 修改时间
         */
        public SortBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> updateTime() {
            return this.sortBuilder.handler(AutRoleMenuModel.updateTime);
        }

        /**
         * 删除时间
         */
        public SortBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.sortBuilder.handler(AutRoleMenuModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public SortBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.sortBuilder.handler(AutRoleMenuModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public SortBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.sortBuilder.handler(AutRoleMenuModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public SortBuilder<AutRoleMenuModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.sortBuilder.handler(AutRoleMenuModel.deleteTimeStamp);
        }

    }

}