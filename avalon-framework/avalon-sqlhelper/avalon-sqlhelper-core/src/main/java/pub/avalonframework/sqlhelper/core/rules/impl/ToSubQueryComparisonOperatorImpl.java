package pub.avalonframework.sqlhelper.core.rules.impl;

import pub.avalonframework.sqlhelper.core.callback.SubQueryCallback;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.ComparisonType;
import pub.avalonframework.sqlhelper.core.data.block.AbstractComparisonDataBlock;
import pub.avalonframework.sqlhelper.core.helper.Helper;
import pub.avalonframework.sqlhelper.core.rules.ToSubQueryComparisonOperator;

/**
 * @author baichao
 */
public interface ToSubQueryComparisonOperatorImpl<T, S extends AbstractComparisonDataBlock<S>> extends ToSubQueryComparisonOperator<T> {

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
    default T equalToSubQuery(SubQueryCallback subQueryCallback) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSubQuery(ComparisonType.EQUAL, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T notEqualToSubQuery(SubQueryCallback subQueryCallback) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSubQuery(ComparisonType.NOT_EQUAL, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T greaterThanSubQuery(SubQueryCallback subQueryCallback) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSubQuery(ComparisonType.GREATER, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T greaterThanAndEqualToSubQuery(SubQueryCallback subQueryCallback) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSubQuery(ComparisonType.GREATER_EQUAL, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T lessThanSubQuery(SubQueryCallback subQueryCallback) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSubQuery(ComparisonType.LESS, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T lessThanAndEqualToSubQuery(SubQueryCallback subQueryCallback) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSubQuery(ComparisonType.LESS_EQUAL, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T likeSubQuery(SubQueryCallback subQueryCallback) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSubQuery(ComparisonType.LIKE, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T inSubQuery(SubQueryCallback subQueryCallback) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSubQuery(ComparisonType.IN, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T notInSubQuery(SubQueryCallback subQueryCallback) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSubQuery(ComparisonType.NOT_IN, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }
}