package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.block.LimitBlock;
import pub.avalonframework.sqlhelper.core.block.callback.CallbackCrudBlock;
import pub.avalonframework.sqlhelper.core.block.callback.executor.CallbackBlockExecutor;
import pub.avalonframework.sqlhelper.core.block.helper.HelperCrudBlock;
import pub.avalonframework.sqlhelper.core.builder.*;
import pub.avalonframework.sqlhelper.core.callback.*;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.block.*;
import pub.avalonframework.sqlhelper.core.data.inject.CrudInjector;
import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface CrudEngine<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
        TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends
        Engine<T, TC, TO, TW, TG, TH, TS>,
        CrudInjector<CrudEngine<T, TC, TO, TW, TG, TH, TS>>,
        HelperCrudBlock<CrudEngine<T, TC, TO, TW, TG, TH, TS>>,
        CallbackCrudBlock<TC, TO, TW, TG, TH, TS, CrudEngine<T, TC, TO, TW, TG, TH, TS>>,
        LimitBlock<CrudEngine<T, TC, TO, TW, TG, TH, TS>>,
        CrudBuilder<CrudEngine<T, TC, TO, TW, TG, TH, TS>> {

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> addInsertTableColumnDataBlock(TableColumnDataBlock tableColumnDataBlock) {
        return CrudInjector.super.addInsertTableColumnDataBlock(tableColumnDataBlock);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> addSelectTableColumnDataBlock(TableColumnDataBlock tableColumnDataBlock) {
        return CrudInjector.super.addSelectTableColumnDataBlock(tableColumnDataBlock);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> setConfiguration(SqlhelperConfiguration configuration) {
        return CrudInjector.super.setConfiguration(configuration);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> addTableGroupDataBlock(TableGroupDataBlock tableGroupDataBlock) {
        return CrudInjector.super.addTableGroupDataBlock(tableGroupDataBlock);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> addTableHavingDataBlock(TableHavingDataBlock tableHavingDataBlock) {
        return CrudInjector.super.addTableHavingDataBlock(tableHavingDataBlock);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> addTableJoinDataBlock(TableJoinDataBlock tableJoinDataBlock) {
        return CrudInjector.super.addTableJoinDataBlock(tableJoinDataBlock);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> setLimit(Long limit) {
        return CrudInjector.super.setLimit(limit);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> setOffset(Long offset) {
        return CrudInjector.super.setOffset(offset);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> addUpdateTableColumnDataBlock(TableColumnDataBlock tableColumnDataBlock) {
        return CrudInjector.super.addUpdateTableColumnDataBlock(tableColumnDataBlock);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> addTableOnDataBlock(TableOnDataBlock tableOnDataBlock) {
        return CrudInjector.super.addTableOnDataBlock(tableOnDataBlock);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> addTableSortDataBlock(TableSortDataBlock tableSortDataBlock) {
        return CrudInjector.super.addTableSortDataBlock(tableSortDataBlock);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> addTableWhereDataBlock(TableWhereDataBlock tableWhereDataBlock) {
        return CrudInjector.super.addTableWhereDataBlock(tableWhereDataBlock);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> virtualColumn(Object columnValue, String columnAlias) {
        return this.addSelectTableColumnDataBlock(CallbackBlockExecutor.executeVirtualColumn(getTableAlias(), columnValue, columnAlias));
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> groupBy(GroupHelper<?>... groupHelpers) {
        GroupHelper.execute(groupHelpers).forEach(this::addTableGroupDataBlock);
        return this;
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> having(HavingHelper<?>... havingHelpers) {
        HavingHelper.execute(havingHelpers).forEach(this::addTableHavingDataBlock);
        return this;
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> insert(ColumnHelper<?>... columnHelpers) {
        ColumnHelper.execute(columnHelpers).forEach(this::addInsertTableColumnDataBlock);
        return this;
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> select(ColumnHelper<?>... columnHelpers) {
        ColumnHelper.execute(columnHelpers).forEach(this::addSelectTableColumnDataBlock);
        return this;
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> update(ColumnHelper<?>... columnHelpers) {
        ColumnHelper.execute(columnHelpers).forEach(this::addUpdateTableColumnDataBlock);
        return this;
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> on(OnHelper<?>... onHelpers) {
        OnHelper.execute(onHelpers).forEach(this::addTableOnDataBlock);
        return this;
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> orderBy(SortHelper<?>... sortHelpers) {
        SortHelper.execute(sortHelpers).forEach(this::addTableSortDataBlock);
        return this;
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> where(WhereHelper<?>... whereHelpers) {
        WhereHelper.execute(whereHelpers).forEach(this::addTableWhereDataBlock);
        return this;
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> insert(ColumnCallback<TC> columnCallback) {
        return this.addInsertTableColumnDataBlock(CallbackExecutor.execute(getTableHelperClass(), getTableAlias(), columnCallback, getConfiguration().getSqlBuilder()));
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> select(ColumnCallback<TC> columnCallback) {
        this.addSelectTableColumnDataBlock(CallbackExecutor.execute(getTableHelperClass(), getTableAlias(), columnCallback, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> select(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        this.addSelectTableColumnDataBlock(CallbackExecutor.execute(tableHelperClass, tableAlias, columnCallback, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> select(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return CallbackCrudBlock.super.select(tableHelperClass, columnCallback);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> groupColumn(GroupType groupType, ColumnCallback<TC> columnCallback) {
        this.addSelectTableColumnDataBlock(CallbackBlockExecutor.executeGroupColumn(getTableHelperClass(), getTableAlias(), groupType, columnCallback, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> groupColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<SC> columnCallback) {
        this.addSelectTableColumnDataBlock(CallbackBlockExecutor.executeGroupColumn(tableHelperClass, tableAlias, groupType, columnCallback, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> groupColumn(Class<S> tableHelperClass, GroupType groupType, ColumnCallback<SC> columnCallback) {
        return CallbackCrudBlock.super.groupColumn(tableHelperClass, groupType, columnCallback);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> subQueryColumn(String columnAlias, SubQueryColumnCallback<TC> subQueryColumnCallback) {
        return this.addSelectTableColumnDataBlock(CallbackBlockExecutor.executeSubQueryColumn(getTableHelperClass(), getTableAlias(), columnAlias, subQueryColumnCallback, getConfiguration().getSqlBuilder()));
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> groupBy(GroupCallback<TG> groupCallback) {
        return this.addTableGroupDataBlock(CallbackExecutor.execute(getTableHelperClass(), getTableAlias(), groupCallback, getConfiguration().getSqlBuilder()));
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> groupBy(Class<S> tableHelperClass, String tableAlias, GroupCallback<SG> groupCallback) {
        this.addTableGroupDataBlock(CallbackExecutor.execute(tableHelperClass, tableAlias, groupCallback, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> groupBy(Class<S> tableHelperClass, GroupCallback<SG> groupCallback) {
        return CallbackCrudBlock.super.groupBy(tableHelperClass, groupCallback);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> having(HavingCallback<TH> havingCallback) {
        this.addTableHavingDataBlock(CallbackExecutor.execute(getTableHelperClass(), getTableAlias(), havingCallback, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> having(Class<S> tableHelperClass, String tableAlias, HavingJoinCallback<TH, SH> havingJoinCallback) {
        this.addTableHavingDataBlock(CallbackExecutor.execute(getTableHelperClass(), getTableAlias(), tableHelperClass, tableAlias, havingJoinCallback, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> having(Class<S> tableHelperClass, HavingJoinCallback<TH, SH> havingJoinCallback) {
        return CallbackCrudBlock.super.having(tableHelperClass, havingJoinCallback);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> update(ColumnCallback<TC> columnCallback) {
        return this.addUpdateTableColumnDataBlock(CallbackExecutor.execute(getTableHelperClass(), getTableAlias(), columnCallback, getConfiguration().getSqlBuilder()));
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        this.addTableJoinDataBlock(CallbackBlockExecutor.execute(joinType, getTableHelperClass(), getTableAlias(), tableName, tableHelperClass, tableAlias, onJoinCallback, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias) {
        return CallbackCrudBlock.super.join(joinType, tableName, tableHelperClass, tableAlias);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        return CallbackCrudBlock.super.join(joinType, tableName, tableHelperClass, onJoinCallback);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass) {
        return CallbackCrudBlock.super.join(joinType, tableName, tableHelperClass);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        return CallbackCrudBlock.super.join(joinType, tableHelperClass, tableAlias, onJoinCallback);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, Class<S> tableHelperClass, String tableAlias) {
        return CallbackCrudBlock.super.join(joinType, tableHelperClass, tableAlias);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        return CallbackCrudBlock.super.join(joinType, tableHelperClass, onJoinCallback);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, Class<S> tableHelperClass) {
        return CallbackCrudBlock.super.join(joinType, tableHelperClass);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        return CallbackCrudBlock.super.innerJoin(tableName, tableHelperClass, tableAlias, onJoinCallback);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(String tableName, Class<S> tableHelperClass, String tableAlias) {
        return CallbackCrudBlock.super.innerJoin(tableName, tableHelperClass, tableAlias);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(String tableName, Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        return CallbackCrudBlock.super.innerJoin(tableName, tableHelperClass, onJoinCallback);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(String tableName, Class<S> tableHelperClass) {
        return CallbackCrudBlock.super.innerJoin(tableName, tableHelperClass);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        return CallbackCrudBlock.super.innerJoin(tableHelperClass, tableAlias, onJoinCallback);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(Class<S> tableHelperClass, String tableAlias) {
        return CallbackCrudBlock.super.innerJoin(tableHelperClass, tableAlias);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        return CallbackCrudBlock.super.innerJoin(tableHelperClass, onJoinCallback);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(Class<S> tableHelperClass) {
        return CallbackCrudBlock.super.innerJoin(tableHelperClass);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        return CallbackCrudBlock.super.leftJoin(tableName, tableHelperClass, tableAlias, onJoinCallback);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(String tableName, Class<S> tableHelperClass, String tableAlias) {
        return CallbackCrudBlock.super.leftJoin(tableName, tableHelperClass, tableAlias);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(String tableName, Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        return CallbackCrudBlock.super.leftJoin(tableName, tableHelperClass, onJoinCallback);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(String tableName, Class<S> tableHelperClass) {
        return CallbackCrudBlock.super.leftJoin(tableName, tableHelperClass);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        return CallbackCrudBlock.super.leftJoin(tableHelperClass, tableAlias, onJoinCallback);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(Class<S> tableHelperClass, String tableAlias) {
        return CallbackCrudBlock.super.leftJoin(tableHelperClass, tableAlias);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        return CallbackCrudBlock.super.leftJoin(tableHelperClass, onJoinCallback);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(Class<S> tableHelperClass) {
        return CallbackCrudBlock.super.leftJoin(tableHelperClass);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        return CallbackCrudBlock.super.rightJoin(tableName, tableHelperClass, tableAlias, onJoinCallback);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(String tableName, Class<S> tableHelperClass, String tableAlias) {
        return CallbackCrudBlock.super.rightJoin(tableName, tableHelperClass, tableAlias);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(String tableName, Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        return CallbackCrudBlock.super.rightJoin(tableName, tableHelperClass, onJoinCallback);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(String tableName, Class<S> tableHelperClass) {
        return CallbackCrudBlock.super.rightJoin(tableName, tableHelperClass);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        return CallbackCrudBlock.super.rightJoin(tableHelperClass, tableAlias, onJoinCallback);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(Class<S> tableHelperClass, String tableAlias) {
        return CallbackCrudBlock.super.rightJoin(tableHelperClass, tableAlias);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        return CallbackCrudBlock.super.rightJoin(tableHelperClass, onJoinCallback);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(Class<S> tableHelperClass) {
        return CallbackCrudBlock.super.rightJoin(tableHelperClass);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> on(OnCallback<TO> onCallback) {
        return this.addTableOnDataBlock(CallbackExecutor.execute(getTableHelperClass(), getTableAlias(), onCallback, getConfiguration().getSqlBuilder()));
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> on(Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        return this.addTableOnDataBlock(CallbackExecutor.execute(getTableHelperClass(), getTableAlias(), tableHelperClass, tableAlias, onJoinCallback, getConfiguration().getSqlBuilder()));
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> on(Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        return CallbackCrudBlock.super.on(tableHelperClass, onJoinCallback);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> orderBy(SortCallback<TS> sortCallback) {
        return this.addTableSortDataBlock(CallbackExecutor.execute(getTableHelperClass(), getTableAlias(), sortCallback, getConfiguration().getSqlBuilder()));
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> orderBy(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> sortCallback) {
        return this.addTableSortDataBlock(CallbackExecutor.execute(tableHelperClass, tableAlias, sortCallback, getConfiguration().getSqlBuilder()));
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> orderBy(Class<S> tableHelperClass, SortCallback<SS> sortCallback) {
        return CallbackCrudBlock.super.orderBy(tableHelperClass, sortCallback);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> where(WhereCallback<TW> whereCallback) {
        return this.addTableWhereDataBlock(CallbackExecutor.execute(getTableHelperClass(), getTableAlias(), whereCallback, getConfiguration().getSqlBuilder()));
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> where(Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<TW, SW> whereJoinCallback) {
        return this.addTableWhereDataBlock(CallbackExecutor.execute(getTableHelperClass(), getTableAlias(), tableHelperClass, tableAlias, whereJoinCallback, getConfiguration().getSqlBuilder()));
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> where(Class<S> tableHelperClass, WhereJoinCallback<TW, SW> whereJoinCallback) {
        return CallbackCrudBlock.super.where(tableHelperClass, whereJoinCallback);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> limit(Long limit) {
        return this.setLimit(limit);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> offset(Long offset) {
        return this.setOffset(offset);
    }

    @Override
    default <FC extends ColumnHelper<FC>> CrudEngine<T, TC, TO, TW, TG, TH, TS> buildInsertColumn(InsertColumnBuilder<FC> insertColumnBuilder) {
        InsertColumnBuilder.execute(insertColumnBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }

    @Override
    default <FC extends ColumnHelper<FC>> CrudEngine<T, TC, TO, TW, TG, TH, TS> buildColumn(ColumnBuilder<FC> columnBuilder) {
        ColumnBuilder.execute(columnBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }

    @Override
    default <FC extends ColumnHelper<FC>> CrudEngine<T, TC, TO, TW, TG, TH, TS> buildSelectColumn(SelectColumnBuilder<FC> selectColumnBuilder) {
        SelectColumnBuilder.execute(selectColumnBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }

    @Override
    default <FO extends OnHelper<FO>> CrudEngine<T, TC, TO, TW, TG, TH, TS> buildJoin(JoinBuilder<FO> joinBuilder) {
        JoinBuilder.execute(joinBuilder, getConfiguration().getSqlBuilder()).forEach(this::addTableJoinDataBlock);
        return this;
    }

    @Override
    default <FO extends OnHelper<FO>> CrudEngine<T, TC, TO, TW, TG, TH, TS> buildOn(OnBuilder<FO> onBuilder) {
        OnBuilder.execute(onBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }

    @Override
    default <FW extends WhereHelper<FW>> CrudEngine<T, TC, TO, TW, TG, TH, TS> buildWhere(WhereBuilder<FW> whereBuilder) {
        WhereBuilder.execute(whereBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }

    @Override
    default <FG extends GroupHelper<FG>> CrudEngine<T, TC, TO, TW, TG, TH, TS> buildGroup(GroupBuilder<FG> groupBuilder) {
        GroupBuilder.execute(groupBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }

    @Override
    default <FH extends HavingHelper<FH>> CrudEngine<T, TC, TO, TW, TG, TH, TS> buildHaving(HavingBuilder<FH> havingBuilder) {
        HavingBuilder.execute(havingBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }

    @Override
    default <FS extends SortHelper<FS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> buildSort(SortBuilder<FS> sortBuilder) {
        SortBuilder.execute(sortBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }

    @Override
    default <FC extends ColumnHelper<FC>> CrudEngine<T, TC, TO, TW, TG, TH, TS> buildUpdateColumn(UpdateColumnBuilder<FC> updateColumnBuilder) {
        UpdateColumnBuilder.execute(updateColumnBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }
}