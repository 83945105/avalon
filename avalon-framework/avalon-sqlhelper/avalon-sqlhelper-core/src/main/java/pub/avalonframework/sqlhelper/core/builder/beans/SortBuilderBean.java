package pub.avalonframework.sqlhelper.core.builder.beans;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.callback.SortCallback;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.block.TableSortDataBlock;
import pub.avalonframework.sqlhelper.core.helper.SortHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class SortBuilderBean<TG extends SortHelper<TG>> extends AbstractSortBuilderBean {

    private TG sortHelper;

    private SortHelper<?>[] sortHelpers;

    private SortCallback<TG> sortCallback;

    public SortBuilderBean(TG sortHelper, String tableAlias) {
        super(tableAlias);
        this.sortHelper = sortHelper;
    }

    public SortBuilderBean<TG> setSortHelpers(SortHelper<?>[] sortHelpers) {
        this.sortHelpers = sortHelpers;
        return this;
    }

    public SortBuilderBean<TG> setSortCallback(SortCallback<TG> sortCallback) {
        this.sortCallback = sortCallback;
        return this;
    }

    @Override
    public List<TableSortDataBlock> execute(SqlBuilderConfiguration sqlBuilderConfiguration) {
        List<TableSortDataBlock> tableSortDataBlocks = new ArrayList<>(1);
        if (this.sortHelpers != null) {
            for (SortHelper<?> sortHelper : this.sortHelpers) {
                tableSortDataBlocks.add(sortHelper.execute());
            }
        }
        if (this.sortCallback != null) {
            tableSortDataBlocks.add(CallbackExecutor.execute(this.sortHelper, this.sortCallback, sqlBuilderConfiguration));
        }
        return tableSortDataBlocks;
    }
}