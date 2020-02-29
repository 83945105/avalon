package pub.avalonframework.sqlhelper.core.callback;

import pub.avalonframework.sqlhelper.core.expression.OnAndExpression;
import pub.avalonframework.sqlhelper.core.helper.OnHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface OnCallback<TW extends OnHelper<TW>> {

    OnAndExpression<TW> apply(OnAndExpression<TW> expression, TW mainTable);
}