package pub.avalonframework.sqlhelper.core.expression;

import pub.avalonframework.sqlhelper.core.callback.HavingLinkerCallback;
import pub.avalonframework.sqlhelper.core.data.ComparisonDataBlockLinker;
import pub.avalonframework.sqlhelper.core.data.block.HavingDataBlock;
import pub.avalonframework.sqlhelper.core.helper.HavingHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class HavingAndOrExpression<TH extends HavingHelper<TH>> implements HavingAndExpression<TH> {

    private List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = new ArrayList<>();

    @Override
    public List<ComparisonDataBlockLinker> takeoutComparisonDataBlockLinker() {
        List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = this.comparisonDataBlockLinkers;
        this.comparisonDataBlockLinkers = new ArrayList<>();
        return comparisonDataBlockLinkers;
    }

    @Override
    public HavingAndOrExpression<TH> and(HavingHelper<?> havingHelper) {
        if (havingHelper == null) {
            return this;
        }
        List<HavingDataBlock> havingDataBlocks = havingHelper.takeoutHavingDataBlocks();
        if (havingDataBlocks == null || havingDataBlocks.size() == 0) {
            return this;
        }
        this.comparisonDataBlockLinkers.add(new ComparisonDataBlockLinker(AndOr.AND).setComparisonDataBlocks(havingDataBlocks));
        return this;
    }

    @Override
    public HavingAndOrExpression<TH> and(HavingLinkerCallback<TH> havingLinkerCallback) {
        if (havingLinkerCallback == null) {
            return this;
        }
        HavingAndExpression<TH> havingAndExpression = havingLinkerCallback.apply(new HavingAndOrExpression<>());
        List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = havingAndExpression.takeoutComparisonDataBlockLinker();
        if (comparisonDataBlockLinkers == null || comparisonDataBlockLinkers.size() == 0) {
            return this;
        }
        this.comparisonDataBlockLinkers.add(new ComparisonDataBlockLinker(AndOr.AND).setComparisonDataBlockLinkers(comparisonDataBlockLinkers));
        return this;
    }

    /**
     * Or
     *
     * @param havingHelper {@link HavingHelper}
     * @return {@link HavingAndOrExpression}
     */
    public HavingAndOrExpression<TH> or(HavingHelper<?> havingHelper) {
        if (havingHelper == null) {
            return this;
        }
        List<HavingDataBlock> havingDataBlocks = havingHelper.takeoutHavingDataBlocks();
        if (havingDataBlocks == null || havingDataBlocks.size() == 0) {
            return this;
        }
        this.comparisonDataBlockLinkers.add(new ComparisonDataBlockLinker(AndOr.OR).setComparisonDataBlocks(havingDataBlocks));
        return this;
    }

    /**
     * Or
     *
     * @param havingLinkerCallback {@link HavingLinkerCallback}
     * @return {@link HavingAndOrExpression}
     */
    public HavingAndOrExpression<TH> or(HavingLinkerCallback<TH> havingLinkerCallback) {
        if (havingLinkerCallback == null) {
            return this;
        }
        HavingAndExpression<TH> havingAndExpression = havingLinkerCallback.apply(new HavingAndOrExpression<>());
        List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = havingAndExpression.takeoutComparisonDataBlockLinker();
        if (comparisonDataBlockLinkers == null || comparisonDataBlockLinkers.size() == 0) {
            return this;
        }
        this.comparisonDataBlockLinkers.add(new ComparisonDataBlockLinker(AndOr.OR).setComparisonDataBlockLinkers(comparisonDataBlockLinkers));
        return this;
    }
}