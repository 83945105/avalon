package pub.avalonframework.sqlhelper.core.expression;

import pub.avalonframework.sqlhelper.core.callback.OnLinkerCallback;
import pub.avalonframework.sqlhelper.core.data.ComparisonDataBlockLinker;
import pub.avalonframework.sqlhelper.core.helper.OnHelper;

import java.util.List;

/**
 * @author baichao
 */
public interface OnAndExpression<TO extends OnHelper<TO>> {

    /**
     * Clean up after each takeout.
     *
     * @return list {@link ComparisonDataBlockLinker}
     */
    List<ComparisonDataBlockLinker> takeoutComparisonDataBlockLinkers();

    /**
     * And
     *
     * @param onHelper {@link OnHelper}
     * @return {@link OnAndOrExpression}
     */
    OnAndOrExpression<TO> and(OnHelper<?> onHelper);

    /**
     * And
     *
     * @param onLinkerCallback {@link OnLinkerCallback}
     * @return {@link OnAndOrExpression}
     */
    OnAndOrExpression<TO> and(OnLinkerCallback<TO> onLinkerCallback);
}