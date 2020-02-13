package pub.avalonframework.sqlhelper.core.block.callback;

import pub.avalonframework.sqlhelper.core.callback.SortCallback;
import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface CallbackSortBlock<TS extends SortHelper<TS>, R> extends CallbackBlock {

    /**
     * Use callback to add sort sql data.
     *
     * @param sortCallback {@link SortCallback}
     * @return R
     */
    R orderBy(SortCallback<TS> sortCallback);

    /**
     * Use callback to add assign class sort sql data.
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param sortCallback     {@link SortCallback}
     * @return R
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R orderBy(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> sortCallback);

    /**
     * Use callback to add assign class sort sql data.
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param sortCallback     {@link SortCallback}
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R orderBy(Class<S> tableHelperClass, SortCallback<SS> sortCallback) {
        return orderBy(tableHelperClass, null, sortCallback);
    }
}