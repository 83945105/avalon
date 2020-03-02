package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.builder.WhereBuilder;
import pub.avalonframework.sqlhelper.core.engine.AbstractEngine;
import pub.avalonframework.sqlhelper.core.helper.WhereHelper;

/**
 * @author baichao
 */
public interface WhereExpressionBuilder<R> {

    /**
     * Build where expression.
     *
     * @param whereBuilder extends {@link WhereBuilder} object
     * @param <FW>         extends {@link WhereHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FW extends WhereHelper<FW>> R buildWhereExpression(WhereBuilder<FW> whereBuilder);
}