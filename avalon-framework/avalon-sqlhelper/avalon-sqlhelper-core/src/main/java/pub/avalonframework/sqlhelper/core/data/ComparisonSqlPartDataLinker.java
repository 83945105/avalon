package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.sqlhelper.core.beans.LinkType;
import pub.avalonframework.sqlhelper.core.data.block.AbstractComparisonDataBlock;

import java.util.List;

/**
 * @author baichao
 */
public final class ComparisonSqlPartDataLinker {

    private LinkType linkType;

    public ComparisonSqlPartDataLinker(LinkType linkType) {
        this.linkType = linkType;
    }

    private List<? extends AbstractComparisonDataBlock> comparisonSqlPartData;

    private List<ComparisonSqlPartDataLinker> comparisonSqlPartDataLinkers;

    public LinkType getLinkType() {
        return linkType;
    }

    public List<? extends AbstractComparisonDataBlock> getComparisonSqlPartData() {
        return comparisonSqlPartData;
    }

    public ComparisonSqlPartDataLinker setComparisonSqlPartData(List<? extends AbstractComparisonDataBlock> comparisonSqlPartData) {
        this.comparisonSqlPartData = comparisonSqlPartData;
        return this;
    }

    public List<ComparisonSqlPartDataLinker> getComparisonSqlPartDataLinkers() {
        return comparisonSqlPartDataLinkers;
    }

    public ComparisonSqlPartDataLinker setComparisonSqlPartDataLinkers(List<ComparisonSqlPartDataLinker> comparisonSqlPartDataLinkers) {
        this.comparisonSqlPartDataLinkers = comparisonSqlPartDataLinkers;
        return this;
    }
}