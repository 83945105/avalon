package pub.avalonframework.sqlhelper.core.data.block.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.data.block.AbstractComparisonDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.WhereDataBlock;
import pub.avalonframework.sqlhelper.core.helper.Helper;
import pub.avalonframework.sqlhelper.core.rules.WhereComparisonOperator;

/**
 * @author baichao
 */
public final class WhereDataBlockBuilder<T extends Helper> extends AbstractComparisonDataBlockBuilder<T, WhereDataBlock> implements WhereComparisonOperator<T> {

    private WhereDataBlock whereDatum;

    private SqlBuilderConfiguration sqlBuilderConfiguration;

    public WhereDataBlockBuilder(String tableAlias, T helper) {
        super(tableAlias, helper);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String sqlPart) {
        this.whereDatum = new WhereDataBlock(templateTableName, templateTableAlias, sqlPart)
                .setTableAlias(this.tableAlias);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {
        this.whereDatum = new WhereDataBlock(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName)
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
    public void addAbstractComparisonSqlPartDatum(AbstractComparisonDataBlock<WhereDataBlock> abstractComparisonSqlPartDatum) {
        super.addDataBlock((WhereDataBlock) abstractComparisonSqlPartDatum);
    }

    @Override
    public AbstractComparisonDataBlock<WhereDataBlock> getAbstractComparisonSqlPartDatum() {
        return this.whereDatum;
    }

    @Override
    public AbstractComparisonDataBlock<WhereDataBlock> getCloneComparisonSqlPartDatum() {
        return this.whereDatum.getCloneComparisonSqlPartDatum();
    }
}