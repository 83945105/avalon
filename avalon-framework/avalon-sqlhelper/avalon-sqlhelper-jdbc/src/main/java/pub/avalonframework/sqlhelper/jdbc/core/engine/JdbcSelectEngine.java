package pub.avalonframework.sqlhelper.jdbc.core.engine;

import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.builder.*;
import pub.avalonframework.sqlhelper.core.data.block.*;
import pub.avalonframework.sqlhelper.core.engine.AbstractSelectEngine;
import pub.avalonframework.sqlhelper.core.expression.lambda.*;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.jdbc.core.JdbcHelper;

import java.util.List;
import java.util.Map;

/**
 * @author baichao
 */
public final class JdbcSelectEngine<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
        TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends AbstractSelectEngine<T, TC, TO, TW, TG, TH, TS> {

    private JdbcHelper jdbcHelper;

    public JdbcSelectEngine(JdbcHelper jdbcHelper, Class<T> tableHelperClass) {
        super(jdbcHelper.getDatabaseType(), tableHelperClass);
    }

    public JdbcSelectEngine(JdbcHelper jdbcHelper, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        super(jdbcHelper.getDatabaseType(), tableHelperClass, configuration);
    }

    public JdbcSelectEngine(JdbcHelper jdbcHelper, String tableName, Class<T> tableHelperClass) {
        super(jdbcHelper.getDatabaseType(), tableName, tableHelperClass);
    }

    public JdbcSelectEngine(JdbcHelper jdbcHelper, String tableName, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        super(jdbcHelper.getDatabaseType(), tableName, tableHelperClass, configuration);
    }

    public JdbcSelectEngine(JdbcHelper jdbcHelper, Class<T> tableHelperClass, String tableAlias) {
        super(jdbcHelper.getDatabaseType(), tableHelperClass, tableAlias);
    }

    public JdbcSelectEngine(JdbcHelper jdbcHelper, String tableName, Class<T> tableHelperClass, String tableAlias) {
        super(jdbcHelper.getDatabaseType(), tableName, tableHelperClass, tableAlias);
    }

    public JdbcSelectEngine(JdbcHelper jdbcHelper, String tableName, Class<T> tableHelperClass, String tableAlias, SqlhelperConfiguration configuration) {
        super(jdbcHelper.getDatabaseType(), tableName, tableHelperClass, tableAlias, configuration);
    }

    public JdbcHelper getJdbcHelper() {
        return jdbcHelper;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> addSelectTableColumnDataBlock(TableColumnDataBlock tableColumnDataBlock) {
        super.addSelectTableColumnDataBlock(tableColumnDataBlock);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> setConfiguration(SqlhelperConfiguration configuration) {
        super.setConfiguration(configuration);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> addTableGroupDataBlock(TableGroupDataBlock tableGroupDataBlock) {
        super.addTableGroupDataBlock(tableGroupDataBlock);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> addTableHavingDataBlock(TableHavingDataBlock tableHavingDataBlock) {
        super.addTableHavingDataBlock(tableHavingDataBlock);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> addTableJoinDataBlock(TableJoinDataBlock tableJoinDataBlock) {
        super.addTableJoinDataBlock(tableJoinDataBlock);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> setLimit(Long limit) {
        super.setLimit(limit);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> setOffset(Long offset) {
        super.setOffset(offset);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> addTableOnDataBlock(TableOnDataBlock tableOnDataBlock) {
        super.addTableOnDataBlock(tableOnDataBlock);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> addTableSortDataBlock(TableSortDataBlock tableSortDataBlock) {
        super.addTableSortDataBlock(tableSortDataBlock);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> addTableWhereDataBlock(TableWhereDataBlock tableWhereDataBlock) {
        super.addTableWhereDataBlock(tableWhereDataBlock);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> virtualColumn(Object columnValue, String columnAlias) {
        super.virtualColumn(columnValue, columnAlias);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> groupBy(GroupHelper<?>... groupHelpers) {
        super.groupBy(groupHelpers);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> having(HavingHelper<?>... havingHelpers) {
        super.having(havingHelpers);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> select(ColumnHelper<?>... columnHelpers) {
        super.select(columnHelpers);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> on(OnHelper<?>... onHelpers) {
        super.on(onHelpers);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> orderBy(SortHelper<?>... sortHelpers) {
        super.orderBy(sortHelpers);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> where(WhereHelper<?>... whereHelpers) {
        super.where(whereHelpers);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> select(ColumnLambdaCallable<TC> columnLambdaCallable) {
        super.select(columnLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> select(Class<S> tableHelperClass, String tableAlias, ColumnLambdaCallable<SC> columnLambdaCallable) {
        super.select(tableHelperClass, tableAlias, columnLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> select(Class<S> tableHelperClass, ColumnLambdaCallable<SC> columnLambdaCallable) {
        super.select(tableHelperClass, columnLambdaCallable);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> groupColumn(GroupType groupType, ColumnLambdaCallable<TC> columnLambdaCallable) {
        super.groupColumn(groupType, columnLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> groupColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnLambdaCallable<SC> columnLambdaCallable) {
        super.groupColumn(tableHelperClass, tableAlias, groupType, columnLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> groupColumn(Class<S> tableHelperClass, GroupType groupType, ColumnLambdaCallable<SC> columnLambdaCallable) {
        super.groupColumn(tableHelperClass, groupType, columnLambdaCallable);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> subQueryColumn(String columnAlias, SubQueryColumnLambdaCallable<TC> subQueryColumnLambdaCallable) {
        super.subQueryColumn(columnAlias, subQueryColumnLambdaCallable);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> groupBy(GroupLambdaCallable<TG> groupLambdaCallable) {
        super.groupBy(groupLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> groupBy(Class<S> tableHelperClass, String tableAlias, GroupLambdaCallable<SG> groupLambdaCallable) {
        super.groupBy(tableHelperClass, tableAlias, groupLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> groupBy(Class<S> tableHelperClass, GroupLambdaCallable<SG> groupLambdaCallable) {
        super.groupBy(tableHelperClass, groupLambdaCallable);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> having(HavingLambdaCallable<TH> havingLambdaCallable) {
        super.having(havingLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> having(Class<S> tableHelperClass, String tableAlias, HavingJoinLambdaCallable<TH, SH> havingJoinLambdaCallable) {
        super.having(tableHelperClass, tableAlias, havingJoinLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> having(Class<S> tableHelperClass, HavingJoinLambdaCallable<TH, SH> havingJoinLambdaCallable) {
        super.having(tableHelperClass, havingJoinLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        super.join(joinType, tableName, tableHelperClass, tableAlias, onJoinLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias) {
        super.join(joinType, tableName, tableHelperClass, tableAlias);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        super.join(joinType, tableName, tableHelperClass, onJoinLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass) {
        super.join(joinType, tableName, tableHelperClass);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        super.join(joinType, tableHelperClass, tableAlias, onJoinLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, Class<S> tableHelperClass, String tableAlias) {
        super.join(joinType, tableHelperClass, tableAlias);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        super.join(joinType, tableHelperClass, onJoinLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, Class<S> tableHelperClass) {
        super.join(joinType, tableHelperClass);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        super.innerJoin(tableName, tableHelperClass, tableAlias, onJoinLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(String tableName, Class<S> tableHelperClass, String tableAlias) {
        super.innerJoin(tableName, tableHelperClass, tableAlias);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(String tableName, Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        super.innerJoin(tableName, tableHelperClass, onJoinLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(String tableName, Class<S> tableHelperClass) {
        super.innerJoin(tableName, tableHelperClass);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        super.innerJoin(tableHelperClass, tableAlias, onJoinLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(Class<S> tableHelperClass, String tableAlias) {
        super.innerJoin(tableHelperClass, tableAlias);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        super.innerJoin(tableHelperClass, onJoinLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(Class<S> tableHelperClass) {
        super.innerJoin(tableHelperClass);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        super.leftJoin(tableName, tableHelperClass, tableAlias, onJoinLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(String tableName, Class<S> tableHelperClass, String tableAlias) {
        super.leftJoin(tableName, tableHelperClass, tableAlias);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(String tableName, Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        super.leftJoin(tableName, tableHelperClass, onJoinLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(String tableName, Class<S> tableHelperClass) {
        super.leftJoin(tableName, tableHelperClass);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        super.leftJoin(tableHelperClass, tableAlias, onJoinLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(Class<S> tableHelperClass, String tableAlias) {
        super.leftJoin(tableHelperClass, tableAlias);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        super.leftJoin(tableHelperClass, onJoinLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(Class<S> tableHelperClass) {
        super.leftJoin(tableHelperClass);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        super.rightJoin(tableName, tableHelperClass, tableAlias, onJoinLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(String tableName, Class<S> tableHelperClass, String tableAlias) {
        super.rightJoin(tableName, tableHelperClass, tableAlias);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(String tableName, Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        super.rightJoin(tableName, tableHelperClass, onJoinLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(String tableName, Class<S> tableHelperClass) {
        super.rightJoin(tableName, tableHelperClass);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        super.rightJoin(tableHelperClass, tableAlias, onJoinLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(Class<S> tableHelperClass, String tableAlias) {
        super.rightJoin(tableHelperClass, tableAlias);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        super.rightJoin(tableHelperClass, onJoinLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(Class<S> tableHelperClass) {
        super.rightJoin(tableHelperClass);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> on(OnLambdaCallable<TO> onLambdaCallable) {
        super.on(onLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> on(Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        super.on(tableHelperClass, tableAlias, onJoinLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> on(Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        super.on(tableHelperClass, onJoinLambdaCallable);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> orderBy(SortLambdaCallable<TS> sortLambdaCallable) {
        super.orderBy(sortLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> orderBy(Class<S> tableHelperClass, String tableAlias, SortLambdaCallable<SS> sortLambdaCallable) {
        super.orderBy(tableHelperClass, tableAlias, sortLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> orderBy(Class<S> tableHelperClass, SortLambdaCallable<SS> sortLambdaCallable) {
        super.orderBy(tableHelperClass, sortLambdaCallable);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> where(WhereLambdaCallable<TW> whereLambdaCallable) {
        super.where(whereLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> where(Class<S> tableHelperClass, String tableAlias, WhereJoinLambdaCallable<TW, SW> whereJoinLambdaCallable) {
        super.where(tableHelperClass, tableAlias, whereJoinLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> where(Class<S> tableHelperClass, WhereJoinLambdaCallable<TW, SW> whereJoinLambdaCallable) {
        super.where(tableHelperClass, whereJoinLambdaCallable);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> limit(Long limit) {
        super.limit(limit);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> offset(Long offset) {
        super.offset(offset);
        return this;
    }

    @Override
    public <FC extends ColumnHelper<FC>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> buildColumnExpression(ColumnBuilder<FC> columnBuilder) {
        super.buildColumnExpression(columnBuilder);
        return this;
    }

    @Override
    public <FC extends ColumnHelper<FC>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> buildSelectColumnExpression(SelectColumnBuilder<FC> selectColumnBuilder) {
        super.buildSelectColumnExpression(selectColumnBuilder);
        return this;
    }

    @Override
    public <FO extends OnHelper<FO>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> buildJoinExpression(JoinBuilder<FO> joinBuilder) {
        super.buildJoinExpression(joinBuilder);
        return this;
    }

    @Override
    public <FO extends OnHelper<FO>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> buildOnExpression(OnBuilder<FO> onBuilder) {
        super.buildOnExpression(onBuilder);
        return this;
    }

    @Override
    public <FW extends WhereHelper<FW>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> buildWhereExpression(WhereBuilder<FW> whereBuilder) {
        super.buildWhereExpression(whereBuilder);
        return this;
    }

    @Override
    public <FG extends GroupHelper<FG>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> buildGroupExpression(GroupBuilder<FG> groupBuilder) {
        super.buildGroupExpression(groupBuilder);
        return this;
    }

    @Override
    public <FH extends HavingHelper<FH>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> buildHavingExpression(HavingBuilder<FH> havingBuilder) {
        super.buildHavingExpression(havingBuilder);
        return this;
    }

    @Override
    public <FS extends SortHelper<FS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> buildSortExpression(SortBuilder<FS> sortBuilder) {
        super.buildSortExpression(sortBuilder);
        return this;
    }

    public <R> List<R> fetch(ResultSetExtractor<List<R>> resultSetExtractor) {
        return this.jdbcHelper.query(this, resultSetExtractor);
    }

    public <R> List<R> fetch(RowMapper<R> rowMapper) {
        return this.jdbcHelper.query(this, rowMapper);
    }

    public List<Map<String, Object>> fetch() {
        return fetch(new ColumnMapRowMapper());
    }

    public <R> R fetchOne(RowMapper<R> rowMapper) {
        return this.jdbcHelper.queryOne(this, rowMapper);
    }

    public Map<String, Object> fetchOne() {
        return this.jdbcHelper.queryOne(this, new ColumnMapRowMapper());
    }

    public <R> R fetchByPrimaryKey(Object keyValue, RowMapper<R> rowMapper) {
        return this.jdbcHelper.queryByPrimaryKey(keyValue, this, rowMapper);
    }

    public Map<String, Object> fetchByPrimaryKey(Object keyValue) {
        return this.jdbcHelper.queryByPrimaryKey(keyValue, this, new ColumnMapRowMapper());
    }
}