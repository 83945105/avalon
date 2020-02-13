package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.block.TableOnDataBlock;

/**
 * @author baichao
 */
public interface OnDataInjector {

    /**
     * add table on data
     *
     * @param tableOnDatum {@link TableOnDataBlock}
     */
    void addTableOnDatum(TableOnDataBlock tableOnDatum);
}