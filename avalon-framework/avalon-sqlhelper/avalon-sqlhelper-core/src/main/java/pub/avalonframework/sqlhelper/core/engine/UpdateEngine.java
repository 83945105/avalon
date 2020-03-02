package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.*;
import pub.avalonframework.sqlhelper.core.data.inject.UpdateInjector;
import pub.avalonframework.sqlhelper.core.expression.UpdateExpression;
import pub.avalonframework.sqlhelper.core.expression.builder.*;
import pub.avalonframework.sqlhelper.core.expression.lambda.*;
import pub.avalonframework.sqlhelper.core.expression.lambda.execute.LambdaCallableExecutor;
import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface UpdateEngine<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
        TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends
        Engine<T, TC, TO, TW, TG, TH, TS>,
        UpdateInjector<UpdateEngine<T, TC, TO, TW, TG, TH, TS>>,
        UpdateExpression<UpdateEngine<T, TC, TO, TW, TG, TH, TS>>,
        UpdateLambdaExpression<TC, TO, TW, UpdateEngine<T, TC, TO, TW, TG, TH, TS>>,
        UpdateExpressionBuilder<UpdateEngine<T, TC, TO, TW, TG, TH, TS>> {

    @Override
    default UpdateEngine<T, TC, TO, TW, TG, TH, TS> setConfiguration(SqlhelperConfiguration configuration) {
        return UpdateInjector.super.setConfiguration(configuration);
    }

    @Override
    default UpdateEngine<T, TC, TO, TW, TG, TH, TS> addTableJoinDataBlock(TableJoinDataBlock tableJoinDataBlock) {
        return UpdateInjector.super.addTableJoinDataBlock(tableJoinDataBlock);
    }

    @Override
    default UpdateEngine<T, TC, TO, TW, TG, TH, TS> addUpdateTableColumnDataBlock(TableColumnDataBlock tableColumnDataBlock) {
        return UpdateInjector.super.addUpdateTableColumnDataBlock(tableColumnDataBlock);
    }

    @Override
    default UpdateEngine<T, TC, TO, TW, TG, TH, TS> addTableOnDataBlock(TableOnDataBlock tableOnDataBlock) {
        return UpdateInjector.super.addTableOnDataBlock(tableOnDataBlock);
    }

    @Override
    default UpdateEngine<T, TC, TO, TW, TG, TH, TS> addTableWhereDataBlock(TableWhereDataBlock tableWhereDataBlock) {
        return UpdateInjector.super.addTableWhereDataBlock(tableWhereDataBlock);
    }

    @Override
    default UpdateEngine<T, TC, TO, TW, TG, TH, TS> update(ColumnHelper<?>... columnHelpers) {
        ColumnHelper.execute(columnHelpers).forEach(this::addUpdateTableColumnDataBlock);
        return this;
    }

    @Override
    default UpdateEngine<T, TC, TO, TW, TG, TH, TS> on(OnHelper<?>... onHelpers) {
        OnHelper.execute(onHelpers).forEach(this::addTableOnDataBlock);
        return this;
    }

    @Override
    default UpdateEngine<T, TC, TO, TW, TG, TH, TS> where(WhereHelper<?>... whereHelpers) {
        WhereHelper.execute(whereHelpers).forEach(this::addTableWhereDataBlock);
        return this;
    }

    @Override
    default UpdateEngine<T, TC, TO, TW, TG, TH, TS> update(ColumnLambdaCallable<TC> columnLambdaCallable) {
        return this.addUpdateTableColumnDataBlock(LambdaCallableExecutor.execute(getTableHelperClass(), getTableAlias(), columnLambdaCallable, getConfiguration().getSqlBuilder()));
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
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
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias) {
        return UpdateLambdaExpression.super.join(joinType, tableName, tableHelperClass, tableAlias);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return UpdateLambdaExpression.super.join(joinType, tableName, tableHelperClass, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass) {
        return UpdateLambdaExpression.super.join(joinType, tableName, tableHelperClass);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return UpdateLambdaExpression.super.join(joinType, tableHelperClass, tableAlias, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, Class<S> tableHelperClass, String tableAlias) {
        return UpdateLambdaExpression.super.join(joinType, tableHelperClass, tableAlias);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return UpdateLambdaExpression.super.join(joinType, tableHelperClass, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, Class<S> tableHelperClass) {
        return UpdateLambdaExpression.super.join(joinType, tableHelperClass);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return UpdateLambdaExpression.super.innerJoin(tableName, tableHelperClass, tableAlias, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(String tableName, Class<S> tableHelperClass, String tableAlias) {
        return UpdateLambdaExpression.super.innerJoin(tableName, tableHelperClass, tableAlias);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(String tableName, Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return UpdateLambdaExpression.super.innerJoin(tableName, tableHelperClass, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(String tableName, Class<S> tableHelperClass) {
        return UpdateLambdaExpression.super.innerJoin(tableName, tableHelperClass);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return UpdateLambdaExpression.super.innerJoin(tableHelperClass, tableAlias, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(Class<S> tableHelperClass, String tableAlias) {
        return UpdateLambdaExpression.super.innerJoin(tableHelperClass, tableAlias);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return UpdateLambdaExpression.super.innerJoin(tableHelperClass, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(Class<S> tableHelperClass) {
        return UpdateLambdaExpression.super.innerJoin(tableHelperClass);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return UpdateLambdaExpression.super.leftJoin(tableName, tableHelperClass, tableAlias, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(String tableName, Class<S> tableHelperClass, String tableAlias) {
        return UpdateLambdaExpression.super.leftJoin(tableName, tableHelperClass, tableAlias);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(String tableName, Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return UpdateLambdaExpression.super.leftJoin(tableName, tableHelperClass, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(String tableName, Class<S> tableHelperClass) {
        return UpdateLambdaExpression.super.leftJoin(tableName, tableHelperClass);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return UpdateLambdaExpression.super.leftJoin(tableHelperClass, tableAlias, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(Class<S> tableHelperClass, String tableAlias) {
        return UpdateLambdaExpression.super.leftJoin(tableHelperClass, tableAlias);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return UpdateLambdaExpression.super.leftJoin(tableHelperClass, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(Class<S> tableHelperClass) {
        return UpdateLambdaExpression.super.leftJoin(tableHelperClass);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return UpdateLambdaExpression.super.rightJoin(tableName, tableHelperClass, tableAlias, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(String tableName, Class<S> tableHelperClass, String tableAlias) {
        return UpdateLambdaExpression.super.rightJoin(tableName, tableHelperClass, tableAlias);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(String tableName, Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return UpdateLambdaExpression.super.rightJoin(tableName, tableHelperClass, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(String tableName, Class<S> tableHelperClass) {
        return UpdateLambdaExpression.super.rightJoin(tableName, tableHelperClass);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return UpdateLambdaExpression.super.rightJoin(tableHelperClass, tableAlias, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(Class<S> tableHelperClass, String tableAlias) {
        return UpdateLambdaExpression.super.rightJoin(tableHelperClass, tableAlias);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return UpdateLambdaExpression.super.rightJoin(tableHelperClass, onJoinLambdaCallable);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(Class<S> tableHelperClass) {
        return UpdateLambdaExpression.super.rightJoin(tableHelperClass);
    }

    @Override
    default UpdateEngine<T, TC, TO, TW, TG, TH, TS> on(OnLambdaCallable<TO> onLambdaCallable) {
        return this.addTableOnDataBlock(LambdaCallableExecutor.execute(getTableHelperClass(), getTableAlias(), onLambdaCallable, getConfiguration().getSqlBuilder()));
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> on(Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return this.addTableOnDataBlock(LambdaCallableExecutor.execute(getTableHelperClass(), getTableAlias(), tableHelperClass, tableAlias, onJoinLambdaCallable, getConfiguration().getSqlBuilder()));
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> on(Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return UpdateLambdaExpression.super.on(tableHelperClass, onJoinLambdaCallable);
    }

    @Override
    default UpdateEngine<T, TC, TO, TW, TG, TH, TS> where(WhereLambdaCallable<TW> whereLambdaCallable) {
        return this.addTableWhereDataBlock(LambdaCallableExecutor.execute(getTableHelperClass(), getTableAlias(), whereLambdaCallable, getConfiguration().getSqlBuilder()));
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> where(Class<S> tableHelperClass, String tableAlias, WhereJoinLambdaCallable<TW, SW> whereJoinLambdaCallable) {
        return this.addTableWhereDataBlock(LambdaCallableExecutor.execute(getTableHelperClass(), getTableAlias(), tableHelperClass, tableAlias, whereJoinLambdaCallable, getConfiguration().getSqlBuilder()));
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> where(Class<S> tableHelperClass, WhereJoinLambdaCallable<TW, SW> whereJoinLambdaCallable) {
        return UpdateLambdaExpression.super.where(tableHelperClass, whereJoinLambdaCallable);
    }

    @Override
    default <FO extends OnHelper<FO>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> buildJoinExpression(JoinBuilder<FO> joinBuilder) {
        JoinBuilder.execute(joinBuilder, getConfiguration().getSqlBuilder()).forEach(this::addTableJoinDataBlock);
        return this;
    }

    @Override
    default <FO extends OnHelper<FO>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> buildOnExpression(OnBuilder<FO> onBuilder) {
        OnBuilder.execute(onBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }

    @Override
    default <FW extends WhereHelper<FW>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> buildWhereExpression(WhereBuilder<FW> whereBuilder) {
        WhereBuilder.execute(whereBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }

    @Override
    default <FC extends ColumnHelper<FC>> UpdateEngine<T, TC, TO, TW, TG, TH, TS> buildUpdateColumnExpression(UpdateColumnBuilder<FC> updateColumnBuilder) {
        UpdateColumnBuilder.execute(updateColumnBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }
}