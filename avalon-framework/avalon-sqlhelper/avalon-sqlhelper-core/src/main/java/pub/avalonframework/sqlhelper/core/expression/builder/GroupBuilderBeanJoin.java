package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.TableGroupDataBlock;
import pub.avalonframework.sqlhelper.core.expression.lambda.GroupLambdaCallable;
import pub.avalonframework.sqlhelper.core.expression.lambda.execute.LambdaCallableExecutor;
import pub.avalonframework.sqlhelper.core.helper.*;

import java.util.Collections;
import java.util.List;

/**
 * @author baichao
 */
public final class GroupBuilderBeanJoin<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
        TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends AbstractGroupBuilderBean {

    private Class<T> tableHelperClass;

    private GroupLambdaCallable<TG> groupLambdaCallable;

    public GroupBuilderBeanJoin(Class<T> tableHelperClass, String tableAlias, GroupLambdaCallable<TG> groupLambdaCallable) {
        super(tableAlias);
        this.tableHelperClass = tableHelperClass;
        this.groupLambdaCallable = groupLambdaCallable;
    }

    @Override
    public List<TableGroupDataBlock> execute(SqlBuilderConfiguration sqlBuilderConfiguration) {
        return Collections.singletonList(LambdaCallableExecutor.execute(this.tableHelperClass, this.tableAlias, this.groupLambdaCallable, sqlBuilderConfiguration));
    }
}