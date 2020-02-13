package pub.avalonframework.sqlhelper.core.rules.impl;

import pub.avalonframework.sqlhelper.core.data.ComparisonType;
import pub.avalonframework.sqlhelper.core.data.block.AbstractComparisonDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.builder.AbstractComparisonDataBlockBuilder;
import pub.avalonframework.sqlhelper.core.helper.Helper;
import pub.avalonframework.sqlhelper.core.rules.ToDataBlockBuilderComparisonOperator;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author baichao
 */
public interface ToDataBlockBuilderComparisonOperatorImpl<T extends Helper, S extends AbstractComparisonDataBlock<S>, SB extends AbstractComparisonDataBlockBuilder> extends ToDataBlockBuilderComparisonOperator<T, SB> {

    /**
     * Get helper.
     *
     * @return extends {@link Helper} object
     */
    T getHelper();

    /**
     * Get comparison data block.
     *
     * @return extends {@link AbstractComparisonDataBlock}
     */
    AbstractComparisonDataBlock<S> getComparisonDataBlock();

    /**
     * Add comparison data block.
     *
     * @param comparisonDataBlock Implements {@link AbstractComparisonDataBlock} object.
     */
    void addComparisonDataBlock(AbstractComparisonDataBlock<S> comparisonDataBlock);

    @Override
    default T equalTo(SB dataBlockBuilder) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSingleDataBlock(ComparisonType.EQUAL, dataBlockBuilder.getComparisonDataBlock()));
        return this.getHelper();
    }

    @Override
    default T notEqualTo(SB dataBlockBuilder) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSingleDataBlock(ComparisonType.NOT_EQUAL, dataBlockBuilder.getComparisonDataBlock()));
        return this.getHelper();
    }

    @Override
    default T greaterThan(SB dataBlockBuilder) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSingleDataBlock(ComparisonType.GREATER, dataBlockBuilder.getComparisonDataBlock()));
        return this.getHelper();
    }

    @Override
    default T greaterThanAndEqualTo(SB dataBlockBuilder) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSingleDataBlock(ComparisonType.GREATER_EQUAL, dataBlockBuilder.getComparisonDataBlock()));
        return this.getHelper();
    }

    @Override
    default T lessThan(SB dataBlockBuilder) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSingleDataBlock(ComparisonType.LESS, dataBlockBuilder.getComparisonDataBlock()));
        return this.getHelper();
    }

    @Override
    default T lessThanAndEqualTo(SB dataBlockBuilder) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSingleDataBlock(ComparisonType.LESS_EQUAL, dataBlockBuilder.getComparisonDataBlock()));
        return this.getHelper();
    }

    @Override
    default T between(SB dataBlockBuilder, SB secondDataBlockBuilder) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetPairDataBlock(ComparisonType.BETWEEN, dataBlockBuilder.getComparisonDataBlock(), secondDataBlockBuilder.getComparisonDataBlock()));
        return this.getHelper();
    }

    @Override
    default T like(SB dataBlockBuilder) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSingleDataBlock(ComparisonType.LIKE, dataBlockBuilder.getComparisonDataBlock()));
        return this.getHelper();
    }

    @Override
    default T in(SB... dataBlockBuilders) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetMultiDataBlock(ComparisonType.IN, Arrays.stream(dataBlockBuilders).map(AbstractComparisonDataBlockBuilder::getComparisonDataBlock).collect(Collectors.toList())));
        return this.getHelper();
    }

    @Override
    default T notIn(SB... dataBlockBuilders) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetMultiDataBlock(ComparisonType.NOT_IN, Arrays.stream(dataBlockBuilders).map(AbstractComparisonDataBlockBuilder::getComparisonDataBlock).collect(Collectors.toList())));
        return this.getHelper();
    }
}