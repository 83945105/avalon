package pub.avalonframework.sqlhelper.core.builder;

import pub.avalonframework.sqlhelper.core.engine.AbstractEngine;
import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface SelectBuilder<R> {

    /**
     * Build column sql data.
     *
     * @param columnBuilder extends {@link ColumnBuilder} object
     * @param <FC>          extends {@link ColumnHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FC extends ColumnHelper<FC>> R buildColumn(ColumnBuilder<FC> columnBuilder);

    /**
     * Build select column sql data.
     *
     * @param selectColumnBuilder extends {@link SelectColumnBuilder} object
     * @param <FC>                extends {@link ColumnHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FC extends ColumnHelper<FC>> R buildSelectColumn(SelectColumnBuilder<FC> selectColumnBuilder);

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

    /**
     * Build group sql data.
     *
     * @param groupBuilder extends {@link GroupBuilder} object
     * @param <FG>         extends {@link GroupHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FG extends GroupHelper<FG>> R buildGroup(GroupBuilder<FG> groupBuilder);

    /**
     * Build having sql data.
     *
     * @param havingBuilder extends {@link HavingBuilder} object
     * @param <FH>          extends {@link GroupHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FH extends HavingHelper<FH>> R buildHaving(HavingBuilder<FH> havingBuilder);

    /**
     * Build sort sql data.
     *
     * @param sortBuilder extends {@link SortBuilder} object
     * @param <FS>        extends {@link SortHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FS extends SortHelper<FS>> R buildSort(SortBuilder<FS> sortBuilder);
}