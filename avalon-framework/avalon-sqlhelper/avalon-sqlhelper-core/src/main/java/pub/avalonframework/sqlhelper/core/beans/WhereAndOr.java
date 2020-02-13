package pub.avalonframework.sqlhelper.core.beans;

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
public final class WhereAndOr<TW extends WhereHelper<TW>> implements WhereLinker<TW> {

    private List<ComparisonDataBlockLinker> comparisonSqlPartDataLinkers = new ArrayList<>();

    @Override
    public List<ComparisonDataBlockLinker> takeoutComparisonSqlPartDataLinkers() {
        List<ComparisonDataBlockLinker> comparisonSqlPartDataLinkers = this.comparisonSqlPartDataLinkers;
        this.comparisonSqlPartDataLinkers = new ArrayList<>();
        return comparisonSqlPartDataLinkers;
    }

    @Override
    public WhereAndOr<TW> and(WhereHelper<?> whereHelper) {
        if (whereHelper == null) {
            return this;
        }
        ComparisonDataBlockLinker comparisonSqlPartDataLinker = new ComparisonDataBlockLinker(LinkType.AND);
        List<WhereDataBlock> whereDataBlocks = whereHelper.takeoutSqlPartData();
        if (whereDataBlocks == null || whereDataBlocks.size() == 0) {
            return this;
        }
        comparisonSqlPartDataLinker.setComparisonSqlPartData(whereDataBlocks);
        this.comparisonSqlPartDataLinkers.add(comparisonSqlPartDataLinker);
        return this;
    }

    @Override
    public WhereAndOr<TW> and(WhereLinkerCallback<TW> whereLinkerCallback) {
        if (whereLinkerCallback == null) {
            return this;
        }
        WhereLinker<TW> whereLinker = whereLinkerCallback.apply(new WhereAndOr<>());
        List<ComparisonDataBlockLinker> comparisonSqlPartDataLinkers = whereLinker.takeoutComparisonSqlPartDataLinkers();
        if (comparisonSqlPartDataLinkers == null || comparisonSqlPartDataLinkers.size() == 0) {
            return this;
        }
        ComparisonDataBlockLinker whereDataLinker = new ComparisonDataBlockLinker(LinkType.AND);
        whereDataLinker.setComparisonSqlPartDataLinkers(comparisonSqlPartDataLinkers);
        this.comparisonSqlPartDataLinkers.add(whereDataLinker);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> WhereAndOr<TW> and(Class<S> tableHelperClass, String tableAlias, WhereJoinLinkerCallback<TW, SW> whereJoinLinkerCallback) {
        if (whereJoinLinkerCallback == null) {
            return this;
        }
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SW sw = s.newWhereHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        WhereLinker<TW> whereLinker = whereJoinLinkerCallback.apply(new WhereAndOr<>(), sw);
        List<ComparisonDataBlockLinker> comparisonSqlPartDataLinkers = whereLinker.takeoutComparisonSqlPartDataLinkers();
        if (comparisonSqlPartDataLinkers == null || comparisonSqlPartDataLinkers.size() == 0) {
            return this;
        }
        ComparisonDataBlockLinker whereDataLinker = new ComparisonDataBlockLinker(LinkType.AND);
        whereDataLinker.setComparisonSqlPartDataLinkers(comparisonSqlPartDataLinkers);
        this.comparisonSqlPartDataLinkers.add(whereDataLinker);
        return this;
    }

    /**
     * or
     *
     * @param whereHelper {@link WhereHelper}
     * @return {@link WhereAndOr}
     */
    public WhereAndOr<TW> or(WhereHelper<?> whereHelper) {
        if (whereHelper == null) {
            return this;
        }
        ComparisonDataBlockLinker whereDataLinker = new ComparisonDataBlockLinker(LinkType.OR);
        List<WhereDataBlock> whereDataBlocks = whereHelper.takeoutSqlPartData();
        if (whereDataBlocks == null || whereDataBlocks.size() == 0) {
            return this;
        }
        whereDataLinker.setComparisonSqlPartData(whereDataBlocks);
        this.comparisonSqlPartDataLinkers.add(whereDataLinker);
        return this;
    }

    /**
     * or
     *
     * @param whereLinkerCallback {@link WhereLinkerCallback}
     * @return {@link WhereAndOr}
     */
    public WhereAndOr<TW> or(WhereLinkerCallback<TW> whereLinkerCallback) {
        if (whereLinkerCallback == null) {
            return this;
        }
        WhereLinker<TW> whereLinker = whereLinkerCallback.apply(new WhereAndOr<>());
        List<ComparisonDataBlockLinker> comparisonSqlPartDataLinkers = whereLinker.takeoutComparisonSqlPartDataLinkers();
        if (comparisonSqlPartDataLinkers == null || comparisonSqlPartDataLinkers.size() == 0) {
            return this;
        }
        ComparisonDataBlockLinker whereDataLinker = new ComparisonDataBlockLinker(LinkType.OR);
        whereDataLinker.setComparisonSqlPartDataLinkers(comparisonSqlPartDataLinkers);
        this.comparisonSqlPartDataLinkers.add(whereDataLinker);
        return this;
    }

    /**
     * or
     *
     * @param tableHelperClass        target {@link TableHelper} class
     * @param tableAlias              target {@link TableHelper} alias
     * @param whereJoinLinkerCallback {@link WhereJoinLinkerCallback}
     * @return {@link WhereAndOr}
     */
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> WhereAndOr<TW> or(Class<S> tableHelperClass, String tableAlias, WhereJoinLinkerCallback<TW, SW> whereJoinLinkerCallback) {
        if (whereJoinLinkerCallback == null) {
            return this;
        }
        S s = HelperManager.defaultTableHelper(tableHelperClass);
        SW sw = s.newWhereHelper(tableAlias == null ? s.getTableAlias() : tableAlias);
        WhereLinker<TW> whereLinker = whereJoinLinkerCallback.apply(new WhereAndOr<>(), sw);
        List<ComparisonDataBlockLinker> comparisonSqlPartDataLinkers = whereLinker.takeoutComparisonSqlPartDataLinkers();
        if (comparisonSqlPartDataLinkers == null || comparisonSqlPartDataLinkers.size() == 0) {
            return this;
        }
        ComparisonDataBlockLinker whereDataLinker = new ComparisonDataBlockLinker(LinkType.OR);
        whereDataLinker.setComparisonSqlPartDataLinkers(comparisonSqlPartDataLinkers);
        this.comparisonSqlPartDataLinkers.add(whereDataLinker);
        return this;
    }

    /**
     * or
     *
     * @param tableHelperClass        target {@link TableHelper} class
     * @param whereJoinLinkerCallback {@link WhereJoinLinkerCallback}
     * @return {@link WhereAndOr}
     */
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> WhereAndOr<TW> or(Class<S> tableHelperClass, WhereJoinLinkerCallback<TW, SW> whereJoinLinkerCallback) {
        return or(tableHelperClass, null, whereJoinLinkerCallback);
    }
}