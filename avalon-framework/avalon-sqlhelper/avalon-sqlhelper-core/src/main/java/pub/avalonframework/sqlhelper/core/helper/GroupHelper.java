package pub.avalonframework.sqlhelper.core.helper;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.TableGroupDatum;
import pub.avalonframework.sqlhelper.core.data.block.GroupDataBlock;
import pub.avalonframework.sqlhelper.core.data.block.builder.GroupDataBlockBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author baichao
 */
public abstract class GroupHelper<T extends GroupHelper<T>> extends Helper {

    private GroupDataBlockBuilder<T> groupSqlPartDatumBuilder;

    @SuppressWarnings("unchecked")
    public GroupHelper(String tableAlias) {
        super(tableAlias);
        this.groupSqlPartDatumBuilder = new GroupDataBlockBuilder<>(tableAlias, (T) this);
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
        this.groupSqlPartDatumBuilder.setTableAlias(tableAlias);
    }

    protected GroupDataBlockBuilder<T> apply(String templateTableName, String templateTableAlias, String sqlPart) {
        this.groupSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, sqlPart);
        return this.groupSqlPartDatumBuilder;
    }

    protected GroupDataBlockBuilder<T> apply(String templateTableName, String templateTableAlias, String templateColumnName, String templateColumnAlias, String mappingFieldName) {
        this.groupSqlPartDatumBuilder.accept(templateTableName, templateTableAlias, templateColumnName, templateColumnAlias, mappingFieldName);
        return this.groupSqlPartDatumBuilder;
    }

    public List<GroupDataBlock> takeoutSqlPartData() {
        return this.groupSqlPartDatumBuilder.takeoutSqlPartData();
    }

    public void setSqlBuilderConfiguration(SqlBuilderConfiguration sqlBuilderConfiguration) {
        this.groupSqlPartDatumBuilder.setSqlBuilderConfiguration(sqlBuilderConfiguration);
    }

    public TableGroupDatum execute() {
        return execute(this);
    }

    public static TableGroupDatum execute(GroupHelper<?> groupHelper) {
        List<GroupDataBlock> groupDataBlocks = groupHelper.takeoutSqlPartData();
        if (groupDataBlocks == null || groupDataBlocks.size() == 0) {
            return null;
        }
        return new TableGroupDatum(groupHelper.getTableAlias(), groupDataBlocks);
    }

    public static List<TableGroupDatum> execute(GroupHelper<?>... groupHelpers) {
        if (groupHelpers == null || groupHelpers.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.stream(groupHelpers).map(groupHelper -> GroupHelper.execute(groupHelper)).collect(Collectors.toList());
    }
}