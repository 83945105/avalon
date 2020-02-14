package pub.avalonframework.sqlhelper.core.data.store;

import pub.avalonframework.sqlhelper.core.data.block.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractDataStore<R> extends AbstractDataStoreCache<R> {

    private List<TableColumnDataBlock> selectTableColumnDataBlocks;

    private List<TableColumnDataBlock> insertTableColumnDataBlocks;

    private List<TableColumnDataBlock> updateTableColumnDataBlocks;

    private List<TableWhereDataBlock> tableWhereDataBlocks;

    private List<TableGroupDataBlock> tableGroupDataBlocks;

    private List<TableHavingDataBlock> tableHavingDataBlocks;

    private List<TableSortDataBlock> tableSortDataBlocks;

    private Long limit;

    private Long offset;

    public AbstractDataStore(R owner, TableMainDataBlock tableMainDataBlock) {
        super(owner, tableMainDataBlock);
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
    public R addSelectTableColumnDataBlock(TableColumnDataBlock tableColumnDataBlock) {
        if (tableColumnDataBlock == null) {
            return owner;
        }
        if (this.selectTableColumnDataBlocks == null) {
            this.selectTableColumnDataBlocks = new ArrayList<>();
        }
        this.selectTableColumnDataBlocks.add(tableColumnDataBlock);
        return owner;
    }

    @Override
    public R addInsertTableColumnDataBlock(TableColumnDataBlock tableColumnDataBlock) {
        if (tableColumnDataBlock == null) {
            return owner;
        }
        if (this.insertTableColumnDataBlocks == null) {
            this.insertTableColumnDataBlocks = new ArrayList<>();
        }
        this.insertTableColumnDataBlocks.add(tableColumnDataBlock);
        return owner;
    }

    @Override
    public R addUpdateTableColumnDataBlock(TableColumnDataBlock tableColumnDataBlock) {
        if (tableColumnDataBlock == null) {
            return owner;
        }
        if (this.updateTableColumnDataBlocks == null) {
            this.updateTableColumnDataBlocks = new ArrayList<>();
        }
        this.updateTableColumnDataBlocks.add(tableColumnDataBlock);
        return owner;
    }

    @Override
    public R addTableWhereDataBlock(TableWhereDataBlock tableWhereDataBlock) {
        if (tableWhereDataBlock == null) {
            return owner;
        }
        if (this.tableWhereDataBlocks == null) {
            this.tableWhereDataBlocks = new ArrayList<>();
        }
        this.tableWhereDataBlocks.add(tableWhereDataBlock);
        return owner;
    }

    @Override
    public R addTableGroupDataBlock(TableGroupDataBlock tableGroupDataBlock) {
        if (tableGroupDataBlock == null) {
            return owner;
        }
        if (this.tableGroupDataBlocks == null) {
            this.tableGroupDataBlocks = new ArrayList<>();
        }
        this.tableGroupDataBlocks.add(tableGroupDataBlock);
        return owner;
    }

    @Override
    public R addTableHavingDataBlock(TableHavingDataBlock tableHavingDataBlock) {
        if (tableHavingDataBlock == null) {
            return owner;
        }
        if (this.tableHavingDataBlocks == null) {
            this.tableHavingDataBlocks = new ArrayList<>();
        }
        this.tableHavingDataBlocks.add(tableHavingDataBlock);
        return owner;
    }

    @Override
    public R addTableSortDataBlock(TableSortDataBlock tableSortDataBlock) {
        if (tableSortDataBlock == null) {
            return owner;
        }
        if (this.tableSortDataBlocks == null) {
            this.tableSortDataBlocks = new ArrayList<>();
        }
        this.tableSortDataBlocks.add(tableSortDataBlock);
        return owner;
    }

    @Override
    public R setLimit(Long limit) {
        if (limit == null) {
            return owner;
        }
        this.limit = limit;
        return owner;
    }

    @Override
    public R setOffset(Long offset) {
        if (offset == null) {
            return owner;
        }
        this.offset = offset;
        return owner;
    }
}