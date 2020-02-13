package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.block.TableWhereDataBlock;

/**
 * @author baichao
 */
public interface WhereDataInjector {

    /**
     * add table where data
     *
     * @param tableWhereDatum {@link TableWhereDataBlock}
     */
    void addTableWhereDatum(TableWhereDataBlock tableWhereDatum);
}