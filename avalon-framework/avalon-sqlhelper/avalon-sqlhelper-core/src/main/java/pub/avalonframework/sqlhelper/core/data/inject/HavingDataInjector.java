package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.TableHavingDatum;

/**
 * @author baichao
 */
public interface HavingDataInjector {

    /**
     * add table having data
     *
     * @param tableHavingDatum {@link TableHavingDatum}
     */
    void addTableHavingDatum(TableHavingDatum tableHavingDatum);
}