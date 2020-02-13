package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.block.TableColumnDataBlock;

/**
 * @author baichao
 */
public interface SelectInjector extends ConfigurationInjector, ColumnDataInjector, JoinDataInjector, OnDataInjector, WhereDataInjector, GroupDataInjector, HavingDataInjector, SortDataInjector, LimitDataInjector {

    /**
     * Add select table column data block.
     *
     * @param tableColumnDataBlock {@link TableColumnDataBlock}
     */
    void addSelectTableColumnDataBlock(TableColumnDataBlock tableColumnDataBlock);
}