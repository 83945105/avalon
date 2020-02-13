package pub.avalonframework.sqlhelper.core.helper;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.beans.LinkType;
import pub.avalonframework.sqlhelper.core.data.ComparisonSqlPartDataLinker;
import pub.avalonframework.sqlhelper.core.data.TableHavingDatum;
import pub.avalonframework.sqlhelper.core.data.block.HavingDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.builder.HavingDataBlockBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author baichao
 */
public abstract class HavingHelper<T extends HavingHelper<T>> extends Helper {

    private HavingDataBlockBuilder<T> havingDataBlockBuilder;

    @SuppressWarnings("unchecked")
    public HavingHelper(String tableAlias) {
        super(tableAlias);
        this.havingDataBlockBuilder = new HavingDataBlockBuilder<>(tableAlias, (T) this);
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
        this.havingDataBlockBuilder.setTableAlias(tableAlias);
    }

    protected HavingDataBlockBuilder<T> apply(String templateTableName, String templateTableAlias, String sqlPart) {
        this.havingDataBlockBuilder.accept(templateTableName, templateTableAlias, sqlPart);
        return this.havingDataBlockBuilder;
    }

    protected HavingDataBlockBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {
        this.havingDataBlockBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName, columnHandlers);
        return this.havingDataBlockBuilder;
    }

    protected HavingDataBlockBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        this.havingDataBlockBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
        return this.havingDataBlockBuilder;
    }

    public List<HavingDataBlock> takeoutSqlPartData() {
        return this.havingDataBlockBuilder.takeoutSqlPartData();
    }

    public void setSqlBuilderConfiguration(SqlBuilderConfiguration sqlBuilderConfiguration) {
        this.havingDataBlockBuilder.setSqlBuilderConfiguration(sqlBuilderConfiguration);
    }

    public TableHavingDatum execute() {
        return execute(this);
    }

    public static TableHavingDatum execute(HavingHelper<?> havingHelper) {
        List<HavingDataBlock> havingDataBlocks = havingHelper.takeoutSqlPartData();
        if (havingDataBlocks == null || havingDataBlocks.size() == 0) {
            return null;
        }
        return new TableHavingDatum(havingHelper.getTableAlias(),
                Collections.singletonList(new ComparisonSqlPartDataLinker(LinkType.AND).setComparisonSqlPartData(havingDataBlocks)));
    }

    public static List<TableHavingDatum> execute(HavingHelper<?>... havingHelpers) {
        if (havingHelpers == null || havingHelpers.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.stream(havingHelpers).map(havingHelper -> HavingHelper.execute(havingHelper)).collect(Collectors.toList());
    }
}