package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.block.TableOnDataBlock;

/**
 * @author baichao
 */
public interface OnDataInjector {

    /**
     * Add table on data block.
     *
     * @param tableOnDataBlock {@link TableOnDataBlock}
     */
    void addTableOnDataBlock(TableOnDataBlock tableOnDataBlock);
}