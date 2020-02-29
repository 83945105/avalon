package pub.avalonframework.sqlhelper.core.data.block.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.ColumnHandler;
import pub.avalonframework.sqlhelper.core.data.block.GroupDataBlock;
import pub.avalonframework.sqlhelper.core.helper.Helper;

/**
 * @author baichao
 */
public final class GroupDataBlockBuilder<T extends Helper> extends AbstractDataBlockBuilder<T, GroupDataBlock> {

    private SqlBuilderConfiguration sqlBuilderConfiguration;

    public GroupDataBlockBuilder(String tableAlias, T helper) {
        super(tableAlias, helper);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String sqlPart) {
        this.addDataBlock(new GroupDataBlock(templateTableName, templateTableAlias, sqlPart)
                .setTableAlias(this.tableAlias));
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {
        this.addDataBlock(new GroupDataBlock(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias)
                .setTableAlias(this.tableAlias));
    }

    @Override
    public SqlBuilderConfiguration getSqlBuilderConfiguration() {
        return sqlBuilderConfiguration;
    }

    @Override
    public void setSqlBuilderConfiguration(SqlBuilderConfiguration sqlBuilderConfiguration) {
        this.sqlBuilderConfiguration = sqlBuilderConfiguration;
    }
}