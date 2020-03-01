package pub.avalonframework.sqlhelper.core.expression.lambda;

import pub.avalonframework.sqlhelper.core.expression.OnAndExpression;
import pub.avalonframework.sqlhelper.core.helper.OnHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface OnAndLambdaCallable<TO extends OnHelper<TO>> {

    OnAndExpression<TO> apply(OnAndExpression<TO> expression);
}