package pub.avalonframework.sqlhelper.core.block.helper;

import pub.avalonframework.sqlhelper.core.helper.HavingHelper;

/**
 * @author baichao
 */
public interface HelperHavingBlock<R> extends HelperBlock {

    /**
     * Add having data block.
     *
     * @param havingHelpers {@link HavingHelper}
     * @return R
     */
    R having(HavingHelper<?>... havingHelpers);
}