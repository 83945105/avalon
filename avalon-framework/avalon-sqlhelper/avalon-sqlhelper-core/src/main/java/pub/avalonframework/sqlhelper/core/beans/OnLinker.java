package pub.avalonframework.sqlhelper.core.beans;

import pub.avalonframework.sqlhelper.core.callback.OnLinkerCallback;
import pub.avalonframework.sqlhelper.core.data.ComparisonDataBlockLinker;
import pub.avalonframework.sqlhelper.core.helper.OnHelper;

import java.util.List;

/**
 * @author baichao
 */
public interface OnLinker<TO extends OnHelper<TO>> {

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
     * @return {@link OnAndOr}
     */
    OnAndOr<TO> and(OnHelper<?> onHelper);

    /**
     * And
     *
     * @param onLinkerCallback {@link OnLinkerCallback}
     * @return {@link OnAndOr}
     */
    OnAndOr<TO> and(OnLinkerCallback<TO> onLinkerCallback);
}