package pub.avalonframework.sqlhelper.core.beans;

import pub.avalonframework.sqlhelper.core.callback.HavingLinkerCallback;
import pub.avalonframework.sqlhelper.core.data.ComparisonDataBlockLinker;
import pub.avalonframework.sqlhelper.core.data.block.HavingDataBlock;
import pub.avalonframework.sqlhelper.core.helper.HavingHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class HavingAndOr<TH extends HavingHelper<TH>> implements HavingLinker<TH> {

    private List<ComparisonDataBlockLinker> comparisonSqlPartDataLinkers = new ArrayList<>();

    @Override
    public List<ComparisonDataBlockLinker> takeoutComparisonSqlPartDataLinkers() {
        List<ComparisonDataBlockLinker> comparisonSqlPartDataLinkers = this.comparisonSqlPartDataLinkers;
        this.comparisonSqlPartDataLinkers = new ArrayList<>();
        return comparisonSqlPartDataLinkers;
    }

    @Override
    public HavingAndOr<TH> and(HavingHelper<?> havingHelper) {
        if (havingHelper == null) {
            return this;
        }
        ComparisonDataBlockLinker havingDataLinker = new ComparisonDataBlockLinker(LinkType.AND);
        List<HavingDataBlock> havingDataBlocks = havingHelper.takeoutSqlPartData();
        if (havingDataBlocks == null || havingDataBlocks.size() == 0) {
            return this;
        }
        havingDataLinker.setComparisonSqlPartData(havingDataBlocks);
        this.comparisonSqlPartDataLinkers.add(havingDataLinker);
        return this;
    }

    @Override
    public HavingAndOr<TH> and(HavingLinkerCallback<TH> havingLinkerCallback) {
        if (havingLinkerCallback == null) {
            return this;
        }
        HavingLinker<TH> havingLinker = havingLinkerCallback.apply(new HavingAndOr<>());
        List<ComparisonDataBlockLinker> comparisonSqlPartDataLinkers = havingLinker.takeoutComparisonSqlPartDataLinkers();
        if (comparisonSqlPartDataLinkers == null || comparisonSqlPartDataLinkers.size() == 0) {
            return this;
        }
        ComparisonDataBlockLinker havingDataLinker = new ComparisonDataBlockLinker(LinkType.AND);
        havingDataLinker.setComparisonSqlPartDataLinkers(comparisonSqlPartDataLinkers);
        this.comparisonSqlPartDataLinkers.add(havingDataLinker);
        return this;
    }

    /**
     * or
     *
     * @param havingHelper {@link HavingHelper}
     * @return {@link HavingAndOr}
     */
    public HavingAndOr<TH> or(HavingHelper<?> havingHelper) {
        if (havingHelper == null) {
            return this;
        }
        ComparisonDataBlockLinker havingDataLinker = new ComparisonDataBlockLinker(LinkType.OR);
        List<HavingDataBlock> havingDataBlocks = havingHelper.takeoutSqlPartData();
        if (havingDataBlocks == null || havingDataBlocks.size() == 0) {
            return this;
        }
        havingDataLinker.setComparisonSqlPartData(havingDataBlocks);
        this.comparisonSqlPartDataLinkers.add(havingDataLinker);
        return this;
    }

    /**
     * or
     *
     * @param havingLinkerCallback {@link HavingLinkerCallback}
     * @return {@link HavingAndOr}
     */
    public HavingAndOr<TH> or(HavingLinkerCallback<TH> havingLinkerCallback) {
        if (havingLinkerCallback == null) {
            return this;
        }
        HavingLinker<TH> havingLinker = havingLinkerCallback.apply(new HavingAndOr<>());
        List<ComparisonDataBlockLinker> comparisonSqlPartDataLinkers = havingLinker.takeoutComparisonSqlPartDataLinkers();
        if (comparisonSqlPartDataLinkers == null || comparisonSqlPartDataLinkers.size() == 0) {
            return this;
        }
        ComparisonDataBlockLinker havingDataLinker = new ComparisonDataBlockLinker(LinkType.OR);
        havingDataLinker.setComparisonSqlPartDataLinkers(comparisonSqlPartDataLinkers);
        this.comparisonSqlPartDataLinkers.add(havingDataLinker);
        return this;
    }
}