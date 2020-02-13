package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.block.TableHavingDataBlock;

/**
 * @author baichao
 */
public interface HavingDataInjector {

    /**
     * add table having data
     *
     * @param tableHavingDatum {@link TableHavingDataBlock}
     */
    void addTableHavingDatum(TableHavingDataBlock tableHavingDatum);
}