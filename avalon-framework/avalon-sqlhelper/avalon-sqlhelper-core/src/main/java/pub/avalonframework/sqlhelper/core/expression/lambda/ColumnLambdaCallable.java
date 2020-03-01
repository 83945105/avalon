package pub.avalonframework.sqlhelper.core.expression.lambda;

import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface ColumnLambdaCallable<TC extends ColumnHelper<TC>> {

    TC apply(TC table);
}