package pub.avalonframework.sqlhelper.core.data.block.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.beans.SortType;
import pub.avalonframework.sqlhelper.core.data.block.SortDataBlock;
import pub.avalonframework.sqlhelper.core.helper.Helper;

/**
 * @author baichao
 */
public final class SortDataBlockBuilder<T extends Helper> extends AbstractDataBlockBuilder<T, SortDataBlock> {

    private SortDataBlock sortDataBlock;

    private SqlBuilderConfiguration sqlBuilderConfiguration;

    public SortDataBlockBuilder(String tableAlias, T helper) {
        super(tableAlias, helper);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String sqlPart) {
        this.sortDataBlock = new SortDataBlock(templateTableName, templateTableAlias, sqlPart)
                .setTableAlias(this.tableAlias);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {
        this.sortDataBlock = new SortDataBlock(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName)
                .setTableAlias(this.tableAlias);
    }

    @Override
    public SqlBuilderConfiguration getSqlBuilderConfiguration() {
        return sqlBuilderConfiguration;
    }

    @Override
    public void setSqlBuilderConfiguration(SqlBuilderConfiguration sqlBuilderConfiguration) {
        this.sqlBuilderConfiguration = sqlBuilderConfiguration;
    }

    public T asc() {
        this.sortDataBlock.setSortType(SortType.ASC);
        this.addDataBlock(this.sortDataBlock);
        return this.getHelper();
    }

    public T desc() {
        this.sortDataBlock.setSortType(SortType.DESC);
        this.addDataBlock(this.sortDataBlock);
        return this.getHelper();
    }
}