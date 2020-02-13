package pub.avalonframework.sqlhelper.core.sqlbuilder;

import pub.avalonframework.sqlhelper.core.sqlbuilder.beans.TableSqlBuilderResult;

/**
 * @author baichao
 */
public interface TableSqlBuilder extends SqlBuilder {

    /**
     * Copy table
     *
     * @param targetTableName target table name
     * @param copyData        copy data or not
     * @return {@link TableSqlBuilderResult}
     */
    TableSqlBuilderResult copyTable(String targetTableName, boolean copyData);

    /**
     * Delete table
     *
     * @return {@link TableSqlBuilderResult}
     */
    TableSqlBuilderResult deleteTable();

    /**
     * Rename table
     *
     * @param newTableName new table name
     * @return {@link TableSqlBuilderResult}
     */
    TableSqlBuilderResult renameTable(String newTableName);

    /**
     * Is table exist
     *
     * @return {@link TableSqlBuilderResult}
     */
    TableSqlBuilderResult isTableExist();
}