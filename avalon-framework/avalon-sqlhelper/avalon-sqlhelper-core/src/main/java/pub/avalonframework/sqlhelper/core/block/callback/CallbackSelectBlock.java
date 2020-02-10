package pub.avalonframework.sqlhelper.core.block.callback;

import pub.avalonframework.sqlhelper.core.callback.ColumnCallback;
import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface CallbackSelectBlock<TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>, R> extends
        CallbackColumnBlock<TC, R>,
        CallbackJoinBlock<TO, R>,
        CallbackOnBlock<TO, R>,
        CallbackWhereBlock<TW, R>,
        CallbackGroupBlock<TG, R>,
        CallbackHavingBlock<TH, R>,
        CallbackSortBlock<TS, R> {

    /**
     * use callback to add select column sql data
     *
     * @param columnCallback {@link ColumnCallback}
     * @return R
     */
    R select(ColumnCallback<TC> columnCallback);

    /**
     * use callback to add assign class select column sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param columnCallback   {@link ColumnCallback}
     * @return R
     */
    <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R select(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback);

    /**
     * use callback to add assign class select column sql data
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param columnCallback   {@link ColumnCallback}
     * @return R
     */
    default <S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
            SO extends OnHelper<SO>,
            SC extends ColumnHelper<SC>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R select(Class<S> tableHelperClass, ColumnCallback<SC> columnCallback) {
        return select(tableHelperClass, null, columnCallback);
    }
}