package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.builder.HavingBuilder;
import pub.avalonframework.sqlhelper.core.helper.HavingHelper;

/**
 * @author baichao
 */
public interface HavingExpressionBuilder<R> {

    /**
     * Build having expression.
     *
     * @param havingBuilder extends {@link HavingBuilder} object
     * @return R
     */
    <FH extends HavingHelper<FH>> R buildHavingExpression(HavingBuilder<FH> havingBuilder);
}