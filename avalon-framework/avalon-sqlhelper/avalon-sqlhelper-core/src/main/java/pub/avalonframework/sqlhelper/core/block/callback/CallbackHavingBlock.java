package pub.avalonframework.sqlhelper.core.block.callback;

import pub.avalonframework.sqlhelper.core.callback.HavingCallback;
import pub.avalonframework.sqlhelper.core.callback.HavingJoinCallback;
import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface CallbackHavingBlock<TH extends HavingHelper<TH>, R> extends CallbackBlock {

    /**
     * Use callback to add having sql data.
     *
     * @param havingCallback {@link HavingCallback}
     * @return R
     */
    R having(HavingCallback<TH> havingCallback);

    /**
     * Use callback to add assign class having sql data.
     *
     * @param tableHelperClass   extends {@link TableHelper} class
     * @param tableAlias         table alias
     * @param havingJoinCallback {@link HavingJoinCallback}
     * @return R
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R having(Class<S> tableHelperClass, String tableAlias, HavingJoinCallback<TH, SH> havingJoinCallback);

    /**
     * Use callback to add assign class having sql data.
     *
     * @param tableHelperClass   extends {@link TableHelper} class
     * @param havingJoinCallback {@link HavingJoinCallback}
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R having(Class<S> tableHelperClass, HavingJoinCallback<TH, SH> havingJoinCallback) {
        return having(tableHelperClass, null, havingJoinCallback);
    }
}