package pub.avalonframework.sqlhelper.core.expression;

import pub.avalonframework.sqlhelper.core.data.ComparisonDataBlockLinker;
import pub.avalonframework.sqlhelper.core.expression.lambda.WhereAndLambdaCallable;
import pub.avalonframework.sqlhelper.core.expression.lambda.WhereJoinAndLambdaCallable;
import pub.avalonframework.sqlhelper.core.helper.*;

import java.util.List;

/**
 * @author baichao
 */
public interface WhereAndExpression<TW extends WhereHelper<TW>> {

    /**
     * Clean up after each takeout.
     *
     * @return list {@link ComparisonDataBlockLinker}
     */
    List<ComparisonDataBlockLinker> takeoutComparisonDataBlockLinkers();

    /**
     * And
     *
     * @param whereHelper {@link WhereHelper}
     * @return {@link WhereAndOrExpression}
     */
    WhereAndOrExpression<TW> and(WhereHelper<?> whereHelper);

    /**
     * And
     *
     * @param whereAndLambdaCallable {@link WhereAndLambdaCallable}
     * @return {@link WhereAndOrExpression}
     */
    WhereAndOrExpression<TW> and(WhereAndLambdaCallable<TW> whereAndLambdaCallable);

    /**
     * And
     *
     * @param tableHelperClass        target {@link TableHelper} class
     * @param tableAlias              target {@link TableHelper} alias
     * @param whereJoinAndLambdaCallable {@link WhereJoinAndLambdaCallable}
     * @return {@link WhereAndOrExpression}
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> WhereAndOrExpression<TW> and(Class<S> tableHelperClass, String tableAlias, WhereJoinAndLambdaCallable<TW, SW> whereJoinAndLambdaCallable);

    /**
     * And
     *
     * @param tableHelperClass        target {@link TableHelper} class
     * @param whereJoinAndLambdaCallable {@link WhereJoinAndLambdaCallable}
     * @return {@link WhereAndOrExpression}
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> WhereAndOrExpression<TW> and(Class<S> tableHelperClass, WhereJoinAndLambdaCallable<TW, SW> whereJoinAndLambdaCallable) {
        return and(tableHelperClass, null, whereJoinAndLambdaCallable);
    }
}