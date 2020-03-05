package pub.avalonframework.sqlhelper.core.data.block;

import pub.avalonframework.sqlhelper.core.helper.TableAliasNullException;

import java.util.List;

/**
 * @author baichao
 */
public final class TableSortDataBlock {

    private String tableAlias;

    private List<SortDataBlock> sortDataBlocks;

    public TableSortDataBlock(String tableAlias, List<SortDataBlock> sortDataBlocks) {
        if (tableAlias == null) {
            throw new TableAliasNullException();
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