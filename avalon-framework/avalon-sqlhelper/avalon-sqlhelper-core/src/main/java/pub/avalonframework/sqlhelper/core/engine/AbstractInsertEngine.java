package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.data.store.DataStore;
import pub.avalonframework.sqlhelper.core.data.store.SqlDataStore;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.mgt.SqlhelperManager;
import pub.avalonframework.sqlhelper.core.sqlbuilder.CrudSqlBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.CrudSqlBuilderProxyBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.InsertSqlBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.InsertSqlBuilderResult;
import pub.avalonframework.sqlhelper.core.utils.HelperUtils;

import java.util.Collection;

/**
 * @author baichao
 */
public abstract class AbstractInsertEngine<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
        TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends AbstractEngine<T, TC, TO, TW, TG, TH, TS> implements InsertEngine<T, TC, TO, TW, TG, TH, TS>, InsertSqlBuilder {

    private DataStore<InsertEngine<T, TC, TO, TW, TG, TH, TS>> dataStore;

    private CrudSqlBuilder crudSqlBuilder;

    public AbstractInsertEngine(DatabaseType databaseType, Class<T> tableHelperClass) {
        this(databaseType, tableHelperClass, SqlhelperManager.getDefaultConfiguration());
    }

    public AbstractInsertEngine(DatabaseType databaseType, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        this(databaseType, null, tableHelperClass, null, configuration);
    }

    public AbstractInsertEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass) {
        this(databaseType, tableName, tableHelperClass, SqlhelperManager.getDefaultConfiguration());
    }

    public AbstractInsertEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        this(databaseType, tableName, tableHelperClass, null, configuration);
    }

    public AbstractInsertEngine(DatabaseType databaseType, Class<T> tableHelperClass, String tableAlias) {
        this(databaseType, null, tableHelperClass, tableAlias);
    }

    public AbstractInsertEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        this(databaseType, tableName, tableHelperClass, tableAlias, SqlhelperManager.getDefaultConfiguration());
    }

    public AbstractInsertEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlhelperConfiguration configuration) {
        super(tableHelperClass, tableAlias == null ? HelperUtils.defaultTableHelper(tableHelperClass).getTableAlias() : tableAlias);
        this.dataStore = new SqlDataStore<>(this, tableName == null ? HelperUtils.defaultTableHelper(tableHelperClass).getTableName() : tableName,
                tableHelperClass, this.tableAlias, configuration.setDatabaseType(databaseType));
        this.crudSqlBuilder = new CrudSqlBuilderProxyBuilder(this.dataStore).createProxy();
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
    public DataStore<InsertEngine<T, TC, TO, TW, TG, TH, TS>> getDataStore() {
        return dataStore;
    }

    @Override
    public SqlhelperConfiguration getConfiguration() {
        return getDataStore().getConfiguration();
    }
}