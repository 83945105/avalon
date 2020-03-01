package pub.avalonframework.sqlhelper.core.expression.lambda;

import pub.avalonframework.sqlhelper.core.helper.SortHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface SortLambdaCallable<T extends SortHelper<T>> {

    T apply(T table);
}