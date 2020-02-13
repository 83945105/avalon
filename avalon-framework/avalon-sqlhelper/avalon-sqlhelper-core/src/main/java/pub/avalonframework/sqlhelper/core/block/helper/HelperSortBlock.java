package pub.avalonframework.sqlhelper.core.block.helper;

import pub.avalonframework.sqlhelper.core.helper.SortHelper;

/**
 * @author baichao
 */
public interface HelperSortBlock<R> extends HelperBlock {

    /**
     * Add sort data block.
     *
     * @param sortHelpers {@link SortHelper}
     * @return R
     */
    R orderBy(SortHelper<?>... sortHelpers);
}