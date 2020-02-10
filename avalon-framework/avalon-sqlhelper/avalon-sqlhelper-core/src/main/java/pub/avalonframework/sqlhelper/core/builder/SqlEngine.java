package pub.avalonframework.sqlhelper.core.builder;

import pub.avalonframework.sqlhelper.core.engine.AbstractEngine;
import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface SqlEngine<R> {

    /**
     * add column sql data
     *
     * @param columnBuilder extends {@link ColumnBuilder} object
     * @param <FC>          extends {@link ColumnHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FC extends ColumnHelper<FC>> R sqlColumn(ColumnBuilder<FC> columnBuilder);

    /**
     * add join sql data
     *
     * @param joinBuilder extends {@link JoinBuilder} object
     * @param <FO>        extends {@link OnHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FO extends OnHelper<FO>> R sqlJoin(JoinBuilder<FO> joinBuilder);

    /**
     * add on sql data
     *
     * @param onBuilder extends {@link OnBuilder} object
     * @param <FO>      extends {@link OnHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FO extends OnHelper<FO>> R sqlOn(OnBuilder<FO> onBuilder);

    /**
     * add where sql data
     *
     * @param whereBuilder extends {@link WhereBuilder} object
     * @param <FW>         extends {@link WhereHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FW extends WhereHelper<FW>> R sqlWhere(WhereBuilder<FW> whereBuilder);

    /**
     * add group sql data
     *
     * @param groupBuilder extends {@link GroupBuilder} object
     * @param <FG>         extends {@link GroupHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FG extends GroupHelper<FG>> R sqlGroup(GroupBuilder<FG> groupBuilder);

    /**
     * add having sql data
     *
     * @param havingBuilder extends {@link HavingBuilder} object
     * @param <FH>          extends {@link GroupHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FH extends HavingHelper<FH>> R sqlHaving(HavingBuilder<FH> havingBuilder);

    /**
     * add sort sql data
     *
     * @param sortBuilder extends {@link SortBuilder} object
     * @param <FS>        extends {@link SortHelper} object
     * @return extends {@link AbstractEngine} object
     */
    <FS extends SortHelper<FS>> R sqlSort(SortBuilder<FS> sortBuilder);
}