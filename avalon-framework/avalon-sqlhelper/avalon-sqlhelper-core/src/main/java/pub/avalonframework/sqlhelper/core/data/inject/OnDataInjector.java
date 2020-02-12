package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.TableOnDatum;

/**
 * @author baichao
 */
public interface OnDataInjector {

    /**
     * add table on data
     *
     * @param tableOnDatum {@link TableOnDatum}
     */
    void addTableOnDatum(TableOnDatum tableOnDatum);
}