package pub.avalonframework.sqlhelper.core.expression;

import pub.avalonframework.sqlhelper.core.helper.SortHelper;

/**
 * @author baichao
 */
public interface SortExpression<R> {

    /**
     * Add sort data block.
     *
     * @param sortHelpers {@link SortHelper}
     * @return R
     */
    R orderBy(SortHelper<?>... sortHelpers);
}