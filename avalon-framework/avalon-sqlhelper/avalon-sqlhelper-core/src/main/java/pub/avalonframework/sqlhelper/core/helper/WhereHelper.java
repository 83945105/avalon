package pub.avalonframework.sqlhelper.core.helper;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.beans.LinkType;
import pub.avalonframework.sqlhelper.core.data.ComparisonDataBlockLinker;
import pub.avalonframework.sqlhelper.core.data.block.TableWhereDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.WhereDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.builder.WhereDataBlockBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author baichao
 */
public abstract class WhereHelper<T extends WhereHelper<T>> extends Helper {

    private WhereDataBlockBuilder<T> whereDataBlockBuilder;

    @SuppressWarnings("unchecked")
    public WhereHelper(String tableAlias) {
        super(tableAlias);
        this.whereDataBlockBuilder = new WhereDataBlockBuilder<>(tableAlias, (T) this);
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
        this.whereDataBlockBuilder.setTableAlias(tableAlias);
    }

    protected WhereDataBlockBuilder<T> apply(String templateTableName, String templateTableAlias, String sqlPart) {
        this.whereDataBlockBuilder.accept(templateTableName, templateTableAlias, sqlPart);
        return this.whereDataBlockBuilder;
    }

    protected WhereDataBlockBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        this.whereDataBlockBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
        return this.whereDataBlockBuilder;
    }

    public List<WhereDataBlock> takeoutSqlPartData() {
        return this.whereDataBlockBuilder.takeoutSqlPartData();
    }

    public void setSqlBuilderConfiguration(SqlBuilderConfiguration sqlBuilderConfiguration) {
        this.whereDataBlockBuilder.setSqlBuilderConfiguration(sqlBuilderConfiguration);
    }

    public TableWhereDataBlock execute() {
        return execute(this);
    }

    public static TableWhereDataBlock execute(WhereHelper<?> whereHelper) {
        List<WhereDataBlock> whereDataBlocks = whereHelper.takeoutSqlPartData();
        if (whereDataBlocks == null || whereDataBlocks.size() == 0) {
            return null;
        }
        return new TableWhereDataBlock(whereHelper.getTableAlias(),
                Collections.singletonList(new ComparisonDataBlockLinker(LinkType.AND).setComparisonSqlPartData(whereDataBlocks)));
    }

    public static List<TableWhereDataBlock> execute(WhereHelper<?>... whereHelpers) {
        if (whereHelpers == null || whereHelpers.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.stream(whereHelpers).map(whereHelper -> WhereHelper.execute(whereHelper)).collect(Collectors.toList());
    }
}