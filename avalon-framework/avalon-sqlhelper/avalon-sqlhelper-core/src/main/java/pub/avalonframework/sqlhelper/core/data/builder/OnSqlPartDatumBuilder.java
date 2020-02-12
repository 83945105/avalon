package pub.avalonframework.sqlhelper.core.data.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.data.AbstractComparisonSqlPartDatum;
import pub.avalonframework.sqlhelper.core.data.OnDatum;
import pub.avalonframework.sqlhelper.core.helper.Helper;
import pub.avalonframework.sqlhelper.core.rules.OnComparisonOperator;

/**
 * @author baichao
 */
public final class OnSqlPartDatumBuilder<T extends Helper> extends AbstractComparisonSqlPartDatumBuilder<T, OnDatum> implements OnComparisonOperator<T> {

    private OnDatum onDatum;

    private SqlBuilderConfiguration sqlBuilderConfiguration;

    public OnSqlPartDatumBuilder(String tableAlias, T helper) {
        super(tableAlias, helper);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String sqlPart) {
        this.onDatum = new OnDatum(templateTableName, templateTableAlias, sqlPart)
                .setTableAlias(this.tableAlias);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {
        this.onDatum = new OnDatum(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName)
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
    public void addAbstractComparisonSqlPartDatum(AbstractComparisonSqlPartDatum<OnDatum> abstractComparisonSqlPartDatum) {
        super.addSqlPartDatum((OnDatum) abstractComparisonSqlPartDatum);
    }

    @Override
    public AbstractComparisonSqlPartDatum<OnDatum> getAbstractComparisonSqlPartDatum() {
        return this.onDatum;
    }

    @Override
    public AbstractComparisonSqlPartDatum<OnDatum> getCloneComparisonSqlPartDatum() {
        return this.onDatum.getCloneComparisonSqlPartDatum();
    }
}