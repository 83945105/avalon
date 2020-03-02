package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.builder.*;
import pub.avalonframework.sqlhelper.core.data.block.*;
import pub.avalonframework.sqlhelper.core.data.inject.CrudInjector;
import pub.avalonframework.sqlhelper.core.expression.CrudExpression;
import pub.avalonframework.sqlhelper.core.expression.LimitExpression;
import pub.avalonframework.sqlhelper.core.expression.builder.*;
import pub.avalonframework.sqlhelper.core.expression.lambda.*;
import pub.avalonframework.sqlhelper.core.expression.lambda.execute.LambdaCallableExecutor;
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
        CrudExpression<CrudEngine<T, TC, TO, TW, TG, TH, TS>>,
        CrudLambdaExpression<TC, TO, TW, TG, TH, TS, CrudEngine<T, TC, TO, TW, TG, TH, TS>>,
        LimitExpression<CrudEngine<T, TC, TO, TW, TG, TH, TS>>,
        CrudExpressionBuilder<CrudEngine<T, TC, TO, TW, TG, TH, TS>> {

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
        return this.addSelectTableColumnDataBlock(LambdaCallableExecutor.executeVirtualColumn(getTableAlias(), columnValue, columnAlias));
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
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> insert(ColumnLambdaCallable<TC> columnLambdaCallable) {
        return this.addInsertTableColumnDataBlock(LambdaCallableExecutor.execute(getTableHelperClass(), getTableAlias(), columnLambdaCallable, getConfiguration().getSqlBuilder()));
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> select(ColumnLambdaCallable<TC> columnLambdaCallable) {
        this.addSelectTableColumnDataBlock(LambdaCallableExecutor.execute(getTableHelperClass(), getTableAlias(), columnLambdaCallable, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> select(Class<S> tableHelperClass, String tableAlias, ColumnLambdaCallable<SC> columnLambdaCallable) {
        this.addSelectTableColumnDataBlock(LambdaCallableExecutor.execute(tableHelperClass, tableAlias, columnLambdaCallable, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> select(Class<S> tableHelperClass, ColumnLambdaCallable<SC> columnLambdaCallable) {
        return CrudLambdaExpression.super.select(tableHelperClass, columnLambdaCallable);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> groupColumn(GroupType groupType, ColumnLambdaCallable<TC> columnLambdaCallable) {
        this.addSelectTableColumnDataBlock(LambdaCallableExecutor.executeGroupColumn(getTableHelperClass(), getTableAlias(), groupType, columnLambdaCallable, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> groupColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnLambdaCallable<SC> columnLambdaCallable) {
        this.addSelectTableColumnDataBlock(LambdaCallableExecutor.executeGroupColumn(tableHelperClass, tableAlias, groupType, columnLambdaCallable, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> groupColumn(Class<S> tableHelperClass, GroupType groupType, ColumnLambdaCallable<SC> columnLambdaCallable) {
        return CrudLambdaExpression.super.groupColumn(tableHelperClass, groupType, columnLambdaCallable);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> subQueryColumn(String columnAlias, SubQueryColumnLambdaCallable<TC> subQueryColumnLambdaCallable) {
        return this.addSelectTableColumnDataBlock(LambdaCallableExecutor.executeSubQueryColumn(getTableHelperClass(), getTableAlias(), columnAlias, subQueryColumnLambdaCallable, getConfiguration().getSqlBuilder()));
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> groupBy(GroupLambdaCallable<TG> groupLambdaCallable) {
        return this.addTableGroupDataBlock(LambdaCallableExecutor.execute(getTableHelperClass(), getTableAlias(), groupLambdaCallable, getConfiguration().getSqlBuilder()));
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> groupBy(Class<S> tableHelperClass, String tableAlias, GroupLambdaCallable<SG> groupLambdaCallable) {
        this.addTableGroupDataBlock(LambdaCallableExecutor.execute(tableHelperClass, tableAlias, groupLambdaCallable, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> groupBy(Class<S> tableHelperClass, GroupLambdaCallable<SG> groupLambdaCallable) {
        return CrudLambdaExpression.super.groupBy(tableHelperClass, groupLambdaCallable);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> having(HavingLambdaCallable<TH> havingLambdaCallable) {
        this.addTableHavingDataBlock(LambdaCallableExecutor.execute(getTableHelperClass(), getTableAlias(), havingLambdaCallable, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> having(Class<S> tableHelperClass, String tableAlias, HavingJoinLambdaCallable<TH, SH> havingJoinLambdaCallable) {
        this.addTableHavingDataBlock(LambdaCallableExecutor.execute(getTableHelperClass(), getTableAlias(), tableHelperClass, tableAlias, havingJoinLambdaCallable, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> having(Class<S> tableHelperClass, HavingJoinLambdaCallable<TH, SH> havingJoinLambdaCallable) {
        return CrudLambdaExpression.super.having(tableHelperClass, havingJoinLambdaCallable);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> update(ColumnLambdaCallable<TC> columnLambdaCallable) {
        return this.addUpdateTableColumnDataBlock(LambdaCallableExecutor.execute(getTableHelperClass(), getTableAlias(), columnLambdaCallable, getConfiguration().getSqlBuilder()));
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        this.addTableJoinDataBlock(LambdaCallableExecutor.execute(joinType, getTableHelperClass(), getTableAlias(), tableName, tableHelperClass, tableAlias, onJoinLambdaCallable, getConfiguration().getSqlBuilder()));
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
        return CrudLambdaExpression.super.join(joinType, tableName, tableHelperClass, tableAlias);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return CrudLambdaExpression.super.join(joinType, tableName, tableHelperClass, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass) {
        return CrudLambdaExpression.super.join(joinType, tableName, tableHelperClass);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return CrudLambdaExpression.super.join(joinType, tableHelperClass, tableAlias, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, Class<S> tableHelperClass, String tableAlias) {
        return CrudLambdaExpression.super.join(joinType, tableHelperClass, tableAlias);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return CrudLambdaExpression.super.join(joinType, tableHelperClass, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, Class<S> tableHelperClass) {
        return CrudLambdaExpression.super.join(joinType, tableHelperClass);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return CrudLambdaExpression.super.innerJoin(tableName, tableHelperClass, tableAlias, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(String tableName, Class<S> tableHelperClass, String tableAlias) {
        return CrudLambdaExpression.super.innerJoin(tableName, tableHelperClass, tableAlias);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(String tableName, Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return CrudLambdaExpression.super.innerJoin(tableName, tableHelperClass, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(String tableName, Class<S> tableHelperClass) {
        return CrudLambdaExpression.super.innerJoin(tableName, tableHelperClass);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return CrudLambdaExpression.super.innerJoin(tableHelperClass, tableAlias, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(Class<S> tableHelperClass, String tableAlias) {
        return CrudLambdaExpression.super.innerJoin(tableHelperClass, tableAlias);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return CrudLambdaExpression.super.innerJoin(tableHelperClass, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(Class<S> tableHelperClass) {
        return CrudLambdaExpression.super.innerJoin(tableHelperClass);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return CrudLambdaExpression.super.leftJoin(tableName, tableHelperClass, tableAlias, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(String tableName, Class<S> tableHelperClass, String tableAlias) {
        return CrudLambdaExpression.super.leftJoin(tableName, tableHelperClass, tableAlias);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(String tableName, Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return CrudLambdaExpression.super.leftJoin(tableName, tableHelperClass, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(String tableName, Class<S> tableHelperClass) {
        return CrudLambdaExpression.super.leftJoin(tableName, tableHelperClass);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return CrudLambdaExpression.super.leftJoin(tableHelperClass, tableAlias, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(Class<S> tableHelperClass, String tableAlias) {
        return CrudLambdaExpression.super.leftJoin(tableHelperClass, tableAlias);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return CrudLambdaExpression.super.leftJoin(tableHelperClass, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(Class<S> tableHelperClass) {
        return CrudLambdaExpression.super.leftJoin(tableHelperClass);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return CrudLambdaExpression.super.rightJoin(tableName, tableHelperClass, tableAlias, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(String tableName, Class<S> tableHelperClass, String tableAlias) {
        return CrudLambdaExpression.super.rightJoin(tableName, tableHelperClass, tableAlias);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(String tableName, Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return CrudLambdaExpression.super.rightJoin(tableName, tableHelperClass, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(String tableName, Class<S> tableHelperClass) {
        return CrudLambdaExpression.super.rightJoin(tableName, tableHelperClass);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return CrudLambdaExpression.super.rightJoin(tableHelperClass, tableAlias, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(Class<S> tableHelperClass, String tableAlias) {
        return CrudLambdaExpression.super.rightJoin(tableHelperClass, tableAlias);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return CrudLambdaExpression.super.rightJoin(tableHelperClass, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(Class<S> tableHelperClass) {
        return CrudLambdaExpression.super.rightJoin(tableHelperClass);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> on(OnLambdaCallable<TO> onLambdaCallable) {
        return this.addTableOnDataBlock(LambdaCallableExecutor.execute(getTableHelperClass(), getTableAlias(), onLambdaCallable, getConfiguration().getSqlBuilder()));
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> on(Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return this.addTableOnDataBlock(LambdaCallableExecutor.execute(getTableHelperClass(), getTableAlias(), tableHelperClass, tableAlias, onJoinLambdaCallable, getConfiguration().getSqlBuilder()));
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> on(Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return CrudLambdaExpression.super.on(tableHelperClass, onJoinLambdaCallable);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> orderBy(SortLambdaCallable<TS> sortLambdaCallable) {
        return this.addTableSortDataBlock(LambdaCallableExecutor.execute(getTableHelperClass(), getTableAlias(), sortLambdaCallable, getConfiguration().getSqlBuilder()));
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> orderBy(Class<S> tableHelperClass, String tableAlias, SortLambdaCallable<SS> sortLambdaCallable) {
        return this.addTableSortDataBlock(LambdaCallableExecutor.execute(tableHelperClass, tableAlias, sortLambdaCallable, getConfiguration().getSqlBuilder()));
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> orderBy(Class<S> tableHelperClass, SortLambdaCallable<SS> sortLambdaCallable) {
        return CrudLambdaExpression.super.orderBy(tableHelperClass, sortLambdaCallable);
    }

    @Override
    default CrudEngine<T, TC, TO, TW, TG, TH, TS> where(WhereLambdaCallable<TW> whereLambdaCallable) {
        return this.addTableWhereDataBlock(LambdaCallableExecutor.execute(getTableHelperClass(), getTableAlias(), whereLambdaCallable, getConfiguration().getSqlBuilder()));
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> where(Class<S> tableHelperClass, String tableAlias, WhereJoinLambdaCallable<TW, SW> whereJoinLambdaCallable) {
        return this.addTableWhereDataBlock(LambdaCallableExecutor.execute(getTableHelperClass(), getTableAlias(), tableHelperClass, tableAlias, whereJoinLambdaCallable, getConfiguration().getSqlBuilder()));
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> where(Class<S> tableHelperClass, WhereJoinLambdaCallable<TW, SW> whereJoinLambdaCallable) {
        return CrudLambdaExpression.super.where(tableHelperClass, whereJoinLambdaCallable);
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
    default <FC extends ColumnHelper<FC>> CrudEngine<T, TC, TO, TW, TG, TH, TS> buildInsertColumnExpression(InsertColumnBuilder<FC> insertColumnBuilder) {
        InsertColumnBuilder.execute(insertColumnBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }

    @Override
    default <FC extends ColumnHelper<FC>> CrudEngine<T, TC, TO, TW, TG, TH, TS> buildColumnExpression(ColumnBuilder<FC> columnBuilder) {
        ColumnBuilder.execute(columnBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }

    @Override
    default <FC extends ColumnHelper<FC>> CrudEngine<T, TC, TO, TW, TG, TH, TS> buildSelectColumnExpression(SelectColumnBuilder<FC> selectColumnBuilder) {
        SelectColumnBuilder.execute(selectColumnBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }

    @Override
    default <FO extends OnHelper<FO>> CrudEngine<T, TC, TO, TW, TG, TH, TS> buildJoinExpression(JoinBuilder<FO> joinBuilder) {
        JoinBuilder.execute(joinBuilder, getConfiguration().getSqlBuilder()).forEach(this::addTableJoinDataBlock);
        return this;
    }

    @Override
    default <FO extends OnHelper<FO>> CrudEngine<T, TC, TO, TW, TG, TH, TS> buildOnExpression(OnBuilder<FO> onBuilder) {
        OnBuilder.execute(onBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }

    @Override
    default <FW extends WhereHelper<FW>> CrudEngine<T, TC, TO, TW, TG, TH, TS> buildWhereExpression(WhereBuilder<FW> whereBuilder) {
        WhereBuilder.execute(whereBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }

    @Override
    default <FG extends GroupHelper<FG>> CrudEngine<T, TC, TO, TW, TG, TH, TS> buildGroupExpression(GroupBuilder<FG> groupBuilder) {
        GroupBuilder.execute(groupBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }

    @Override
    default <FH extends HavingHelper<FH>> CrudEngine<T, TC, TO, TW, TG, TH, TS> buildHavingExpression(HavingBuilder<FH> havingBuilder) {
        HavingBuilder.execute(havingBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }

    @Override
    default <FS extends SortHelper<FS>> CrudEngine<T, TC, TO, TW, TG, TH, TS> buildSortExpression(SortBuilder<FS> sortBuilder) {
        SortBuilder.execute(sortBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }

    @Override
    default <FC extends ColumnHelper<FC>> CrudEngine<T, TC, TO, TW, TG, TH, TS> buildUpdateColumnExpression(UpdateColumnBuilder<FC> updateColumnBuilder) {
        UpdateColumnBuilder.execute(updateColumnBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }
}