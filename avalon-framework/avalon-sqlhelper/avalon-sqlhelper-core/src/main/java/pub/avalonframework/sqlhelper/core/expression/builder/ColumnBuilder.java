package pub.avalonframework.sqlhelper.core.expression.builder;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.data.block.GroupType;
import pub.avalonframework.sqlhelper.core.data.inject.ColumnDataInjector;
import pub.avalonframework.sqlhelper.core.expression.ColumnExpression;
import pub.avalonframework.sqlhelper.core.expression.lambda.ColumnLambdaCallable;
import pub.avalonframework.sqlhelper.core.expression.lambda.ColumnLambdaExpression;
import pub.avalonframework.sqlhelper.core.expression.lambda.SubQueryColumnLambdaCallable;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.utils.HelperUtils;

import java.util.function.Supplier;

/**
 * @author baichao
 */
public abstract class ColumnBuilder<TC extends ColumnHelper<TC>> implements ColumnExpression<ColumnBuilder<TC>>, ColumnLambdaExpression<TC, ColumnBuilder<TC>> {

    private TC columnHelper;
    private String tableAlias;

    {
        this.columnHelper = HelperUtils.findColumnHelperClassFromAncestorsGenericType(this);
    }

    public ColumnBuilder() {
        this.tableAlias = this.columnHelper.getTableAlias();
    }

    public ColumnBuilder(String tableAlias) {
        this.columnHelper.setTableAlias(tableAlias);
        this.tableAlias = tableAlias;
    }

    @Override
    public ColumnBuilder<TC> virtualColumn(Object columnValue, String columnAlias) {
        return null;
    }

    @Override
    public ColumnBuilder<TC> groupColumn(GroupType groupType, ColumnLambdaCallable<TC> columnLambdaCallable) {
        return null;
    }

    @Override
    public <S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
            SC extends ColumnHelper<SC>,
            SO extends OnHelper<SO>,
            SW extends WhereHelper<SW>,
            SG extends GroupHelper<SG>,
            SH extends HavingHelper<SH>,
            SS extends SortHelper<SS>> ColumnBuilder<TC> groupColumn(Class<S> tableHelperClass, String tableAlias, GroupType groupType, ColumnLambdaCallable<SC> columnLambdaCallable) {
        return null;
    }

    @Override
    public ColumnBuilder<TC> subQueryColumn(String columnAlias, SubQueryColumnLambdaCallable<TC> subQueryColumnLambdaCallable) {
        return null;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public void execute(SqlBuilderConfiguration sqlBuilderConfiguration, Supplier<ColumnDataInjector<?>> supplier) {
        execute(this, sqlBuilderConfiguration, supplier);
    }

    public static <FC extends ColumnHelper<FC>> void execute(ColumnBuilder<FC> columnBuilder, SqlBuilderConfiguration sqlBuilderConfiguration, Supplier<ColumnDataInjector<?>> supplier) {
        if (supplier == null) {
            return;
        }
        ColumnDataInjector<?> columnDataInjector = supplier.get();
        if (columnDataInjector == null) {
            return;
        }
        //TODO 待实现
    }
}