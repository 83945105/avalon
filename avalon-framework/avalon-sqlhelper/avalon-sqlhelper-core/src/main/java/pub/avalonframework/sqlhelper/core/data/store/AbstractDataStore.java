package pub.avalonframework.sqlhelper.core.data.store;

import pub.avalonframework.sqlhelper.core.data.block.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public abstract class AbstractDataStore extends AbstractDataStoreCache {

    private List<TableColumnDataBlock> selectTableColumnData;

    private List<TableColumnDataBlock> insertTabColumnData;

    private List<TableColumnDataBlock> updateTabColumnData;

    private List<TableWhereDataBlock> tableWhereData;

    private List<TableGroupDataBlock> tableGroupData;

    private List<TableHavingDataBlock> tableHavingData;

    private List<TableSortDataBlock> tableSortData;

    private Long limit;

    private Long offset;

    public AbstractDataStore(TableMainDataBlock mainTableDatum) {
        super(mainTableDatum);
    }

    @Override
    public List<TableColumnDataBlock> getSelectTableColumnData() {
        return this.selectTableColumnData;
    }

    @Override
    public List<TableColumnDataBlock> getInsertTableColumnData() {
        return this.insertTabColumnData;
    }

    @Override
    public List<TableColumnDataBlock> getUpdateTableColumnData() {
        return this.updateTabColumnData;
    }

    @Override
    public List<TableWhereDataBlock> getTableWhereData() {
        return this.tableWhereData;
    }

    @Override
    public List<TableGroupDataBlock> getTableGroupData() {
        return this.tableGroupData;
    }

    @Override
    public List<TableHavingDataBlock> getTableHavingData() {
        return this.tableHavingData;
    }

    @Override
    public List<TableSortDataBlock> getTableSortData() {
        return this.tableSortData;
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
    public void addSelectTableColumnDatum(TableColumnDataBlock tableColumnDatum) {
        if (tableColumnDatum == null) {
            return;
        }
        if (this.selectTableColumnData == null) {
            this.selectTableColumnData = new ArrayList<>();
        }
        this.selectTableColumnData.add(tableColumnDatum);
    }

    @Override
    public void addInsertTableColumnDatum(TableColumnDataBlock tableColumnDatum) {
        if (tableColumnDatum == null) {
            return;
        }
        if (this.insertTabColumnData == null) {
            this.insertTabColumnData = new ArrayList<>();
        }
        this.insertTabColumnData.add(tableColumnDatum);
    }

    @Override
    public void addUpdateTableColumnDatum(TableColumnDataBlock tableColumnDatum) {
        if (tableColumnDatum == null) {
            return;
        }
        if (this.updateTabColumnData == null) {
            this.updateTabColumnData = new ArrayList<>();
        }
        this.updateTabColumnData.add(tableColumnDatum);
    }

    @Override
    public void addTableWhereDatum(TableWhereDataBlock tableWhereDatum) {
        if (tableWhereDatum == null) {
            return;
        }
        if (this.tableWhereData == null) {
            this.tableWhereData = new ArrayList<>();
        }
        this.tableWhereData.add(tableWhereDatum);
    }

    @Override
    public void addTableGroupDatum(TableGroupDataBlock tableGroupDatum) {
        if (tableGroupDatum == null) {
            return;
        }
        if (this.tableGroupData == null) {
            this.tableGroupData = new ArrayList<>();
        }
        this.tableGroupData.add(tableGroupDatum);
    }

    @Override
    public void addTableHavingDatum(TableHavingDataBlock tableHavingDatum) {
        if (tableHavingDatum == null) {
            return;
        }
        if (this.tableHavingData == null) {
            this.tableHavingData = new ArrayList<>();
        }
        this.tableHavingData.add(tableHavingDatum);
    }

    @Override
    public void addTableSortDatum(TableSortDataBlock tableSortDatum) {
        if (tableSortDatum == null) {
            return;
        }
        if (this.tableSortData == null) {
            this.tableSortData = new ArrayList<>();
        }
        this.tableSortData.add(tableSortDatum);
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