package pub.avalonframework.sqlhelper.core.block.callback;

import pub.avalonframework.sqlhelper.core.callback.ColumnCallback;
import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;
import pub.avalonframework.sqlhelper.core.helper.OnHelper;
import pub.avalonframework.sqlhelper.core.helper.WhereHelper;

/**
 * @author baichao
 */
public interface CallbackUpdateBlock<TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>, R> extends
        CallbackJoinBlock<TO, R>,
        CallbackOnBlock<TO, R>,
        CallbackWhereBlock<TW, R> {

    /**
     * Use callback to add update column sql data.
     *
     * @param columnCallback {@link ColumnCallback}
     * @return R
     */
    R update(ColumnCallback<TC> columnCallback);
}