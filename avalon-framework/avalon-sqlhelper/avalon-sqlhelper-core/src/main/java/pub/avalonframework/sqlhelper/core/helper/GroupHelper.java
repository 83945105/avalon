package pub.avalonframework.sqlhelper.core.helper;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.GroupDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.TableGroupDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.builder.GroupDataBlockBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author baichao
 */
public abstract class GroupHelper<T extends GroupHelper<T>> extends Helper {

    private GroupDataBlockBuilder<T> groupDataBlockBuilder;

    @SuppressWarnings("unchecked")
    public GroupHelper(String tableAlias) {
        super(tableAlias);
        this.groupDataBlockBuilder = new GroupDataBlockBuilder<>(tableAlias, (T) this);
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
        this.groupDataBlockBuilder.setTableAlias(tableAlias);
    }

    protected GroupDataBlockBuilder<T> apply(String templateTableName, String templateTableAlias, String sqlPart) {
        this.groupDataBlockBuilder.accept(templateTableName, templateTableAlias, sqlPart);
        return this.groupDataBlockBuilder;
    }

    protected GroupDataBlockBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        this.groupDataBlockBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
        return this.groupDataBlockBuilder;
    }

    public List<GroupDataBlock> takeoutGroupDataBlocks() {
        return this.groupDataBlockBuilder.takeoutDataBlocks();
    }

    public void setSqlBuilderConfiguration(SqlBuilderConfiguration sqlBuilderConfiguration) {
        this.groupDataBlockBuilder.setSqlBuilderConfiguration(sqlBuilderConfiguration);
    }

    public TableGroupDataBlock execute() {
        return execute(this);
    }

    public static TableGroupDataBlock execute(GroupHelper<?> groupHelper) {
        List<GroupDataBlock> groupDataBlocks = groupHelper.takeoutGroupDataBlocks();
        if (groupDataBlocks == null || groupDataBlocks.size() == 0) {
            return null;
        }
        return new TableGroupDataBlock(groupHelper.getTableAlias(), groupDataBlocks);
    }

    public static List<TableGroupDataBlock> execute(GroupHelper<?>... groupHelpers) {
        if (groupHelpers == null || groupHelpers.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.stream(groupHelpers).map(groupHelper -> GroupHelper.execute(groupHelper)).collect(Collectors.toList());
    }
}