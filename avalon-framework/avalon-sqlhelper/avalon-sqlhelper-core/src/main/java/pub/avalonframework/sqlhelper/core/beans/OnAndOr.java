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

    private List<ComparisonDataBlockLinker> comparisonSqlPartDataLinkers = new ArrayList<>();

    @Override
    public List<ComparisonDataBlockLinker> takeoutComparisonSqlPartDataLinkers() {
        List<ComparisonDataBlockLinker> comparisonSqlPartDataLinkers = this.comparisonSqlPartDataLinkers;
        this.comparisonSqlPartDataLinkers = new ArrayList<>();
        return comparisonSqlPartDataLinkers;
    }

    @Override
    public OnAndOr<TO> and(OnHelper<?> onHelper) {
        if (onHelper == null) {
            return this;
        }
        ComparisonDataBlockLinker onDataLinker = new ComparisonDataBlockLinker(LinkType.AND);
        List<OnDataBlock> onDataBlocks = onHelper.takeoutSqlPartData();
        if (onDataBlocks == null || onDataBlocks.size() == 0) {
            return this;
        }
        onDataLinker.setComparisonSqlPartData(onDataBlocks);
        this.comparisonSqlPartDataLinkers.add(onDataLinker);
        return this;
    }

    @Override
    public OnAndOr<TO> and(OnLinkerCallback<TO> onLinkerCallback) {
        if (onLinkerCallback == null) {
            return this;
        }
        OnLinker<TO> onLinker = onLinkerCallback.apply(new OnAndOr<>());
        List<ComparisonDataBlockLinker> comparisonSqlPartDataLinkers = onLinker.takeoutComparisonSqlPartDataLinkers();
        if (comparisonSqlPartDataLinkers == null || comparisonSqlPartDataLinkers.size() == 0) {
            return this;
        }
        ComparisonDataBlockLinker onDataLinker = new ComparisonDataBlockLinker(LinkType.AND);
        onDataLinker.setComparisonSqlPartDataLinkers(comparisonSqlPartDataLinkers);
        this.comparisonSqlPartDataLinkers.add(onDataLinker);
        return this;
    }

    /**
     * or
     *
     * @param onHelper {@link OnHelper}
     * @return {@link OnAndOr}
     */
    public OnAndOr<TO> or(OnHelper<?> onHelper) {
        if (onHelper == null) {
            return this;
        }
        ComparisonDataBlockLinker onDataLinker = new ComparisonDataBlockLinker(LinkType.OR);
        List<OnDataBlock> onDataBlocks = onHelper.takeoutSqlPartData();
        if (onDataBlocks == null || onDataBlocks.size() == 0) {
            return this;
        }
        onDataLinker.setComparisonSqlPartData(onDataBlocks);
        this.comparisonSqlPartDataLinkers.add(onDataLinker);
        return this;
    }

    /**
     * or
     *
     * @param onLinkerCallback {@link OnLinkerCallback}
     * @return {@link OnAndOr}
     */
    public OnAndOr<TO> or(OnLinkerCallback<TO> onLinkerCallback) {
        if (onLinkerCallback == null) {
            return this;
        }
        OnLinker<TO> onLinker = onLinkerCallback.apply(new OnAndOr<>());
        List<ComparisonDataBlockLinker> comparisonSqlPartDataLinkers = onLinker.takeoutComparisonSqlPartDataLinkers();
        if (comparisonSqlPartDataLinkers == null || comparisonSqlPartDataLinkers.size() == 0) {
            return this;
        }
        ComparisonDataBlockLinker onDataLinker = new ComparisonDataBlockLinker(LinkType.OR);
        onDataLinker.setComparisonSqlPartDataLinkers(comparisonSqlPartDataLinkers);
        this.comparisonSqlPartDataLinkers.add(onDataLinker);
        return this;
    }
}