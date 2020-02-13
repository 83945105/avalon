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

    private List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = new ArrayList<>();

    @Override
    public List<ComparisonDataBlockLinker> takeoutComparisonDataBlockLinker() {
        List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = this.comparisonDataBlockLinkers;
        this.comparisonDataBlockLinkers = new ArrayList<>();
        return comparisonDataBlockLinkers;
    }

    @Override
    public HavingAndOr<TH> and(HavingHelper<?> havingHelper) {
        if (havingHelper == null) {
            return this;
        }
        ComparisonDataBlockLinker comparisonDataBlockLinker = new ComparisonDataBlockLinker(LinkType.AND);
        List<HavingDataBlock> havingDataBlocks = havingHelper.takeoutHavingDataBlocks();
        if (havingDataBlocks == null || havingDataBlocks.size() == 0) {
            return this;
        }
        comparisonDataBlockLinker.setComparisonDataBlocks(havingDataBlocks);
        this.comparisonDataBlockLinkers.add(comparisonDataBlockLinker);
        return this;
    }

    @Override
    public HavingAndOr<TH> and(HavingLinkerCallback<TH> havingLinkerCallback) {
        if (havingLinkerCallback == null) {
            return this;
        }
        HavingLinker<TH> havingLinker = havingLinkerCallback.apply(new HavingAndOr<>());
        List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = havingLinker.takeoutComparisonDataBlockLinker();
        if (comparisonDataBlockLinkers == null || comparisonDataBlockLinkers.size() == 0) {
            return this;
        }
        ComparisonDataBlockLinker havingDataLinker = new ComparisonDataBlockLinker(LinkType.AND);
        havingDataLinker.setComparisonDataBlockLinkers(comparisonDataBlockLinkers);
        this.comparisonDataBlockLinkers.add(havingDataLinker);
        return this;
    }

    /**
     * Or
     *
     * @param havingHelper {@link HavingHelper}
     * @return {@link HavingAndOr}
     */
    public HavingAndOr<TH> or(HavingHelper<?> havingHelper) {
        if (havingHelper == null) {
            return this;
        }
        ComparisonDataBlockLinker comparisonDataBlockLinker = new ComparisonDataBlockLinker(LinkType.OR);
        List<HavingDataBlock> havingDataBlocks = havingHelper.takeoutHavingDataBlocks();
        if (havingDataBlocks == null || havingDataBlocks.size() == 0) {
            return this;
        }
        comparisonDataBlockLinker.setComparisonDataBlocks(havingDataBlocks);
        this.comparisonDataBlockLinkers.add(comparisonDataBlockLinker);
        return this;
    }

    /**
     * Or
     *
     * @param havingLinkerCallback {@link HavingLinkerCallback}
     * @return {@link HavingAndOr}
     */
    public HavingAndOr<TH> or(HavingLinkerCallback<TH> havingLinkerCallback) {
        if (havingLinkerCallback == null) {
            return this;
        }
        HavingLinker<TH> havingLinker = havingLinkerCallback.apply(new HavingAndOr<>());
        List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = havingLinker.takeoutComparisonDataBlockLinker();
        if (comparisonDataBlockLinkers == null || comparisonDataBlockLinkers.size() == 0) {
            return this;
        }
        ComparisonDataBlockLinker havingDataLinker = new ComparisonDataBlockLinker(LinkType.OR);
        havingDataLinker.setComparisonDataBlockLinkers(comparisonDataBlockLinkers);
        this.comparisonDataBlockLinkers.add(havingDataLinker);
        return this;
    }
}