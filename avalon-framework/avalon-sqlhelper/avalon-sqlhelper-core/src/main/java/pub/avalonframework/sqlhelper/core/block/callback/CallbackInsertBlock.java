package pub.avalonframework.sqlhelper.core.block.callback;

import pub.avalonframework.sqlhelper.core.callback.ColumnCallback;
import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;

/**
 * @author baichao
 */
public interface CallbackInsertBlock<TC extends ColumnHelper<TC>, R> {

    /**
     * Use callback to add insert column sql data.
     *
     * @param columnCallback {@link ColumnCallback}
     * @return R
     */
    R insert(ColumnCallback<TC> columnCallback);
}