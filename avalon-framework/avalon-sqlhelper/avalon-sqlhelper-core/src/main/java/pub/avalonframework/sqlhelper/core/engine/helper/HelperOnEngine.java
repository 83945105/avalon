package pub.avalonframework.sqlhelper.core.engine.helper;

import pub.avalonframework.sqlhelper.core.helper.OnHelper;

/**
 * @author baichao
 */
public interface HelperOnEngine<R> extends HelperEngine {

    /**
     * add on sql data
     *
     * @param onHelpers {@link OnHelper}
     * @return R
     */
    R on(OnHelper<?>... onHelpers);
}