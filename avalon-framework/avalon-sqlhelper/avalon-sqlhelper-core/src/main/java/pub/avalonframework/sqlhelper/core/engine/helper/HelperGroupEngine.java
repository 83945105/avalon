package pub.avalonframework.sqlhelper.core.engine.helper;

import pub.avalonframework.sqlhelper.core.helper.GroupHelper;

/**
 * @author baichao
 */
public interface HelperGroupEngine<R> extends HelperEngine {

    /**
     * add group sql data
     *
     * @param groupHelpers extends {@link GroupHelper} objects
     * @return R
     */
    R groupBy(GroupHelper<?>... groupHelpers);
}