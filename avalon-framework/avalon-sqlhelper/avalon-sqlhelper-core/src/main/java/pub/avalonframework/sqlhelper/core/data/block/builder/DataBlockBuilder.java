package pub.avalonframework.sqlhelper.core.data.block.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.ColumnHandler;
import pub.avalonframework.sqlhelper.core.data.block.DataBlock;
import pub.avalonframework.sqlhelper.core.helper.Helper;

import java.util.List;

/**
 * @author baichao
 */
public interface DataBlockBuilder<T extends Helper, S extends DataBlock> {

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

    /**
     * Get helper.
     *
     * @return extends {@link Helper} object
     */
    T getHelper();

    /**
     * Set helper.
     *
     * @param helper extends {@link Helper} object
     */
    void setHelper(T helper);

    /**
     * Add data block.
     *
     * @param dataBlock implements {@link DataBlock} object
     */
    void addDataBlock(S dataBlock);

    /**
     * Clean up after each takeout.
     *
     * @return implements {@link DataBlock} objects
     */
    List<S> takeoutDataBlocks();

    /**
     * Accept data block.
     *
     * @param templateTableName  template table name
     * @param templateTableAlias template table alias
     * @param sqlPart            sql part
     */
    void accept(String templateTableName, String templateTableAlias, String sqlPart);

    /**
     * Accept data block.
     *
     * @param templateTableName   template table name
     * @param templateTableAlias  template table alias
     * @param templateColumnName  template column name
     * @param templateColumnAlias template column alias
     * @param mappingFieldName    mapping field name
     * @param columnHandlers      {@link ColumnHandler}
     */
    void accept(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers);

    /**
     * Get sql builder configuration.
     *
     * @return {@link SqlBuilderConfiguration}
     */
    SqlBuilderConfiguration getSqlBuilderConfiguration();

    /**
     * Set sql builder configuration.
     *
     * @param sqlBuilderConfiguration {@link SqlBuilderConfiguration}
     */
    void setSqlBuilderConfiguration(SqlBuilderConfiguration sqlBuilderConfiguration);
}