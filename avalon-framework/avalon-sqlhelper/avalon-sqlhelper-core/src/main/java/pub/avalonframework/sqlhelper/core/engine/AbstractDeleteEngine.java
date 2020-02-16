package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.data.store.DataStore;
import pub.avalonframework.sqlhelper.core.data.store.SqlDataStore;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.mgt.SqlhelperManager;
import pub.avalonframework.sqlhelper.core.sqlbuilder.CrudSqlBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.CrudSqlBuilderProxyBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.DeleteSqlBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.DeleteSqlBuilderResult;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

/**
 * @author baichao
 */
public abstract class AbstractDeleteEngine<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
        TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends AbstractEngine<T, TC, TO, TW, TG, TH, TS> implements DeleteSqlBuilder, DeleteEngine<T, TC, TO, TW, TG, TH, TS> {

    private DataStore<DeleteEngine<T, TC, TO, TW, TG, TH, TS>> dataStore;

    private CrudSqlBuilder crudSqlBuilder;

    public AbstractDeleteEngine(DatabaseType databaseType, Class<T> tableHelperClass) {
        this(databaseType, tableHelperClass, SqlhelperManager.getDefaultConfiguration());
    }

    public AbstractDeleteEngine(DatabaseType databaseType, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        this(databaseType, null, tableHelperClass, null, configuration);
    }

    public AbstractDeleteEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass) {
        this(databaseType, tableName, tableHelperClass, SqlhelperManager.getDefaultConfiguration());
    }

    public AbstractDeleteEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        this(databaseType, tableName, tableHelperClass, null, configuration);
    }

    public AbstractDeleteEngine(DatabaseType databaseType, Class<T> tableHelperClass, String tableAlias) {
        this(databaseType, null, tableHelperClass, tableAlias);
    }

    public AbstractDeleteEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        this(databaseType, tableName, tableHelperClass, tableAlias, SqlhelperManager.getDefaultConfiguration());
    }

    public AbstractDeleteEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlhelperConfiguration configuration) {
        super(tableHelperClass, tableAlias == null ? HelperManager.defaultTableHelper(tableHelperClass).getTableAlias() : tableAlias);
        this.dataStore = new SqlDataStore<>(this, tableName == null ? HelperManager.defaultTableHelper(tableHelperClass).getTableName() : tableName,
                tableHelperClass, this.tableAlias, configuration.setDatabaseType(databaseType));
        this.crudSqlBuilder = new CrudSqlBuilderProxyBuilder(this.dataStore).createCrudSqlBuilder();
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
    public DataStore<DeleteEngine<T, TC, TO, TW, TG, TH, TS>> getDataStore() {
        return dataStore;
    }

    @Override
    public SqlhelperConfiguration getConfiguration() {
        return getDataStore().getConfiguration();
    }
}