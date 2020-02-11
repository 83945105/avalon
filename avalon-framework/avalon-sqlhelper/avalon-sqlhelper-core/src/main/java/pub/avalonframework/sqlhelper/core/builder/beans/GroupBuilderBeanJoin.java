package pub.avalonframework.sqlhelper.core.builder.beans;

import pub.avalonframework.sqlhelper.core.callback.GroupCallback;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.TableGroupDatum;
import pub.avalonframework.sqlhelper.core.helper.*;
import pub.avalonframework.sqlhelper.core.option.SqlBuilderOptions;

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

    private GroupCallback<TG> groupCallback;

    public GroupBuilderBeanJoin(Class<T> tableHelperClass, String tableAlias, GroupCallback<TG> groupCallback) {
        super(tableAlias);
        this.tableHelperClass = tableHelperClass;
        this.groupCallback = groupCallback;
    }

    @Override
    public List<TableGroupDatum> execute(SqlBuilderOptions sqlBuilderOptions) {
        return Collections.singletonList(CallbackExecutor.execute(this.tableHelperClass, this.tableAlias, this.groupCallback, sqlBuilderOptions));
    }
}