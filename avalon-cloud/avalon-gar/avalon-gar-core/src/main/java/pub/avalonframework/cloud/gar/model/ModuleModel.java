package pub.avalonframework.cloud.gar.model;

import pub.avalon.sqlhelper.core.beans.*;
import pub.avalon.sqlhelper.core.norm.Model;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("all")
public final class ModuleModel implements Model<ModuleModel, ModuleModel.Column, ModuleModel.On, ModuleModel.Where, ModuleModel.Sort, ModuleModel.Group> {

    /**
     * 表名
     */
    public static final String tableName = "gar_module";
    /**
     * 表别名
     */
    public static final String tableAlias = "Module";

    /**
     * 主键名
     */
    public static final String primaryKeyName = "id";
    /**
     * 主键别名
     */
    public static final String primaryKeyAlias = "id";



    /**
     * 模块ID
     */
    public static final String id = "id";
    /**
     * 模块ID
     */
    public static final String id_alias = "id";
    /**
     * 网关地址
     */
    public static final String path = "path";
    /**
     * 网关地址
     */
    public static final String path_alias = "path";
    /**
     * 服务ID
     */
    public static final String serviceId = "service_id";
    /**
     * 服务ID
     */
    public static final String serviceId_alias = "serviceId";
    /**
     * 服务名称
     */
    public static final String serviceName = "service_name";
    /**
     * 服务名称
     */
    public static final String serviceName_alias = "serviceName";
    /**
     * 地址
     */
    public static final String url = "url";
    /**
     * 地址
     */
    public static final String url_alias = "url";
    /**
     * 登录地址
     */
    public static final String loginUrl = "login_url";
    /**
     * 登录地址
     */
    public static final String loginUrl_alias = "loginUrl";
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
        COLUMN_ALIAS_MAP.put(path, path_alias);
        ALIAS_COLUMN_MAP.put(path_alias, path);
        COLUMN_ALIAS_MAP.put(serviceId, serviceId_alias);
        ALIAS_COLUMN_MAP.put(serviceId_alias, serviceId);
        COLUMN_ALIAS_MAP.put(serviceName, serviceName_alias);
        ALIAS_COLUMN_MAP.put(serviceName_alias, serviceName);
        COLUMN_ALIAS_MAP.put(url, url_alias);
        ALIAS_COLUMN_MAP.put(url_alias, url);
        COLUMN_ALIAS_MAP.put(loginUrl, loginUrl_alias);
        ALIAS_COLUMN_MAP.put(loginUrl_alias, loginUrl);
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

    public static final class Column extends ColumnModel<ModuleModel, Column, On, Where, Sort, Group> {

        private Column() {
        }

        /**
         * 主键
         */
        public Column primaryKey() {
            this.addColumnAlias(ModuleModel.primaryKeyName, ModuleModel.primaryKeyAlias);
            return this;
        }

        /**
         * 主键
         * @param alias 别名
         */
        public Column primaryKey(String alias) {
            this.addColumnAlias(ModuleModel.primaryKeyName, alias);
            return this;
        }
    
        /**
         * 模块ID
         */
        public Column id() {
            this.addColumnAlias(ModuleModel.id, ModuleModel.id_alias);
            return this;
        }

        /**
         * 模块ID
         * @param alias 别名
         */
        public Column id(String alias) {
            this.addColumnAlias(ModuleModel.id, alias);
            return this;
        }

        /**
         * 网关地址
         */
        public Column path() {
            this.addColumnAlias(ModuleModel.path, ModuleModel.path_alias);
            return this;
        }

        /**
         * 网关地址
         * @param alias 别名
         */
        public Column path(String alias) {
            this.addColumnAlias(ModuleModel.path, alias);
            return this;
        }

        /**
         * 服务ID
         */
        public Column serviceId() {
            this.addColumnAlias(ModuleModel.serviceId, ModuleModel.serviceId_alias);
            return this;
        }

        /**
         * 服务ID
         * @param alias 别名
         */
        public Column serviceId(String alias) {
            this.addColumnAlias(ModuleModel.serviceId, alias);
            return this;
        }

        /**
         * 服务名称
         */
        public Column serviceName() {
            this.addColumnAlias(ModuleModel.serviceName, ModuleModel.serviceName_alias);
            return this;
        }

        /**
         * 服务名称
         * @param alias 别名
         */
        public Column serviceName(String alias) {
            this.addColumnAlias(ModuleModel.serviceName, alias);
            return this;
        }

        /**
         * 地址
         */
        public Column url() {
            this.addColumnAlias(ModuleModel.url, ModuleModel.url_alias);
            return this;
        }

        /**
         * 地址
         * @param alias 别名
         */
        public Column url(String alias) {
            this.addColumnAlias(ModuleModel.url, alias);
            return this;
        }

        /**
         * 登录地址
         */
        public Column loginUrl() {
            this.addColumnAlias(ModuleModel.loginUrl, ModuleModel.loginUrl_alias);
            return this;
        }

        /**
         * 登录地址
         * @param alias 别名
         */
        public Column loginUrl(String alias) {
            this.addColumnAlias(ModuleModel.loginUrl, alias);
            return this;
        }

        /**
         * 排序号
         */
        public Column index() {
            this.addColumnAlias(ModuleModel.index, ModuleModel.index_alias);
            return this;
        }

        /**
         * 排序号
         * @param alias 别名
         */
        public Column index(String alias) {
            this.addColumnAlias(ModuleModel.index, alias);
            return this;
        }

        /**
         * 状态
         */
        public Column status() {
            this.addColumnAlias(ModuleModel.status, ModuleModel.status_alias);
            return this;
        }

        /**
         * 状态
         * @param alias 别名
         */
        public Column status(String alias) {
            this.addColumnAlias(ModuleModel.status, alias);
            return this;
        }

        /**
         * 创建时间
         */
        public Column createTime() {
            this.addColumnAlias(ModuleModel.createTime, ModuleModel.createTime_alias);
            return this;
        }

        /**
         * 创建时间
         * @param alias 别名
         */
        public Column createTime(String alias) {
            this.addColumnAlias(ModuleModel.createTime, alias);
            return this;
        }

        /**
         * 修改时间
         */
        public Column updateTime() {
            this.addColumnAlias(ModuleModel.updateTime, ModuleModel.updateTime_alias);
            return this;
        }

        /**
         * 修改时间
         * @param alias 别名
         */
        public Column updateTime(String alias) {
            this.addColumnAlias(ModuleModel.updateTime, alias);
            return this;
        }

        /**
         * 删除时间
         */
        public Column deleteTime() {
            this.addColumnAlias(ModuleModel.deleteTime, ModuleModel.deleteTime_alias);
            return this;
        }

        /**
         * 删除时间
         * @param alias 别名
         */
        public Column deleteTime(String alias) {
            this.addColumnAlias(ModuleModel.deleteTime, alias);
            return this;
        }

        /**
         * 创建时间戳
         */
        public Column createTimeStamp() {
            this.addColumnAlias(ModuleModel.createTimeStamp, ModuleModel.createTimeStamp_alias);
            return this;
        }

        /**
         * 创建时间戳
         * @param alias 别名
         */
        public Column createTimeStamp(String alias) {
            this.addColumnAlias(ModuleModel.createTimeStamp, alias);
            return this;
        }

        /**
         * 修改时间戳
         */
        public Column updateTimeStamp() {
            this.addColumnAlias(ModuleModel.updateTimeStamp, ModuleModel.updateTimeStamp_alias);
            return this;
        }

        /**
         * 修改时间戳
         * @param alias 别名
         */
        public Column updateTimeStamp(String alias) {
            this.addColumnAlias(ModuleModel.updateTimeStamp, alias);
            return this;
        }

        /**
         * 删除时间戳
         */
        public Column deleteTimeStamp() {
            this.addColumnAlias(ModuleModel.deleteTimeStamp, ModuleModel.deleteTimeStamp_alias);
            return this;
        }

        /**
         * 删除时间戳
         * @param alias 别名
         */
        public Column deleteTimeStamp(String alias) {
            this.addColumnAlias(ModuleModel.deleteTimeStamp, alias);
            return this;
        }

    }

    @Override
    public On getOnModel() {
        return new On();
    }

    public static final class On extends OnModel<ModuleModel, Column, On, Where, Sort, Group> {

        private On() {
        }

        /**
         * 主键
         */
        public OnBuilder<ModuleModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.onBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.primaryKeyName);
        }
    
        /**
         * 模块ID
         */
        public OnBuilder<ModuleModel, Column, On, Where, Sort, Group> id() {
            return this.onBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.id);
        }

        /**
         * 网关地址
         */
        public OnBuilder<ModuleModel, Column, On, Where, Sort, Group> path() {
            return this.onBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.path);
        }

        /**
         * 服务ID
         */
        public OnBuilder<ModuleModel, Column, On, Where, Sort, Group> serviceId() {
            return this.onBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.serviceId);
        }

        /**
         * 服务名称
         */
        public OnBuilder<ModuleModel, Column, On, Where, Sort, Group> serviceName() {
            return this.onBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.serviceName);
        }

        /**
         * 地址
         */
        public OnBuilder<ModuleModel, Column, On, Where, Sort, Group> url() {
            return this.onBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.url);
        }

        /**
         * 登录地址
         */
        public OnBuilder<ModuleModel, Column, On, Where, Sort, Group> loginUrl() {
            return this.onBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.loginUrl);
        }

        /**
         * 排序号
         */
        public OnBuilder<ModuleModel, Column, On, Where, Sort, Group> index() {
            return this.onBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.index);
        }

        /**
         * 状态
         */
        public OnBuilder<ModuleModel, Column, On, Where, Sort, Group> status() {
            return this.onBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.status);
        }

        /**
         * 创建时间
         */
        public OnBuilder<ModuleModel, Column, On, Where, Sort, Group> createTime() {
            return this.onBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.createTime);
        }

        /**
         * 修改时间
         */
        public OnBuilder<ModuleModel, Column, On, Where, Sort, Group> updateTime() {
            return this.onBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.updateTime);
        }

        /**
         * 删除时间
         */
        public OnBuilder<ModuleModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.onBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public OnBuilder<ModuleModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.onBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public OnBuilder<ModuleModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.onBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public OnBuilder<ModuleModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.onBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.deleteTimeStamp);
        }

    }

    @Override
    public Where getWhereModel() {
        return new Where();
    }

    public static final class Where extends WhereModel<ModuleModel, Column, On, Where, Sort, Group> {

        private Where() {
        }

        /**
         * 主键
         */
        public WhereBuilder<ModuleModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.whereBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.primaryKeyName);
        }
    
        /**
         * 模块ID
         */
        public WhereBuilder<ModuleModel, Column, On, Where, Sort, Group> id() {
            return this.whereBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.id);
        }

        /**
         * 网关地址
         */
        public WhereBuilder<ModuleModel, Column, On, Where, Sort, Group> path() {
            return this.whereBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.path);
        }

        /**
         * 服务ID
         */
        public WhereBuilder<ModuleModel, Column, On, Where, Sort, Group> serviceId() {
            return this.whereBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.serviceId);
        }

        /**
         * 服务名称
         */
        public WhereBuilder<ModuleModel, Column, On, Where, Sort, Group> serviceName() {
            return this.whereBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.serviceName);
        }

        /**
         * 地址
         */
        public WhereBuilder<ModuleModel, Column, On, Where, Sort, Group> url() {
            return this.whereBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.url);
        }

        /**
         * 登录地址
         */
        public WhereBuilder<ModuleModel, Column, On, Where, Sort, Group> loginUrl() {
            return this.whereBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.loginUrl);
        }

        /**
         * 排序号
         */
        public WhereBuilder<ModuleModel, Column, On, Where, Sort, Group> index() {
            return this.whereBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.index);
        }

        /**
         * 状态
         */
        public WhereBuilder<ModuleModel, Column, On, Where, Sort, Group> status() {
            return this.whereBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.status);
        }

        /**
         * 创建时间
         */
        public WhereBuilder<ModuleModel, Column, On, Where, Sort, Group> createTime() {
            return this.whereBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.createTime);
        }

        /**
         * 修改时间
         */
        public WhereBuilder<ModuleModel, Column, On, Where, Sort, Group> updateTime() {
            return this.whereBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.updateTime);
        }

        /**
         * 删除时间
         */
        public WhereBuilder<ModuleModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.whereBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public WhereBuilder<ModuleModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.whereBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public WhereBuilder<ModuleModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.whereBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public WhereBuilder<ModuleModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.whereBuilder.handler(ModuleModel.tableName, ModuleModel.tableAlias, ModuleModel.deleteTimeStamp);
        }

    }

    @Override
    public Group getGroupModel() {
        return new Group();
    }

    public static final class Group extends GroupModel<ModuleModel, Column, On, Where, Sort, Group> {

        private Group() {
        }

        /**
         * 主键
         */
        public Group primaryKey() {
            this.addColumn(ModuleModel.primaryKeyName);
            return this;
        }
    
        /**
         * 模块ID
         */
        public Group id() {
            this.addColumn(ModuleModel.id);
            return this;
        }

        /**
         * 网关地址
         */
        public Group path() {
            this.addColumn(ModuleModel.path);
            return this;
        }

        /**
         * 服务ID
         */
        public Group serviceId() {
            this.addColumn(ModuleModel.serviceId);
            return this;
        }

        /**
         * 服务名称
         */
        public Group serviceName() {
            this.addColumn(ModuleModel.serviceName);
            return this;
        }

        /**
         * 地址
         */
        public Group url() {
            this.addColumn(ModuleModel.url);
            return this;
        }

        /**
         * 登录地址
         */
        public Group loginUrl() {
            this.addColumn(ModuleModel.loginUrl);
            return this;
        }

        /**
         * 排序号
         */
        public Group index() {
            this.addColumn(ModuleModel.index);
            return this;
        }

        /**
         * 状态
         */
        public Group status() {
            this.addColumn(ModuleModel.status);
            return this;
        }

        /**
         * 创建时间
         */
        public Group createTime() {
            this.addColumn(ModuleModel.createTime);
            return this;
        }

        /**
         * 修改时间
         */
        public Group updateTime() {
            this.addColumn(ModuleModel.updateTime);
            return this;
        }

        /**
         * 删除时间
         */
        public Group deleteTime() {
            this.addColumn(ModuleModel.deleteTime);
            return this;
        }

        /**
         * 创建时间戳
         */
        public Group createTimeStamp() {
            this.addColumn(ModuleModel.createTimeStamp);
            return this;
        }

        /**
         * 修改时间戳
         */
        public Group updateTimeStamp() {
            this.addColumn(ModuleModel.updateTimeStamp);
            return this;
        }

        /**
         * 删除时间戳
         */
        public Group deleteTimeStamp() {
            this.addColumn(ModuleModel.deleteTimeStamp);
            return this;
        }

    }

    @Override
    public Sort getSortModel() {
        return new Sort();
    }

    public static final class Sort extends SortModel<ModuleModel, Column, On, Where, Sort, Group> {

        private Sort() {
        }

        /**
         * 主键
         */
        public SortBuilder<ModuleModel, Column, On, Where, Sort, Group> primaryKey() {
            return this.sortBuilder.handler(ModuleModel.primaryKeyName);
        }
    
        /**
         * 模块ID
         */
        public SortBuilder<ModuleModel, Column, On, Where, Sort, Group> id() {
            return this.sortBuilder.handler(ModuleModel.id);
        }

        /**
         * 网关地址
         */
        public SortBuilder<ModuleModel, Column, On, Where, Sort, Group> path() {
            return this.sortBuilder.handler(ModuleModel.path);
        }

        /**
         * 服务ID
         */
        public SortBuilder<ModuleModel, Column, On, Where, Sort, Group> serviceId() {
            return this.sortBuilder.handler(ModuleModel.serviceId);
        }

        /**
         * 服务名称
         */
        public SortBuilder<ModuleModel, Column, On, Where, Sort, Group> serviceName() {
            return this.sortBuilder.handler(ModuleModel.serviceName);
        }

        /**
         * 地址
         */
        public SortBuilder<ModuleModel, Column, On, Where, Sort, Group> url() {
            return this.sortBuilder.handler(ModuleModel.url);
        }

        /**
         * 登录地址
         */
        public SortBuilder<ModuleModel, Column, On, Where, Sort, Group> loginUrl() {
            return this.sortBuilder.handler(ModuleModel.loginUrl);
        }

        /**
         * 排序号
         */
        public SortBuilder<ModuleModel, Column, On, Where, Sort, Group> index() {
            return this.sortBuilder.handler(ModuleModel.index);
        }

        /**
         * 状态
         */
        public SortBuilder<ModuleModel, Column, On, Where, Sort, Group> status() {
            return this.sortBuilder.handler(ModuleModel.status);
        }

        /**
         * 创建时间
         */
        public SortBuilder<ModuleModel, Column, On, Where, Sort, Group> createTime() {
            return this.sortBuilder.handler(ModuleModel.createTime);
        }

        /**
         * 修改时间
         */
        public SortBuilder<ModuleModel, Column, On, Where, Sort, Group> updateTime() {
            return this.sortBuilder.handler(ModuleModel.updateTime);
        }

        /**
         * 删除时间
         */
        public SortBuilder<ModuleModel, Column, On, Where, Sort, Group> deleteTime() {
            return this.sortBuilder.handler(ModuleModel.deleteTime);
        }

        /**
         * 创建时间戳
         */
        public SortBuilder<ModuleModel, Column, On, Where, Sort, Group> createTimeStamp() {
            return this.sortBuilder.handler(ModuleModel.createTimeStamp);
        }

        /**
         * 修改时间戳
         */
        public SortBuilder<ModuleModel, Column, On, Where, Sort, Group> updateTimeStamp() {
            return this.sortBuilder.handler(ModuleModel.updateTimeStamp);
        }

        /**
         * 删除时间戳
         */
        public SortBuilder<ModuleModel, Column, On, Where, Sort, Group> deleteTimeStamp() {
            return this.sortBuilder.handler(ModuleModel.deleteTimeStamp);
        }

    }

}