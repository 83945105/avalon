package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.data.*;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.sqlbuilder.SelectSqlBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.SelectSqlBuilderResult;

/**
 * @author baichao
 */
public abstract class AbstractSelectEngine<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
        TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends AbstractEngine<T, TC, TO, TW, TG, TH, TS> implements SelectSqlBuilder, SelectEngine<T, TC, TO, TW, TG, TH, TS> {

    public AbstractSelectEngine(DatabaseType databaseType, Class<T> tableHelperClass) {
        super(databaseType, tableHelperClass);
    }

    public AbstractSelectEngine(DatabaseType databaseType, Class<T> tableHelperClass, SqlhelperConfiguration sqlhelperConfiguration) {
        super(databaseType, tableHelperClass, sqlhelperConfiguration);
    }

    public AbstractSelectEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass) {
        super(databaseType, tableName, tableHelperClass);
    }

    public AbstractSelectEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, SqlhelperConfiguration sqlhelperConfiguration) {
        super(databaseType, tableName, tableHelperClass, sqlhelperConfiguration);
    }

    public AbstractSelectEngine(DatabaseType databaseType, Class<T> tableHelperClass, String tableAlias) {
        super(databaseType, tableHelperClass, tableAlias);
    }

    public AbstractSelectEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        super(databaseType, tableName, tableHelperClass, tableAlias);
    }

    public AbstractSelectEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlhelperConfiguration sqlhelperConfiguration) {
        super(databaseType, tableName, tableHelperClass, tableAlias, sqlhelperConfiguration);
    }

    @Override
    public SelectSqlBuilderResult query() {
        return this.crudSqlBuilder.query();
    }

    @Override
    public SelectSqlBuilderResult queryCount() {
        return this.crudSqlBuilder.queryCount();
    }

    @Override
    public SelectSqlBuilderResult queryByPrimaryKey(Object primaryKeyValue) {
        return this.crudSqlBuilder.queryByPrimaryKey(primaryKeyValue);
    }

    public DatabaseType getDatabaseType() {
        return this.sqlData.getDatabaseType();
    }

    @Override
    public void addJoinTableDatum(JoinTableDatum joinTableDatum) {
        this.sqlData.addJoinTableDatum(joinTableDatum);
    }

    @Override
    public void addTableOnDatum(TableOnDatum tableOnDatum) {
        this.sqlData.addTableOnDatum(tableOnDatum);
    }

    @Override
    public void addSelectTableColumnDatum(TableColumnDatum tableColumnDatum) {
        this.sqlData.addSelectTableColumnDatum(tableColumnDatum);
    }

    @Override
    public void addTableWhereDatum(TableWhereDatum tableWhereDatum) {
        this.sqlData.addTableWhereDatum(tableWhereDatum);
    }

    @Override
    public void addTableGroupDatum(TableGroupDatum tableGroupDatum) {
        this.sqlData.addTableGroupDatum(tableGroupDatum);
    }

    @Override
    public void addTableHavingDatum(TableHavingDatum tableHavingDatum) {
        this.sqlData.addTableHavingDatum(tableHavingDatum);
    }

    @Override
    public void addTableSortDatum(TableSortDatum tableSortDatum) {
        this.sqlData.addTableSortDatum(tableSortDatum);
    }

    @Override
    public void setLimit(Long limit) {
        this.sqlData.setLimit(limit);
    }

    @Override
    public void setOffset(Long offset) {
        this.sqlData.setOffset(offset);
    }
}