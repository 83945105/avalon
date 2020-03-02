package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.TableSortDataBlock;
import pub.avalonframework.sqlhelper.core.expression.lambda.SortLambdaCallable;
import pub.avalonframework.sqlhelper.core.expression.lambda.execute.LambdaCallableExecutor;
import pub.avalonframework.sqlhelper.core.helper.*;

import java.util.Collections;
import java.util.List;

/**
 * @author baichao
 */
public final class SortBuilderBeanJoin<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
        TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends AbstractSortBuilderBean {

    private Class<T> tableHelperClass;

    private SortLambdaCallable<TS> sortLambdaCallable;

    public SortBuilderBeanJoin(Class<T> tableHelperClass, String tableAlias, SortLambdaCallable<TS> sortLambdaCallable) {
        super(tableAlias);
        this.tableHelperClass = tableHelperClass;
        this.sortLambdaCallable = sortLambdaCallable;
    }

    @Override
    public List<TableSortDataBlock> getTableSortDataBlocks(SqlBuilderConfiguration sqlBuilderConfiguration) {
        return Collections.singletonList(LambdaCallableExecutor.execute(this.tableHelperClass, this.tableAlias, this.sortLambdaCallable, sqlBuilderConfiguration));
    }
}