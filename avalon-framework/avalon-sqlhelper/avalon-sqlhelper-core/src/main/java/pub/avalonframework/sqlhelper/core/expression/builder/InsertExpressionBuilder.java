package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;

/**
 * @author baichao
 */
public interface InsertExpressionBuilder<R> {

    /**
     * Build insert column expression.
     *
     * @param insertColumnBuilder extends {@link InsertColumnBuilder} object
     * @return R
     */
    <FC extends ColumnHelper<FC>> R buildInsertColumnExpression(InsertColumnBuilder<FC> insertColumnBuilder);
}