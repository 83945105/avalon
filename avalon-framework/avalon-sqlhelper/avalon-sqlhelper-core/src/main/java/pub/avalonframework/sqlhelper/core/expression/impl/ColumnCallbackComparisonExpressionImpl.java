package pub.avalonframework.sqlhelper.core.expression.impl;

import pub.avalonframework.sqlhelper.core.expression.ColumnCallbackComparisonExpression;
import pub.avalonframework.sqlhelper.core.expression.ColumnComparisonExpression;
import pub.avalonframework.sqlhelper.core.expression.lambda.ColumnLambdaCallable;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

/**
 * @author baichao
 */
public interface ColumnCallbackComparisonExpressionImpl<T> extends ColumnCallbackComparisonExpression<T>, ColumnComparisonExpression<T> {

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T eq(Class<S> tableHelperClass, String tableAlias, ColumnLambdaCallable<SC> columnLambdaCallable) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = columnLambdaCallable.apply(sc);
        return eq(sc);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T neq(Class<S> tableHelperClass, String tableAlias, ColumnLambdaCallable<SC> columnLambdaCallable) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = columnLambdaCallable.apply(sc);
        return neq(sc);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T gt(Class<S> tableHelperClass, String tableAlias, ColumnLambdaCallable<SC> columnLambdaCallable) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = columnLambdaCallable.apply(sc);
        return gt(sc);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T gte(Class<S> tableHelperClass, String tableAlias, ColumnLambdaCallable<SC> columnLambdaCallable) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = columnLambdaCallable.apply(sc);
        return gte(sc);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lt(Class<S> tableHelperClass, String tableAlias, ColumnLambdaCallable<SC> columnLambdaCallable) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = columnLambdaCallable.apply(sc);
        return lt(sc);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lte(Class<S> tableHelperClass, String tableAlias, ColumnLambdaCallable<SC> columnLambdaCallable) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = columnLambdaCallable.apply(sc);
        return lte(sc);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T bt(Class<S> tableHelperClass, String tableAlias, ColumnLambdaCallable<SC> columnLambdaCallable, ColumnLambdaCallable<SC> secondColumnCallback) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = columnLambdaCallable.apply(sc);
        SC secondSc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        secondSc = secondColumnCallback.apply(secondSc);
        return bt(sc, secondSc);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lk(Class<S> tableHelperClass, String tableAlias, ColumnLambdaCallable<SC> columnLambdaCallable) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = columnLambdaCallable.apply(sc);
        return lk(sc);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T in(Class<S> tableHelperClass, String tableAlias, ColumnLambdaCallable<SC> columnLambdaCallable) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = columnLambdaCallable.apply(sc);
        return in(sc);
    }

    @Override
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T nin(Class<S> tableHelperClass, String tableAlias, ColumnLambdaCallable<SC> columnLambdaCallable) {
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SC sc = s.newColumnHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        sc = columnLambdaCallable.apply(sc);
        return nin(sc);
    }
}