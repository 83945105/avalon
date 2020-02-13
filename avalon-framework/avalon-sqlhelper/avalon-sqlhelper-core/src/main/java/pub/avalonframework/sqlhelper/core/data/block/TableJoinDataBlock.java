package pub.avalonframework.sqlhelper.core.data.block;

import pub.avalonframework.sqlhelper.core.beans.JoinType;

/**
 * @author baichao
 */
public final class TableJoinDataBlock extends AbstractTableDataBlock {

    private JoinType joinType;

    public TableJoinDataBlock(JoinType joinType, Class<?> tableHelperClass, String tableName, String tableAlias) {
        super(tableHelperClass, tableName, tableAlias);
        this.joinType = joinType;
    }

    private TableOnDataBlock tableOnDataBlock;

    public JoinType getJoinType() {
        return joinType;
    }

    public TableOnDataBlock getTableOnDataBlock() {
        return tableOnDataBlock;
    }

    public void setTableOnDataBlock(TableOnDataBlock tableOnDataBlock) {
        this.tableOnDataBlock = tableOnDataBlock;
    }

    public TableJoinDataBlock appendTableOnDataBlock(TableOnDataBlock tableOnDataBlock) {
        if (tableOnDataBlock == null) {
            return this;
        }
        if (this.tableOnDataBlock == null) {
            this.setTableOnDataBlock(tableOnDataBlock);
            return this;
        }
        this.tableOnDataBlock.merge(tableOnDataBlock);
        return this;
    }

    public TableJoinDataBlock merge(TableJoinDataBlock tableJoinDataBlock) {
        if (tableJoinDataBlock == null) {
            return this;
        }
        return appendTableOnDataBlock(tableJoinDataBlock.getTableOnDataBlock());
    }
}