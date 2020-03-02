package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.builder.UpdateColumnBuilder;
import pub.avalonframework.sqlhelper.core.engine.AbstractEngine;
import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;

/**
 * @author baichao
 */
public interface UpdateExpressionBuilder<R> extends
        JoinExpressionBuilder<R>,
        OnExpressionBuilder<R>,
        WhereExpressionBuilder<R> {

    /**
     * Build update column sql data.
     *
     * @param updateColumnBuilder extends {@link UpdateColumnBuilder} object
     * @param <FC>                extends {@link ColumnHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FC extends ColumnHelper<FC>> R buildUpdateColumn(UpdateColumnBuilder<FC> updateColumnBuilder);
}