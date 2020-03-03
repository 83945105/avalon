package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.data.store.DataStore;
import pub.avalonframework.sqlhelper.core.data.store.SqlDataStore;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.mgt.SqlhelperManager;
import pub.avalonframework.sqlhelper.core.sqlbuilder.CrudSqlBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.CrudSqlBuilderProxyBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.SelectSqlBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SelectSqlBuilderResult;
import pub.avalonframework.sqlhelper.core.utils.HelperUtils;

/**
 * @author baichao
 */
public abstract class AbstractSelectEngine<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
        TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends AbstractEngine<T, TC, TO, TW, TG, TH, TS> implements SelectEngine<T, TC, TO, TW, TG, TH, TS>, SelectSqlBuilder {

    private DataStore<SelectEngine<T, TC, TO, TW, TG, TH, TS>> dataStore;

    private CrudSqlBuilder crudSqlBuilder;

    public AbstractSelectEngine(DatabaseType databaseType, Class<T> tableHelperClass) {
        this(databaseType, tableHelperClass, SqlhelperManager.getDefaultConfiguration());
    }

    public AbstractSelectEngine(DatabaseType databaseType, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        this(databaseType, null, tableHelperClass, null, configuration);
    }

    public AbstractSelectEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass) {
        this(databaseType, tableName, tableHelperClass, SqlhelperManager.getDefaultConfiguration());
    }

    public AbstractSelectEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        this(databaseType, tableName, tableHelperClass, null, configuration);
    }

    public AbstractSelectEngine(DatabaseType databaseType, Class<T> tableHelperClass, String tableAlias) {
        this(databaseType, null, tableHelperClass, tableAlias);
    }

    public AbstractSelectEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        this(databaseType, tableName, tableHelperClass, tableAlias, SqlhelperManager.getDefaultConfiguration());
    }

    public AbstractSelectEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlhelperConfiguration configuration) {
        super(tableHelperClass, tableAlias == null ? HelperUtils.defaultTableHelper(tableHelperClass).getTableAlias() : tableAlias);
        this.dataStore = new SqlDataStore<>(this, tableName == null ? HelperUtils.defaultTableHelper(tableHelperClass).getTableName() : tableName,
                tableHelperClass, this.tableAlias, configuration.setDatabaseType(databaseType));
        this.crudSqlBuilder = new CrudSqlBuilderProxyBuilder(this.dataStore).createProxy();
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
    public DataStore<SelectEngine<T, TC, TO, TW, TG, TH, TS>> getDataStore() {
        return dataStore;
    }

    @Override
    public SqlhelperConfiguration getConfiguration() {
        return getDataStore().getConfiguration();
    }
}