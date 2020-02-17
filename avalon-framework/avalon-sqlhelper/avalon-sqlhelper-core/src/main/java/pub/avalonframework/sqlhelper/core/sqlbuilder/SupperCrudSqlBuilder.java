package pub.avalonframework.sqlhelper.core.sqlbuilder;

import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.data.store.DataStore;
import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.*;
import pub.avalonframework.sqlhelper.core.sqlbuilder.template.SqlBuilderTemplate;

import java.util.Collection;

/**
 * @author baichao
 */
public class SupperCrudSqlBuilder implements CrudSqlBuilder {

    private DataStore dataStore;

    SqlBuilderTemplate sqlBuilderTemplate;

    SupperCrudSqlBuilder(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public TableSqlBuilderResult copyTable(String targetTableName, boolean copyData) {
        return this.sqlBuilderTemplate.buildCopyTable(this.dataStore, targetTableName, copyData);
    }

    @Override
    public TableSqlBuilderResult deleteTable() {
        return this.sqlBuilderTemplate.buildDeleteTable(this.dataStore);
    }

    @Override
    public TableSqlBuilderResult renameTable(String newTableName) {
        return this.sqlBuilderTemplate.buildRenameTable(this.dataStore, newTableName);
    }

    @Override
    public TableSqlBuilderResult isTableExist() {
        return this.sqlBuilderTemplate.buildIsTableExist(this.dataStore);
    }

    @Override
    public InsertSqlBuilderResult insertJavaBean(Object javaBean) {
        return this.sqlBuilderTemplate.buildInsertJavaBean(this.dataStore, javaBean);
    }

    @Override
    public InsertSqlBuilderResult insertJavaBeanSelective(Object javaBean) {
        return this.sqlBuilderTemplate.buildInsertJavaBeanSelective(this.dataStore, javaBean);
    }

    @Override
    public InsertSqlBuilderResult batchInsertJavaBeans(Collection<?> javaBeans) {
        return this.sqlBuilderTemplate.buildBatchInsertJavaBeans(this.dataStore, javaBeans);
    }

    @Override
    public DeleteSqlBuilderResult delete() {
        return this.sqlBuilderTemplate.buildDelete(this.dataStore);
    }

    @Override
    public DeleteSqlBuilderResult deleteByPrimaryKey(Object primaryKeyValue) {
        return this.sqlBuilderTemplate.buildDeleteByPrimaryKey(this.dataStore, primaryKeyValue);
    }

    @Override
    public DeleteSqlBuilderResult batchDeleteByPrimaryKeys(Object... primaryKeyValues) {
        return this.sqlBuilderTemplate.buildBatchDeleteByPrimaryKeys(this.dataStore, primaryKeyValues);
    }

    @Override
    public UpdateSqlBuilderResult updateJavaBean(Object javaBean) {
        return this.sqlBuilderTemplate.buildUpdateJavaBean(this.dataStore, javaBean);
    }

    @Override
    public UpdateSqlBuilderResult updateJavaBeanSelective(Object javaBean) {
        return this.sqlBuilderTemplate.buildUpdateJavaBeanSelective(this.dataStore, javaBean);
    }

    @Override
    public UpdateSqlBuilderResult updateArgsByPrimaryKey(Object primaryKeyValue, Object... args) {
        return this.sqlBuilderTemplate.buildUpdateArgsByPrimaryKey(this.dataStore, primaryKeyValue, args);
    }

    @Override
    public UpdateSqlBuilderResult updateJavaBeanByPrimaryKey(Object primaryKeyValue, Object javaBean) {
        return this.sqlBuilderTemplate.buildUpdateJavaBeanByPrimaryKey(this.dataStore, primaryKeyValue, javaBean);
    }

    @Override
    public UpdateSqlBuilderResult updateJavaBeanByPrimaryKeySelective(Object primaryKeyValue, Object javaBean) {
        return this.sqlBuilderTemplate.buildUpdateJavaBeanByPrimaryKeySelective(this.dataStore, primaryKeyValue, javaBean);
    }

    @Override
    public UpdateSqlBuilderResult batchUpdateJavaBeansByPrimaryKeys(Collection<?> javaBeans) {
        return this.sqlBuilderTemplate.buildBatchUpdateJavaBeansByPrimaryKeys(this.dataStore, javaBeans);
    }

    @Override
    public UpdateSqlBuilderResult updateOrInsertJavaBeans(Collection<?> javaBeans) {
        return this.sqlBuilderTemplate.buildUpdateOrInsertJavaBeans(this.dataStore, javaBeans);
    }

    @Override
    public SelectSqlBuilderResult query() {
        return this.sqlBuilderTemplate.buildQuery(this.dataStore);
    }

    @Override
    public SelectSqlBuilderResult queryCount() {
        return this.sqlBuilderTemplate.buildQueryCount(this.dataStore);
    }

    @Override
    public SelectSqlBuilderResult queryByPrimaryKey(Object primaryKeyValue) {
        return this.sqlBuilderTemplate.buildQueryByPrimaryKey(this.dataStore, primaryKeyValue);
    }

    @Override
    public SqlhelperConfiguration getConfiguration() {
        return this.dataStore.getConfiguration();
    }
}