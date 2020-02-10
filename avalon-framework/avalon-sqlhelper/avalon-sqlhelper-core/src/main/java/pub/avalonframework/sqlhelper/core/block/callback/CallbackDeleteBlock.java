package pub.avalonframework.sqlhelper.core.block.callback;

import pub.avalonframework.sqlhelper.core.helper.OnHelper;
import pub.avalonframework.sqlhelper.core.helper.WhereHelper;

/**
 * @author baichao
 */
public interface CallbackDeleteBlock<TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>, R> extends
        CallbackJoinBlock<TO, R>,
        CallbackOnBlock<TO, R>,
        CallbackWhereBlock<TW, R> {

}