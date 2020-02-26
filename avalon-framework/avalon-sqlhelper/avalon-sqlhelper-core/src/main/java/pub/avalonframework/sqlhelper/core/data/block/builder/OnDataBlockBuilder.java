package pub.avalonframework.sqlhelper.core.data.block.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.data.block.AbstractComparisonDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.OnDataBlock;
import pub.avalonframework.sqlhelper.core.helper.Helper;
import pub.avalonframework.sqlhelper.core.expression.OnComparisonExpression;

/**
 * @author baichao
 */
public final class OnDataBlockBuilder<T extends Helper> extends AbstractComparisonDataBlockBuilder<T, OnDataBlock> implements OnComparisonExpression<T> {

    private OnDataBlock onDataBlock;

    private SqlBuilderConfiguration sqlBuilderConfiguration;

    public OnDataBlockBuilder(String tableAlias, T helper) {
        super(tableAlias, helper);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String sqlPart) {
        this.onDataBlock = new OnDataBlock(templateTableName, templateTableAlias, sqlPart)
                .setTableAlias(this.tableAlias);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {
        this.onDataBlock = new OnDataBlock(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName)
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
    public void addComparisonDataBlock(AbstractComparisonDataBlock<OnDataBlock> comparisonDataBlock) {
        super.addDataBlock((OnDataBlock) comparisonDataBlock);
    }

    @Override
    public AbstractComparisonDataBlock<OnDataBlock> getComparisonDataBlock() {
        return this.onDataBlock;
    }

    @Override
    public AbstractComparisonDataBlock<OnDataBlock> getCloneComparisonDataBlock() {
        return this.onDataBlock.getCloneOnDataBlock();
    }
}