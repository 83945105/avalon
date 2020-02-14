package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.block.TableColumnDataBlock;

/**
 * @author baichao
 */
public interface InsertInjector<R> extends ConfigurationInjector<R> {

    /**
     * Add insert table column data block.
     *
     * @param tableColumnDataBlock {@link TableColumnDataBlock}
     * @return
     */
    default R addInsertTableColumnDataBlock(TableColumnDataBlock tableColumnDataBlock) {
        return getDataStore().addInsertTableColumnDataBlock(tableColumnDataBlock);
    }
}