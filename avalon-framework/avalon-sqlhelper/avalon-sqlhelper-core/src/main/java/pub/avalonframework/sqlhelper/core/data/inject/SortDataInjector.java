package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.block.TableSortDataBlock;

/**
 * @author baichao
 */
public interface SortDataInjector {

    /**
     * add table sort data
     *
     * @param tableSortDatum {@link TableSortDataBlock}
     */
    void addTableSortDatum(TableSortDataBlock tableSortDatum);
}