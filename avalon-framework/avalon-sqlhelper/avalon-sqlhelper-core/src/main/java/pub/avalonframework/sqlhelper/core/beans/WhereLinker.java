package pub.avalonframework.sqlhelper.core.beans;

import pub.avalonframework.sqlhelper.core.callback.WhereJoinLinkerCallback;
import pub.avalonframework.sqlhelper.core.callback.WhereLinkerCallback;
import pub.avalonframework.sqlhelper.core.data.ComparisonDataBlockLinker;
import pub.avalonframework.sqlhelper.core.helper.*;

import java.util.List;

/**
 * @author baichao
 */
public interface WhereLinker<TW extends WhereHelper<TW>> {

    /**
     * Clean up after each takeout.
     *
     * @return list {@link ComparisonDataBlockLinker}
     */
    List<ComparisonDataBlockLinker> takeoutComparisonSqlPartDataLinkers();

    /**
     * and
     *
     * @param whereHelper {@link WhereHelper}
     * @return {@link WhereAndOr}
     */
    WhereAndOr<TW> and(WhereHelper<?> whereHelper);

    /**
     * and
     *
     * @param whereLinkerCallback {@link WhereLinkerCallback}
     * @return {@link WhereAndOr}
     */
    WhereAndOr<TW> and(WhereLinkerCallback<TW> whereLinkerCallback);

    /**
     * and
     *
     * @param tableHelperClass        target {@link TableHelper} class
     * @param tableAlias              target {@link TableHelper} alias
     * @param whereJoinLinkerCallback {@link WhereJoinLinkerCallback}
     * @return {@link WhereAndOr}
     */
    <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> WhereAndOr<TW> and(Class<S> tableHelperClass, String tableAlias, WhereJoinLinkerCallback<TW, SW> whereJoinLinkerCallback);

    /**
     * and
     *
     * @param tableHelperClass        target {@link TableHelper} class
     * @param whereJoinLinkerCallback {@link WhereJoinLinkerCallback}
     * @return {@link WhereAndOr}
     */
    default <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> WhereAndOr<TW> and(Class<S> tableHelperClass, WhereJoinLinkerCallback<TW, SW> whereJoinLinkerCallback) {
        return and(tableHelperClass, null, whereJoinLinkerCallback);
    }
}