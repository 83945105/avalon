package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.sqlhelper.core.beans.LinkType;
import pub.avalonframework.sqlhelper.core.data.block.AbstractComparisonDataBlock;

import java.util.List;

/**
 * @author baichao
 */
public final class ComparisonDataBlockLinker {

    private LinkType linkType;

    public ComparisonDataBlockLinker(LinkType linkType) {
        this.linkType = linkType;
    }

    private List<? extends AbstractComparisonDataBlock> comparisonDataBlocks;

    private List<ComparisonDataBlockLinker> comparisonDataBlockLinkers;

    public LinkType getLinkType() {
        return linkType;
    }

    public List<? extends AbstractComparisonDataBlock> getComparisonDataBlocks() {
        return comparisonDataBlocks;
    }

    public ComparisonDataBlockLinker setComparisonDataBlocks(List<? extends AbstractComparisonDataBlock> comparisonDataBlocks) {
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