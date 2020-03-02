package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.builder.ColumnBuilder;
import pub.avalonframework.sqlhelper.core.engine.AbstractEngine;
import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;

/**
 * @author baichao
 */
public interface ColumnExpressionBuilder<R> {

    /**
     * Build column expression.
     *
     * @param columnBuilder extends {@link ColumnBuilder} object
     * @param <FC>          extends {@link ColumnHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FC extends ColumnHelper<FC>> R buildColumnExpression(ColumnBuilder<FC> columnBuilder);
}