package pub.avalonframework.sqlhelper.core.expression.lambda.impl;

import pub.avalonframework.sqlhelper.core.data.ComparisonType;
import pub.avalonframework.sqlhelper.core.data.block.AbstractComparisonDataBlock;
import pub.avalonframework.sqlhelper.core.expression.lambda.SubQueryComparisonLambdaExpression;
import pub.avalonframework.sqlhelper.core.expression.lambda.SubQueryLambdaCallable;
import pub.avalonframework.sqlhelper.core.expression.lambda.execute.LambdaCallableExecutor;
import pub.avalonframework.sqlhelper.core.helper.Helper;

/**
 * @author baichao
 */
public interface SubQueryComparisonLambdaExpressionImpl<T, S extends AbstractComparisonDataBlock<S>> extends SubQueryComparisonLambdaExpression<T> {

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
    default T eqSq(SubQueryLambdaCallable subQueryLambdaCallable) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSubQuery(ComparisonType.EQUAL, LambdaCallableExecutor.execute(subQueryLambdaCallable)));
        return this.getHelper();
    }

    @Override
    default T neqSq(SubQueryLambdaCallable subQueryLambdaCallable) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSubQuery(ComparisonType.NOT_EQUAL, LambdaCallableExecutor.execute(subQueryLambdaCallable)));
        return this.getHelper();
    }

    @Override
    default T gtSq(SubQueryLambdaCallable subQueryLambdaCallable) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSubQuery(ComparisonType.GREATER, LambdaCallableExecutor.execute(subQueryLambdaCallable)));
        return this.getHelper();
    }

    @Override
    default T gteSq(SubQueryLambdaCallable subQueryLambdaCallable) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSubQuery(ComparisonType.GREATER_EQUAL, LambdaCallableExecutor.execute(subQueryLambdaCallable)));
        return this.getHelper();
    }

    @Override
    default T ltSq(SubQueryLambdaCallable subQueryLambdaCallable) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSubQuery(ComparisonType.LESS, LambdaCallableExecutor.execute(subQueryLambdaCallable)));
        return this.getHelper();
    }

    @Override
    default T lteSq(SubQueryLambdaCallable subQueryLambdaCallable) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSubQuery(ComparisonType.LESS_EQUAL, LambdaCallableExecutor.execute(subQueryLambdaCallable)));
        return this.getHelper();
    }

    @Override
    default T lkSq(SubQueryLambdaCallable subQueryLambdaCallable) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSubQuery(ComparisonType.LIKE, LambdaCallableExecutor.execute(subQueryLambdaCallable)));
        return this.getHelper();
    }

    @Override
    default T inSq(SubQueryLambdaCallable subQueryLambdaCallable) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSubQuery(ComparisonType.IN, LambdaCallableExecutor.execute(subQueryLambdaCallable)));
        return this.getHelper();
    }

    @Override
    default T ninSq(SubQueryLambdaCallable subQueryLambdaCallable) {
        this.addComparisonDataBlock(this.getComparisonDataBlock().setTargetSubQuery(ComparisonType.NOT_IN, LambdaCallableExecutor.execute(subQueryLambdaCallable)));
        return this.getHelper();
    }
}