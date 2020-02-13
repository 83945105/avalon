package pub.avalonframework.sqlhelper.core.data.block.builder;

import pub.avalonframework.sqlhelper.core.data.block.AbstractComparisonDataBlock;
import pub.avalonframework.sqlhelper.core.helper.Helper;

/**
 * @author baichao
 */
public abstract class AbstractComparisonDataBlockBuilder<T extends Helper, S extends AbstractComparisonDataBlock<S>> extends AbstractDataBlockBuilder<T, S> {

    public AbstractComparisonDataBlockBuilder(String tableAlias, T helper) {
        super(tableAlias, helper);
    }

    /**
     * Get comparison data block.
     *
     * @return extends {@link AbstractComparisonDataBlock}
     */
    public abstract AbstractComparisonDataBlock<S> getComparisonDataBlock();
}