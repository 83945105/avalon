package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.block.TableJoinDataBlock;

/**
 * @author baichao
 */
public interface JoinDataInjector<R> extends DataInjector<R> {

    /**
     * Add table join data block.
     *
     * @param tableJoinDataBlock {@link TableJoinDataBlock}
     * @return
     */
    default R addTableJoinDataBlock(TableJoinDataBlock tableJoinDataBlock) {
        return getDataStore().addTableJoinDataBlock(tableJoinDataBlock);
    }
}