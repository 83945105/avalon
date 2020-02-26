package pub.avalonframework.sqlhelper.core.expression;

import pub.avalonframework.sqlhelper.core.data.block.builder.AbstractComparisonDataBlockBuilder;
import pub.avalonframework.sqlhelper.core.data.block.builder.DataBlockBuilder;
import pub.avalonframework.sqlhelper.core.helper.Helper;

/**
 * @author baichao
 */
public interface DataBlockBuilderComparisonExpression<T extends Helper, SB extends AbstractComparisonDataBlockBuilder> {

    /**
     * Equal to
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T eq(SB dataBlockBuilder);

    /**
     * Not equal to
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T neq(SB dataBlockBuilder);

    /**
     * Greater than
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T gt(SB dataBlockBuilder);

    /**
     * Greater than or equal to
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T gte(SB dataBlockBuilder);

    /**
     * Less than
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T lt(SB dataBlockBuilder);

    /**
     * Less than or equal to
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T lte(SB dataBlockBuilder);

    /**
     * Between ... and ...
     *
     * @param dataBlockBuilder       {@link DataBlockBuilder}
     * @param secondDataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T bt(SB dataBlockBuilder, SB secondDataBlockBuilder);

    /**
     * Like
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T lk(SB dataBlockBuilder);

    /**
     * In
     *
     * @param dataBlockBuilders {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T in(SB... dataBlockBuilders);

    /**
     * Not in
     *
     * @param dataBlockBuilders {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T nin(SB... dataBlockBuilders);
}