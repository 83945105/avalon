package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.helper.SortHelper;

/**
 * @author baichao
 */
public interface SortExpressionBuilder<R> {

    /**
     * Build sort expression.
     *
     * @param sortBuilder extends {@link SortBuilder} object
     * @return R
     */
    <FS extends SortHelper<FS>> R buildSortExpression(SortBuilder<FS> sortBuilder);
}