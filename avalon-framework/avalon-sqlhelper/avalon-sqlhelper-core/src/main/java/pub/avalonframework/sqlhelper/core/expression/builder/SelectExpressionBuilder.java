package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.builder.SelectColumnBuilder;
import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;

/**
 * @author baichao
 */
public interface SelectExpressionBuilder<R> extends
        ColumnExpressionBuilder<R>,
        JoinExpressionBuilder<R>,
        OnExpressionBuilder<R>,
        WhereExpressionBuilder<R>,
        GroupExpressionBuilder<R>,
        HavingExpressionBuilder<R>,
        SortExpressionBuilder<R> {

    /**
     * Build select column expression.
     *
     * @param selectColumnBuilder extends {@link SelectColumnBuilder} object
     * @return R
     */
    <FC extends ColumnHelper<FC>> R buildSelectColumnExpression(SelectColumnBuilder<FC> selectColumnBuilder);
}