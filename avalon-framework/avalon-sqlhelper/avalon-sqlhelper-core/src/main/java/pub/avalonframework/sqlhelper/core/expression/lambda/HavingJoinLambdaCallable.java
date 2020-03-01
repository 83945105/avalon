package pub.avalonframework.sqlhelper.core.expression.lambda;

import pub.avalonframework.sqlhelper.core.expression.HavingAndExpression;
import pub.avalonframework.sqlhelper.core.helper.HavingHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface HavingJoinLambdaCallable<TW extends HavingHelper<TW>, SW extends HavingHelper<SW>> {

    HavingAndExpression<TW> apply(HavingAndExpression<TW> expression, SW joinTable, TW mainTable);
}