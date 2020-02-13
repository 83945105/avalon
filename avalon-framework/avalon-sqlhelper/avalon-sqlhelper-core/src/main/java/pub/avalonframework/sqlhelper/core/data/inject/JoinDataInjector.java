package pub.avalonframework.sqlhelper.core.data.inject;

import pub.avalonframework.sqlhelper.core.data.block.TableJoinDataBlock;

/**
 * @author baichao
 */
public interface JoinDataInjector {

    /**
     * add join table data
     *
     * @param joinTableDatum {@link TableJoinDataBlock}
     */
    void addJoinTableDatum(TableJoinDataBlock joinTableDatum);
}