package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.builder.SelectColumnBuilder;
import pub.avalonframework.sqlhelper.core.engine.AbstractEngine;
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
     * Build select column sql data.
     *
     * @param selectColumnBuilder extends {@link SelectColumnBuilder} object
     * @param <FC>                extends {@link ColumnHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FC extends ColumnHelper<FC>> R buildSelectColumn(SelectColumnBuilder<FC> selectColumnBuilder);
}