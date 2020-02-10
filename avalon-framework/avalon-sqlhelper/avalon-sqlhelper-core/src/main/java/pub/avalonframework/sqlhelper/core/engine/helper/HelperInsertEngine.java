package pub.avalonframework.sqlhelper.core.engine.helper;

import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;

/**
 * @author baichao
 */
public interface HelperInsertEngine<R> {

    R insert(ColumnHelper<?>... columnHelpers);
}