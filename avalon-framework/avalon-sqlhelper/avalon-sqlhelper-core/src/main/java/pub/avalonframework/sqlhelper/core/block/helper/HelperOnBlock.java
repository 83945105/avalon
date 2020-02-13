package pub.avalonframework.sqlhelper.core.block.helper;

import pub.avalonframework.sqlhelper.core.helper.OnHelper;

/**
 * @author baichao
 */
public interface HelperOnBlock<R> extends HelperBlock {

    /**
     * Add on data block.
     *
     * @param onHelpers {@link OnHelper}
     * @return R
     */
    R on(OnHelper<?>... onHelpers);
}