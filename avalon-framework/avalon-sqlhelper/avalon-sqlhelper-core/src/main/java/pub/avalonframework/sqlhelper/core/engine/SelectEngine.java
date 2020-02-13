package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.sqlhelper.core.beans.GroupType;
import pub.avalonframework.sqlhelper.core.beans.JoinType;
import pub.avalonframework.sqlhelper.core.block.LimitBlock;
import pub.avalonframework.sqlhelper.core.block.callback.CallbackSelectBlock;
import pub.avalonframework.sqlhelper.core.block.callback.executor.CallbackBlockExecutor;
import pub.avalonframework.sqlhelper.core.block.helper.HelperSelectBlock;
import pub.avalonframework.sqlhelper.core.builder.*;
import pub.avalonframework.sqlhelper.core.callback.*;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.inject.SelectInjector;
import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface SelectEngine<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
        TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends
        Engine<T, TC, TO, TW, TG, TH, TS>,
        SelectInjector,
        HelperSelectBlock<SelectEngine<T, TC, TO, TW, TG, TH, TS>>,
        CallbackSelectBlock<TC, TO, TW, TG, TH, TS, SelectEngine<T, TC, TO, TW, TG, TH, TS>>,
        LimitBlock<SelectEngine<T, TC, TO, TW, TG, TH, TS>>,
        SelectBuilder<SelectEngine<T, TC, TO, TW, TG, TH, TS>> {

    @Override
    default SelectEngine<T, TC, TO, TW, TG, TH, TS> select(ColumnHelper<?>... columnHelpers) {
        ColumnHelper.execute(columnHelpers).forEach(this::addSelectTableColumnDataBlock);
        return this;
    }

    @Override
    default SelectEngine<T, TC, TO, TW, TG, TH, TS> virtualColumn(Object columnValue, String columnAlias) {
        this.addSelectTableColumnDataBlock(CallbackBlockExecutor.executeVirtualColumn(getTableAlias(), columnValue, columnAlias));
        return this;
    }

    @Override
    default SelectEngine<T, TC, TO, TW, TG, TH, TS> on(OnHelper<?>... onHelpers) {
        OnHelper.execute(onHelpers).forEach(this::addTableOnDataBlock);
        return this;
    }

    @Override
    default SelectEngine<T, TC, TO, TW, TG, TH, TS> where(WhereHelper<?>... whereHelpers) {
        WhereHelper.execute(whereHelpers).forEach(this::addTableWhereDataBlock);
        return this;
    }

    @Override
    default SelectEngine<T, TC, TO, TW, TG, TH, TS> groupBy(GroupHelper<?>... groupHelpers) {
        GroupHelper.execute(groupHelpers).forEach(this::addTableGroupDataBlock);
        return this;
    }

    @Override
    default SelectEngine<T, TC, TO, TW, TG, TH, TS> having(HavingHelper<?>... havingHelpers) {
        HavingHelper.execute(havingHelpers).forEach(this::addTableHavingDataBlock);
        return this;
    }

    @Override
    default SelectEngine<T, TC, TO, TW, TG, TH, TS> orderBy(SortHelper<?>... sortHelpers) {
        SortHelper.execute(sortHelpers).forEach(this::addTableSortDataBlock);
        return this;
    }

    @Override
    default SelectEngine<T, TC, TO, TW, TG, TH, TS> select(ColumnCallback<TC> columnCallback) {
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
            SS extends SortHelper<SS>> SelectEngine<T, TC, TO, TW, TG, TH, TS> select(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        this.addSelectTableColumnDataBlock(CallbackExecutor.execute(tableHelperClass, tableAlias, columnCallback, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default SelectEngine<T, TC, TO, TW, TG, TH, TS> groupColumn(GroupType groupType, ColumnCallback<TC> columnCallback) {
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
            SS extends SortHelper<SS>> SelectEngine<T, TC, TO, TW, TG, TH, TS> groupColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<SC> columnCallback) {
        this.addSelectTableColumnDataBlock(CallbackBlockExecutor.executeGroupColumn(tableHelperClass, tableAlias, groupType, columnCallback, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default SelectEngine<T, TC, TO, TW, TG, TH, TS> subQueryColumn(String columnAlias, SubQueryColumnCallback<TC> subQueryColumnCallback) {
        this.addSelectTableColumnDataBlock(CallbackBlockExecutor.executeSubQueryColumn(getTableHelperClass(), getTableAlias(), columnAlias, subQueryColumnCallback, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SelectEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        this.addTableJoinDataBlock(CallbackBlockExecutor.execute(joinType, getTableHelperClass(), getTableAlias(), tableName, tableHelperClass, tableAlias, onJoinCallback, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default SelectEngine<T, TC, TO, TW, TG, TH, TS> on(OnCallback<TO> onCallback) {
        this.addTableOnDataBlock(CallbackExecutor.execute(getTableHelperClass(), getTableAlias(), onCallback, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SelectEngine<T, TC, TO, TW, TG, TH, TS> on(Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        this.addTableOnDataBlock(CallbackExecutor.execute(getTableHelperClass(), getTableAlias(), tableHelperClass, tableAlias, onJoinCallback, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default SelectEngine<T, TC, TO, TW, TG, TH, TS> where(WhereCallback<TW> whereCallback) {
        this.addTableWhereDataBlock(CallbackExecutor.execute(getTableHelperClass(), getTableAlias(), whereCallback, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SelectEngine<T, TC, TO, TW, TG, TH, TS> where(Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<TW, SW> whereJoinCallback) {
        this.addTableWhereDataBlock(CallbackExecutor.execute(getTableHelperClass(), getTableAlias(), tableHelperClass, tableAlias, whereJoinCallback, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default SelectEngine<T, TC, TO, TW, TG, TH, TS> groupBy(GroupCallback<TG> groupCallback) {
        this.addTableGroupDataBlock(CallbackExecutor.execute(getTableHelperClass(), getTableAlias(), groupCallback, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SelectEngine<T, TC, TO, TW, TG, TH, TS> groupBy(Class<S> tableHelperClass, String tableAlias, GroupCallback<SG> groupCallback) {
        this.addTableGroupDataBlock(CallbackExecutor.execute(tableHelperClass, tableAlias, groupCallback, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default SelectEngine<T, TC, TO, TW, TG, TH, TS> having(HavingCallback<TH> havingCallback) {
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
            SS extends SortHelper<SS>> SelectEngine<T, TC, TO, TW, TG, TH, TS> having(Class<S> tableHelperClass, String tableAlias, HavingJoinCallback<TH, SH> havingJoinCallback) {
        this.addTableHavingDataBlock(CallbackExecutor.execute(getTableHelperClass(), getTableAlias(), tableHelperClass, tableAlias, havingJoinCallback, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default SelectEngine<T, TC, TO, TW, TG, TH, TS> orderBy(SortCallback<TS> sortCallback) {
        this.addTableSortDataBlock(CallbackExecutor.execute(getTableHelperClass(), getTableAlias(), sortCallback, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SelectEngine<T, TC, TO, TW, TG, TH, TS> orderBy(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> sortCallback) {
        this.addTableSortDataBlock(CallbackExecutor.execute(tableHelperClass, tableAlias, sortCallback, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default SelectEngine<T, TC, TO, TW, TG, TH, TS> limit(Long limit) {
        this.setLimit(limit);
        return this;
    }

    @Override
    default SelectEngine<T, TC, TO, TW, TG, TH, TS> offset(Long offset) {
        this.setOffset(offset);
        return this;
    }

    @Override
    default <FC extends ColumnHelper<FC>> SelectEngine<T, TC, TO, TW, TG, TH, TS> buildSelectColumn(SelectColumnBuilder<FC> selectColumnBuilder) {
        SelectColumnBuilder.execute(selectColumnBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }

    @Override
    default <FC extends ColumnHelper<FC>> SelectEngine<T, TC, TO, TW, TG, TH, TS> buildColumn(ColumnBuilder<FC> columnBuilder) {
        ColumnBuilder.execute(columnBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }

    @Override
    default <FO extends OnHelper<FO>> SelectEngine<T, TC, TO, TW, TG, TH, TS> buildJoin(JoinBuilder<FO> joinBuilder) {
        JoinBuilder.execute(joinBuilder, getConfiguration().getSqlBuilder()).forEach(this::addTableJoinDataBlock);
        return this;
    }

    @Override
    default <FO extends OnHelper<FO>> SelectEngine<T, TC, TO, TW, TG, TH, TS> buildOn(OnBuilder<FO> onBuilder) {
        OnBuilder.execute(onBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }

    @Override
    default <FW extends WhereHelper<FW>> SelectEngine<T, TC, TO, TW, TG, TH, TS> buildWhere(WhereBuilder<FW> whereBuilder) {
        WhereBuilder.execute(whereBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }

    @Override
    default <FG extends GroupHelper<FG>> SelectEngine<T, TC, TO, TW, TG, TH, TS> buildGroup(GroupBuilder<FG> groupBuilder) {
        GroupBuilder.execute(groupBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }

    @Override
    default <FH extends HavingHelper<FH>> SelectEngine<T, TC, TO, TW, TG, TH, TS> buildHaving(HavingBuilder<FH> havingBuilder) {
        HavingBuilder.execute(havingBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }

    @Override
    default <FS extends SortHelper<FS>> SelectEngine<T, TC, TO, TW, TG, TH, TS> buildSort(SortBuilder<FS> sortBuilder) {
        SortBuilder.execute(sortBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }
}