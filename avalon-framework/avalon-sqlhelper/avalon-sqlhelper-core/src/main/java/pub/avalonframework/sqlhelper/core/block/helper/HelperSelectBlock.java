package pub.avalonframework.sqlhelper.core.block.helper;

import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;

/**
 * @author baichao
 */
public interface HelperSelectBlock<R> extends
        HelperColumnBlock<R>,
        HelperJoinBlock<R>,
        HelperOnBlock<R>,
        HelperWhereBlock<R>,
        HelperGroupBlock<R>,
        HelperHavingBlock<R>,
        HelperSortBlock<R> {

    R select(ColumnHelper<?>... columnHelpers);
}