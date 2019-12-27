package pub.avalonframework.cloud.gar.model;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("all")
public final class SubModuleModel implements Model<SubModuleModel, SubModuleModel.Column, SubModuleModel.On, SubModuleModel.Where, SubModuleModel.Sort, SubModuleModel.Group> {

    /**
     * 表名
     */
    public static final String tableName = "gar_sub_module";
    /**
     * 表别名
     */
    public static final String tableAlias = "SubModule";

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
     * 所属模块名称
     */
    public static final String moduleName = "module_name";
    /**
     * 所属模块名称
     */
    public static final String moduleName_alias = "moduleName";
    /**
     * 路由名称
     */
    public static final String name = "name";
    /**
     * 路由名称
     */
    public static final String name_alias = "name";
    /**
     * 子模块唯一标识符
     */
    public static final String value = "value";
    /**
     * 子模块唯一标识符
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
        COLUMN_ALIAS_MAP.put(moduleName, moduleName_alias);
        ALIAS_COLUMN_MAP.put(moduleName_alias, moduleName);
        COLUMN_ALIAS_MAP.put(name, name_alias);
        ALIAS_COLUMN_MAP.put(name_alias, name);
        COLUMN_ALIAS_MAP.put(value, value_alias);
        ALIAS_COLUMN_MAP.put(value_alias, value);
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

    public static final class Column extends ColumnModel<SubModuleModel, Column, On, Where, Sort, Group> {

        private Column() {
        }

        /**
         * 主键
         */
        public Column primaryKey() {
            this.addColumnAlias(SubModuleModel.primaryKeyName, SubModuleModel.primaryKeyAlias);
            return this;
        }

        /**
         * 主键
         * @param alias 别名
         */
        public Column primaryKey(String alias) {
            this.addColumnAlias(SubModuleModel.primaryKeyName, alias);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Column id() {
            this.addColumnAlias(SubModuleModel.id, SubModuleModel.id_alias);
            return this;
        }

        /**
         * 主键ID
         * @param alias 别名
         */
        public Column id(String alias) {
            this.addColumnAlias(SubModuleModel.id, alias);
            return this;
        }

        /**
         * 所属模块ID
         */
        public Column moduleId() {
            this.addColumnAlias(SubModuleModel.moduleId, SubModuleModel.moduleId_alias);
            return this;
        }

        /**
         * 所属模块ID
         * @param alias 别名
         */
        public Column moduleId(String alias) {
            this.addColumnAlias(SubModuleModel.moduleId, alias);
            return this;
        }

        /**
         * 所属模块名称
         */
        public Column moduleName() {
            this.addColumnAlias(SubModuleModel.moduleName, SubModuleModel.moduleName_alias);
            return this;
        }

        /**
         * 所属模块名称
         * @param alias 别名
         */
        public Column moduleName(String alias) {
            this.addColumnAlias(SubModuleModel.moduleName, alias);
            return this;
        }

        /**
         * 路由名称
         */
        public Column name() {
            this.addColumnAlias(SubModuleModel.name, SubModuleModel.name_alias);
            return this;
        }

        /**
         * 路由名称
         * @param alias 别名
         */
        public Column name(String alias) {
            this.addColumnAlias(SubModuleModel.name, alias);
            return this;
        }

        /**
         * 子模块唯一标识符
         */
        public Column value() {
            this.addColumnAlias(SubModuleModel.value, SubModuleModel.value_alias);
            return this;
        }

        /**
         * 子模块唯一标识符
         * @param alias 别名
         */
        public Column value(String alias) {
            this.addColumnAlias(SubModuleModel.value, alias);
            return this;
        }

        /**
         * 模块描述
         */
        public Column description() {
            this.addColumnAlias(SubModuleModel.description, SubModuleModel.description_alias);
            return this;
        }

        /**
         * 模块描述
         * @param alias 别名
         */
        public Column description(String alias) {
            this.addColumnAlias(SubModuleModel.description, alias);
            return this;
        }

        /**
         * 排序号
         */
        public Column index() {
            this.addColumnAlias(SubModuleModel.index, SubModuleModel.index_alias);
            return this;
        }

        /**
         * 排序号
         * @param alias 别名
         */
        public Column index(String alias) {
            this.addColumnAlias(SubModuleModel.index, alias);
            return this;
        }

        /**
         * 状态
         */
        public Column status() {
            this.addColumnAlias(SubModuleModel.status, SubModuleModel.status_alias);
            return this;
        }

        /**
         * 状态
         * @param alias 别名
         */
        public Column status(String alias) {
            this.addColumnAlias(SubModuleModel.status, alias);
            return this;
        }

        /**
         * 创建时间
         */
        public Column createTime() {
            this.addColumnAlias(SubModuleModel.createTime, SubModuleModel.createTime_alias);
            return this;
        }

        /**
         * 创建时间
         * @param alias 别名
         */
        public Column createTime(String alias) {
            this.addColumnAlias(SubModuleModel.createTime, alias);
            return this;
        }

        /**
         * 修改时间
         */
        public Column updateTime() {
            this.addColumnAlias(SubModuleModel.updateTime, SubModuleModel.updateTime_alias);
            return this;
        }

        /**
         * 修改时间
         * @param alias 别名
         */
        public Column updateTime(String alias) {
            this.addColumnAlias(SubModuleModel.updateTime, alias);
            return this;
        }

        /**
         * 删除时间
         */
        public Column deleteTime() {
            this.addColumnAlias(SubModuleModel.deleteTime, SubModuleModel.deleteTime_alias);
            return this;
        }

        /**
         * 删除时间
         * @param alias 别名
         */
        public Column deleteTime(String alias) {
            this.addColumnAlias(SubModuleModel.deleteTime, alias);
            return this;
        }

        /**
         * 创建时间戳
         */
        public Column createTimeStamp() {
            this.addColumnAlias(SubModuleModel.createTimeStamp, SubModuleModel.createTimeStamp_alias);
            return this;
        }

        /**
         * 创建时间戳
         * @param alias 别名
         */
        public Column createTimeStamp(String alias) {
            this.addColumnAlias(SubModuleModel.createTimeStamp, alias);
            return this;
        }

        /**
         * 修改时间戳
         */
        public Column updateTimeStamp() {
            this.addColumnAlias(SubModuleModel.updateTimeStamp, SubModuleModel.updateTimeStamp_alias);
            return this;
        }

        /**
         * 修改时间戳
         * @param alias 别名
         */
        public Column updateTimeStamp(String alias) {
            this.addColumnAlias(SubModuleModel.updateTimeStamp, alias);
            return this;
        }

        /**
         * 删除时间戳
         */
        public Column deleteTimeStamp() {
            this.addColumnAlias(SubModuleModel.deleteTimeStamp, SubModuleModel.deleteTimeStamp_alias);
            return this;
        }

        /**
         * 删除时间戳
         * @param alias 别名
         */
        public Column deleteTimeStamp(String alias) {
            this.addColumnAlias(SubModuleModel.deleteTimeStamp, alias);
            return this;
        }

    }

    @Override
    public On getOnModel() {
        return new On();
    }

    public static final class On extends OnModel<SubModuleModel, Column, On, Where, Sort, Group> {

        private On() {
        }

        /**
         * 主键
         */
        public OnBuilder<SubModuleModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.onBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public OnBuilder<SubModuleModel, Column, On, Where, Sort, Group> id() {
            return this.onBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.id);
        }

        /**
         * 所属模块ID
         */
        public OnBuilder<SubModuleModel, Column, On, Where, Sort, Group> moduleId() {
            return this.onBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.moduleId);
        }

        /**
         * 所属模块名称
         */
        public OnBuilder<SubModuleModel, Column, On, Where, Sort, Group> moduleName() {
            return this.onBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.moduleName);
        }

        /**
         * 路由名称
         */
        public OnBuilder<SubModuleModel, Column, On, Where, Sort, Group> name() {
            return this.onBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.name);
        }

        /**
         * 子模块唯一标识符
         */
        public OnBuilder<SubModuleModel, Column, On, Where, Sort, Group> value() {
            return this.onBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.value);
        }

        /**
         * 模块描述
         */
        public OnBuilder<SubModuleModel, Column, On, Where, Sort, Group> description() {
            return this.onBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.description);
        }

        /**
         * 排序号
         */
        public OnBuilder<SubModuleModel, Column, On, Where, Sort, Group> index() {
            return this.onBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.index);
        }

        /**
         * 状态
         */
        public OnBuilder<SubModuleModel, Column, On, Where, Sort, Group> status() {
            return this.onBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.status);
        }

        /**
         * 创建时间
         */
        public OnBuilder<SubModuleModel, Column, On, Where, Sort, Group> createTime() {
            return this.onBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.createTime);
        }

        /**
         * 修改时间
         */
        public OnBuilder<SubModuleModel, Column, On, Where, Sort, Group> updateTime() {
            return this.onBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.updateTime);
        }

        /**
         * 删除时间
         */
        public OnBuilder<SubModuleModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.onBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public OnBuilder<SubModuleModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.onBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public OnBuilder<SubModuleModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.onBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public OnBuilder<SubModuleModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.onBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.deleteTimeStamp);
        }

    }

    @Override
    public Where getWhereModel() {
        return new Where();
    }

    public static final class Where extends WhereModel<SubModuleModel, Column, On, Where, Sort, Group> {

        private Where() {
        }

        /**
         * 主键
         */
        public WhereBuilder<SubModuleModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.whereBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public WhereBuilder<SubModuleModel, Column, On, Where, Sort, Group> id() {
            return this.whereBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.id);
        }

        /**
         * 所属模块ID
         */
        public WhereBuilder<SubModuleModel, Column, On, Where, Sort, Group> moduleId() {
            return this.whereBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.moduleId);
        }

        /**
         * 所属模块名称
         */
        public WhereBuilder<SubModuleModel, Column, On, Where, Sort, Group> moduleName() {
            return this.whereBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.moduleName);
        }

        /**
         * 路由名称
         */
        public WhereBuilder<SubModuleModel, Column, On, Where, Sort, Group> name() {
            return this.whereBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.name);
        }

        /**
         * 子模块唯一标识符
         */
        public WhereBuilder<SubModuleModel, Column, On, Where, Sort, Group> value() {
            return this.whereBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.value);
        }

        /**
         * 模块描述
         */
        public WhereBuilder<SubModuleModel, Column, On, Where, Sort, Group> description() {
            return this.whereBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.description);
        }

        /**
         * 排序号
         */
        public WhereBuilder<SubModuleModel, Column, On, Where, Sort, Group> index() {
            return this.whereBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.index);
        }

        /**
         * 状态
         */
        public WhereBuilder<SubModuleModel, Column, On, Where, Sort, Group> status() {
            return this.whereBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.status);
        }

        /**
         * 创建时间
         */
        public WhereBuilder<SubModuleModel, Column, On, Where, Sort, Group> createTime() {
            return this.whereBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.createTime);
        }

        /**
         * 修改时间
         */
        public WhereBuilder<SubModuleModel, Column, On, Where, Sort, Group> updateTime() {
            return this.whereBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.updateTime);
        }

        /**
         * 删除时间
         */
        public WhereBuilder<SubModuleModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.whereBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public WhereBuilder<SubModuleModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.whereBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public WhereBuilder<SubModuleModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.whereBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public WhereBuilder<SubModuleModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.whereBuilder.handler(SubModuleModel.tableName, SubModuleModel.tableAlias, SubModuleModel.deleteTimeStamp);
        }

    }

    @Override
    public Group getGroupModel() {
        return new Group();
    }

    public static final class Group extends GroupModel<SubModuleModel, Column, On, Where, Sort, Group> {

        private Group() {
        }

        /**
         * 主键
         */
        public Group primaryKey() {
            this.addColumn(SubModuleModel.primaryKeyName);
            return this;
        }
    
        /**
         * 主键ID
         */
        public Group id() {
            this.addColumn(SubModuleModel.id);
            return this;
        }

        /**
         * 所属模块ID
         */
        public Group moduleId() {
            this.addColumn(SubModuleModel.moduleId);
            return this;
        }

        /**
         * 所属模块名称
         */
        public Group moduleName() {
            this.addColumn(SubModuleModel.moduleName);
            return this;
        }

        /**
         * 路由名称
         */
        public Group name() {
            this.addColumn(SubModuleModel.name);
            return this;
        }

        /**
         * 子模块唯一标识符
         */
        public Group value() {
            this.addColumn(SubModuleModel.value);
            return this;
        }

        /**
         * 模块描述
         */
        public Group description() {
            this.addColumn(SubModuleModel.description);
            return this;
        }

        /**
         * 排序号
         */
        public Group index() {
            this.addColumn(SubModuleModel.index);
            return this;
        }

        /**
         * 状态
         */
        public Group status() {
            this.addColumn(SubModuleModel.status);
            return this;
        }

        /**
         * 创建时间
         */
        public Group createTime() {
            this.addColumn(SubModuleModel.createTime);
            return this;
        }

        /**
         * 修改时间
         */
        public Group updateTime() {
            this.addColumn(SubModuleModel.updateTime);
            return this;
        }

        /**
         * 删除时间
         */
        public Group deleteTime() {
            this.addColumn(SubModuleModel.deleteTime);
            return this;
        }

        /**
         * 创建时间戳
         */
        public Group createTimeStamp() {
            this.addColumn(SubModuleModel.createTimeStamp);
            return this;
        }

        /**
         * 修改时间戳
         */
        public Group updateTimeStamp() {
            this.addColumn(SubModuleModel.updateTimeStamp);
            return this;
        }

        /**
         * 删除时间戳
         */
        public Group deleteTimeStamp() {
            this.addColumn(SubModuleModel.deleteTimeStamp);
            return this;
        }

    }

    @Override
    public Sort getSortModel() {
        return new Sort();
    }

    public static final class Sort extends SortModel<SubModuleModel, Column, On, Where, Sort, Group> {

        private Sort() {
        }

        /**
         * 主键
         */
        public SortBuilder<SubModuleModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.sortBuilder.handler(SubModuleModel.primaryKeyName);
        }
    
        /**
         * 主键ID
         */
        public SortBuilder<SubModuleModel, Column, On, Where, Sort, Group> id() {
            return this.sortBuilder.handler(SubModuleModel.id);
        }

        /**
         * 所属模块ID
         */
        public SortBuilder<SubModuleModel, Column, On, Where, Sort, Group> moduleId() {
            return this.sortBuilder.handler(SubModuleModel.moduleId);
        }

        /**
         * 所属模块名称
         */
        public SortBuilder<SubModuleModel, Column, On, Where, Sort, Group> moduleName() {
            return this.sortBuilder.handler(SubModuleModel.moduleName);
        }

        /**
         * 路由名称
         */
        public SortBuilder<SubModuleModel, Column, On, Where, Sort, Group> name() {
            return this.sortBuilder.handler(SubModuleModel.name);
        }

        /**
         * 子模块唯一标识符
         */
        public SortBuilder<SubModuleModel, Column, On, Where, Sort, Group> value() {
            return this.sortBuilder.handler(SubModuleModel.value);
        }

        /**
         * 模块描述
         */
        public SortBuilder<SubModuleModel, Column, On, Where, Sort, Group> description() {
            return this.sortBuilder.handler(SubModuleModel.description);
        }

        /**
         * 排序号
         */
        public SortBuilder<SubModuleModel, Column, On, Where, Sort, Group> index() {
            return this.sortBuilder.handler(SubModuleModel.index);
        }

        /**
         * 状态
         */
        public SortBuilder<SubModuleModel, Column, On, Where, Sort, Group> status() {
            return this.sortBuilder.handler(SubModuleModel.status);
        }

        /**
         * 创建时间
         */
        public SortBuilder<SubModuleModel, Column, On, Where, Sort, Group> createTime() {
            return this.sortBuilder.handler(SubModuleModel.createTime);
        }

        /**
         * 修改时间
         */
        public SortBuilder<SubModuleModel, Column, On, Where, Sort, Group> updateTime() {
            return this.sortBuilder.handler(SubModuleModel.updateTime);
        }

        /**
         * 删除时间
         */
        public SortBuilder<SubModuleModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.sortBuilder.handler(SubModuleModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public SortBuilder<SubModuleModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.sortBuilder.handler(SubModuleModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public SortBuilder<SubModuleModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.sortBuilder.handler(SubModuleModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public SortBuilder<SubModuleModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.sortBuilder.handler(SubModuleModel.deleteTimeStamp);
        }

    }

}