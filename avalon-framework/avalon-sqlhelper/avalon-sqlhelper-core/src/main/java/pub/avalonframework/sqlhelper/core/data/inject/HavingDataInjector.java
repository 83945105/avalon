package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.block.TableHavingDataBlock;

/**
 * @author baichao
 */
public interface HavingDataInjector {

    /**
     * Add table having data block.
     *
     * @param tableHavingDataBlock {@link TableHavingDataBlock}
     */
    void addTableHavingDataBlock(TableHavingDataBlock tableHavingDataBlock);
}