package pub.avalonframework.sqlhelper.core.data.block.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.data.block.AbstractComparisonDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.WhereDataBlock;
import pub.avalonframework.sqlhelper.core.helper.Helper;
import pub.avalonframework.sqlhelper.core.expression.WhereComparisonExpression;

/**
 * @author baichao
 */
public final class WhereDataBlockBuilder<T extends Helper> extends AbstractComparisonDataBlockBuilder<T, WhereDataBlock> implements WhereComparisonExpression<T> {

    private WhereDataBlock whereDataBlock;

    private SqlBuilderConfiguration sqlBuilderConfiguration;

    public WhereDataBlockBuilder(String tableAlias, T helper) {
        super(tableAlias, helper);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String sqlPart) {
        this.whereDataBlock = new WhereDataBlock(templateTableName, templateTableAlias, sqlPart)
                .setTableAlias(this.tableAlias);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {
        this.whereDataBlock = new WhereDataBlock(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName)
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

    @Override
    public void addComparisonDataBlock(AbstractComparisonDataBlock<WhereDataBlock> comparisonDataBlock) {
        super.addDataBlock((WhereDataBlock) comparisonDataBlock);
    }

    @Override
    public AbstractComparisonDataBlock<WhereDataBlock> getComparisonDataBlock() {
        return this.whereDataBlock;
    }

    @Override
    public AbstractComparisonDataBlock<WhereDataBlock> getCloneComparisonDataBlock() {
        return this.whereDataBlock.getCloneWhereDataBlock();
    }
}