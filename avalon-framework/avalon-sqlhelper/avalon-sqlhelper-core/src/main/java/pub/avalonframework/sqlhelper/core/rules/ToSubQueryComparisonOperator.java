package pub.avalonframework.sqlhelper.core.rules;

import pub.avalonframework.sqlhelper.core.callback.SubQueryCallback;
import pub.avalonframework.sqlhelper.core.helper.Helper;

/**
 * @author baichao
 */
public interface ToSubQueryComparisonOperator<T> {

    /**
     * Equal to sub query.
     *
     * @param subQueryCallback {@link SubQueryCallback}
     * @return extends {@link Helper} object
     */
    T equalToSubQuery(SubQueryCallback subQueryCallback);

    /**
     * Not equal to sub query.
     *
     * @param subQueryCallback {@link SubQueryCallback}
     * @return extends {@link Helper} object
     */
    T notEqualToSubQuery(SubQueryCallback subQueryCallback);

    /**
     * Greater than sub query.
     *
     * @param subQueryCallback {@link SubQueryCallback}
     * @return extends {@link Helper} object
     */
    T greaterThanSubQuery(SubQueryCallback subQueryCallback);

    /**
     * Greater than and equal to sub query.
     *
     * @param subQueryCallback {@link SubQueryCallback}
     * @return extends {@link Helper} object
     */
    T greaterThanAndEqualToSubQuery(SubQueryCallback subQueryCallback);

    /**
     * Less than sub query.
     *
     * @param subQueryCallback {@link SubQueryCallback}
     * @return extends {@link Helper} object
     */
    T lessThanSubQuery(SubQueryCallback subQueryCallback);

    /**
     * Less than and equal to sub query.
     *
     * @param subQueryCallback {@link SubQueryCallback}
     * @return extends {@link Helper} object
     */
    T lessThanAndEqualToSubQuery(SubQueryCallback subQueryCallback);

    /**
     * Like sub query.
     *
     * @param subQueryCallback {@link SubQueryCallback}
     * @return extends {@link Helper} object
     */
    T likeSubQuery(SubQueryCallback subQueryCallback);

    /**
     * In sub query.
     *
     * @param subQueryCallback {@link SubQueryCallback}
     * @return extends {@link Helper} object
     */
    T inSubQuery(SubQueryCallback subQueryCallback);

    /**
     * Not in sub query.
     *
     * @param subQueryCallback {@link SubQueryCallback}
     * @return extends {@link Helper} object
     */
    T notInSubQuery(SubQueryCallback subQueryCallback);
}