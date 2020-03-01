package pub.avalonframework.sqlhelper.core.expression.lambda;

import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface HavingLambdaExpression<TH extends HavingHelper<TH>, R> extends LambdaExpression {

    /**
     * Use lambda callable to add having sql data.
     *
     * @param havingLambdaCallable {@link HavingLambdaCallable}
     * @return R
     */
    R having(HavingLambdaCallable<TH> havingLambdaCallable);

    /**
     * Use lambda callable to add assign class having sql data.
     *
     * @param tableHelperClass   extends {@link TableHelper} class
     * @param tableAlias         table alias
     * @param havingJoinLambdaCallable {@link HavingJoinLambdaCallable}
     * @return R
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R having(Class<S> tableHelperClass, String tableAlias, HavingJoinLambdaCallable<TH, SH> havingJoinLambdaCallable);

    /**
     * Use lambda callable to add assign class having sql data.
     *
     * @param tableHelperClass   extends {@link TableHelper} class
     * @param havingJoinLambdaCallable {@link HavingJoinLambdaCallable}
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R having(Class<S> tableHelperClass, HavingJoinLambdaCallable<TH, SH> havingJoinLambdaCallable) {
        return having(tableHelperClass, null, havingJoinLambdaCallable);
    }
}