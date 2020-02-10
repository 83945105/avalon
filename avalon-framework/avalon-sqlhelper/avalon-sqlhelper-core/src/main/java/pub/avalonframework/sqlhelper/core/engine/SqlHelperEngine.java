package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.beans.GroupType;
import pub.avalonframework.sqlhelper.core.beans.JoinType;
import pub.avalonframework.sqlhelper.core.block.callback.CallbackCrudBlock;
import pub.avalonframework.sqlhelper.core.block.callback.executor.CallbackBlockExecutor;
import pub.avalonframework.sqlhelper.core.block.helper.HelperCrudBlock;
import pub.avalonframework.sqlhelper.core.builder.*;
import pub.avalonframework.sqlhelper.core.callback.*;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

/**
 * @author baichao
 */
public class SqlHelperEngine<T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>>

        extends AbstractCrudEngine<T, TO, TC, TW, TG, TH, TS> implements

        HelperCrudBlock<SqlHelperEngine<T, TO, TC, TW, TG, TH, TS>>,

        CallbackCrudBlock<TC, TO, TW, TG, TH, TS, SqlHelperEngine<T, TO, TC, TW, TG, TH, TS>>,

        LimitEngine<SqlHelperEngine<T, TO, TC, TW, TG, TH, TS>>,

        CrudBuilder<SqlHelperEngine<T, TO, TC, TW, TG, TH, TS>> {

    public SqlHelperEngine(DatabaseType databaseType, Class<T> tableHelperClass) {
        super(databaseType, tableHelperClass);
    }

    public SqlHelperEngine(DatabaseType databaseType, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        super(databaseType, tableHelperClass, sqlBuilderOptions);
    }

    public SqlHelperEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass) {
        super(databaseType, tableName, tableHelperClass);
    }

    public SqlHelperEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        super(databaseType, tableName, tableHelperClass, sqlBuilderOptions);
    }

    public SqlHelperEngine(DatabaseType databaseType, Class<T> tableHelperClass, String tableAlias) {
        super(databaseType, tableHelperClass, tableAlias);
    }

    public SqlHelperEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        super(databaseType, tableName, tableHelperClass, tableAlias);
    }

    public SqlHelperEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlBuilderOptions sqlBuilderOptions) {
        super(databaseType, tableName, tableHelperClass, tableAlias, sqlBuilderOptions);
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> insert(ColumnHelper<?>... columnHelpers) {
        ColumnHelper.execute(columnHelpers).forEach(this::addInsertTableColumnDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> select(ColumnHelper<?>... columnHelpers) {
        ColumnHelper.execute(columnHelpers).forEach(this::addSelectTableColumnDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> update(ColumnHelper<?>... columnHelpers) {
        ColumnHelper.execute(columnHelpers).forEach(this::addUpdateTableColumnDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> select(ColumnCallback<TC> columnCallback) {
        this.addSelectTableColumnDatum(CallbackExecutor.execute(this.tableHelperClass, this.tableAlias, columnCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> insert(ColumnCallback<TC> columnCallback) {
        this.addInsertTableColumnDatum(CallbackExecutor.execute(this.tableHelperClass, this.tableAlias, columnCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> update(ColumnCallback<TC> columnCallback) {
        this.addUpdateTableColumnDatum(CallbackExecutor.execute(this.tableHelperClass, this.tableAlias, columnCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> select(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        this.addSelectTableColumnDatum(CallbackExecutor.execute(tableHelperClass, tableAlias, columnCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> virtualColumn(Object columnValue, String columnAlias) {
        this.addSelectTableColumnDatum(CallbackBlockExecutor.executeVirtualColumn(this.tableHelperClass, this.tableAlias, columnValue, columnAlias));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> groupColumn(GroupType groupType, ColumnCallback<TC> columnCallback) {
        this.addSelectTableColumnDatum(CallbackBlockExecutor.executeGroupColumn(this.tableHelperClass, this.tableAlias, groupType, columnCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> groupColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<SC> columnCallback) {
        this.addSelectTableColumnDatum(CallbackBlockExecutor.executeGroupColumn(tableHelperClass, tableAlias, groupType, columnCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> subQueryColumn(String columnAlias, SubQueryColumnCallback<TC> subQueryColumnCallback) {
        this.addSelectTableColumnDatum(CallbackBlockExecutor.executeSubQueryColumn(this.tableHelperClass, this.tableAlias, columnAlias, subQueryColumnCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        this.addJoinTableDatum(CallbackBlockExecutor.execute(joinType, this.tableHelperClass, this.tableAlias, tableName, tableHelperClass, tableAlias, onJoinCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> on(OnHelper<?>... onHelpers) {
        OnHelper.execute(onHelpers).forEach(this::addTableOnDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> on(OnCallback<TO> onCallback) {
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
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> on(Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        this.addTableOnDatum(CallbackExecutor.execute(this.tableHelperClass, this.tableAlias, tableHelperClass, tableAlias, onJoinCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> where(WhereHelper<?>... whereHelpers) {
        WhereHelper.execute(whereHelpers).forEach(this::addTableWhereDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> where(WhereCallback<TW> whereCallback) {
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
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> where(Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<TW, SW> whereJoinCallback) {
        this.addTableWhereDatum(CallbackExecutor.execute(this.tableHelperClass, this.tableAlias, tableHelperClass, tableAlias, whereJoinCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> groupBy(GroupHelper<?>... groupHelpers) {
        GroupHelper.execute(groupHelpers).forEach(this::addTableGroupDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> groupBy(GroupCallback<TG> groupCallback) {
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
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> groupBy(Class<S> tableHelperClass, String tableAlias, GroupCallback<SG> groupCallback) {
        this.addTableGroupDatum(CallbackExecutor.execute(tableHelperClass, tableAlias, groupCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> having(HavingHelper<?>... havingHelpers) {
        HavingHelper.execute(havingHelpers).forEach(this::addTableHavingDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> having(HavingCallback<TH> havingCallback) {
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
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> having(Class<S> tableHelperClass, String tableAlias, HavingJoinCallback<TH, SH> havingJoinCallback) {
        this.addTableHavingDatum(CallbackExecutor.execute(this.tableHelperClass, this.tableAlias, tableHelperClass, tableAlias, havingJoinCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> orderBy(SortHelper<?>... sortHelpers) {
        SortHelper.execute(sortHelpers).forEach(this::addTableSortDatum);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> orderBy(SortCallback<TS> sortCallback) {
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
            SS extends SortHelper<SS>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> orderBy(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> sortCallback) {
        this.addTableSortDatum(CallbackExecutor.execute(tableHelperClass, tableAlias, sortCallback, this.sqlBuilderOptions));
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> limit(Long limit) {
        this.setLimit(limit);
        return this;
    }

    @Override
    public SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> offset(Long offset) {
        this.setOffset(offset);
        return this;
    }

    @Override
    public <FC extends ColumnHelper<FC>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> buildInsertColumn(InsertColumnBuilder<FC> insertColumnBuilder) {
        InsertColumnBuilder.execute(insertColumnBuilder, this.sqlBuilderOptions, () -> this);
        return this;
    }

    @Override
    public <FC extends ColumnHelper<FC>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> buildSelectColumn(SelectColumnBuilder<FC> selectColumnBuilder) {
        SelectColumnBuilder.execute(selectColumnBuilder, this.sqlBuilderOptions, () -> this);
        return this;
    }

    @Override
    public <FC extends ColumnHelper<FC>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> buildUpdateColumn(UpdateColumnBuilder<FC> updateColumnBuilder) {
        UpdateColumnBuilder.execute(updateColumnBuilder, this.sqlBuilderOptions, () -> this);
        return this;
    }

    @Override
    public <FC extends ColumnHelper<FC>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> buildColumn(ColumnBuilder<FC> columnBuilder) {
        ColumnBuilder.execute(columnBuilder, this.sqlBuilderOptions, () -> this);
        return this;
    }

    @Override
    public <FO extends OnHelper<FO>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> buildJoin(JoinBuilder<FO> joinBuilder) {
        JoinBuilder.execute(joinBuilder, this.sqlBuilderOptions).forEach(this::addJoinTableDatum);
        return this;
    }

    @Override
    public <FO extends OnHelper<FO>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> buildOn(OnBuilder<FO> onBuilder) {
        OnBuilder.execute(onBuilder, this.sqlBuilderOptions, () -> this);
        return this;
    }

    @Override
    public <FW extends WhereHelper<FW>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> buildWhere(WhereBuilder<FW> whereBuilder) {
        WhereBuilder.execute(whereBuilder, this.sqlBuilderOptions, () -> this);
        return this;
    }

    @Override
    public <FG extends GroupHelper<FG>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> buildGroup(GroupBuilder<FG> groupBuilder) {
        GroupBuilder.execute(groupBuilder, this.sqlBuilderOptions, () -> this);
        return this;
    }

    @Override
    public <FH extends HavingHelper<FH>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> buildHaving(HavingBuilder<FH> havingBuilder) {
        HavingBuilder.execute(havingBuilder, this.sqlBuilderOptions, () -> this);
        return this;
    }

    @Override
    public <FS extends SortHelper<FS>> SqlHelperEngine<T, TO, TC, TW, TG, TH, TS> buildSort(SortBuilder<FS> sortBuilder) {
        SortBuilder.execute(sortBuilder, this.sqlBuilderOptions, () -> this);
        return this;
    }
}