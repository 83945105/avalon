package pub.avalonframework.sqlhelper.core.data.block.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.data.block.AbstractComparisonDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.OnDataBlock;
import pub.avalonframework.sqlhelper.core.helper.Helper;
import pub.avalonframework.sqlhelper.core.rules.OnComparisonOperator;

/**
 * @author baichao
 */
public final class OnDataBlockBuilder<T extends Helper> extends AbstractComparisonDataBlockBuilder<T, OnDataBlock> implements OnComparisonOperator<T> {

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
    public void addAbstractComparisonSqlPartDatum(AbstractComparisonDataBlock<OnDataBlock> abstractComparisonSqlPartDatum) {
        super.addDataBlock((OnDataBlock) abstractComparisonSqlPartDatum);
    }

    @Override
    public AbstractComparisonDataBlock<OnDataBlock> getAbstractComparisonSqlPartDatum() {
        return this.onDataBlock;
    }

    @Override
    public AbstractComparisonDataBlock<OnDataBlock> getCloneComparisonSqlPartDatum() {
        return this.onDataBlock.getCloneComparisonSqlPartDatum();
    }
}