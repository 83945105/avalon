package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.block.TableColumnDataBlock;

/**
 * @author baichao
 */
public interface InsertInjector extends ConfigurationInjector {

    /**
     * Add insert table column data block.
     *
     * @param tableColumnDataBlock {@link TableColumnDataBlock}
     */
    void addInsertTableColumnDataBlock(TableColumnDataBlock tableColumnDataBlock);
}