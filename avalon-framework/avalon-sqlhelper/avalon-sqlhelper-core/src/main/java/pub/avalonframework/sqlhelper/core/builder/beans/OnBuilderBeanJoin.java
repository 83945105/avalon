package pub.avalonframework.sqlhelper.core.builder.beans;

import pub.avalonframework.sqlhelper.core.callback.OnJoinCallback;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.TableOnDatum;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

import java.util.Collections;
import java.util.List;

/**
 * @author baichao
 */
public final class OnBuilderBeanJoin<TO extends OnHelper<TO>,
        S extends TableHelper<S, SO, SC, SW, SG, SH, SS>,
        SO extends OnHelper<SO>,
        SC extends ColumnHelper<SC>,
        SW extends WhereHelper<SW>,
        SG extends GroupHelper<SG>,
        SH extends HavingHelper<SH>,
        SS extends SortHelper<SS>> extends AbstractOnBuilderBean {

    private TO onHelper;

    private Class<S> tableHelperClass;

    private OnJoinCallback<TO, SO> onJoinCallback;

    public OnBuilderBeanJoin(TO onHelper, Class<S> tableHelperClass, String tableAlias, OnJoinCallback<TO, SO> onJoinCallback) {
        super(tableAlias);
        this.onHelper = onHelper;
        this.tableHelperClass = tableHelperClass;
        this.onJoinCallback = onJoinCallback;
    }

    @Override
    public List<TableOnDatum> execute(SqlBuilderOptions sqlBuilderOptions) {
        return Collections.singletonList(CallbackExecutor.execute(this.onHelper, this.tableHelperClass, this.tableAlias, this.onJoinCallback, sqlBuilderOptions));
    }
}