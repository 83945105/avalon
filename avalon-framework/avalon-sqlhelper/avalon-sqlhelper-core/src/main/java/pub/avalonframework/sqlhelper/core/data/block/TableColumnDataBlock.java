package pub.avalonframework.sqlhelper.core.data.block;

import pub.avalonframework.sqlhelper.core.helper.TableAliasNullException;

import java.util.List;

/**
 * @author baichao
 */
public final class TableColumnDataBlock {

    private String tableAlias;

    private List<ColumnDataBlock> columnDataBlocks;

    public TableColumnDataBlock(String tableAlias, List<ColumnDataBlock> columnDataBlocks) {
        if (tableAlias == null) {
            throw new TableAliasNullException();
        }
        this.tableAlias = tableAlias;
        this.columnDataBlocks = columnDataBlocks;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<ColumnDataBlock> getColumnDataBlocks() {
        return columnDataBlocks;
    }
}