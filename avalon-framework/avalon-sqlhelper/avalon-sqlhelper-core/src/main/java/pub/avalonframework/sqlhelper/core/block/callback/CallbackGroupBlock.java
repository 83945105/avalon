package pub.avalonframework.sqlhelper.core.block.callback;

import pub.avalonframework.sqlhelper.core.callback.GroupCallback;
import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface CallbackGroupBlock<TG extends GroupHelper<TG>, R> extends CallbackBlock {

    /**
     * Use callback to add group sql data.
     *
     * @param groupCallback {@link GroupCallback}
     * @return R
     */
    R groupBy(GroupCallback<TG> groupCallback);

    /**
     * Use callback to add assign class group sql data.
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param groupCallback    {@link GroupCallback}
     * @return R
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R groupBy(Class<S> tableHelperClass, String tableAlias, GroupCallback<SG> groupCallback);

    /**
     * Use callback to add assign class group sql data.
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param groupCallback    {@link GroupCallback}
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R groupBy(Class<S> tableHelperClass, GroupCallback<SG> groupCallback) {
        return groupBy(tableHelperClass, null, groupCallback);
    }
}