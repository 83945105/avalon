package pub.avalonframework.database;

import java.util.function.Function;

/**
 * The limit interface.
 *
 * @author baichao
 */
public interface Limit {

    /**
     * Get total
     *
     * @return The total
     */
    Long getTotal();

    /**
     * Set total
     *
     * @param total The total
     */
    void setTotal(Long total);

    /**
     * Get current page
     *
     * @return The current page
     */
    Long getCurrentPage();

    /**
     * Set current page
     *
     * @param currentPage The current page
     */
    void setCurrentPage(Long currentPage);

    /**
     * Get page size
     *
     * @return The page size
     */
    Long getPageSize();

    /**
     * Set page size
     *
     * @param pageSize The page size
     */
    void setPageSize(Long pageSize);

    /**
     * Get page count
     *
     * @return The page count
     */
    Long getPageCount();

    /**
     * Get limit
     *
     * @return The limit
     */
    Long getLimit();

    /**
     * Get offset
     *
     * @return The offset
     */
    Long getOffset();

    /**
     * execute limit
     *
     * @param limit    The limit
     * @param callback The callback
     */
    static <L extends Limit> void execute(L limit, Function<L, Boolean> callback) {
        if (!callback.apply(limit) || limit.getCurrentPage() >= limit.getPageCount()) {
            return;
        }
        limit.setCurrentPage(limit.getCurrentPage() + 1);
        execute(limit, callback);
    }
}