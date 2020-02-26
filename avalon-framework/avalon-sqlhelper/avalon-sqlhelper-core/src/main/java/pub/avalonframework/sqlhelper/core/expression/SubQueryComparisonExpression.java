package pub.avalonframework.sqlhelper.core.expression;

import pub.avalonframework.sqlhelper.core.callback.SubQueryCallback;
import pub.avalonframework.sqlhelper.core.helper.Helper;

/**
 * @author baichao
 */
public interface SubQueryComparisonExpression<T> {

    /**
     * Equal to sub query.
     *
     * @param subQueryCallback {@link SubQueryCallback}
     * @return extends {@link Helper} object
     */
    T eqSq(SubQueryCallback subQueryCallback);

    /**
     * Not equal to sub query.
     *
     * @param subQueryCallback {@link SubQueryCallback}
     * @return extends {@link Helper} object
     */
    T neqSq(SubQueryCallback subQueryCallback);

    /**
     * Greater than sub query.
     *
     * @param subQueryCallback {@link SubQueryCallback}
     * @return extends {@link Helper} object
     */
    T gtSq(SubQueryCallback subQueryCallback);

    /**
     * Greater than and equal to sub query.
     *
     * @param subQueryCallback {@link SubQueryCallback}
     * @return extends {@link Helper} object
     */
    T gteSq(SubQueryCallback subQueryCallback);

    /**
     * Less than sub query.
     *
     * @param subQueryCallback {@link SubQueryCallback}
     * @return extends {@link Helper} object
     */
    T ltSq(SubQueryCallback subQueryCallback);

    /**
     * Less than and equal to sub query.
     *
     * @param subQueryCallback {@link SubQueryCallback}
     * @return extends {@link Helper} object
     */
    T lteSq(SubQueryCallback subQueryCallback);

    /**
     * Like sub query.
     *
     * @param subQueryCallback {@link SubQueryCallback}
     * @return extends {@link Helper} object
     */
    T lkSq(SubQueryCallback subQueryCallback);

    /**
     * In sub query.
     *
     * @param subQueryCallback {@link SubQueryCallback}
     * @return extends {@link Helper} object
     */
    T inSq(SubQueryCallback subQueryCallback);

    /**
     * Not in sub query.
     *
     * @param subQueryCallback {@link SubQueryCallback}
     * @return extends {@link Helper} object
     */
    T ninSq(SubQueryCallback subQueryCallback);
}