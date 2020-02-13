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
     * get configuration.
     *
     * @return {@link SqlhelperConfiguration}
     */
    SqlhelperConfiguration getConfiguration();

    /**
     * get main table data
     *
     * @return {@link TableMainDataBlock}
     */
    TableMainDataBlock getMainTableDatum();

    /**
     * get select table column data
     *
     * @return {@link TableColumnDataBlock}
     */
    List<TableColumnDataBlock> getSelectTableColumnData();

    /**
     * get insert table column data
     *
     * @return {@link TableColumnDataBlock}
     */
    List<TableColumnDataBlock> getInsertTableColumnData();

    /**
     * get update table column data
     *
     * @return {@link TableColumnDataBlock}
     */
    List<TableColumnDataBlock> getUpdateTableColumnData();

    /**
     * get join table alias and data
     *
     * @return key - table alias | value - {@link TableJoinDataBlock}
     */
    LinkedHashMap<String, TableJoinDataBlock> getAliasJoinTableData();

    /**
     * get table where data
     *
     * @return {@link TableWhereDataBlock}
     */
    List<TableWhereDataBlock> getTableWhereData();

    /**
     * get table group data
     *
     * @return {@link TableGroupDataBlock}
     */
    List<TableGroupDataBlock> getTableGroupData();

    /**
     * get table having data
     *
     * @return {@link TableHavingDataBlock}
     */
    List<TableHavingDataBlock> getTableHavingData();

    /**
     * get table sort data
     *
     * @return {@link TableSortDataBlock}
     */
    List<TableSortDataBlock> getTableSortData();

    /**
     * get limit data
     *
     * @return The Limit data
     */
    Long getLimit();

    /**
     * get offset data
     *
     * @return The offset data
     */
    Long getOffset();
}