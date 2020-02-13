package pub.avalonframework.sqlhelper.core.builder.beans;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.callback.OnJoinCallback;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.block.TableOnDataBlock;
import pub.avalonframework.sqlhelper.core.helper.*;

import java.util.Collections;
import java.util.List;

/**
 * @author baichao
 */
public final class OnBuilderBeanJoin<TO extends OnHelper<TO>,
        S extends TableHelper<S, SC, SO, SW, SG, SH, SS>,
        SC extends ColumnHelper<SC>,
        SO extends OnHelper<SO>,
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
    public List<TableOnDataBlock> execute(SqlBuilderConfiguration sqlBuilderConfiguration) {
        return Collections.singletonList(CallbackExecutor.execute(this.onHelper, this.tableHelperClass, this.tableAlias, this.onJoinCallback, sqlBuilderConfiguration));
    }
}