package pub.avalonframework.cloud.gar.beans;

import java.util.List;

/**
 * @author 白超
 * @date 2019/4/3
 */
public class Table {

    private List<TableColumn> tableColumns;

    public List<TableColumn> getTableColumns() {
        return tableColumns;
    }

    public void setTableColumns(List<TableColumn> tableColumns) {
        this.tableColumns = tableColumns;
    }
}
