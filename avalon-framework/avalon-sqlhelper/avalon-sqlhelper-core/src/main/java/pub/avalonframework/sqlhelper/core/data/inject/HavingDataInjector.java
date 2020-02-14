package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.block.TableHavingDataBlock;

/**
 * @author baichao
 */
public interface HavingDataInjector<R> extends DataInjector<R> {

    /**
     * Add table having data block.
     *
     * @param tableHavingDataBlock {@link TableHavingDataBlock}
     * @return
     */
    default R addTableHavingDataBlock(TableHavingDataBlock tableHavingDataBlock) {
        return getDataStore().addTableHavingDataBlock(tableHavingDataBlock);
    }
}