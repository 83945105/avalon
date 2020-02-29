package pub.avalonframework.sqlhelper.core.expression;

import pub.avalonframework.sqlhelper.core.callback.WhereJoinLinkerCallback;
import pub.avalonframework.sqlhelper.core.callback.WhereLinkerCallback;
import pub.avalonframework.sqlhelper.core.data.ComparisonDataBlockLinker;
import pub.avalonframework.sqlhelper.core.data.block.WhereDataBlock;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class WhereAndOrExpression<TW extends WhereHelper<TW>> implements WhereAndExpression<TW> {

    private List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = new ArrayList<>();

    @Override
    public List<ComparisonDataBlockLinker> takeoutComparisonDataBlockLinkers() {
        List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = this.comparisonDataBlockLinkers;
        this.comparisonDataBlockLinkers = new ArrayList<>();
        return comparisonDataBlockLinkers;
    }

    @Override
    public WhereAndOrExpression<TW> and(WhereHelper<?> whereHelper) {
        if (whereHelper == null) {
            return this;
        }
        ComparisonDataBlockLinker comparisonDataBlockLinker = new ComparisonDataBlockLinker(AndOr.AND);
        List<WhereDataBlock> whereDataBlocks = whereHelper.takeoutWhereDataBlocks();
        if (whereDataBlocks == null || whereDataBlocks.size() == 0) {
            return this;
        }
        comparisonDataBlockLinker.setComparisonDataBlocks(whereDataBlocks);
        this.comparisonDataBlockLinkers.add(comparisonDataBlockLinker);
        return this;
    }

    @Override
    public WhereAndOrExpression<TW> and(WhereLinkerCallback<TW> whereLinkerCallback) {
        if (whereLinkerCallback == null) {
            return this;
        }
        WhereAndExpression<TW> whereAndExpression = whereLinkerCallback.apply(new WhereAndOrExpression<>());
        List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = whereAndExpression.takeoutComparisonDataBlockLinkers();
        if (comparisonDataBlockLinkers == null || comparisonDataBlockLinkers.size() == 0) {
            return this;
        }
        ComparisonDataBlockLinker comparisonDataBlockLinker = new ComparisonDataBlockLinker(AndOr.AND);
        comparisonDataBlockLinker.setComparisonDataBlockLinkers(comparisonDataBlockLinkers);
        this.comparisonDataBlockLinkers.add(comparisonDataBlockLinker);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> WhereAndOrExpression<TW> and(Class<S> tableHelperClass, String tableAlias, WhereJoinLinkerCallback<TW, SW> whereJoinLinkerCallback) {
        if (whereJoinLinkerCallback == null) {
            return this;
        }
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SW sw = s.newWhereHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        WhereAndExpression<TW> whereAndExpression = whereJoinLinkerCallback.apply(new WhereAndOrExpression<>(), sw);
        List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = whereAndExpression.takeoutComparisonDataBlockLinkers();
        if (comparisonDataBlockLinkers == null || comparisonDataBlockLinkers.size() == 0) {
            return this;
        }
        ComparisonDataBlockLinker comparisonDataBlockLinker = new ComparisonDataBlockLinker(AndOr.AND);
        comparisonDataBlockLinker.setComparisonDataBlockLinkers(comparisonDataBlockLinkers);
        this.comparisonDataBlockLinkers.add(comparisonDataBlockLinker);
        return this;
    }

    /**
     * Or
     *
     * @param whereHelper {@link WhereHelper}
     * @return {@link WhereAndOrExpression}
     */
    public WhereAndOrExpression<TW> or(WhereHelper<?> whereHelper) {
        if (whereHelper == null) {
            return this;
        }
        ComparisonDataBlockLinker comparisonDataBlockLinker = new ComparisonDataBlockLinker(AndOr.OR);
        List<WhereDataBlock> whereDataBlocks = whereHelper.takeoutWhereDataBlocks();
        if (whereDataBlocks == null || whereDataBlocks.size() == 0) {
            return this;
        }
        comparisonDataBlockLinker.setComparisonDataBlocks(whereDataBlocks);
        this.comparisonDataBlockLinkers.add(comparisonDataBlockLinker);
        return this;
    }

    /**
     * Or
     *
     * @param whereLinkerCallback {@link WhereLinkerCallback}
     * @return {@link WhereAndOrExpression}
     */
    public WhereAndOrExpression<TW> or(WhereLinkerCallback<TW> whereLinkerCallback) {
        if (whereLinkerCallback == null) {
            return this;
        }
        WhereAndExpression<TW> whereAndExpression = whereLinkerCallback.apply(new WhereAndOrExpression<>());
        List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = whereAndExpression.takeoutComparisonDataBlockLinkers();
        if (comparisonDataBlockLinkers == null || comparisonDataBlockLinkers.size() == 0) {
            return this;
        }
        ComparisonDataBlockLinker comparisonDataBlockLinker = new ComparisonDataBlockLinker(AndOr.OR);
        comparisonDataBlockLinker.setComparisonDataBlockLinkers(comparisonDataBlockLinkers);
        this.comparisonDataBlockLinkers.add(comparisonDataBlockLinker);
        return this;
    }

    /**
     * Or
     *
     * @param tableHelperClass        target {@link TableHelper} class
     * @param tableAlias              target {@link TableHelper} alias
     * @param whereJoinLinkerCallback {@link WhereJoinLinkerCallback}
     * @return {@link WhereAndOrExpression}
     */
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> WhereAndOrExpression<TW> or(Class<S> tableHelperClass, String tableAlias, WhereJoinLinkerCallback<TW, SW> whereJoinLinkerCallback) {
        if (whereJoinLinkerCallback == null) {
            return this;
        }
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SW sw = s.newWhereHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        WhereAndExpression<TW> whereAndExpression = whereJoinLinkerCallback.apply(new WhereAndOrExpression<>(), sw);
        List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = whereAndExpression.takeoutComparisonDataBlockLinkers();
        if (comparisonDataBlockLinkers == null || comparisonDataBlockLinkers.size() == 0) {
            return this;
        }
        ComparisonDataBlockLinker comparisonDataBlockLinker = new ComparisonDataBlockLinker(AndOr.OR);
        comparisonDataBlockLinker.setComparisonDataBlockLinkers(comparisonDataBlockLinkers);
        this.comparisonDataBlockLinkers.add(comparisonDataBlockLinker);
        return this;
    }

    /**
     * Or
     *
     * @param tableHelperClass        target {@link TableHelper} class
     * @param whereJoinLinkerCallback {@link WhereJoinLinkerCallback}
     * @return {@link WhereAndOrExpression}
     */
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> WhereAndOrExpression<TW> or(Class<S> tableHelperClass, WhereJoinLinkerCallback<TW, SW> whereJoinLinkerCallback) {
        return or(tableHelperClass, null, whereJoinLinkerCallback);
    }
}