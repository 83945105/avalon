package pub.avalonframework.sqlhelper.core.data.block;

import pub.avalonframework.sqlhelper.core.helper.TableAliasNullException;

import java.util.List;

/**
 * @author baichao
 */
public final class TableHavingDataBlock {

    private String tableAlias;

    private List<ComparisonDataBlockLinker> comparisonDataBlockLinkers;

    public TableHavingDataBlock(String tableAlias, List<ComparisonDataBlockLinker> comparisonDataBlockLinkers) {
        if (tableAlias == null) {
            throw new TableAliasNullException();
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
