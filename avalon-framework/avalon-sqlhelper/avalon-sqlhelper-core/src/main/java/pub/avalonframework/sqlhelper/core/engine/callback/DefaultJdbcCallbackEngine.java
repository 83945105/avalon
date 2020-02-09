package pub.avalonframework.sqlhelper.core.engine.callback;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.beans.GroupType;
import pub.avalonframework.sqlhelper.core.beans.JoinType;
import pub.avalonframework.sqlhelper.core.callback.*;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.TableColumnDatum;
import pub.avalonframework.sqlhelper.core.engine.AbstractEngine;
import pub.avalonframework.sqlhelper.core.engine.callback.executor.CallbackEngineExecutor;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

/**
 * @author baichao
 */
public final class DefaultJdbcCallbackEngine<T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends AbstractEngine<T, TO, TC, TW, TG, TH, TS> implements JdbcCallbackEngine<TO, TC, TW, TG, TH, TS, DefaultJdbcCallbackEngine<T, TO, TC, TW, TG, TH, TS>> {

    public DefaultJdbcCallbackEngine(DatabaseType databaseType, Class<T> tableHelperClass) {
        super(databaseType, tableHelperClass);
    }

    public DefaultJdbcCallbackEngine(DatabaseType databaseType, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        super(databaseType, tableHelperClass, sqlBuilderOptions);
    }

    public DefaultJdbcCallbackEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass) {
        super(databaseType, tableName, tableHelperClass);
    }

    public DefaultJdbcCallbackEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        super(databaseType, tableName, tableHelperClass, sqlBuilderOptions);
    }

    public DefaultJdbcCallbackEngine(DatabaseType databaseType, Class<T> tableHelperClass, String tableAlias) {
        super(databaseType, tableHelperClass, tableAlias);
    }

    public DefaultJdbcCallbackEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        super(databaseType, tableName, tableHelperClass, tableAlias);
    }

    public DefaultJdbcCallbackEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlBuilderOptions sqlBuilderOptions) {
        super(databaseType, tableName, tableHelperClass, tableAlias, sqlBuilderOptions);
    }

    @Override
    public DefaultJdbcCallbackEngine<T, TO, TC, TW, TG, TH, TS> column(ColumnCallback<TC> columnCallback) {
        TableColumnDatum tableColumnDatum = CallbackExecutor.execute(this.tableHelperClass, this.tableAlias, columnCallback, this.sqlBuilderOptions);
        this.addSelectTableColumnDatum(tableColumnDatum);
        this.addInsertTableColumnDatum(tableColumnDatum);
        this.addUpdateTableColumnDatum(tableColumnDatum);
        return this;
    }

    @Override
    public DefaultJdbcCallbackEngine<T, TO, TC, TW, TG, TH, TS> select(ColumnCallback<TC> columnCallback) {
        TableColumnDatum tableColumnDatum = CallbackExecutor.execute(this.tableHelperClass, this.tableAlias, columnCallback, this.sqlBuilderOptions);
        this.addSelectTableColumnDatum(tableColumnDatum);
        return this;
    }

    @Override
    public DefaultJdbcCallbackEngine<T, TO, TC, TW, TG, TH, TS> insert(ColumnCallback<TC> columnCallback) {
        TableColumnDatum tableColumnDatum = CallbackExecutor.execute(this.tableHelperClass, this.tableAlias, columnCallback, this.sqlBuilderOptions);
        this.addInsertTableColumnDatum(tableColumnDatum);
        return this;
    }

    @Override
    public DefaultJdbcCallbackEngine<T, TO, TC, TW, TG, TH, TS> update(ColumnCallback<TC> columnCallback) {
        TableColumnDatum tableColumnDatum = CallbackExecutor.execute(this.tableHelperClass, this.tableAlias, columnCallback, this.sqlBuilderOptions);
        this.addUpdateTableColumnDatum(tableColumnDatum);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> DefaultJdbcCallbackEngine<T, TO, TC, TW, TG, TH, TS> column(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        TableColumnDatum tableColumnDatum = CallbackExecutor.execute(tableHelperClass, tableAlias, columnCallback, this.sqlBuilderOptions);
        this.addSelectTableColumnDatum(tableColumnDatum);
        this.addInsertTableColumnDatum(tableColumnDatum);
        this.addUpdateTableColumnDatum(tableColumnDatum);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> DefaultJdbcCallbackEngine<T, TO, TC, TW, TG, TH, TS> select(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        TableColumnDatum tableColumnDatum = CallbackExecutor.execute(tableHelperClass, tableAlias, columnCallback, this.sqlBuilderOptions);
        this.addSelectTableColumnDatum(tableColumnDatum);
        return this;
    }

    @Override
    public DefaultJdbcCallbackEngine<T, TO, TC, TW, TG, TH, TS> groupColumn(GroupType groupType, ColumnCallback<TC> columnCallback) {
        TableColumnDatum tableColumnDatum = CallbackEngineExecutor.executeGroupColumn(this.tableHelperClass, this.tableAlias, groupType, columnCallback, this.sqlBuilderOptions);
        this.addSelectTableColumnDatum(tableColumnDatum);
        this.addInsertTableColumnDatum(tableColumnDatum);
        this.addUpdateTableColumnDatum(tableColumnDatum);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> DefaultJdbcCallbackEngine<T, TO, TC, TW, TG, TH, TS> groupColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<SC> columnCallback) {
        TableColumnDatum tableColumnDatum = CallbackEngineExecutor.executeGroupColumn(tableHelperClass, tableAlias, groupType, columnCallback, this.sqlBuilderOptions);
        this.addSelectTableColumnDatum(tableColumnDatum);
        this.addInsertTableColumnDatum(tableColumnDatum);
        this.addUpdateTableColumnDatum(tableColumnDatum);
        return this;
    }

    @Override
    public DefaultJdbcCallbackEngine<T, TO, TC, TW, TG, TH, TS> subQueryColumn(String columnAlias, SubQueryColumnCallback<TC> subQueryColumnCallback) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> DefaultJdbcCallbackEngine<T, TO, TC, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        this.addJoinTableDatum(CallbackEngineExecutor.execute(joinType, this.tableHelperClass, this.tableAlias, tableName, tableHelperClass, tableAlias, onJoinCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public DefaultJdbcCallbackEngine<T, TO, TC, TW, TG, TH, TS> on(OnCallback<TO> onCallback) {
        this.addTableOnDatum(CallbackExecutor.execute(this.tableHelperClass, this.tableAlias, onCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> DefaultJdbcCallbackEngine<T, TO, TC, TW, TG, TH, TS> on(Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        this.addTableOnDatum(CallbackExecutor.execute(this.tableHelperClass, this.tableAlias, tableHelperClass, tableAlias, onJoinCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public DefaultJdbcCallbackEngine<T, TO, TC, TW, TG, TH, TS> where(WhereCallback<TW> whereCallback) {
        this.addTableWhereDatum(CallbackExecutor.execute(this.tableHelperClass, this.tableAlias, whereCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> DefaultJdbcCallbackEngine<T, TO, TC, TW, TG, TH, TS> where(Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<TW, SW> whereJoinCallback) {
        this.addTableWhereDatum(CallbackExecutor.execute(this.tableHelperClass, this.tableAlias, tableHelperClass, tableAlias, whereJoinCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public DefaultJdbcCallbackEngine<T, TO, TC, TW, TG, TH, TS> groupBy(GroupCallback<TG> groupCallback) {
        this.addTableGroupDatum(CallbackExecutor.execute(this.tableHelperClass, this.tableAlias, groupCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> DefaultJdbcCallbackEngine<T, TO, TC, TW, TG, TH, TS> groupBy(Class<S> tableHelperClass, String tableAlias, GroupCallback<SG> groupCallback) {
        this.addTableGroupDatum(CallbackExecutor.execute(tableHelperClass, tableAlias, groupCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public DefaultJdbcCallbackEngine<T, TO, TC, TW, TG, TH, TS> having(HavingCallback<TH> havingCallback) {
        this.addTableHavingDatum(CallbackExecutor.execute(this.tableHelperClass, this.tableAlias, havingCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> DefaultJdbcCallbackEngine<T, TO, TC, TW, TG, TH, TS> having(Class<S> tableHelperClass, String tableAlias, HavingJoinCallback<TH, SH> havingJoinCallback) {
        this.addTableHavingDatum(CallbackExecutor.execute(this.tableHelperClass, this.tableAlias, tableHelperClass, tableAlias, havingJoinCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public DefaultJdbcCallbackEngine<T, TO, TC, TW, TG, TH, TS> orderBy(SortCallback<TS> sortCallback) {
        this.addTableSortDatum(CallbackExecutor.execute(this.tableHelperClass, this.tableAlias, sortCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> DefaultJdbcCallbackEngine<T, TO, TC, TW, TG, TH, TS> orderBy(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> sortCallback) {
        this.addTableSortDatum(CallbackExecutor.execute(tableHelperClass, tableAlias, sortCallback, this.sqlBuilderOptions));
        return this;
    }
}