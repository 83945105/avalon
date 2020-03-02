package pub.avalonframework.sqlhelper.core.expression;

import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;

/**
 * @author baichao
 */
public interface SelectExpression<R> extends
        ColumnExpression<R>,
        JoinExpression<R>,
        OnExpression<R>,
        WhereExpression<R>,
        GroupExpression<R>,
        HavingExpression<R>,
        SortExpression<R> {

    R select(ColumnHelper<?>... columnHelpers);
}