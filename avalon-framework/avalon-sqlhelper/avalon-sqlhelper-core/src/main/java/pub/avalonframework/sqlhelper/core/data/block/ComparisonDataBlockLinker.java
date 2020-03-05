package pub.avalonframework.sqlhelper.core.data.block;

import pub.avalonframework.sqlhelper.core.expression.AndOr;

import java.util.List;

/**
 * @author baichao
 */
public final class ComparisonDataBlockLinker {

    private AndOr andOr;

    public ComparisonDataBlockLinker(AndOr andOr) {
        this.andOr = andOr;
    }

    private List<? extends AbstractComparisonDataBlock<?>> comparisonDataBlocks;

    private List<ComparisonDataBlockLinker> comparisonDataBlockLinkers;

    public AndOr getAndOr() {
        return andOr;
    }

    public List<? extends AbstractComparisonDataBlock<?>> getComparisonDataBlocks() {
        return comparisonDataBlocks;
    }

    public ComparisonDataBlockLinker setComparisonDataBlocks(List<? extends AbstractComparisonDataBlock<?>> comparisonDataBlocks) {
        this.comparisonDataBlocks = comparisonDataBlocks;
        return this;
    }

    public List<ComparisonDataBlockLinker> getComparisonDataBlockLinkers() {
        return comparisonDataBlockLinkers;
    }

    public ComparisonDataBlockLinker setComparisonDataBlockLinkers(List<ComparisonDataBlockLinker> comparisonDataBlockLinkers) {
        this.comparisonDataBlockLinkers = comparisonDataBlockLinkers;
        return this;
    }
}