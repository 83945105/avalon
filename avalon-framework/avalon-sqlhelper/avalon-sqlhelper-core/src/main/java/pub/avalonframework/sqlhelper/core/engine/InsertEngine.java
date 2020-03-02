package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.sqlhelper.core.api.config.SqlhelperConfiguration;
import pub.avalonframework.sqlhelper.core.builder.InsertColumnBuilder;
import pub.avalonframework.sqlhelper.core.data.block.TableColumnDataBlock;
import pub.avalonframework.sqlhelper.core.data.inject.InsertInjector;
import pub.avalonframework.sqlhelper.core.expression.InsertExpression;
import pub.avalonframework.sqlhelper.core.expression.builder.InsertExpressionBuilder;
import pub.avalonframework.sqlhelper.core.expression.lambda.ColumnLambdaCallable;
import pub.avalonframework.sqlhelper.core.expression.lambda.InsertLambdaExpression;
import pub.avalonframework.sqlhelper.core.expression.lambda.execute.LambdaCallableExecutor;
import pub.avalonframework.sqlhelper.core.helper.*;

/**
 * @author baichao
 */
public interface InsertEngine<T extends TableHelper<T, TC, TO, TW, TG, TH, TS>,
        TC extends ColumnHelper<TC>,
        TO extends OnHelper<TO>,
        TW extends WhereHelper<TW>,
        TG extends GroupHelper<TG>,
        TH extends HavingHelper<TH>,
        TS extends SortHelper<TS>> extends
        Engine<T, TC, TO, TW, TG, TH, TS>,
        InsertInjector<InsertEngine<T, TC, TO, TW, TG, TH, TS>>,
        InsertExpression<InsertEngine<T, TC, TO, TW, TG, TH, TS>>,
        InsertLambdaExpression<TC, InsertEngine<T, TC, TO, TW, TG, TH, TS>>,
        InsertExpressionBuilder<InsertEngine<T, TC, TO, TW, TG, TH, TS>> {

    @Override
    default InsertEngine<T, TC, TO, TW, TG, TH, TS> addInsertTableColumnDataBlock(TableColumnDataBlock tableColumnDataBlock) {
        return InsertInjector.super.addInsertTableColumnDataBlock(tableColumnDataBlock);
    }

    @Override
    default InsertEngine<T, TC, TO, TW, TG, TH, TS> setConfiguration(SqlhelperConfiguration configuration) {
        return InsertInjector.super.setConfiguration(configuration);
    }

    @Override
    default InsertEngine<T, TC, TO, TW, TG, TH, TS> insert(ColumnHelper<?>... columnHelpers) {
        ColumnHelper.execute(columnHelpers).forEach(this::addInsertTableColumnDataBlock);
        return this;
    }

    @Override
    default InsertEngine<T, TC, TO, TW, TG, TH, TS> insert(ColumnLambdaCallable<TC> columnLambdaCallable) {
        return this.addInsertTableColumnDataBlock(LambdaCallableExecutor.execute(getTableHelperClass(), getTableAlias(), columnLambdaCallable, getConfiguration().getSqlBuilder()));
    }

    @Override
    default <FC extends ColumnHelper<FC>> InsertEngine<T, TC, TO, TW, TG, TH, TS> buildInsertColumnExpression(InsertColumnBuilder<FC> insertColumnBuilder) {
        InsertColumnBuilder.execute(insertColumnBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }
}