package pub.avalonframework.sqlhelper.core.expression;

import pub.avalonframework.sqlhelper.core.callback.WhereJoinLinkerCallback;
import pub.avalonframework.sqlhelper.core.callback.WhereLinkerCallback;
import pub.avalonframework.sqlhelper.core.data.ComparisonDataBlockLinker;
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
     * @param whereLinkerCallback {@link WhereLinkerCallback}
     * @return {@link WhereAndOrExpression}
     */
    WhereAndOrExpression<TW> and(WhereLinkerCallback<TW> whereLinkerCallback);

    /**
     * And
     *
     * @param tableHelperClass        target {@link TableHelper} class
     * @param tableAlias              target {@link TableHelper} alias
     * @param whereJoinLinkerCallback {@link WhereJoinLinkerCallback}
     * @return {@link WhereAndOrExpression}
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> WhereAndOrExpression<TW> and(Class<S> tableHelperClass, String tableAlias, WhereJoinLinkerCallback<TW, SW> whereJoinLinkerCallback);

    /**
     * And
     *
     * @param tableHelperClass        target {@link TableHelper} class
     * @param whereJoinLinkerCallback {@link WhereJoinLinkerCallback}
     * @return {@link WhereAndOrExpression}
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> WhereAndOrExpression<TW> and(Class<S> tableHelperClass, WhereJoinLinkerCallback<TW, SW> whereJoinLinkerCallback) {
        return and(tableHelperClass, null, whereJoinLinkerCallback);
    }
}