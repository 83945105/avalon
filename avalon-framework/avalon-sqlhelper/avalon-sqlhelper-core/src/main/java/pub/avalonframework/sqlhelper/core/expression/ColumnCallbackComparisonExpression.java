package pub.avalonframework.sqlhelper.core.expression;

import pub.avalonframework.sqlhelper.core.callback.ColumnCallback;
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
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T eq(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

    /**
     * Equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T eq(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return eq(tableHelperClass, null, columnCallback);
    }

    /**
     * Not equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T neq(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

    /**
     * Not equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T neq(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return neq(tableHelperClass, null, columnCallback);
    }

    /**
     * Greater than
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T gt(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

    /**
     * Greater than
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T gt(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return gt(tableHelperClass, null, columnCallback);
    }

    /**
     * Greater than or equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T gte(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

    /**
     * Greater than or equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T gte(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return gte(tableHelperClass, null, columnCallback);
    }

    /**
     * Less than
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lt(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

    /**
     * Less than
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lt(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return lt(tableHelperClass, null, columnCallback);
    }

    /**
     * Less than or equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lte(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

    /**
     * Less than or equal to
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lte(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return lte(tableHelperClass, null, columnCallback);
    }

    /**
     * Between ... and ...
     *
     * @param tableHelperClass     extends {@link TableHelper} class
     * @param tableAlias           table alias
     * @param columnCallback       {@link ColumnCallback}
     * @param secondColumnCallback {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T bt(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback, ColumnCallback<SC> secondColumnCallback);

    /**
     * Between ... and ...
     *
     * @param tableHelperClass     extends {@link TableHelper} class
     * @param columnCallback       {@link ColumnCallback}
     * @param secondColumnCallback {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T bt(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback, ColumnCallback<SC> secondColumnCallback) {
        return bt(tableHelperClass, null, columnCallback, secondColumnCallback);
    }

    /**
     * Like
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lk(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

    /**
     * Like
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T lk(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return lk(tableHelperClass, null, columnCallback);
    }

    /**
     * In
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T in(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

    /**
     * In
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T in(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return in(tableHelperClass, null, columnCallback);
    }

    /**
     * Not in
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T nin(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

    /**
     * Not in
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnCallback   {@link ColumnCallback}
     * @return extends {@link Helper} object
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> T nin(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return nin(tableHelperClass, null, columnCallback);
    }
}