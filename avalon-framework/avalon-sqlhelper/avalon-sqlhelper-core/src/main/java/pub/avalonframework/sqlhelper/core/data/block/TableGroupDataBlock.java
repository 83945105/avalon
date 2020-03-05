package pub.avalonframework.sqlhelper.core.data.block;

import pub.avalonframework.sqlhelper.core.helper.TableAliasNullException;

import java.util.List;

/**
 * @author baichao
 */
public final class TableGroupDataBlock {

    private String tableAlias;

    private List<GroupDataBlock> groupDataBlocks;

    public TableGroupDataBlock(String tableAlias, List<GroupDataBlock> groupDataBlocks) {
        if (tableAlias == null) {
            throw new TableAliasNullException();
        }
        this.tableAlias = tableAlias;
        this.groupDataBlocks = groupDataBlocks;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public List<GroupDataBlock> getGroupDataBlocks() {
        return groupDataBlocks;
    }
}