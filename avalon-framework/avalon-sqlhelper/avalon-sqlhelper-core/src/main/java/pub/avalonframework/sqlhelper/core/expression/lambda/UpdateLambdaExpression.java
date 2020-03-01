package pub.avalonframework.sqlhelper.core.expression.lambda;

import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;
import pub.avalonframework.sqlhelper.core.helper.OnHelper;
import pub.avalonframework.sqlhelper.core.helper.WhereHelper;

/**
 * @author baichao
 */
public interface UpdateLambdaExpression<TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>, R> extends
        JoinLambdaExpression<TO, R>,
        OnLambdaExpression<TO, R>,
        WhereLambdaExpression<TW, R> {

    /**
     * Use lambda callable to add update column sql data.
     *
     * @param columnLambdaCallable {@link ColumnLambdaCallable}
     * @return R
     */
    R update(ColumnLambdaCallable<TC> columnLambdaCallable);
}