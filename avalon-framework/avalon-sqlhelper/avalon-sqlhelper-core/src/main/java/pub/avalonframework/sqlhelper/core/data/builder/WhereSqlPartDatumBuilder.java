package pub.avalonframework.sqlhelper.core.data.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.data.AbstractComparisonSqlPartDatum;
import pub.avalonframework.sqlhelper.core.data.block.WhereDataBlock;
import pub.avalonframework.sqlhelper.core.helper.Helper;
import pub.avalonframework.sqlhelper.core.rules.WhereComparisonOperator;

/**
 * @author baichao
 */
public final class WhereSqlPartDatumBuilder<T extends Helper> extends AbstractComparisonSqlPartDatumBuilder<T, WhereDataBlock> implements WhereComparisonOperator<T> {

    private WhereDataBlock whereDatum;

    private SqlBuilderConfiguration sqlBuilderConfiguration;

    public WhereSqlPartDatumBuilder(String tableAlias, T helper) {
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
    public void addAbstractComparisonSqlPartDatum(AbstractComparisonSqlPartDatum<WhereDataBlock> abstractComparisonSqlPartDatum) {
        super.addDataBlock((WhereDataBlock) abstractComparisonSqlPartDatum);
    }

    @Override
    public AbstractComparisonSqlPartDatum<WhereDataBlock> getAbstractComparisonSqlPartDatum() {
        return this.whereDatum;
    }

    @Override
    public AbstractComparisonSqlPartDatum<WhereDataBlock> getCloneComparisonSqlPartDatum() {
        return this.whereDatum.getCloneComparisonSqlPartDatum();
    }
}