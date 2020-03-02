package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.builder.GroupBuilder;
import pub.avalonframework.sqlhelper.core.engine.AbstractEngine;
import pub.avalonframework.sqlhelper.core.helper.GroupHelper;

/**
 * @author baichao
 */
public interface GroupExpressionBuilder<R> {

    /**
     * Build group expression.
     *
     * @param groupBuilder extends {@link GroupBuilder} object
     * @param <FG>         extends {@link GroupHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FG extends GroupHelper<FG>> R buildGroupExpression(GroupBuilder<FG> groupBuilder);
}