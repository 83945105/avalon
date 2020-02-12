package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author baichao
 */
public interface SqlDataConsumer {

    /**
     * get sqlhelper configuration.
     *
     * @return {@link SqlhelperConfiguration}
     */
    SqlhelperConfiguration getConfiguration();

    /**
     * get main table data
     *
     * @return {@link MainTableDatum}
     */
    MainTableDatum getMainTableDatum();

    /**
     * get select table column data
     *
     * @return {@link TableColumnDatum}
     */
    List<TableColumnDatum> getSelectTableColumnData();

    /**
     * get insert table column data
     *
     * @return {@link TableColumnDatum}
     */
    List<TableColumnDatum> getInsertTableColumnData();

    /**
     * get update table column data
     *
     * @return {@link TableColumnDatum}
     */
    List<TableColumnDatum> getUpdateTableColumnData();

    /**
     * get join table alias and data
     *
     * @return key - table alias | value - {@link JoinTableDatum}
     */
    LinkedHashMap<String, JoinTableDatum> getAliasJoinTableData();

    /**
     * get table where data
     *
     * @return {@link TableWhereDatum}
     */
    List<TableWhereDatum> getTableWhereData();

    /**
     * get table group data
     *
     * @return {@link TableGroupDatum}
     */
    List<TableGroupDatum> getTableGroupData();

    /**
     * get table having data
     *
     * @return {@link TableHavingDatum}
     */
    List<TableHavingDatum> getTableHavingData();

    /**
     * get table sort data
     *
     * @return {@link TableSortDatum}
     */
    List<TableSortDatum> getTableSortData();

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