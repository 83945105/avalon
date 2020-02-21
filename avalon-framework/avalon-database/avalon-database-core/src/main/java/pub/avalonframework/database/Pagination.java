package pub.avalonframework.database;

/**
 * The pagination.
 *
 * @author baichao
 */
public class Pagination implements Limit {

    private long total;

    private long currentPage = 1;

    private long pageSize = 1;

    public Pagination() {
    }

    public Pagination(Long currentPage, Long pageSize) {
        this.setCurrentPage(currentPage);
        this.setPageSize(pageSize);
    }

    public Pagination(Long total, Long currentPage, Long pageSize) {
        this.setTotal(total);
        this.setCurrentPage(currentPage);
        this.setPageSize(pageSize);
    }

    @Override
    public Long getTotal() {
        return this.total;
    }

    @Override
    public void setTotal(Long total) {
        this.total = (total == null || total <= 0) ? 0 : total;
    }

    @Override
    public Long getCurrentPage() {
        return this.currentPage;
    }

    @Override
    public void setCurrentPage(Long currentPage) {
        this.currentPage = (currentPage == null || currentPage <= 0) ? 1 : currentPage;
    }

    @Override
    public Long getPageSize() {
        return this.pageSize;
    }

    @Override
    public void setPageSize(Long pageSize) {
        this.pageSize = (pageSize == null || pageSize <= 0) ? 1 : pageSize;
    }

    @Override
    public Long getPageCount() {
        if (this.total <= 0) {
            return 1L;
        }
        if (this.total % this.pageSize == 0) {
            return this.total / this.pageSize;
        }
        if (total % pageSize > 0) {
            return this.total / this.pageSize + 1;
        }
        return 1L;
    }

    @Override
    public Long getLimit() {
        return this.pageSize;
    }

    @Override
    public Long getOffset() {
        return (this.currentPage - 1) * this.pageSize;
    }
}