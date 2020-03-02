package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.builder.WhereBuilder;
import pub.avalonframework.sqlhelper.core.helper.WhereHelper;

/**
 * @author baichao
 */
public interface WhereExpressionBuilder<R> {

    /**
     * Build where expression.
     *
     * @param whereBuilder extends {@link WhereBuilder} object
     * @return R
     */
    <FW extends WhereHelper<FW>> R buildWhereExpression(WhereBuilder<FW> whereBuilder);
}