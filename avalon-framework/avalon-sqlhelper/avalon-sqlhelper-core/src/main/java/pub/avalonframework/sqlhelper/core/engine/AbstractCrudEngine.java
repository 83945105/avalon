package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.data.store.DataStore;
import pub.avalonframework.sqlhelper.core.data.store.SqlDataStore;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.mgt.SqlhelperManager;
import pub.avalonframework.sqlhelper.core.sqlbuilder.CrudSqlBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.CrudSqlBuilderProxyBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.*;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.Collection;

/**
 * @author baichao
 */
public abstract class AbstractCrudEngine<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
        TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends AbstractEngine<T, TC, TO, TW, TG, TH, TS> implements CrudSqlBuilder, CrudEngine<T, TC, TO, TW, TG, TH, TS> {

    private DataStore<CrudEngine<T, TC, TO, TW, TG, TH, TS>> dataStore;

    private CrudSqlBuilder crudSqlBuilder;

    public AbstractCrudEngine(DatabaseType databaseType, Class<T> tableHelperClass) {
        this(databaseType, tableHelperClass, SqlhelperManager.getDefaultConfiguration());
    }

    public AbstractCrudEngine(DatabaseType databaseType, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        this(databaseType, null, tableHelperClass, null, configuration);
    }

    public AbstractCrudEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass) {
        this(databaseType, tableName, tableHelperClass, SqlhelperManager.getDefaultConfiguration());
    }

    public AbstractCrudEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        this(databaseType, tableName, tableHelperClass, null, configuration);
    }

    public AbstractCrudEngine(DatabaseType databaseType, Class<T> tableHelperClass, String tableAlias) {
        this(databaseType, null, tableHelperClass, tableAlias);
    }

    public AbstractCrudEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        this(databaseType, tableName, tableHelperClass, tableAlias, SqlhelperManager.getDefaultConfiguration());
    }

    public AbstractCrudEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlhelperConfiguration configuration) {
        super(tableHelperClass, tableAlias == null ? HelperManager.defaultTableHelper(tableHelperClass).getTableAlias() : tableAlias);
        this.dataStore = new SqlDataStore<>(this, tableName == null ? HelperManager.defaultTableHelper(tableHelperClass).getTableName() : tableName,
                tableHelperClass, this.tableAlias, configuration.setDatabaseType(databaseType));
        this.crudSqlBuilder = new CrudSqlBuilderProxyBuilder(this.dataStore).createCrudSqlBuilder();
    }

    @Override
    public TableSqlBuilderResult copyTable(String targetTableName, boolean copyData) {
        return this.crudSqlBuilder.copyTable(targetTableName, copyData);
    }

    @Override
    public TableSqlBuilderResult deleteTable() {
        return this.crudSqlBuilder.deleteTable();
    }

    @Override
    public TableSqlBuilderResult renameTable(String newTableName) {
        return this.crudSqlBuilder.renameTable(newTableName);
    }

    @Override
    public TableSqlBuilderResult isTableExist() {
        return this.crudSqlBuilder.isTableExist();
    }

    @Override
    public InsertSqlBuilderResult insertArgs(Object... args) {
        return this.crudSqlBuilder.insertArgs(args);
    }

    @Override
    public InsertSqlBuilderResult insertJavaBean(Object javaBean) {
        return this.crudSqlBuilder.insertJavaBean(javaBean);
    }

    @Override
    public InsertSqlBuilderResult insertJavaBeanSelective(Object javaBean) {
        return this.crudSqlBuilder.insertJavaBeanSelective(javaBean);
    }

    @Override
    public InsertSqlBuilderResult batchInsertJavaBeans(Collection<?> javaBeans) {
        return this.crudSqlBuilder.batchInsertJavaBeans(javaBeans);
    }

    @Override
    public DeleteSqlBuilderResult delete() {
        return this.crudSqlBuilder.delete();
    }

    @Override
    public DeleteSqlBuilderResult deleteByPrimaryKey(Object primaryKeyValue) {
        return this.crudSqlBuilder.deleteByPrimaryKey(primaryKeyValue);
    }

    @Override
    public DeleteSqlBuilderResult batchDeleteByPrimaryKeys(Object... primaryKeyValues) {
        return this.crudSqlBuilder.batchDeleteByPrimaryKeys(primaryKeyValues);
    }

    @Override
    public UpdateSqlBuilderResult updateJavaBean(Object javaBean) {
        return this.crudSqlBuilder.updateJavaBean(javaBean);
    }

    @Override
    public UpdateSqlBuilderResult updateJavaBeanSelective(Object javaBean) {
        return this.crudSqlBuilder.updateJavaBeanSelective(javaBean);
    }

    @Override
    public UpdateSqlBuilderResult updateArgsByPrimaryKey(Object primaryKeyValue, Object... args) {
        return this.crudSqlBuilder.updateArgsByPrimaryKey(primaryKeyValue, args);
    }

    @Override
    public UpdateSqlBuilderResult updateJavaBeanByPrimaryKey(Object primaryKeyValue, Object javaBean) {
        return this.crudSqlBuilder.updateJavaBeanByPrimaryKey(primaryKeyValue, javaBean);
    }

    @Override
    public UpdateSqlBuilderResult updateJavaBeanByPrimaryKeySelective(Object primaryKeyValue, Object javaBean) {
        return this.crudSqlBuilder.updateJavaBeanByPrimaryKeySelective(primaryKeyValue, javaBean);
    }

    @Override
    public UpdateSqlBuilderResult batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans) {
        return this.crudSqlBuilder.batchUpdateJavaBeansByPrimaryKeys(javaBeans);
    }

    @Override
    public UpdateSqlBuilderResult updateOrInsertJavaBeans(Collection<?> javaBeans) {
        return this.crudSqlBuilder.updateOrInsertJavaBeans(javaBeans);
    }

    @Override
    public SelectSqlBuilderResult query() {
        return this.crudSqlBuilder.query();
    }

    @Override
    public SelectSqlBuilderResult queryCount() {
        return this.crudSqlBuilder.queryCount();
    }

    @Override
    public SelectSqlBuilderResult queryByPrimaryKey(Object primaryKeyValue) {
        return this.crudSqlBuilder.queryByPrimaryKey(primaryKeyValue);
    }

    @Override
    public DataStore<CrudEngine<T, TC, TO, TW, TG, TH, TS>> getDataStore() {
        return dataStore;
    }

    @Override
    public SqlhelperConfiguration getConfiguration() {
        return getDataStore().getConfiguration();
    }
}