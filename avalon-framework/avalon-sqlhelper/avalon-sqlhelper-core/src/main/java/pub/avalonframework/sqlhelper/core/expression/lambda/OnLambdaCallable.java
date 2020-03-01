package pub.avalonframework.sqlhelper.core.expression.lambda;

import pub.avalonframework.sqlhelper.core.expression.OnAndExpression;
import pub.avalonframework.sqlhelper.core.helper.OnHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface OnLambdaCallable<TW extends OnHelper<TW>> {

    OnAndExpression<TW> apply(OnAndExpression<TW> expression, TW mainTable);
}