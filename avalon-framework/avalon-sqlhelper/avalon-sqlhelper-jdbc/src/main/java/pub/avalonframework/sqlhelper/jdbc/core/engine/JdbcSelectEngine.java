package pub.avalonframework.sqlhelper.jdbc.core.engine;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.beans.GroupType;
import pub.avalonframework.sqlhelper.core.beans.JoinType;
import pub.avalonframework.sqlhelper.core.builder.*;
import pub.avalonframework.sqlhelper.core.callback.*;
import pub.avalonframework.sqlhelper.core.engine.AbstractSelectEngine;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author baichao
 */
public class JdbcSelectEngine<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
        TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends AbstractSelectEngine<T, TC, TO, TW, TG, TH, TS> {

    private JdbcTemplate jdbcTemplate;

    public JdbcSelectEngine(JdbcTemplate jdbcTemplate, Class<T> tableHelperClass) {
        super(jdbcTemplate.getDatabaseType(), tableHelperClass);
    }

    public JdbcSelectEngine(JdbcTemplate jdbcTemplate, Class<T> tableHelperClass, SqlhelperConfiguration sqlhelperConfiguration) {
        super(jdbcTemplate.getDatabaseType(), tableHelperClass, sqlhelperConfiguration);
    }

    public JdbcSelectEngine(JdbcTemplate jdbcTemplate, String tableName, Class<T> tableHelperClass) {
        super(jdbcTemplate.getDatabaseType(), tableName, tableHelperClass);
    }

    public JdbcSelectEngine(JdbcTemplate jdbcTemplate, String tableName, Class<T> tableHelperClass, SqlhelperConfiguration sqlhelperConfiguration) {
        super(jdbcTemplate.getDatabaseType(), tableName, tableHelperClass, sqlhelperConfiguration);
    }

    public JdbcSelectEngine(JdbcTemplate jdbcTemplate, Class<T> tableHelperClass, String tableAlias) {
        super(jdbcTemplate.getDatabaseType(), tableHelperClass, tableAlias);
    }

    public JdbcSelectEngine(JdbcTemplate jdbcTemplate, String tableName, Class<T> tableHelperClass, String tableAlias) {
        super(jdbcTemplate.getDatabaseType(), tableName, tableHelperClass, tableAlias);
    }

    public JdbcSelectEngine(JdbcTemplate jdbcTemplate, String tableName, Class<T> tableHelperClass, String tableAlias, SqlhelperConfiguration sqlhelperConfiguration) {
        super(jdbcTemplate.getDatabaseType(), tableName, tableHelperClass, tableAlias, sqlhelperConfiguration);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> select(ColumnHelper<?>... columnHelpers) {
        super.select(columnHelpers);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> virtualColumn(Object columnValue, String columnAlias) {
        super.virtualColumn(columnValue, columnAlias);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> on(OnHelper<?>... onHelpers) {
        super.on(onHelpers);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> where(WhereHelper<?>... whereHelpers) {
        super.where(whereHelpers);
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
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> orderBy(SortHelper<?>... sortHelpers) {
        super.orderBy(sortHelpers);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> select(ColumnCallback<TC> columnCallback) {
        super.select(columnCallback);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> select(Class<S> tableHelperClass, String tableAlias, ColumnCallback<SC> columnCallback) {
        super.select(tableHelperClass, tableAlias, columnCallback);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> groupColumn(GroupType groupType, ColumnCallback<TC> columnCallback) {
        super.groupColumn(groupType, columnCallback);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> groupColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnCallback<SC> columnCallback) {
        super.groupColumn(tableHelperClass, tableAlias, groupType, columnCallback);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> subQueryColumn(String columnAlias, SubQueryColumnCallback<TC> subQueryColumnCallback) {
        super.subQueryColumn(columnAlias, subQueryColumnCallback);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        super.join(joinType, tableName, tableHelperClass, tableAlias, onJoinCallback);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> on(OnCallback<TO> onCallback) {
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
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> on(Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        super.on(tableHelperClass, tableAlias, onJoinCallback);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> where(WhereCallback<TW> whereCallback) {
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
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> where(Class<S> tableHelperClass, String tableAlias, WhereJoinCallback<TW, SW> whereJoinCallback) {
        super.where(tableHelperClass, tableAlias, whereJoinCallback);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> groupBy(GroupCallback<TG> groupCallback) {
        super.groupBy(groupCallback);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> groupBy(Class<S> tableHelperClass, String tableAlias, GroupCallback<SG> groupCallback) {
        super.groupBy(tableHelperClass, tableAlias, groupCallback);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> having(HavingCallback<TH> havingCallback) {
        super.having(havingCallback);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> having(Class<S> tableHelperClass, String tableAlias, HavingJoinCallback<TH, SH> havingJoinCallback) {
        super.having(tableHelperClass, tableAlias, havingJoinCallback);
        return this;
    }

    @Override
    public JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> orderBy(SortCallback<TS> sortCallback) {
        super.orderBy(sortCallback);
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> orderBy(Class<S> tableHelperClass, String tableAlias, SortCallback<SS> sortCallback) {
        super.orderBy(tableHelperClass, tableAlias, sortCallback);
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
    public <FC extends ColumnHelper<FC>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> buildSelectColumn(SelectColumnBuilder<FC> selectColumnBuilder) {
        super.buildSelectColumn(selectColumnBuilder);
        return this;
    }

    @Override
    public <FC extends ColumnHelper<FC>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> buildColumn(ColumnBuilder<FC> columnBuilder) {
        super.buildColumn(columnBuilder);
        return this;
    }

    @Override
    public <FO extends OnHelper<FO>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> buildJoin(JoinBuilder<FO> joinBuilder) {
        super.buildJoin(joinBuilder);
        return this;
    }

    @Override
    public <FO extends OnHelper<FO>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> buildOn(OnBuilder<FO> onBuilder) {
        super.buildOn(onBuilder);
        return this;
    }

    @Override
    public <FW extends WhereHelper<FW>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> buildWhere(WhereBuilder<FW> whereBuilder) {
        super.buildWhere(whereBuilder);
        return this;
    }

    @Override
    public <FG extends GroupHelper<FG>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> buildGroup(GroupBuilder<FG> groupBuilder) {
        super.buildGroup(groupBuilder);
        return this;
    }

    @Override
    public <FH extends HavingHelper<FH>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> buildHaving(HavingBuilder<FH> havingBuilder) {
        super.buildHaving(havingBuilder);
        return this;
    }

    @Override
    public <FS extends SortHelper<FS>> JdbcSelectEngine<T, TC, TO, TW, TG, TH, TS> buildSort(SortBuilder<FS> sortBuilder) {
        super.buildSort(sortBuilder);
        return this;
    }

    public <R> R fetch(ResultSetExtractor<R> resultSetExtractor) {
        return null;
    }

    public <R> List<R> fetch(RowMapper<R> rowMapper) {
        return null;
    }

    public List<Map<String, Object>> fetch() {
        return null;
    }

    public Map<String, Object> fetchOne() {
        return null;
    }
}