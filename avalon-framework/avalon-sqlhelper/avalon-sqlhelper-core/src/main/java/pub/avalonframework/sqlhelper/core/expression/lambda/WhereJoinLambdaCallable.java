package pub.avalonframework.sqlhelper.core.expression.lambda;

import pub.avalonframework.sqlhelper.core.expression.WhereAndExpression;
import pub.avalonframework.sqlhelper.core.helper.WhereHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface WhereJoinLambdaCallable<TW extends WhereHelper<TW>, SW extends WhereHelper<SW>> {

    WhereAndExpression<TW> apply(WhereAndExpression<TW> expression, SW joinTable, TW mainTable);
}