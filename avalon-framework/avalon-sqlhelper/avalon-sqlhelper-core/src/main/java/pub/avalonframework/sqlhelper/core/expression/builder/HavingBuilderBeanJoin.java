package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.TableHavingDataBlock;
import pub.avalonframework.sqlhelper.core.expression.lambda.HavingJoinLambdaCallable;
import pub.avalonframework.sqlhelper.core.expression.lambda.execute.LambdaCallableExecutor;
import pub.avalonframework.sqlhelper.core.helper.*;

import java.util.Collections;
import java.util.List;

/**
 * @author baichao
 */
public final class HavingBuilderBeanJoin<TH extends HavingHelper<TH>,
        S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
        SC extends ColumnHelper<SC>,
        SO extends OnHelper<SO>,
        SW extends WhereHelper<SW>,
        SG extends GroupHelper<SG>,
        SH extends HavingHelper<SH>,
        SS extends SortHelper<SS>> extends AbstractHavingBuilderBean {

    private TH havingHelper;

    private Class<S> tableHelperClass;

    private HavingJoinLambdaCallable<TH, SH> havingJoinLambdaCallable;

    public HavingBuilderBeanJoin(TH havingHelper, Class<S> tableHelperClass, String tableAlias, HavingJoinLambdaCallable<TH, SH> havingJoinLambdaCallable) {
        super(tableAlias);
        this.havingHelper = havingHelper;
        this.tableHelperClass = tableHelperClass;
        this.havingJoinLambdaCallable = havingJoinLambdaCallable;
    }

    @Override
    public List<TableHavingDataBlock> getTableHavingDataBlocks(SqlBuilderConfiguration sqlBuilderConfiguration) {
        return Collections.singletonList(LambdaCallableExecutor.execute(this.havingHelper, this.tableHelperClass, this.tableAlias, this.havingJoinLambdaCallable, sqlBuilderConfiguration));
    }
}