package pub.avalonframework.sqlhelper.core.expression;

import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;

/**
 * @author baichao
 */
public interface UpdateExpression<R> extends JoinExpression<R>, OnExpression<R>, WhereExpression<R> {

    R update(ColumnHelper<?>... columnHelpers);
}