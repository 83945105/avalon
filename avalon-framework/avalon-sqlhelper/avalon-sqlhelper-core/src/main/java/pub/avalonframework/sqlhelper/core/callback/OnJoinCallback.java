package pub.avalonframework.sqlhelper.core.callback;

import pub.avalonframework.sqlhelper.core.expression.OnAndExpression;
import pub.avalonframework.sqlhelper.core.helper.OnHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface OnJoinCallback<TO extends OnHelper<TO>, SO extends OnHelper<SO>> {

    OnAndExpression<TO> apply(OnAndExpression<TO> expression, SO joinTable, TO mainTable);
}