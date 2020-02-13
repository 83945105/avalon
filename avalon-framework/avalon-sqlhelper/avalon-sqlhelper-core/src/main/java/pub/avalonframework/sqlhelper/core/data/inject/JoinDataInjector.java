package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.block.TableJoinDataBlock;

/**
 * @author baichao
 */
public interface JoinDataInjector {

    /**
     * Add table join data block.
     *
     * @param tableJoinDataBlock {@link TableJoinDataBlock}
     */
    void addTableJoinDataBlock(TableJoinDataBlock tableJoinDataBlock);
}