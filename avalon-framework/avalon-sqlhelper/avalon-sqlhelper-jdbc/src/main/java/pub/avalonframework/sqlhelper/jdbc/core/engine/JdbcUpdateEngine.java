package pub.avalonframework.sqlhelper.jdbc.core.engine;

import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.beans.JoinType;
import pub.avalonframework.sqlhelper.core.builder.JoinBuilder;
import pub.avalonframework.sqlhelper.core.builder.OnBuilder;
import pub.avalonframework.sqlhelper.core.builder.UpdateColumnBuilder;
import pub.avalonframework.sqlhelper.core.builder.WhereBuilder;
import pub.avalonframework.sqlhelper.core.callback.*;
import pub.avalonframework.sqlhelper.core.data.block.TableColumnDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.TableJoinDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.TableOnDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.TableWhereDataBlock;
import pub.avalonframework.sqlhelper.core.engine.AbstractUpdateEngine;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.jdbc.core.JdbcTemplate;

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

    private JdbcTemplate jdbcTemplate;

    public JdbcUpdateEngine(JdbcTemplate jdbcTemplate, Class<T> tableHelperClass) {
        super(jdbcTemplate.getDatabaseType(), tableHelperClass);
    }

    public JdbcUpdateEngine(JdbcTemplate jdbcTemplate, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        super(jdbcTemplate.getDatabaseType(), tableHelperClass, configuration);
    }

    public JdbcUpdateEngine(JdbcTemplate jdbcTemplate, String tableName, Class<T> tableHelperClass) {
        super(jdbcTemplate.getDatabaseType(), tableName, tableHelperClass);
    }

    public JdbcUpdateEngine(JdbcTemplate jdbcTemplate, String tableName, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        super(jdbcTemplate.getDatabaseType(), tableName, tableHelperClass, configuration);
    }

    public JdbcUpdateEngine(JdbcTemplate jdbcTemplate, Class<T> tableHelperClass, String tableAlias) {
        super(jdbcTemplate.getDatabaseType(), tableHelperClass, tableAlias);
    }

    public JdbcUpdateEngine(JdbcTemplate jdbcTemplate, String tableName, Class<T> tableHelperClass, String tableAlias) {
        super(jdbcTemplate.getDatabaseType(), tableName, tableHelperClass, tableAlias);
    }

    public JdbcUpdateEngine(JdbcTemplate jdbcTemplate, String tableName, Class<T> tableHelperClass, String tableAlias, SqlhelperConfiguration configuration) {
        super(jdbcTemplate.getDatabaseType(), tableName, tableHelperClass, tableAlias, configuration);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
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
    public JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> update(ColumnCallback<TC> columnCallback) {
        super.update(columnCallback);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        super.join(joinType, tableName, tableHelperClass, tableAlias, onJoinCallback);
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        super.join(joinType, tableName, tableHelperClass, onJoinCallback);
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        super.join(joinType, tableHelperClass, tableAlias, onJoinCallback);
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        super.join(joinType, tableHelperClass, onJoinCallback);
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        super.innerJoin(tableName, tableHelperClass, tableAlias, onJoinCallback);
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(String tableName, Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        super.innerJoin(tableName, tableHelperClass, onJoinCallback);
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        super.innerJoin(tableHelperClass, tableAlias, onJoinCallback);
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> innerJoin(Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        super.innerJoin(tableHelperClass, onJoinCallback);
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        super.leftJoin(tableName, tableHelperClass, tableAlias, onJoinCallback);
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(String tableName, Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        super.leftJoin(tableName, tableHelperClass, onJoinCallback);
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        super.leftJoin(tableHelperClass, tableAlias, onJoinCallback);
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> leftJoin(Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        super.leftJoin(tableHelperClass, onJoinCallback);
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        super.rightJoin(tableName, tableHelperClass, tableAlias, onJoinCallback);
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(String tableName, Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        super.rightJoin(tableName, tableHelperClass, onJoinCallback);
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        super.rightJoin(tableHelperClass, tableAlias, onJoinCallback);
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
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> rightJoin(Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        super.rightJoin(tableHelperClass, onJoinCallback);
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
    public JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> on(OnCallback<TO> onCallback) {
        super.on(onCallback);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> on(Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        super.on(tableHelperClass, tableAlias, onJoinCallback);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> on(Class<S> tableHelperClass, OnJoinCallback<TO, SO> onJoinCallback) {
        super.on(tableHelperClass, onJoinCallback);
        return this;
    }

    @Override
    public JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> where(WhereCallback<TW> whereCallback) {
        super.where(whereCallback);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> where(Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<TW, SW> whereJoinCallback) {
        super.where(tableHelperClass, tableAlias, whereJoinCallback);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> where(Class<S> tableHelperClass, WhereJoinCallback<TW, SW> whereJoinCallback) {
        super.where(tableHelperClass, whereJoinCallback);
        return this;
    }

    @Override
    public <FO extends OnHelper<FO>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> buildJoin(JoinBuilder<FO> joinBuilder) {
        super.buildJoin(joinBuilder);
        return this;
    }

    @Override
    public <FO extends OnHelper<FO>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> buildOn(OnBuilder<FO> onBuilder) {
        super.buildOn(onBuilder);
        return this;
    }

    @Override
    public <FW extends WhereHelper<FW>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> buildWhere(WhereBuilder<FW> whereBuilder) {
        super.buildWhere(whereBuilder);
        return this;
    }

    @Override
    public <FC extends ColumnHelper<FC>> JdbcUpdateEngine<T, TC, TO, TW, TG, TH, TS> buildUpdateColumn(UpdateColumnBuilder<FC> updateColumnBuilder) {
        super.buildUpdateColumn(updateColumnBuilder);
        return this;
    }

    public int execute(Object javaBean, boolean selective) {
        return selective ? this.jdbcTemplate.updateJavaBeanSelective(javaBean, this) : this.jdbcTemplate.updateJavaBean(javaBean, this);
    }

    public int execute(Object javaBean) {
        return execute(javaBean, false);
    }

    public int executeByPrimaryKey(Object keyValue, Object javaBean, boolean selective) {
        return selective ? this.jdbcTemplate.updateJavaBeanByPrimaryKeySelective(keyValue, javaBean, this) : this.jdbcTemplate.updateJavaBeanByPrimaryKey(keyValue, javaBean, this);
    }

    public int executeBatchByPrimaryKeys(Collection<?> primaryKeys) {
        return this.jdbcTemplate.batchUpdateJavaBeansByPrimaryKeys(primaryKeys, this);
    }
}