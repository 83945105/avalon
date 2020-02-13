package pub.avalonframework.sqlhelper.core.block.helper;

import pub.avalonframework.sqlhelper.core.helper.WhereHelper;

/**
 * @author baichao
 */
public interface HelperWhereBlock<R> extends HelperBlock {

    /**
     * Add where data block.
     *
     * @param whereHelpers {@link WhereHelper}
     * @return R
     */
    R where(WhereHelper<?>... whereHelpers);
}