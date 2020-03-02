package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.builder.SortBuilder;
import pub.avalonframework.sqlhelper.core.engine.AbstractEngine;
import pub.avalonframework.sqlhelper.core.helper.SortHelper;

/**
 * @author baichao
 */
public interface SortExpressionBuilder<R> {

    /**
     * Build sort expression.
     *
     * @param sortBuilder extends {@link SortBuilder} object
     * @param <FS>        extends {@link SortHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FS extends SortHelper<FS>> R buildSortExpression(SortBuilder<FS> sortBuilder);
}