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
     * get helper
     *
     * @return extends {@link Helper} object
     */
    T getHelper();

    /**
     * get abstract comparison sql part datum
     *
     * @return extends {@link AbstractComparisonDataBlock}
     */
    AbstractComparisonDataBlock<S> getAbstractComparisonSqlPartDatum();

    /**
     * Add abstract comparison sql part datum.
     *
     * @param abstractComparisonSqlPartDatum Implements {@link AbstractComparisonDataBlock} object.
     */
    void addAbstractComparisonSqlPartDatum(AbstractComparisonDataBlock<S> abstractComparisonSqlPartDatum);

    @Override
    default T equalTo(SB dataBlockBuilder) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.EQUAL, dataBlockBuilder.getAbstractComparisonSqlPartDatum()));
        return this.getHelper();
    }

    @Override
    default T notEqualTo(SB dataBlockBuilder) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.NOT_EQUAL, dataBlockBuilder.getAbstractComparisonSqlPartDatum()));
        return this.getHelper();
    }

    @Override
    default T greaterThan(SB dataBlockBuilder) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.GREATER, dataBlockBuilder.getAbstractComparisonSqlPartDatum()));
        return this.getHelper();
    }

    @Override
    default T greaterThanAndEqualTo(SB dataBlockBuilder) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.GREATER_EQUAL, dataBlockBuilder.getAbstractComparisonSqlPartDatum()));
        return this.getHelper();
    }

    @Override
    default T lessThan(SB dataBlockBuilder) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.LESS, dataBlockBuilder.getAbstractComparisonSqlPartDatum()));
        return this.getHelper();
    }

    @Override
    default T lessThanAndEqualTo(SB dataBlockBuilder) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.LESS_EQUAL, dataBlockBuilder.getAbstractComparisonSqlPartDatum()));
        return this.getHelper();
    }

    @Override
    default T between(SB dataBlockBuilder, SB secondOnSqlPartDatumBuilder) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetPairSqlPartDatum(ComparisonType.BETWEEN, dataBlockBuilder.getAbstractComparisonSqlPartDatum(), secondOnSqlPartDatumBuilder.getAbstractComparisonSqlPartDatum()));
        return this.getHelper();
    }

    @Override
    default T like(SB dataBlockBuilder) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetSingleSqlPartDatum(ComparisonType.LIKE, dataBlockBuilder.getAbstractComparisonSqlPartDatum()));
        return this.getHelper();
    }

    @Override
    default T in(SB... dataBlockBuilders) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetMultiSqlPartDatum(ComparisonType.IN, Arrays.stream(dataBlockBuilders).map(AbstractComparisonDataBlockBuilder::getAbstractComparisonSqlPartDatum).collect(Collectors.toList())));
        return this.getHelper();
    }

    @Override
    default T notIn(SB... dataBlockBuilders) {
        this.addAbstractComparisonSqlPartDatum(this.getAbstractComparisonSqlPartDatum().setTargetMultiSqlPartDatum(ComparisonType.NOT_IN, Arrays.stream(dataBlockBuilders).map(AbstractComparisonDataBlockBuilder::getAbstractComparisonSqlPartDatum).collect(Collectors.toList())));
        return this.getHelper();
    }
}