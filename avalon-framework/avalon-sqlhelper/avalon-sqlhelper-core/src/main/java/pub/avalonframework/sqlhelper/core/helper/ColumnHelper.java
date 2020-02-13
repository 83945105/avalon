package pub.avalonframework.sqlhelper.core.helper;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.beans.ColumnHandler;
import pub.avalonframework.sqlhelper.core.data.block.ColumnDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.TableColumnDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.builder.ColumnDataBlockBuilder;
import pub.avalonframework.sqlhelper.core.utils.HelperManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author baichao
 */
public abstract class ColumnHelper<T extends ColumnHelper<T>> extends Helper {

    private ColumnDataBlockBuilder<T> columnDataBlockBuilder;

    @SuppressWarnings("unchecked")
    public ColumnHelper(String tableAlias) {
        super(tableAlias);
        this.columnDataBlockBuilder = new ColumnDataBlockBuilder<>(tableAlias, (T) this);
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
        this.columnDataBlockBuilder.setTableAlias(tableAlias);
    }

    protected ColumnDataBlockBuilder<T> apply(String templateTableName, String templateTableAlias, String sqlPart) {
        this.columnDataBlockBuilder.accept(templateTableName, templateTableAlias, sqlPart);
        return this.columnDataBlockBuilder;
    }

    protected ColumnDataBlockBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName, ColumnHandler... columnHandlers) {
        this.columnDataBlockBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName, columnHandlers);
        return this.columnDataBlockBuilder;
    }

    protected ColumnDataBlockBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        this.columnDataBlockBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
        return this.columnDataBlockBuilder;
    }

    public List<ColumnDataBlock> takeoutColumnDataBlocks() {
        return this.columnDataBlockBuilder.takeoutDataBlocks();
    }

    public void setSqlBuilderConfiguration(SqlBuilderConfiguration sqlBuilderConfiguration) {
        this.columnDataBlockBuilder.setSqlBuilderConfiguration(sqlBuilderConfiguration);
    }

    public TableColumnDataBlock execute() {
        return execute(this);
    }

    public static TableColumnDataBlock execute(ColumnHelper<?> columnHelper) {
        List<ColumnDataBlock> columnDataBlocks = columnHelper.takeoutColumnDataBlocks();
        if (columnDataBlocks == null || columnDataBlocks.size() == 0) {
            columnDataBlocks = HelperManager.defaultColumnData(columnHelper);
        }
        return new TableColumnDataBlock(columnHelper.getTableAlias(), columnDataBlocks);
    }

    public static List<TableColumnDataBlock> execute(ColumnHelper<?>... columnHelpers) {
        if (columnHelpers == null || columnHelpers.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.stream(columnHelpers).map(columnHelper -> ColumnHelper.execute(columnHelper)).collect(Collectors.toList());
    }
}