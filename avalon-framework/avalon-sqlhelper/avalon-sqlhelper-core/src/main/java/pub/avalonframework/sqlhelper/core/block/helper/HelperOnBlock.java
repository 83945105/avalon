package pub.avalonframework.sqlhelper.core.block.helper;

import pub.avalonframework.sqlhelper.core.helper.OnHelper;

/**
 * @author baichao
 */
public interface HelperOnBlock<R> extends HelperBlock {

    /**
     * add on sql data
     *
     * @param onHelpers {@link OnHelper}
     * @return R
     */
    R on(OnHelper<?>... onHelpers);
}