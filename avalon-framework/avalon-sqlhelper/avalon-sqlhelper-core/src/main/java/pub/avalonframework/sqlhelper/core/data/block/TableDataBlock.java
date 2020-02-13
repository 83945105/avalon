package pub.avalonframework.sqlhelper.core.data.block;

import pub.avalonframework.sqlhelper.core.helper.TableHelper;

/**
 * @author baichao
 */
public interface TableDataBlock {

    /**
     * Get extends {@link TableHelper} class.
     *
     * @return {@link TableHelper}
     */
    Class<?> getTableHelperClass();

    /**
     * Get table name.
     *
     * @return table name
     */
    String getTableName();

    /**
     * Set table name.
     *
     * @param tableName table name
     */
    void setTableName(String tableName);

    /**
     * Get table alias.
     *
     * @return table alias
     */
    String getTableAlias();

    /**
     * Set table alias.
     *
     * @param tableAlias table alias
     */
    void setTableAlias(String tableAlias);
}