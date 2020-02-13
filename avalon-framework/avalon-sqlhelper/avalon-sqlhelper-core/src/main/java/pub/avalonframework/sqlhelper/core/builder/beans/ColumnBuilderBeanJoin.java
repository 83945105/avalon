package pub.avalonframework.sqlhelper.core.builder.beans;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.callback.ColumnCallback;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.block.TableColumnDataBlock;
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

    private ColumnCallback<TC> columnCallback;

    public ColumnBuilderBeanJoin(Class<T> tableHelperClass, String tableAlias, ColumnCallback<TC> columnCallback) {
        super(tableAlias);
        this.tableHelperClass = tableHelperClass;
        this.columnCallback = columnCallback;
    }

    @Override
    public List<TableColumnDataBlock> execute(SqlBuilderConfiguration sqlBuilderConfiguration) {
        return Collections.singletonList(CallbackExecutor.execute(this.tableHelperClass, this.tableAlias, this.columnCallback, sqlBuilderConfiguration));
    }
}