package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.TableColumnDatum;

/**
 * @author baichao
 */
public interface SelectInjector extends ConfigurationInjector, ColumnDataInjector, JoinDataInjector, OnDataInjector, WhereDataInjector, GroupDataInjector, HavingDataInjector, SortDataInjector, LimitDataInjector {

    /**
     * add select table column data
     *
     * @param tableColumnDatum {@link TableColumnDatum}
     */
    void addSelectTableColumnDatum(TableColumnDatum tableColumnDatum);
}