package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.builder.OnBuilder;
import pub.avalonframework.sqlhelper.core.helper.OnHelper;

/**
 * @author baichao
 */
public interface OnExpressionBuilder<R> {

    /**
     * Build on sql expression.
     *
     * @param onBuilder extends {@link OnBuilder} object
     * @return R
     */
    <FO extends OnHelper<FO>> R buildOnExpression(OnBuilder<FO> onBuilder);
}