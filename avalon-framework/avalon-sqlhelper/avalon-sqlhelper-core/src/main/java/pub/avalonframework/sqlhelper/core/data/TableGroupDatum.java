package pub.avalonframework.sqlhelper.core.data;

import pub.avalonframework.sqlhelper.core.data.block.GroupDataBlock;
import pub.avalonframework.sqlhelper.core.utils.ExceptionUtils;

import java.util.List;

/**
 * @author baichao
 */
public final class TableGroupDatum {

    private String tableAlias;

    private List<GroupDataBlock> groupDataBlocks;

    public TableGroupDatum(String tableAlias, List<GroupDataBlock> groupDataBlocks) {
        if (tableAlias == null) {
            ExceptionUtils.tableAliasNullException();
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