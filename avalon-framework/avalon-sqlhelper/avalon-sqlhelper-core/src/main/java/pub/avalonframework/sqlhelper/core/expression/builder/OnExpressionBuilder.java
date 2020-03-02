package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.builder.OnBuilder;
import pub.avalonframework.sqlhelper.core.engine.AbstractEngine;
import pub.avalonframework.sqlhelper.core.helper.OnHelper;

/**
 * @author baichao
 */
public interface OnExpressionBuilder<R> {

    /**
     * Build on sql expression.
     *
     * @param onBuilder extends {@link OnBuilder} object
     * @param <FO>      extends {@link OnHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FO extends OnHelper<FO>> R buildOnExpression(OnBuilder<FO> onBuilder);
}