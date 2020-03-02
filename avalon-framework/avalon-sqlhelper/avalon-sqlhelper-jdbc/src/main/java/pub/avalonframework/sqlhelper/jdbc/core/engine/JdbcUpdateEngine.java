package pub.avalonframework.sqlhelper.jdbc.core.engine;

import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.*;
import pub.avalonframework.sqlhelper.core.engine.AbstractUpdateEngine;
import pub.avalonframework.sqlhelper.core.expression.builder.JoinBuilder;
import pub.avalonframework.sqlhelper.core.expression.builder.OnBuilder;
import pub.avalonframework.sqlhelper.core.expression.builder.UpdateColumnBuilder;
import pub.avalonframework.sqlhelper.core.expression.builder.WhereBuilder;
import pub.avalonframework.sqlhelper.core.expression.lambda.*;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.jdbc.core.JdbcHelper;

import java.util.Collection;

/**
 * @author baichao
 */
public final class JdbcUpdateEngine<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
        TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends AbstractUpdateEngine<T, TC, TO, TW, TG, TH, TS> {

    private JdbcHelper jdbcHelper;

    public JdbcUpdateEngine(JdbcHelper jdbcHelper, Class<T> tableHelperClass) {
        super(jdbcHelper.getDatabaseType(), tableHelperClass);
    }

    public JdbcUpdateEngine(JdbcHelper jdbcHelper, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        super(jdbcHelper.getDatabaseType(), tableHelperClass, configuration);
    }

    public JdbcUpdateEngine(JdbcHelper jdbcHelper, String tableName, Class<T> tableHelperClass) {
        super(jdbcHelper.getDatabaseType(), tableName, tableHelperClass);
    }

    public JdbcUpdateEngine(JdbcHelper jdbcHelper, String tableName, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        super(jdbcHelper.getDatabaseType(), tableName, tableHelperClass, configuration);
    }

    public JdbcUpdateEngine(JdbcHelper jdbcHelper, Class<T> tableHelperClass, String tableAlias) {
        super(jdbcHelper.getDatabaseType(), tableHelperClass, tableAlias);
    }

    public JdbcUpdateEngine(JdbcHelper jdbcHelper, String tableName, Class<T> tableHelperClass, String tableAlias) {
        super(jdbcHelper.getDatabaseType(), tableName, tableHelperClass, tableAlias);
    }

    public JdbcUpdateEngine(JdbcHelper jdbcHelper, String tableName, Class<T> tableHelperClass, String tableAlias, SqlhelperConfiguration configuration) {
        super(jdbcHelper.getDatabaseType(), tableName, tableHelperClass, tableAlias, configuration);
    }

    public JdbcHelper getJdbcHelper() {
        return jdbcHelper;
    }

    @Override
    public JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> setConfiguration(SqlhelperConfiguration configuration) {
        super.setConfiguration(configuration);
        return this;
    }

    @Override
    public JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> addTableJoinDataBlock(TableJoinDataBlock tableJoinDataBlock) {
        super.addTableJoinDataBlock(tableJoinDataBlock);
        return this;
    }

    @Override
    public JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> addUpdateTableColumnDataBlock(TableColumnDataBlock tableColumnDataBlock) {
        super.addUpdateTableColumnDataBlock(tableColumnDataBlock);
        return this;
    }

    @Override
    public JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> addTableOnDataBlock(TableOnDataBlock tableOnDataBlock) {
        super.addTableOnDataBlock(tableOnDataBlock);
        return this;
    }

    @Override
    public JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> addTableWhereDataBlock(TableWhereDataBlock tableWhereDataBlock) {
        super.addTableWhereDataBlock(tableWhereDataBlock);
        return this;
    }

    @Override
    public JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> update(ColumnHelper<?>... columnHelpers) {
        super.update(columnHelpers);
        return this;
    }

    @Override
    public JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> on(OnHelper<?>... onHelpers) {
        super.on(onHelpers);
        return this;
    }

    @Override
    public JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> where(WhereHelper<?>... whereHelpers) {
        super.where(whereHelpers);
        return this;
    }

    @Override
    public JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> update(ColumnLambdaCallable<TC> columnLambdaCallable) {
        super.update(columnLambdaCallable);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, Class<S> tableHelperClass, String tableAlias) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, Class<S> tableHelperClass) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(String tableName, Class<S> tableHelperClass, String tableAlias) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(String tableName, Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(String tableName, Class<S> tableHelperClass) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(Class<S> tableHelperClass, String tableAlias) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(Class<S> tableHelperClass) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(String tableName, Class<S> tableHelperClass, String tableAlias) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(String tableName, Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(String tableName, Class<S> tableHelperClass) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(Class<S> tableHelperClass, String tableAlias) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(Class<S> tableHelperClass) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(String tableName, Class<S> tableHelperClass, String tableAlias) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(String tableName, Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(String tableName, Class<S> tableHelperClass) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(Class<S> tableHelperClass, String tableAlias) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(Class<S> tableHelperClass) {
        super.rightJoin(tableHelperClass);
        return this;
    }

    @Override
    public JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> on(OnLambdaCallable<TO> onLambdaCallable) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> on(Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> on(Class<S> tableHelperClass, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        super.on(tableHelperClass, onJoinLambdaCallable);
        return this;
    }

    @Override
    public JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> where(WhereLambdaCallable<TW> whereLambdaCallable) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> where(Class<S> tableHelperClass, String tableAlias, WhereJoinLambdaCallable<TW, SW> whereJoinLambdaCallable) {
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> where(Class<S> tableHelperClass, WhereJoinLambdaCallable<TW, SW> whereJoinLambdaCallable) {
        super.where(tableHelperClass, whereJoinLambdaCallable);
        return this;
    }

    @Override
    public <FO extends OnHelper<FO>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> buildJoinExpression(JoinBuilder<FO> joinBuilder) {
        super.buildJoinExpression(joinBuilder);
        return this;
    }

    @Override
    public <FO extends OnHelper<FO>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> buildOnExpression(OnBuilder<FO> onBuilder) {
        super.buildOnExpression(onBuilder);
        return this;
    }

    @Override
    public <FW extends WhereHelper<FW>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> buildWhereExpression(WhereBuilder<FW> whereBuilder) {
        super.buildWhereExpression(whereBuilder);
        return this;
    }

    @Override
    public <FC extends ColumnHelper<FC>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> buildUpdateColumnExpression(UpdateColumnBuilder<FC> updateColumnBuilder) {
        super.buildUpdateColumnExpression(updateColumnBuilder);
        return this;
    }

    public int execute(Object javaBean, boolean selective) {
        return selective ? this.jdbcHelper.updateJavaBeanSelective(javaBean, this) : this.jdbcHelper.updateJavaBean(javaBean, this);
    }

    public int execute(Object javaBean) {
        return execute(javaBean, false);
    }

    public int executeByPrimaryKey(Object keyValue, Object javaBean, boolean selective) {
        return selective ? this.jdbcHelper.updateJavaBeanByPrimaryKeySelective(keyValue, javaBean, this) : this.jdbcHelper.updateJavaBeanByPrimaryKey(keyValue, javaBean, this);
    }

    public int executeBatchByPrimaryKeys(Collection<?> primaryKeys) {
        return this.jdbcHelper.batchUpdateJavaBeansByPrimaryKeys(primaryKeys, this);
    }
}