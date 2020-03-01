package pub.avalonframework.sqlhelper.core.expression.lambda;

import pub.avalonframework.sqlhelper.core.helper.GroupHelper;

/**
 * @author baichao
 */
@FunctionalInterface
public interface GroupLambdaCallable<T extends GroupHelper<T>> {

    T apply(T table);
}