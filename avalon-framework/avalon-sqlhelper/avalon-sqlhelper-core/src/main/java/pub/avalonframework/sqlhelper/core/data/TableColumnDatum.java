package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.sqlhelper.core.data.block.ColumnDataBlock;
import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;

import java.util.List;

/**
 * @author baichao
 */
public final class TableColumnDatum {

    private String tableAlias;

    private List<ColumnDataBlock> columnDataBlocks;

    public TableColumnDatum(String tableAlias, List<ColumnDataBlock> columnDataBlocks) {
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
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