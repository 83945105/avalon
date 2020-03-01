package pub.avalonframework.sqlhelper.core.expression.lambda;

import pub.avalonframework.sqlhelper.core.helper.Helper;

/**
 * @author baichao
 */
public interface SubQueryComparisonLambdaExpression<T> {

    /**
     * Equal to sub query.
     *
     * @param subQueryLambdaCallable {@link SubQueryLambdaCallable}
     * @return extends {@link Helper} object
     */
    T eqSq(SubQueryLambdaCallable subQueryLambdaCallable);

    /**
     * Not equal to sub query.
     *
     * @param subQueryLambdaCallable {@link SubQueryLambdaCallable}
     * @return extends {@link Helper} object
     */
    T neqSq(SubQueryLambdaCallable subQueryLambdaCallable);

    /**
     * Greater than sub query.
     *
     * @param subQueryLambdaCallable {@link SubQueryLambdaCallable}
     * @return extends {@link Helper} object
     */
    T gtSq(SubQueryLambdaCallable subQueryLambdaCallable);

    /**
     * Greater than and equal to sub query.
     *
     * @param subQueryLambdaCallable {@link SubQueryLambdaCallable}
     * @return extends {@link Helper} object
     */
    T gteSq(SubQueryLambdaCallable subQueryLambdaCallable);

    /**
     * Less than sub query.
     *
     * @param subQueryLambdaCallable {@link SubQueryLambdaCallable}
     * @return extends {@link Helper} object
     */
    T ltSq(SubQueryLambdaCallable subQueryLambdaCallable);

    /**
     * Less than and equal to sub query.
     *
     * @param subQueryLambdaCallable {@link SubQueryLambdaCallable}
     * @return extends {@link Helper} object
     */
    T lteSq(SubQueryLambdaCallable subQueryLambdaCallable);

    /**
     * Like sub query.
     *
     * @param subQueryLambdaCallable {@link SubQueryLambdaCallable}
     * @return extends {@link Helper} object
     */
    T lkSq(SubQueryLambdaCallable subQueryLambdaCallable);

    /**
     * In sub query.
     *
     * @param subQueryLambdaCallable {@link SubQueryLambdaCallable}
     * @return extends {@link Helper} object
     */
    T inSq(SubQueryLambdaCallable subQueryLambdaCallable);

    /**
     * Not in sub query.
     *
     * @param subQueryLambdaCallable {@link SubQueryLambdaCallable}
     * @return extends {@link Helper} object
     */
    T ninSq(SubQueryLambdaCallable subQueryLambdaCallable);
}