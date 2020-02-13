package pub.avalonframework.sqlhelper.core.data.block;

import pub.avalonframework.sqlhelper.core.data.ComparisonDataBlockLinker;
import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;

import java.util.List;

/**
 * @author baichao
 */
public final class TableHavingDataBlock {

    private String tableAlias;

    private List<ComparisonDataBlockLinker> comparisonSqlPartDataLinkers;

    public TableHavingDataBlock(String tableAlias, List<ComparisonDataBlockLinker> comparisonSqlPartDataLinkers) {
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
        }
        this.tableAlias = tableAlias;
        this.comparisonSqlPartDataLinkers = comparisonSqlPartDataLinkers;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<ComparisonDataBlockLinker> getComparisonSqlPartDataLinkers() {
        return comparisonSqlPartDataLinkers;
    }
}
