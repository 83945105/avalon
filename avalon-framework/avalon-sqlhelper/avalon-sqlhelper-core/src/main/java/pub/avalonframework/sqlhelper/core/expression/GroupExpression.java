package pub.avalonframework.sqlhelper.core.expression;

import pub.avalonframework.sqlhelper.core.helper.GroupHelper;

/**
 * @author baichao
 */
public interface GroupExpression<R> {

    /**
     * Add group data block.
     *
     * @param groupHelpers extends {@link GroupHelper} objects
     * @return R
     */
    R groupBy(GroupHelper<?>... groupHelpers);
}