package pub.avalonframework.sqlhelper.core.callback;

import pub.avalonframework.sqlhelper.core.expression.WhereAndExpression;
import pub.avalonframework.sqlhelper.core.helper.WhereHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface WhereJoinLinkerCallback<TW extends WhereHelper<TW>, SW extends WhereHelper<SW>> {

    WhereAndExpression<TW> apply(WhereAndExpression<TW> expression, SW joinTable);
}