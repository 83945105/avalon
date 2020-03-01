package pub.avalonframework.sqlhelper.core.expression.lambda;

import pub.avalonframework.sqlhelper.core.expression.HavingAndExpression;
import pub.avalonframework.sqlhelper.core.helper.HavingHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface HavingAndLambdaCallable<TH extends HavingHelper<TH>> {

    HavingAndExpression<TH> apply(HavingAndExpression<TH> expression);
}