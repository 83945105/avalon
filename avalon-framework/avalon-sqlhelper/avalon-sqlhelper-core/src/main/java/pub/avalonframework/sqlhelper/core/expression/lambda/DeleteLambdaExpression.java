package pub.avalonframework.sqlhelper.core.expression.lambda;

import pub.avalonframework.sqlhelper.core.helper.OnHelper;
import pub.avalonframework.sqlhelper.core.helper.WhereHelper;

/**
 * @author baichao
 */
public interface DeleteLambdaExpression<TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>, R> extends
        JoinLambdaExpression<TO, R>,
        OnLambdaExpression<TO, R>,
        WhereLambdaExpression<TW, R> {

}