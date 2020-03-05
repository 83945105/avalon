package pub.avalonframework.sqlhelper.core.expression;

import pub.avalonframework.sqlhelper.core.data.block.ComparisonDataBlockLinker;
import pub.avalonframework.sqlhelper.core.data.block.OnDataBlock;
import pub.avalonframework.sqlhelper.core.expression.lambda.OnAndLambdaCallable;
import pub.avalonframework.sqlhelper.core.helper.OnHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class OnAndOrExpression<TO extends OnHelper<TO>> implements OnAndExpression<TO> {

    private List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = new ArrayList<>();

    @Override
    public List<ComparisonDataBlockLinker> takeoutComparisonDataBlockLinkers() {
        List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = this.comparisonDataBlockLinkers;
        this.comparisonDataBlockLinkers = new ArrayList<>();
        return comparisonDataBlockLinkers;
    }

    @Override
    public OnAndOrExpression<TO> and(OnHelper<?> onHelper) {
        if (onHelper == null) {
            return this;
        }
        ComparisonDataBlockLinker comparisonDataBlockLinker = new ComparisonDataBlockLinker(AndOr.AND);
        List<OnDataBlock> onDataBlocks = onHelper.takeoutOnDataBlocks();
        if (onDataBlocks == null || onDataBlocks.size() == 0) {
            return this;
        }
        comparisonDataBlockLinker.setComparisonDataBlocks(onDataBlocks);
        this.comparisonDataBlockLinkers.add(comparisonDataBlockLinker);
        return this;
    }

    @Override
    public OnAndOrExpression<TO> and(OnAndLambdaCallable<TO> onAndLambdaCallable) {
        if (onAndLambdaCallable == null) {
            return this;
        }
        OnAndExpression<TO> onAndExpression = onAndLambdaCallable.apply(new OnAndOrExpression<>());
        List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = onAndExpression.takeoutComparisonDataBlockLinkers();
        if (comparisonDataBlockLinkers == null || comparisonDataBlockLinkers.size() == 0) {
            return this;
        }
        ComparisonDataBlockLinker comparisonDataBlockLinker = new ComparisonDataBlockLinker(AndOr.AND);
        comparisonDataBlockLinker.setComparisonDataBlockLinkers(comparisonDataBlockLinkers);
        this.comparisonDataBlockLinkers.add(comparisonDataBlockLinker);
        return this;
    }

    /**
     * Or
     *
     * @param onHelper {@link OnHelper}
     * @return {@link OnAndOrExpression}
     */
    public OnAndOrExpression<TO> or(OnHelper<?> onHelper) {
        if (onHelper == null) {
            return this;
        }
        ComparisonDataBlockLinker comparisonDataBlockLinker = new ComparisonDataBlockLinker(AndOr.OR);
        List<OnDataBlock> onDataBlocks = onHelper.takeoutOnDataBlocks();
        if (onDataBlocks == null || onDataBlocks.size() == 0) {
            return this;
        }
        comparisonDataBlockLinker.setComparisonDataBlocks(onDataBlocks);
        this.comparisonDataBlockLinkers.add(comparisonDataBlockLinker);
        return this;
    }

    /**
     * Or
     *
     * @param onAndLambdaCallable {@link OnAndLambdaCallable}
     * @return {@link OnAndOrExpression}
     */
    public OnAndOrExpression<TO> or(OnAndLambdaCallable<TO> onAndLambdaCallable) {
        if (onAndLambdaCallable == null) {
            return this;
        }
        OnAndExpression<TO> onAndExpression = onAndLambdaCallable.apply(new OnAndOrExpression<>());
        List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = onAndExpression.takeoutComparisonDataBlockLinkers();
        if (comparisonDataBlockLinkers == null || comparisonDataBlockLinkers.size() == 0) {
            return this;
        }
        ComparisonDataBlockLinker comparisonDataBlockLinker = new ComparisonDataBlockLinker(AndOr.OR);
        comparisonDataBlockLinker.setComparisonDataBlockLinkers(comparisonDataBlockLinkers);
        this.comparisonDataBlockLinkers.add(comparisonDataBlockLinker);
        return this;
    }
}