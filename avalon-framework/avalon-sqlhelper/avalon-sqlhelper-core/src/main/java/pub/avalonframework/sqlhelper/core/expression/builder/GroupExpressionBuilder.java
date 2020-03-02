package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.helper.GroupHelper;

/**
 * @author baichao
 */
public interface GroupExpressionBuilder<R> {

    /**
     * Build group expression.
     *
     * @param groupBuilder extends {@link GroupBuilder} object
     * @return extends R
     */
    <FG extends GroupHelper<FG>> R buildGroupExpression(GroupBuilder<FG> groupBuilder);
}