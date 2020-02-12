package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.sqlbuilder.TableSqlBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.TableSqlBuilderResult;

/**
 * @author baichao
 */
public abstract class AbstractTableEngine<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
        TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends AbstractEngine<T, TC, TO, TW, TG, TH, TS> implements TableSqlBuilder {

    public AbstractTableEngine(DatabaseType databaseType, Class<T> tableHelperClass) {
        super(databaseType, tableHelperClass);
    }

    public AbstractTableEngine(DatabaseType databaseType, Class<T> tableHelperClass, SqlhelperConfiguration sqlhelperConfiguration) {
        super(databaseType, tableHelperClass, sqlhelperConfiguration);
    }

    public AbstractTableEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass) {
        super(databaseType, tableName, tableHelperClass);
    }

    public AbstractTableEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, SqlhelperConfiguration sqlhelperConfiguration) {
        super(databaseType, tableName, tableHelperClass, sqlhelperConfiguration);
    }

    public AbstractTableEngine(DatabaseType databaseType, Class<T> tableHelperClass, String tableAlias) {
        super(databaseType, tableHelperClass, tableAlias);
    }

    public AbstractTableEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        super(databaseType, tableName, tableHelperClass, tableAlias);
    }

    public AbstractTableEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlhelperConfiguration sqlhelperConfiguration) {
        super(databaseType, tableName, tableHelperClass, tableAlias, sqlhelperConfiguration);
    }

    @Override
    public TableSqlBuilderResult copyTable(String targetTableName, boolean copyData) {
        return this.crudSqlBuilder.copyTable(targetTableName, copyData);
    }

    @Override
    public TableSqlBuilderResult deleteTable() {
        return this.crudSqlBuilder.deleteTable();
    }

    @Override
    public TableSqlBuilderResult renameTable(String newTableName) {
        return this.crudSqlBuilder.renameTable(newTableName);
    }

    @Override
    public TableSqlBuilderResult isTableExist() {
        return this.crudSqlBuilder.isTableExist();
    }

    public DatabaseType getDatabaseType() {
        return this.sqlData.getDatabaseType();
    }
}