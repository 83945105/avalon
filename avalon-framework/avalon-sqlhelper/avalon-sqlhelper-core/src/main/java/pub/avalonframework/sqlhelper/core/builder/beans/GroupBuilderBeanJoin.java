package pub.avalonframework.sqlhelper.core.builder.beans;

import pub.avalonframework.sqlhelper.core.api.config.SqlBuilderConfiguration;
import pub.avalonframework.sqlhelper.core.callback.GroupCallback;
import pub.avalonframework.sqlhelper.core.callback.executor.CallbackExecutor;
import pub.avalonframework.sqlhelper.core.data.block.TableGroupDataBlock;
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

    private GroupCallback<TG> groupCallback;

    public GroupBuilderBeanJoin(Class<T> tableHelperClass, String tableAlias, GroupCallback<TG> groupCallback) {
        super(tableAlias);
        this.tableHelperClass = tableHelperClass;
        this.groupCallback = groupCallback;
    }

    @Override
    public List<TableGroupDataBlock> execute(SqlBuilderConfiguration sqlBuilderConfiguration) {
        return Collections.singletonList(CallbackExecutor.execute(this.tableHelperClass, this.tableAlias, this.groupCallback, sqlBuilderConfiguration));
    }
}