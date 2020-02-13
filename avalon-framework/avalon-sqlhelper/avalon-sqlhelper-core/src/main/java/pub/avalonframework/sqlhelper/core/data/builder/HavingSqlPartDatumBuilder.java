package pub.avalonframework.sqlhelper.core.data.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.beans.GroupType;
import pub.avalonframework.sqlhelper.core.data.AbstractComparisonSqlPartDatum;
import pub.avalonframework.sqlhelper.core.data.block.HavingDataBlock;
import pub.avalonframework.sqlhelper.core.helper.Helper;
import pub.avalonframework.sqlhelper.core.rules.GroupByOperator;
import pub.avalonframework.sqlhelper.core.rules.HavingComparisonOperator;

/**
 * @author baichao
 */
public final class HavingSqlPartDatumBuilder<T extends Helper> extends AbstractComparisonSqlPartDatumBuilder<T, HavingDataBlock> implements HavingComparisonOperator<T>, GroupByOperator<HavingSqlPartDatumBuilder<T>> {

    private HavingDataBlock havingDataBlock;

    private SqlBuilderConfiguration sqlBuilderConfiguration;

    public HavingSqlPartDatumBuilder(String tableAlias, T helper) {
        super(tableAlias, helper);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String sqlPart) {
        this.havingDataBlock = new HavingDataBlock(templateTableName, templateTableAlias, sqlPart)
                .setTableAlias(this.tableAlias);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {
        this.havingDataBlock = new HavingDataBlock(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName)
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
    public void addAbstractComparisonSqlPartDatum(AbstractComparisonSqlPartDatum<HavingDataBlock> abstractComparisonSqlPartDatum) {
        super.addDataBlock((HavingDataBlock) abstractComparisonSqlPartDatum);
    }

    @Override
    public AbstractComparisonSqlPartDatum<HavingDataBlock> getAbstractComparisonSqlPartDatum() {
        return this.havingDataBlock;
    }

    @Override
    public AbstractComparisonSqlPartDatum<HavingDataBlock> getCloneComparisonSqlPartDatum() {
        return this.havingDataBlock.getCloneComparisonSqlPartDatum();
    }

    @Override
    public HavingSqlPartDatumBuilder<T> min() {
        this.havingDataBlock.setColumnHandler(GroupType.MIN);
        return this;
    }

    @Override
    public HavingSqlPartDatumBuilder<T> max() {
        this.havingDataBlock.setColumnHandler(GroupType.MAX);
        return this;
    }

    @Override
    public HavingSqlPartDatumBuilder<T> count() {
        this.havingDataBlock.setColumnHandler(GroupType.COUNT);
        return this;
    }

    @Override
    public HavingSqlPartDatumBuilder<T> sum() {
        this.havingDataBlock.setColumnHandler(GroupType.SUM);
        return this;
    }

    @Override
    public HavingSqlPartDatumBuilder<T> avg() {
        this.havingDataBlock.setColumnHandler(GroupType.AVG);
        return this;
    }

    @Override
    public HavingSqlPartDatumBuilder<T> stddev() {
        this.havingDataBlock.setColumnHandler(GroupType.STDDEV);
        return this;
    }

    @Override
    public HavingSqlPartDatumBuilder<T> variance() {
        this.havingDataBlock.setColumnHandler(GroupType.VARIANCE);
        return this;
    }
}