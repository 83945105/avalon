package pub.avalonframework.sqlhelper.core.expression.lambda;

import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface WhereLambdaExpression<TW extends WhereHelper<TW>, R> extends LambdaExpression {

    /**
     * Use lambda callable to add where sql data.
     *
     * @param whereLambdaCallable {@link WhereLambdaCallable}
     * @return R
     */
    R where(WhereLambdaCallable<TW> whereLambdaCallable);

    /**
     * Use lambda callable to add assign class where sql data.
     *
     * @param tableHelperClass  extends {@link TableHelper} class
     * @param tableAlias        table alias
     * @param whereJoinLambdaCallable {@link WhereJoinLambdaCallable}
     * @return R
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R where(Class<S> tableHelperClass, String tableAlias, WhereJoinLambdaCallable<TW, SW> whereJoinLambdaCallable);

    /**
     * Use lambda callable to add assign class where sql data.
     *
     * @param tableHelperClass  extends {@link TableHelper} class
     * @param whereJoinLambdaCallable {@link WhereJoinLambdaCallable}
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R where(Class<S> tableHelperClass, WhereJoinLambdaCallable<TW, SW> whereJoinLambdaCallable) {
        return where(tableHelperClass, null, whereJoinLambdaCallable);
    }
}