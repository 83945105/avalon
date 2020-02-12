package pub.avalonframework.sqlhelper.core.data.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.beans.GroupType;
import pub.avalonframework.sqlhelper.core.data.AbstractComparisonSqlPartDatum;
import pub.avalonframework.sqlhelper.core.data.HavingDatum;
import pub.avalonframework.sqlhelper.core.helper.Helper;
import pub.avalonframework.sqlhelper.core.rules.GroupByOperator;
import pub.avalonframework.sqlhelper.core.rules.HavingComparisonOperator;

/**
 * @author baichao
 */
public final class HavingSqlPartDatumBuilder<T extends Helper> extends AbstractComparisonSqlPartDatumBuilder<T, HavingDatum> implements HavingComparisonOperator<T>, GroupByOperator<HavingSqlPartDatumBuilder<T>> {

    private HavingDatum havingDatum;

    private SqlBuilderConfiguration sqlBuilderConfiguration;

    public HavingSqlPartDatumBuilder(String tableAlias, T helper) {
        super(tableAlias, helper);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String sqlPart) {
        this.havingDatum = new HavingDatum(templateTableName, templateTableAlias, sqlPart)
                .setTableAlias(this.tableAlias);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {
        this.havingDatum = new HavingDatum(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName)
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
    public void addAbstractComparisonSqlPartDatum(AbstractComparisonSqlPartDatum<HavingDatum> abstractComparisonSqlPartDatum) {
        super.addSqlPartDatum((HavingDatum) abstractComparisonSqlPartDatum);
    }

    @Override
    public AbstractComparisonSqlPartDatum<HavingDatum> getAbstractComparisonSqlPartDatum() {
        return this.havingDatum;
    }

    @Override
    public AbstractComparisonSqlPartDatum<HavingDatum> getCloneComparisonSqlPartDatum() {
        return this.havingDatum.getCloneComparisonSqlPartDatum();
    }

    @Override
    public HavingSqlPartDatumBuilder<T> min() {
        this.havingDatum.setColumnHandler(GroupType.MIN);
        return this;
    }

    @Override
    public HavingSqlPartDatumBuilder<T> max() {
        this.havingDatum.setColumnHandler(GroupType.MAX);
        return this;
    }

    @Override
    public HavingSqlPartDatumBuilder<T> count() {
        this.havingDatum.setColumnHandler(GroupType.COUNT);
        return this;
    }

    @Override
    public HavingSqlPartDatumBuilder<T> sum() {
        this.havingDatum.setColumnHandler(GroupType.SUM);
        return this;
    }

    @Override
    public HavingSqlPartDatumBuilder<T> avg() {
        this.havingDatum.setColumnHandler(GroupType.AVG);
        return this;
    }

    @Override
    public HavingSqlPartDatumBuilder<T> stddev() {
        this.havingDatum.setColumnHandler(GroupType.STDDEV);
        return this;
    }

    @Override
    public HavingSqlPartDatumBuilder<T> variance() {
        this.havingDatum.setColumnHandler(GroupType.VARIANCE);
        return this;
    }
}