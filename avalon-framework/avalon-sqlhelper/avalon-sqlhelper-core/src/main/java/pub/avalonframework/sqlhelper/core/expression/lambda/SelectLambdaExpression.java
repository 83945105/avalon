package pub.avalonframework.sqlhelper.core.expression.lambda;

import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface SelectLambdaExpression<TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>, R> extends
        ColumnLambdaExpression<TC, R>,
        JoinLambdaExpression<TO, R>,
        OnLambdaExpression<TO, R>,
        WhereLambdaExpression<TW, R>,
        GroupLambdaExpression<TG, R>,
        HavingLambdaExpression<TH, R>,
        SortLambdaExpression<TS, R> {

    /**
     * Use lambda callable to add select column sql data.
     *
     * @param columnLambdaCallable {@link ColumnLambdaCallable}
     * @return R
     */
    R select(ColumnLambdaCallable<TC> columnLambdaCallable);

    /**
     * Use lambda callable to add assign class select column sql data.
     *
     * @param tableHelperClass     extends {@link TableHelper} class
     * @param tableAlias           table alias
     * @param columnLambdaCallable {@link ColumnLambdaCallable}
     * @return R
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R select(Class<S> tableHelperClass, String tableAlias, ColumnLambdaCallable<SC> columnLambdaCallable);

    /**
     * Use lambda callable to add assign class select column sql data.
     *
     * @param tableHelperClass     extends {@link TableHelper} class
     * @param columnLambdaCallable {@link ColumnLambdaCallable}
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R select(Class<S> tableHelperClass, ColumnLambdaCallable<SC> columnLambdaCallable) {
        return select(tableHelperClass, null, columnLambdaCallable);
    }
}