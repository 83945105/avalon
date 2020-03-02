package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.builder.JoinBuilder;
import pub.avalonframework.sqlhelper.core.engine.AbstractEngine;
import pub.avalonframework.sqlhelper.core.helper.OnHelper;

/**
 * @author baichao
 */
public interface JoinExpressionBuilder<R> {

    /**
     * Build join expression.
     *
     * @param joinBuilder extends {@link JoinBuilder} object
     * @param <FO>        extends {@link OnHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FO extends OnHelper<FO>> R buildJoinExpression(JoinBuilder<FO> joinBuilder);
}