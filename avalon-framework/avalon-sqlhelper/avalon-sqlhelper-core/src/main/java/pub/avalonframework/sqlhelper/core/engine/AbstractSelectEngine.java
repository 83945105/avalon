package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.*;
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

    public AbstractSelectEngine(DatabaseType databaseType, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        super(databaseType, tableHelperClass, configuration);
    }

    public AbstractSelectEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass) {
        super(databaseType, tableName, tableHelperClass);
    }

    public AbstractSelectEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        super(databaseType, tableName, tableHelperClass, configuration);
    }

    public AbstractSelectEngine(DatabaseType databaseType, Class<T> tableHelperClass, String tableAlias) {
        super(databaseType, tableHelperClass, tableAlias);
    }

    public AbstractSelectEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        super(databaseType, tableName, tableHelperClass, tableAlias);
    }

    public AbstractSelectEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlhelperConfiguration configuration) {
        super(databaseType, tableName, tableHelperClass, tableAlias, configuration);
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

    @Override
    public void addTableJoinDataBlock(TableJoinDataBlock tableJoinDataBlock) {
        this.dataStore.addTableJoinDataBlock(tableJoinDataBlock);
    }

    @Override
    public void addTableOnDataBlock(TableOnDataBlock tableOnDataBlock) {
        this.dataStore.addTableOnDataBlock(tableOnDataBlock);
    }

    @Override
    public void addSelectTableColumnDataBlock(TableColumnDataBlock tableColumnDataBlock) {
        this.dataStore.addSelectTableColumnDataBlock(tableColumnDataBlock);
    }

    @Override
    public void addTableWhereDataBlock(TableWhereDataBlock tableWhereDataBlock) {
        this.dataStore.addTableWhereDataBlock(tableWhereDataBlock);
    }

    @Override
    public void addTableGroupDataBlock(TableGroupDataBlock tableGroupDataBlock) {
        this.dataStore.addTableGroupDataBlock(tableGroupDataBlock);
    }

    @Override
    public void addTableHavingDataBlock(TableHavingDataBlock tableHavingDataBlock) {
        this.dataStore.addTableHavingDataBlock(tableHavingDataBlock);
    }

    @Override
    public void addTableSortDataBlock(TableSortDataBlock tableSortDataBlock) {
        this.dataStore.addTableSortDataBlock(tableSortDataBlock);
    }

    @Override
    public void setLimit(Long limit) {
        this.dataStore.setLimit(limit);
    }

    @Override
    public void setOffset(Long offset) {
        this.dataStore.setOffset(offset);
    }
}