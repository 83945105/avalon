package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.block.TableWhereDataBlock;

/**
 * @author baichao
 */
public interface WhereDataInjector<R> extends DataInjector<R> {

    /**
     * Add table where data block.
     *
     * @param tableWhereDataBlock {@link TableWhereDataBlock}
     * @return
     */
    default R addTableWhereDataBlock(TableWhereDataBlock tableWhereDataBlock) {
        return getDataStore().addTableWhereDataBlock(tableWhereDataBlock);
    }
}