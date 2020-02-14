package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.block.TableGroupDataBlock;

/**
 * @author baichao
 */
public interface GroupDataInjector<R> extends DataInjector<R> {

    /**
     * Add table group data block.
     *
     * @param tableGroupDataBlock {@link TableGroupDataBlock}
     * @return
     */
    default R addTableGroupDataBlock(TableGroupDataBlock tableGroupDataBlock) {
        return getDataStore().addTableGroupDataBlock(tableGroupDataBlock);
    }
}