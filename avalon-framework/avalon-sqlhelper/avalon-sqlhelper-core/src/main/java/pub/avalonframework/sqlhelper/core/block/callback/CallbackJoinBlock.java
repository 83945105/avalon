package pub.avalonframework.sqlhelper.core.block.callback;

import pub.avalonframework.sqlhelper.core.beans.JoinType;
import pub.avalonframework.sqlhelper.core.callback.OnJoinCallback;
import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface CallbackJoinBlock<TO extends OnHelper<TO>, R> extends CallbackBlock {

    /**
     * Use callback to add join sql data.
     *
     * @param joinType         {@link JoinType}
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param onJoinCallback   {@link OnJoinCallback}
     * @return R
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback);

    /**
     * Use callback to add join sql data.
     *
     * @param joinType         {@link JoinType}
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias) {
        return join(joinType, tableName, tableHelperClass, tableAlias, null);
    }

    /**
     * Use callback to add join sql data.
     *
     * @param joinType         {@link JoinType}
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param onJoinCallback   {@link OnJoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R join(JoinType joinType, String tableName, Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        return join(joinType, tableName, tableHelperClass, null, onJoinCallback);
    }

    /**
     * Use callback to add join sql data.
     *
     * @param joinType         {@link JoinType}
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R join(JoinType joinType, String tableName, Class<S> tableHelperClass) {
        return join(joinType, tableName, tableHelperClass, null, null);
    }

    /**
     * Use callback to add join sql data.
     *
     * @param joinType         {@link JoinType}
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param onJoinCallback   {@link OnJoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R join(JoinType joinType, Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        return join(joinType, null, tableHelperClass, tableAlias, onJoinCallback);
    }

    /**
     * Use callback to add join sql data.
     *
     * @param joinType         {@link JoinType}
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R join(JoinType joinType, Class<S> tableHelperClass, String tableAlias) {
        return join(joinType, null, tableHelperClass, tableAlias, null);
    }

    /**
     * Use callback to add join sql data.
     *
     * @param joinType         {@link JoinType}
     * @param tableHelperClass extends {@link TableHelper} class
     * @param onJoinCallback   {@link OnJoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R join(JoinType joinType, Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        return join(joinType, null, tableHelperClass, null, onJoinCallback);
    }

    /**
     * Use callback to add join sql data.
     *
     * @param joinType         {@link JoinType}
     * @param tableHelperClass extends {@link TableHelper} class
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R join(JoinType joinType, Class<S> tableHelperClass) {
        return join(joinType, null, tableHelperClass, null, null);
    }

    /**
     * Use callback to add inner join sql data.
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param onJoinCallback   {@link OnJoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R innerJoin(String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        return join(JoinType.INNER, tableName, tableHelperClass, tableAlias, onJoinCallback);
    }

    /**
     * Use callback to add inner join sql data.
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R innerJoin(String tableName, Class<S> tableHelperClass, String tableAlias) {
        return join(JoinType.INNER, tableName, tableHelperClass, tableAlias, null);
    }

    /**
     * Use callback to add inner join sql data.
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param onJoinCallback   {@link OnJoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R innerJoin(String tableName, Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        return join(JoinType.INNER, tableName, tableHelperClass, null, onJoinCallback);
    }

    /**
     * Use callback to add inner join sql data.
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R innerJoin(String tableName, Class<S> tableHelperClass) {
        return join(JoinType.INNER, tableName, tableHelperClass, null, null);
    }

    /**
     * Use callback to add inner join sql data.
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param onJoinCallback   {@link OnJoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R innerJoin(Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        return join(JoinType.INNER, null, tableHelperClass, tableAlias, onJoinCallback);
    }

    /**
     * Use callback to add inner join sql data.
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R innerJoin(Class<S> tableHelperClass, String tableAlias) {
        return join(JoinType.INNER, null, tableHelperClass, tableAlias, null);
    }

    /**
     * Use callback to add inner join sql data.
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param onJoinCallback   {@link OnJoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R innerJoin(Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        return join(JoinType.INNER, null, tableHelperClass, null, onJoinCallback);
    }

    /**
     * Use callback to add inner join sql data.
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R innerJoin(Class<S> tableHelperClass) {
        return join(JoinType.INNER, null, tableHelperClass, null, null);
    }

    /**
     * Use callback to add left join sql data.
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param onJoinCallback   {@link OnJoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R leftJoin(String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        return join(JoinType.LEFT, tableName, tableHelperClass, tableAlias, onJoinCallback);
    }

    /**
     * Use callback to add left join sql data.
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R leftJoin(String tableName, Class<S> tableHelperClass, String tableAlias) {
        return join(JoinType.LEFT, tableName, tableHelperClass, tableAlias, null);
    }

    /**
     * Use callback to add left join sql data.
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param onJoinCallback   {@link OnJoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R leftJoin(String tableName, Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        return join(JoinType.LEFT, tableName, tableHelperClass, null, onJoinCallback);
    }

    /**
     * Use callback to add left join sql data.
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R leftJoin(String tableName, Class<S> tableHelperClass) {
        return join(JoinType.LEFT, tableName, tableHelperClass, null, null);
    }

    /**
     * Use callback to add left join sql data.
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param onJoinCallback   {@link OnJoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R leftJoin(Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        return join(JoinType.LEFT, null, tableHelperClass, tableAlias, onJoinCallback);
    }

    /**
     * Use callback to add left join sql data.
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R leftJoin(Class<S> tableHelperClass, String tableAlias) {
        return join(JoinType.LEFT, null, tableHelperClass, tableAlias, null);
    }

    /**
     * Use callback to add left join sql data.
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param onJoinCallback   {@link OnJoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R leftJoin(Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        return join(JoinType.LEFT, null, tableHelperClass, null, onJoinCallback);
    }

    /**
     * Use callback to add left join sql data.
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R leftJoin(Class<S> tableHelperClass) {
        return join(JoinType.LEFT, null, tableHelperClass, null, null);
    }

    /**
     * Use callback to add right join sql data.
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param onJoinCallback   {@link OnJoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R rightJoin(String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        return join(JoinType.RIGHT, tableName, tableHelperClass, tableAlias, onJoinCallback);
    }

    /**
     * Use callback to add right join sql data.
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R rightJoin(String tableName, Class<S> tableHelperClass, String tableAlias) {
        return join(JoinType.RIGHT, tableName, tableHelperClass, tableAlias, null);
    }

    /**
     * Use callback to add right join sql data.
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @param onJoinCallback   {@link OnJoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R rightJoin(String tableName, Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        return join(JoinType.RIGHT, tableName, tableHelperClass, null, onJoinCallback);
    }

    /**
     * Use callback to add right join sql data.
     *
     * @param tableName        table name
     * @param tableHelperClass extends {@link TableHelper} class
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R rightJoin(String tableName, Class<S> tableHelperClass) {
        return join(JoinType.RIGHT, tableName, tableHelperClass, null, null);
    }

    /**
     * Use callback to add right join sql data.
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param onJoinCallback   {@link OnJoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R rightJoin(Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        return join(JoinType.RIGHT, null, tableHelperClass, tableAlias, onJoinCallback);
    }

    /**
     * Use callback to add right join sql data.
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R rightJoin(Class<S> tableHelperClass, String tableAlias) {
        return join(JoinType.RIGHT, null, tableHelperClass, tableAlias, null);
    }

    /**
     * Use callback to add right join sql data.
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param onJoinCallback   {@link OnJoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R rightJoin(Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        return join(JoinType.RIGHT, null, tableHelperClass, null, onJoinCallback);
    }

    /**
     * Use callback to add right join sql data.
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R rightJoin(Class<S> tableHelperClass) {
        return join(JoinType.RIGHT, null, tableHelperClass, null, null);
    }
}