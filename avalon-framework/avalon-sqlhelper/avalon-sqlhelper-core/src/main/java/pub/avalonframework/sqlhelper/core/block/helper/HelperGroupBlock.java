package pub.avalonframework.sqlhelper.core.block.helper;

import pub.avalonframework.sqlhelper.core.helper.GroupHelper;

/**
 * @author baichao
 */
public interface HelperGroupBlock<R> extends HelperBlock {

    /**
     * Add group data block.
     *
     * @param groupHelpers extends {@link GroupHelper} objects
     * @return R
     */
    R groupBy(GroupHelper<?>... groupHelpers);
}