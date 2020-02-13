package pub.avalonframework.sqlhelper.core.data.block;

import pub.avalonframework.sqlhelper.core.data.ComparisonDataBlockLinker;
import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;

import java.util.List;

/**
 * @author baichao
 */
public final class TableWhereDataBlock {

    private String tableAlias;

    private List<ComparisonDataBlockLinker> comparisonDataBlockLinkers;

    public TableWhereDataBlock(String tableAlias, List<ComparisonDataBlockLinker> comparisonDataBlockLinkers) {
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
        }
        this.tableAlias = tableAlias;
        this.comparisonDataBlockLinkers = comparisonDataBlockLinkers;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<ComparisonDataBlockLinker> getComparisonDataBlockLinkers() {
        return comparisonDataBlockLinkers;
    }
}