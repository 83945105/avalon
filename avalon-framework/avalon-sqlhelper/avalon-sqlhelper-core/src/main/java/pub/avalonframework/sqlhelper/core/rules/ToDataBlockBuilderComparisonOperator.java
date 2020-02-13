package pub.avalonframework.sqlhelper.core.rules;

import pub.avalonframework.sqlhelper.core.data.block.builder.AbstractComparisonDataBlockBuilder;
import pub.avalonframework.sqlhelper.core.data.block.builder.DataBlockBuilder;
import pub.avalonframework.sqlhelper.core.helper.Helper;

/**
 * @author baichao
 */
public interface ToDataBlockBuilderComparisonOperator<T extends Helper, SB extends AbstractComparisonDataBlockBuilder> {

    /**
     * Equal to
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T equalTo(SB dataBlockBuilder);

    /**
     * Not equal to
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T notEqualTo(SB dataBlockBuilder);

    /**
     * Greater than
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T greaterThan(SB dataBlockBuilder);

    /**
     * Greater than or equal to
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T greaterThanAndEqualTo(SB dataBlockBuilder);

    /**
     * Less than
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T lessThan(SB dataBlockBuilder);

    /**
     * Less than or equal to
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T lessThanAndEqualTo(SB dataBlockBuilder);

    /**
     * Between ... and ...
     *
     * @param dataBlockBuilder       {@link DataBlockBuilder}
     * @param secondDataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T between(SB dataBlockBuilder, SB secondDataBlockBuilder);

    /**
     * Like
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T like(SB dataBlockBuilder);

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
    T notIn(SB... dataBlockBuilders);
}