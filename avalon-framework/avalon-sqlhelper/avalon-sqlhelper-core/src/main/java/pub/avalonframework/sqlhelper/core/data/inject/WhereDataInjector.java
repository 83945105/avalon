package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.block.TableWhereDataBlock;

/**
 * @author baichao
 */
public interface WhereDataInjector {

    /**
     * Add table where data block.
     *
     * @param tableWhereDataBlock {@link TableWhereDataBlock}
     */
    void addTableWhereDataBlock(TableWhereDataBlock tableWhereDataBlock);
}