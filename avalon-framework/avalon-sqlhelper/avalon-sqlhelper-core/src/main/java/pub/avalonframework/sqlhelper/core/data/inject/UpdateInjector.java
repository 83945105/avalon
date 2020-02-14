package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.block.TableColumnDataBlock;

/**
 * @author baichao
 */
public interface UpdateInjector<R> extends ConfigurationInjector<R>, JoinDataInjector<R>, OnDataInjector<R>, WhereDataInjector<R> {

    /**
     * Add update table column data block.
     *
     * @param tableColumnDataBlock {@link TableColumnDataBlock}
     * @return
     */
    default R addUpdateTableColumnDataBlock(TableColumnDataBlock tableColumnDataBlock) {
        return getDataStore().addUpdateTableColumnDataBlock(tableColumnDataBlock);
    }
}