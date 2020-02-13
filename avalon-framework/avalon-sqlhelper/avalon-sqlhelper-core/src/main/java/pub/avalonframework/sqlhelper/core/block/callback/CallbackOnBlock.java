package pub.avalonframework.sqlhelper.core.block.callback;

import pub.avalonframework.sqlhelper.core.callback.OnCallback;
import pub.avalonframework.sqlhelper.core.callback.OnJoinCallback;
import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface CallbackOnBlock<TO extends OnHelper<TO>, R> extends CallbackBlock {

    /**
     * Use callback to add where sql data.
     *
     * @param onCallback {@link OnCallback}
     * @return R
     */
    R on(OnCallback<TO> onCallback);

    /**
     * Use callback to add assign class on sql data.
     *
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
            SS extends SortHelper<SS>> R on(Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback);

    /**
     * Use callback to add assign class on sql data.
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
            SS extends SortHelper<SS>> R on(Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        return on(tableHelperClass, null, onJoinCallback);
    }
}