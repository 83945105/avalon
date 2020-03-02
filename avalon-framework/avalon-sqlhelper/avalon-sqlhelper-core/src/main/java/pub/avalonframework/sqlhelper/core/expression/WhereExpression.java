package pub.avalonframework.sqlhelper.core.expression;

import pub.avalonframework.sqlhelper.core.helper.WhereHelper;

/**
 * @author baichao
 */
public interface WhereExpression<R> {

    /**
     * Add where data block.
     *
     * @param whereHelpers {@link WhereHelper}
     * @return R
     */
    R where(WhereHelper<?>... whereHelpers);
}