package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.TableSortDataBlock;
import pub.avalonframework.sqlhelper.core.expression.lambda.SortLambdaCallable;
import pub.avalonframework.sqlhelper.core.expression.lambda.execute.LambdaCallableExecutor;
import pub.avalonframework.sqlhelper.core.helper.SortHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class SortBuilderBean<TG extends SortHelper<TG>> extends AbstractSortBuilderBean {

    private TG sortHelper;

    private SortHelper<?>[] sortHelpers;

    private SortLambdaCallable<TG> sortLambdaCallable;

    public SortBuilderBean(TG sortHelper, String tableAlias) {
        super(tableAlias);
        this.sortHelper = sortHelper;
    }

    public SortBuilderBean<TG> setSortHelpers(SortHelper<?>[] sortHelpers) {
        this.sortHelpers = sortHelpers;
        return this;
    }

    public SortBuilderBean<TG> setSortLambdaCallable(SortLambdaCallable<TG> sortLambdaCallable) {
        this.sortLambdaCallable = sortLambdaCallable;
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
        if (this.sortLambdaCallable != null) {
            tableSortDataBlocks.add(LambdaCallableExecutor.execute(this.sortHelper, this.sortLambdaCallable, sqlBuilderConfiguration));
        }
        return tableSortDataBlocks;
    }
}