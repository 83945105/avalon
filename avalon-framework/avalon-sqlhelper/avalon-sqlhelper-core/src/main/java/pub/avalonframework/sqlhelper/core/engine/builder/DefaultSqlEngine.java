package pub.avalonframework.sqlhelper.core.engine.builder;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.engine.AbstractEngine;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

/**
 * @author baichao
 */
public final class DefaultSqlEngine<T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends AbstractEngine<T, TO, TC, TW, TG, TH, TS> implements SqlEngine<DefaultSqlEngine> {

    public DefaultSqlEngine(DatabaseType databaseType, Class<T> tableHelperClass) {
        super(databaseType, tableHelperClass);
    }

    public DefaultSqlEngine(DatabaseType databaseType, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        super(databaseType, tableHelperClass, sqlBuilderOptions);
    }

    public DefaultSqlEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass) {
        super(databaseType, tableName, tableHelperClass);
    }

    public DefaultSqlEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        super(databaseType, tableName, tableHelperClass, sqlBuilderOptions);
    }

    public DefaultSqlEngine(DatabaseType databaseType, Class<T> tableHelperClass, String tableAlias) {
        super(databaseType, tableHelperClass, tableAlias);
    }

    public DefaultSqlEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        super(databaseType, tableName, tableHelperClass, tableAlias);
    }

    public DefaultSqlEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlBuilderOptions sqlBuilderOptions) {
        super(databaseType, tableName, tableHelperClass, tableAlias, sqlBuilderOptions);
    }

    @Override
    public <FO extends OnHelper<FO>> DefaultSqlEngine sqlJoin(SqlJoin<FO> sqlJoin) {
        SqlJoin.execute(sqlJoin, this.sqlBuilderOptions).forEach(this::addJoinTableDatum);
        return this;
    }

    @Override
    public <FO extends OnHelper<FO>> DefaultSqlEngine sqlOn(SqlOn<FO> sqlOn) {
        SqlOn.execute(sqlOn, this.sqlBuilderOptions, () -> this);
        return this;
    }

    @Override
    public <FC extends ColumnHelper<FC>> DefaultSqlEngine sqlColumn(SqlColumn<FC> sqlColumn) {
        SqlColumn.execute(sqlColumn, this.sqlBuilderOptions, () -> this);
        return this;
    }

    @Override
    public <FW extends WhereHelper<FW>> DefaultSqlEngine sqlWhere(SqlWhere<FW> sqlWhere) {
        SqlWhere.execute(sqlWhere, this.sqlBuilderOptions, () -> this);
        return this;
    }

    @Override
    public <FG extends GroupHelper<FG>> DefaultSqlEngine sqlGroup(SqlGroup<FG> sqlGroup) {
        SqlGroup.execute(sqlGroup, this.sqlBuilderOptions, () -> this);
        return this;
    }

    @Override
    public <FS extends SortHelper<FS>> DefaultSqlEngine sqlSort(SqlSort<FS> sqlSort) {
        SqlSort.execute(sqlSort, this.sqlBuilderOptions, () -> this);
        return this;
    }
}