package pub.avalonframework.sqlhelper.core.expression.lambda;

import pub.avalonframework.sqlhelper.core.data.block.GroupType;
import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface ColumnLambdaExpression<TC extends ColumnHelper<TC>, R> extends LambdaExpression {

    /**
     * Use lambda callable to add group column sql data.
     *
     * @param groupType            {@link GroupType}
     * @param columnLambdaCallable {@link ColumnLambdaCallable}
     * @return R
     */
    R groupColumn(GroupType groupType, ColumnLambdaCallable<TC> columnLambdaCallable);

    /**
     * Use lambda callable to add assign class group column sql data.
     *
     * @param tableHelperClass     extends {@link TableHelper} class
     * @param tableAlias           table alias
     * @param groupType            {@link GroupType}
     * @param columnLambdaCallable {@link ColumnLambdaCallable}
     * @return R
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R groupColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnLambdaCallable<SC> columnLambdaCallable);

    /**
     * Use lambda callable to add assign class group column sql data.
     *
     * @param tableHelperClass     extends {@link TableHelper} class
     * @param groupType            {@link GroupType}
     * @param columnLambdaCallable {@link ColumnLambdaCallable}
     * @return R
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> R groupColumn(Class<S> tableHelperClass, GroupType groupType, ColumnLambdaCallable<SC> columnLambdaCallable) {
        return groupColumn(tableHelperClass, null, groupType, columnLambdaCallable);
    }

    /**
     * Use lambda callable to add sub query column sql data.
     *
     * @param columnAlias            column alias
     * @param subQueryColumnLambdaCallable {@link SubQueryColumnLambdaCallable}
     * @return R
     */
    R subQueryColumn(String columnAlias, SubQueryColumnLambdaCallable<TC> subQueryColumnLambdaCallable);
}