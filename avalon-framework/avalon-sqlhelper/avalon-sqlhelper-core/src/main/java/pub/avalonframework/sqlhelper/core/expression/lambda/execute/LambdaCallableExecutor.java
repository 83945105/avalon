package pub.avalonframework.sqlhelper.core.expression.lambda.execute;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.ComparisonDataBlockLinker;
import pub.avalonframework.sqlhelper.core.data.block.*;
import pub.avalonframework.sqlhelper.core.expression.*;
import pub.avalonframework.sqlhelper.core.expression.lambda.*;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.FinalSqlBuilderResult;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SelectSqlBuilderResult;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;
import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;
import pub.avalonframework.sqlhelper.core.utils.HelperUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author baichao
 */
public final class LambdaCallableExecutor {

    private LambdaCallableExecutor() {
    }

    public static <TC extends ColumnHelper<TC>> TableColumnDataBlock execute(TC columnHelper, ColumnLambdaCallable<TC> columnLambdaCallable, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (columnHelper == null) {
            ExceptionUtils.columnHelperNullException();
        }
        if (columnLambdaCallable == null) {
            return null;
        }
        columnHelper.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        columnHelper = columnLambdaCallable.apply(columnHelper);
        List<ColumnDataBlock> columnDataBlocks = columnHelper.takeoutColumnDataBlocks();
        if (columnDataBlocks == null || columnDataBlocks.size() == 0) {
            columnDataBlocks = HelperUtils.defaultColumnData(columnHelper);
        }
        return new TableColumnDataBlock(columnHelper.getTableAlias(), columnDataBlocks);
    }

    public static <TG extends GroupHelper<TG>> TableGroupDataBlock execute(TG groupHelper, GroupLambdaCallable<TG> groupLambdaCallable, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (groupHelper == null) {
            ExceptionUtils.groupHelperNullException();
        }
        if (groupLambdaCallable == null) {
            return null;
        }
        groupHelper.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        groupHelper = groupLambdaCallable.apply(groupHelper);
        List<GroupDataBlock> groupDataBlocks = groupHelper.takeoutGroupDataBlocks();
        if (groupDataBlocks == null || groupDataBlocks.size() == 0) {
            return null;
        }
        return new TableGroupDataBlock(groupHelper.getTableAlias(), groupDataBlocks);
    }

    public static <TS extends SortHelper<TS>> TableSortDataBlock execute(TS sortHelper, SortLambdaCallable<TS> sortLambdaCallable, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (sortHelper == null) {
            ExceptionUtils.sortHelperNullException();
        }
        if (sortLambdaCallable == null) {
            return null;
        }
        sortHelper.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        sortHelper = sortLambdaCallable.apply(sortHelper);
        List<SortDataBlock> sortDataBlocks = sortHelper.takeoutSortDataBlocks();
        if (sortDataBlocks == null || sortDataBlocks.size() == 0) {
            return null;
        }
        return new TableSortDataBlock(sortHelper.getTableAlias(), sortDataBlocks);
    }

    public static <TO extends OnHelper<TO>> TableOnDataBlock execute(TO onHelper, OnLambdaCallable<TO> onLambdaCallable, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (onHelper == null) {
            ExceptionUtils.onHelperNullException();
        }
        if (onLambdaCallable == null) {
            return null;
        }
        onHelper.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        OnAndExpression<TO> onAndExpression = onLambdaCallable.apply(new OnAndOrExpression<>(), onHelper);
        List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = onAndExpression.takeoutComparisonDataBlockLinkers();
        if (comparisonDataBlockLinkers == null || comparisonDataBlockLinkers.size() == 0) {
            return null;
        }
        return new TableOnDataBlock(onHelper.getTableAlias(), comparisonDataBlockLinkers);
    }

    public static <TO extends OnHelper<TO>,
            S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> TableOnDataBlock execute(TO mainOnHelper, Class<S> joinTableHelperClass, String joinTableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (mainOnHelper == null) {
            ExceptionUtils.onHelperNullException();
        }
        if (onJoinLambdaCallable == null) {
            return null;
        }
        mainOnHelper.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        S s = HelperUtils.defaultTableHelper(joinTableHelperClass);
        joinTableAlias = joinTableAlias == null ? s.getTableAlias() : joinTableAlias;
        SO so = s.newOnHelper(joinTableAlias);
        so.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        OnAndExpression<TO> onAndExpression = onJoinLambdaCallable.apply(new OnAndOrExpression<>(), so, mainOnHelper);
        List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = onAndExpression.takeoutComparisonDataBlockLinkers();
        if (comparisonDataBlockLinkers == null || comparisonDataBlockLinkers.size() == 0) {
            return null;
        }
        return new TableOnDataBlock(joinTableAlias, comparisonDataBlockLinkers);
    }

    public static <TW extends WhereHelper<TW>> TableWhereDataBlock execute(TW whereHelper, WhereLambdaCallable<TW> whereLambdaCallable, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (whereHelper == null) {
            ExceptionUtils.whereHelperNullException();
        }
        if (whereLambdaCallable == null) {
            return null;
        }
        whereHelper.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        WhereAndExpression<TW> whereAndExpression = whereLambdaCallable.apply(new WhereAndOrExpression<>(), whereHelper);
        List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = whereAndExpression.takeoutComparisonDataBlockLinkers();
        if (comparisonDataBlockLinkers == null || comparisonDataBlockLinkers.size() == 0) {
            return null;
        }
        return new TableWhereDataBlock(whereHelper.getTableAlias(), comparisonDataBlockLinkers);
    }

    public static <TW extends WhereHelper<TW>,
            S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> TableWhereDataBlock execute(TW mainWhereHelper, Class<S> joinTableHelperClass, String joinTableAlias, WhereJoinLambdaCallable<TW, SW> whereJoinLambdaCallable, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (mainWhereHelper == null) {
            ExceptionUtils.whereHelperNullException();
        }
        if (whereJoinLambdaCallable == null) {
            return null;
        }
        mainWhereHelper.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        S s = HelperUtils.defaultTableHelper(joinTableHelperClass);
        joinTableAlias = joinTableAlias == null ? s.getTableAlias() : joinTableAlias;
        SW sw = s.newWhereHelper(joinTableAlias);
        sw.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        WhereAndExpression<TW> whereAndExpression = whereJoinLambdaCallable.apply(new WhereAndOrExpression<>(), sw, mainWhereHelper);
        List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = whereAndExpression.takeoutComparisonDataBlockLinkers();
        if (comparisonDataBlockLinkers == null || comparisonDataBlockLinkers.size() == 0) {
            return null;
        }
        return new TableWhereDataBlock(joinTableAlias, comparisonDataBlockLinkers);
    }

    public static <TH extends HavingHelper<TH>> TableHavingDataBlock execute(TH havingHelper, HavingLambdaCallable<TH> havingLambdaCallable, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (havingHelper == null) {
            ExceptionUtils.havingHelperNullException();
        }
        if (havingLambdaCallable == null) {
            return null;
        }
        havingHelper.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        HavingAndExpression<TH> havingAndExpression = havingLambdaCallable.apply(new HavingAndOrExpression<>(), havingHelper);
        List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = havingAndExpression.takeoutComparisonDataBlockLinker();
        if (comparisonDataBlockLinkers == null || comparisonDataBlockLinkers.size() == 0) {
            return null;
        }
        return new TableHavingDataBlock(havingHelper.getTableAlias(), comparisonDataBlockLinkers);
    }

    public static <TH extends HavingHelper<TH>,
            S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> TableHavingDataBlock execute(TH mainHavingHelper, Class<S> joinTableHelperClass, String joinTableAlias, HavingJoinLambdaCallable<TH, SH> havingJoinLambdaCallable, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (mainHavingHelper == null) {
            ExceptionUtils.havingHelperNullException();
        }
        if (havingJoinLambdaCallable == null) {
            return null;
        }
        mainHavingHelper.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        S s = HelperUtils.defaultTableHelper(joinTableHelperClass);
        joinTableAlias = joinTableAlias == null ? s.getTableAlias() : joinTableAlias;
        SH sh = s.newHavingHelper(joinTableAlias);
        sh.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        HavingAndExpression<TH> havingAndExpression = havingJoinLambdaCallable.apply(new HavingAndOrExpression<>(), sh, mainHavingHelper);
        List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = havingAndExpression.takeoutComparisonDataBlockLinker();
        if (comparisonDataBlockLinkers == null || comparisonDataBlockLinkers.size() == 0) {
            return null;
        }
        return new TableHavingDataBlock(joinTableAlias, comparisonDataBlockLinkers);
    }

    public static <T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
            TC extends ColumnHelper<TC>,
            TO extends OnHelper<TO>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableColumnDataBlock execute(Class<T> tableHelperClass, String tableAlias, ColumnLambdaCallable<TC> columnLambdaCallable, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperUtils.defaultTableHelper(tableHelperClass);
        TC tc = t.newColumnHelper(tableAlias == null ? t.getTableAlias() : tableAlias);
        return execute(tc, columnLambdaCallable, sqlBuilderConfiguration);
    }

    public static <T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
            TC extends ColumnHelper<TC>,
            TO extends OnHelper<TO>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableGroupDataBlock execute(Class<T> tableHelperClass, String tableAlias, GroupLambdaCallable<TG> groupLambdaCallable, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperUtils.defaultTableHelper(tableHelperClass);
        TG tg = t.newGroupHelper(tableAlias == null ? t.getTableAlias() : tableAlias);
        return execute(tg, groupLambdaCallable, sqlBuilderConfiguration);
    }

    public static <T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
            TC extends ColumnHelper<TC>,
            TO extends OnHelper<TO>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableSortDataBlock execute(Class<T> tableHelperClass, String tableAlias, SortLambdaCallable<TS> sortLambdaCallable, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperUtils.defaultTableHelper(tableHelperClass);
        TS ts = t.newSortHelper(tableAlias == null ? t.getTableAlias() : tableAlias);
        return execute(ts, sortLambdaCallable, sqlBuilderConfiguration);
    }

    public static <T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
            TC extends ColumnHelper<TC>,
            TO extends OnHelper<TO>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableOnDataBlock execute(Class<T> tableHelperClass, String tableAlias, OnLambdaCallable<TO> onLambdaCallable, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperUtils.defaultTableHelper(tableHelperClass);
        TO to = t.newOnHelper(tableAlias == null ? t.getTableAlias() : tableAlias);
        return execute(to, onLambdaCallable, sqlBuilderConfiguration);
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
            SS extends SortHelper<SS>> TableOnDataBlock execute(Class<T> mainTableHelperClass, String mainTableAlias, Class<S> joinTableHelperClass, String joinTableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (mainTableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperUtils.defaultTableHelper(mainTableHelperClass);
        TO to = t.newOnHelper(mainTableAlias == null ? t.getTableAlias() : mainTableAlias);
        return execute(to, joinTableHelperClass, joinTableAlias, onJoinLambdaCallable, sqlBuilderConfiguration);
    }

    public static <T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
            TC extends ColumnHelper<TC>,
            TO extends OnHelper<TO>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableWhereDataBlock execute(Class<T> tableHelperClass, String tableAlias, WhereLambdaCallable<TW> whereLambdaCallable, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperUtils.defaultTableHelper(tableHelperClass);
        TW tw = t.newWhereHelper(tableAlias == null ? t.getTableAlias() : tableAlias);
        return execute(tw, whereLambdaCallable, sqlBuilderConfiguration);
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
            SS extends SortHelper<SS>> TableWhereDataBlock execute(Class<T> mainTableHelperClass, String mainTableAlias, Class<S> joinTableHelperClass, String joinTableAlias, WhereJoinLambdaCallable<TW, SW> whereJoinLambdaCallable, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (mainTableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperUtils.defaultTableHelper(mainTableHelperClass);
        TW tw = t.newWhereHelper(mainTableAlias == null ? t.getTableAlias() : mainTableAlias);
        return execute(tw, joinTableHelperClass, joinTableAlias, whereJoinLambdaCallable, sqlBuilderConfiguration);
    }

    public static <T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
            TC extends ColumnHelper<TC>,
            TO extends OnHelper<TO>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableHavingDataBlock execute(Class<T> tableHelperClass, String tableAlias, HavingLambdaCallable<TH> havingLambdaCallable, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperUtils.defaultTableHelper(tableHelperClass);
        TH th = t.newHavingHelper(tableAlias == null ? t.getTableAlias() : tableAlias);
        return execute(th, havingLambdaCallable, sqlBuilderConfiguration);
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
            SS extends SortHelper<SS>> TableHavingDataBlock execute(Class<T> mainTableHelperClass, String mainTableAlias, Class<S> joinTableHelperClass, String joinTableAlias, HavingJoinLambdaCallable<TH, SH> havingJoinLambdaCallable, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (mainTableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        T t = HelperUtils.defaultTableHelper(mainTableHelperClass);
        TH th = t.newHavingHelper(mainTableAlias == null ? t.getTableAlias() : mainTableAlias);
        return execute(th, joinTableHelperClass, joinTableAlias, havingJoinLambdaCallable, sqlBuilderConfiguration);
    }

    public static SqlBuilderResult execute(SubQueryLambdaCallable subQueryLambdaCallable) {
        SelectSqlBuilderResult selectSqlBuilderResult = subQueryLambdaCallable.apply();
        return FinalSqlBuilderResult.newInstance(selectSqlBuilderResult.getPreparedStatementSql(), selectSqlBuilderResult.getPreparedStatementArgs());
    }

    public static <T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
            TC extends ColumnHelper<TC>,
            TO extends OnHelper<TO>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableColumnDataBlock executeGroupColumn(Class<T> tableHelperClass, String tableAlias, GroupType groupType, ColumnLambdaCallable<TC> columnLambdaCallable, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (groupType == null) {
            ExceptionUtils.groupTypeNullException();
        }
        if (columnLambdaCallable == null) {
            return null;
        }
        T t = HelperUtils.defaultTableHelper(tableHelperClass);
        TC tc = t.newColumnHelper(tableAlias);
        tc.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        tc = columnLambdaCallable.apply(tc);
        List<ColumnDataBlock> columnDataBlocks = tc.takeoutColumnDataBlocks();
        if (columnDataBlocks == null || columnDataBlocks.size() == 0) {
            return null;
        }
        columnDataBlocks.forEach(columnDataBlock -> columnDataBlock.setColumnHandlers(groupType));
        return new TableColumnDataBlock(tableAlias, columnDataBlocks);
    }

    public static TableColumnDataBlock executeVirtualColumn(String tableAlias, Object columnValue, String columnAlias) {
        return columnAlias == null ? null : new TableColumnDataBlock(tableAlias, Collections.singletonList(new ColumnDataBlock(null, null, columnValue, columnAlias)));
    }

    public static <T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
            TC extends ColumnHelper<TC>,
            TO extends OnHelper<TO>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableColumnDataBlock executeSubQueryColumn(Class<T> tableHelperClass, String tableAlias, String columnAlias, SubQueryColumnLambdaCallable<TC> subQueryColumnLambdaCallable, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (columnAlias == null) {
            return null;
        }
        T t = HelperUtils.defaultTableHelper(tableHelperClass);
        TC tc = t.newColumnHelper(tableAlias);
        tc.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        SqlBuilderResult sqlBuilderResult = subQueryColumnLambdaCallable.apply(tc);
        return new TableColumnDataBlock(tableAlias, Collections.singletonList(new ColumnDataBlock(null, null, sqlBuilderResult, columnAlias)));
    }

    public static <TO extends OnHelper<TO>,
            S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> TableJoinDataBlock execute(JoinType joinType, TO mainOnHelper, String joinTableName, Class<S> joinTableHelperClass, String joinTableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable, SqlBuilderConfiguration sqlBuilderConfiguration) {
        S s = HelperUtils.defaultTableHelper(joinTableHelperClass);
        joinTableName = joinTableName == null ? s.getTableName() : joinTableName;
        joinTableAlias = joinTableAlias == null ? s.getTableAlias() : joinTableAlias;
        TableJoinDataBlock tableJoinDataBlock = new TableJoinDataBlock(joinType, joinTableHelperClass, joinTableName, joinTableAlias);
        TableOnDataBlock tableOnDataBlock = LambdaCallableExecutor.execute(mainOnHelper, joinTableHelperClass, joinTableAlias, onJoinLambdaCallable, sqlBuilderConfiguration);
        tableJoinDataBlock.setTableOnDataBlock(tableOnDataBlock);
        return tableJoinDataBlock;
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
            SS extends SortHelper<SS>> TableJoinDataBlock execute(JoinType joinType, Class<T> mainTableHelperClass, String mainTableAlias, String joinTableName, Class<S> joinTableHelperClass, String joinTableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable, SqlBuilderConfiguration sqlBuilderConfiguration) {
        S s = HelperUtils.defaultTableHelper(joinTableHelperClass);
        joinTableName = joinTableName == null ? s.getTableName() : joinTableName;
        joinTableAlias = joinTableAlias == null ? s.getTableAlias() : joinTableAlias;
        TableJoinDataBlock tableJoinDataBlock = new TableJoinDataBlock(joinType, joinTableHelperClass, joinTableName, joinTableAlias);
        TableOnDataBlock tableOnDataBlock = LambdaCallableExecutor.execute(mainTableHelperClass, mainTableAlias, joinTableHelperClass, joinTableAlias, onJoinLambdaCallable, sqlBuilderConfiguration);
        tableJoinDataBlock.setTableOnDataBlock(tableOnDataBlock);
        return tableJoinDataBlock;
    }
}