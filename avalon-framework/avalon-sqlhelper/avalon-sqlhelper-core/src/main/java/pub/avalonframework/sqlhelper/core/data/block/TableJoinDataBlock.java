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

    private TableOnDataBlock tableOnDatum;

    public JoinType getJoinType() {
        return joinType;
    }

    public TableOnDataBlock getTableOnDatum() {
        return tableOnDatum;
    }

    public void setTableOnDatum(TableOnDataBlock tableOnDatum) {
        this.tableOnDatum = tableOnDatum;
    }

    public TableJoinDataBlock appendTableOnDatum(TableOnDataBlock tableOnDatum) {
        if (tableOnDatum == null) {
            return this;
        }
        if (this.tableOnDatum == null) {
            this.setTableOnDatum(tableOnDatum);
            return this;
        }
        this.tableOnDatum.merge(tableOnDatum);
        return this;
    }

    public TableJoinDataBlock merge(TableJoinDataBlock joinTableDatum) {
        if (joinTableDatum == null) {
            return this;
        }
        return appendTableOnDatum(joinTableDatum.getTableOnDatum());
    }
}