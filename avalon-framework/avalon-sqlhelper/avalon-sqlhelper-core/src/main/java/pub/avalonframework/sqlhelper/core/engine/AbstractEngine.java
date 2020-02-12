package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.data.FinalSqlData;
import pub.avalonframework.sqlhelper.core.data.MainTableDatum;
import pub.avalonframework.sqlhelper.core.data.SqlData;
import pub.avalonframework.sqlhelper.core.data.SqlDataOptionsProducer;
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
        TS extends SortHelper<TS>> implements Engine<T, TC, TO, TW, TG, TH, TS>, SqlDataOptionsProducer {

    protected Class<T> tableHelperClass;

    protected String tableAlias;

    protected SqlData sqlData;

    protected CrudSqlBuilder crudSqlBuilder;

    public AbstractEngine(DatabaseType databaseType, Class<T> tableHelperClass) {
        this(databaseType, tableHelperClass, SqlhelperManager.getDefaultSqlhelperConfiguration());
    }

    public AbstractEngine(DatabaseType databaseType, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        this(databaseType, HelperManager.newTableHelper(tableHelperClass).getTableName(), tableHelperClass, HelperManager.newTableHelper(tableHelperClass).getTableAlias(), configuration);
    }

    public AbstractEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass) {
        this(databaseType, tableName, tableHelperClass, SqlhelperManager.getDefaultSqlhelperConfiguration());
    }

    public AbstractEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        this(databaseType, tableName, tableHelperClass, HelperManager.newTableHelper(tableHelperClass).getTableAlias(), configuration);
    }

    public AbstractEngine(DatabaseType databaseType, Class<T> tableHelperClass, String tableAlias) {
        this(databaseType, HelperManager.newTableHelper(tableHelperClass).getTableName(), tableHelperClass, tableAlias, SqlhelperManager.getDefaultSqlhelperConfiguration());
    }

    public AbstractEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        this(databaseType, tableName, tableHelperClass, tableAlias, SqlhelperManager.getDefaultSqlhelperConfiguration());
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
        this.sqlData = new FinalSqlData(databaseType, new MainTableDatum(tableHelperClass, tableName, this.tableAlias), configuration);
        this.crudSqlBuilder = new CrudSqlBuilderProxyBuilder(this.sqlData).createCrudSqlBuilder();
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
        return this.sqlData.getConfiguration();
    }

    @Override
    public void setConfiguration(SqlhelperConfiguration configuration) {
        this.sqlData.setConfiguration(configuration);
    }
}