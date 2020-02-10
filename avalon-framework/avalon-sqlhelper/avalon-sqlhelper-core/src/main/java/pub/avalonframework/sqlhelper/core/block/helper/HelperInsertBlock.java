package pub.avalonframework.sqlhelper.core.block.helper;

import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;

/**
 * @author baichao
 */
public interface HelperInsertBlock<R> {

    R insert(ColumnHelper<?>... columnHelpers);
}