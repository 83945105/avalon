package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.block.TableOnDataBlock;

/**
 * @author baichao
 */
public interface OnDataInjector<R> extends DataInjector<R> {

    /**
     * Add table on data block.
     *
     * @param tableOnDataBlock {@link TableOnDataBlock}
     * @return
     */
    default R addTableOnDataBlock(TableOnDataBlock tableOnDataBlock) {
        return getDataStore().addTableOnDataBlock(tableOnDataBlock);
    }
}