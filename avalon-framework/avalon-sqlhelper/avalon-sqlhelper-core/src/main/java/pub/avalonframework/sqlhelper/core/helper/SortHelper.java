package pub.avalonframework.sqlhelper.core.helper;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.TableSortDatum;
import pub.avalonframework.sqlhelper.core.data.block.SortDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.builder.SortDataBlockBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author baichao
 */
public abstract class SortHelper<T extends SortHelper<T>> extends Helper {

    private SortDataBlockBuilder<T> sortDataBlockBuilder;

    @SuppressWarnings("unchecked")
    public SortHelper(String tableAlias) {
        super(tableAlias);
        this.sortDataBlockBuilder = new SortDataBlockBuilder<>(tableAlias, (T) this);
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
        this.sortDataBlockBuilder.setTableAlias(tableAlias);
    }

    protected SortDataBlockBuilder<T> apply(String templateTableName, String templateTableAlias, String sqlPart) {
        this.sortDataBlockBuilder.accept(templateTableName, templateTableAlias, sqlPart);
        return this.sortDataBlockBuilder;
    }

    protected SortDataBlockBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        this.sortDataBlockBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
        return this.sortDataBlockBuilder;
    }

    public List<SortDataBlock> takeoutSqlPartData() {
        return this.sortDataBlockBuilder.takeoutSqlPartData();
    }

    public void setSqlBuilderConfiguration(SqlBuilderConfiguration sqlBuilderConfiguration) {
        this.sortDataBlockBuilder.setSqlBuilderConfiguration(sqlBuilderConfiguration);
    }

    public TableSortDatum execute() {
        return execute(this);
    }

    public static TableSortDatum execute(SortHelper<?> sortHelper) {
        List<SortDataBlock> sortDataBlocks = sortHelper.takeoutSqlPartData();
        if (sortDataBlocks == null || sortDataBlocks.size() == 0) {
            return null;
        }
        return new TableSortDatum(sortHelper.getTableAlias(), sortDataBlocks);
    }

    public static List<TableSortDatum> execute(SortHelper<?>... sortHelpers) {
        if (sortHelpers == null || sortHelpers.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.stream(sortHelpers).map(sortHelper -> SortHelper.execute(sortHelper)).collect(Collectors.toList());
    }
}