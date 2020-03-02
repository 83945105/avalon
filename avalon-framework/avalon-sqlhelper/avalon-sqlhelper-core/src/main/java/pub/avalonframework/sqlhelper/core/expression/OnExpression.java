package pub.avalonframework.sqlhelper.core.expression;

import pub.avalonframework.sqlhelper.core.helper.OnHelper;

/**
 * @author baichao
 */
public interface OnExpression<R> {

    /**
     * Add on data block.
     *
     * @param onHelpers {@link OnHelper}
     * @return R
     */
    R on(OnHelper<?>... onHelpers);
}