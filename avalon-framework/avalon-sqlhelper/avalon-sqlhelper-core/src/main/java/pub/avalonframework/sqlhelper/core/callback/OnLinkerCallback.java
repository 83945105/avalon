package pub.avalonframework.sqlhelper.core.callback;

import pub.avalonframework.sqlhelper.core.expression.OnAndExpression;
import pub.avalonframework.sqlhelper.core.helper.OnHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface OnLinkerCallback<TO extends OnHelper<TO>> {

    OnAndExpression<TO> apply(OnAndExpression<TO> expression);
}