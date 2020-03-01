package pub.avalonframework.sqlhelper.core.builder;

import pub.avalonframework.sqlhelper.core.block.LimitBlock;
import pub.avalonframework.sqlhelper.core.block.helper.HelperCrudBlock;
import pub.avalonframework.sqlhelper.core.data.block.GroupType;
import pub.avalonframework.sqlhelper.core.data.block.JoinType;
import pub.avalonframework.sqlhelper.core.expression.lambda.*;
import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
//TODO 改名
public abstract class SqlBuilder<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
        TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> implements

        HelperCrudBlock<SqlBuilder<T, TC, TO, TW, TG, TH, TS>>,

        CrudLambdaExpression<TC, TO, TW, TG, TH, TS, SqlBuilder<T, TC, TO, TW, TG, TH, TS>>,

        LimitBlock<SqlBuilder<T, TC, TO, TW, TG, TH, TS>> {

    private TO onHelper;
    private TC columnHelper;
    private TW whereHelper;
    private TG groupHelper;
    private TH havingHelper;
    private TS sortHelper;
    private String tableAlias;

    public SqlBuilder() {

    }

    public SqlBuilder(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    @Override
    public SqlBuilder<T, TC, TO, TW, TG, TH, TS> select(ColumnHelper<?>... columnHelpers) {
        return null;
    }

    @Override
    public SqlBuilder<T, TC, TO, TW, TG, TH, TS> insert(ColumnHelper<?>... columnHelpers) {
        return null;
    }

    @Override
    public SqlBuilder<T, TC, TO, TW, TG, TH, TS> update(ColumnHelper<?>... columnHelpers) {
        return null;
    }

    @Override
    public SqlBuilder<T, TC, TO, TW, TG, TH, TS> select(ColumnLambdaCallable<TC> columnLambdaCallable) {
        return this;
    }

    @Override
    public SqlBuilder<T, TC, TO, TW, TG, TH, TS> insert(ColumnLambdaCallable<TC> columnLambdaCallable) {
        return this;
    }

    @Override
    public SqlBuilder<T, TC, TO, TW, TG, TH, TS> update(ColumnLambdaCallable<TC> columnLambdaCallable) {
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlBuilder<T, TC, TO, TW, TG, TH, TS> select(Class<S> tableHelperClass, String tableAlias, ColumnLambdaCallable<SC> columnLambdaCallable) {
        return this;
    }

    @Override
    public SqlBuilder<T, TC, TO, TW, TG, TH, TS> virtualColumn(Object columnValue, String columnAlias) {
        return this;
    }

    @Override
    public SqlBuilder<T, TC, TO, TW, TG, TH, TS> groupColumn(GroupType groupType, ColumnLambdaCallable<TC> columnLambdaCallable) {
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlBuilder<T, TC, TO, TW, TG, TH, TS> groupColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnLambdaCallable<SC> columnLambdaCallable) {
        return this;
    }

    @Override
    public SqlBuilder<T, TC, TO, TW, TG, TH, TS> subQueryColumn(String columnAlias, SubQueryColumnLambdaCallable<TC> subQueryColumnLambdaCallable) {
        return null;
    }

    @Override
    public SqlBuilder<T, TC, TO, TW, TG, TH, TS> groupBy(GroupHelper<?>... groupHelpers) {
        return this;
    }

    @Override
    public SqlBuilder<T, TC, TO, TW, TG, TH, TS> groupBy(GroupLambdaCallable<TG> groupLambdaCallable) {
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlBuilder<T, TC, TO, TW, TG, TH, TS> groupBy(Class<S> tableHelperClass, String tableAlias, GroupLambdaCallable<SG> groupLambdaCallable) {
        return this;
    }

    @Override
    public SqlBuilder<T, TC, TO, TW, TG, TH, TS> limit(Long limit) {
        return null;
    }

    @Override
    public SqlBuilder<T, TC, TO, TW, TG, TH, TS> offset(Long offset) {
        return null;
    }

    @Override
    public SqlBuilder<T, TC, TO, TW, TG, TH, TS> orderBy(SortHelper<?>... sortHelpers) {
        return this;
    }

    @Override
    public SqlBuilder<T, TC, TO, TW, TG, TH, TS> orderBy(SortLambdaCallable<TS> sortLambdaCallable) {
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlBuilder<T, TC, TO, TW, TG, TH, TS> orderBy(Class<S> tableHelperClass, String tableAlias, SortLambdaCallable<SS> sortLambdaCallable) {
        return this;
    }

    @Override
    public SqlBuilder<T, TC, TO, TW, TG, TH, TS> where(WhereHelper<?>... whereHelpers) {
        return this;
    }

    @Override
    public SqlBuilder<T, TC, TO, TW, TG, TH, TS> where(WhereLambdaCallable<TW> callback) {
        return this;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlBuilder<T, TC, TO, TW, TG, TH, TS> where(Class<S> tableHelperClass, String tableAlias, WhereJoinLambdaCallable<TW, SW> callback) {
        return this;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlBuilder<T, TC, TO, TW, TG, TH, TS> join(JoinType joinType, String tableName, Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return null;
    }

    @Override
    public SqlBuilder<T, TC, TO, TW, TG, TH, TS> on(OnLambdaCallable<TO> onLambdaCallable) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlBuilder<T, TC, TO, TW, TG, TH, TS> on(Class<S> tableHelperClass, String tableAlias, OnJoinLambdaCallable<TO, SO> onJoinLambdaCallable) {
        return null;
    }

    @Override
    public SqlBuilder<T, TC, TO, TW, TG, TH, TS> on(OnHelper<?>... onHelpers) {
        return null;
    }

    @Override
    public SqlBuilder<T, TC, TO, TW, TG, TH, TS> having(HavingLambdaCallable<TH> havingLambdaCallable) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> SqlBuilder<T, TC, TO, TW, TG, TH, TS> having(Class<S> tableHelperClass, String tableAlias, HavingJoinLambdaCallable<TH, SH> havingJoinLambdaCallable) {
        return null;
    }

    @Override
    public SqlBuilder<T, TC, TO, TW, TG, TH, TS> having(HavingHelper<?>... havingHelpers) {
        return null;
    }
}