package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.block.TableColumnDataBlock;

/**
 * @author baichao
 */
public interface UpdateInjector extends ConfigurationInjector, JoinDataInjector, OnDataInjector, WhereDataInjector {

    /**
     * Add update table column data block.
     *
     * @param tableColumnDataBlock {@link TableColumnDataBlock}
     */
    void addUpdateTableColumnDataBlock(TableColumnDataBlock tableColumnDataBlock);
}