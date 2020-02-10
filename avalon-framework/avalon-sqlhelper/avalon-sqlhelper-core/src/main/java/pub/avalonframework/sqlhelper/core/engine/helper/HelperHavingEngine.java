package pub.avalonframework.sqlhelper.core.engine.helper;

import pub.avalonframework.sqlhelper.core.helper.HavingHelper;

/**
 * @author baichao
 */
public interface HelperHavingEngine<R> extends HelperEngine {

    /**
     * add having sql data
     *
     * @param havingHelpers {@link HavingHelper}
     * @return R
     */
    R having(HavingHelper<?>... havingHelpers);
}