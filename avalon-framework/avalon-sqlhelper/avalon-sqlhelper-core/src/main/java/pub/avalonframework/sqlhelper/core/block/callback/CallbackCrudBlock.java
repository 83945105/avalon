package pub.avalonframework.sqlhelper.core.block.callback;

import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface CallbackCrudBlock<TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>, R> extends CallbackInsertBlock<TC, R>, CallbackSelectBlock<TC, TO, TW, TG, TH, TS, R>, CallbackUpdateBlock<TC, TO, TW, R>, CallbackDeleteBlock<TO, TW, R> {
}