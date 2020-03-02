package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.helper.OnHelper;

/**
 * @author baichao
 */
public interface JoinExpressionBuilder<R> {

    /**
     * Build join expression.
     *
     * @param joinBuilder extends {@link JoinBuilder} object
     * @return R
     */
    <FO extends OnHelper<FO>> R buildJoinExpression(JoinBuilder<FO> joinBuilder);
}