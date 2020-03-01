package pub.avalonframework.sqlhelper.core.expression.lambda;

import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface SortLambdaExpression<TS extends SortHelper<TS>, R> extends LambdaExpression {

    /**
     * Use lambda callable to add sort sql data.
     *
     * @param sortLambdaCallable {@link SortLambdaCallable}
     * @return R
     */
    R orderBy(SortLambdaCallable<TS> sortLambdaCallable);

    /**
     * Use lambda callable to add assign class sort sql data.
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param sortLambdaCallable     {@link SortLambdaCallable}
     * @return R
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R orderBy(Class<S> tableHelperClass, String tableAlias, SortLambdaCallable<SS> sortLambdaCallable);

    /**
     * Use lambda callable to add assign class sort sql data.
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param sortLambdaCallable     {@link SortLambdaCallable}
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R orderBy(Class<S> tableHelperClass, SortLambdaCallable<SS> sortLambdaCallable) {
        return orderBy(tableHelperClass, null, sortLambdaCallable);
    }
}