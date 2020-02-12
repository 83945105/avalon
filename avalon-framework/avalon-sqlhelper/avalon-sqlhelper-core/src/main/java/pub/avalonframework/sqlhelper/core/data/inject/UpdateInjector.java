package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.TableColumnDatum;

/**
 * @author baichao
 */
public interface UpdateInjector extends ConfigurationInjector, JoinDataInjector, OnDataInjector, WhereDataInjector {

    /**
     * add update table column data
     *
     * @param tableColumnDatum {@link TableColumnDatum}
     */
    void addUpdateTableColumnDatum(TableColumnDatum tableColumnDatum);
}