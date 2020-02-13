package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.TableJoinDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.TableOnDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.TableWhereDataBlock;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.sqlbuilder.DeleteSqlBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.DeleteSqlBuilderResult;

/**
 * @author baichao
 */
public abstract class AbstractDeleteEngine<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
        TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends AbstractEngine<T, TC, TO, TW, TG, TH, TS> implements DeleteSqlBuilder, DeleteEngine<T, TC, TO, TW, TG, TH, TS> {

    public AbstractDeleteEngine(DatabaseType databaseType, Class<T> tableHelperClass) {
        super(databaseType, tableHelperClass);
    }

    public AbstractDeleteEngine(DatabaseType databaseType, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        super(databaseType, tableHelperClass, configuration);
    }

    public AbstractDeleteEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass) {
        super(databaseType, tableName, tableHelperClass);
    }

    public AbstractDeleteEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, SqlhelperConfiguration configuration) {
        super(databaseType, tableName, tableHelperClass, configuration);
    }

    public AbstractDeleteEngine(DatabaseType databaseType, Class<T> tableHelperClass, String tableAlias) {
        super(databaseType, tableHelperClass, tableAlias);
    }

    public AbstractDeleteEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        super(databaseType, tableName, tableHelperClass, tableAlias);
    }

    public AbstractDeleteEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlhelperConfiguration configuration) {
        super(databaseType, tableName, tableHelperClass, tableAlias, configuration);
    }

    @Override
    public DeleteSqlBuilderResult delete() {
        return this.crudSqlBuilder.delete();
    }

    @Override
    public DeleteSqlBuilderResult deleteByPrimaryKey(Object primaryKeyValue) {
        return this.crudSqlBuilder.deleteByPrimaryKey(primaryKeyValue);
    }

    @Override
    public DeleteSqlBuilderResult batchDeleteByPrimaryKeys(Object... primaryKeyValues) {
        return this.crudSqlBuilder.batchDeleteByPrimaryKeys(primaryKeyValues);
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
    public void addTableWhereDataBlock(TableWhereDataBlock tableWhereDataBlock) {
        this.dataStore.addTableWhereDataBlock(tableWhereDataBlock);
    }
}