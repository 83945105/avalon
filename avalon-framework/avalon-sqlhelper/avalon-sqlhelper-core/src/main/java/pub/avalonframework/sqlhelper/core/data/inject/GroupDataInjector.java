package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.block.TableGroupDataBlock;

/**
 * @author baichao
 */
public interface GroupDataInjector {

    /**
     * Add table group data block.
     *
     * @param tableGroupDataBlock {@link TableGroupDataBlock}
     */
    void addTableGroupDataBlock(TableGroupDataBlock tableGroupDataBlock);
}