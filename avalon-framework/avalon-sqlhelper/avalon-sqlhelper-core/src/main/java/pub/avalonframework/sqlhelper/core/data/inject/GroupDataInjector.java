package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.block.TableGroupDataBlock;

/**
 * @author baichao
 */
public interface GroupDataInjector {

    /**
     * add table group data
     *
     * @param tableGroupDatum {@link TableGroupDataBlock}
     */
    void addTableGroupDatum(TableGroupDataBlock tableGroupDatum);
}