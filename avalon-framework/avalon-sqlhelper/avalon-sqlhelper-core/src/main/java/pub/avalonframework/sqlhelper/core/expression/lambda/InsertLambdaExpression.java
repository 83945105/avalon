package pub.avalonframework.sqlhelper.core.expression.lambda;

import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;

/**
 * @author baichao
 */
public interface InsertLambdaExpression<TC extends ColumnHelper<TC>, R> {

    /**
     * Use lambda callable to add insert column sql data.
     *
     * @param columnLambdaCallable {@link ColumnLambdaCallable}
     * @return R
     */
    R insert(ColumnLambdaCallable<TC> columnLambdaCallable);
}