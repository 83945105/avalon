package pub.avalonframework.sqlhelper.core.helper;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.beans.LinkType;
import pub.avalonframework.sqlhelper.core.data.ComparisonSqlPartDataLinker;
import pub.avalonframework.sqlhelper.core.data.TableWhereDatum;
import pub.avalonframework.sqlhelper.core.data.WhereDatum;
import pub.avalonframework.sqlhelper.core.data.builder.WhereSqlPartDatumBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author baichao
 */
public abstract class WhereHelper<T extends WhereHelper<T>> extends Helper {

    private WhereSqlPartDatumBuilder<T> whereSqlPartDatumBuilder;

    @SuppressWarnings("unchecked")
    public WhereHelper(String tableAlias) {
        super(tableAlias);
        this.whereSqlPartDatumBuilder = new WhereSqlPartDatumBuilder<>(tableAlias, (T) this);
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
        this.whereSqlPartDatumBuilder.setTableAlias(tableAlias);
    }

    protected WhereSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String sqlPart) {
        this.whereSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, sqlPart);
        return this.whereSqlPartDatumBuilder;
    }

    protected WhereSqlPartDatumBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        this.whereSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
        return this.whereSqlPartDatumBuilder;
    }

    public List<WhereDatum> takeoutSqlPartData() {
        return this.whereSqlPartDatumBuilder.takeoutSqlPartData();
    }

    public void setSqlBuilderConfiguration(SqlBuilderConfiguration sqlBuilderConfiguration) {
        this.whereSqlPartDatumBuilder.setSqlBuilderConfiguration(sqlBuilderConfiguration);
    }

    public TableWhereDatum execute() {
        return execute(this);
    }

    public static TableWhereDatum execute(WhereHelper<?> whereHelper) {
        List<WhereDatum> whereData = whereHelper.takeoutSqlPartData();
        if (whereData == null || whereData.size() == 0) {
            return null;
        }
        return new TableWhereDatum(whereHelper.getTableAlias(),
                Collections.singletonList(new ComparisonSqlPartDataLinker(LinkType.AND).setComparisonSqlPartData(whereData)));
    }

    public static List<TableWhereDatum> execute(WhereHelper<?>... whereHelpers) {
        if (whereHelpers == null || whereHelpers.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.stream(whereHelpers).map(whereHelper -> WhereHelper.execute(whereHelper)).collect(Collectors.toList());
    }
}