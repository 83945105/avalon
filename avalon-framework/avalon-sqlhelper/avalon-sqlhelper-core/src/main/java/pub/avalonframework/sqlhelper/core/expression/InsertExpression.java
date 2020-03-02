package pub.avalonframework.sqlhelper.core.expression;

import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;

/**
 * @author baichao
 */
public interface InsertExpression<R> {

    R insert(ColumnHelper<?>... columnHelpers);
}