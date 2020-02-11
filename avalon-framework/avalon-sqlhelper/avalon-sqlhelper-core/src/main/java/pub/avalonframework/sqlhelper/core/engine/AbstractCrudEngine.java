package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.database.DatabaseType;
import pub.avalonframework.sqlhelper.core.data.*;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;
import pub.avalonframework.sqlhelper.core.sqlbuilder.AbstractSqlCrudBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.SqlCrudBuilder;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.*;
import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.Collection;

/**
 * @author baichao
 */
public abstract class AbstractCrudEngine<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
        TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends AbstractEngine<T, TC, TO, TW, TG, TH, TS> implements
        SqlCrudBuilder, SqlDataCrudProducer {

    public AbstractCrudEngine(DatabaseType databaseType, Class<T> tableHelperClass) {
        super(databaseType, tableHelperClass);
    }

    public AbstractCrudEngine(DatabaseType databaseType, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        super(databaseType, tableHelperClass, sqlBuilderOptions);
    }

    public AbstractCrudEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass) {
        super(databaseType, tableName, tableHelperClass);
    }

    public AbstractCrudEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, SqlBuilderOptions sqlBuilderOptions) {
        super(databaseType, tableName, tableHelperClass, sqlBuilderOptions);
    }

    public AbstractCrudEngine(DatabaseType databaseType, Class<T> tableHelperClass, String tableAlias) {
        super(databaseType, tableHelperClass, tableAlias);
    }

    public AbstractCrudEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias) {
        super(databaseType, tableName, tableHelperClass, tableAlias);
    }

    public AbstractCrudEngine(DatabaseType databaseType, String tableName, Class<T> tableHelperClass, String tableAlias, SqlBuilderOptions sqlBuilderOptions) {
        super(databaseType, tableName, tableHelperClass, tableAlias, sqlBuilderOptions);
    }

    @Override
    public TableSqlBuilderResult copyTable(String targetTableName, boolean copyData) {
        return this.sqlCrudBuilder.copyTable(targetTableName, copyData);
    }

    @Override
    public TableSqlBuilderResult deleteTable() {
        return this.sqlCrudBuilder.deleteTable();
    }

    @Override
    public TableSqlBuilderResult renameTable(String newTableName) {
        return this.sqlCrudBuilder.renameTable(newTableName);
    }

    @Override
    public TableSqlBuilderResult isTableExist() {
        return this.sqlCrudBuilder.isTableExist();
    }

    @Override
    public InsertSqlBuilderResult insertArgs(Object... args) {
        return this.sqlCrudBuilder.insertArgs(args);
    }

    @Override
    public InsertSqlBuilderResult insertJavaBean(Object javaBean) {
        return this.sqlCrudBuilder.insertJavaBean(javaBean);
    }

    @Override
    public InsertSqlBuilderResult insertJavaBeanSelective(Object javaBean) {
        return this.sqlCrudBuilder.insertJavaBeanSelective(javaBean);
    }

    @Override
    public InsertSqlBuilderResult batchInsertJavaBeans(Collection<?> javaBeans) {
        return this.sqlCrudBuilder.batchInsertJavaBeans(javaBeans);
    }

    @Override
    public DeleteSqlBuilderResult delete() {
        return this.sqlCrudBuilder.delete();
    }

    @Override
    public DeleteSqlBuilderResult deleteByPrimaryKey(Object primaryKeyValue) {
        return this.sqlCrudBuilder.deleteByPrimaryKey(primaryKeyValue);
    }

    @Override
    public DeleteSqlBuilderResult batchDeleteByPrimaryKeys(Object... primaryKeyValues) {
        return this.sqlCrudBuilder.batchDeleteByPrimaryKeys(primaryKeyValues);
    }

    @Override
    public UpdateSqlBuilderResult updateJavaBean(Object javaBean) {
        return this.sqlCrudBuilder.updateJavaBean(javaBean);
    }

    @Override
    public UpdateSqlBuilderResult updateJavaBeanSelective(Object javaBean) {
        return this.sqlCrudBuilder.updateJavaBeanSelective(javaBean);
    }

    @Override
    public UpdateSqlBuilderResult updateArgsByPrimaryKey(Object primaryKeyValue, Object... args) {
        return this.sqlCrudBuilder.updateArgsByPrimaryKey(primaryKeyValue, args);
    }

    @Override
    public UpdateSqlBuilderResult updateJavaBeanByPrimaryKey(Object primaryKeyValue, Object javaBean) {
        return this.sqlCrudBuilder.updateJavaBeanByPrimaryKey(primaryKeyValue, javaBean);
    }

    @Override
    public UpdateSqlBuilderResult updateJavaBeanByPrimaryKeySelective(Object primaryKeyValue, Object javaBean) {
        return this.sqlCrudBuilder.updateJavaBeanByPrimaryKeySelective(primaryKeyValue, javaBean);
    }

    @Override
    public UpdateSqlBuilderResult batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans) {
        return this.sqlCrudBuilder.batchUpdateJavaBeansByPrimaryKeys(javaBeans);
    }

    @Override
    public UpdateSqlBuilderResult updateOrInsertJavaBeans(Collection<?> javaBeans) {
        return this.sqlCrudBuilder.updateOrInsertJavaBeans(javaBeans);
    }

    @Override
    public SelectSqlBuilderResult query() {
        return this.sqlCrudBuilder.query();
    }

    @Override
    public SelectSqlBuilderResult queryCount() {
        return this.sqlCrudBuilder.queryCount();
    }

    @Override
    public SelectSqlBuilderResult queryByPrimaryKey(Object primaryKeyValue) {
        return this.sqlCrudBuilder.queryByPrimaryKey(primaryKeyValue);
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