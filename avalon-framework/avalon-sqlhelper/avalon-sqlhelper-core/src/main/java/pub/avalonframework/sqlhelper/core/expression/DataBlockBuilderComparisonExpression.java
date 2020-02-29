package pub.avalonframework.sqlhelper.core.expression;

import pub.avalonframework.sqlhelper.core.data.block.builder.AbstractComparisonDataBlockBuilder;
import pub.avalonframework.sqlhelper.core.data.block.builder.DataBlockBuilder;
import pub.avalonframework.sqlhelper.core.helper.Helper;

/**
 * @author baichao
 */
public interface DataBlockBuilderComparisonExpression<T extends Helper> {

    /**
     * Equal to
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T eq(AbstractComparisonDataBlockBuilder<?, ?> dataBlockBuilder);

    /**
     * Not equal to
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T neq(AbstractComparisonDataBlockBuilder<?, ?> dataBlockBuilder);

    /**
     * Greater than
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T gt(AbstractComparisonDataBlockBuilder<?, ?> dataBlockBuilder);

    /**
     * Greater than or equal to
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T gte(AbstractComparisonDataBlockBuilder<?, ?> dataBlockBuilder);

    /**
     * Less than
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T lt(AbstractComparisonDataBlockBuilder<?, ?> dataBlockBuilder);

    /**
     * Less than or equal to
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T lte(AbstractComparisonDataBlockBuilder<?, ?> dataBlockBuilder);

    /**
     * Between ... and ...
     *
     * @param dataBlockBuilder       {@link DataBlockBuilder}
     * @param secondDataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T bt(AbstractComparisonDataBlockBuilder<?, ?> dataBlockBuilder, AbstractComparisonDataBlockBuilder<?, ?> secondDataBlockBuilder);

    /**
     * Like
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T lk(AbstractComparisonDataBlockBuilder<?, ?> dataBlockBuilder);

    /**
     * In
     *
     * @param dataBlockBuilders {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T in(AbstractComparisonDataBlockBuilder<?, ?>... dataBlockBuilders);

    /**
     * Not in
     *
     * @param dataBlockBuilders {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T nin(AbstractComparisonDataBlockBuilder<?, ?>... dataBlockBuilders);
}