package pub.avalonframework.cloud.gar.beans;

import pub.avalon.beans.Limit;

import java.util.List;

/**
 * @author 白超
 * @date 2019/4/3
 */
public class PageListResult<L extends Limit, R> {

    private L limit;

    private List<? extends R> rows;

    public PageListResult(L limit, List<? extends R> rows) {
        this.limit = limit;
        this.rows = rows;
    }

    public L getLimit() {
        return limit;
    }

    public void setLimit(L limit) {
        this.limit = limit;
    }

    public List<? extends R> getRows() {
        return rows;
    }

    public void setRows(List<? extends R> rows) {
        this.rows = rows;
    }
}
