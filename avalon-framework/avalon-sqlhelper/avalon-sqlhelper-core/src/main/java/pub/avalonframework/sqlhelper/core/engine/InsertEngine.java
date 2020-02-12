package pub.avalonframework.sqlhelper.core.engine;

import pub.avalonframework.sqlhelper.core.block.callback.CallbackInsertBlock;
import pub.avalonframework.sqlhelper.core.block.helper.HelperInsertBlock;
import pub.avalonframework.sqlhelper.core.builder.InsertBuilder;
import pub.avalonframework.sqlhelper.core.builder.InsertColumnBuilder;
import pub.avalonframework.sqlhelper.core.callback.ColumnCallback;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.SqlDataInsertProducer;
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
        SqlDataInsertProducer,
        HelperInsertBlock<InsertEngine<T, TC, TO, TW, TG, TH, TS>>,
        CallbackInsertBlock<TC, InsertEngine<T, TC, TO, TW, TG, TH, TS>>,
        InsertBuilder<InsertEngine<T, TC, TO, TW, TG, TH, TS>> {

    @Override
    default InsertEngine<T, TC, TO, TW, TG, TH, TS> insert(ColumnHelper<?>... columnHelpers) {
        ColumnHelper.execute(columnHelpers).forEach(this::addInsertTableColumnDatum);
        return this;
    }

    @Override
    default InsertEngine<T, TC, TO, TW, TG, TH, TS> insert(ColumnCallback<TC> columnCallback) {
        this.addInsertTableColumnDatum(CallbackExecutor.execute(getTableHelperClass(), getTableAlias(), columnCallback, getConfiguration().getSqlBuilder()));
        return this;
    }

    @Override
    default <FC extends ColumnHelper<FC>> InsertEngine<T, TC, TO, TW, TG, TH, TS> buildInsertColumn(InsertColumnBuilder<FC> insertColumnBuilder) {
        InsertColumnBuilder.execute(insertColumnBuilder, getConfiguration().getSqlBuilder(), () -> this);
        return this;
    }
}