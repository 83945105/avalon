package pub.avalonframework.sqlhelper.core.engine.helper;

import pub.avalonframework.sqlhelper.core.helper.WhereHelper;

/**
 * @author baichao
 */
public interface HelperWhereEngine<R> extends HelperEngine {

    /**
     * add where sql data
     *
     * @param whereHelpers {@link WhereHelper}
     * @return R
     */
    R where(WhereHelper<?>... whereHelpers);
}