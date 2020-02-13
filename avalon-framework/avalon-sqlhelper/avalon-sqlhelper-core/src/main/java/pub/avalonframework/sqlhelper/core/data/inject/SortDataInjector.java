package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.block.TableSortDataBlock;

/**
 * @author baichao
 */
public interface SortDataInjector {

    /**
     * Add table sort data block.
     *
     * @param tableSortDataBlock {@link TableSortDataBlock}
     */
    void addTableSortDataBlock(TableSortDataBlock tableSortDataBlock);
}