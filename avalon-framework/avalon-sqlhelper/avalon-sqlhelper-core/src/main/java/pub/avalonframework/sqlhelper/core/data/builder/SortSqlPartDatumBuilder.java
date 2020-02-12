package pub.avalonframework.sqlhelper.core.data.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.beans.SortType;
import pub.avalonframework.sqlhelper.core.data.SortDatum;
import pub.avalonframework.sqlhelper.core.helper.Helper;

/**
 * @author baichao
 */
public final class SortSqlPartDatumBuilder<T extends Helper> extends AbstractSqlPartDatumBuilder<T, SortDatum> {

    private SortDatum sortDatum;

    private SqlBuilderConfiguration sqlBuilderConfiguration;

    public SortSqlPartDatumBuilder(String tableAlias, T helper) {
        super(tableAlias, helper);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String sqlPart) {
        this.sortDatum = new SortDatum(templateTableName, templateTableAlias, sqlPart)
                .setTableAlias(this.tableAlias);
    }

    @Override
    public void accept(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {
        this.sortDatum = new SortDatum(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName)
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
        this.sortDatum.setSortType(SortType.ASC);
        this.addSqlPartDatum(this.sortDatum);
        return this.getHelper();
    }

    public T desc() {
        this.sortDatum.setSortType(SortType.DESC);
        this.addSqlPartDatum(this.sortDatum);
        return this.getHelper();
    }
}