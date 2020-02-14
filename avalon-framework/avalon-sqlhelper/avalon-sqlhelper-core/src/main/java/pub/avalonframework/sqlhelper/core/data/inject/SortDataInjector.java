package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.block.TableSortDataBlock;

/**
 * @author baichao
 */
public interface SortDataInjector<R> extends DataInjector<R> {

    /**
     * Add table sort data block.
     *
     * @param tableSortDataBlock {@link TableSortDataBlock}
     * @return
     */
    default R addTableSortDataBlock(TableSortDataBlock tableSortDataBlock) {
        return getDataStore().addTableSortDataBlock(tableSortDataBlock);
    }
}