package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.TableWhereDataBlock;
import pub.avalonframework.sqlhelper.core.expression.lambda.WhereJoinLambdaCallable;
import pub.avalonframework.sqlhelper.core.expression.lambda.execute.LambdaCallableExecutor;
import pub.avalonframework.sqlhelper.core.helper.*;

import java.util.Collections;
import java.util.List;

/**
 * @author baichao
 */
public final class WhereBuilderBeanJoin<TW extends WhereHelper<TW>,
        S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
        SC extends ColumnHelper<SC>,
        SO extends OnHelper<SO>,
        SW extends WhereHelper<SW>,
        SG extends GroupHelper<SG>,
        SH extends HavingHelper<SH>,
        SS extends SortHelper<SS>> extends AbstractWhereBuilderBean {

    private TW whereHelper;

    private Class<S> tableHelperClass;

    private WhereJoinLambdaCallable<TW, SW> whereJoinLambdaCallable;

    public WhereBuilderBeanJoin(TW whereHelper, Class<S> tableHelperClass, String tableAlias, WhereJoinLambdaCallable<TW, SW> whereJoinLambdaCallable) {
        super(tableAlias);
        this.whereHelper = whereHelper;
        this.tableHelperClass = tableHelperClass;
        this.whereJoinLambdaCallable = whereJoinLambdaCallable;
    }

    @Override
    public List<TableWhereDataBlock> getTableWhereDataBlocks(SqlBuilderConfiguration sqlBuilderConfiguration) {
        return Collections.singletonList(LambdaCallableExecutor.execute(this.whereHelper, this.tableHelperClass, this.tableAlias, this.whereJoinLambdaCallable, sqlBuilderConfiguration));
    }
}