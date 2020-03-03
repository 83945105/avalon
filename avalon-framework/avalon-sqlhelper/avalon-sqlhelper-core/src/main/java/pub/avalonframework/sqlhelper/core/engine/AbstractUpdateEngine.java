package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.data.store.DataStore;
import pub.avalonframework.sqlhelper.core.data.store.SqlDataStore;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.mgt.SqlhelperManager;
import pub.avalonframework.sqlhelper.core.sqlbuilder.CrudSqlBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.CrudSqlBuilderProxyBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.UpdateSqlBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.UpdateSqlBuilderResult;
import pub.avalonframework.sqlhelper.core.utils.HelperUtils;

import java.util.Collection;

/**
 * @author baichao
 */
public abstract class AbstractUpdateEngine<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
        TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends AbstractEngine<T, TC, TO, TW, TG, TH, TS> implements UpdateEngine<T, TC, TO, TW, TG, TH, TS>, UpdateSqlBuilder {

    private DataStore<UpdateEngine<T, TC, TO, TW, TG, TH, TS>> dataStore;

    private CrudSqlBuilder crudSqlBuilder;

    public AbstractUpdateEngine(DatabaseType databaseType, Class<T> tableHelperClass) {
        this(databaseType, tableHelperClass, SqlhelperManager.getDefaultConfiguration());
    }

    public AbstractUpdateEngine(DatabaseType databaseType, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        this(databaseType, null, tableHelperClass, null, configuration);
    }

    public AbstractUpdateEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass) {
        this(databaseType, tableName, tableHelperClass, SqlhelperManager.getDefaultConfiguration());
    }

    public AbstractUpdateEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        this(databaseType, tableName, tableHelperClass, null, configuration);
    }

    public AbstractUpdateEngine(DatabaseType databaseType, Class<T> tableHelperClass, String tableAlias) {
        this(databaseType, null, tableHelperClass, tableAlias);
    }

    public AbstractUpdateEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        this(databaseType, tableName, tableHelperClass, tableAlias, SqlhelperManager.getDefaultConfiguration());
    }

    public AbstractUpdateEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlhelperConfiguration configuration) {
        super(tableHelperClass, tableAlias == null ? HelperUtils.defaultTableHelper(tableHelperClass).getTableAlias() : tableAlias);
        this.dataStore = new SqlDataStore<>(this, tableName == null ? HelperUtils.defaultTableHelper(tableHelperClass).getTableName() : tableName,
                tableHelperClass, this.tableAlias, configuration.setDatabaseType(databaseType));
        this.crudSqlBuilder = new CrudSqlBuilderProxyBuilder(this.dataStore).createProxy();
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
    public DataStore<UpdateEngine<T, TC, TO, TW, TG, TH, TS>> getDataStore() {
        return dataStore;
    }

    @Override
    public SqlhelperConfiguration getConfiguration() {
        return getDataStore().getConfiguration();
    }
}