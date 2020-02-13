package pub.avalonframework.sqlhelper.core.rules;

import pub.avalonframework.sqlhelper.core.callback.ColumnCallback;
import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface ToColumnCallbackComparisonOperator<T> {

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
            SS extends SortHelper<SS>> T equalTo(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

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
            SS extends SortHelper<SS>> T equalTo(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return equalTo(tableHelperClass, null, columnCallback);
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
            SS extends SortHelper<SS>> T notEqualTo(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

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
            SS extends SortHelper<SS>> T notEqualTo(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return notEqualTo(tableHelperClass, null, columnCallback);
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
            SS extends SortHelper<SS>> T greaterThan(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

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
            SS extends SortHelper<SS>> T greaterThan(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return greaterThan(tableHelperClass, null, columnCallback);
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
            SS extends SortHelper<SS>> T greaterThanAndEqualTo(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

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
            SS extends SortHelper<SS>> T greaterThanAndEqualTo(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return greaterThanAndEqualTo(tableHelperClass, null, columnCallback);
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
            SS extends SortHelper<SS>> T lessThan(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

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
            SS extends SortHelper<SS>> T lessThan(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return lessThan(tableHelperClass, null, columnCallback);
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
            SS extends SortHelper<SS>> T lessThanAndEqualTo(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

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
            SS extends SortHelper<SS>> T lessThanAndEqualTo(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return lessThanAndEqualTo(tableHelperClass, null, columnCallback);
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
            SS extends SortHelper<SS>> T between(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback, ColumnCallback<SC> secondColumnCallback);

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
            SS extends SortHelper<SS>> T between(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback, ColumnCallback<SC> secondColumnCallback) {
        return between(tableHelperClass, null, columnCallback, secondColumnCallback);
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
            SS extends SortHelper<SS>> T like(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

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
            SS extends SortHelper<SS>> T like(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return like(tableHelperClass, null, columnCallback);
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
            SS extends SortHelper<SS>> T notIn(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

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
            SS extends SortHelper<SS>> T notIn(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return notIn(tableHelperClass, null, columnCallback);
    }
}