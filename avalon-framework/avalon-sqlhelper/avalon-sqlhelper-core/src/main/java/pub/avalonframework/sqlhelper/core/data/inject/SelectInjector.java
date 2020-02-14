package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.block.TableColumnDataBlock;

/**
 * @author baichao
 */
public interface SelectInjector<R> extends ConfigurationInjector<R>, ColumnDataInjector<R>, JoinDataInjector<R>, OnDataInjector<R>, WhereDataInjector<R>, GroupDataInjector<R>, HavingDataInjector<R>, SortDataInjector<R>, LimitDataInjector<R> {

    /**
     * Add select table column data block.
     *
     * @param tableColumnDataBlock {@link TableColumnDataBlock}
     * @return
     */
    default R addSelectTableColumnDataBlock(TableColumnDataBlock tableColumnDataBlock) {
        return getDataStore().addSelectTableColumnDataBlock(tableColumnDataBlock);
    }
}