package pub.avalonframework.sqlhelper.core.rules;

import pub.avalonframework.sqlhelper.core.data.block.builder.AbstractComparisonDataBlockBuilder;
import pub.avalonframework.sqlhelper.core.data.block.builder.DataBlockBuilder;
import pub.avalonframework.sqlhelper.core.helper.Helper;

/**
 * @author baichao
 */
public interface ToSqlPartBuilderComparisonOperator<T extends Helper, SB extends AbstractComparisonDataBlockBuilder> {

    /**
     * equal to
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T equalTo(SB dataBlockBuilder);

    /**
     * not equal to
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T notEqualTo(SB dataBlockBuilder);

    /**
     * greater than
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T greaterThan(SB dataBlockBuilder);

    /**
     * greater than or equal to
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T greaterThanAndEqualTo(SB dataBlockBuilder);

    /**
     * less than
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T lessThan(SB dataBlockBuilder);

    /**
     * less than or equal to
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T lessThanAndEqualTo(SB dataBlockBuilder);

    /**
     * between ... and ...
     *
     * @param dataBlockBuilder       {@link DataBlockBuilder}
     * @param secondSqlPartDatumBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T between(SB dataBlockBuilder, SB secondSqlPartDatumBuilder);

    /**
     * like
     *
     * @param dataBlockBuilder {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T like(SB dataBlockBuilder);

    /**
     * in
     *
     * @param dataBlockBuilders {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T in(SB... dataBlockBuilders);

    /**
     * not in
     *
     * @param dataBlockBuilders {@link DataBlockBuilder}
     * @return extends {@link Helper} object
     */
    T notIn(SB... dataBlockBuilders);
}