package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.sqlhelper.core.data.block.SortDataBlock;
import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;

import java.util.List;

/**
 * @author baichao
 */
public final class TableSortDatum {

    private String tableAlias;

    private List<SortDataBlock> sortDataBlocks;

    public TableSortDatum(String tableAlias, List<SortDataBlock> sortDataBlocks) {
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
        }
        this.tableAlias = tableAlias;
        this.sortDataBlocks = sortDataBlocks;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<SortDataBlock> getSortDataBlocks() {
        return sortDataBlocks;
    }
}