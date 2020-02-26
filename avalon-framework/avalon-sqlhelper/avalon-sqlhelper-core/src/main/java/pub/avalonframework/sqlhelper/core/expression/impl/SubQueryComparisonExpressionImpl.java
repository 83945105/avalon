package pub.avalonframework.sqlhelper.core.expression.impl;

import pub.avalonframework.sqlhelper.core.callback.SubQueryCallback;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.ComparisonType;
import pub.avalonframework.sqlhelper.core.data.block.AbstractComparisonDataBlock;
import pub.avalonframework.sqlhelper.core.expression.SubQueryComparisonExpression;
import pub.avalonframework.sqlhelper.core.helper.Helper;

/**
 * @author baichao
 */
public interface SubQueryComparisonExpressionImpl<T, S extends AbstractComparisonDataBlock<S>> extends SubQueryComparisonExpression<T> {

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
    default T eqSq(SubQueryCallback subQueryCallback) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSubQuery(ComparisonType.EQUAL, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T neqSq(SubQueryCallback subQueryCallback) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSubQuery(ComparisonType.NOT_EQUAL, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T gtSq(SubQueryCallback subQueryCallback) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSubQuery(ComparisonType.GREATER, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T gteSq(SubQueryCallback subQueryCallback) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSubQuery(ComparisonType.GREATER_EQUAL, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T ltSq(SubQueryCallback subQueryCallback) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSubQuery(ComparisonType.LESS, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T lteSq(SubQueryCallback subQueryCallback) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSubQuery(ComparisonType.LESS_EQUAL, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T lkSq(SubQueryCallback subQueryCallback) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSubQuery(ComparisonType.LIKE, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T inSq(SubQueryCallback subQueryCallback) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSubQuery(ComparisonType.IN, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }

    @Override
    default T ninSq(SubQueryCallback subQueryCallback) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSubQuery(ComparisonType.NOT_IN, CallbackExecutor.execute(subQueryCallback)));
        return this.getHelper();
    }
}