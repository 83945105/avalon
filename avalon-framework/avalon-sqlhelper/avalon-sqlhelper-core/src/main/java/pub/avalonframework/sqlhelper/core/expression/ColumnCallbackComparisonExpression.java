package pub.avalonframework.sqlhelper.core.expression;

import pub.avalonframework.sqlhelper.core.expression.lambda.ColumnLambdaCallable;
import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface ColumnCallbackComparisonExpression<T> {

    /**
     * Equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnLambdaCallable   {@link ColumnLambdaCallable}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T eq(Class<S> tableHelperClass, String tableAlias, ColumnLambdaCallable<SC> columnLambdaCallable);

    /**
     * Equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnLambdaCallable   {@link ColumnLambdaCallable}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T eq(Class<S> tableHelperClass, ColumnLambdaCallable<SC> columnLambdaCallable) {
        return eq(tableHelperClass, null, columnLambdaCallable);
    }

    /**
     * Not equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnLambdaCallable   {@link ColumnLambdaCallable}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T neq(Class<S> tableHelperClass, String tableAlias, ColumnLambdaCallable<SC> columnLambdaCallable);

    /**
     * Not equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnLambdaCallable   {@link ColumnLambdaCallable}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T neq(Class<S> tableHelperClass, ColumnLambdaCallable<SC> columnLambdaCallable) {
        return neq(tableHelperClass, null, columnLambdaCallable);
    }

    /**
     * Greater than
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnLambdaCallable   {@link ColumnLambdaCallable}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T gt(Class<S> tableHelperClass, String tableAlias, ColumnLambdaCallable<SC> columnLambdaCallable);

    /**
     * Greater than
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnLambdaCallable   {@link ColumnLambdaCallable}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T gt(Class<S> tableHelperClass, ColumnLambdaCallable<SC> columnLambdaCallable) {
        return gt(tableHelperClass, null, columnLambdaCallable);
    }

    /**
     * Greater than or equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnLambdaCallable   {@link ColumnLambdaCallable}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T gte(Class<S> tableHelperClass, String tableAlias, ColumnLambdaCallable<SC> columnLambdaCallable);

    /**
     * Greater than or equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnLambdaCallable   {@link ColumnLambdaCallable}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T gte(Class<S> tableHelperClass, ColumnLambdaCallable<SC> columnLambdaCallable) {
        return gte(tableHelperClass, null, columnLambdaCallable);
    }

    /**
     * Less than
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnLambdaCallable   {@link ColumnLambdaCallable}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lt(Class<S> tableHelperClass, String tableAlias, ColumnLambdaCallable<SC> columnLambdaCallable);

    /**
     * Less than
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnLambdaCallable   {@link ColumnLambdaCallable}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lt(Class<S> tableHelperClass, ColumnLambdaCallable<SC> columnLambdaCallable) {
        return lt(tableHelperClass, null, columnLambdaCallable);
    }

    /**
     * Less than or equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnLambdaCallable   {@link ColumnLambdaCallable}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lte(Class<S> tableHelperClass, String tableAlias, ColumnLambdaCallable<SC> columnLambdaCallable);

    /**
     * Less than or equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnLambdaCallable   {@link ColumnLambdaCallable}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lte(Class<S> tableHelperClass, ColumnLambdaCallable<SC> columnLambdaCallable) {
        return lte(tableHelperClass, null, columnLambdaCallable);
    }

    /**
     * Between ... and ...
     *
     * @param tableHelperClass     extends {@link TableHelper} class
     * @param tableAlias           table alias
     * @param columnLambdaCallable       {@link ColumnLambdaCallable}
     * @param secondColumnCallback {@link ColumnLambdaCallable}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T bt(Class<S> tableHelperClass, String tableAlias, ColumnLambdaCallable<SC> columnLambdaCallable, ColumnLambdaCallable<SC> secondColumnCallback);

    /**
     * Between ... and ...
     *
     * @param tableHelperClass     extends {@link TableHelper} class
     * @param columnLambdaCallable       {@link ColumnLambdaCallable}
     * @param secondColumnCallback {@link ColumnLambdaCallable}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T bt(Class<S> tableHelperClass, ColumnLambdaCallable<SC> columnLambdaCallable, ColumnLambdaCallable<SC> secondColumnCallback) {
        return bt(tableHelperClass, null, columnLambdaCallable, secondColumnCallback);
    }

    /**
     * Like
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnLambdaCallable   {@link ColumnLambdaCallable}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lk(Class<S> tableHelperClass, String tableAlias, ColumnLambdaCallable<SC> columnLambdaCallable);

    /**
     * Like
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnLambdaCallable   {@link ColumnLambdaCallable}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lk(Class<S> tableHelperClass, ColumnLambdaCallable<SC> columnLambdaCallable) {
        return lk(tableHelperClass, null, columnLambdaCallable);
    }

    /**
     * In
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnLambdaCallable   {@link ColumnLambdaCallable}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T in(Class<S> tableHelperClass, String tableAlias, ColumnLambdaCallable<SC> columnLambdaCallable);

    /**
     * In
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnLambdaCallable   {@link ColumnLambdaCallable}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T in(Class<S> tableHelperClass, ColumnLambdaCallable<SC> columnLambdaCallable) {
        return in(tableHelperClass, null, columnLambdaCallable);
    }

    /**
     * Not in
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnLambdaCallable   {@link ColumnLambdaCallable}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T nin(Class<S> tableHelperClass, String tableAlias, ColumnLambdaCallable<SC> columnLambdaCallable);

    /**
     * Not in
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnLambdaCallable   {@link ColumnLambdaCallable}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T nin(Class<S> tableHelperClass, ColumnLambdaCallable<SC> columnLambdaCallable) {
        return nin(tableHelperClass, null, columnLambdaCallable);
    }
}