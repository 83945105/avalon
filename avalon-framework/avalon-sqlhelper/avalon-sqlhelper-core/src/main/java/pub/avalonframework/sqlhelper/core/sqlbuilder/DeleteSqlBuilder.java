package pub.avalonframework.sqlhelper.core.sqlbuilder;

import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.DeleteSqlBuilderResult;

/**
 * @author baichao
 */
public interface DeleteSqlBuilder extends SqlBuilder {

    /**
     * Delete
     *
     * @return {@link DeleteSqlBuilderResult}
     */
    DeleteSqlBuilderResult delete();

    /**
     * Delete by primary key
     *
     * @param primaryKeyValue primary key value
     * @return {@link DeleteSqlBuilderResult}
     */
    DeleteSqlBuilderResult deleteByPrimaryKey(Object primaryKeyValue);

    /**
     * Batch delete by primary keys
     *
     * @param primaryKeyValues primary key values
     * @return {@link DeleteSqlBuilderResult}
     */
    DeleteSqlBuilderResult batchDeleteByPrimaryKeys(Object... primaryKeyValues);
}