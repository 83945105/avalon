package pub.avalonframework.sqlhelper.core.data.store;

import pub.avalonframework.sqlhelper.core.data.block.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractDataStore extends AbstractDataStoreCache {

    private List<TableColumnDataBlock> selectTableColumnDataBlocks;

    private List<TableColumnDataBlock> insertTableColumnDataBlocks;

    private List<TableColumnDataBlock> updateTableColumnDataBlocks;

    private List<TableWhereDataBlock> tableWhereDataBlocks;

    private List<TableGroupDataBlock> tableGroupDataBlocks;

    private List<TableHavingDataBlock> tableHavingDataBlocks;

    private List<TableSortDataBlock> tableSortDataBlocks;

    private Long limit;

    private Long offset;

    public AbstractDataStore(TableMainDataBlock tableMainDataBlock) {
        super(tableMainDataBlock);
    }

    @Override
    public List<TableColumnDataBlock> getSelectTableColumnDataBlocks() {
        return selectTableColumnDataBlocks;
    }

    @Override
    public List<TableColumnDataBlock> getInsertTableColumnDataBlocks() {
        return insertTableColumnDataBlocks;
    }

    @Override
    public List<TableColumnDataBlock> getUpdateTableColumnDataBlocks() {
        return updateTableColumnDataBlocks;
    }

    @Override
    public List<TableWhereDataBlock> getTableWhereDataBlocks() {
        return tableWhereDataBlocks;
    }

    @Override
    public List<TableGroupDataBlock> getTableGroupDataBlocks() {
        return tableGroupDataBlocks;
    }

    @Override
    public List<TableHavingDataBlock> getTableHavingDataBlocks() {
        return tableHavingDataBlocks;
    }

    @Override
    public List<TableSortDataBlock> getTableSortDataBlocks() {
        return tableSortDataBlocks;
    }

    @Override
    public Long getLimit() {
        return this.limit;
    }

    @Override
    public Long getOffset() {
        return this.offset;
    }

    @Override
    public void addSelectTableColumnDataBlock(TableColumnDataBlock tableColumnDataBlock) {
        if (tableColumnDataBlock == null) {
            return;
        }
        if (this.selectTableColumnDataBlocks == null) {
            this.selectTableColumnDataBlocks = new ArrayList<>();
        }
        this.selectTableColumnDataBlocks.add(tableColumnDataBlock);
    }

    @Override
    public void addInsertTableColumnDataBlock(TableColumnDataBlock tableColumnDataBlock) {
        if (tableColumnDataBlock == null) {
            return;
        }
        if (this.insertTableColumnDataBlocks == null) {
            this.insertTableColumnDataBlocks = new ArrayList<>();
        }
        this.insertTableColumnDataBlocks.add(tableColumnDataBlock);
    }

    @Override
    public void addUpdateTableColumnDataBlock(TableColumnDataBlock tableColumnDataBlock) {
        if (tableColumnDataBlock == null) {
            return;
        }
        if (this.updateTableColumnDataBlocks == null) {
            this.updateTableColumnDataBlocks = new ArrayList<>();
        }
        this.updateTableColumnDataBlocks.add(tableColumnDataBlock);
    }

    @Override
    public void addTableWhereDataBlock(TableWhereDataBlock tableWhereDataBlock) {
        if (tableWhereDataBlock == null) {
            return;
        }
        if (this.tableWhereDataBlocks == null) {
            this.tableWhereDataBlocks = new ArrayList<>();
        }
        this.tableWhereDataBlocks.add(tableWhereDataBlock);
    }

    @Override
    public void addTableGroupDataBlock(TableGroupDataBlock tableGroupDataBlock) {
        if (tableGroupDataBlock == null) {
            return;
        }
        if (this.tableGroupDataBlocks == null) {
            this.tableGroupDataBlocks = new ArrayList<>();
        }
        this.tableGroupDataBlocks.add(tableGroupDataBlock);
    }

    @Override
    public void addTableHavingDataBlock(TableHavingDataBlock tableHavingDataBlock) {
        if (tableHavingDataBlock == null) {
            return;
        }
        if (this.tableHavingDataBlocks == null) {
            this.tableHavingDataBlocks = new ArrayList<>();
        }
        this.tableHavingDataBlocks.add(tableHavingDataBlock);
    }

    @Override
    public void addTableSortDataBlock(TableSortDataBlock tableSortDataBlock) {
        if (tableSortDataBlock == null) {
            return;
        }
        if (this.tableSortDataBlocks == null) {
            this.tableSortDataBlocks = new ArrayList<>();
        }
        this.tableSortDataBlocks.add(tableSortDataBlock);
    }

    @Override
    public void setLimit(Long limit) {
        if (limit == null) {
            return;
        }
        this.limit = limit;
    }

    @Override
    public void setOffset(Long offset) {
        if (offset == null) {
            return;
        }
        this.offset = offset;
    }
}