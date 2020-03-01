package pub.avalonframework.sqlhelper.core.expression.lambda;

import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface GroupLambdaExpression<TG extends GroupHelper<TG>, R> extends LambdaExpression {

    /**
     * Use lambda callable to add group sql data.
     *
     * @param groupLambdaCallable {@link GroupLambdaCallable}
     * @return R
     */
    R groupBy(GroupLambdaCallable<TG> groupLambdaCallable);

    /**
     * Use lambda callable to add assign class group sql data.
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param tableAlias       table alias
     * @param groupLambdaCallable    {@link GroupLambdaCallable}
     * @return R
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R groupBy(Class<S> tableHelperClass, String tableAlias, GroupLambdaCallable<SG> groupLambdaCallable);

    /**
     * Use lambda callable to add assign class group sql data.
     *
     * @param tableHelperClass extends {@link TableHelper} class
     * @param groupLambdaCallable    {@link GroupLambdaCallable}
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R groupBy(Class<S> tableHelperClass, GroupLambdaCallable<SG> groupLambdaCallable) {
        return groupBy(tableHelperClass, null, groupLambdaCallable);
    }
}