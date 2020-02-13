package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.block.TableColumnDataBlock;

/**
 * @author baichao
 */
public interface InsertInjector extends ConfigurationInjector {

    /**
     * add insert table column data
     *
     * @param tableColumnDatum {@link TableColumnDataBlock}
     */
    void addInsertTableColumnDatum(TableColumnDataBlock tableColumnDatum);
}