package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.data.*;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalonframework.sqlhelper.core.sqlbuilder.AbstractSqlBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.SqlBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.*;
import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.Collection;

/**
 * @author baichao
 */
public abstract class AbstractEngine<T extends TableHelper<T, TO, TC, TW, TG, TH, TS>,
        TO extends OnHelper<TO>,
        TC extends ColumnHelper<TC>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> implements Engine, SqlBuilder, SqlDataProducer {

    protected Class<T> tableHelperClass;

    protected T tableHelper;

    protected String tableName;

    protected String tableAlias;

    protected MainTableDatum mainTableDatum;

    protected SqlData sqlData;

    protected SqlBuilderOptions sqlBuilderOptions;

    protected SqlBuilder sqlBuilder;

    public AbstractEngine(DatabaseType databaseType, Class<T> tableHelperClass) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        this.tableHelperClass = tableHelperClass;
        this.tableHelper = HelperManager.newTableHelper(tableHelperClass);
        this.tableName = this.tableHelper.getTableName();
        this.tableAlias = this.tableHelper.getTableAlias();
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableName, this.tableAlias);
        this.sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;
        this.sqlData = new FinalSqlData(databaseType, this.mainTableDatum, this.sqlBuilderOptions);
        this.sqlBuilder = new AbstractSqlBuilder(this.sqlData, this.sqlBuilderOptions) {
        };
    }

    public AbstractEngine(DatabaseType databaseType, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        this.tableHelperClass = tableHelperClass;
        this.tableHelper = HelperManager.newTableHelper(tableHelperClass);
        this.tableName = this.tableHelper.getTableName();
        this.tableAlias = this.tableHelper.getTableAlias();
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableName, this.tableAlias);
        this.sqlBuilderOptions = sqlBuilderOptions;
        this.sqlData = new FinalSqlData(databaseType, this.mainTableDatum, this.sqlBuilderOptions);
        this.sqlBuilder = new AbstractSqlBuilder(this.sqlData, this.sqlBuilderOptions) {
        };
    }

    public AbstractEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        this.tableHelperClass = tableHelperClass;
        this.tableHelper = HelperManager.newTableHelper(tableHelperClass);
        this.tableName = tableName;
        this.tableAlias = this.tableHelper.getTableAlias();
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableName, this.tableAlias);
        this.sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;
        this.sqlData = new FinalSqlData(databaseType, this.mainTableDatum, this.sqlBuilderOptions);
        this.sqlBuilder = new AbstractSqlBuilder(this.sqlData, this.sqlBuilderOptions) {
        };
    }

    public AbstractEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        this.tableHelperClass = tableHelperClass;
        this.tableHelper = HelperManager.newTableHelper(tableHelperClass);
        this.tableName = tableName;
        this.tableAlias = this.tableHelper.getTableAlias();
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableName, this.tableAlias);
        this.sqlBuilderOptions = sqlBuilderOptions;
        this.sqlData = new FinalSqlData(databaseType, this.mainTableDatum, this.sqlBuilderOptions);
        this.sqlBuilder = new AbstractSqlBuilder(this.sqlData, this.sqlBuilderOptions) {
        };
    }

    public AbstractEngine(DatabaseType databaseType, Class<T> tableHelperClass, String tableAlias) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
        }
        this.tableHelperClass = tableHelperClass;
        this.tableHelper = HelperManager.newTableHelper(tableHelperClass);
        this.tableName = this.tableHelper.getTableName();
        this.tableAlias = tableAlias;
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableName, this.tableAlias);
        this.sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;
        this.sqlData = new FinalSqlData(databaseType, this.mainTableDatum, this.sqlBuilderOptions);
        this.sqlBuilder = new AbstractSqlBuilder(this.sqlData, this.sqlBuilderOptions) {
        };
    }

    public AbstractEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
        }
        this.tableHelperClass = tableHelperClass;
        this.tableHelper = HelperManager.newTableHelper(tableHelperClass);
        this.tableName = tableName;
        this.tableAlias = tableAlias;
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableName, this.tableAlias);
        this.sqlBuilderOptions = SqlBuilderOptions.DEFAULT_SQL_BUILDER_OPTIONS;
        this.sqlData = new FinalSqlData(databaseType, this.mainTableDatum, this.sqlBuilderOptions);
        this.sqlBuilder = new AbstractSqlBuilder(this.sqlData, this.sqlBuilderOptions) {
        };
    }

    public AbstractEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlBuilderOptions sqlBuilderOptions) {
        if (tableHelperClass == null) {
            ExceptionUtils.tableHelperClassNullException();
        }
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
        }
        this.tableHelperClass = tableHelperClass;
        this.tableHelper = HelperManager.newTableHelper(tableHelperClass);
        this.tableName = tableName;
        this.tableAlias = tableAlias;
        this.mainTableDatum = new MainTableDatum(tableHelperClass, this.tableName, this.tableAlias);
        this.sqlBuilderOptions = sqlBuilderOptions;
        this.sqlData = new FinalSqlData(databaseType, this.mainTableDatum, this.sqlBuilderOptions);
        this.sqlBuilder = new AbstractSqlBuilder(this.sqlData, this.sqlBuilderOptions) {
        };
    }

    @Override
    public TableSqlBuilderResult copyTable(String targetTableName, boolean copyData) {
        return this.sqlBuilder.copyTable(targetTableName, copyData);
    }

    @Override
    public TableSqlBuilderResult deleteTable() {
        return this.sqlBuilder.deleteTable();
    }

    @Override
    public TableSqlBuilderResult renameTable(String newTableName) {
        return this.sqlBuilder.renameTable(newTableName);
    }

    @Override
    public TableSqlBuilderResult isTableExist() {
        return this.sqlBuilder.isTableExist();
    }

    @Override
    public InsertSqlBuilderResult insertArgs(Object... args) {
        return this.sqlBuilder.insertArgs(args);
    }

    @Override
    public InsertSqlBuilderResult insertJavaBean(Object javaBean) {
        return this.sqlBuilder.insertJavaBean(javaBean);
    }

    @Override
    public InsertSqlBuilderResult insertJavaBeanSelective(Object javaBean) {
        return this.sqlBuilder.insertJavaBeanSelective(javaBean);
    }

    @Override
    public InsertSqlBuilderResult batchInsertJavaBeans(Collection<?> javaBeans) {
        return this.sqlBuilder.batchInsertJavaBeans(javaBeans);
    }

    @Override
    public DeleteSqlBuilderResult delete() {
        return this.sqlBuilder.delete();
    }

    @Override
    public DeleteSqlBuilderResult deleteByPrimaryKey(Object primaryKeyValue) {
        return this.sqlBuilder.deleteByPrimaryKey(primaryKeyValue);
    }

    @Override
    public DeleteSqlBuilderResult batchDeleteByPrimaryKeys(Object... primaryKeyValues) {
        return this.sqlBuilder.batchDeleteByPrimaryKeys(primaryKeyValues);
    }

    @Override
    public UpdateSqlBuilderResult updateJavaBean(Object javaBean) {
        return this.sqlBuilder.updateJavaBean(javaBean);
    }

    @Override
    public UpdateSqlBuilderResult updateJavaBeanSelective(Object javaBean) {
        return this.sqlBuilder.updateJavaBeanSelective(javaBean);
    }

    @Override
    public UpdateSqlBuilderResult updateArgsByPrimaryKey(Object primaryKeyValue, Object... args) {
        return this.sqlBuilder.updateArgsByPrimaryKey(primaryKeyValue, args);
    }

    @Override
    public UpdateSqlBuilderResult updateJavaBeanByPrimaryKey(Object primaryKeyValue, Object javaBean) {
        return this.sqlBuilder.updateJavaBeanByPrimaryKey(primaryKeyValue, javaBean);
    }

    @Override
    public UpdateSqlBuilderResult updateJavaBeanByPrimaryKeySelective(Object primaryKeyValue, Object javaBean) {
        return this.sqlBuilder.updateJavaBeanByPrimaryKeySelective(primaryKeyValue, javaBean);
    }

    @Override
    public UpdateSqlBuilderResult batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans) {
        return this.sqlBuilder.batchUpdateJavaBeansByPrimaryKeys(javaBeans);
    }

    @Override
    public UpdateSqlBuilderResult updateOrInsertJavaBeans(Collection<?> javaBeans) {
        return this.sqlBuilder.updateOrInsertJavaBeans(javaBeans);
    }

    @Override
    public SelectSqlBuilderResult query() {
        return this.sqlBuilder.query();
    }

    @Override
    public SelectSqlBuilderResult queryCount() {
        return this.sqlBuilder.queryCount();
    }

    @Override
    public SelectSqlBuilderResult queryByPrimaryKey(Object primaryKeyValue) {
        return this.sqlBuilder.queryByPrimaryKey(primaryKeyValue);
    }

    public DatabaseType getDatabaseType() {
        return this.sqlData.getDatabaseType();
    }

    @Override
    public void setSqlBuilderOptions(SqlBuilderOptions sqlBuilderOptions) {
        this.sqlBuilderOptions = sqlBuilderOptions;
    }

    @Override
    public void setDatabaseType(DatabaseType databaseType) {
        this.sqlData.setDatabaseType(databaseType);
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
    public void addInsertTableColumnDatum(TableColumnDatum tableColumnDatum) {
        this.sqlData.addInsertTableColumnDatum(tableColumnDatum);
    }

    @Override
    public void addUpdateTableColumnDatum(TableColumnDatum tableColumnDatum) {
        this.sqlData.addUpdateTableColumnDatum(tableColumnDatum);
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