package pub.avalonframework.sqlhelper.core.engine.helper;

import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;

/**
 * @author baichao
 */
public interface HelperSelectEngine<R> extends
        HelperColumnEngine<R>,
        HelperJoinEngine<R>,
        HelperOnEngine<R>,
        HelperWhereEngine<R>,
        HelperGroupEngine<R>,
        HelperHavingEngine<R>,
        HelperSortEngine<R> {

    R select(ColumnHelper<?>... columnHelpers);
}