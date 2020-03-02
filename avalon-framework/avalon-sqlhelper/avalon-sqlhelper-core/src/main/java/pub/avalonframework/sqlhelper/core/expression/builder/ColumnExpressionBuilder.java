package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;

/**
 * @author baichao
 */
public interface ColumnExpressionBuilder<R> {

    /**
     * Build column expression.
     *
     * @param columnBuilder extends {@link ColumnBuilder} object
     * @return R
     */
    <FC extends ColumnHelper<FC>> R buildColumnExpression(ColumnBuilder<FC> columnBuilder);
}