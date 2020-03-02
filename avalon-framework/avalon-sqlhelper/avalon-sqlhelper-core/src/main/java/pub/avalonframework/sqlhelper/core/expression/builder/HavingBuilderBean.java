package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.TableHavingDataBlock;
import pub.avalonframework.sqlhelper.core.expression.lambda.HavingLambdaCallable;
import pub.avalonframework.sqlhelper.core.expression.lambda.execute.LambdaCallableExecutor;
import pub.avalonframework.sqlhelper.core.helper.HavingHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class HavingBuilderBean<TH extends HavingHelper<TH>> extends AbstractHavingBuilderBean {

    private TH havingHelper;

    private HavingHelper<?>[] havingHelpers;

    private HavingLambdaCallable<TH> havingLambdaCallable;

    public HavingBuilderBean(TH havingHelper, String tableAlias) {
        super(tableAlias);
        this.havingHelper = havingHelper;
    }

    public HavingBuilderBean<TH> setHavingHelpers(HavingHelper<?>[] havingHelpers) {
        this.havingHelpers = havingHelpers;
        return this;
    }

    public HavingBuilderBean<TH> setHavingLambdaCallable(HavingLambdaCallable<TH> havingLambdaCallable) {
        this.havingLambdaCallable = havingLambdaCallable;
        return this;
    }

    @Override
    public List<TableHavingDataBlock> getTableHavingDataBlocks(SqlBuilderConfiguration sqlBuilderConfiguration) {
        List<TableHavingDataBlock> tableHavingDataBlocks = new ArrayList<>(1);
        if (this.havingHelpers != null) {
            for (HavingHelper<?> havingHelper : this.havingHelpers) {
                tableHavingDataBlocks.add(havingHelper.execute());
            }
        }
        if (this.havingLambdaCallable != null) {
            tableHavingDataBlocks.add(LambdaCallableExecutor.execute(this.havingHelper, this.havingLambdaCallable, sqlBuilderConfiguration));
        }
        return tableHavingDataBlocks;
    }
}