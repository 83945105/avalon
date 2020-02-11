package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.data.FinalSqlData;
import pub.avalonframework.sqlhelper.core.data.MainTableDatum;
import pub.avalonframework.sqlhelper.core.data.SqlData;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalonframework.sqlhelper.core.sqlbuilder.AbstractSqlCrudBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.SqlCrudBuilder;
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
        TS extends SortHelper<TS>> implements Engine<T, TC, TO, TW, TG, TH, TS> {

    protected Class<T> tableHelperClass;

    protected T tableHelper;

    protected String tableName;

    protected String tableAlias;

    protected MainTableDatum mainTableDatum;

    protected SqlData sqlData;

    protected SqlBuilderOptions sqlBuilderOptions;

    protected SqlCrudBuilder sqlCrudBuilder;

    public AbstractEngine(DatabaseType databaseType, Class<T> tableHelperClass) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        this.tableHelperClass = tableHelperClass;
        this.tableHelper = HelperManager.newTableHelper(tableHelperClass);
        this.tableName = this.tableHelper.getTableName();
        this.tableAlias = this.tableHelper.getTableAlias();
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableName, this.tableAlias);
        this.sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;
        this.sqlData = new FinalSqlData(databaseType, this.mainTableDatum, this.sqlBuilderOptions);
        this.sqlCrudBuilder = new AbstractSqlCrudBuilder(this.sqlData, this.sqlBuilderOptions) {
        };
    }

    public AbstractEngine(DatabaseType databaseType, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        this.tableHelperClass = tableHelperClass;
        this.tableHelper = HelperManager.newTableHelper(tableHelperClass);
        this.tableName = this.tableHelper.getTableName();
        this.tableAlias = this.tableHelper.getTableAlias();
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableName, this.tableAlias);
        this.sqlBuilderOptions = sqlBuilderOptions;
        this.sqlData = new FinalSqlData(databaseType, this.mainTableDatum, this.sqlBuilderOptions);
        this.sqlCrudBuilder = new AbstractSqlCrudBuilder(this.sqlData, this.sqlBuilderOptions) {
        };
    }

    public AbstractEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        this.tableHelperClass = tableHelperClass;
        this.tableHelper = HelperManager.newTableHelper(tableHelperClass);
        this.tableName = tableName;
        this.tableAlias = this.tableHelper.getTableAlias();
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableName, this.tableAlias);
        this.sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;
        this.sqlData = new FinalSqlData(databaseType, this.mainTableDatum, this.sqlBuilderOptions);
        this.sqlCrudBuilder = new AbstractSqlCrudBuilder(this.sqlData, this.sqlBuilderOptions) {
        };
    }

    public AbstractEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        this.tableHelperClass = tableHelperClass;
        this.tableHelper = HelperManager.newTableHelper(tableHelperClass);
        this.tableName = tableName;
        this.tableAlias = this.tableHelper.getTableAlias();
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableName, this.tableAlias);
        this.sqlBuilderOptions = sqlBuilderOptions;
        this.sqlData = new FinalSqlData(databaseType, this.mainTableDatum, this.sqlBuilderOptions);
        this.sqlCrudBuilder = new AbstractSqlCrudBuilder(this.sqlData, this.sqlBuilderOptions) {
        };
    }

    public AbstractEngine(DatabaseType databaseType, Class<T> tableHelperClass, String tableAlias) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
        }
        this.tableHelperClass = tableHelperClass;
        this.tableHelper = HelperManager.newTableHelper(tableHelperClass);
        this.tableName = this.tableHelper.getTableName();
        this.tableAlias = tableAlias;
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableName, this.tableAlias);
        this.sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;
        this.sqlData = new FinalSqlData(databaseType, this.mainTableDatum, this.sqlBuilderOptions);
        this.sqlCrudBuilder = new AbstractSqlCrudBuilder(this.sqlData, this.sqlBuilderOptions) {
        };
    }

    public AbstractEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
        }
        this.tableHelperClass = tableHelperClass;
        this.tableHelper = HelperManager.newTableHelper(tableHelperClass);
        this.tableName = tableName;
        this.tableAlias = tableAlias;
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableName, this.tableAlias);
        this.sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;
        this.sqlData = new FinalSqlData(databaseType, this.mainTableDatum, this.sqlBuilderOptions);
        this.sqlCrudBuilder = new AbstractSqlCrudBuilder(this.sqlData, this.sqlBuilderOptions) {
        };
    }

    public AbstractEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlBuilderOptions sqlBuilderOptions) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
        }
        this.tableHelperClass = tableHelperClass;
        this.tableHelper = HelperManager.newTableHelper(tableHelperClass);
        this.tableName = tableName;
        this.tableAlias = tableAlias;
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableName, this.tableAlias);
        this.sqlBuilderOptions = sqlBuilderOptions;
        this.sqlData = new FinalSqlData(databaseType, this.mainTableDatum, this.sqlBuilderOptions);
        this.sqlCrudBuilder = new AbstractSqlCrudBuilder(this.sqlData, this.sqlBuilderOptions) {
        };
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
    public SqlBuilderOptions getSqlBuilderOptions() {
        return sqlBuilderOptions;
    }
}