package pub.avalonframework.cloud.gar.model;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("all")
public final class TemplateModel implements Model<TemplateModel, TemplateModel.Column, TemplateModel.On, TemplateModel.Where, TemplateModel.Sort, TemplateModel.Group> {

    /**
     * 表名
     */
    public static final String tableName = "gar_template";
    /**
     * 表别名
     */
    public static final String tableAlias = "Template";

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
     * 模板名称
     */
    public static final String name = "name";
    /**
     * 模板名称
     */
    public static final String name_alias = "name";
    /**
     * 模板唯一标识符
     */
    public static final String value = "value";
    /**
     * 模板唯一标识符
     */
    public static final String value_alias = "value";
    /**
     * 模板描述
     */
    public static final String description = "description";
    /**
     * 模板描述
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
     * 模板类型
     */
    public static final String type = "type";
    /**
     * 模板类型
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

    public static final class Column extends ColumnModel<TemplateModel, Column, On, Where, Sort, Group> {

        private Column() {
        }

        /**
         * 主键
         */
        public Column primaryKey() {
            this.addColumnAlias(TemplateModel.primaryKeyName, TemplateModel.primaryKeyAlias);
            return this;
        }

        /**
         * 主键
         * @param alias 别名
         */
        public Column primaryKey(String alias) {
            this.addColumnAlias(TemplateModel.primaryKeyName, alias);
            return this;
        }
    
        /**
         * 主键
         */
        public Column id() {
            this.addColumnAlias(TemplateModel.id, TemplateModel.id_alias);
            return this;
        }

        /**
         * 主键
         * @param alias 别名
         */
        public Column id(String alias) {
            this.addColumnAlias(TemplateModel.id, alias);
            return this;
        }

        /**
         * 所属模块ID
         */
        public Column moduleId() {
            this.addColumnAlias(TemplateModel.moduleId, TemplateModel.moduleId_alias);
            return this;
        }

        /**
         * 所属模块ID
         * @param alias 别名
         */
        public Column moduleId(String alias) {
            this.addColumnAlias(TemplateModel.moduleId, alias);
            return this;
        }

        /**
         * 所属子模块ID
         */
        public Column subModuleId() {
            this.addColumnAlias(TemplateModel.subModuleId, TemplateModel.subModuleId_alias);
            return this;
        }

        /**
         * 所属子模块ID
         * @param alias 别名
         */
        public Column subModuleId(String alias) {
            this.addColumnAlias(TemplateModel.subModuleId, alias);
            return this;
        }

        /**
         * 所属子模块名称
         */
        public Column subModuleName() {
            this.addColumnAlias(TemplateModel.subModuleName, TemplateModel.subModuleName_alias);
            return this;
        }

        /**
         * 所属子模块名称
         * @param alias 别名
         */
        public Column subModuleName(String alias) {
            this.addColumnAlias(TemplateModel.subModuleName, alias);
            return this;
        }

        /**
         * 模板名称
         */
        public Column name() {
            this.addColumnAlias(TemplateModel.name, TemplateModel.name_alias);
            return this;
        }

        /**
         * 模板名称
         * @param alias 别名
         */
        public Column name(String alias) {
            this.addColumnAlias(TemplateModel.name, alias);
            return this;
        }

        /**
         * 模板唯一标识符
         */
        public Column value() {
            this.addColumnAlias(TemplateModel.value, TemplateModel.value_alias);
            return this;
        }

        /**
         * 模板唯一标识符
         * @param alias 别名
         */
        public Column value(String alias) {
            this.addColumnAlias(TemplateModel.value, alias);
            return this;
        }

        /**
         * 模板描述
         */
        public Column description() {
            this.addColumnAlias(TemplateModel.description, TemplateModel.description_alias);
            return this;
        }

        /**
         * 模板描述
         * @param alias 别名
         */
        public Column description(String alias) {
            this.addColumnAlias(TemplateModel.description, alias);
            return this;
        }

        /**
         * 父id
         */
        public Column parentId() {
            this.addColumnAlias(TemplateModel.parentId, TemplateModel.parentId_alias);
            return this;
        }

        /**
         * 父id
         * @param alias 别名
         */
        public Column parentId(String alias) {
            this.addColumnAlias(TemplateModel.parentId, alias);
            return this;
        }

        /**
         * 祖先id
         */
        public Column parentIds() {
            this.addColumnAlias(TemplateModel.parentIds, TemplateModel.parentIds_alias);
            return this;
        }

        /**
         * 祖先id
         * @param alias 别名
         */
        public Column parentIds(String alias) {
            this.addColumnAlias(TemplateModel.parentIds, alias);
            return this;
        }

        /**
         * 模板类型
         */
        public Column type() {
            this.addColumnAlias(TemplateModel.type, TemplateModel.type_alias);
            return this;
        }

        /**
         * 模板类型
         * @param alias 别名
         */
        public Column type(String alias) {
            this.addColumnAlias(TemplateModel.type, alias);
            return this;
        }

        /**
         * 排序号
         */
        public Column index() {
            this.addColumnAlias(TemplateModel.index, TemplateModel.index_alias);
            return this;
        }

        /**
         * 排序号
         * @param alias 别名
         */
        public Column index(String alias) {
            this.addColumnAlias(TemplateModel.index, alias);
            return this;
        }

        /**
         * 状态
         */
        public Column status() {
            this.addColumnAlias(TemplateModel.status, TemplateModel.status_alias);
            return this;
        }

        /**
         * 状态
         * @param alias 别名
         */
        public Column status(String alias) {
            this.addColumnAlias(TemplateModel.status, alias);
            return this;
        }

        /**
         * 创建时间
         */
        public Column createTime() {
            this.addColumnAlias(TemplateModel.createTime, TemplateModel.createTime_alias);
            return this;
        }

        /**
         * 创建时间
         * @param alias 别名
         */
        public Column createTime(String alias) {
            this.addColumnAlias(TemplateModel.createTime, alias);
            return this;
        }

        /**
         * 修改时间
         */
        public Column updateTime() {
            this.addColumnAlias(TemplateModel.updateTime, TemplateModel.updateTime_alias);
            return this;
        }

        /**
         * 修改时间
         * @param alias 别名
         */
        public Column updateTime(String alias) {
            this.addColumnAlias(TemplateModel.updateTime, alias);
            return this;
        }

        /**
         * 删除时间
         */
        public Column deleteTime() {
            this.addColumnAlias(TemplateModel.deleteTime, TemplateModel.deleteTime_alias);
            return this;
        }

        /**
         * 删除时间
         * @param alias 别名
         */
        public Column deleteTime(String alias) {
            this.addColumnAlias(TemplateModel.deleteTime, alias);
            return this;
        }

        /**
         * 创建时间戳
         */
        public Column createTimeStamp() {
            this.addColumnAlias(TemplateModel.createTimeStamp, TemplateModel.createTimeStamp_alias);
            return this;
        }

        /**
         * 创建时间戳
         * @param alias 别名
         */
        public Column createTimeStamp(String alias) {
            this.addColumnAlias(TemplateModel.createTimeStamp, alias);
            return this;
        }

        /**
         * 修改时间戳
         */
        public Column updateTimeStamp() {
            this.addColumnAlias(TemplateModel.updateTimeStamp, TemplateModel.updateTimeStamp_alias);
            return this;
        }

        /**
         * 修改时间戳
         * @param alias 别名
         */
        public Column updateTimeStamp(String alias) {
            this.addColumnAlias(TemplateModel.updateTimeStamp, alias);
            return this;
        }

        /**
         * 删除时间戳
         */
        public Column deleteTimeStamp() {
            this.addColumnAlias(TemplateModel.deleteTimeStamp, TemplateModel.deleteTimeStamp_alias);
            return this;
        }

        /**
         * 删除时间戳
         * @param alias 别名
         */
        public Column deleteTimeStamp(String alias) {
            this.addColumnAlias(TemplateModel.deleteTimeStamp, alias);
            return this;
        }

    }

    @Override
    public On getOnModel() {
        return new On();
    }

    public static final class On extends OnModel<TemplateModel, Column, On, Where, Sort, Group> {

        private On() {
        }

        /**
         * 主键
         */
        public OnBuilder<TemplateModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.onBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.primaryKeyName);
        }
    
        /**
         * 主键
         */
        public OnBuilder<TemplateModel, Column, On, Where, Sort, Group> id() {
            return this.onBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.id);
        }

        /**
         * 所属模块ID
         */
        public OnBuilder<TemplateModel, Column, On, Where, Sort, Group> moduleId() {
            return this.onBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.moduleId);
        }

        /**
         * 所属子模块ID
         */
        public OnBuilder<TemplateModel, Column, On, Where, Sort, Group> subModuleId() {
            return this.onBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.subModuleId);
        }

        /**
         * 所属子模块名称
         */
        public OnBuilder<TemplateModel, Column, On, Where, Sort, Group> subModuleName() {
            return this.onBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.subModuleName);
        }

        /**
         * 模板名称
         */
        public OnBuilder<TemplateModel, Column, On, Where, Sort, Group> name() {
            return this.onBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.name);
        }

        /**
         * 模板唯一标识符
         */
        public OnBuilder<TemplateModel, Column, On, Where, Sort, Group> value() {
            return this.onBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.value);
        }

        /**
         * 模板描述
         */
        public OnBuilder<TemplateModel, Column, On, Where, Sort, Group> description() {
            return this.onBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.description);
        }

        /**
         * 父id
         */
        public OnBuilder<TemplateModel, Column, On, Where, Sort, Group> parentId() {
            return this.onBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.parentId);
        }

        /**
         * 祖先id
         */
        public OnBuilder<TemplateModel, Column, On, Where, Sort, Group> parentIds() {
            return this.onBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.parentIds);
        }

        /**
         * 模板类型
         */
        public OnBuilder<TemplateModel, Column, On, Where, Sort, Group> type() {
            return this.onBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.type);
        }

        /**
         * 排序号
         */
        public OnBuilder<TemplateModel, Column, On, Where, Sort, Group> index() {
            return this.onBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.index);
        }

        /**
         * 状态
         */
        public OnBuilder<TemplateModel, Column, On, Where, Sort, Group> status() {
            return this.onBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.status);
        }

        /**
         * 创建时间
         */
        public OnBuilder<TemplateModel, Column, On, Where, Sort, Group> createTime() {
            return this.onBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.createTime);
        }

        /**
         * 修改时间
         */
        public OnBuilder<TemplateModel, Column, On, Where, Sort, Group> updateTime() {
            return this.onBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.updateTime);
        }

        /**
         * 删除时间
         */
        public OnBuilder<TemplateModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.onBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public OnBuilder<TemplateModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.onBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public OnBuilder<TemplateModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.onBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public OnBuilder<TemplateModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.onBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.deleteTimeStamp);
        }

    }

    @Override
    public Where getWhereModel() {
        return new Where();
    }

    public static final class Where extends WhereModel<TemplateModel, Column, On, Where, Sort, Group> {

        private Where() {
        }

        /**
         * 主键
         */
        public WhereBuilder<TemplateModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.whereBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.primaryKeyName);
        }
    
        /**
         * 主键
         */
        public WhereBuilder<TemplateModel, Column, On, Where, Sort, Group> id() {
            return this.whereBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.id);
        }

        /**
         * 所属模块ID
         */
        public WhereBuilder<TemplateModel, Column, On, Where, Sort, Group> moduleId() {
            return this.whereBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.moduleId);
        }

        /**
         * 所属子模块ID
         */
        public WhereBuilder<TemplateModel, Column, On, Where, Sort, Group> subModuleId() {
            return this.whereBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.subModuleId);
        }

        /**
         * 所属子模块名称
         */
        public WhereBuilder<TemplateModel, Column, On, Where, Sort, Group> subModuleName() {
            return this.whereBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.subModuleName);
        }

        /**
         * 模板名称
         */
        public WhereBuilder<TemplateModel, Column, On, Where, Sort, Group> name() {
            return this.whereBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.name);
        }

        /**
         * 模板唯一标识符
         */
        public WhereBuilder<TemplateModel, Column, On, Where, Sort, Group> value() {
            return this.whereBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.value);
        }

        /**
         * 模板描述
         */
        public WhereBuilder<TemplateModel, Column, On, Where, Sort, Group> description() {
            return this.whereBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.description);
        }

        /**
         * 父id
         */
        public WhereBuilder<TemplateModel, Column, On, Where, Sort, Group> parentId() {
            return this.whereBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.parentId);
        }

        /**
         * 祖先id
         */
        public WhereBuilder<TemplateModel, Column, On, Where, Sort, Group> parentIds() {
            return this.whereBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.parentIds);
        }

        /**
         * 模板类型
         */
        public WhereBuilder<TemplateModel, Column, On, Where, Sort, Group> type() {
            return this.whereBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.type);
        }

        /**
         * 排序号
         */
        public WhereBuilder<TemplateModel, Column, On, Where, Sort, Group> index() {
            return this.whereBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.index);
        }

        /**
         * 状态
         */
        public WhereBuilder<TemplateModel, Column, On, Where, Sort, Group> status() {
            return this.whereBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.status);
        }

        /**
         * 创建时间
         */
        public WhereBuilder<TemplateModel, Column, On, Where, Sort, Group> createTime() {
            return this.whereBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.createTime);
        }

        /**
         * 修改时间
         */
        public WhereBuilder<TemplateModel, Column, On, Where, Sort, Group> updateTime() {
            return this.whereBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.updateTime);
        }

        /**
         * 删除时间
         */
        public WhereBuilder<TemplateModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.whereBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public WhereBuilder<TemplateModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.whereBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public WhereBuilder<TemplateModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.whereBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public WhereBuilder<TemplateModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.whereBuilder.handler(TemplateModel.tableName, TemplateModel.tableAlias, TemplateModel.deleteTimeStamp);
        }

    }

    @Override
    public Group getGroupModel() {
        return new Group();
    }

    public static final class Group extends GroupModel<TemplateModel, Column, On, Where, Sort, Group> {

        private Group() {
        }

        /**
         * 主键
         */
        public Group primaryKey() {
            this.addColumn(TemplateModel.primaryKeyName);
            return this;
        }
    
        /**
         * 主键
         */
        public Group id() {
            this.addColumn(TemplateModel.id);
            return this;
        }

        /**
         * 所属模块ID
         */
        public Group moduleId() {
            this.addColumn(TemplateModel.moduleId);
            return this;
        }

        /**
         * 所属子模块ID
         */
        public Group subModuleId() {
            this.addColumn(TemplateModel.subModuleId);
            return this;
        }

        /**
         * 所属子模块名称
         */
        public Group subModuleName() {
            this.addColumn(TemplateModel.subModuleName);
            return this;
        }

        /**
         * 模板名称
         */
        public Group name() {
            this.addColumn(TemplateModel.name);
            return this;
        }

        /**
         * 模板唯一标识符
         */
        public Group value() {
            this.addColumn(TemplateModel.value);
            return this;
        }

        /**
         * 模板描述
         */
        public Group description() {
            this.addColumn(TemplateModel.description);
            return this;
        }

        /**
         * 父id
         */
        public Group parentId() {
            this.addColumn(TemplateModel.parentId);
            return this;
        }

        /**
         * 祖先id
         */
        public Group parentIds() {
            this.addColumn(TemplateModel.parentIds);
            return this;
        }

        /**
         * 模板类型
         */
        public Group type() {
            this.addColumn(TemplateModel.type);
            return this;
        }

        /**
         * 排序号
         */
        public Group index() {
            this.addColumn(TemplateModel.index);
            return this;
        }

        /**
         * 状态
         */
        public Group status() {
            this.addColumn(TemplateModel.status);
            return this;
        }

        /**
         * 创建时间
         */
        public Group createTime() {
            this.addColumn(TemplateModel.createTime);
            return this;
        }

        /**
         * 修改时间
         */
        public Group updateTime() {
            this.addColumn(TemplateModel.updateTime);
            return this;
        }

        /**
         * 删除时间
         */
        public Group deleteTime() {
            this.addColumn(TemplateModel.deleteTime);
            return this;
        }

        /**
         * 创建时间戳
         */
        public Group createTimeStamp() {
            this.addColumn(TemplateModel.createTimeStamp);
            return this;
        }

        /**
         * 修改时间戳
         */
        public Group updateTimeStamp() {
            this.addColumn(TemplateModel.updateTimeStamp);
            return this;
        }

        /**
         * 删除时间戳
         */
        public Group deleteTimeStamp() {
            this.addColumn(TemplateModel.deleteTimeStamp);
            return this;
        }

    }

    @Override
    public Sort getSortModel() {
        return new Sort();
    }

    public static final class Sort extends SortModel<TemplateModel, Column, On, Where, Sort, Group> {

        private Sort() {
        }

        /**
         * 主键
         */
        public SortBuilder<TemplateModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.sortBuilder.handler(TemplateModel.primaryKeyName);
        }
    
        /**
         * 主键
         */
        public SortBuilder<TemplateModel, Column, On, Where, Sort, Group> id() {
            return this.sortBuilder.handler(TemplateModel.id);
        }

        /**
         * 所属模块ID
         */
        public SortBuilder<TemplateModel, Column, On, Where, Sort, Group> moduleId() {
            return this.sortBuilder.handler(TemplateModel.moduleId);
        }

        /**
         * 所属子模块ID
         */
        public SortBuilder<TemplateModel, Column, On, Where, Sort, Group> subModuleId() {
            return this.sortBuilder.handler(TemplateModel.subModuleId);
        }

        /**
         * 所属子模块名称
         */
        public SortBuilder<TemplateModel, Column, On, Where, Sort, Group> subModuleName() {
            return this.sortBuilder.handler(TemplateModel.subModuleName);
        }

        /**
         * 模板名称
         */
        public SortBuilder<TemplateModel, Column, On, Where, Sort, Group> name() {
            return this.sortBuilder.handler(TemplateModel.name);
        }

        /**
         * 模板唯一标识符
         */
        public SortBuilder<TemplateModel, Column, On, Where, Sort, Group> value() {
            return this.sortBuilder.handler(TemplateModel.value);
        }

        /**
         * 模板描述
         */
        public SortBuilder<TemplateModel, Column, On, Where, Sort, Group> description() {
            return this.sortBuilder.handler(TemplateModel.description);
        }

        /**
         * 父id
         */
        public SortBuilder<TemplateModel, Column, On, Where, Sort, Group> parentId() {
            return this.sortBuilder.handler(TemplateModel.parentId);
        }

        /**
         * 祖先id
         */
        public SortBuilder<TemplateModel, Column, On, Where, Sort, Group> parentIds() {
            return this.sortBuilder.handler(TemplateModel.parentIds);
        }

        /**
         * 模板类型
         */
        public SortBuilder<TemplateModel, Column, On, Where, Sort, Group> type() {
            return this.sortBuilder.handler(TemplateModel.type);
        }

        /**
         * 排序号
         */
        public SortBuilder<TemplateModel, Column, On, Where, Sort, Group> index() {
            return this.sortBuilder.handler(TemplateModel.index);
        }

        /**
         * 状态
         */
        public SortBuilder<TemplateModel, Column, On, Where, Sort, Group> status() {
            return this.sortBuilder.handler(TemplateModel.status);
        }

        /**
         * 创建时间
         */
        public SortBuilder<TemplateModel, Column, On, Where, Sort, Group> createTime() {
            return this.sortBuilder.handler(TemplateModel.createTime);
        }

        /**
         * 修改时间
         */
        public SortBuilder<TemplateModel, Column, On, Where, Sort, Group> updateTime() {
            return this.sortBuilder.handler(TemplateModel.updateTime);
        }

        /**
         * 删除时间
         */
        public SortBuilder<TemplateModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.sortBuilder.handler(TemplateModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public SortBuilder<TemplateModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.sortBuilder.handler(TemplateModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public SortBuilder<TemplateModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.sortBuilder.handler(TemplateModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public SortBuilder<TemplateModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.sortBuilder.handler(TemplateModel.deleteTimeStamp);
        }

    }

}