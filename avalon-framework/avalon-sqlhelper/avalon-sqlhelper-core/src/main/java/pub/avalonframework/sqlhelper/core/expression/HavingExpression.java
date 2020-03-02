package pub.avalonframework.sqlhelper.core.expression;

import pub.avalonframework.sqlhelper.core.helper.HavingHelper;

/**
 * @author baichao
 */
public interface HavingExpression<R> {

    /**
     * Add having data block.
     *
     * @param havingHelpers {@link HavingHelper}
     * @return R
     */
    R having(HavingHelper<?>... havingHelpers);
}