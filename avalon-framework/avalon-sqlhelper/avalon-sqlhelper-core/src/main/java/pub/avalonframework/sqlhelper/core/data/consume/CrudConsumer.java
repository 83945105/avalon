package pub.avalonframework.sqlhelper.core.data.consume;

import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.*;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author baichao
 */
public interface CrudConsumer {

    /**
     * Get configuration.
     *
     * @return {@link SqlhelperConfiguration}
     */
    SqlhelperConfiguration getConfiguration();

    /**
     * Get table main data block.
     *
     * @return {@link TableMainDataBlock}
     */
    TableMainDataBlock getTableMainDataBlock();

    /**
     * Get select table column data blocks.
     *
     * @return {@link TableColumnDataBlock}
     */
    List<TableColumnDataBlock> getSelectTableColumnDataBlocks();

    /**
     * Get insert table column data blocks.
     *
     * @return {@link TableColumnDataBlock}
     */
    List<TableColumnDataBlock> getInsertTableColumnDataBlocks();

    /**
     * Get update table column data blocks.
     *
     * @return {@link TableColumnDataBlock}
     */
    List<TableColumnDataBlock> getUpdateTableColumnDataBlocks();

    /**
     * Get alias table join data block map.
     *
     * @return key - table alias | value - {@link TableJoinDataBlock}
     */
    LinkedHashMap<String, TableJoinDataBlock> getAliasTableJoinDataBlockMap();

    /**
     * Get table where data blocks.
     *
     * @return {@link TableWhereDataBlock}
     */
    List<TableWhereDataBlock> getTableWhereDataBlocks();

    /**
     * Get table group data blocks.
     *
     * @return {@link TableGroupDataBlock}
     */
    List<TableGroupDataBlock> getTableGroupDataBlocks();

    /**
     * Get table having data blocks.
     *
     * @return {@link TableHavingDataBlock}
     */
    List<TableHavingDataBlock> getTableHavingDataBlocks();

    /**
     * Get table sort data blocks.
     *
     * @return {@link TableSortDataBlock}
     */
    List<TableSortDataBlock> getTableSortDataBlocks();

    /**
     * Get limit number.
     *
     * @return The Limit number.
     */
    Long getLimit();

    /**
     * Get offset number.
     *
     * @return The offset number.
     */
    Long getOffset();
}