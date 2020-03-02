package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.builder.UpdateColumnBuilder;
import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;

/**
 * @author baichao
 */
public interface UpdateExpressionBuilder<R> extends
        JoinExpressionBuilder<R>,
        OnExpressionBuilder<R>,
        WhereExpressionBuilder<R> {

    /**
     * Build update column expression.
     *
     * @param updateColumnBuilder extends {@link UpdateColumnBuilder} object
     * @return R
     */
    <FC extends ColumnHelper<FC>> R buildUpdateColumnExpression(UpdateColumnBuilder<FC> updateColumnBuilder);
}