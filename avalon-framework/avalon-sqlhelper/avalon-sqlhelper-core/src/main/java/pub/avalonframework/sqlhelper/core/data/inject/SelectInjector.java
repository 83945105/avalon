package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.block.TableColumnDataBlock;

/**
 * @author baichao
 */
public interface SelectInjector extends ConfigurationInjector, ColumnDataInjector, JoinDataInjector, OnDataInjector, WhereDataInjector, GroupDataInjector, HavingDataInjector, SortDataInjector, LimitDataInjector {

    /**
     * add select table column data
     *
     * @param tableColumnDatum {@link TableColumnDataBlock}
     */
    void addSelectTableColumnDatum(TableColumnDataBlock tableColumnDatum);
}