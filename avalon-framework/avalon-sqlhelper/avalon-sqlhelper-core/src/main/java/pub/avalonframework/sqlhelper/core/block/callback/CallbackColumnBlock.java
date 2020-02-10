package pub.avalonframework.sqlhelper.core.block.callback;

import pub.avalonframework.sqlhelper.core.beans.GroupType;
import pub.avalonframework.sqlhelper.core.callback.ColumnCallback;
import pub.avalonframework.sqlhelper.core.callback.SubQueryColumnCallback;
import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface CallbackColumnBlock<TC extends ColumnHelper<TC>, R> extends CallbackBlock {

    /**
     * use callback to add group column sql data
     *
     * @param groupType      {@link GroupType}
     * @param columnCallback {@link ColumnCallback}
     * @return R
     */
    R groupColumn(GroupType groupType, ColumnCallback<TC> columnCallback);

    /**
     * use callback to add assign class group column sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param groupType        {@link GroupType}
     * @param columnCallback   {@link ColumnCallback}
     * @return R
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R groupColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<SC> columnCallback);

    /**
     * use callback to add assign class group column sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param groupType        {@link GroupType}
     * @param columnCallback   {@link ColumnCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R groupColumn(Class<S> tableHelperClass, GroupType groupType, ColumnCallback<SC> columnCallback) {
        return groupColumn(tableHelperClass, null, groupType, columnCallback);
    }

    /**
     * use callback to add sub query column sql data
     *
     * @param columnAlias            column alias
     * @param subQueryColumnCallback {@link SubQueryColumnCallback}
     * @return R
     */
    R subQueryColumn(String columnAlias, SubQueryColumnCallback<TC> subQueryColumnCallback);
}