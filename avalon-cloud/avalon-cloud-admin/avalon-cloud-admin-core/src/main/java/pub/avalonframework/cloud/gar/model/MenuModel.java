package pub.avalonframework.cloud.gar.model;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("all")
public final class MenuModel implements Model<MenuModel, MenuModel.Column, MenuModel.On, MenuModel.Where, MenuModel.Sort, MenuModel.Group> {

    /**
     * 表名
     */
    public static final String tableName = "gar_menu";
    /**
     * 表别名
     */
    public static final String tableAlias = "Menu";

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
     * 所属菜单组ID
     */
    public static final String menuGroupId = "menu_group_id";
    /**
     * 所属菜单组ID
     */
    public static final String menuGroupId_alias = "menuGroupId";
    /**
     * 菜单名称
     */
    public static final String name = "name";
    /**
     * 菜单名称
     */
    public static final String name_alias = "name";
    /**
     * 菜单唯一标识符
     */
    public static final String value = "value";
    /**
     * 菜单唯一标识符
     */
    public static final String value_alias = "value";
    /**
     * 图标名称
     */
    public static final String iconName = "icon_name";
    /**
     * 图标名称
     */
    public static final String iconName_alias = "iconName";
    /**
     * 菜单描述
     */
    public static final String description = "description";
    /**
     * 菜单描述
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
     * 是否使用选项卡
     */
    public static final String useTab = "use_tab";
    /**
     * 是否使用选项卡
     */
    public static final String useTab_alias = "useTab";
    /**
     * 初始化是否在选项卡内打开
     */
    public static final String initOpenInTab = "init_open_in_tab";
    /**
     * 初始化是否在选项卡内打开
     */
    public static final String initOpenInTab_alias = "initOpenInTab";
    /**
     * 在选项卡内是否可关闭
     */
    public static final String closableInTab = "closable_in_tab";
    /**
     * 在选项卡内是否可关闭
     */
    public static final String closableInTab_alias = "closableInTab";
    /**
     * 在选项卡内是否缓存
     */
    public static final String cacheInTab = "cache_in_tab";
    /**
     * 在选项卡内是否缓存
     */
    public static final String cacheInTab_alias = "cacheInTab";
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
        COLUMN_ALIAS_MAP.put(menuGroupId, menuGroupId_alias);
        ALIAS_COLUMN_MAP.put(menuGroupId_alias, menuGroupId);
        COLUMN_ALIAS_MAP.put(name, name_alias);
        ALIAS_COLUMN_MAP.put(name_alias, name);
        COLUMN_ALIAS_MAP.put(value, value_alias);
        ALIAS_COLUMN_MAP.put(value_alias, value);
        COLUMN_ALIAS_MAP.put(iconName, iconName_alias);
        ALIAS_COLUMN_MAP.put(iconName_alias, iconName);
        COLUMN_ALIAS_MAP.put(description, description_alias);
        ALIAS_COLUMN_MAP.put(description_alias, description);
        COLUMN_ALIAS_MAP.put(parentId, parentId_alias);
        ALIAS_COLUMN_MAP.put(parentId_alias, parentId);
        COLUMN_ALIAS_MAP.put(parentIds, parentIds_alias);
        ALIAS_COLUMN_MAP.put(parentIds_alias, parentIds);
        COLUMN_ALIAS_MAP.put(useTab, useTab_alias);
        ALIAS_COLUMN_MAP.put(useTab_alias, useTab);
        COLUMN_ALIAS_MAP.put(initOpenInTab, initOpenInTab_alias);
        ALIAS_COLUMN_MAP.put(initOpenInTab_alias, initOpenInTab);
        COLUMN_ALIAS_MAP.put(closableInTab, closableInTab_alias);
        ALIAS_COLUMN_MAP.put(closableInTab_alias, closableInTab);
        COLUMN_ALIAS_MAP.put(cacheInTab, cacheInTab_alias);
        ALIAS_COLUMN_MAP.put(cacheInTab_alias, cacheInTab);
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

    public static final class Column extends ColumnModel<MenuModel, Column, On, Where, Sort, Group> {

        private Column() {
        }

        /**
         * 主键
         */
        public Column primaryKey() {
            this.addColumnAlias(MenuModel.primaryKeyName, MenuModel.primaryKeyAlias);
            return this;
        }

        /**
         * 主键
         * @param alias 别名
         */
        public Column primaryKey(String alias) {
            this.addColumnAlias(MenuModel.primaryKeyName, alias);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Column id() {
            this.addColumnAlias(MenuModel.id, MenuModel.id_alias);
            return this;
        }

        /**
         * 主键ID
         * @param alias 别名
         */
        public Column id(String alias) {
            this.addColumnAlias(MenuModel.id, alias);
            return this;
        }

        /**
         * 所属模块ID
         */
        public Column moduleId() {
            this.addColumnAlias(MenuModel.moduleId, MenuModel.moduleId_alias);
            return this;
        }

        /**
         * 所属模块ID
         * @param alias 别名
         */
        public Column moduleId(String alias) {
            this.addColumnAlias(MenuModel.moduleId, alias);
            return this;
        }

        /**
         * 所属子模块ID
         */
        public Column subModuleId() {
            this.addColumnAlias(MenuModel.subModuleId, MenuModel.subModuleId_alias);
            return this;
        }

        /**
         * 所属子模块ID
         * @param alias 别名
         */
        public Column subModuleId(String alias) {
            this.addColumnAlias(MenuModel.subModuleId, alias);
            return this;
        }

        /**
         * 所属子模块名称
         */
        public Column subModuleName() {
            this.addColumnAlias(MenuModel.subModuleName, MenuModel.subModuleName_alias);
            return this;
        }

        /**
         * 所属子模块名称
         * @param alias 别名
         */
        public Column subModuleName(String alias) {
            this.addColumnAlias(MenuModel.subModuleName, alias);
            return this;
        }

        /**
         * 所属菜单组ID
         */
        public Column menuGroupId() {
            this.addColumnAlias(MenuModel.menuGroupId, MenuModel.menuGroupId_alias);
            return this;
        }

        /**
         * 所属菜单组ID
         * @param alias 别名
         */
        public Column menuGroupId(String alias) {
            this.addColumnAlias(MenuModel.menuGroupId, alias);
            return this;
        }

        /**
         * 菜单名称
         */
        public Column name() {
            this.addColumnAlias(MenuModel.name, MenuModel.name_alias);
            return this;
        }

        /**
         * 菜单名称
         * @param alias 别名
         */
        public Column name(String alias) {
            this.addColumnAlias(MenuModel.name, alias);
            return this;
        }

        /**
         * 菜单唯一标识符
         */
        public Column value() {
            this.addColumnAlias(MenuModel.value, MenuModel.value_alias);
            return this;
        }

        /**
         * 菜单唯一标识符
         * @param alias 别名
         */
        public Column value(String alias) {
            this.addColumnAlias(MenuModel.value, alias);
            return this;
        }

        /**
         * 图标名称
         */
        public Column iconName() {
            this.addColumnAlias(MenuModel.iconName, MenuModel.iconName_alias);
            return this;
        }

        /**
         * 图标名称
         * @param alias 别名
         */
        public Column iconName(String alias) {
            this.addColumnAlias(MenuModel.iconName, alias);
            return this;
        }

        /**
         * 菜单描述
         */
        public Column description() {
            this.addColumnAlias(MenuModel.description, MenuModel.description_alias);
            return this;
        }

        /**
         * 菜单描述
         * @param alias 别名
         */
        public Column description(String alias) {
            this.addColumnAlias(MenuModel.description, alias);
            return this;
        }

        /**
         * 父id
         */
        public Column parentId() {
            this.addColumnAlias(MenuModel.parentId, MenuModel.parentId_alias);
            return this;
        }

        /**
         * 父id
         * @param alias 别名
         */
        public Column parentId(String alias) {
            this.addColumnAlias(MenuModel.parentId, alias);
            return this;
        }

        /**
         * 祖先id
         */
        public Column parentIds() {
            this.addColumnAlias(MenuModel.parentIds, MenuModel.parentIds_alias);
            return this;
        }

        /**
         * 祖先id
         * @param alias 别名
         */
        public Column parentIds(String alias) {
            this.addColumnAlias(MenuModel.parentIds, alias);
            return this;
        }

        /**
         * 是否使用选项卡
         */
        public Column useTab() {
            this.addColumnAlias(MenuModel.useTab, MenuModel.useTab_alias);
            return this;
        }

        /**
         * 是否使用选项卡
         * @param alias 别名
         */
        public Column useTab(String alias) {
            this.addColumnAlias(MenuModel.useTab, alias);
            return this;
        }

        /**
         * 初始化是否在选项卡内打开
         */
        public Column initOpenInTab() {
            this.addColumnAlias(MenuModel.initOpenInTab, MenuModel.initOpenInTab_alias);
            return this;
        }

        /**
         * 初始化是否在选项卡内打开
         * @param alias 别名
         */
        public Column initOpenInTab(String alias) {
            this.addColumnAlias(MenuModel.initOpenInTab, alias);
            return this;
        }

        /**
         * 在选项卡内是否可关闭
         */
        public Column closableInTab() {
            this.addColumnAlias(MenuModel.closableInTab, MenuModel.closableInTab_alias);
            return this;
        }

        /**
         * 在选项卡内是否可关闭
         * @param alias 别名
         */
        public Column closableInTab(String alias) {
            this.addColumnAlias(MenuModel.closableInTab, alias);
            return this;
        }

        /**
         * 在选项卡内是否缓存
         */
        public Column cacheInTab() {
            this.addColumnAlias(MenuModel.cacheInTab, MenuModel.cacheInTab_alias);
            return this;
        }

        /**
         * 在选项卡内是否缓存
         * @param alias 别名
         */
        public Column cacheInTab(String alias) {
            this.addColumnAlias(MenuModel.cacheInTab, alias);
            return this;
        }

        /**
         * 排序号
         */
        public Column index() {
            this.addColumnAlias(MenuModel.index, MenuModel.index_alias);
            return this;
        }

        /**
         * 排序号
         * @param alias 别名
         */
        public Column index(String alias) {
            this.addColumnAlias(MenuModel.index, alias);
            return this;
        }

        /**
         * 状态
         */
        public Column status() {
            this.addColumnAlias(MenuModel.status, MenuModel.status_alias);
            return this;
        }

        /**
         * 状态
         * @param alias 别名
         */
        public Column status(String alias) {
            this.addColumnAlias(MenuModel.status, alias);
            return this;
        }

        /**
         * 创建时间
         */
        public Column createTime() {
            this.addColumnAlias(MenuModel.createTime, MenuModel.createTime_alias);
            return this;
        }

        /**
         * 创建时间
         * @param alias 别名
         */
        public Column createTime(String alias) {
            this.addColumnAlias(MenuModel.createTime, alias);
            return this;
        }

        /**
         * 修改时间
         */
        public Column updateTime() {
            this.addColumnAlias(MenuModel.updateTime, MenuModel.updateTime_alias);
            return this;
        }

        /**
         * 修改时间
         * @param alias 别名
         */
        public Column updateTime(String alias) {
            this.addColumnAlias(MenuModel.updateTime, alias);
            return this;
        }

        /**
         * 删除时间
         */
        public Column deleteTime() {
            this.addColumnAlias(MenuModel.deleteTime, MenuModel.deleteTime_alias);
            return this;
        }

        /**
         * 删除时间
         * @param alias 别名
         */
        public Column deleteTime(String alias) {
            this.addColumnAlias(MenuModel.deleteTime, alias);
            return this;
        }

        /**
         * 创建时间戳
         */
        public Column createTimeStamp() {
            this.addColumnAlias(MenuModel.createTimeStamp, MenuModel.createTimeStamp_alias);
            return this;
        }

        /**
         * 创建时间戳
         * @param alias 别名
         */
        public Column createTimeStamp(String alias) {
            this.addColumnAlias(MenuModel.createTimeStamp, alias);
            return this;
        }

        /**
         * 修改时间戳
         */
        public Column updateTimeStamp() {
            this.addColumnAlias(MenuModel.updateTimeStamp, MenuModel.updateTimeStamp_alias);
            return this;
        }

        /**
         * 修改时间戳
         * @param alias 别名
         */
        public Column updateTimeStamp(String alias) {
            this.addColumnAlias(MenuModel.updateTimeStamp, alias);
            return this;
        }

        /**
         * 删除时间戳
         */
        public Column deleteTimeStamp() {
            this.addColumnAlias(MenuModel.deleteTimeStamp, MenuModel.deleteTimeStamp_alias);
            return this;
        }

        /**
         * 删除时间戳
         * @param alias 别名
         */
        public Column deleteTimeStamp(String alias) {
            this.addColumnAlias(MenuModel.deleteTimeStamp, alias);
            return this;
        }

    }

    @Override
    public On getOnModel() {
        return new On();
    }

    public static final class On extends OnModel<MenuModel, Column, On, Where, Sort, Group> {

        private On() {
        }

        /**
         * 主键
         */
        public OnBuilder<MenuModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.onBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public OnBuilder<MenuModel, Column, On, Where, Sort, Group> id() {
            return this.onBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.id);
        }

        /**
         * 所属模块ID
         */
        public OnBuilder<MenuModel, Column, On, Where, Sort, Group> moduleId() {
            return this.onBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.moduleId);
        }

        /**
         * 所属子模块ID
         */
        public OnBuilder<MenuModel, Column, On, Where, Sort, Group> subModuleId() {
            return this.onBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.subModuleId);
        }

        /**
         * 所属子模块名称
         */
        public OnBuilder<MenuModel, Column, On, Where, Sort, Group> subModuleName() {
            return this.onBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.subModuleName);
        }

        /**
         * 所属菜单组ID
         */
        public OnBuilder<MenuModel, Column, On, Where, Sort, Group> menuGroupId() {
            return this.onBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.menuGroupId);
        }

        /**
         * 菜单名称
         */
        public OnBuilder<MenuModel, Column, On, Where, Sort, Group> name() {
            return this.onBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.name);
        }

        /**
         * 菜单唯一标识符
         */
        public OnBuilder<MenuModel, Column, On, Where, Sort, Group> value() {
            return this.onBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.value);
        }

        /**
         * 图标名称
         */
        public OnBuilder<MenuModel, Column, On, Where, Sort, Group> iconName() {
            return this.onBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.iconName);
        }

        /**
         * 菜单描述
         */
        public OnBuilder<MenuModel, Column, On, Where, Sort, Group> description() {
            return this.onBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.description);
        }

        /**
         * 父id
         */
        public OnBuilder<MenuModel, Column, On, Where, Sort, Group> parentId() {
            return this.onBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.parentId);
        }

        /**
         * 祖先id
         */
        public OnBuilder<MenuModel, Column, On, Where, Sort, Group> parentIds() {
            return this.onBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.parentIds);
        }

        /**
         * 是否使用选项卡
         */
        public OnBuilder<MenuModel, Column, On, Where, Sort, Group> useTab() {
            return this.onBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.useTab);
        }

        /**
         * 初始化是否在选项卡内打开
         */
        public OnBuilder<MenuModel, Column, On, Where, Sort, Group> initOpenInTab() {
            return this.onBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.initOpenInTab);
        }

        /**
         * 在选项卡内是否可关闭
         */
        public OnBuilder<MenuModel, Column, On, Where, Sort, Group> closableInTab() {
            return this.onBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.closableInTab);
        }

        /**
         * 在选项卡内是否缓存
         */
        public OnBuilder<MenuModel, Column, On, Where, Sort, Group> cacheInTab() {
            return this.onBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.cacheInTab);
        }

        /**
         * 排序号
         */
        public OnBuilder<MenuModel, Column, On, Where, Sort, Group> index() {
            return this.onBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.index);
        }

        /**
         * 状态
         */
        public OnBuilder<MenuModel, Column, On, Where, Sort, Group> status() {
            return this.onBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.status);
        }

        /**
         * 创建时间
         */
        public OnBuilder<MenuModel, Column, On, Where, Sort, Group> createTime() {
            return this.onBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.createTime);
        }

        /**
         * 修改时间
         */
        public OnBuilder<MenuModel, Column, On, Where, Sort, Group> updateTime() {
            return this.onBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.updateTime);
        }

        /**
         * 删除时间
         */
        public OnBuilder<MenuModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.onBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public OnBuilder<MenuModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.onBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public OnBuilder<MenuModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.onBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public OnBuilder<MenuModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.onBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.deleteTimeStamp);
        }

    }

    @Override
    public Where getWhereModel() {
        return new Where();
    }

    public static final class Where extends WhereModel<MenuModel, Column, On, Where, Sort, Group> {

        private Where() {
        }

        /**
         * 主键
         */
        public WhereBuilder<MenuModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.whereBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public WhereBuilder<MenuModel, Column, On, Where, Sort, Group> id() {
            return this.whereBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.id);
        }

        /**
         * 所属模块ID
         */
        public WhereBuilder<MenuModel, Column, On, Where, Sort, Group> moduleId() {
            return this.whereBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.moduleId);
        }

        /**
         * 所属子模块ID
         */
        public WhereBuilder<MenuModel, Column, On, Where, Sort, Group> subModuleId() {
            return this.whereBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.subModuleId);
        }

        /**
         * 所属子模块名称
         */
        public WhereBuilder<MenuModel, Column, On, Where, Sort, Group> subModuleName() {
            return this.whereBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.subModuleName);
        }

        /**
         * 所属菜单组ID
         */
        public WhereBuilder<MenuModel, Column, On, Where, Sort, Group> menuGroupId() {
            return this.whereBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.menuGroupId);
        }

        /**
         * 菜单名称
         */
        public WhereBuilder<MenuModel, Column, On, Where, Sort, Group> name() {
            return this.whereBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.name);
        }

        /**
         * 菜单唯一标识符
         */
        public WhereBuilder<MenuModel, Column, On, Where, Sort, Group> value() {
            return this.whereBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.value);
        }

        /**
         * 图标名称
         */
        public WhereBuilder<MenuModel, Column, On, Where, Sort, Group> iconName() {
            return this.whereBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.iconName);
        }

        /**
         * 菜单描述
         */
        public WhereBuilder<MenuModel, Column, On, Where, Sort, Group> description() {
            return this.whereBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.description);
        }

        /**
         * 父id
         */
        public WhereBuilder<MenuModel, Column, On, Where, Sort, Group> parentId() {
            return this.whereBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.parentId);
        }

        /**
         * 祖先id
         */
        public WhereBuilder<MenuModel, Column, On, Where, Sort, Group> parentIds() {
            return this.whereBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.parentIds);
        }

        /**
         * 是否使用选项卡
         */
        public WhereBuilder<MenuModel, Column, On, Where, Sort, Group> useTab() {
            return this.whereBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.useTab);
        }

        /**
         * 初始化是否在选项卡内打开
         */
        public WhereBuilder<MenuModel, Column, On, Where, Sort, Group> initOpenInTab() {
            return this.whereBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.initOpenInTab);
        }

        /**
         * 在选项卡内是否可关闭
         */
        public WhereBuilder<MenuModel, Column, On, Where, Sort, Group> closableInTab() {
            return this.whereBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.closableInTab);
        }

        /**
         * 在选项卡内是否缓存
         */
        public WhereBuilder<MenuModel, Column, On, Where, Sort, Group> cacheInTab() {
            return this.whereBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.cacheInTab);
        }

        /**
         * 排序号
         */
        public WhereBuilder<MenuModel, Column, On, Where, Sort, Group> index() {
            return this.whereBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.index);
        }

        /**
         * 状态
         */
        public WhereBuilder<MenuModel, Column, On, Where, Sort, Group> status() {
            return this.whereBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.status);
        }

        /**
         * 创建时间
         */
        public WhereBuilder<MenuModel, Column, On, Where, Sort, Group> createTime() {
            return this.whereBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.createTime);
        }

        /**
         * 修改时间
         */
        public WhereBuilder<MenuModel, Column, On, Where, Sort, Group> updateTime() {
            return this.whereBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.updateTime);
        }

        /**
         * 删除时间
         */
        public WhereBuilder<MenuModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.whereBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public WhereBuilder<MenuModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.whereBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public WhereBuilder<MenuModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.whereBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public WhereBuilder<MenuModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.whereBuilder.handler(MenuModel.tableName, MenuModel.tableAlias, MenuModel.deleteTimeStamp);
        }

    }

    @Override
    public Group getGroupModel() {
        return new Group();
    }

    public static final class Group extends GroupModel<MenuModel, Column, On, Where, Sort, Group> {

        private Group() {
        }

        /**
         * 主键
         */
        public Group primaryKey() {
            this.addColumn(MenuModel.primaryKeyName);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Group id() {
            this.addColumn(MenuModel.id);
            return this;
        }

        /**
         * 所属模块ID
         */
        public Group moduleId() {
            this.addColumn(MenuModel.moduleId);
            return this;
        }

        /**
         * 所属子模块ID
         */
        public Group subModuleId() {
            this.addColumn(MenuModel.subModuleId);
            return this;
        }

        /**
         * 所属子模块名称
         */
        public Group subModuleName() {
            this.addColumn(MenuModel.subModuleName);
            return this;
        }

        /**
         * 所属菜单组ID
         */
        public Group menuGroupId() {
            this.addColumn(MenuModel.menuGroupId);
            return this;
        }

        /**
         * 菜单名称
         */
        public Group name() {
            this.addColumn(MenuModel.name);
            return this;
        }

        /**
         * 菜单唯一标识符
         */
        public Group value() {
            this.addColumn(MenuModel.value);
            return this;
        }

        /**
         * 图标名称
         */
        public Group iconName() {
            this.addColumn(MenuModel.iconName);
            return this;
        }

        /**
         * 菜单描述
         */
        public Group description() {
            this.addColumn(MenuModel.description);
            return this;
        }

        /**
         * 父id
         */
        public Group parentId() {
            this.addColumn(MenuModel.parentId);
            return this;
        }

        /**
         * 祖先id
         */
        public Group parentIds() {
            this.addColumn(MenuModel.parentIds);
            return this;
        }

        /**
         * 是否使用选项卡
         */
        public Group useTab() {
            this.addColumn(MenuModel.useTab);
            return this;
        }

        /**
         * 初始化是否在选项卡内打开
         */
        public Group initOpenInTab() {
            this.addColumn(MenuModel.initOpenInTab);
            return this;
        }

        /**
         * 在选项卡内是否可关闭
         */
        public Group closableInTab() {
            this.addColumn(MenuModel.closableInTab);
            return this;
        }

        /**
         * 在选项卡内是否缓存
         */
        public Group cacheInTab() {
            this.addColumn(MenuModel.cacheInTab);
            return this;
        }

        /**
         * 排序号
         */
        public Group index() {
            this.addColumn(MenuModel.index);
            return this;
        }

        /**
         * 状态
         */
        public Group status() {
            this.addColumn(MenuModel.status);
            return this;
        }

        /**
         * 创建时间
         */
        public Group createTime() {
            this.addColumn(MenuModel.createTime);
            return this;
        }

        /**
         * 修改时间
         */
        public Group updateTime() {
            this.addColumn(MenuModel.updateTime);
            return this;
        }

        /**
         * 删除时间
         */
        public Group deleteTime() {
            this.addColumn(MenuModel.deleteTime);
            return this;
        }

        /**
         * 创建时间戳
         */
        public Group createTimeStamp() {
            this.addColumn(MenuModel.createTimeStamp);
            return this;
        }

        /**
         * 修改时间戳
         */
        public Group updateTimeStamp() {
            this.addColumn(MenuModel.updateTimeStamp);
            return this;
        }

        /**
         * 删除时间戳
         */
        public Group deleteTimeStamp() {
            this.addColumn(MenuModel.deleteTimeStamp);
            return this;
        }

    }

    @Override
    public Sort getSortModel() {
        return new Sort();
    }

    public static final class Sort extends SortModel<MenuModel, Column, On, Where, Sort, Group> {

        private Sort() {
        }

        /**
         * 主键
         */
        public SortBuilder<MenuModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.sortBuilder.handler(MenuModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public SortBuilder<MenuModel, Column, On, Where, Sort, Group> id() {
            return this.sortBuilder.handler(MenuModel.id);
        }

        /**
         * 所属模块ID
         */
        public SortBuilder<MenuModel, Column, On, Where, Sort, Group> moduleId() {
            return this.sortBuilder.handler(MenuModel.moduleId);
        }

        /**
         * 所属子模块ID
         */
        public SortBuilder<MenuModel, Column, On, Where, Sort, Group> subModuleId() {
            return this.sortBuilder.handler(MenuModel.subModuleId);
        }

        /**
         * 所属子模块名称
         */
        public SortBuilder<MenuModel, Column, On, Where, Sort, Group> subModuleName() {
            return this.sortBuilder.handler(MenuModel.subModuleName);
        }

        /**
         * 所属菜单组ID
         */
        public SortBuilder<MenuModel, Column, On, Where, Sort, Group> menuGroupId() {
            return this.sortBuilder.handler(MenuModel.menuGroupId);
        }

        /**
         * 菜单名称
         */
        public SortBuilder<MenuModel, Column, On, Where, Sort, Group> name() {
            return this.sortBuilder.handler(MenuModel.name);
        }

        /**
         * 菜单唯一标识符
         */
        public SortBuilder<MenuModel, Column, On, Where, Sort, Group> value() {
            return this.sortBuilder.handler(MenuModel.value);
        }

        /**
         * 图标名称
         */
        public SortBuilder<MenuModel, Column, On, Where, Sort, Group> iconName() {
            return this.sortBuilder.handler(MenuModel.iconName);
        }

        /**
         * 菜单描述
         */
        public SortBuilder<MenuModel, Column, On, Where, Sort, Group> description() {
            return this.sortBuilder.handler(MenuModel.description);
        }

        /**
         * 父id
         */
        public SortBuilder<MenuModel, Column, On, Where, Sort, Group> parentId() {
            return this.sortBuilder.handler(MenuModel.parentId);
        }

        /**
         * 祖先id
         */
        public SortBuilder<MenuModel, Column, On, Where, Sort, Group> parentIds() {
            return this.sortBuilder.handler(MenuModel.parentIds);
        }

        /**
         * 是否使用选项卡
         */
        public SortBuilder<MenuModel, Column, On, Where, Sort, Group> useTab() {
            return this.sortBuilder.handler(MenuModel.useTab);
        }

        /**
         * 初始化是否在选项卡内打开
         */
        public SortBuilder<MenuModel, Column, On, Where, Sort, Group> initOpenInTab() {
            return this.sortBuilder.handler(MenuModel.initOpenInTab);
        }

        /**
         * 在选项卡内是否可关闭
         */
        public SortBuilder<MenuModel, Column, On, Where, Sort, Group> closableInTab() {
            return this.sortBuilder.handler(MenuModel.closableInTab);
        }

        /**
         * 在选项卡内是否缓存
         */
        public SortBuilder<MenuModel, Column, On, Where, Sort, Group> cacheInTab() {
            return this.sortBuilder.handler(MenuModel.cacheInTab);
        }

        /**
         * 排序号
         */
        public SortBuilder<MenuModel, Column, On, Where, Sort, Group> index() {
            return this.sortBuilder.handler(MenuModel.index);
        }

        /**
         * 状态
         */
        public SortBuilder<MenuModel, Column, On, Where, Sort, Group> status() {
            return this.sortBuilder.handler(MenuModel.status);
        }

        /**
         * 创建时间
         */
        public SortBuilder<MenuModel, Column, On, Where, Sort, Group> createTime() {
            return this.sortBuilder.handler(MenuModel.createTime);
        }

        /**
         * 修改时间
         */
        public SortBuilder<MenuModel, Column, On, Where, Sort, Group> updateTime() {
            return this.sortBuilder.handler(MenuModel.updateTime);
        }

        /**
         * 删除时间
         */
        public SortBuilder<MenuModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.sortBuilder.handler(MenuModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public SortBuilder<MenuModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.sortBuilder.handler(MenuModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public SortBuilder<MenuModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.sortBuilder.handler(MenuModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public SortBuilder<MenuModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.sortBuilder.handler(MenuModel.deleteTimeStamp);
        }

    }

}