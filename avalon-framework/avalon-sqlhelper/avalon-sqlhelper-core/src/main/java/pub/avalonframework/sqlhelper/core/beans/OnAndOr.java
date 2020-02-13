package pub.avalonframework.sqlhelper.core.beans;

import pub.avalonframework.sqlhelper.core.callback.OnLinkerCallback;
import pub.avalonframework.sqlhelper.core.data.ComparisonDataBlockLinker;
import pub.avalonframework.sqlhelper.core.data.block.OnDataBlock;
import pub.avalonframework.sqlhelper.core.helper.OnHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class OnAndOr<TO extends OnHelper<TO>> implements OnLinker<TO> {

    private List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = new ArrayList<>();

    @Override
    public List<ComparisonDataBlockLinker> takeoutComparisonDataBlockLinkers() {
        List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = this.comparisonDataBlockLinkers;
        this.comparisonDataBlockLinkers = new ArrayList<>();
        return comparisonDataBlockLinkers;
    }

    @Override
    public OnAndOr<TO> and(OnHelper<?> onHelper) {
        if (onHelper == null) {
            return this;
        }
        ComparisonDataBlockLinker comparisonDataBlockLinker = new ComparisonDataBlockLinker(LinkType.AND);
        List<OnDataBlock> onDataBlocks = onHelper.takeoutOnDataBlocks();
        if (onDataBlocks == null || onDataBlocks.size() == 0) {
            return this;
        }
        comparisonDataBlockLinker.setComparisonDataBlocks(onDataBlocks);
        this.comparisonDataBlockLinkers.add(comparisonDataBlockLinker);
        return this;
    }

    @Override
    public OnAndOr<TO> and(OnLinkerCallback<TO> onLinkerCallback) {
        if (onLinkerCallback == null) {
            return this;
        }
        OnLinker<TO> onLinker = onLinkerCallback.apply(new OnAndOr<>());
        List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = onLinker.takeoutComparisonDataBlockLinkers();
        if (comparisonDataBlockLinkers == null || comparisonDataBlockLinkers.size() == 0) {
            return this;
        }
        ComparisonDataBlockLinker comparisonDataBlockLinker = new ComparisonDataBlockLinker(LinkType.AND);
        comparisonDataBlockLinker.setComparisonDataBlockLinkers(comparisonDataBlockLinkers);
        this.comparisonDataBlockLinkers.add(comparisonDataBlockLinker);
        return this;
    }

    /**
     * Or
     *
     * @param onHelper {@link OnHelper}
     * @return {@link OnAndOr}
     */
    public OnAndOr<TO> or(OnHelper<?> onHelper) {
        if (onHelper == null) {
            return this;
        }
        ComparisonDataBlockLinker comparisonDataBlockLinker = new ComparisonDataBlockLinker(LinkType.OR);
        List<OnDataBlock> onDataBlocks = onHelper.takeoutOnDataBlocks();
        if (onDataBlocks == null || onDataBlocks.size() == 0) {
            return this;
        }
        comparisonDataBlockLinker.setComparisonDataBlocks(onDataBlocks);
        this.comparisonDataBlockLinkers.add(comparisonDataBlockLinker);
        return this;
    }

    /**
     * Or
     *
     * @param onLinkerCallback {@link OnLinkerCallback}
     * @return {@link OnAndOr}
     */
    public OnAndOr<TO> or(OnLinkerCallback<TO> onLinkerCallback) {
        if (onLinkerCallback == null) {
            return this;
        }
        OnLinker<TO> onLinker = onLinkerCallback.apply(new OnAndOr<>());
        List<ComparisonDataBlockLinker> comparisonDataBlockLinkers = onLinker.takeoutComparisonDataBlockLinkers();
        if (comparisonDataBlockLinkers == null || comparisonDataBlockLinkers.size() == 0) {
            return this;
        }
        ComparisonDataBlockLinker comparisonDataBlockLinker = new ComparisonDataBlockLinker(LinkType.OR);
        comparisonDataBlockLinker.setComparisonDataBlockLinkers(comparisonDataBlockLinkers);
        this.comparisonDataBlockLinkers.add(comparisonDataBlockLinker);
        return this;
    }
}