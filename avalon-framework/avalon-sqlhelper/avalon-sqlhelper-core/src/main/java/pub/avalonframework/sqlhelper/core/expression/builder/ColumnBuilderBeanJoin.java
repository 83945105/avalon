package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.TableColumnDataBlock;
import pub.avalonframework.sqlhelper.core.expression.lambda.ColumnLambdaCallable;
import pub.avalonframework.sqlhelper.core.expression.lambda.execute.LambdaCallableExecutor;
import pub.avalonframework.sqlhelper.core.helper.*;

import java.util.Collections;
import java.util.List;

/**
 * @author baichao
 */
public final class ColumnBuilderBeanJoin<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
        TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends AbstractColumnBuilderBean {

    private Class<T> tableHelperClass;

    private ColumnLambdaCallable<TC> columnLambdaCallable;

    public ColumnBuilderBeanJoin(Class<T> tableHelperClass, String tableAlias, ColumnLambdaCallable<TC> columnLambdaCallable) {
        super(tableAlias);
        this.tableHelperClass = tableHelperClass;
        this.columnLambdaCallable = columnLambdaCallable;
    }

    @Override
    public List<TableColumnDataBlock> execute(SqlBuilderConfiguration sqlBuilderConfiguration) {
        return Collections.singletonList(LambdaCallableExecutor.execute(this.tableHelperClass, this.tableAlias, this.columnLambdaCallable, sqlBuilderConfiguration));
    }
}