package pub.avalonframework.sqlhelper.core.engine.helper;

import pub.avalonframework.sqlhelper.core.helper.SortHelper;

/**
 * @author baichao
 */
public interface HelperSortEngine<R> extends HelperEngine {

    /**
     * add sort sql data
     *
     * @param sortHelpers {@link SortHelper}
     * @return R
     */
    R orderBy(SortHelper<?>... sortHelpers);
}