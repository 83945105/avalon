package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.TableColumnDatum;

/**
 * @author baichao
 */
public interface InsertInjector extends ConfigurationInjector {

    /**
     * add insert table column data
     *
     * @param tableColumnDatum {@link TableColumnDatum}
     */
    void addInsertTableColumnDatum(TableColumnDatum tableColumnDatum);
}