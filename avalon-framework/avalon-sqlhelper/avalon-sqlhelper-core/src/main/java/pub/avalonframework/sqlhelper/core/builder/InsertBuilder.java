package pub.avalonframework.sqlhelper.core.builder;

import pub.avalonframework.sqlhelper.core.engine.AbstractEngine;
import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;

/**
 * @author baichao
 */
public interface InsertBuilder<R> {

    /**
     * Build insert column sql data.
     *
     * @param insertColumnBuilder extends {@link InsertColumnBuilder} object
     * @param <FC>                extends {@link ColumnHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FC extends ColumnHelper<FC>> R buildInsertColumn(InsertColumnBuilder<FC> insertColumnBuilder);
}