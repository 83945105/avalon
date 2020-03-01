package pub.avalonframework.sqlhelper.core.expression.lambda;

import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface OnLambdaExpression<TO extends OnHelper<TO>, R> extends LambdaExpression {

    /**
     * Use lambda callable to add where sql data.
     *
     * @param onLambdaCallable {@link OnLambdaCallable}
     * @return R
     */
    R on(OnLambdaCallable<TO> onLambdaCallable);

    /**
     * Use lambda callable to add assign class on sql data.
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param onJoinLambdaCallable   {@link OnJoinLambdaCallable}
     * @return R
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R on(Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable);

    /**
     * Use lambda callable to add assign class on sql data.
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param onJoinLambdaCallable   {@link OnJoinLambdaCallable}
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R on(Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return on(tableHelperClass, null, onJoinLambdaCallable);
    }
}