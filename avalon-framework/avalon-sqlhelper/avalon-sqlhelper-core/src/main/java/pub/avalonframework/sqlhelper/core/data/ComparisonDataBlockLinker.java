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

    private List<? extends AbstractComparisonDataBlock> comparisonSqlPartData;

    private List<ComparisonDataBlockLinker> comparisonSqlPartDataLinkers;

    public LinkType getLinkType() {
        return linkType;
    }

    public List<? extends AbstractComparisonDataBlock> getComparisonSqlPartData() {
        return comparisonSqlPartData;
    }

    public ComparisonDataBlockLinker setComparisonSqlPartData(List<? extends AbstractComparisonDataBlock> comparisonSqlPartData) {
        this.comparisonSqlPartData = comparisonSqlPartData;
        return this;
    }

    public List<ComparisonDataBlockLinker> getComparisonSqlPartDataLinkers() {
        return comparisonSqlPartDataLinkers;
    }

    public ComparisonDataBlockLinker setComparisonSqlPartDataLinkers(List<ComparisonDataBlockLinker> comparisonSqlPartDataLinkers) {
        this.comparisonSqlPartDataLinkers = comparisonSqlPartDataLinkers;
        return this;
    }
}