package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.TableColumnDataBlock;
import pub.avalonframework.sqlhelper.core.expression.lambda.ColumnLambdaCallable;
import pub.avalonframework.sqlhelper.core.expression.lambda.execute.LambdaCallableExecutor;
import pub.avalonframework.sqlhelper.core.helper.ColumnHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baichao
 */
public final class ColumnBuilderBean<TC extends ColumnHelper<TC>> extends AbstractColumnBuilderBean {

    private TC columnHelper;

    private ColumnHelper<?>[] columnHelpers;

    private ColumnLambdaCallable<TC> columnLambdaCallable;

    public ColumnBuilderBean(TC columnHelper, String tableAlias) {
        super(tableAlias);
        this.columnHelper = columnHelper;
    }

    public ColumnBuilderBean<TC> setColumnHelpers(ColumnHelper<?>[] columnHelpers) {
        this.columnHelpers = columnHelpers;
        return this;
    }

    public ColumnBuilderBean<TC> setColumnLambdaCallable(ColumnLambdaCallable<TC> columnLambdaCallable) {
        this.columnLambdaCallable = columnLambdaCallable;
        return this;
    }

    @Override
    public List<TableColumnDataBlock> getTableColumnDataBlocks(SqlBuilderConfiguration sqlBuilderConfiguration) {
        List<TableColumnDataBlock> tableColumnDataBlocks = new ArrayList<>(1);
        if (this.columnHelpers != null) {
            for (ColumnHelper<?> columnHelper : this.columnHelpers) {
                tableColumnDataBlocks.add(columnHelper.execute());
            }
        }
        if (this.columnLambdaCallable != null) {
            tableColumnDataBlocks.add(LambdaCallableExecutor.execute(this.columnHelper, this.columnLambdaCallable, sqlBuilderConfiguration));
        }
        return tableColumnDataBlocks;
    }
}