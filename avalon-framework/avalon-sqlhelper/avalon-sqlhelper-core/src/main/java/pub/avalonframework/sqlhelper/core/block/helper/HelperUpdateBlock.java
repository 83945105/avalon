package pub.avalonframework.sqlhelper.core.block.helper;

import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;

/**
 * @author baichao
 */
public interface HelperUpdateBlock<R> extends HelperJoinBlock<R>, HelperOnBlock<R>, HelperWhereBlock<R> {

    R update(ColumnHelper<?>... columnHelpers);
}