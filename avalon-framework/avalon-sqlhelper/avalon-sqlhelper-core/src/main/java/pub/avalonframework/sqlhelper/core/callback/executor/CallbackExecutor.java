package pub.avalonframework.sqlhelper.core.callback.executor;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.beans.*;
import pub.avalonframework.sqlhelper.core.callback.*;
import pub.avalonframework.sqlhelper.core.data.ComparisonDataBlockLinker;
import pub.avalonframework.sqlhelper.core.data.block.*;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.FinalSqlBuilderResult;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SelectSqlBuilderResult;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;
import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.List;

/**
 * @author baichao
 */
public final class CallbackExecutor {

    private CallbackExecutor() {
    }

    public static <TC extends ColumnHelper<TC>> TableColumnDataBlock execute(TC columnHelper, ColumnCallback<TC> columnCallback, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (columnHelper == null) {
            ExceptionUtils.columnHelperNullException();
        }
        if (columnCallback == null) {
            return null;
        }
        columnHelper.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        columnHelper = columnCallback.apply(columnHelper);
        List<ColumnDataBlock> columnDataBlocks = columnHelper.takeoutSqlPartData();
        if (columnDataBlocks == null || columnDataBlocks.size() == 0) {
            columnDataBlocks = HelperManager.defaultColumnData(columnHelper);
        }
        return new TableColumnDataBlock(columnHelper.getTableAlias(), columnDataBlocks);
    }

    public static <TG extends GroupHelper<TG>> TableGroupDataBlock execute(TG groupHelper, GroupCallback<TG> groupCallback, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (groupHelper == null) {
            ExceptionUtils.groupHelperNullException();
        }
        if (groupCallback == null) {
            return null;
        }
        groupHelper.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        groupHelper = groupCallback.apply(groupHelper);
        List<GroupDataBlock> groupDataBlocks = groupHelper.takeoutSqlPartData();
        if (groupDataBlocks == null || groupDataBlocks.size() == 0) {
            return null;
        }
        return new TableGroupDataBlock(groupHelper.getTableAlias(), groupDataBlocks);
    }

    public static <TS extends SortHelper<TS>> TableSortDataBlock execute(TS sortHelper, SortCallback<TS> sortCallback, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (sortHelper == null) {
            ExceptionUtils.sortHelperNullException();
        }
        if (sortCallback == null) {
            return null;
        }
        sortHelper.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        sortHelper = sortCallback.apply(sortHelper);
        List<SortDataBlock> sortDataBlocks = sortHelper.takeoutSqlPartData();
        if (sortDataBlocks == null || sortDataBlocks.size() == 0) {
            return null;
        }
        return new TableSortDataBlock(sortHelper.getTableAlias(), sortDataBlocks);
    }

    public static <TO extends OnHelper<TO>> TableOnDataBlock execute(TO onHelper, OnCallback<TO> onCallback, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (onHelper == null) {
            ExceptionUtils.onHelperNullException();
        }
        if (onCallback == null) {
            return null;
        }
        onHelper.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        OnLinker<TO> onLinker = onCallback.apply(new OnAndOr<>(), onHelper);
        List<ComparisonDataBlockLinker> onDataLinkers = onLinker.takeoutComparisonSqlPartDataLinkers();
        if (onDataLinkers == null || onDataLinkers.size() == 0) {
            return null;
        }
        return new TableOnDataBlock(onHelper.getTableAlias(), onDataLinkers);
    }

    public static <TO extends OnHelper<TO>,
            S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> TableOnDataBlock execute(TO mainOnHelper, Class<S> joinTableHelperClass, String joinTableAlias, OnJoinCallback<TO, SO> onJoinCallback, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (mainOnHelper == null) {
            ExceptionUtils.onHelperNullException();
        }
        if (onJoinCallback == null) {
            return null;
        }
        mainOnHelper.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        S s = HelperManager.defaultTableHelper(joinTableHelperClass);
        joinTableAlias = joinTableAlias == null ? s.getTableAlias() : joinTableAlias;
        SO so = s.newOnHelper(joinTableAlias);
        so.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        OnLinker<TO> onLinker = onJoinCallback.apply(new OnAndOr<>(), so, mainOnHelper);
        List<ComparisonDataBlockLinker> onDataLinkers = onLinker.takeoutComparisonSqlPartDataLinkers();
        if (onDataLinkers == null || onDataLinkers.size() == 0) {
            return null;
        }
        return new TableOnDataBlock(joinTableAlias, onDataLinkers);
    }

    public static <TW extends WhereHelper<TW>> TableWhereDataBlock execute(TW whereHelper, WhereCallback<TW> whereCallback, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (whereHelper == null) {
            ExceptionUtils.whereHelperNullException();
        }
        if (whereCallback == null) {
            return null;
        }
        whereHelper.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        WhereLinker<TW> whereLinker = whereCallback.apply(new WhereAndOr<>(), whereHelper);
        List<ComparisonDataBlockLinker> whereDataLinkers = whereLinker.takeoutComparisonSqlPartDataLinkers();
        if (whereDataLinkers == null || whereDataLinkers.size() == 0) {
            return null;
        }
        return new TableWhereDataBlock(whereHelper.getTableAlias(), whereDataLinkers);
    }

    public static <TW extends WhereHelper<TW>,
            S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> TableWhereDataBlock execute(TW mainWhereHelper, Class<S> joinTableHelperClass, String joinTableAlias, WhereJoinCallback<TW, SW> whereJoinCallback, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (mainWhereHelper == null) {
            ExceptionUtils.whereHelperNullException();
        }
        if (whereJoinCallback == null) {
            return null;
        }
        mainWhereHelper.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        S s = HelperManager.defaultTableHelper(joinTableHelperClass);
        joinTableAlias = joinTableAlias == null ? s.getTableAlias() : joinTableAlias;
        SW sw = s.newWhereHelper(joinTableAlias);
        sw.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        WhereLinker<TW> whereLinker = whereJoinCallback.apply(new WhereAndOr<>(), sw, mainWhereHelper);
        List<ComparisonDataBlockLinker> whereDataLinkers = whereLinker.takeoutComparisonSqlPartDataLinkers();
        if (whereDataLinkers == null || whereDataLinkers.size() == 0) {
            return null;
        }
        return new TableWhereDataBlock(joinTableAlias, whereDataLinkers);
    }

    public static <TH extends HavingHelper<TH>> TableHavingDataBlock execute(TH havingHelper, HavingCallback<TH> havingCallback, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (havingHelper == null) {
            ExceptionUtils.havingHelperNullException();
        }
        if (havingCallback == null) {
            return null;
        }
        havingHelper.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        HavingLinker<TH> havingLinker = havingCallback.apply(new HavingAndOr<>(), havingHelper);
        List<ComparisonDataBlockLinker> havingDataLinkers = havingLinker.takeoutComparisonSqlPartDataLinkers();
        if (havingDataLinkers == null || havingDataLinkers.size() == 0) {
            return null;
        }
        return new TableHavingDataBlock(havingHelper.getTableAlias(), havingDataLinkers);
    }

    public static <TH extends HavingHelper<TH>,
            S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> TableHavingDataBlock execute(TH mainHavingHelper, Class<S> joinTableHelperClass, String joinTableAlias, HavingJoinCallback<TH, SH> havingJoinCallback, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (mainHavingHelper == null) {
            ExceptionUtils.havingHelperNullException();
        }
        if (havingJoinCallback == null) {
            return null;
        }
        mainHavingHelper.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        S s = HelperManager.defaultTableHelper(joinTableHelperClass);
        joinTableAlias = joinTableAlias == null ? s.getTableAlias() : joinTableAlias;
        SH sh = s.newHavingHelper(joinTableAlias);
        sh.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        HavingLinker<TH> havingLinker = havingJoinCallback.apply(new HavingAndOr<>(), sh, mainHavingHelper);
        List<ComparisonDataBlockLinker> havingDataLinkers = havingLinker.takeoutComparisonSqlPartDataLinkers();
        if (havingDataLinkers == null || havingDataLinkers.size() == 0) {
            return null;
        }
        return new TableHavingDataBlock(joinTableAlias, havingDataLinkers);
    }

    public static <T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
            TC extends ColumnHelper<TC>,
            TO extends OnHelper<TO>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableColumnDataBlock execute(Class<T> tableHelperClass, String tableAlias, ColumnCallback<TC> columnCallback, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperManager.defaultTableHelper(tableHelperClass);
        TC tc = t.newColumnHelper(tableAlias == null ? t.getTableAlias() : tableAlias);
        return execute(tc, columnCallback, sqlBuilderConfiguration);
    }

    public static <T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
            TC extends ColumnHelper<TC>,
            TO extends OnHelper<TO>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableGroupDataBlock execute(Class<T> tableHelperClass, String tableAlias, GroupCallback<TG> groupCallback, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperManager.defaultTableHelper(tableHelperClass);
        TG tg = t.newGroupHelper(tableAlias == null ? t.getTableAlias() : tableAlias);
        return execute(tg, groupCallback, sqlBuilderConfiguration);
    }

    public static <T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
            TC extends ColumnHelper<TC>,
            TO extends OnHelper<TO>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableSortDataBlock execute(Class<T> tableHelperClass, String tableAlias, SortCallback<TS> sortCallback, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperManager.defaultTableHelper(tableHelperClass);
        TS ts = t.newSortHelper(tableAlias == null ? t.getTableAlias() : tableAlias);
        return execute(ts, sortCallback, sqlBuilderConfiguration);
    }

    public static <T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
            TC extends ColumnHelper<TC>,
            TO extends OnHelper<TO>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableOnDataBlock execute(Class<T> tableHelperClass, String tableAlias, OnCallback<TO> onCallback, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperManager.defaultTableHelper(tableHelperClass);
        TO to = t.newOnHelper(tableAlias == null ? t.getTableAlias() : tableAlias);
        return execute(to, onCallback, sqlBuilderConfiguration);
    }

    public static <T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
            TC extends ColumnHelper<TC>,
            TO extends OnHelper<TO>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>,
            S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> TableOnDataBlock execute(Class<T> mainTableHelperClass, String mainTableAlias, Class<S> joinTableHelperClass, String joinTableAlias, OnJoinCallback<TO, SO> onJoinCallback, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (mainTableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperManager.newTableHelper(mainTableHelperClass);
        TO to = t.newOnHelper(mainTableAlias == null ? t.getTableAlias() : mainTableAlias);
        return execute(to, joinTableHelperClass, joinTableAlias, onJoinCallback, sqlBuilderConfiguration);
    }

    public static <T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
            TC extends ColumnHelper<TC>,
            TO extends OnHelper<TO>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableWhereDataBlock execute(Class<T> tableHelperClass, String tableAlias, WhereCallback<TW> whereCallback, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperManager.defaultTableHelper(tableHelperClass);
        TW tw = t.newWhereHelper(tableAlias == null ? t.getTableAlias() : tableAlias);
        return execute(tw, whereCallback, sqlBuilderConfiguration);
    }

    public static <T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
            TC extends ColumnHelper<TC>,
            TO extends OnHelper<TO>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>,
            S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> TableWhereDataBlock execute(Class<T> mainTableHelperClass, String mainTableAlias, Class<S> joinTableHelperClass, String joinTableAlias, WhereJoinCallback<TW, SW> whereJoinCallback, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (mainTableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperManager.defaultTableHelper(mainTableHelperClass);
        TW tw = t.newWhereHelper(mainTableAlias == null ? t.getTableAlias() : mainTableAlias);
        return execute(tw, joinTableHelperClass, joinTableAlias, whereJoinCallback, sqlBuilderConfiguration);
    }

    public static <T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
            TC extends ColumnHelper<TC>,
            TO extends OnHelper<TO>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableHavingDataBlock execute(Class<T> tableHelperClass, String tableAlias, HavingCallback<TH> havingCallback, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperManager.defaultTableHelper(tableHelperClass);
        TH th = t.newHavingHelper(tableAlias == null ? t.getTableAlias() : tableAlias);
        return execute(th, havingCallback, sqlBuilderConfiguration);
    }

    public static <T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
            TC extends ColumnHelper<TC>,
            TO extends OnHelper<TO>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>,
            S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> TableHavingDataBlock execute(Class<T> mainTableHelperClass, String mainTableAlias, Class<S> joinTableHelperClass, String joinTableAlias, HavingJoinCallback<TH, SH> havingJoinCallback, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (mainTableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperManager.defaultTableHelper(mainTableHelperClass);
        TH th = t.newHavingHelper(mainTableAlias == null ? t.getTableAlias() : mainTableAlias);
        return execute(th, joinTableHelperClass, joinTableAlias, havingJoinCallback, sqlBuilderConfiguration);
    }

    public static SqlBuilderResult execute(SubQueryCallback subQueryCallback) {
        SelectSqlBuilderResult selectSqlBuilderResult = subQueryCallback.apply();
        return FinalSqlBuilderResult.newInstance(selectSqlBuilderResult.getPreparedStatementSql(), selectSqlBuilderResult.getPreparedStatementArgs());
    }
}