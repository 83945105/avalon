package pub.avalonframework.sqlhelper.core.block.helper;

import pub.avalonframework.sqlhelper.core.helper.HavingHelper;

/**
 * @author baichao
 */
public interface HelperHavingBlock<R> extends HelperBlock {

    /**
     * add having sql data
     *
     * @param havingHelpers {@link HavingHelper}
     * @return R
     */
    R having(HavingHelper<?>... havingHelpers);
}