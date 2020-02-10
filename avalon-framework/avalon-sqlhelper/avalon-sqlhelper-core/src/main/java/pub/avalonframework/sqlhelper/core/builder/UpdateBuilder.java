package pub.avalonframework.sqlhelper.core.builder;

import pub.avalonframework.sqlhelper.core.engine.AbstractEngine;
import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;
import pub.avalonframework.sqlhelper.core.helper.OnHelper;
import pub.avalonframework.sqlhelper.core.helper.WhereHelper;

/**
 * @author baichao
 */
public interface UpdateBuilder<R> {

    /**
     * Build update column sql data.
     *
     * @param updateColumnBuilder extends {@link UpdateColumnBuilder} object
     * @param <FC>                extends {@link ColumnHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FC extends ColumnHelper<FC>> R buildUpdateColumn(UpdateColumnBuilder<FC> updateColumnBuilder);

    /**
     * Build join sql data.
     *
     * @param joinBuilder extends {@link JoinBuilder} object
     * @param <FO>        extends {@link OnHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FO extends OnHelper<FO>> R buildJoin(JoinBuilder<FO> joinBuilder);

    /**
     * Build on sql data.
     *
     * @param onBuilder extends {@link OnBuilder} object
     * @param <FO>      extends {@link OnHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FO extends OnHelper<FO>> R buildOn(OnBuilder<FO> onBuilder);

    /**
     * Build where sql data.
     *
     * @param whereBuilder extends {@link WhereBuilder} object
     * @param <FW>         extends {@link WhereHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FW extends WhereHelper<FW>> R buildWhere(WhereBuilder<FW> whereBuilder);
}