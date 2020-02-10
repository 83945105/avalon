package pub.avalonframework.sqlhelper.core.engine.helper;

import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;

/**
 * @author baichao
 */
public interface HelperUpdateEngine<R> extends HelperJoinEngine<R>, HelperOnEngine<R>, HelperWhereEngine<R> {

    R update(ColumnHelper<?>... columnHelpers);
}