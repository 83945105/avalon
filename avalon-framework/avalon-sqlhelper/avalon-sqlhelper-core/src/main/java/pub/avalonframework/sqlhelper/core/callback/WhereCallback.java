package pub.avalonframework.sqlhelper.core.callback;

import pub.avalonframework.sqlhelper.core.expression.WhereAndExpression;
import pub.avalonframework.sqlhelper.core.helper.WhereHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface WhereCallback<TW extends WhereHelper<TW>> {

    WhereAndExpression<TW> apply(WhereAndExpression<TW> expression, TW mainTable);
}