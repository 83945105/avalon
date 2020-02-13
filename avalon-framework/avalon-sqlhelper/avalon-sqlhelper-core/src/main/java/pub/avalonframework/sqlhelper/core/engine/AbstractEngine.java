package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.TableMainDataBlock;
import pub.avalonframework.sqlhelper.core.data.inject.ConfigurationInjector;
import pub.avalonframework.sqlhelper.core.data.store.DataStore;
import pub.avalonframework.sqlhelper.core.data.store.SqlDataStore;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.mgt.SqlhelperManager;
import pub.avalonframework.sqlhelper.core.sqlbuilder.CrudSqlBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.CrudSqlBuilderProxyBuilder;
import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

/**
 * @author baichao
 */
public abstract class AbstractEngine<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
        TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> implements Engine<T, TC, TO, TW, TG, TH, TS>, ConfigurationInjector {

    protected Class<T> tableHelperClass;

    protected String tableAlias;

    protected DataStore dataStore;

    protected CrudSqlBuilder crudSqlBuilder;

    public AbstractEngine(DatabaseType databaseType, Class<T> tableHelperClass) {
        this(databaseType, tableHelperClass, SqlhelperManager.getDefaultConfiguration());
    }

    public AbstractEngine(DatabaseType databaseType, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        this(databaseType, HelperManager.newTableHelper(tableHelperClass).getTableName(), tableHelperClass, HelperManager.newTableHelper(tableHelperClass).getTableAlias(), configuration);
    }

    public AbstractEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass) {
        this(databaseType, tableName, tableHelperClass, SqlhelperManager.getDefaultConfiguration());
    }

    public AbstractEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        this(databaseType, tableName, tableHelperClass, HelperManager.newTableHelper(tableHelperClass).getTableAlias(), configuration);
    }

    public AbstractEngine(DatabaseType databaseType, Class<T> tableHelperClass, String tableAlias) {
        this(databaseType, HelperManager.newTableHelper(tableHelperClass).getTableName(), tableHelperClass, tableAlias, SqlhelperManager.getDefaultConfiguration());
    }

    public AbstractEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        this(databaseType, tableName, tableHelperClass, tableAlias, SqlhelperManager.getDefaultConfiguration());
    }

    public AbstractEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlhelperConfiguration configuration) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
        }
        this.tableHelperClass = tableHelperClass;
        this.tableAlias = tableAlias;
        configuration.setDatabaseType(databaseType);
        this.dataStore = new SqlDataStore(new TableMainDataBlock(tableHelperClass, tableName, this.tableAlias), configuration);
        this.crudSqlBuilder = new CrudSqlBuilderProxyBuilder(this.dataStore).createCrudSqlBuilder();
    }

    @Override
    public Class<T> getTableHelperClass() {
        return tableHelperClass;
    }

    @Override
    public String getTableAlias() {
        return tableAlias;
    }

    @Override
    public SqlhelperConfiguration getConfiguration() {
        return this.dataStore.getConfiguration();
    }

    @Override
    public void setConfiguration(SqlhelperConfiguration configuration) {
        this.dataStore.setConfiguration(configuration);
    }
}