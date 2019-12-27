package pub.avalonframework.cloud.gar.model;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("all")
public final class MenuRouteModel implements Model<MenuRouteModel, MenuRouteModel.Column, MenuRouteModel.On, MenuRouteModel.Where, MenuRouteModel.Sort, MenuRouteModel.Group> {

    /**
     * 表名
     */
    public static final String tableName = "gar_menu_route";
    /**
     * 表别名
     */
    public static final String tableAlias = "MenuRoute";

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
     * 关系
     */
    public static final String relation = "relation";
    /**
     * 关系
     */
    public static final String relation_alias = "relation";
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
        COLUMN_ALIAS_MAP.put(menuGroupId, menuGroupId_alias);
        ALIAS_COLUMN_MAP.put(menuGroupId_alias, menuGroupId);
        COLUMN_ALIAS_MAP.put(menuId, menuId_alias);
        ALIAS_COLUMN_MAP.put(menuId_alias, menuId);
        COLUMN_ALIAS_MAP.put(menuName, menuName_alias);
        ALIAS_COLUMN_MAP.put(menuName_alias, menuName);
        COLUMN_ALIAS_MAP.put(menuValue, menuValue_alias);
        ALIAS_COLUMN_MAP.put(menuValue_alias, menuValue);
        COLUMN_ALIAS_MAP.put(routeId, routeId_alias);
        ALIAS_COLUMN_MAP.put(routeId_alias, routeId);
        COLUMN_ALIAS_MAP.put(routeName, routeName_alias);
        ALIAS_COLUMN_MAP.put(routeName_alias, routeName);
        COLUMN_ALIAS_MAP.put(routeValue, routeValue_alias);
        ALIAS_COLUMN_MAP.put(routeValue_alias, routeValue);
        COLUMN_ALIAS_MAP.put(routePath, routePath_alias);
        ALIAS_COLUMN_MAP.put(routePath_alias, routePath);
        COLUMN_ALIAS_MAP.put(relation, relation_alias);
        ALIAS_COLUMN_MAP.put(relation_alias, relation);
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

    public static final class Column extends ColumnModel<MenuRouteModel, Column, On, Where, Sort, Group> {

        private Column() {
        }

        /**
         * 主键
         */
        public Column primaryKey() {
            this.addColumnAlias(MenuRouteModel.primaryKeyName, MenuRouteModel.primaryKeyAlias);
            return this;
        }

        /**
         * 主键
         * @param alias 别名
         */
        public Column primaryKey(String alias) {
            this.addColumnAlias(MenuRouteModel.primaryKeyName, alias);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Column id() {
            this.addColumnAlias(MenuRouteModel.id, MenuRouteModel.id_alias);
            return this;
        }

        /**
         * 主键ID
         * @param alias 别名
         */
        public Column id(String alias) {
            this.addColumnAlias(MenuRouteModel.id, alias);
            return this;
        }

        /**
         * 所属模块ID
         */
        public Column moduleId() {
            this.addColumnAlias(MenuRouteModel.moduleId, MenuRouteModel.moduleId_alias);
            return this;
        }

        /**
         * 所属模块ID
         * @param alias 别名
         */
        public Column moduleId(String alias) {
            this.addColumnAlias(MenuRouteModel.moduleId, alias);
            return this;
        }

        /**
         * 所属菜单组ID
         */
        public Column menuGroupId() {
            this.addColumnAlias(MenuRouteModel.menuGroupId, MenuRouteModel.menuGroupId_alias);
            return this;
        }

        /**
         * 所属菜单组ID
         * @param alias 别名
         */
        public Column menuGroupId(String alias) {
            this.addColumnAlias(MenuRouteModel.menuGroupId, alias);
            return this;
        }

        /**
         * 菜单ID
         */
        public Column menuId() {
            this.addColumnAlias(MenuRouteModel.menuId, MenuRouteModel.menuId_alias);
            return this;
        }

        /**
         * 菜单ID
         * @param alias 别名
         */
        public Column menuId(String alias) {
            this.addColumnAlias(MenuRouteModel.menuId, alias);
            return this;
        }

        /**
         * 菜单名称
         */
        public Column menuName() {
            this.addColumnAlias(MenuRouteModel.menuName, MenuRouteModel.menuName_alias);
            return this;
        }

        /**
         * 菜单名称
         * @param alias 别名
         */
        public Column menuName(String alias) {
            this.addColumnAlias(MenuRouteModel.menuName, alias);
            return this;
        }

        /**
         * 菜单唯一标识符
         */
        public Column menuValue() {
            this.addColumnAlias(MenuRouteModel.menuValue, MenuRouteModel.menuValue_alias);
            return this;
        }

        /**
         * 菜单唯一标识符
         * @param alias 别名
         */
        public Column menuValue(String alias) {
            this.addColumnAlias(MenuRouteModel.menuValue, alias);
            return this;
        }

        /**
         * 路由ID
         */
        public Column routeId() {
            this.addColumnAlias(MenuRouteModel.routeId, MenuRouteModel.routeId_alias);
            return this;
        }

        /**
         * 路由ID
         * @param alias 别名
         */
        public Column routeId(String alias) {
            this.addColumnAlias(MenuRouteModel.routeId, alias);
            return this;
        }

        /**
         * 路由名称
         */
        public Column routeName() {
            this.addColumnAlias(MenuRouteModel.routeName, MenuRouteModel.routeName_alias);
            return this;
        }

        /**
         * 路由名称
         * @param alias 别名
         */
        public Column routeName(String alias) {
            this.addColumnAlias(MenuRouteModel.routeName, alias);
            return this;
        }

        /**
         * 路由唯一标识符
         */
        public Column routeValue() {
            this.addColumnAlias(MenuRouteModel.routeValue, MenuRouteModel.routeValue_alias);
            return this;
        }

        /**
         * 路由唯一标识符
         * @param alias 别名
         */
        public Column routeValue(String alias) {
            this.addColumnAlias(MenuRouteModel.routeValue, alias);
            return this;
        }

        /**
         * 路由地址
         */
        public Column routePath() {
            this.addColumnAlias(MenuRouteModel.routePath, MenuRouteModel.routePath_alias);
            return this;
        }

        /**
         * 路由地址
         * @param alias 别名
         */
        public Column routePath(String alias) {
            this.addColumnAlias(MenuRouteModel.routePath, alias);
            return this;
        }

        /**
         * 关系
         */
        public Column relation() {
            this.addColumnAlias(MenuRouteModel.relation, MenuRouteModel.relation_alias);
            return this;
        }

        /**
         * 关系
         * @param alias 别名
         */
        public Column relation(String alias) {
            this.addColumnAlias(MenuRouteModel.relation, alias);
            return this;
        }

        /**
         * 状态
         */
        public Column status() {
            this.addColumnAlias(MenuRouteModel.status, MenuRouteModel.status_alias);
            return this;
        }

        /**
         * 状态
         * @param alias 别名
         */
        public Column status(String alias) {
            this.addColumnAlias(MenuRouteModel.status, alias);
            return this;
        }

        /**
         * 创建时间
         */
        public Column createTime() {
            this.addColumnAlias(MenuRouteModel.createTime, MenuRouteModel.createTime_alias);
            return this;
        }

        /**
         * 创建时间
         * @param alias 别名
         */
        public Column createTime(String alias) {
            this.addColumnAlias(MenuRouteModel.createTime, alias);
            return this;
        }

        /**
         * 修改时间
         */
        public Column updateTime() {
            this.addColumnAlias(MenuRouteModel.updateTime, MenuRouteModel.updateTime_alias);
            return this;
        }

        /**
         * 修改时间
         * @param alias 别名
         */
        public Column updateTime(String alias) {
            this.addColumnAlias(MenuRouteModel.updateTime, alias);
            return this;
        }

        /**
         * 删除时间
         */
        public Column deleteTime() {
            this.addColumnAlias(MenuRouteModel.deleteTime, MenuRouteModel.deleteTime_alias);
            return this;
        }

        /**
         * 删除时间
         * @param alias 别名
         */
        public Column deleteTime(String alias) {
            this.addColumnAlias(MenuRouteModel.deleteTime, alias);
            return this;
        }

        /**
         * 创建时间戳
         */
        public Column createTimeStamp() {
            this.addColumnAlias(MenuRouteModel.createTimeStamp, MenuRouteModel.createTimeStamp_alias);
            return this;
        }

        /**
         * 创建时间戳
         * @param alias 别名
         */
        public Column createTimeStamp(String alias) {
            this.addColumnAlias(MenuRouteModel.createTimeStamp, alias);
            return this;
        }

        /**
         * 修改时间戳
         */
        public Column updateTimeStamp() {
            this.addColumnAlias(MenuRouteModel.updateTimeStamp, MenuRouteModel.updateTimeStamp_alias);
            return this;
        }

        /**
         * 修改时间戳
         * @param alias 别名
         */
        public Column updateTimeStamp(String alias) {
            this.addColumnAlias(MenuRouteModel.updateTimeStamp, alias);
            return this;
        }

        /**
         * 删除时间戳
         */
        public Column deleteTimeStamp() {
            this.addColumnAlias(MenuRouteModel.deleteTimeStamp, MenuRouteModel.deleteTimeStamp_alias);
            return this;
        }

        /**
         * 删除时间戳
         * @param alias 别名
         */
        public Column deleteTimeStamp(String alias) {
            this.addColumnAlias(MenuRouteModel.deleteTimeStamp, alias);
            return this;
        }

    }

    @Override
    public On getOnModel() {
        return new On();
    }

    public static final class On extends OnModel<MenuRouteModel, Column, On, Where, Sort, Group> {

        private On() {
        }

        /**
         * 主键
         */
        public OnBuilder<MenuRouteModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.onBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public OnBuilder<MenuRouteModel, Column, On, Where, Sort, Group> id() {
            return this.onBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.id);
        }

        /**
         * 所属模块ID
         */
        public OnBuilder<MenuRouteModel, Column, On, Where, Sort, Group> moduleId() {
            return this.onBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.moduleId);
        }

        /**
         * 所属菜单组ID
         */
        public OnBuilder<MenuRouteModel, Column, On, Where, Sort, Group> menuGroupId() {
            return this.onBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.menuGroupId);
        }

        /**
         * 菜单ID
         */
        public OnBuilder<MenuRouteModel, Column, On, Where, Sort, Group> menuId() {
            return this.onBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.menuId);
        }

        /**
         * 菜单名称
         */
        public OnBuilder<MenuRouteModel, Column, On, Where, Sort, Group> menuName() {
            return this.onBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.menuName);
        }

        /**
         * 菜单唯一标识符
         */
        public OnBuilder<MenuRouteModel, Column, On, Where, Sort, Group> menuValue() {
            return this.onBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.menuValue);
        }

        /**
         * 路由ID
         */
        public OnBuilder<MenuRouteModel, Column, On, Where, Sort, Group> routeId() {
            return this.onBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.routeId);
        }

        /**
         * 路由名称
         */
        public OnBuilder<MenuRouteModel, Column, On, Where, Sort, Group> routeName() {
            return this.onBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.routeName);
        }

        /**
         * 路由唯一标识符
         */
        public OnBuilder<MenuRouteModel, Column, On, Where, Sort, Group> routeValue() {
            return this.onBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.routeValue);
        }

        /**
         * 路由地址
         */
        public OnBuilder<MenuRouteModel, Column, On, Where, Sort, Group> routePath() {
            return this.onBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.routePath);
        }

        /**
         * 关系
         */
        public OnBuilder<MenuRouteModel, Column, On, Where, Sort, Group> relation() {
            return this.onBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.relation);
        }

        /**
         * 状态
         */
        public OnBuilder<MenuRouteModel, Column, On, Where, Sort, Group> status() {
            return this.onBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.status);
        }

        /**
         * 创建时间
         */
        public OnBuilder<MenuRouteModel, Column, On, Where, Sort, Group> createTime() {
            return this.onBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.createTime);
        }

        /**
         * 修改时间
         */
        public OnBuilder<MenuRouteModel, Column, On, Where, Sort, Group> updateTime() {
            return this.onBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.updateTime);
        }

        /**
         * 删除时间
         */
        public OnBuilder<MenuRouteModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.onBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public OnBuilder<MenuRouteModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.onBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public OnBuilder<MenuRouteModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.onBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public OnBuilder<MenuRouteModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.onBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.deleteTimeStamp);
        }

    }

    @Override
    public Where getWhereModel() {
        return new Where();
    }

    public static final class Where extends WhereModel<MenuRouteModel, Column, On, Where, Sort, Group> {

        private Where() {
        }

        /**
         * 主键
         */
        public WhereBuilder<MenuRouteModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.whereBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public WhereBuilder<MenuRouteModel, Column, On, Where, Sort, Group> id() {
            return this.whereBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.id);
        }

        /**
         * 所属模块ID
         */
        public WhereBuilder<MenuRouteModel, Column, On, Where, Sort, Group> moduleId() {
            return this.whereBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.moduleId);
        }

        /**
         * 所属菜单组ID
         */
        public WhereBuilder<MenuRouteModel, Column, On, Where, Sort, Group> menuGroupId() {
            return this.whereBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.menuGroupId);
        }

        /**
         * 菜单ID
         */
        public WhereBuilder<MenuRouteModel, Column, On, Where, Sort, Group> menuId() {
            return this.whereBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.menuId);
        }

        /**
         * 菜单名称
         */
        public WhereBuilder<MenuRouteModel, Column, On, Where, Sort, Group> menuName() {
            return this.whereBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.menuName);
        }

        /**
         * 菜单唯一标识符
         */
        public WhereBuilder<MenuRouteModel, Column, On, Where, Sort, Group> menuValue() {
            return this.whereBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.menuValue);
        }

        /**
         * 路由ID
         */
        public WhereBuilder<MenuRouteModel, Column, On, Where, Sort, Group> routeId() {
            return this.whereBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.routeId);
        }

        /**
         * 路由名称
         */
        public WhereBuilder<MenuRouteModel, Column, On, Where, Sort, Group> routeName() {
            return this.whereBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.routeName);
        }

        /**
         * 路由唯一标识符
         */
        public WhereBuilder<MenuRouteModel, Column, On, Where, Sort, Group> routeValue() {
            return this.whereBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.routeValue);
        }

        /**
         * 路由地址
         */
        public WhereBuilder<MenuRouteModel, Column, On, Where, Sort, Group> routePath() {
            return this.whereBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.routePath);
        }

        /**
         * 关系
         */
        public WhereBuilder<MenuRouteModel, Column, On, Where, Sort, Group> relation() {
            return this.whereBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.relation);
        }

        /**
         * 状态
         */
        public WhereBuilder<MenuRouteModel, Column, On, Where, Sort, Group> status() {
            return this.whereBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.status);
        }

        /**
         * 创建时间
         */
        public WhereBuilder<MenuRouteModel, Column, On, Where, Sort, Group> createTime() {
            return this.whereBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.createTime);
        }

        /**
         * 修改时间
         */
        public WhereBuilder<MenuRouteModel, Column, On, Where, Sort, Group> updateTime() {
            return this.whereBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.updateTime);
        }

        /**
         * 删除时间
         */
        public WhereBuilder<MenuRouteModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.whereBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public WhereBuilder<MenuRouteModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.whereBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public WhereBuilder<MenuRouteModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.whereBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public WhereBuilder<MenuRouteModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.whereBuilder.handler(MenuRouteModel.tableName, MenuRouteModel.tableAlias, MenuRouteModel.deleteTimeStamp);
        }

    }

    @Override
    public Group getGroupModel() {
        return new Group();
    }

    public static final class Group extends GroupModel<MenuRouteModel, Column, On, Where, Sort, Group> {

        private Group() {
        }

        /**
         * 主键
         */
        public Group primaryKey() {
            this.addColumn(MenuRouteModel.primaryKeyName);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Group id() {
            this.addColumn(MenuRouteModel.id);
            return this;
        }

        /**
         * 所属模块ID
         */
        public Group moduleId() {
            this.addColumn(MenuRouteModel.moduleId);
            return this;
        }

        /**
         * 所属菜单组ID
         */
        public Group menuGroupId() {
            this.addColumn(MenuRouteModel.menuGroupId);
            return this;
        }

        /**
         * 菜单ID
         */
        public Group menuId() {
            this.addColumn(MenuRouteModel.menuId);
            return this;
        }

        /**
         * 菜单名称
         */
        public Group menuName() {
            this.addColumn(MenuRouteModel.menuName);
            return this;
        }

        /**
         * 菜单唯一标识符
         */
        public Group menuValue() {
            this.addColumn(MenuRouteModel.menuValue);
            return this;
        }

        /**
         * 路由ID
         */
        public Group routeId() {
            this.addColumn(MenuRouteModel.routeId);
            return this;
        }

        /**
         * 路由名称
         */
        public Group routeName() {
            this.addColumn(MenuRouteModel.routeName);
            return this;
        }

        /**
         * 路由唯一标识符
         */
        public Group routeValue() {
            this.addColumn(MenuRouteModel.routeValue);
            return this;
        }

        /**
         * 路由地址
         */
        public Group routePath() {
            this.addColumn(MenuRouteModel.routePath);
            return this;
        }

        /**
         * 关系
         */
        public Group relation() {
            this.addColumn(MenuRouteModel.relation);
            return this;
        }

        /**
         * 状态
         */
        public Group status() {
            this.addColumn(MenuRouteModel.status);
            return this;
        }

        /**
         * 创建时间
         */
        public Group createTime() {
            this.addColumn(MenuRouteModel.createTime);
            return this;
        }

        /**
         * 修改时间
         */
        public Group updateTime() {
            this.addColumn(MenuRouteModel.updateTime);
            return this;
        }

        /**
         * 删除时间
         */
        public Group deleteTime() {
            this.addColumn(MenuRouteModel.deleteTime);
            return this;
        }

        /**
         * 创建时间戳
         */
        public Group createTimeStamp() {
            this.addColumn(MenuRouteModel.createTimeStamp);
            return this;
        }

        /**
         * 修改时间戳
         */
        public Group updateTimeStamp() {
            this.addColumn(MenuRouteModel.updateTimeStamp);
            return this;
        }

        /**
         * 删除时间戳
         */
        public Group deleteTimeStamp() {
            this.addColumn(MenuRouteModel.deleteTimeStamp);
            return this;
        }

    }

    @Override
    public Sort getSortModel() {
        return new Sort();
    }

    public static final class Sort extends SortModel<MenuRouteModel, Column, On, Where, Sort, Group> {

        private Sort() {
        }

        /**
         * 主键
         */
        public SortBuilder<MenuRouteModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.sortBuilder.handler(MenuRouteModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public SortBuilder<MenuRouteModel, Column, On, Where, Sort, Group> id() {
            return this.sortBuilder.handler(MenuRouteModel.id);
        }

        /**
         * 所属模块ID
         */
        public SortBuilder<MenuRouteModel, Column, On, Where, Sort, Group> moduleId() {
            return this.sortBuilder.handler(MenuRouteModel.moduleId);
        }

        /**
         * 所属菜单组ID
         */
        public SortBuilder<MenuRouteModel, Column, On, Where, Sort, Group> menuGroupId() {
            return this.sortBuilder.handler(MenuRouteModel.menuGroupId);
        }

        /**
         * 菜单ID
         */
        public SortBuilder<MenuRouteModel, Column, On, Where, Sort, Group> menuId() {
            return this.sortBuilder.handler(MenuRouteModel.menuId);
        }

        /**
         * 菜单名称
         */
        public SortBuilder<MenuRouteModel, Column, On, Where, Sort, Group> menuName() {
            return this.sortBuilder.handler(MenuRouteModel.menuName);
        }

        /**
         * 菜单唯一标识符
         */
        public SortBuilder<MenuRouteModel, Column, On, Where, Sort, Group> menuValue() {
            return this.sortBuilder.handler(MenuRouteModel.menuValue);
        }

        /**
         * 路由ID
         */
        public SortBuilder<MenuRouteModel, Column, On, Where, Sort, Group> routeId() {
            return this.sortBuilder.handler(MenuRouteModel.routeId);
        }

        /**
         * 路由名称
         */
        public SortBuilder<MenuRouteModel, Column, On, Where, Sort, Group> routeName() {
            return this.sortBuilder.handler(MenuRouteModel.routeName);
        }

        /**
         * 路由唯一标识符
         */
        public SortBuilder<MenuRouteModel, Column, On, Where, Sort, Group> routeValue() {
            return this.sortBuilder.handler(MenuRouteModel.routeValue);
        }

        /**
         * 路由地址
         */
        public SortBuilder<MenuRouteModel, Column, On, Where, Sort, Group> routePath() {
            return this.sortBuilder.handler(MenuRouteModel.routePath);
        }

        /**
         * 关系
         */
        public SortBuilder<MenuRouteModel, Column, On, Where, Sort, Group> relation() {
            return this.sortBuilder.handler(MenuRouteModel.relation);
        }

        /**
         * 状态
         */
        public SortBuilder<MenuRouteModel, Column, On, Where, Sort, Group> status() {
            return this.sortBuilder.handler(MenuRouteModel.status);
        }

        /**
         * 创建时间
         */
        public SortBuilder<MenuRouteModel, Column, On, Where, Sort, Group> createTime() {
            return this.sortBuilder.handler(MenuRouteModel.createTime);
        }

        /**
         * 修改时间
         */
        public SortBuilder<MenuRouteModel, Column, On, Where, Sort, Group> updateTime() {
            return this.sortBuilder.handler(MenuRouteModel.updateTime);
        }

        /**
         * 删除时间
         */
        public SortBuilder<MenuRouteModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.sortBuilder.handler(MenuRouteModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public SortBuilder<MenuRouteModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.sortBuilder.handler(MenuRouteModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public SortBuilder<MenuRouteModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.sortBuilder.handler(MenuRouteModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public SortBuilder<MenuRouteModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.sortBuilder.handler(MenuRouteModel.deleteTimeStamp);
        }

    }

}