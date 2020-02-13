package pub.avalonframework.sqlhelper.core.block.callback.executor;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.beans.GroupType;
import pub.avalonframework.sqlhelper.core.beans.JoinType;
import pub.avalonframework.sqlhelper.core.callback.ColumnCallback;
import pub.avalonframework.sqlhelper.core.callback.OnJoinCallback;
import pub.avalonframework.sqlhelper.core.callback.SubQueryColumnCallback;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.block.ColumnDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.TableColumnDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.TableJoinDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.TableOnDataBlock;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SqlBuilderResult;
import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.Collections;
import java.util.List;

/**
 * @author baichao
 */
public final class CallbackBlockExecutor {

    private CallbackBlockExecutor() {
    }

    public static <T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
            TC extends ColumnHelper<TC>,
            TO extends OnHelper<TO>,
            TW extends WhereHelper<TW>,
            TG extends GroupHelper<TG>,
            TH extends HavingHelper<TH>,
            TS extends SortHelper<TS>> TableColumnDataBlock executeGroupColumn(Class<T> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<TC> columnCallback, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (groupType == null) {
            ExceptionUtils.groupTypeNullException();
        }
        if (columnCallback == null) {
            return null;
        }
        T t = HelperManager.defaultTableHelper(tableHelperClass);
        TC tc = t.newColumnHelper(tableAlias);
        tc.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        tc = columnCallback.apply(tc);
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
            TS extends SortHelper<TS>> TableColumnDataBlock executeSubQueryColumn(Class<T> tableHelperClass, String tableAlias, String columnAlias, SubQueryColumnCallback<TC> subQueryColumnCallback, SqlBuilderConfiguration sqlBuilderConfiguration) {
        if (columnAlias == null) {
            return null;
        }
        T t = HelperManager.defaultTableHelper(tableHelperClass);
        TC tc = t.newColumnHelper(tableAlias);
        tc.setSqlBuilderConfiguration(sqlBuilderConfiguration);
        SqlBuilderResult sqlBuilderResult = subQueryColumnCallback.apply(tc);
        return new TableColumnDataBlock(tableAlias, Collections.singletonList(new ColumnDataBlock(null, null, sqlBuilderResult, columnAlias)));
    }

    public static <TO extends OnHelper<TO>,
            S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> TableJoinDataBlock execute(JoinType joinType, TO mainOnHelper, String joinTableName, Class<S> joinTableHelperClass, String joinTableAlias, OnJoinCallback<TO, SO> onJoinCallback, SqlBuilderConfiguration sqlBuilderConfiguration) {
        S s = HelperManager.defaultTableHelper(joinTableHelperClass);
        joinTableName = joinTableName == null ? s.getTableName() : joinTableName;
        joinTableAlias = joinTableAlias == null ? s.getTableAlias() : joinTableAlias;
        TableJoinDataBlock tableJoinDataBlock = new TableJoinDataBlock(joinType, joinTableHelperClass, joinTableName, joinTableAlias);
        TableOnDataBlock tableOnDataBlock = CallbackExecutor.execute(mainOnHelper, joinTableHelperClass, joinTableAlias, onJoinCallback, sqlBuilderConfiguration);
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
            SS extends SortHelper<SS>> TableJoinDataBlock execute(JoinType joinType, Class<T> mainTableHelperClass, String mainTableAlias, String joinTableName, Class<S> joinTableHelperClass, String joinTableAlias, OnJoinCallback<TO, SO> onJoinCallback, SqlBuilderConfiguration sqlBuilderConfiguration) {
        S s = HelperManager.defaultTableHelper(joinTableHelperClass);
        joinTableName = joinTableName == null ? s.getTableName() : joinTableName;
        joinTableAlias = joinTableAlias == null ? s.getTableAlias() : joinTableAlias;
        TableJoinDataBlock tableJoinDataBlock = new TableJoinDataBlock(joinType, joinTableHelperClass, joinTableName, joinTableAlias);
        TableOnDataBlock tableOnDataBlock = CallbackExecutor.execute(mainTableHelperClass, mainTableAlias, joinTableHelperClass, joinTableAlias, onJoinCallback, sqlBuilderConfiguration);
        tableJoinDataBlock.setTableOnDataBlock(tableOnDataBlock);
        return tableJoinDataBlock;
    }
}