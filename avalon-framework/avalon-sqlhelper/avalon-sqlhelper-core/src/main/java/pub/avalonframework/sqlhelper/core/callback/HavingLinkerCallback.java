package pub.avalonframework.sqlhelper.core.callback;

import pub.avalonframework.sqlhelper.core.expression.HavingAndExpression;
import pub.avalonframework.sqlhelper.core.helper.HavingHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface HavingLinkerCallback<TH extends HavingHelper<TH>> {

    HavingAndExpression<TH> apply(HavingAndExpression<TH> expression);
}