package pub.avalonframework.sqlhelper.core.expression.lambda;

import pub.avalonframework.sqlhelper.core.expression.WhereAndExpression;
import pub.avalonframework.sqlhelper.core.helper.WhereHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface WhereAndLambdaCallable<TW extends WhereHelper<TW>> {

    WhereAndExpression<TW> apply(WhereAndExpression<TW> expression);
}