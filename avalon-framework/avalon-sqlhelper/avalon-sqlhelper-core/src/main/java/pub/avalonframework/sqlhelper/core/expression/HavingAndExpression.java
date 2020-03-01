package pub.avalonframework.sqlhelper.core.expression;

import pub.avalonframework.sqlhelper.core.data.ComparisonDataBlockLinker;
import pub.avalonframework.sqlhelper.core.expression.lambda.HavingAndLambdaCallable;
import pub.avalonframework.sqlhelper.core.helper.HavingHelper;

import java.util.List;

/**
 * @author baichao
 */
public interface HavingAndExpression<TH extends HavingHelper<TH>> {

    /**
     * Clean up after each takeout.
     *
     * @return list {@link ComparisonDataBlockLinker}
     */
    List<ComparisonDataBlockLinker> takeoutComparisonDataBlockLinker();

    /**
     * And
     *
     * @param havingHelper {@link HavingHelper}
     * @return {@link HavingAndOrExpression}
     */
    HavingAndOrExpression<TH> and(HavingHelper<?> havingHelper);

    /**
     * And
     *
     * @param havingAndLambdaCallable {@link HavingAndLambdaCallable}
     * @return {@link HavingAndOrExpression}
     */
    HavingAndOrExpression<TH> and(HavingAndLambdaCallable<TH> havingAndLambdaCallable);
}